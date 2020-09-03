import java.awt.Color;
import java.awt.Graphics2D;

public class Button
{
	int x;			// Instance's actual X position.
	int y;			// Instance's actual Y position.
	int w;			// Width.
	int h;			// Height.
	int x2;			// Where it wants to go X.
	int y2;			// Where it wants to go Y.
	String text;	// What it says.
	
	boolean hover;	// If it's being hovered by the mouse.
	Color color;
	
	
	// Constructor
	public Button(int x_, int y_, int w_, int h_, int x2_, int y2_, String text_)
	{
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		x2 = x2_;
		y2 = y2_;
		text = text_;
		
		hover = false;
		color = Color.orange;
	}
	
	
	// Step
	public void update()
	{
		System.out.println(text);
	}
	
	
	// Draw
	public void draw(Graphics2D g2d)
	{
		g2d.setColor(color);
		g2d.fillRect(x, y, w, h);
	}
}
