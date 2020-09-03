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
	
	
	public void getData()
	{
		try
		{
			String query = "select * from pelicula";
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
