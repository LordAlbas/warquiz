import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQL_Requete_Quiz {
	private Fenetre fenetre;
	private String selection;
	public String query_quiz; //variable dans lesquelles sont placées les requetes
	private Quiz[] mesQuiz;
	
	public SQL_Requete_Quiz(Fenetre fen) {
		fenetre = fen;
		fenetre.goToAccueil(selection);
	}
	
	
	
	
	public void recup_Quiz(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			query_quiz = "SELECT id_quiz, nom_quiz FROM QUIZ";
			stmt_quiz.executeQuery(query_quiz);
	        
	        ResultSet rs_quiz = stmt_quiz.getResultSet();
	        
	            while(rs_quiz.next()){
	            	String Nom_quiz = rs_quiz.getString("nom_quiz");
	            	int Id_quiz = rs_quiz.getInt("id_quiz");
	            	Quiz monQuiz = new Quiz(Nom_quiz, fenetre);
	            	monQuiz.setId(Id_quiz);
	            }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}		
	}

}
