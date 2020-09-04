import java.awt.Label;
import javax.swing.JComboBox;

public class InputCombo
{
	Label label;
	JComboBox combo;
	
	
	public InputCombo(int x, int y, String title, String def)
	{
		int lw = 100;
		int fw = Main.rw/2 - 84;
		int h = 24;
		
		label = new Label(title);
		label.setBounds(x, y, lw, h);
		Main.frame.add(label);
		
		combo = new JComboBox();
		combo.setBounds(x+lw, y, fw, h);
		Main.frame.add(combo);
	}
	
	/*
	public String getItem()
	{
		return combo.getText();
	}*/
}
