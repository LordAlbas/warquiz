import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;


public class SQL_Requete_Quiz {
	private Fenetre fenetre;
	public String query_quiz; //variable dans lesquelles sont placï¿½es les requetes
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
			query_quiz = "SELECT id_quiz, nom_quiz, nb_question FROM QUIZ";
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
	
	public void recup_Quiz_facile(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz, nb_question FROM QUIZ WHERE DIFFICULTE_QUIZ = 1";
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
	public void recup_Quiz_moyen(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz, nb_question FROM QUIZ WHERE DIFFICULTE_QUIZ = 2";
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
	
	public void recup_Quiz_difficile(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz = (Statement) conn.createStatement();
			// la requete doit retourner UNIQUEMENT les quiz de l'admin en cours
			// l'id_admin doit etre disponible quelque part
			query_quiz = "SELECT id_quiz, nom_quiz, nb_question FROM QUIZ WHERE DIFFICULTE_QUIZ = 3";
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
		Time tmpsQuiz = null;
		
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
	            
	            // bug pour le temps_quiz "The column name temps_quiz is not valid."
	            //tmpsQuiz = rs_quiz.getTime("temps_quiz");
	            // normal la colonne est divisï¿½e en 3 (heure_quiz, minute_quiz et seconde_quiz)
	            
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
	    		//quiz.setTempsQuiz(tmpsQuiz);
	    		
	            // dans le constructeur quiz, nbQuest automatiquement a zero et s'incremente tout seul, pas de modif du champ ici.
	    		//quiz.setNb_questions(nbQuest);
	    		
	    		// me sert pour boucler les reponses apres avoir boucler toutes les questions
	    		int[] idQuest = new int[nbQuest];
	    		short x = 0;
	    		
	            while(rs_quest.next()) {
	            	if (x>9) {
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

/*Mise a jour du nom du quiz : "UPDATE QUIZ SET NOM_QUIZ =" +nouveau_nom;
 *Mise a jour de la difficulté du quiz: "UPDATE QUIZ SET DIFFICULTE_QUIZ =" +nouvelle_diff;
 *Mise a jour des heures du quiz : "UPDATE QUIZ SET HEURE_QUIZ =" +nouvelle_heure;
 *Mise a jour des minutes du quiz : "UPDATE QUIZ SET MINUTE_QUIZ =" +nouvelle_minute;
 *Mise a jour des secondes du quiz : "UPDATE QUIZ SET SECONDE_QUIZ =" +nouvelle_seconde;
 *Ajout d'un nouveau quiz : INSERT INTO QUIZ VALUES("+login_admin+", "+nom_quiz, minute_quiz+", "+seconde_quiz+", "+heure_quiz+", COUNT("+nb_questions+"));
 *Ajout d'une question : INSERT INTO QUESTION VALUES("+id_quiz+", "+nb_rep_total+", "+nb_rep_juste+", "+text_quest+");
 *Mise a jour du texte de la question : UPDATE QUESTION SET TEXT_QUEST =" +nouveau_texte;
 *Mise a jour du nombre de réponses total : UPDATE QUESTION SET NB_REP_TOTAL =" + nouveau_nombre;
 *Mise a jour du nombre de réponses juste :  UPDATE QUESTION SET NB_REP_JUSTE =" + nouveau_nombre;
 *Suppression d'une question : DELETE FROM QUESTION WHERE TEXT_QUEST ="+text_quest+"AND ID_QUIZ="+id_quiz;
 *Ajout d'une réponse : INSERT INTO REPONSE VALUES("+id_question+","+text_rep+","+statut_rep+","+id_quiz+");
 *Mise a jour du texte de la réponse : UPDATE REPONSE SET TEXT_REP =" +nouveau_texte;
 *Mise a jour du statut de la reponse : UPDATE REPONSE SET STATUT_REP ="+nouveau_statut;
 *Suppression d'une réponse : DELETE FROM REPONSE WHERE ID_QUESTION ="+id_question;
 *Ajout d'un quiz lors de la validation : "INSERT INTO JOUER VALUES("+login_usr+","+id_quiz+","+score_usr_quiz+","+heure_quiz+","+minute_quiz+","+seconde_quiz+")";
 **/
