import java.sql.*;


public class SQL_Connect {

	public SQL_Connect() {
		// TODO Auto-generated constructor stub
		
	}
	public static void tryConnect() {
		Connection conn = null;
		try {
            // Load the SQLServerDriver class, build the
            // connection string, and get a connection	
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
            System.out.println("Connecté");
		} 
		catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("erreur connection");
            System.exit(0);
		}
	}
}
