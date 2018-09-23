package Spring.SpringDemoApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService {
//	static String url="jdbc:mysql://testdb.crp3r8kuzkpb.us-east-2.rds.amazonaws.com:3306/testdb";
//	static String user="udhay";
//	static String password="12345678";
	
	public String TestData() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbName = System.getenv("RDS_DB_NAME");
	    String userName = System.getenv("RDS_USERNAME");
	    String password = System.getenv("RDS_PASSWORD");
	    String hostname = System.getenv("RDS_HOSTNAME");
	    String port = System.getenv("RDS_PORT");
	    
	    String jdbcUrl1 = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
	    
	    String jdbcUrl = "jdbc:mysql://" + "udhay.cogigyqi135e.ap-south-1.rds.amazonaws.com" + ":" + 3306 + "/" + "testdb" 
	    					+ "?user=" + "udhay" + "&password=" + "12345678";
	    
	    System.out.println(">>>>>> Getting remote connection with connection string from environment variables.");
	    System.out.println(jdbcUrl);
	    Connection con = DriverManager.getConnection(jdbcUrl);
	    System.out.println(">>>>>> Remote connection successful.");
		
		String result="";
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection con=DriverManager.getConnection(url, user, password);
		String selectQuery="select * from products";
		PreparedStatement stmt=con.prepareStatement(selectQuery);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) {
			result=result+" ["+rs.getInt(1)+": "+rs.getString(2)+": "+rs.getBigDecimal(3)+"]  ";
		}
		System.out.println("-----------");
		stmt.close();
		con.close();
		return result;
	}
}
