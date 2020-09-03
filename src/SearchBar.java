import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.TextField;


public class SearchBar
{
	TextField campo;
	String def;
	
	int x;			// Instance's actual X position.
	int y;			// Instance's actual Y position.
	int w;			// Width.
	int h;			// Height.
	int xf;			// Where it wants to go X.
	int yf;			// Where it wants to go Y.
	String text;	// What it says.
	
	int xs;
	int ys;
	
	boolean hover;	// If it's being hovered by the mouse.
	Color color;
	
	Font font;
	
	
	// Constructor
	public SearchBar(int x_, int y_, int w_, int h_, int xf_, int yf_, String text_)
	{
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		xs = x;
		ys = y;
		xf = xf_;
		yf = yf_;
		def = text_;
		
		hover = false;
		color = Color.orange;
		
		campo = new TextField(def);
		campo.setBounds(x-w/2, y-h/2, w, h);
		campo.setBackground(color);
		font = new Font("SansSerif", Font.BOLD, 64);
		campo.setFont(font);
		Main.frame.add(campo);
	}
	
	
	// Step
	public void update()
	{
		x = lerp(x, xf, 0.05);
		y = lerp(y, yf, 0.05);
		
		campo.setLocation(x-w/2, y-h/2);		
	}
	
	
	// Draw
	public void draw(Graphics2D g2d)
	{
		//g2d.setColor(color);
		//g2d.fillRect(x-w/2, y-h/2, w, h);
	}
	
	
	public int lerp(int p1, int p2, double alpha)
	{
		return (int) (p1 + alpha * (p2 - p1));
	}
}
