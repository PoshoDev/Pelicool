import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;

public class Pelicula
{
	int x, y, w, h;
	
	String titulo;
	String año;
	String genero;
	String duracion;
	String descripcion;
	
	Image portada = null;
	int imgw;
	
	boolean hover;
	boolean edit;
	Boton edit_button;
	
	
	public Pelicula(int x_, int y_, String t, String a, String g, String du, String de, String url_)
	{
		x = x_;
		y = y_;
		
		titulo = 	  t;
		año = 		  a;
		genero = 	  g.substring(0, 1).toUpperCase() + g.substring(1);
		duracion = 	  du;
		descripcion = de;
		
		try
		{
			URL url = new URL(url_);
			portada = ImageIO.read(url);
			
			int ow = portada.getWidth(null);
			int oh = portada.getHeight(null);
			
			imgw = ow * Main.len / oh;
		}
		catch(Exception ex)
		{
			System.out.println("ERROR: "+ex);
		}
		
		w = (int)(Main.rw/1.5);
		h = Main.len;
		
		hover = false;
		edit = false;
		edit_button = null;
	}
	
	
	// Step
	public void update()
	{
		
		Main.input.isMouseClicked(); // lmao
		System.out.println(Main.input.getMouseX() + ", "+ Main.input.getMouseY());
		
		int mx = Main.input.getMouseX();
		int my = Main.input.getMouseY();
		boolean inside = (mx>x && mx<x+w && my>y && my<y+h);
		
		if (!hover && inside)
		{
			hover = true;
			edit_button = new Boton(w-64, y, 64, 64, Main.rw/2, Main.rh/2, "Editar");
		}
		else if (hover && !inside)
		{
			hover = false;
			edit_button = null;
		}
	}
		
	
	// Draw
	public void draw(Graphics2D g2d)
	{
		// Square
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(x, y, w, h);
		
		
		// Text
		int stx = x + 192;
		g2d.setColor(Color.orange);
		
		g2d.setFont(new Font("SansSerif", Font.BOLD, 48));
		g2d.drawString(titulo+" ("+año+")", stx, y+48+8);
		
		
		g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
		FontMetrics fm= g2d.getFontMetrics();
		//g2d.drawString(descripcion, x, y+64);
		drawTextUgly(descripcion, stx, y+48+8*5, fm, g2d);
		
		g2d.drawString(genero, stx, y+Main.len - 24-12);
		g2d.drawString(duracion+" min.", stx, y+Main.len - 12);
		
		
		// Cover
		g2d.drawImage(portada, x, y, imgw, Main.len, null);
		
		
		// Button
		if (hover)
			edit_button.draw(g2d);
	}
	
	
	private void drawTextUgly(String text, int startX, int startY, FontMetrics textMetrics, Graphics2D g2)
	{
	    // Ugly code to wrap text
	    int lineHeight = textMetrics.getHeight();
	    String textToDraw = text;
	    String[] arr = textToDraw.split(" ");
	    int nIndex = 0;
	    //int startX = 319;
	    //int startY = 113;
	    while ( nIndex < arr.length )
	    {
	        String line = arr[nIndex++];
	        while ( ( nIndex < arr.length ) && (textMetrics.stringWidth(line + " " + arr[nIndex]) < Main.rw/2) )
	        {
	            line = line + " " + arr[nIndex];
	            nIndex++;
	        }
	        g2.drawString(line, startX, startY);
	        startY = startY + lineHeight;
	    }
	}
}
