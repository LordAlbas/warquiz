import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Statistiques extends JPanel implements MouseListener, MouseMotionListener{
	
	private Fenetre fenetre;
	
	public static String selection; 		// defini quel bouton est selectionn�
	private Header header1;
	private Header_menu header2;
	public String query_score;
	private String db_score; // le score sorti de la bdd
	private String db_num_quiz; // le numéro du quiz 
	public JLabel score; // le nouveau score de l'utilisateur pour un quiz
	
	/**
	 * Constructeur
	 */
	public Statistiques(Fenetre fen) {
		fenetre = fen; // on r�cup�re la classe m�re
		setLayout(null);
		repaint();
		//****Inclusion du Header en 2 parties ****
        header1 = new Header(fen);
        header1.setBounds(0, 0, 444, 130);
        header1.addMouseListener(header1);
        header1.addMouseMotionListener(header1);
        this.add(header1); 
     

        header2 = new Header_menu(fen);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
        
        
        //*******TEST FONCTIONS NOUVELLES
        try {
			Score();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        //*******
	}
	
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
	        	db_num_quiz = rs_score.getString("ID_QUIZ"); // on récupère le score
	        	
	        	score = new JLabel("Votre score pour le quiz n°" + db_num_quiz + " est de : " + db_score);
	    		score.setBounds(48, cpt, 600,600);
	    		score.setForeground(Color.WHITE); 
	    		add(score);
	    		
	    		cpt = cpt +20;
	        	
	        }
		} catch(ClassNotFoundException e){
			e.printStackTrace();}
	}
	
	
	public void nbPartiesJouees(){}
	public void meilleurScore(){}
	//public 
	
	
	
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
	}
}
