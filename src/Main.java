import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main
{
	static JFrame frame =  new JFrame();
	
	public static void main(String args[])
	{
		frame.pack();
		frame.setSize(1920, 1080);
		frame.setBackground(Color.black);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JTextField campo = new JTextField("lamao", 20);
		//campo.setBounds(100, 100, 100, 100);
		//frame.add(campo);
		
		frame.add(new Control());
		
		
		
		frame.setVisible(true);
	}
}
