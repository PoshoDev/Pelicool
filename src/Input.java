import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements MouseListener, MouseMotionListener
{
	public int mx, my;
	private boolean mouseClicked, mousePressed;
	private boolean[] currentlyPressed, pressed;
	
	public Input()
	{
		mx = 0;
		my = 0;
	}
	
	public boolean isPressed(int keyCode)
	{
		if (!pressed[keyCode] && currentlyPressed[keyCode])
		{
			pressed[keyCode] = true;
			return true;
		}
		return false;
	}
	
	public boolean isCurrentlyPressed(int keyCode)
	{
		return currentlyPressed[keyCode];
	}

	public void mouseDragged(MouseEvent e)
	{
		mx = (int) e.getPoint().getX();
		my = (int) e.getPoint().getY();
	}

	public void mouseMoved(MouseEvent e)
	{
		mx = (int) e.getPoint().getX();
		my = (int) e.getPoint().getY();
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e)
	{
		mx = (int) e.getPoint().getX();
		my = (int) e.getPoint().getY();
		
		mousePressed = true;
	}

	public void mouseReleased(MouseEvent e)
	{
		mx = (int) e.getPoint().getX();
		my = (int) e.getPoint().getY();
	
		mouseClicked = true;
		mousePressed = false;
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	public void clearMouseClick()
	{
		mouseClicked = false;
	}

	public void keyReleased(KeyEvent e)
	{
		currentlyPressed[e.getKeyCode()] = false;
		pressed[e.getKeyCode()] = false;
	}

	public boolean isMouseClicked()
	{
		return mouseClicked;
	}
	
	public boolean isMousePressed()
	{
		return mousePressed;
	}
	
	public int getMouseX()
	{
		return mx;
	}
	
	public int getMouseY()
	{
		return my;
	}
}
