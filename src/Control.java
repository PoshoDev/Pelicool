import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


enum stg { MAIN, SEARCH, DEL_MOVIE, UPD_MOVIE, NEW_MOVIE, NEW_MOVIE_SEND, NEW_ACTOR, NEW_ACTOR_SEND };

public class Control extends JPanel implements ActionListener
{
	// ID-lization or something.
	private static final long serialVersionUID = 1L;
	
	Timer loopTimer;	// Timer for the program's loop.
	
	static ArrayList<Boton> botones;
	static ArrayList<SearchBar> campos;
	
	static ArrayList<Pelicula> peliculas;
	
	// Specifics
	Boton		but_actors;
	SearchBar 	search;
	
	String deftext;
	String prevtext;
	
	static stg stage;
	stg prev;
	
	static Pelicula editing;
	
	DBConnect connect;

	// Constructor
	public Control()
	{
		setFocusable(true);
		
		loopTimer = new Timer(10, this);
		loopTimer.start();
		
		botones = new ArrayList<Boton>();
		campos = new ArrayList<SearchBar>();
		
		peliculas = new ArrayList<Pelicula>();
		
		but_actors = new Boton(-2000, Main.rh/2, 100, 100, Main.rw/2, Main.rh/2, "Nueva Película", but_type.NEW_MOVIE);
		botones.add(but_actors);
		
		deftext = "Buscar (película, director, actor...)";
		prevtext = deftext;
		search = new SearchBar((int)(Main.rw/2), -300, (int)(Main.rw/1.5), 84, (int)(Main.rw/2), 400, deftext);
		campos.add(search);
		
		stage = stg.MAIN;
		prev = stage;
		
		connect = new DBConnect();		
	}
	
	
	// Evento de Dibujo
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Fondo
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, 1920, 1080);
		
		// Dibuja todos los botones.
		for (int i=0; i<botones.size(); i++)
			botones.get(i).draw(g2d);
		
		// Dibuja todos los campos de texto.
		for (int i=0; i<campos.size(); i++)
			campos.get(i).draw(g2d);
		
		// Dibuja todas las películas encontradas.
		for (int i=0; i<peliculas.size(); i++)
			peliculas.get(i).draw(g2d);
	}
	
	
	// Step Event
	public void actionPerformed(ActionEvent e)
	{
		// Check change
		if (stage != prev)
		{
			prev = stage;
			
			switch(stage)
			{
				case MAIN:				roomReset();			break;
				case DEL_MOVIE:			startDelMovie(editing);	break;
				case UPD_MOVIE:			startUpdMovie(editing);	break;
				case NEW_MOVIE:			startNewMovie();		break;
				case NEW_MOVIE_SEND:	startNewMovieSend();	break;
			}
		}
		
		
		if (search != null)
		{
			String tx = search.campo.getText();
			
			if (!tx.contentEquals(prevtext))
			{
				prevtext = tx;
				
				if (connect!=null && !tx.contentEquals("") && !tx.contentEquals(deftext))
				{
					connect.Buscar(tx);
					
					search.entry = false;
					but_actors.entry = false;
				}
				else
				{
					search.entry = true;
					but_actors.entry = true;
					
					if (peliculas != null)
						peliculas.clear();
				}
			}
		}
		
		
		
		// Actualiza todos los botones.
		for (int i=0; i<botones.size(); i++)
			botones.get(i).update();
		
		// Actualiza todos los campos de texto.
		for (int i=0; i<campos.size(); i++)
			campos.get(i).update();
		
		// Actualiza todas las películas.
		for (int i=0; i<peliculas.size(); i++)
			peliculas.get(i).update();
			
		
		// Draws everything at the end.
		repaint();
	}
	
	public void startNewMovie()
	{
		stage = stg.NEW_MOVIE;
		
		for (int i=0; i<botones.size(); i++)
			botones.get(i).entry = false;
		
		search.entry = false;
		

		peliculas.clear();
		
		int x = (int)(Main.rw/3/2);
		int y = 164;
		
		peliculas.add(new Pelicula(x, y));
	}
	
	public void startNewMovieSend()
	{
		Pelicula mov = peliculas.get(0);
		
		mov.setData();
		connect.sendNewMovie(mov);
		
		mov.removeFields();
		
		peliculas.clear();
		
		for (int i=0; i<botones.size(); i++)
			botones.get(i).entry = true;
		
		search.entry = true;
		
		stage = stg.MAIN;
	}
	
	
	public void startUpdMovie(Pelicula mov)
	{
		Pelicula noo = new Pelicula(-100, -100, mov.titulo, mov.año, mov.genero, mov.duracion, mov.descripcion, mov.url);
		mov.setData();
		connect.updMovie(noo, mov);
				
		mov.removeFields();
		
		for (int i=0; i<peliculas.size(); i++)
			peliculas.get(i).removeFields();
		
		peliculas.clear();
		
		for (int i=0; i<botones.size(); i++)
			botones.get(i).entry = true;
		
		search.entry = true;
		search.campo.setText(search.def);
		
		stage = stg.MAIN;
		
		System.out.println("del");
	}
	
	
	public void startDelMovie(Pelicula mov)
	{
		connect.delMovie(mov);
		
		mov.removeFields();
		
		for (int i=0; i<peliculas.size(); i++)
			peliculas.get(i).removeFields();
		
		peliculas.clear();
		
		for (int i=0; i<botones.size(); i++)
			botones.get(i).entry = true;
		
		search.entry = true;
		search.campo.setText(search.def);
		
		stage = stg.MAIN;
		
		System.out.println("del");
	}
	
	
	public void roomReset()
	{
		for (int i=0; i<peliculas.size(); i++)
			peliculas.get(i).removeFields();
		
		peliculas.clear();
		
		for (int i=0; i<botones.size(); i++)
			botones.get(i).entry = true;
		
		search.entry = true;
		search.campo.setText(search.def);
		
		stage = stg.MAIN;
	}
}
