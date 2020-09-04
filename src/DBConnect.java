import java.sql.*;

public class DBConnect
{
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pelicool", "root", "");
			st = con.createStatement();
		}
		catch (Exception ex)
		{
			System.out.println("DBConnect Error: "+ex);
		}
	}
	
	
	public void Buscar(String text)
	{
		try
		{
			String query = "SELECT * FROM `pelicula` WHERE `Titulo` LIKE '%"+text+"%' LIMIT 5";
			rs = st.executeQuery(query);
			
			Control.peliculas.clear();
			
			int x = (int)(Main.rw/3/2);
			int y = 164;
			
			System.out.println("\nQuery:");
			while(rs.next())
			{
				String t =   rs.getString("Titulo");
				String a =   rs.getString("Año");
				String g =   rs.getString("Genero");
				String de =  rs.getString("Descripcion");
				String du =  rs.getString("Duracion");
				String url = rs.getString("Imagen");
				
				Control.peliculas.add(new Pelicula(x, y, t, a, g, du, de, url));
				
				System.out.println(t+" ("+a+")");
				
				y += Main.len + 32;
			}
		}
		catch(Exception ex)
		{
			System.out.println("DBConnect Error: "+ex);
		}
	}
	
	
	
	public void getData()
	{
		try
		{
			String query = "SELECT * FROM `pelicula` WHERE `Titulo` LIKE '%lig%'";
			rs = st.executeQuery(query);
			
			System.out.println("DB");
			while(rs.next())
			{
				String titulo = rs.getString("Titulo");
				String año = rs.getString("Año");
				
				System.out.println(titulo+" ("+año+")");
			}
		}
		catch(Exception ex)
		{
			System.out.println("DBConnect Error: "+ex);
		}
	}
	
	
	public void sendNewMovie(Pelicula mov)
	{
		try
		{
			String query = 	"INSERT INTO pelicula (Titulo, Año, Genero, Descripcion, Duracion, Imagen) VALUES ('"
							+mov.titulo+"', '"+mov.año+"', '"+mov.genero+"', '"+mov.descripcion+"', '"+mov.duracion+"', '"+mov.url+"');";
			st.executeUpdate(query);
			System.out.println("SENT QUERY: "+query);
		}
		catch(Exception ex)
		{
			System.out.println("DBConnect Error: "+ex);
		}
	}
	
	
	public void delMovie(Pelicula mov)
	{
		try
		{
			String query = 	"DELETE FROM pelicula WHERE titulo = '"+mov.titulo+"' AND año = '"+mov.año+"';";
			st.executeUpdate(query);
			System.out.println("SENT QUERY: "+query);
		}
		catch(Exception ex)
		{
			System.out.println("DBConnect Error: "+ex);
		}
	}
}
