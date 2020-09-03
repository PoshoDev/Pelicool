import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pelicula
{
	int x, y, w, h, stx;
	
	String titulo;
	String año;
	String genero;
	String duracion;
	String descripcion;
	String url;
	
	Image portada = null;
	int imgw;
	
	boolean hover;
	boolean edit;
	Boton edit_button;
	
	ArrayList<InputBox> inputs;
	
	
	public Pelicula(int x_, int y_, String t, String a, String g, String du, String de, String url_)
	{
		x = x_;
		y = y_;
		
		stx = x + 192;
		
		titulo = 	  t;
		año = 		  a;
		genero = 	  g.substring(0, 1).toUpperCase() + g.substring(1);
		duracion = 	  du;
		descripcion = de;
		url =		  url_;
		
		try
		{
			URL link = new URL(url_);
			portada = ImageIO.read(link);
			
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
		
		inputs = new ArrayList<InputBox>();
	}
	
	
	// Step
	public void update()
	{
		int mx = Main.input.getMouseX();
		int my = Main.input.getMouseY();
		boolean inside = (mx>x && mx<x+w && my>y && my<y+h);
		
		if (!hover && inside)
		{
			hover = true;
			edit_button = new Boton(x+w-64, y, 64, 64, x+w-64, y, "Editar", but_type.EDIT);
		}
		else if (hover && !inside)
		{
			hover = false;
			edit_button = null;
		}
		
		if (edit_button != null)
		{
			edit_button.update();
			
			if (edit_button.pressed)
			{
				edit_button.pressed = false;
				edit = true;
				
				int i = 0;
				inputs.add(new InputBox(stx, y+8+(24*i++), "Título:", titulo));
				inputs.add(new InputBox(stx, y+8+(24*i++), "Año:", año));
				inputs.add(new InputBox(stx, y+8+(24*i++), "Género:", genero));
				inputs.add(new InputBox(stx, y+8+(24*i++), "Duración:", duracion));
				inputs.add(new InputBox(stx, y+8+(24*i++), "Descripción:", descripcion));
				inputs.add(new InputBox(stx, y+8+(24*i++), "URL Imagen:", url));
			}
		}
	}
		
	
	// Draw
	public void draw(Graphics2D g2d)
	{
		// Square
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(x, y, w, h);
		
		
		// Text
		if (!edit)
		{
			g2d.setColor(Color.orange);
			
			g2d.setFont(new Font("SansSerif", Font.BOLD, 48));
			g2d.drawString(titulo+" ("+año+")", stx, y+48+8);
			
			
			g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
			FontMetrics fm= g2d.getFontMetrics();
			//g2d.drawString(descripcion, x, y+64);
			drawTextUgly(descripcion, stx, y+48+8*5, fm, g2d);
			
			g2d.drawString(genero, stx, y+Main.len - 24-12);
			g2d.drawString(duracion+" min.", stx, y+Main.len - 12);
		}
		
		
		// Cover
		g2d.drawImage(portada, x, y, imgw, Main.len, null);
		
		
		// Button
		if (hover)
		{
			edit_button.draw(g2d);
			
			g2d.setColor(Color.white);
			g2d.drawRect(x, y, w, h);
		}
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
