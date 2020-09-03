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
			String query = "SELECT * FROM `pelicula` WHERE `Titulo` LIKE '%"+text+"%'";
			rs = st.executeQuery(query);
			
			Control.peliculas.clear();
			
			int x = 128;
			int y = 300;
			
			System.out.println("Query:");
			while(rs.next())
			{
				String t =   rs.getString("Titulo");
				String a =   rs.getString("A�o");
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
				String a�o = rs.getString("A�o");
				/*String titulo = rs.getString("Titulo");
				String titulo = rs.getString("Titulo");
				String titulo = rs.getString("Titulo");*/
				
				System.out.println(titulo+" ("+a�o+")");
			}
		}
		catch(Exception ex)
		{
			System.out.println("DBConnect Error: "+ex);
		}
	}
}
