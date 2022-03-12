package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionProvider 
{
private ConnectionProvider ()
{
	
}

   public static Connection con=null;
public static Connection getconnection()
{
	
	try
	{
		Class.forName(DataBaseDetails.Driver);
		con= DriverManager.getConnection(DataBaseDetails.Url, DataBaseDetails.user, DataBaseDetails.pass);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return con;
	
}
	

}
