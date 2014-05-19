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
import javax.swing.JButton;
import javax.swing.JLabel;
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
	private JButton bt_jouer;
	private int filtre = 0;
	private Jouer_partie jouer_Quiz;
	
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
     

        header2 = new Header_menu(fen, this);
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
		bt_afficherAllQuiz.removeMouseListener(bt_afficherAllQuiz);		// REMOVE du mouseListener qui posait probleme !
		bt_afficherAllQuiz.addActionListener(bt_afficherAllQuiz);		// (le mouseListener s'utilise du cote statistique je crois bien)
		bt_afficherAllQuiz.addActionListener(new ActionListener() {		// (alors qu'ici on veut juste l'actionListener !)
			public void actionPerformed(ActionEvent e) {
				filtre = 0;
			}
		});
		add(bt_afficherAllQuiz);		
		
		bt_afficherQuizFacile = new Bouton("Facile");
		bt_afficherQuizFacile.setSize(122, 36);
		bt_afficherQuizFacile.setLocation(183, 282);
		bt_afficherQuizFacile.removeMouseListener(bt_afficherQuizFacile);
		bt_afficherQuizFacile.addActionListener(bt_afficherQuizFacile);
		bt_afficherQuizFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtre = 1;
			}
		});
		add(bt_afficherQuizFacile);
		
		bt_afficherQuizMoyen = new Bouton("Moyen");
		bt_afficherQuizMoyen.setSize(122, 36);
		bt_afficherQuizMoyen.setLocation(306, 282);
		bt_afficherQuizMoyen.removeMouseListener(bt_afficherQuizMoyen);
		bt_afficherQuizMoyen.addActionListener(bt_afficherQuizMoyen);
		bt_afficherQuizMoyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtre = 2;
			}
		});
		add(bt_afficherQuizMoyen);
		
		bt_afficherQuizDifficile = new Bouton("Difficile");
		bt_afficherQuizDifficile.setSize(122, 36);
		bt_afficherQuizDifficile.setLocation(429, 282);
		bt_afficherQuizDifficile.removeMouseListener(bt_afficherQuizDifficile);
		bt_afficherQuizDifficile.addActionListener(bt_afficherQuizDifficile);
		bt_afficherQuizDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtre = 3;
			}
		});
		add(bt_afficherQuizDifficile);
		
		JLabel info = new JLabel("Selectionnez un quiz parmis la liste ci-dessus pour jouer.");
		info.setFont(new Font("Arial", Font.PLAIN, 15));
		info.setForeground(Images.couleurLabel);
		info.setBounds(130, 660, 390, 20);
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
				if(filtre == 1){
					jouer_Quiz = new Jouer_partie(fenetre, ListeQuiz_facile[list_quizcree.getSelectedIndex()]);
				}
				else if(filtre == 2){
					jouer_Quiz = new Jouer_partie(fenetre, ListeQuiz_moyen[list_quizcree.getSelectedIndex()]);
				} else if (filtre == 3){
					jouer_Quiz = new Jouer_partie(fenetre, ListeQuiz_difficile[list_quizcree.getSelectedIndex()]);
				}else {
					jouer_Quiz = new Jouer_partie(fenetre, ListeQuiz[list_quizcree.getSelectedIndex()]);
				}
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
		list_quizcree.setBackground(new Color(54, 90, 118));
		list_quizcree.setForeground(Color.WHITE);
		add(list_quizcree);
		
		// remplissage de la liste avec une requete du style "recuperer tout les quiz creer par cet admin"
		for (short i=0; i<ListeQuiz.length; i++) {
			list_quizcree.add(ListeQuiz[i].getNom()+" - [ "+ListeQuiz[i].getNb_questions()+" question(s) ] - Temps : " + ListeQuiz[i].getHeureQuiz() + "h "+ ListeQuiz[i].getMinuteQuiz() + "m " + ListeQuiz[i].getSecondeQuiz() +"s ");
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
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (list_quizcree.getItemCount() > 0 && list_quizcree.getSelectedItem() != null) {
			bt_jouer.setEnabled(true);
			bt_jouer.setBackground(new Color(27, 113, 16));
		}else{
			bt_jouer.setEnabled(false);
		}
	}
}
