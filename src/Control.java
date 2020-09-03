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
	ArrayList<CampoTexto> campos;

	// Constructor
	public Control()
	{
		setFocusable(true);
		
		loopTimer = new Timer(10, this);
		loopTimer.start();
		
		botones = new ArrayList<Boton>();
		campos = new ArrayList<CampoTexto>();
		
		botones.add(new Boton(100, 100, 100, 100, 800, 600, "lol"));
		campos.add(new CampoTexto(100, 100, 100, 100, 800, 200, "lol"));
	}
	
	
	// Evento de Dibujo
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Fondo
		//g2d.setColor(Color.black);
		//g2d.fillRect(0, 0, 1920, 1080);
		
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
