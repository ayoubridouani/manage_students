import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDB {
	private Connection con;
	private Statement stmt;
	
	public Statement connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/etudiant", "etudiant", "etudiant");
			stmt = con.createStatement();
		}catch(Exception error) {
			stmt = null;
			System.out.println("Error: " + error.getMessage());
		}
		return stmt;
	}
}