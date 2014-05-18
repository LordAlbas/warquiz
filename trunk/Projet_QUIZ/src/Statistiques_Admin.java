import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;


public class Statistiques_Admin extends JPanel implements MouseListener, MouseMotionListener, ItemListener{
	
	private Fenetre fenetre;
	
	public static String selection; 		// defini quel bouton est selectionnï¿½
	private Header header1;
	private Header_menu header2;
	public String query_score,query_moyenne,query_moyenne_total,query_nb_parties,query_nb_quiz,query_quiz_joue,query_nb_participants_quiz,query_score_diff,query_nb_quiz_dispo,query_heure_moyenne,query_minute_moyenne,query_seconde_moyenne;
	private String db_score,db_score_diff,db_num_quiz_diff,db_diff,db_moyenne,db_moyenne_total,db_num_quiz,db_nb_parties,db_nb_quiz,db_quiz_joue,db_nb_participants_quiz,db_nb_quiz_dispo; // le score sorti de la bdd
	public JLabel score,titreA,titreU,score_moyen,score_moyen_total,nb_parties,nb_quiz,quiz_joue,nb_participants_quiz,nb_quiz_dispo; // le score d'un joueur pour un quiz
	private JLabel lb_titreStatistiques,score_diff,lb_nom_quiz,lb_score_quiz,lb_temps_quiz,lb_nb_quest_quiz,lb_nb_part_quiz,lb_temps_moyen_quiz;
	private JList table;
	private Bouton bouton;
	private String texte;
	
	private Bouton_Stats bt_afficherQuizFacile;
	private Bouton_Stats bt_afficherQuizMoyen;
	private Bouton_Stats bt_afficherQuizDifficile;
	private Bouton_Stats bt_afficherAllQuiz;
	
	public static List list_quiz_stats;
	public static Quiz[] ListeQuizStats;
	public static Quiz[] ListeQuizStats_facile;
	public static Quiz[] ListeQuizStats_moyen;
	public static Quiz[] ListeQuizStats_difficile;
	private int diff=0;
	
	/**
	 * Constructeur
	 * @throws SQLException 
	 */
	public Statistiques_Admin(Fenetre fen) throws SQLException {
		fenetre = fen; // on rï¿½cupï¿½re la classe mï¿½re
		setLayout(null);
		repaint();
		//****Inclusion du Header en 2 parties ****
        header1 = new Header(fen);
        header1.setBounds(0, 0, 444, 130);
        header1.addMouseListener(header1);
        header1.addMouseMotionListener(header1);
        this.add(header1); 
     
        SQL_Requete_Quiz maRequete_stats = new SQL_Requete_Quiz(fenetre);
        maRequete_stats.recup_Quiz();
        ListeQuizStats = maRequete_stats.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_stats_facile = new SQL_Requete_Quiz(fenetre);
		maRequete_stats_facile.recup_Quiz_facile();
		ListeQuizStats_facile = maRequete_stats_facile.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_stats_moyen = new SQL_Requete_Quiz(fenetre);
		maRequete_stats_moyen.recup_Quiz_moyen();
		ListeQuizStats_moyen = maRequete_stats_moyen.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_stats_difficile = new SQL_Requete_Quiz(fenetre);
		maRequete_stats_difficile.recup_Quiz_difficile();
		ListeQuizStats_difficile = maRequete_stats_difficile.getMesQuiz();
        

        header2 = new Header_menu(fen, this);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
        
		lb_titreStatistiques = new JLabel("Statistiques Admin");
		lb_titreStatistiques.setForeground(Color.WHITE);
		lb_titreStatistiques.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreStatistiques.setBounds(575, 105, 400, 50);
		lb_titreStatistiques.setOpaque(false);
		add(lb_titreStatistiques);
        
		/*******/
		bt_afficherQuizFacile = new Bouton_Stats("Facile");
		bt_afficherQuizFacile.setLocation(264, 300);
		bt_afficherQuizFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diff = 1;
			}
		});
		add(bt_afficherQuizFacile);
		
		bt_afficherQuizMoyen = new Bouton_Stats("Moyen");
		bt_afficherQuizMoyen.setLocation(380, 300);
		bt_afficherQuizMoyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diff = 2;
			}
		});
		add(bt_afficherQuizMoyen);
		
		bt_afficherQuizDifficile = new Bouton_Stats("Difficile");
		bt_afficherQuizDifficile.setLocation(496, 300);
		bt_afficherQuizDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diff = 3;
			}
		});
		add(bt_afficherQuizDifficile);
		
		bt_afficherAllQuiz = new Bouton_Stats("Tous");
		bt_afficherAllQuiz.setLocation(148, 300);
		bt_afficherAllQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diff = 0;
			}
		});
		add(bt_afficherAllQuiz);

		titreA = new JLabel("Statistiques Administrateur");
		titreA.setForeground(Color.WHITE);
		titreA.setFont(new Font("Arial", Font.PLAIN, 30));
    	titreA.setBounds(172, 120, 450,200);
    	titreA.setOpaque(false);
		add(titreA);	
		nbQuiz(); //nombre de quiz
		scoreMoyenTotal(); //score moyen des quiz
		
		list_quiz_stats = new List();
		list_quiz_stats.setBounds(148, 336, 463, 200);
		list_quiz_stats.addItemListener(this);
		list_quiz_stats.setBackground(new Color(54, 90, 118));
		list_quiz_stats.setForeground(Color.WHITE);
		add(list_quiz_stats);
		
		for (short i=0; i<ListeQuizStats.length; i++) {
			list_quiz_stats.add(ListeQuizStats[i].getNom());
		}
		
		lb_nom_quiz = new JLabel("Nom du quiz :");
		lb_nom_quiz.setForeground(Color.WHITE);
		lb_nom_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_nom_quiz.setBounds(180, 560, 400, 50);
		add(lb_nom_quiz);
		
		lb_score_quiz = new JLabel("Score du quiz :");
		lb_score_quiz.setForeground(Color.ORANGE);
		lb_score_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_score_quiz.setBounds(180, 580, 400, 50);
		add(lb_score_quiz);
		
		lb_temps_quiz = new JLabel("Temps du quiz :");
		lb_temps_quiz.setForeground(Color.GREEN);
		lb_temps_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_temps_quiz.setBounds(180, 600, 400, 50);
		add(lb_temps_quiz);
		
		lb_nb_quest_quiz = new JLabel("Nombre de questions : ");
		lb_nb_quest_quiz.setForeground(Color.WHITE);
		lb_nb_quest_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_nb_quest_quiz.setBounds(180, 620, 400, 50);
		add(lb_nb_quest_quiz);
		
		lb_nb_part_quiz = new JLabel("Nombre de participants : ");
		lb_nb_part_quiz.setForeground(Color.WHITE);
		lb_nb_part_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_nb_part_quiz.setBounds(180, 640, 400, 50);
		add(lb_nb_part_quiz);
		
		lb_temps_moyen_quiz = new JLabel("Temps moyen sur ce quiz : ");
		lb_temps_moyen_quiz.setForeground(Color.WHITE);
		lb_temps_moyen_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_temps_moyen_quiz.setBounds(180, 660, 400, 50);
		add(lb_temps_moyen_quiz);
		
		
	}

	/**
	 * RÃ©cupÃ¨re le numÃ©ro et le score de chaques quiz que le joueur Ã  fait.
	 * @throws SQLException
	 */
	public void Score(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_score = (Statement) conn.createStatement();
	        query_score = "SELECT DISTINCT * FROM JOUER WHERE LOGIN_USR = 'ClaraMorgane'";	       
	        stmt_score.executeQuery(query_score);	
	        ResultSet rs_score = stmt_score.getResultSet();
	        int cpt = 200;
	        while(rs_score.next()){
	        	db_score = rs_score.getString("SCORE_USR_QUIZ"); // on récupère le score
	        	db_num_quiz = rs_score.getString("ID_QUIZ"); // on récupère le num quiz
	        	
	        	score = new JLabel("Votre score pour le quiz n°" + db_num_quiz + " est de : " + db_score);
	    		score.setBounds(48, cpt, 600,600);
	    		score.setForeground(Color.WHITE); 
	    		add(score);
	    		cpt = cpt +20;
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Récupère le numéro et le score des quiz 1:FACILE 2:MOYEN ou 3:DIFFICILE que le joueur à fait.
	 * @throws SQLException
	 */
	public void ScoreDifficulte(int diff){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_score_diff = (Statement) conn.createStatement();
	        query_score_diff = "SELECT DISTINCT * FROM JOUER, QUIZ WHERE LOGIN_USR = 'ClaraMorgane' AND JOUER.ID_QUIZ = QUIZ.ID_QUIZ AND QUIZ.DIFFICULTE_QUIZ ="+diff;	       
	        stmt_score_diff.executeQuery(query_score);	
	        ResultSet rs_score_diff = stmt_score_diff.getResultSet();
	        int cpt_diff = 500;
	        while(rs_score_diff.next()){
	        	db_score_diff = rs_score_diff.getString("SCORE_USR_QUIZ"); // on récupère le score
	        	db_num_quiz_diff = rs_score_diff.getString("ID_QUIZ"); // on récupère le num quiz
	        	//db_diff = rs_score_diff.getString("DIFFICULTE_QUIZ"); // on récupère la difficulte
	        	
	        	score_diff = new JLabel("[SELECT DIFF] Votre score pour le quiz n°" + db_num_quiz_diff + " est de : " + db_score_diff);
	    		score_diff.setBounds(48, cpt_diff, 600,600);
	    		score_diff.setForeground(Color.WHITE); 
	    		add(score_diff);
	    		cpt_diff = cpt_diff +20;
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcule et affiche le score moyen du joueur
	 * @return 
	 * @throws SQLException 
	 */
	public int scoreMoyen(int id_quiz){
		int score_moyen = 0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_moyenne = (Statement) conn.createStatement();
	        query_moyenne = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen FROM JOUER WHERE ID_QUIZ = "+ id_quiz;	       
	        stmt_moyenne.executeQuery(query_moyenne);	
	        ResultSet rs_moyenne = stmt_moyenne.getResultSet();
	        
	        while(rs_moyenne.next()){
	        	score_moyen = rs_moyenne.getInt("score_moyen"); // on récupère la moyenne
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score_moyen;
	}
	
	/**
	 * Calcule et affiche la moyenne des scores des quiz
	 * @throws SQLException 
	 */
	public void scoreMoyenTotal(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_moyenne_total = (Statement) conn.createStatement();
	        query_moyenne_total = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen_total FROM JOUER";	       
	        stmt_moyenne_total.executeQuery(query_moyenne_total);	
	        ResultSet rs_moyenne_total = stmt_moyenne_total.getResultSet();
	        
	        while(rs_moyenne_total.next()){
	        	db_moyenne_total = rs_moyenne_total.getString("score_moyen_total"); // on récupère la moyenne
	        	score_moyen_total = new JLabel("Le score moyen aux quiz est de : " + db_moyenne_total);
	    		score_moyen_total.setBounds(188, 220, 450, 60);
	    		score_moyen_total.setForeground(Color.WHITE); 
	    		score_moyen_total.setFont(new Font("Arial", Font.PLAIN, 17));
	    		add(score_moyen_total);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcule le nombre de parties jouées par l'utilisateur.
	 * @throws SQLException
	 */
	public void nbPartiesJouees(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_nb_parties = (Statement) conn.createStatement();
	        query_nb_parties = "SELECT NB_PARTIE_JOUE AS nb_parties FROM UTILISATEUR WHERE LOGIN_USR = 'ClaraMorgane'";	       
	        stmt_nb_parties.executeQuery(query_nb_parties);	
	        ResultSet rs_nb_parties = stmt_nb_parties.getResultSet();
	        
	        while(rs_nb_parties.next()){
	        	db_nb_parties = rs_nb_parties.getString("nb_parties"); // on récupère la moyenne
	        	nb_parties = new JLabel("Vous avez joué un total de " + db_nb_parties + " parties.");
	    		nb_parties.setBounds(48, 160, 600,600);
	    		nb_parties.setForeground(Color.WHITE); 
	    		add(nb_parties);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Calcule le nombre de quiz joué parmis le nombre max.
	 * @throws SQLException 
	 */
	public void nbQuizJouees(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        
	        Statement stmt_nb_quiz = (Statement) conn.createStatement();
	        query_nb_quiz = "SELECT COUNT (ID_QUIZ) AS nb_quiz FROM QUIZ";	       
	        stmt_nb_quiz.executeQuery(query_nb_quiz);	
	        ResultSet rs_nb_quiz = stmt_nb_quiz.getResultSet();
	        
	        Statement stmt_quiz_joue = (Statement) conn.createStatement();
	        query_quiz_joue = "SELECT NB_QUIZ_JOUE FROM UTILISATEUR WHERE LOGIN_USR = 'ClaraMorgane'";	       
	        stmt_quiz_joue.executeQuery(query_quiz_joue);	
	        ResultSet rs_quiz_joue = stmt_quiz_joue.getResultSet();
	        
	        while(rs_quiz_joue.next()){
	        	db_quiz_joue = rs_quiz_joue.getString("NB_QUIZ_JOUE"); // on récupère la moyenne
	        	
	        }
	        while(rs_nb_quiz.next()){
	        	db_nb_quiz = rs_nb_quiz.getString("nb_quiz"); // on récupère la moyenne
	        	nb_quiz = new JLabel("Nombre de quiz joué(s) : " + db_quiz_joue + " / " + db_nb_quiz);
	        	nb_quiz.setBounds(188, 140, 450,220);
	    		nb_quiz.setForeground(Color.WHITE); 
	    		nb_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
	    		add(nb_quiz);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void meilleurScore(){} 
	
	/**
	 * Le nombre de personnes qui on paticipé à un quiz donné
	 * @param num_quiz
	 * @throws SQLException
	 */
	public int nbParticiants(int id_quiz){
		int nb_participants_quiz = 0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");     
	        Statement stmt_nb_participants_quiz = (Statement) conn.createStatement();
	        query_nb_participants_quiz = "SELECT COUNT (DISTINCT LOGIN_USR) AS nb_participants_quiz FROM JOUER WHERE ID_QUIZ = " + id_quiz;	       
	        stmt_nb_participants_quiz.executeQuery(query_nb_participants_quiz);	
	        ResultSet rs_nb_participants_quiz = stmt_nb_participants_quiz.getResultSet();
	        
	        while(rs_nb_participants_quiz.next()){
	        	nb_participants_quiz = rs_nb_participants_quiz.getInt("nb_participants_quiz"); // on récupère le nombre       	
	        }
	        
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nb_participants_quiz;
	}
	
	public int getHeuresMoyenne(int id_quiz){
		Connection conn = null;
		int heure_quiz_moyenne = 1;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_heure = (Statement) conn.createStatement();
			query_heure_moyenne = "SELECT AVG(HEURE_USR_QUIZ) AS heure_moyenne_quiz FROM JOUER WHERE ID_QUIZ="+ id_quiz;
			stmt_heure.executeQuery(query_heure_moyenne);
			ResultSet rs_heure = stmt_heure.getResultSet();
			while(rs_heure.next()){
				heure_quiz_moyenne = rs_heure.getInt("heure_moyenne_quiz");
			}
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		return heure_quiz_moyenne;	
	}
	public int getMinutesMoyenne(int id_quiz){
		Connection conn = null;
		int minute_quiz_moyenne = 1;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_minute = (Statement) conn.createStatement();
			query_minute_moyenne = "SELECT AVG(MINUTE_USR_QUIZ) AS minute_moyenne_quiz FROM JOUER WHERE ID_QUIZ="+ id_quiz;
			stmt_minute.executeQuery(query_minute_moyenne);
			ResultSet rs_minute = stmt_minute.getResultSet();
			while(rs_minute.next()){
				minute_quiz_moyenne = rs_minute.getInt("minute_moyenne_quiz");
			}
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		return minute_quiz_moyenne;	
	}
	public int getSecondesMoyenne(int id_quiz){
		Connection conn = null;
		int seconde_quiz_moyenne = 1;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_seconde = (Statement) conn.createStatement();
			query_heure_moyenne = "SELECT AVG(SECONDE_USR_QUIZ) AS seconde_moyenne_quiz FROM JOUER WHERE ID_QUIZ="+id_quiz;
			stmt_seconde.executeQuery(query_heure_moyenne);
			ResultSet rs_seconde = stmt_seconde.getResultSet();
			while(rs_seconde.next()){
				seconde_quiz_moyenne = rs_seconde.getInt("seconde_moyenne_quiz");
			}
	    } catch (SQLException eeee) {
	    	eeee.printStackTrace();
	    } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
		}
		return seconde_quiz_moyenne;	
	}
	
	/**
	 * Calcule le nombre de quiz.
	 * @throws SQLException 
	 */
	public void nbQuiz(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        
	        Statement stmt_nb_quiz_dispo = (Statement) conn.createStatement();
	        query_nb_quiz_dispo = "SELECT COUNT (ID_QUIZ) AS nb_quiz_dispo FROM QUIZ";	       
	        stmt_nb_quiz_dispo.executeQuery(query_nb_quiz_dispo);	
	        ResultSet rs_nb_quiz_dispo = stmt_nb_quiz_dispo.getResultSet();
	        
	        while(rs_nb_quiz_dispo.next()){
	        	db_nb_quiz_dispo = rs_nb_quiz_dispo.getString("nb_quiz_dispo"); 
	        	nb_quiz_dispo = new JLabel("Nombre de quiz disponibles : "+db_nb_quiz_dispo);
	        	nb_quiz_dispo.setBounds(188, 210, 450, 125);
	    		nb_quiz_dispo.setForeground(Color.WHITE); 
	    		nb_quiz_dispo.setFont(new Font("Arial", Font.PLAIN, 17));
	    		add(nb_quiz_dispo);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * Methodes override du MouseListener
	 */
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran
		g.drawImage(Images.img_element[1], 140, 113, 8, 655, null); // barre bleu
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (list_quiz_stats.getItemCount() > 0 && list_quiz_stats.getSelectedItem() != null) {
			String item_nom = "";
			int item_quest = 0;
			int item_score = 0;
			String item_temps = "";
			int item_participant = 0;
			String item_temps_moyen = "";
			if(diff == 1){
				int init = list_quiz_stats.getSelectedIndex();
				int id = ListeQuizStats_facile[init].getId();
				item_quest = ListeQuizStats_facile[init].getNb_questions();
				item_temps = ListeQuizStats_facile[init].getHeureQuiz() +"h "+  ListeQuizStats_facile[init].getMinuteQuiz()+"m "+ ListeQuizStats_facile[init].getSecondeQuiz() + "s";
				item_nom = list_quiz_stats.getSelectedItem();
				item_score = scoreMoyen(id);
				item_participant = nbParticiants(id);
				item_temps_moyen = getHeuresMoyenne(id) + "h " + getMinutesMoyenne(id) + "m " + getSecondesMoyenne(id) + "s";
			}
			else if(diff == 2){
				int init = list_quiz_stats.getSelectedIndex();
				int id = ListeQuizStats_moyen[init].getId();
				item_quest = ListeQuizStats_moyen[init].getNb_questions();
				item_temps = ListeQuizStats_moyen[init].getHeureQuiz() +"h "+  ListeQuizStats_moyen[init].getMinuteQuiz()+"m "+ ListeQuizStats_moyen[init].getSecondeQuiz() + "s";
				item_nom = list_quiz_stats.getSelectedItem();
				item_score = scoreMoyen(id);
				item_participant = nbParticiants(id);
				item_temps_moyen = getHeuresMoyenne(id) + "h " + getMinutesMoyenne(id) + "m " + getSecondesMoyenne(id) + "s";
			}
			else if(diff == 3){
				int init = list_quiz_stats.getSelectedIndex();
				int id = ListeQuizStats_difficile[init].getId();
				item_quest = ListeQuizStats_difficile[init].getNb_questions();
				item_temps = ListeQuizStats_difficile[init].getHeureQuiz() +"h "+  ListeQuizStats_difficile[init].getMinuteQuiz()+"m "+ ListeQuizStats_difficile[init].getSecondeQuiz() + "s";
				item_nom = list_quiz_stats.getSelectedItem();
				item_score = scoreMoyen(id);
				item_participant = nbParticiants(id);
				item_temps_moyen = getHeuresMoyenne(id) + "h " + getMinutesMoyenne(id) + "m " + getSecondesMoyenne(id) + "s";
			}
			else {
				int init = list_quiz_stats.getSelectedIndex();
				int id = ListeQuizStats[init].getId();
				item_quest = ListeQuizStats[init].getNb_questions();
				item_temps = ListeQuizStats[init].getHeureQuiz() +"h "+  ListeQuizStats[init].getMinuteQuiz()+"m "+ ListeQuizStats[init].getSecondeQuiz() + "s";
				item_nom = list_quiz_stats.getSelectedItem();
				item_score = scoreMoyen(id);
				item_participant = nbParticiants(id);
				item_temps_moyen = getHeuresMoyenne(id) + "h " + getMinutesMoyenne(id) + "m " + getSecondesMoyenne(id) + "s";
			}
			lb_nom_quiz.setText("Nom du quiz : " + item_nom);
			lb_nb_quest_quiz.setText("Nombre de questions du quiz : " + item_quest);
			lb_score_quiz.setText("Score du quiz : " + item_score);
			lb_temps_quiz.setText("Temps du quiz : " + item_temps);
			lb_nb_part_quiz.setText("Nombre de participants : " + item_participant);
			lb_temps_moyen_quiz.setText("Temps moyen sur ce quiz : " + item_temps_moyen);
		}else{System.out.println("marche pas");
		}
	}
}
