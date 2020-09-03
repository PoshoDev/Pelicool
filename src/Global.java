import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Global extends JPanel implements ActionListener
{
	// ID-lization or something.
	private static final long serialVersionUID = 1L;
	
	Timer loopTimer;	// Timer for the program's loop.
	ArrayList<Button> buttons;

	// Constructor
	public Global()
	{
		setFocusable(true);
		
		loopTimer = new Timer(10, this);
		loopTimer.start();
		
		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(100, 100, 100, 100, 100, 100, "lol"));
	}
	
	
	// Draw Event
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
	}
	
	
	// Step Event
	public void actionPerformed(ActionEvent e)
	{
		for (int i=0; i<buttons.size(); i++)
			buttons.get(i).update();
			
		
		// Draws everything at the end.
		repaint();
	}
}
