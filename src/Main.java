import javax.swing.JFrame;

public class Main
{
	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.pack();
		frame.setSize(1280, 720);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Global());
		frame.setVisible(true);
	}
}
