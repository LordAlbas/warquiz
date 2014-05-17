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
	public String query_score,query_score_user,query_moyenne,query_moyenne_total,query_nb_parties,query_nb_quiz,query_quiz_joue,query_nb_participants_quiz,query_score_diff,query_nb_quiz_dispo,query_score_facile,query_score_moyen,query_score_difficile;
	private String db_score,db_score_diff,db_num_quiz_diff,db_diff,db_moyenne,db_moyenne_total,db_num_quiz,db_nb_parties,db_nb_quiz,db_quiz_joue,db_nb_participants_quiz,db_nb_quiz_dispo; // le score sorti de la bdd
	public JLabel score,titreA,titreU,score_moyen_total,nb_parties,nb_quiz,quiz_joue,nb_participants_quiz,nb_quiz_dispo,lb_score_moyen_quiz_difficile,lb_score_moyen_quiz_moyen,lb_score_moyen_quiz_facile,lb_score_user_quiz; // le score d'un joueur pour un quiz
	private JLabel lb_titreStatistiques,score_diff,lb_nom_quiz,lb_score_quiz,lb_temps_quiz,lb_nb_quest_quiz;
	private Bouton bouton;
	private String texte;
	public static List list_quiz_stats_user;
	public static Quiz[] ListeQuizStats_user;
	public static Quiz[] ListeQuizStats_facile_user;
	public static Quiz[] ListeQuizStats_moyen_user;
	public static Quiz[] ListeQuizStats_difficile_user;
	public static short i;
	

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
		filtre_tous.setLocation(148, 300);
		filtre_tous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(filtre_tous);
		
		Bouton filtre_facile = new Bouton("Facile");
		filtre_facile.setLocation(264, 300);
		filtre_facile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(filtre_facile);
		
		Bouton filtre_moyen = new Bouton("Moyen");
		filtre_moyen.setLocation(380, 300);
		filtre_moyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(filtre_moyen);
		
		Bouton filtre_difficile = new Bouton("Difficile");
		filtre_difficile.setLocation(496, 300);
		filtre_difficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		add(filtre_difficile);
		 
		list_quiz_stats_user = new List();
		list_quiz_stats_user.setBounds(148, 336, 463, 200);
		list_quiz_stats_user.addItemListener(this);
		list_quiz_stats_user.setBackground(Color.WHITE);
		list_quiz_stats_user.setBackground(new Color(54, 90, 118));
		list_quiz_stats_user.setForeground(Color.WHITE);
		add(list_quiz_stats_user);

		titreU = new JLabel("Statistiques Utilisateur");
		titreU.setForeground(Color.WHITE);
		titreU.setFont(new Font("Arial", Font.PLAIN, 30));
    	titreU.setBounds(172, 120, 450,200); 
		add(titreU);

		nbQuizJouees();
		
		
		for (i=0; i<ListeQuizStats_user.length; i++) {
			list_quiz_stats_user.add(ListeQuizStats_user[i].getNom());
		}
		
		lb_nom_quiz = new JLabel("Nom du quiz :");
		lb_nom_quiz.setForeground(Color.WHITE);
		lb_nom_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_nom_quiz.setBounds(180, 540, 400, 50);
		add(lb_nom_quiz);
		
		lb_score_quiz = new JLabel("Score moyen du quiz :");
		lb_score_quiz.setForeground(Color.ORANGE);
		lb_score_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_score_quiz.setBounds(180, 560, 400, 50);
		add(lb_score_quiz);
		
		lb_score_user_quiz = new JLabel("Votre score moyen à ce quiz :");
		lb_score_user_quiz.setForeground(Color.WHITE);
		lb_score_user_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_score_user_quiz.setBounds(180, 580, 400, 50);
		add(lb_score_user_quiz);
		
		lb_temps_quiz = new JLabel("Temps du quiz :");
		lb_temps_quiz.setForeground(Color.GREEN);
		lb_temps_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_temps_quiz.setBounds(180, 600, 400, 50);
		add(lb_temps_quiz);
		
		lb_nb_quest_quiz = new JLabel("Nombre de questions du quiz : ");
		lb_nb_quest_quiz.setForeground(Color.WHITE);
		lb_nb_quest_quiz.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_nb_quest_quiz.setBounds(180, 620, 400, 50);
		add(lb_nb_quest_quiz);
		
		lb_score_moyen_quiz_facile = new JLabel("Score moyen des quiz faciles : ");
		lb_score_moyen_quiz_facile.setForeground(Color.WHITE);
		lb_score_moyen_quiz_facile.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_score_moyen_quiz_facile.setBounds(180, 640, 400, 50);
		add(lb_score_moyen_quiz_facile);
		
		lb_score_moyen_quiz_moyen = new JLabel("Score moyen des quiz moyens : ");
		lb_score_moyen_quiz_moyen.setForeground(Color.WHITE);
		lb_score_moyen_quiz_moyen.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_score_moyen_quiz_moyen.setBounds(180, 660, 400, 50);
		add(lb_score_moyen_quiz_moyen);
		
		lb_score_moyen_quiz_difficile = new JLabel("Score moyen des quiz difficiles : ");
		lb_score_moyen_quiz_difficile.setForeground(Color.WHITE);
		lb_score_moyen_quiz_difficile.setFont(new Font("Arial", Font.PLAIN, 17));
		lb_score_moyen_quiz_difficile.setBounds(180, 680, 400, 50);
		add(lb_score_moyen_quiz_difficile);
		
		scoreMoyenFacile();
		scoreMoyenMoyen();
		scoreMoyenDifficile();
		
	}

	
	/**
	 * Récupère le numéro et le score de chaques quiz que le joueur à fait.
	 * @throws SQLException
	 */
	public int Score(){
		int score_moyen_user = 0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_score_user;
	        stmt_score_user = (Statement) conn.createStatement();
	        query_score_user = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen_user FROM JOUER WHERE ID_QUIZ = "+ ListeQuizStats_user[list_quiz_stats_user.getSelectedIndex()].getId() +" AND LOGIN_USR = '" +Connexion.login_general + "';";	       
	        stmt_score_user.executeQuery(query_score_user);	
	        ResultSet rs_score_user = stmt_score_user.getResultSet();
	        while(rs_score_user.next()){
	        	score_moyen_user = rs_score_user.getInt("score_moyen_user"); // on récupère le score
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score_moyen_user;
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
	 * @throws SQLException 
	 */
	public int scoreMoyen(){
		int score_moyen = 0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_moyenne = (Statement) conn.createStatement();
	        query_moyenne = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen FROM JOUER WHERE ID_QUIZ = "+ ListeQuizStats_user[list_quiz_stats_user.getSelectedIndex()].getId();      
	        stmt_moyenne.executeQuery(query_moyenne);	
	        ResultSet rs_moyenne = stmt_moyenne.getResultSet();
	        
	        if(rs_moyenne.next()){
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
	public void scoreMoyenFacile(){
		int score_moyen_facile = 0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_score_facile = (Statement) conn.createStatement();
	        query_score_facile = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen_facile FROM JOUER,QUIZ WHERE JOUER.ID_QUIZ = QUIZ.ID_QUIZ AND QUIZ.DIFFICULTE_QUIZ = 1";      
	        stmt_score_facile.executeQuery(query_score_facile);	
	        ResultSet rs_score_facile = stmt_score_facile.getResultSet();
	        
	        if(rs_score_facile.next()){
	        	score_moyen_facile = rs_score_facile.getInt("score_moyen_facile"); // on récupère la moyenne
	        	lb_score_moyen_quiz_facile.setText("Score moyen des quiz faciles : "+ score_moyen_facile);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void scoreMoyenMoyen(){
		int score_moyen_moyen = 0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_score_moyen = (Statement) conn.createStatement();
	        query_score_moyen = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen_moyen FROM JOUER, QUIZ WHERE JOUER.ID_QUIZ = QUIZ.ID_QUIZ AND QUIZ.DIFFICULTE_QUIZ = 2";      
	        stmt_score_moyen.executeQuery(query_score_moyen);	
	        ResultSet rs_score_moyen = stmt_score_moyen.getResultSet();
	        
	        if(rs_score_moyen.next()){
	        	score_moyen_moyen = rs_score_moyen.getInt("score_moyen_moyen"); // on récupère la moyenne
	        	lb_score_moyen_quiz_moyen.setText("Score moyen des quiz moyens : "+ score_moyen_moyen);
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void scoreMoyenDifficile(){
		int score_moyen_difficile = 0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_score_difficile = (Statement) conn.createStatement();
	        query_score_difficile = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen_dfifficile FROM JOUER,QUIZ WHERE JOUER.ID_QUIZ = QUIZ.ID_QUIZ AND QUIZ.DIFFICULTE_QUIZ = 3";      
	        stmt_score_difficile.executeQuery(query_score_difficile);	
	        ResultSet rs_score_difficile = stmt_score_difficile.getResultSet();
	        
	        if(rs_score_difficile.next()){
	        	score_moyen_difficile = rs_score_difficile.getInt("score_moyen_dfifficile"); // on récupère la moyenne
	        	lb_score_moyen_quiz_difficile.setText("Score moyen des quiz difficiles : "+ score_moyen_difficile);
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
	 * Calcule et affiche la moyenne des scores des quiz
	 * @throws SQLException 
	 */
	public void scoreMoyenTotal(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
	        Statement stmt_moyenne_total = (Statement) conn.createStatement();
	        query_moyenne_total = "SELECT AVG(SCORE_USR_QUIZ) AS score_moyen_total FROM JOUER WHERE LOGIN_USR = '"+ Connexion.login_general + "';";	       
	        stmt_moyenne_total.executeQuery(query_moyenne_total);	
	        ResultSet rs_moyenne_total = stmt_moyenne_total.getResultSet();
	        
	        while(rs_moyenne_total.next()){
	        	db_moyenne_total = rs_moyenne_total.getString("score_moyen_total"); // on récupère la moyenne
	        	score_moyen_total = new JLabel("Le score moyen aux quiz est de : " + db_moyenne_total);
	    		score_moyen_total.setBounds(188, 180, 450, 180);
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
	        query_nb_parties = "SELECT NB_PARTIE_JOUE AS nb_parties FROM UTILISATEUR WHERE LOGIN_USR = '"+ Connexion.login_general + "';";	       
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
	        query_quiz_joue = "SELECT NB_QUIZ_JOUE FROM UTILISATEUR WHERE LOGIN_USR = '"+ Connexion.login_general + "';";	       
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
	public void nbParticiants(int num_quiz){
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
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	        	nb_quiz_dispo.setBounds(188, 140, 450,220);
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
		if (list_quiz_stats_user.getItemCount() > 0 && list_quiz_stats_user.getSelectedItem() != null) {
			int init = list_quiz_stats_user.getSelectedIndex();
			String item_nom = "";
			int item_quest = 0;
			int item_score = 0;
			int item_score_user = 0;
			String item_temps = "";
			item_nom = list_quiz_stats_user.getSelectedItem();
			item_quest = ListeQuizStats_user[init].getNb_questions();
			item_score = scoreMoyen();
			item_score_user = Score();
			item_temps = ListeQuizStats_user[init].getHeureQuiz() +"h "+  ListeQuizStats_user[init].getMinuteQuiz()+"m "+ ListeQuizStats_user[init].getSecondeQuiz() + "s";
			lb_nom_quiz.setText("Nom du quiz : " + item_nom);
			lb_nb_quest_quiz.setText("Nombre de questions du quiz : " + item_quest);
			lb_score_quiz.setText("Score moyen du quiz : " + item_score);
			lb_score_user_quiz.setText("Votre score moyen à ce quiz : " + item_score_user);
			lb_temps_quiz.setText("Temps du quiz : " + item_temps);
		}else{System.out.println("marche pas");
		}
	}
}
