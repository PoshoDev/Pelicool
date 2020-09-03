import java.awt.Color;
import javax.swing.JFrame;

public class Main
{
	static JFrame frame =  new JFrame();
	
	static int rw = 1720;
	static int rh = 1000;
	
	static int len = 256;
	
	static Input input = new Input();
	
	
	public static void main(String args[])
	{
		frame.pack();
		frame.setSize(rw, rh);
		frame.setBackground(Color.black);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(input);
		frame.addMouseMotionListener(input);
		frame.add(new Control());
		frame.setVisible(true);
	}
}
