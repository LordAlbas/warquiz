import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Jouer extends JPanel implements MouseListener, MouseMotionListener{

	private Fenetre fenetre;
	
	private Header header1;
	private Header_menu header2;
	
	private JLabel lb_titreBienvenue;
	private JLabel lb_descrJouer;
	private JLabel lb_titreListeQuiz;
	
	private JButton bt_afficherQuizFacile;
	private JButton bt_afficherQuizMoyen;
	private JButton bt_afficherQuizDifficile;
	private JButton bt_afficherAllQuiz;
	
	public String query_facile;
	public String query_moyen;
	public String query_difficile;
	public String query_all_quiz;
	
	private String db_facile; // le score sorti de la bdd
	private String db_moyen; // la moyenne
	private String db_difficile; // le numéro du quiz 
	private String db_all_quiz; // le nombre de parties
	
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
		lb_titreListeQuiz.setFont(new Font("Arial", Font.PLAIN, 18));
		lb_titreListeQuiz.setBounds(155, 275, 300, 20);
		add(lb_titreListeQuiz);
		
		bt_afficherQuizFacile = new JButton("Quiz Faciles");
		bt_afficherQuizFacile.setBounds(370, 350, 120, 35);
		bt_afficherQuizFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		add(bt_afficherQuizFacile);
		
		bt_afficherQuizMoyen = new JButton("Quiz Moyens");
		bt_afficherQuizMoyen.setBounds(500, 350, 120, 35);
		bt_afficherQuizMoyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bt_afficherQuizMoyen);
		
		bt_afficherQuizDifficile = new JButton("Quiz Difficiles");
		bt_afficherQuizDifficile.setBounds(620, 350, 120, 35);
		bt_afficherQuizDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bt_afficherQuizDifficile);
		
		bt_afficherAllQuiz = new JButton("Tous les Quiz");
		bt_afficherAllQuiz.setBounds(740, 350, 120, 35);
		bt_afficherAllQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bt_afficherAllQuiz);
		
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
}
