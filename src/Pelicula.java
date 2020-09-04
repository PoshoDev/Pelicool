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
	
	Boton button_edit;
	Boton button_save;
	Boton button_delete;
	
	ArrayList<InputBox> inputs;
	
	
	public Pelicula(int x_, int y_, String t, String a, String g, String du, String de, String url_)
	{
		setDefaults(x_, y_);
		
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
		
		edit = false;
	}
	
	
	public Pelicula(int x_, int y_)
	{
		setDefaults(x_, y_);
		
		titulo = 	  "";
		año = 		  "";
		genero = 	  "";
		duracion = 	  "";
		descripcion = "";
		url =		  "";
		
		button_edit = new Boton(x+w-64, y, 64, 64, x+w-64, y, "Cancelar", but_type.CANCEL);
		
		startEdit();
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
			button_edit = new Boton(x+w-64, y, 64, 64, x+w-64, y, "Editar", but_type.EDIT);
		}
		else if (hover && !inside)
		{
			if (!edit)
			{
				hover = false;
				button_edit = null;
			}
		}
		
		if (button_edit != null)
		{
			button_edit.update();
			
			if (button_edit.pressed)
			{
				switch(button_edit.type)
				{
					case EDIT:
						startEdit();
					break;
					
					case CANCEL:
						button_edit.pressed = false;
						button_edit.type = but_type.EDIT;
						button_edit.text = "Editar";
						edit = false;
						
						button_edit = null;
						button_save = null;
						button_delete = null;
						
						removeFields();
						
						System.out.println("kansel");
					break;
				}
			}
			
			
			// Other buttons
			if (button_save != null)
				button_save.update();
			
			if (button_delete != null)
				button_delete.update();
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
			if (button_edit != null)
				button_edit.draw(g2d);
			
			g2d.setColor(Color.white);
			g2d.drawRect(x, y, w, h);
		}
		
		// Other buttons
		if (button_save != null)
			button_save.draw(g2d);
		
		if (button_delete != null)
			button_delete.draw(g2d);
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
	
	
	private void startEdit()
	{
		button_edit.pressed = false;
		button_edit.type = but_type.CANCEL;
		button_edit.text = "Cancelar";
		edit = true;
		
		int i = 0;
		inputs.add(new InputBox(stx, y+8+(24*i++), "Título:", titulo, inp.TEXTBOX));
		inputs.add(new InputBox(stx, y+8+(24*i++), "Año:", año, inp.TEXTBOX));
		inputs.add(new InputBox(stx, y+8+(24*i++), "Género:", genero, inp.TEXTBOX));
		inputs.add(new InputBox(stx, y+8+(24*i++), "Duración:", duracion, inp.TEXTBOX));
		inputs.add(new InputBox(stx, y+8+(24*i++), "Descripción:", descripcion, inp.TEXTBOX));
		inputs.add(new InputBox(stx, y+8+(24*i++), "URL Imagen:", url, inp.TEXTBOX));
		
		button_save = new Boton(Main.rw+100, y+h-64, 64, 64, x+w-64, y+h-64, "Guardar", Control.stage==stg.NEW_MOVIE ? but_type.SAVE_ADD : but_type.SAVE);
		button_delete = new Boton(-100, y+h-64, 64, 64, stx+16, y+h-64, "Eliminar", but_type.DEL_MOVIE);
		
		Control.editing = this;
	}
	
	
	private void setDefaults(int x_, int y_)
	{
		x = x_;
		y = y_;
		
		stx = x + 192;
		
		w = (int)(Main.rw/1.5);
		h = Main.len;
		
		hover = false;
		edit = true;
		
		button_edit = null;
		button_save = null;
		button_delete = null;
		
		inputs = new ArrayList<InputBox>();
	}
	
	
	public void setData()
	{
		int i = 0;
		
		titulo = 		inputs.get(i++).getItem();
		año =			inputs.get(i++).getItem();
		genero =		inputs.get(i++).getItem();
		duracion =		inputs.get(i++).getItem();
		descripcion =	inputs.get(i++).getItem();
		url =			inputs.get(i++).getItem();
	}
	
	
	public void removeFields()
	{
		for(int i=0; i<inputs.size(); i++)
			inputs.get(i).remove();
	}
}
