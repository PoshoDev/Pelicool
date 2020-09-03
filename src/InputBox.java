import java.awt.Label;
import java.awt.TextField;

public class InputBox
{
	Label label;
	TextField campo;
	
	
	public InputBox(int x, int y, String title, String def)
	{
		int lw = 100;
		int fw = Main.rw/2 - 84;
		int h = 24;
		
		label = new Label(title);
		label.setBounds(x, y, lw, h);
		Main.frame.add(label);
		
		campo = new TextField(def);
		campo.setBounds(x+lw, y, fw, h);
		Main.frame.add(campo);
	}
	
}
