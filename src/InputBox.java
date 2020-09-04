import java.awt.Label;
import java.awt.TextField;

import javax.swing.JComboBox;

enum inp { TEXTBOX, COMBO };

public class InputBox
{
	Label label;
	TextField campo;
	JComboBox combo;
	inp type;
	
	public InputBox(int x, int y, String title, String def, inp type_)
	{
		int lw = 100;
		int fw = Main.rw/2 - 84;
		int h = 24;
		
		label = new Label(title);
		label.setBounds(x, y, lw, h);
		Main.frame.add(label);
		
		type = type_;
		if (type == inp.TEXTBOX)
		{
			campo = new TextField(def);
			campo.setBounds(x+lw, y, fw, h);
			Main.frame.add(campo);
		}
		else
		{
			combo = new JComboBox();
			combo.setBounds(x+lw, y, fw, h);
			Main.frame.add(combo);
		}
	}
	
	
	public String getItem()
	{
		return campo.getText();
	}
	
	public void remove()
	{
		Main.frame.remove(label);
		Main.frame.remove(campo);
	}
}
