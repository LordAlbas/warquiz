import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;


public class SQL_Requete_Quiz {
	private Fenetre fenetre;
	public String query_quiz; //variable dans lesquelles sont plac�es les requetes
	public String query_del1;
	public String query_del2;
	public String query_del3;
	public static String query_heure;
	public static String query_minute;
	public static String query_seconde;
	public String query_nbQuiz;
	private Quiz[] mesQuiz;
	public String query_idquiz;
	
	public SQL_Requete_Quiz(Fenetre fen) {
		fenetre = fen;
	}
	
	/**
	 * Renvoi le nombre de quiz total dans la BDD.
	 * Used from recup_QuizXXX() pour initialiser la taille du tableau de quiz "mesQuiz[]" (attribut de classe).
	 * @return
	 */
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
	
	/**
	 * Ecrit dans l'attribut "mesQuiz[]" un tableau de Quiz contenant uniquement quiz.NOM et quiz.ID
	 */
	public void recup_Quiz(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, login_admin, nom_quiz, minute_quiz, seconde_quiz, heure_quiz, nb_question FROM QUIZ";
			stmt_quiz.executeQuery(query_quiz);
	        
	        ResultSet rs_quiz = stmt_quiz.getResultSet();
	        mesQuiz = new Quiz[recup_nbQuiz()];
	        short i = 0;
	            while(rs_quiz.next()){
	            	String Nom_quiz = rs_quiz.getString("nom_quiz");
	            	int Id_quiz = rs_quiz.getInt("id_quiz");
	            	String Login_admin = rs_quiz.getString("login_admin");
	            	int Nb_quest = rs_quiz.getInt("nb_question");
	            	String Heure_quiz = rs_quiz.getString("heure_quiz");
	            	String Minute_quiz = rs_quiz.getString("minute_quiz");
	            	String Seconde_quiz = rs_quiz.getString("seconde_quiz");
	            	mesQuiz[i] = new Quiz(Nom_quiz, fenetre);
	            	mesQuiz[i].setId(Id_quiz);
	            	mesQuiz[i].setLoginAdmin(Login_admin);
	            	mesQuiz[i].setNb_questions(Nb_quest);
	            	mesQuiz[i].setHeureQuiz(Heure_quiz);
	            	mesQuiz[i].setMinuteQuiz(Minute_quiz);
	            	mesQuiz[i].setSecondeQuiz(Seconde_quiz);
	            	i++;
	            }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}		
	}
	
	/**
	 * Ecrit dans l'attribut "mesQuiz[]" un tableau de Quiz contenant uniquement quiz.NOM et quiz.ID
	 * Recupere uniquement les quiz de difficulte _FACILE_ (diff=1).
	 */
	public void recup_Quiz_facile(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz, nb_question, minute_quiz, seconde_quiz, heure_quiz FROM QUIZ WHERE DIFFICULTE_QUIZ = 1";
			stmt_quiz.executeQuery(query_quiz);
	        
	        ResultSet rs_quiz = stmt_quiz.getResultSet();
	        mesQuiz = new Quiz[recup_nbQuiz()];
	        short i = 0;
	            while(rs_quiz.next()){
	            	String Nom_quiz = rs_quiz.getString("nom_quiz");
	            	int Id_quiz = rs_quiz.getInt("id_quiz");
	            	int Nb_rep = rs_quiz.getInt("nb_question");
	            	String Minute_quiz = rs_quiz.getString("minute_quiz");
	            	String Seconde_quiz = rs_quiz.getString("seconde_quiz");
	            	String Heure_quiz = rs_quiz.getString("heure_quiz");
	            	mesQuiz[i] = new Quiz(Nom_quiz, fenetre);
	            	mesQuiz[i].setId(Id_quiz);
	            	mesQuiz[i].setNb_questions(Nb_rep);
	            	mesQuiz[i].setMinuteQuiz(Minute_quiz);
	            	mesQuiz[i].setSecondeQuiz(Seconde_quiz);
	            	mesQuiz[i].setHeureQuiz(Heure_quiz);
	            	i++;
	            }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}		
	}
	
	/**
	 * Ecrit dans l'attribut "mesQuiz[]" un tableau de Quiz contenant uniquement quiz.NOM et quiz.ID
	 * Recupere uniquement les quiz de difficulte _MOYEN_ (diff=2).
	 */
	public void recup_Quiz_moyen(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz, nb_question, minute_quiz, seconde_quiz, heure_quiz FROM QUIZ WHERE DIFFICULTE_QUIZ = 2";
			stmt_quiz.executeQuery(query_quiz);
	        
	        ResultSet rs_quiz = stmt_quiz.getResultSet();
	        mesQuiz = new Quiz[recup_nbQuiz()];
	        short i = 0;
	            while(rs_quiz.next()){
	            	String Nom_quiz = rs_quiz.getString("nom_quiz");
	            	int Id_quiz = rs_quiz.getInt("id_quiz");
	            	int Nb_rep = rs_quiz.getInt("nb_question");
	            	String Minute_quiz = rs_quiz.getString("minute_quiz");
	            	String Seconde_quiz = rs_quiz.getString("seconde_quiz");
	            	String Heure_quiz = rs_quiz.getString("heure_quiz");
	            	mesQuiz[i] = new Quiz(Nom_quiz, fenetre);
	            	mesQuiz[i].setId(Id_quiz);
	            	mesQuiz[i].setNb_questions(Nb_rep);
	            	mesQuiz[i].setMinuteQuiz(Minute_quiz);
	            	mesQuiz[i].setSecondeQuiz(Seconde_quiz);
	            	mesQuiz[i].setHeureQuiz(Heure_quiz);
	            	i++;
	            }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}		
	}
	
	/**
	 * Ecrit dans l'attribut "mesQuiz[]" un tableau de Quiz contenant uniquement quiz.NOM et quiz.ID
	 * Recupere uniquement les quiz de difficulte _DIFFICILE_ (diff=3).
	 */
	public void recup_Quiz_difficile(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz, nb_question, minute_quiz, seconde_quiz, heure_quiz FROM QUIZ WHERE DIFFICULTE_QUIZ = 3";
			stmt_quiz.executeQuery(query_quiz);
	        
	        ResultSet rs_quiz = stmt_quiz.getResultSet();
	        mesQuiz = new Quiz[recup_nbQuiz()];
	        short i = 0;
	            while(rs_quiz.next()){
	            	String Nom_quiz = rs_quiz.getString("nom_quiz");
	            	int Id_quiz = rs_quiz.getInt("id_quiz");
	            	int Nb_rep = rs_quiz.getInt("nb_question");
	            	String Minute_quiz = rs_quiz.getString("minute_quiz");
	            	String Seconde_quiz = rs_quiz.getString("seconde_quiz");
	            	String Heure_quiz = rs_quiz.getString("heure_quiz");
	            	mesQuiz[i] = new Quiz(Nom_quiz, fenetre);
	            	mesQuiz[i].setId(Id_quiz);
	            	mesQuiz[i].setNb_questions(Nb_rep);
	            	mesQuiz[i].setMinuteQuiz(Minute_quiz);
	            	mesQuiz[i].setSecondeQuiz(Seconde_quiz);
	            	mesQuiz[i].setHeureQuiz(Heure_quiz);
	            	i++;
	            }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
	}
	
	public void recup_QuizStats(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz FROM QUIZ WHERE LOGIN_ADMIN =" + Connexion.dbUsername_admin;;
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
	
	
	public void recup_QuizStats_facile(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz FROM QUIZ WHERE DIFFICULTE_QUIZ = 1 AND LOGIN_ADMIN =" + Connexion.dbUsername_admin;;
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
	
	
	public void recup_QuizStats_moyen(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz FROM QUIZ WHERE DIFFICULTE_QUIZ = 2 AND LOGIN_ADMIN =" + Connexion.dbUsername_admin;;
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
	
	public void recup_QuizStats_difficile(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz FROM QUIZ WHERE DIFFICULTE_QUIZ = 3 AND LOGIN_ADMIN =" + Connexion.dbUsername_admin;
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
	
	/**
	 * Retourne un objet quiz COMPLET a partir de l'ID_QUIZ.
	 * L'objet retournee comporte deja les questions et reponses liees.
	 * @param id_quiz
	 * @return
	 */
	public Quiz getMyQuiz(int id_quiz) {
		Connection conn = null;
		
		//"SELECT id_question, id_quiz, txt_quest FROM QUESTION, QUIZ WHERE QUIZ.id_quiz = QUESTION.id_quiz"
		
		Quiz quiz = null;
		// champs Quiz
		String nomQuiz = "";
		int diffQuiz = 0;
		String catQuiz = "";
		String logAdminQuiz = "";
		int idQuiz = id_quiz;
		int nbQuest = 0;
		String heureQuiz = "";
		String minuteQuiz = "";
		String secondeQuiz = "";
		
		// Requete
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			Statement stmt_quest = (Statement) conn.createStatement();
			Statement stmt_rep = (Statement) conn.createStatement();
			
			String query_quiz = "SELECT * FROM QUIZ WHERE ID_QUIZ = "+id_quiz;
			String query_quest = "SELECT * FROM QUESTION WHERE ID_QUIZ = "+id_quiz;
			String query_rep = "SELECT * FROM REPONSE WHERE ID_QUIZ = "+id_quiz;
			
			stmt_quiz.executeQuery(query_quiz);
	        ResultSet rs_quiz = stmt_quiz.getResultSet();
	        stmt_quest.executeQuery(query_quest);
	        ResultSet rs_quest = stmt_quest.getResultSet();
	        stmt_rep.executeQuery(query_rep);
	        ResultSet rs_rep = stmt_rep.getResultSet();
	        
	        if (rs_quiz.next()) {
	            nomQuiz = rs_quiz.getString("nom_quiz");
	            diffQuiz = rs_quiz.getInt("difficulte_quiz");
	            catQuiz = rs_quiz.getString("categorie_quiz");
	            logAdminQuiz = rs_quiz.getString("login_admin");
	            // nbQuest doit rester a zero ici car il est incremente quand on ajoute des question juste en dessous.
	            nbQuest = rs_quiz.getInt("nb_question"); // mais je l'utilise pour autre chose
	            heureQuiz = rs_quiz.getString("heure_quiz");
	            minuteQuiz = rs_quiz.getString("minute_quiz");
	            secondeQuiz = rs_quiz.getString("seconde_quiz");
	            
	            String diff;
	    		switch (diffQuiz) {
	    		case 1:
	    			diff="facile";
	    			break;
	    		case 2:
	    			diff="moyen";
	    			break;
	    		case 3:
	    			diff="difficile";
	    			break;
	    		default:
	    			diff="empty";
	    		}
	            
	            // Quiz
	    		quiz = new Quiz(nomQuiz, fenetre);
	    		quiz.setId(idQuiz);
	    		quiz.setDifficulteQuiz(diff);
	    		quiz.setCategorieQuiz(catQuiz);
	    		quiz.setLoginAdmin(logAdminQuiz);
	    		quiz.setHeureQuiz(heureQuiz);
	    		quiz.setMinuteQuiz(minuteQuiz);
	    		quiz.setSecondeQuiz(secondeQuiz);
	    		
	            // dans le constructeur quiz, nbQuest automatiquement a zero et s'incremente tout seul, pas de modif du champ ici.
	    		//quiz.setNb_questions(nbQuest);
	    		
	    		// me sert pour boucler les reponses apres avoir boucler toutes les questions
	    		int[] idQuest = new int[nbQuest];
	    		short x = 0;
	    		
	            while(rs_quest.next()) {
	            	if (x>19) {
	            		System.out.println("Trop de questions ...!!");
	            	} else {
	            		if (x>=idQuest.length) {
	            			System.out.println("Erreur dans la recuperation des questions du quiz dans SQL_Requete_Quiz.getMyQuiz(int)");
	            		} else {
	            			// champs Quest
	            			String nomQuest = rs_quest.getString("text_quest");
	            			// pareil que pour les nbQuest, pas besoin de modifier le champs de la classe Question ici.
	            			//int nbRep = rs_quest.getInt("nb_rep_total");
	            			int nbRepJuste = rs_quest.getInt("nb_rep_juste");
	            			
	            			// me sert pour boucler les reponses apres avoir boucler toutes les questions
	            			idQuest[x] = rs_quest.getInt("id_question");
	            			x++;
	            			
	            			// Question
	            			Question quest = quiz.ajoutQuestion(nomQuest);
	            			//quest.setNb_reponses(nbRep);
	            			quest.setNbr_reponses_juste(nbRepJuste);
	            		}
	            	}
	            }
	            
	            while (rs_rep.next()) {
	            	for (short z=0; z<idQuest.length;z++) {
	            		if (idQuest[z] == rs_rep.getInt("id_question")) {
	            			// champs Rep
            				String nomRep = rs_rep.getString("text_rep");
            				boolean statutRep = rs_rep.getBoolean("statut_rep");
            				
            				// Reponse
            				Reponse rep = quiz.getQuest(z).addReponse(nomRep);
            				rep.setStatutRep(statutRep);
	            		}
	            	}
	            }
	            
	        } else {
	        	System.out.println("Erreur dans le chargement d'un quiz (num."+id_quiz+")");
	        }
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		
		return quiz;
	}
	
	/**
	 * Supprime de la BDD le quiz correspondant a ID_QUIZ.
	 * @param id_quiz
	 */
	public void deleteQuiz(int id_quiz) {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_del1 = (Statement) conn.createStatement();
			Statement stmt_del2 = (Statement) conn.createStatement();
			Statement stmt_del3 = (Statement) conn.createStatement();
			Statement stmt_del4 = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_del1 = "DELETE FROM QUIZ WHERE QUIZ.ID_QUIZ = " + id_quiz + ";";
			query_del2 = "DELETE FROM REPONSE WHERE REPONSE.ID_QUIZ = " + id_quiz + ";";
			query_del3 = "DELETE FROM QUESTION WHERE QUESTION.ID_QUIZ = " + id_quiz + ";";
			String query_del4 = "DELETE FROM JOUER WHERE JOUER.ID_QUIZ ="+id_quiz+";";
			
			//  /!\ executeUpdate != executeQuery /!\
			// car Update quand aucun retour (modif la bdd)
			// et Query quand un retour (aucune modif de la bdd)
			stmt_del4.executeUpdate(query_del4);
			stmt_del3.executeUpdate(query_del3);
			stmt_del2.executeUpdate(query_del2);
			stmt_del1.executeUpdate(query_del1);
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
	}
	
	/**
	 * Getter de l'attribut tableau de quiz "Quiz[] mesQuiz".
	 * @return
	 */
	public Quiz[] getMesQuiz(){
		return mesQuiz;	
	}
	
	public static long getHeures(int id_quiz){
		Connection conn = null;
		int heure_quiz = 1;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_heure = (Statement) conn.createStatement();
			query_heure = "SELECT HEURE_QUIZ FROM QUIZ WHERE ID_QUIZ="+id_quiz;
			stmt_heure.executeQuery(query_heure);
			ResultSet rs_heure = stmt_heure.getResultSet();
			while(rs_heure.next()){
				heure_quiz = rs_heure.getInt("HEURE_QUIZ");
			}
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		return heure_quiz;	
	}

	public static long getMin(int id_quiz) {
		Connection conn = null;
		int minute_quiz = 1;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_minute = (Statement) conn.createStatement();
			query_minute = "SELECT MINUTE_QUIZ FROM QUIZ WHERE ID_QUIZ="+id_quiz;
			stmt_minute.executeQuery(query_minute);
			ResultSet rs_minute = stmt_minute.getResultSet();
			while(rs_minute.next()){
				minute_quiz = rs_minute.getInt("MINUTE_QUIZ");
			}
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		return minute_quiz;
	}

	public static long getSec(int id_quiz) {
		Connection conn = null;
		int seconde_quiz = 1;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_seconde = (Statement) conn.createStatement();
			query_seconde = "SELECT SECONDE_QUIZ FROM QUIZ WHERE ID_QUIZ="+id_quiz;
			stmt_seconde.executeQuery(query_seconde);
			ResultSet rs_seconde = stmt_seconde.getResultSet();
			while(rs_seconde.next()){
				seconde_quiz = rs_seconde.getInt("SECONDE_QUIZ");
			}
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		return seconde_quiz;
	}
	
	/**
	 * 
	 * @param currentQuiz
	 */
	public boolean setModifQuiz(Quiz currentQuiz) {
		boolean querySuccess = true;
		int id = 0;
		boolean exist = false;
		Connection conn = null;
		
		/*
		 * BLOC de SELECT ID_QUIZ
		 * pour voir si c'est une MODIF ou une CREATION de quiz
		 */
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_recupIdQuiz = (Statement) conn.createStatement();
			String query_recupIdQuiz = "SELECT id_quiz FROM QUIZ";
			stmt_recupIdQuiz.executeQuery(query_recupIdQuiz);
			ResultSet rs_idQuiz = stmt_recupIdQuiz.getResultSet();
			while (rs_idQuiz.next()) {
				exist = false;
				if (rs_idQuiz.getInt("id_quiz") == currentQuiz.getId()) {
					id = rs_idQuiz.getInt("id_quiz");
					exist = true;
				}
			}
			//  /!\ executeUpdate != executeQuery /!\
			// car Update quand aucun retour (modif la bdd)
			// et Query quand un retour (aucune modif de la bdd)
	    } catch (SQLException eeee) {
	    	querySuccess = false;
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
	    	querySuccess = false;
			eeee.printStackTrace();
		}
		
		if (exist) {
			/*
			 * Code pour MODIFIER un quiz
			 */
			System.out.println("Quiz deja existant (=modif) --> "+id);
		} else {
			/*
			 * Code pour AJOUTER un quiz
			 */
			System.out.println("Quiz tout nouveau ! (=creation) --> "+currentQuiz.getId());
			int newIdQuiz = 99;
			Connection connAdd = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connAdd = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
				
				/*
				 * PREPARED_STATEMENT FOR THE WIN!!
				 */
				
				// Insert QUIZ
				String query_insertQuiz = "INSERT INTO QUIZ (LOGIN_ADMIN, DIFFICULTE_QUIZ, NOM_QUIZ, MINUTE_QUIZ, SECONDE_QUIZ, HEURE_QUIZ, NB_QUESTION) VALUES(?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement prep_stmt_addQuiz = connAdd.prepareStatement(query_insertQuiz);
				prep_stmt_addQuiz.setString(1, Connexion.login_general);
				prep_stmt_addQuiz.setInt(2, currentQuiz.getDifficulteQuizInt());
				prep_stmt_addQuiz.setString(3, currentQuiz.getNom());
				prep_stmt_addQuiz.setString(4, currentQuiz.getMinuteQuiz());		// minutes
				prep_stmt_addQuiz.setString(5, currentQuiz.getSecondeQuiz());	// secondes
				prep_stmt_addQuiz.setString(6, currentQuiz.getHeureQuiz());		// heures
				prep_stmt_addQuiz.setInt(7, currentQuiz.getNb_questions());
				prep_stmt_addQuiz.executeUpdate();
				
				// Recuperation de l'ID_QUIZ qui vient d'etre inserer (id auto incremente dans SQL_Server).
				String query_getIdQuiz = "SELECT ID_QUIZ FROM QUIZ WHERE NOM_QUIZ=?;";
				PreparedStatement prep_stmt_getIdQuiz = connAdd.prepareStatement(query_getIdQuiz);
				prep_stmt_getIdQuiz.setString(1, currentQuiz.getNom());
				prep_stmt_getIdQuiz.executeQuery();
				ResultSet rs_getIdQuiz = prep_stmt_getIdQuiz.getResultSet();
				if (rs_getIdQuiz.next()) {
					newIdQuiz = rs_getIdQuiz.getInt("id_quiz");
				} else {
					querySuccess = false;
				}
				
				for (byte i=0; i<currentQuiz.getNb_questions(); i++) {
					int newIdQuest = 999;
					
					// Insert QUESTION
					String query_insertQuest = "INSERT INTO QUESTION (ID_QUIZ, NB_REP_TOTAL, NB_REP_JUSTE, TEXT_QUEST) VALUES(?, ?, ?, ?);";
					PreparedStatement prep_stmt_addQuest = connAdd.prepareStatement(query_insertQuest);
					prep_stmt_addQuest.setInt(1, newIdQuiz);
					prep_stmt_addQuest.setInt(2, currentQuiz.getQuest(i).getNb_reponses());
					prep_stmt_addQuest.setInt(3, currentQuiz.getQuest(i).getNbr_reponses_juste());
					prep_stmt_addQuest.setString(4, currentQuiz.getQuest(i).getQuestTxt());
					prep_stmt_addQuest.executeUpdate();
					
					// Recuperation de l'ID_REPONSE qui vient d'etre inserer (id auto incremente dans SQL_Server).
					String query_getIdQuest = "SELECT ID_QUESTION FROM QUESTION WHERE ID_QUIZ=? AND TEXT_QUEST=?;";
					PreparedStatement prep_stmt_getIdQuest = connAdd.prepareStatement(query_getIdQuest);
					prep_stmt_getIdQuest.setInt(1, newIdQuiz);
					prep_stmt_getIdQuest.setString(2, currentQuiz.getQuest(i).getQuestTxt());
					prep_stmt_getIdQuest.executeQuery();
					ResultSet rs_getIdQuest = prep_stmt_getIdQuest.getResultSet();
					if (rs_getIdQuest.next()) {
						newIdQuest = rs_getIdQuest.getInt("ID_QUESTION");
					} else {
						querySuccess = false;
					}
					
					for (byte j=0; j<currentQuiz.getQuest(i).getNb_reponses(); j++) {
						// Insert REPONSE
						String query_insertRep = "INSERT INTO REPONSE (ID_QUESTION, TEXT_REP, STATUT_REP, ID_QUIZ) VALUES(?, ?, ?, ?);";
						PreparedStatement prep_stmt_addRep = connAdd.prepareStatement(query_insertRep);
						prep_stmt_addRep.setInt(1, newIdQuest);
						prep_stmt_addRep.setString(2, currentQuiz.getQuest(i).getReponse(j).getTxtReponse());
						prep_stmt_addRep.setInt(3, currentQuiz.getQuest(i).getReponse(j).getStatutRepInt());
						prep_stmt_addRep.setInt(4, newIdQuiz);
						prep_stmt_addRep.executeUpdate();
					}
				}
		    } catch (SQLException eeee) {
		    	eeee.printStackTrace();
		    	querySuccess = false;
		    } catch (ClassNotFoundException eeee) {
				eeee.printStackTrace();
				querySuccess = false;
			}
		}
		return querySuccess;
	}
	
	public void comptabiliserPartie(Quiz monQuiz, int monScore, long monTemps) {
		int sec = (int)monTemps%60;
    	int min = (int)(monTemps/60)%60;
    	int hrs = (int)(monTemps/3600)%60;
    	
		Connection conn = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			
			String query_jouer = "SELECT * FROM JOUER WHERE LOGIN_USR = ? AND ID_QUIZ = ?;";
			PreparedStatement prep_stmt_jouer = conn.prepareStatement(query_jouer);
			prep_stmt_jouer.setString(1, Connexion.login_general);
			prep_stmt_jouer.setInt(2, monQuiz.getId());
			prep_stmt_jouer.executeQuery();							// execute la query
			ResultSet rs_jouer = prep_stmt_jouer.getResultSet();
			if (!rs_jouer.next()) {
				String query_insertUser = "UPDATE UTILISATEUR SET NB_QUIZ_JOUE = NB_QUIZ_JOUE+1 WHERE LOGIN_USR = ?";
				PreparedStatement prep_stmt_insertUser = conn.prepareStatement(query_insertUser);
				prep_stmt_insertUser.setString(1, Connexion.login_general);
				prep_stmt_insertUser.executeUpdate();
			}
			String query_updateUser = "UPDATE UTILISATEUR SET NB_PARTIE_JOUE = NB_PARTIE_JOUE+1 WHERE LOGIN_USR = ?";
			PreparedStatement prep_stmt_updateUser = conn.prepareStatement(query_updateUser);
			prep_stmt_updateUser.setString(1, Connexion.login_general);
			prep_stmt_updateUser.executeUpdate();
		} catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			
			// INSERT INTO JOUER => nouvelle ligne avec le score et le temps etc de la game
			String query_insertPartie = "INSERT INTO JOUER "
					+ "(LOGIN_USR, ID_QUIZ, SCORE_USR_QUIZ, HEURE_USR_QUIZ, MINUTE_USR_QUIZ, SECONDE_USR_QUIZ) "
					+ "VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement prep_stmt_insertPartie = conn.prepareStatement(query_insertPartie);
			prep_stmt_insertPartie.setString(1, Connexion.login_general);
			prep_stmt_insertPartie.setInt(2, monQuiz.getId());
			prep_stmt_insertPartie.setInt(3, monScore);
			prep_stmt_insertPartie.setInt(4, hrs);
			prep_stmt_insertPartie.setInt(5, min);
			prep_stmt_insertPartie.setInt(6, sec);
			prep_stmt_insertPartie.executeUpdate();					// execute la commande
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
				
				/*
				 * TABLE JOUER
				 * 		login_usr			(varchar)
				 * 		id_quiz				(int)
				 * 		score_usr_quiz		(int)
				 * 		heure_usr_quiz		(int)
				 * 		minute_usr_quiz		(int)
				 * 		seconde_usr_quiz	(int)
				 */
				
				/*
				 * TABLE UTILISATEUR
				 * 		nb_partie_joue	(int)
				 * 		nb_quiz_joue	(int)
				 * 		(login_usr)		(varchar)
				 * 		(mdp_usr)		(varchar)
				 * 		(adr_mail_usr)	(varchar)
				 */
	}

}

/*
 *				   Mise a jour du nom du quiz :	"UPDATE QUIZ SET NOM_QUIZ =" +nouveau_nom;
 *		 Mise a jour de la difficulte du quiz :	"UPDATE QUIZ SET DIFFICULTE_QUIZ =" +nouvelle_diff;
 *			   Mise a jour des heures du quiz :	"UPDATE QUIZ SET HEURE_QUIZ =" +nouvelle_heure;
 *			  Mise a jour des minutes du quiz :	"UPDATE QUIZ SET MINUTE_QUIZ =" +nouvelle_minute;
 *			 Mise a jour des secondes du quiz :	"UPDATE QUIZ SET SECONDE_QUIZ =" +nouvelle_seconde;
 *					  Ajout d'un nouveau quiz :	"INSERT INTO QUIZ VALUES("+login_admin+", "+nom_quiz+", "+minute_quiz+", "+seconde_quiz+", "+heure_quiz+", "+nb_questions+");";
 *						 Ajout d'une question :	"INSERT INTO QUESTION VALUES("+id_quiz+", "+nb_rep_total+", "+nb_rep_juste+", "+text_quest+");";
 *		  Mise a jour du texte de la question :	"UPDATE QUESTION SET TEXT_QUEST =" +nouveau_texte;
 *	  Mise a jour du nombre de reponses total :	"UPDATE QUESTION SET NB_REP_TOTAL =" + nouveau_nombre;
 *	  Mise a jour du nombre de reponses juste :	"UPDATE QUESTION SET NB_REP_JUSTE =" + nouveau_nombre;
 *				   Suppression d'une question :	"DELETE FROM QUESTION WHERE TEXT_QUEST ="+text_quest+" AND ID_QUIZ="+id_quiz;
 *						  Ajout d'une reponse :	"INSERT INTO REPONSE VALUES("+id_question+", "+text_rep+", "+statut_rep+", "+id_quiz+");";
 *		   Mise a jour du texte de la reponse :	"UPDATE REPONSE SET TEXT_REP =" +nouveau_texte;
 *		  Mise a jour du statut de la reponse :	"UPDATE REPONSE SET STATUT_REP ="+nouveau_statut;
 *					Suppression d'une reponse :	"DELETE FROM REPONSE WHERE ID_QUESTION ="+id_question;
 *		Ajout d'un quiz lors de la validation :	"INSERT INTO JOUER VALUES("+login_usr+", "+id_quiz+", "+score_usr_quiz+", "+heure_quiz+", "+minute_quiz+", "+seconde_quiz+");";
 *			  Recuperation des heures du quiz :	"SELECT HEURE_QUIZ FROM QUIZ WHERE ID_QUIZ="+id_quiz;
 *			 Recuperation des minutes du quiz :	"SELECT MINUTE_QUIZ FROM QUIZ WHERE ID_QUIZ="+id_quiz;
 *			Recuperation des secondes du quiz :	"SELECT SECONDE_QUIZ FROM QUIZ WHERE ID_QUIZ="+id_quiz;
 */
