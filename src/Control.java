import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Control extends JPanel implements ActionListener
{
	// ID-lization or something.
	private static final long serialVersionUID = 1L;
	
	Timer loopTimer;	// Timer for the program's loop.
	
	ArrayList<Boton> botones;
	ArrayList<SearchBar> campos;
	
	// Specifics
	Boton		but_actors;
	SearchBar 	search;
	
	String deftext;

	// Constructor
	public Control()
	{
		setFocusable(true);
		
		loopTimer = new Timer(10, this);
		loopTimer.start();
		
		botones = new ArrayList<Boton>();
		campos = new ArrayList<SearchBar>();
		
		but_actors = new Boton(-200, Main.rh/2, 100, 100, Main.rw/2, Main.rh/2, "Todos los\nActores");
		botones.add(but_actors);
		
		deftext = "Buscar (película, director, actor...)";
		search = new SearchBar(100, 100, (int)(Main.rw/1.5), 84, 800, 200, deftext);
		campos.add(search);
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
	}
	
	
	// Step Event
	public void actionPerformed(ActionEvent e)
	{
		if (search!=null && search.campo.getText().contentEquals(deftext))
		{
			but_actors.entry = false;
		}
		else
		{
			but_actors.entry = true;
		}
		
		// Actualiza todos los botones.
		for (int i=0; i<botones.size(); i++)
			botones.get(i).update();
		
		// Actualiza todos los campos de texto.
		for (int i=0; i<campos.size(); i++)
			campos.get(i).update();
			
		
		// Draws everything at the end.
		repaint();
	}
}
