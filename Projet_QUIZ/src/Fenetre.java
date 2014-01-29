import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Fenetre extends JFrame {
	public static String titreFenetre = "(> o _ o )>          --[__W  A  R___Q  U  I  Z__]--          <[ x _ x <]";		// titre de la fenetre
	public static Dimension tailleFenetre = new Dimension(1030, 796);	// taille de la fenetre
	
	Statistiques statistiques ;
	Accueil accueil;
	Credits credits;
	Jouer jouer;
	
	/**
	 * Constructeur
	 */
	public Fenetre() {
		setTitle(titreFenetre);
		setSize(tailleFenetre);
		setResizable(false);
		setLocationRelativeTo(null);			// la fenetre apparait au milieu de l'ecran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Connect.tryConnect();	// !! LIGNE EN COMMENTAIRE JUSTE POUR TRAVAILLER EN DEHORS DE L'EPSI !!
		
		accueil = new Accueil(this);		// creation du JPanel accueil
		statistiques = new Statistiques(this); // creation du JPanel statistiques
		credits = new Credits(this);        // creation du JPanel credits
		jouer = new Jouer(this);
		
		setLayout(new GridLayout(1, 1));		// Layout grid (tableau)  1 colonne 1 ligne
		accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		accueil.addMouseMotionListener(accueil);
		this.setContentPane(accueil);			// ajout du JPanel au JFrame (gridLayout)
		setVisible(true);
	}
	
	/**
	 * @param selection
	 */
	public void goToAccueil(String selection){
		this.getContentPane().setVisible(false);
		accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		accueil.addMouseMotionListener(accueil);
		this.setContentPane(accueil);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * @param selection
	 */
	public void goToStatistiques(String selection){
		this.getContentPane().setVisible(false);
		statistiques.addMouseListener(statistiques);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		statistiques.addMouseMotionListener(statistiques);
		this.setContentPane(statistiques);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * @param selection
	 */
	public void goToCredits(String selection){
		this.getContentPane().setVisible(false);
		credits.addMouseListener(credits);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		credits.addMouseMotionListener(credits);
		this.setContentPane(credits);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * @param selection
	 */
	public void goToJouer(String selection){
		this.getContentPane().setVisible(false);
		jouer.addMouseListener(jouer);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		jouer.addMouseMotionListener(jouer);
		this.setContentPane(jouer);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * @param selection
	 */
	public void goToQuitter(String selection){
		int rep;
		 rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?");

		if(rep == 0){
		 this.dispose();
		}
		
	}
	
	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Fenetre fenetre = new Fenetre();
		// et roule ma poule
	}
	
}
