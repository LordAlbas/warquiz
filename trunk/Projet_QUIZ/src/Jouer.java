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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
	private Bouton bt_afficherQuizRien;
	private JButton bt_jouer;
	
	private Tableau Tableau_quiz;
	public static List list_quizcree;
	public static Quiz[] ListeQuiz;
	public static Quiz[] ListeQuiz_facile;
	public static Quiz[] ListeQuiz_moyen;
	public static Quiz[] ListeQuiz_difficile;
	
	/**
	 * Constructeur
	 */
	public Jouer(Fenetre fen) {
		setLayout(null);
		fenetre = fen;  // on r�cup�re la classe m�re
		
		SQL_Requete_Quiz maRequete = new SQL_Requete_Quiz(fenetre);
		maRequete.recup_Quiz();
		ListeQuiz = maRequete.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_facile = new SQL_Requete_Quiz(fenetre);
		maRequete_facile.recup_Quiz_facile();
		ListeQuiz_facile = maRequete_facile.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_moyen = new SQL_Requete_Quiz(fenetre);
		maRequete_moyen.recup_Quiz_moyen();
		ListeQuiz_moyen = maRequete_moyen.getMesQuiz();
		
		SQL_Requete_Quiz maRequete_difficile = new SQL_Requete_Quiz(fenetre);
		maRequete_difficile.recup_Quiz_difficile();
		ListeQuiz_difficile = maRequete_difficile.getMesQuiz();
		
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
		
		lb_titreListeQuiz = new JLabel("Vous pouvez jouer à ces quiz !");
		lb_titreListeQuiz.setForeground(Color.WHITE);
		lb_titreListeQuiz.setFont(new Font("Arial", Font.PLAIN, 25));
		lb_titreListeQuiz.setBounds(60, 221, 355, 50);
		add(lb_titreListeQuiz);
		
		
		bt_afficherAllQuiz = new Bouton("Tous");
		bt_afficherAllQuiz.setSize(122, 36);
		bt_afficherAllQuiz.setLocation(60, 282);
		bt_afficherAllQuiz.addActionListener(bt_afficherAllQuiz);
		add(bt_afficherAllQuiz);		
		
		bt_afficherQuizFacile = new Bouton("Facile");
		bt_afficherQuizFacile.setSize(122, 36);
		bt_afficherQuizFacile.setLocation(183, 282);
		bt_afficherQuizFacile.addActionListener(bt_afficherQuizFacile);
		add(bt_afficherQuizFacile);
		
		bt_afficherQuizMoyen = new Bouton("Moyen");
		bt_afficherQuizMoyen.setSize(122, 36);
		bt_afficherQuizMoyen.setLocation(306, 282);
		bt_afficherQuizMoyen.addActionListener(bt_afficherQuizMoyen);
		add(bt_afficherQuizMoyen);
		
		bt_afficherQuizDifficile = new Bouton("Difficile");
		bt_afficherQuizDifficile.setSize(122, 36);
		bt_afficherQuizDifficile.setLocation(429, 282);
		bt_afficherQuizDifficile.addActionListener(bt_afficherQuizDifficile);
		add(bt_afficherQuizDifficile);
		
		JLabel info = new JLabel("Selectionnez un quiz parmis la liste ci-dessus pour jouer !");
		info.setFont(new Font("Arial", Font.PLAIN, 12));
		info.setForeground(Color.RED);
		info.setBounds(145, 660, 327, 14);
		add(info);
		
		bt_jouer = new JButton("Jouer");
		
		bt_jouer.setEnabled(false);
		bt_jouer.setForeground(Color.WHITE);
		bt_jouer.setFont(new Font("Arial", Font.PLAIN, 20));
		bt_jouer.setBackground(new Color(7, 92, 120));
		bt_jouer.setBorder(null);
		
		bt_jouer.setBounds(850, 500, 122, 36);
		bt_jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jouer_partie jouer_Quiz = new Jouer_partie(fenetre, ListeQuiz[list_quizcree.getSelectedIndex()]);
				fenetre.getContentPane().setVisible(false);
				jouer_Quiz.addMouseListener(jouer_Quiz);
				fenetre.setContentPane(jouer_Quiz);
				fenetre.getContentPane().setVisible(true);
			}
		});
		add(bt_jouer);
		

				
		// liste des quiz creer par cet admin
		list_quizcree = new List();
		list_quizcree.setBounds(60, 318, 491, 319);
		list_quizcree.addItemListener(this);
		list_quizcree.setBackground(Color.WHITE);
		add(list_quizcree);
		
		

		// remplissage de la liste avec une requete du style "recuperer tout les quiz creer par cet admin"
		for (short i=0; i<ListeQuiz.length; i++) {
			
		
			list_quizcree.add(ListeQuiz[i].getNom()+" - [ "+ListeQuiz[i].getNb_questions()+" question(s) ] - Temps : " + ListeQuiz[i].getTempsQuiz());
		}
		

		
		// il faut remplir la liste avec une requete du style "recuperer tout les quiz creer par cet admin"
		//list_quizcree.add("quiz qui rox");
		//list_quizcree.add("quiz qui rox un peu moins");
		
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
		if (list_quizcree.getItemCount() > 0 && list_quizcree.getSelectedItem() != null) {
			bt_jouer.setEnabled(true);
			bt_jouer.setBackground(new Color(7, 192, 30));
		}else{bt_jouer.setEnabled(false);}
	}
}
