import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQL_Requete_Quiz {
	private Fenetre fenetre;
	public String query_quiz; //variable dans lesquelles sont plac�es les requetes
	public String query_del1;
	public String query_del2;
	public String query_del3;
	public String query_nbQuiz;
	private Quiz[] mesQuiz;
	
	public SQL_Requete_Quiz(Fenetre fen) {
		fenetre = fen;
	}
	
	public int recup_nbQuiz(){
		Connection conn = null;
		int Nb_quiz = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_nbQuiz = (Statement) conn.createStatement();
			query_nbQuiz = "SELECT COUNT(DISTINCT nom_quiz) AS nbQuiz FROM QUIZ";
			stmt_nbQuiz.executeQuery(query_nbQuiz);
	        
	        ResultSet rs_nbQuiz = stmt_nbQuiz.getResultSet();
	        
	            while(rs_nbQuiz.next()){
	            	Nb_quiz = rs_nbQuiz.getInt("nbQuiz");
	            }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		return Nb_quiz;
	}
	
	
	public void recup_Quiz(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz FROM QUIZ";
			stmt_quiz.executeQuery(query_quiz);
	        
	        ResultSet rs_quiz = stmt_quiz.getResultSet();
	        mesQuiz = new Quiz[recup_nbQuiz()];
	        short i = 0;
	            while(rs_quiz.next()){
	            	String Nom_quiz = rs_quiz.getString("nom_quiz");
	            	int Id_quiz = rs_quiz.getInt("id_quiz");
	            	mesQuiz[i] = new Quiz(Nom_quiz, fenetre);
	            	mesQuiz[i].setId(Id_quiz);
	            	i++;
	            }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}		
	}
	
	public Quiz getMyQuiz(int id_quiz) {
		//"SELECT id_question, id_quiz, txt_quest FROM QUESTION, QUIZ WHERE QUIZ.id_quiz = QUESTION.id_quiz"
		
		// champs Quiz
		String nomQuiz = "";
		String diffQuiz = "";
		String catQuiz = "";
		String logAdminQuiz = "";
		int idQuiz = 0;
		int nbQuest = 0;
		int tmpsQuiz = 0;
		
		// champs Quest
		String nomQuest = "";
		int nbRep = 0;
		int nbRepJuste = 0;
		
		// champs Rep
		String nomRep = "";
		int idRep = 0;
		boolean statutRep = false;
		
		// Quiz
		Quiz quiz = new Quiz(nomQuiz, fenetre);
		quiz.setId(idQuiz);
		quiz.setDifficulteQuiz(diffQuiz);
		quiz.setNb_questions(nbQuest);
		quiz.setCategorieQuiz(catQuiz);
		quiz.setLoginAdmin(logAdminQuiz);
		quiz.setTempsQuiz(tmpsQuiz);
		// Question
		Question quest = quiz.ajoutQuestion(nomQuest);
		quest.setNb_reponses(nbRep);
		quest.setNbr_reponses_juste(nbRepJuste);
		// Reponse
		Reponse rep = quest.addReponse(nomRep);
		rep.setIdReponse(idRep);
		rep.setStatutRep(statutRep);
		
		return quiz;
	}
	
	public void deleteQuiz(int id_quiz) {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_del1 = (Statement) conn.createStatement();
			Statement stmt_del2 = (Statement) conn.createStatement();
			Statement stmt_del3 = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_del1 = "DELETE FROM QUIZ WHERE QUIZ.ID_QUIZ = " + id_quiz + ";";
			query_del2 = "DELETE FROM REPONSE WHERE REPONSE.ID_QUIZ = " + id_quiz + ";";
			query_del3 = "DELETE FROM QUESTION WHERE QUESTION.ID_QUIZ = " + id_quiz + ";";
			
			//  /!\ executeUpdate != executeQuery /!\
			// car Update quand aucun retour (modif la bdd)
			// et Query quand un retour (aucune modif de la bdd)
			stmt_del1.executeUpdate(query_del1);
			stmt_del2.executeUpdate(query_del2);
			stmt_del3.executeUpdate(query_del3);
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}	
	}
	
	public Quiz[] getMesQuiz(){
		return mesQuiz;	
	}

}
