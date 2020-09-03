import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileInputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pelicula
{
	int x, y, w;
	
	String titulo;
	String año;
	String genero;
	String duracion;
	String descripcion;
	
	Image portada = null;
	int imgw;
	
	
	public Pelicula(int x_, int y_, String t, String a, String g, String du, String de, String url_)
	{
		x = x_;
		y = y_;
		
		titulo = 	  t;
		año = 		  a;
		genero = 	  g;
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
	}
	
	
	// Step
	public void update() {}
		
	
	// Draw
	public void draw(Graphics2D g2d)
	{
		// Square
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(x, y, w, Main.len);
		
		// Text
		g2d.setColor(Color.orange);
		g2d.drawString(titulo+" ("+año+")", x, y);
		g2d.drawString(descripcion, x, y+64);
		g2d.drawString(genero+" "+duracion+" min.", x, Main.len - 32);
		
		// Cover
		g2d.drawImage(portada, x, y, imgw, Main.len, null);
	}
}
