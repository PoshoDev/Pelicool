import java.awt.Color;
import java.awt.Graphics2D;

public class Boton
{
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
	boolean entry;
	Color color;
	
	
	// Constructor
	public Boton(int x_, int y_, int w_, int h_, int xf_, int yf_, String text_)
	{
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		xs = x;
		ys = y;
		xf = xf_;
		yf = yf_;
		text = text_;
		
		hover = false;
		entry = true;
		color = Color.orange;
	}
	
	
	// Step
	public void update()
	{
		x = lerp(x, entry ? xf : xs, 0.05);
		y = lerp(y, entry ? yf : ys, 0.05);
	}
	
	
	// Draw
	public void draw(Graphics2D g2d)
	{
		// Square
		g2d.setColor(color);
		g2d.fillRect(x-w/2, y-h/2, w, h);
		
		// Text
		g2d.setColor(Color.black);
		g2d.drawString(text, x, y);
	}
	
	
	public void perform()
	{
		
	}
	
	
	public int lerp(int p1, int p2, double alpha)
	{
		return (int) (p1 + alpha * (p2 - p1));
	}
}
