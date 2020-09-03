import java.awt.Color;
import java.awt.Graphics2D;

enum but_type { EDIT, ALL_ACTORS };

public class Boton
{
	int x;			// Instance's actual X position.
	int y;			// Instance's actual Y position.
	int w;			// Width.
	int h;			// Height.
	int xf;			// Where it wants to go X.
	int yf;			// Where it wants to go Y.
	String text;	// What it says.
	but_type type;
	
	boolean pressed;
	
	int xs;
	int ys;
	
	boolean hover;	// If it's being hovered by the mouse.
	boolean entry;
	Color color;
	Color color_hover;
	
	
	// Constructor
	public Boton(int x_, int y_, int w_, int h_, int xf_, int yf_, String text_, but_type type_)
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
		type = type_;
		
		hover = false;
		entry = true;
		pressed = false;
		
		color = Color.orange;
		color_hover = Color.red;
	}
	
	
	// Step
	public void update()
	{
		// Mouse
		int mx = Main.input.getMouseX();
		int my = Main.input.getMouseY();
		boolean inside = (mx>x && mx<x+w && my>y && my<y+h);
		
		if (!hover && inside)
		{
			hover = true;
		}
		else if (hover && !inside)
		{
			hover = false;
		}
		
		if (hover && Main.input.isMousePressed())
			pressed = true;
				
		// Movement
		x = lerp(x, entry ? xf : xs, 0.05);
		y = lerp(y, entry ? yf : ys, 0.05);
	}
	
	
	// Draw
	public void draw(Graphics2D g2d)
	{
		// Square
		g2d.setColor(hover ? color_hover : color);
		g2d.fillRect(x, y, w, h);
		
		// Text
		g2d.setColor(Color.black);
		g2d.drawString(text, x, y+h/2);
		
		// Hover
		if (hover)
		{
			g2d.setColor(Color.white);
			g2d.drawRect(x, y, w, h);
		}
	}
	
	
	public void perform()
	{
		switch(type)
		{
			case EDIT:
				
			break;
		}
	}
	
	
	public int lerp(int p1, int p2, double alpha)
	{
		return (int) (p1 + alpha * (p2 - p1));
	}
}
