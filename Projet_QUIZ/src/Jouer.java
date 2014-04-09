import java.awt.Color;
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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Jouer extends JPanel implements MouseListener, MouseMotionListener, ItemListener{

	private Fenetre fenetre;
	
	private Header header1;
	private Header_menu header2;
	
	private JLabel lb_titreBienvenue;
	private JLabel lb_descrJouer;
	private JLabel lb_titreListeQuiz;
	
	private Bouton bt_afficherQuizFacile;
	private Bouton bt_afficherQuizMoyen;
	private Bouton bt_afficherQuizDifficile;
	private Bouton bt_afficherAllQuiz;
	private Bouton bt_jouer;
	
	public String query_facile;
	public String query_moyen;
	public String query_difficile;
	public String query_all_quiz;
	
	private String db_facile; // le score sorti de la bdd
	private String db_moyen; // la moyenne
	private String db_difficile; // le numéro du quiz 
	private String db_all_quiz; // le nombre de parties
	
	private List list_quizcree;
	
	/**
	 * Constructeur
	 */
	public Jouer(Fenetre fen) {
		setLayout(null);
		fenetre = fen;  // on r�cup�re la classe m�re
		
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
		
		lb_titreBienvenue = new JLabel("JOUER");
		lb_titreBienvenue.setForeground(Color.WHITE);
		lb_titreBienvenue.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreBienvenue.setBounds(575, 105, 400, 50);
		add(lb_titreBienvenue);
		
		lb_descrJouer = new JLabel("<html>Cette section vous permet de jouer aux quiz.</html>");
		lb_descrJouer.setForeground(Color.WHITE);
		lb_descrJouer.setFont(new Font("Arial", Font.PLAIN, 20));
		lb_descrJouer.setBounds(655, 175, 320, 50);
		add(lb_descrJouer);
		
		lb_titreListeQuiz = new JLabel("Liste des quiz existants");
		lb_titreListeQuiz.setForeground(Color.WHITE);
		lb_titreListeQuiz.setFont(new Font("Arial", Font.PLAIN, 25));
		lb_titreListeQuiz.setBounds(150, 150, 300, 50);
		add(lb_titreListeQuiz);
		
		bt_afficherQuizFacile = new Bouton("Facile");
		bt_afficherQuizFacile.setLocation(20, 210);
		bt_afficherQuizFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		add(bt_afficherQuizFacile);
		
		bt_afficherQuizMoyen = new Bouton("Moyen");
		bt_afficherQuizMoyen.setLocation(150, 210);
		bt_afficherQuizMoyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bt_afficherQuizMoyen);
		
		bt_afficherQuizDifficile = new Bouton("Difficile");
		bt_afficherQuizDifficile.setLocation(280, 210);
		bt_afficherQuizDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bt_afficherQuizDifficile);
		
		bt_afficherAllQuiz = new Bouton("Tous");
		bt_afficherAllQuiz.setLocation(410, 210);
		bt_afficherAllQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bt_afficherAllQuiz);
		
		bt_jouer = new Bouton("Jouer");
		bt_jouer.setLocation(850, 500);
		bt_jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bt_jouer);
		
				
		// liste des quiz creer par cet admin
		list_quizcree = new List();
		list_quizcree.setBounds(100, 300, 500, 319);
		list_quizcree.addItemListener(this);
		list_quizcree.setBackground(Color.WHITE);
		add(list_quizcree);
		
		// il faut remplir la liste avec une requete du style "recuperer tout les quiz creer par cet admin"
		list_quizcree.add("quiz qui rox");
		list_quizcree.add("quiz qui rox un peu moins");
		
		
		/* #############################
		 * 		TEST FATOUMA
		 * #############################
		 */
		JButton bt_supprQuiz = new JButton("Test Fatouma");
		bt_supprQuiz.setBounds(500, 700, 120, 35);
		bt_supprQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jouer_partie jouer_partie = new Jouer_partie(fenetre, new Quiz("Super Quiz", fenetre));
				fenetre.getContentPane().setVisible(false);
				jouer_partie.addMouseListener(jouer_partie);
				fenetre.setContentPane(jouer_partie);
				fenetre.getContentPane().setVisible(true);
			}
		});
		add(bt_supprQuiz);
		/* #############################
		 * 		FIN TEST FATOUMA
		 * #############################
		 */
		
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
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
