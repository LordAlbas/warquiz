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


public class Statistiques extends JPanel implements MouseListener, MouseMotionListener, ItemListener{
	
	private Fenetre fenetre;
	
	public static String selection; 		// defini quel bouton est selectionn�
	private Header header1;
	private Header_menu header2;
	public String query_score,query_moyenne,query_moyenne_total,query_nb_parties,query_nb_quiz,query_quiz_joue,query_nb_participants_quiz,query_score_diff,query_nb_quiz_dispo;
	private String db_score,db_score_diff,db_num_quiz_diff,db_diff,db_moyenne,db_moyenne_total,db_num_quiz,db_nb_parties,db_nb_quiz,db_quiz_joue,db_nb_participants_quiz,db_nb_quiz_dispo; // le score sorti de la bdd
	public JLabel score,titreA,titreU,score_moyen,score_moyen_total,nb_parties,nb_quiz,quiz_joue,nb_participants_quiz,nb_quiz_dispo; // le score d'un joueur pour un quiz
	private JLabel lb_titreStatistiques,score_diff;
	private Bouton bouton;
	private String texte;
	public static List list_quiz_stats_user;
	public static Quiz[] ListeQuizStats_user;
	public static Quiz[] ListeQuizStats_facile_user;
	public static Quiz[] ListeQuizStats_moyen_user;
	public static Quiz[] ListeQuizStats_difficile_user;
	

	/**
	 * Constructeur
	 * @throws SQLException 
	 */
	public Statistiques(Fenetre fen) throws SQLException {
		fenetre = fen; // on r�cup�re la classe m�re
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
        ListeQuizStats_user = maRequete_stats.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_stats_facile = new SQL_Requete_Quiz(fenetre);
		maRequete_stats_facile.recup_Quiz_facile();
		ListeQuizStats_facile_user = maRequete_stats_facile.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_stats_moyen = new SQL_Requete_Quiz(fenetre);
		maRequete_stats_moyen.recup_Quiz_moyen();
		ListeQuizStats_moyen_user = maRequete_stats_moyen.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_stats_difficile = new SQL_Requete_Quiz(fenetre);
		maRequete_stats_difficile.recup_Quiz_difficile();
		ListeQuizStats_difficile_user = maRequete_stats_difficile.getMesQuiz();
        
        header2 = new Header_menu(fen);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
        
		lb_titreStatistiques = new JLabel("Statistiques");
		lb_titreStatistiques.setForeground(Color.WHITE);
		lb_titreStatistiques.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreStatistiques.setBounds(575, 105, 400, 50);
		add(lb_titreStatistiques);
        
		 Bouton filtre_tous = new Bouton("Tous");
		 filtre_tous.setLocation(260, 300);
		 filtre_tous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
		 });
		 add(filtre_tous);
		 
		 Bouton filtre_facile = new Bouton("Facile");
		 filtre_facile.setLocation(341, 300);
		 filtre_facile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
		 });
		 add(filtre_facile);
		 
		 Bouton filtre_moyen = new Bouton("Moyen");
		 filtre_moyen.setLocation(422, 300);
		 filtre_moyen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
		 });
		 add(filtre_moyen);
		 
		 Bouton filtre_difficile = new Bouton("Difficile");
		 filtre_difficile.setLocation(503, 300);
		 filtre_difficile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
		 });
		 add(filtre_difficile);
		 
		 list_quiz_stats_user = new List();
		 list_quiz_stats_user.setBounds(260, 336, 323, 269);
		 list_quiz_stats_user.addItemListener(this);
		 list_quiz_stats_user.setBackground(Color.WHITE);
		 add(list_quiz_stats_user);
		 
		/********/
			titreU = new JLabel("STATS USER");
			titreU.setForeground(Color.WHITE);
			titreU.setFont(new Font("Arial", Font.PLAIN, 30));
    		titreU.setBounds(72, 120, 200,200); 
			add(titreU);

			nbQuizJouees();


		

		/**
        try {
			Score();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			scoreMoyen();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			nbPartiesJouees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			nbQuizJouees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			nbParticiants(11);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ScoreDifficulte(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		 **/
	}

	
	/**
	 * Récupère le numéro et le score de chaques quiz que le joueur à fait.
	 * @throws SQLException
	 */
	public void Score() throws SQLException{
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
	}
	
	/**
	 * Récupère le numéro et le score des quiz 1:FACILE 2:MOYEN ou 3:DIFFICILE que le joueur à fait.
	 * @throws SQLException
	 */
	public void ScoreDifficulte(int diff) throws SQLException{
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
	}
	
	/**
	 * Calcule et affiche le score moyen du joueur
	 * @throws SQLException 
	 */
	public void scoreMoyen() throws SQLException{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_moyenne = (Statement) conn.createStatement();
	        query_moyenne = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen FROM JOUER";	       
	        stmt_moyenne.executeQuery(query_moyenne);	
	        ResultSet rs_moyenne = stmt_moyenne.getResultSet();
	        
	        while(rs_moyenne.next()){
	        	db_moyenne = rs_moyenne.getString("score_moyen"); // on récupère la moyenne
	        	score_moyen = new JLabel("Votre score moyen est de : " + db_moyenne);
	    		score_moyen.setBounds(48, 180, 600,600);
	    		score_moyen.setForeground(Color.WHITE); 
	    		add(score_moyen);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Calcule et affiche la moyenne des scores des quiz
	 * @throws SQLException 
	 */
	public void scoreMoyenTotal() throws SQLException{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_moyenne_total = (Statement) conn.createStatement();
	        query_moyenne_total = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen_total FROM JOUER WHERE LOGIN_USR = 'ClaraMorgane'";	       
	        stmt_moyenne_total.executeQuery(query_moyenne_total);	
	        ResultSet rs_moyenne_total = stmt_moyenne_total.getResultSet();
	        
	        while(rs_moyenne_total.next()){
	        	db_moyenne_total = rs_moyenne_total.getString("score_moyen_total"); // on récupère la moyenne
	        	score_moyen_total = new JLabel("Le score moyen aux quiz est de : " + db_moyenne_total);
	    		score_moyen_total.setBounds(148, 140, 250, 250);
	    		score_moyen_total.setForeground(Color.WHITE); 
	    		add(score_moyen_total);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Calcule le nombre de parties jouées par l'utilisateur.
	 * @throws SQLException
	 */
	public void nbPartiesJouees() throws SQLException{
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
		
	}
	
	/**
	 * Calcule le nombre de quiz joué parmis le nombre max.
	 * @throws SQLException 
	 */
	public void nbQuizJouees() throws SQLException{
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
	        	nb_quiz.setBounds(148, 140, 250,220);
	    		nb_quiz.setForeground(Color.WHITE); 
	    		add(nb_quiz);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}			
	}
	
	public void meilleurScore(){} 
	
	/**
	 * Le nombre de personnes qui on paticipé à un quiz donné
	 * @param num_quiz
	 * @throws SQLException
	 */
	public void nbParticiants(int num_quiz) throws SQLException{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        
	        Statement stmt_nb_participants_quiz = (Statement) conn.createStatement();
	        query_nb_participants_quiz = "SELECT COUNT (LOGIN_USR) AS nb_participants_quiz FROM JOUER WHERE ID_QUIZ = " + num_quiz ;	       
	        stmt_nb_participants_quiz.executeQuery(query_nb_participants_quiz);	
	        ResultSet rs_nb_participants_quiz = stmt_nb_participants_quiz.getResultSet();
	        

	        
	        while(rs_nb_participants_quiz.next()){
	        	db_nb_participants_quiz = rs_nb_participants_quiz.getString("nb_participants_quiz"); // on récupère le nombre
	        	
	        	
	        	nb_participants_quiz = new JLabel("Nombre de participant pour le quiz "+num_quiz+" : "+db_nb_participants_quiz);
	        	nb_participants_quiz.setBounds(48, 100, 600,600);
	        	nb_participants_quiz.setForeground(Color.WHITE); 
	    		add(nb_participants_quiz);	        	
	        }
	        
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcule le nombre de quiz.
	 * @throws SQLException 
	 */
	public void nbQuiz() throws SQLException{
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
	        	nb_quiz_dispo.setBounds(148, 140, 250,220);
	    		nb_quiz_dispo.setForeground(Color.WHITE); 
	    		add(nb_quiz_dispo);
	        }
		} catch(ClassNotFoundException e){
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
		// TODO Auto-generated method stub
		
	}
}
