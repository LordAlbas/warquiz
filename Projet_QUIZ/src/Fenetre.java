import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Fenetre extends JFrame {
	public static String titreFenetre = "(> o _ o )>          --[__W  A  R___Q  U  I  Z__]--          <[ x _ x <]";		// titre de la fenetre
	public static Dimension tailleFenetre = new Dimension(1030, 796);	// taille de la fenetre

	// Definition des JPanel de l'appli (en private pour le moment)
	public Statistiques statistiques ;
	public Accueil accueil;
	public Credits credits;
	public Jouer jouer;
	public Inscription inscription;
	public Connexion connexion;
	public Creation_quiz creation_quiz;

	/**
	 * Constructeur
	 */
	public Fenetre() {
		setTitle(titreFenetre);
		setSize(tailleFenetre);
		setResizable(false);
		setLocationRelativeTo(null);			// la fenetre apparait au milieu de l'ecran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Images.initImage();		
		
		// initialisation des JPanel
		accueil = new Accueil(this);			// creation du JPanel accueil
		statistiques = new Statistiques(this); // creation du JPanel statistiques
		credits = new Credits(this);        	// creation du JPanel credits
		jouer = new Jouer(this);				// creation du JPanel jouer
		inscription = new Inscription(this);	// creation du JPanel inscription
		connexion = new Connexion(this);			// creation du JPanel conexion
		creation_quiz = new Creation_quiz(this);
		
		// ajout des mouseListener
		accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		accueil.addMouseMotionListener(accueil);	
		connexion.addMouseListener(connexion);		// 'connexion' implemente les methodes relatif a l'ecoute de la souris
		connexion.addMouseMotionListener(connexion);
		inscription.addMouseListener(inscription);	// 'inscription' implemente les methodes relatif a l'ecoute de la souris
		inscription.addMouseMotionListener(inscription);
		creation_quiz.addMouseListener(creation_quiz);	// 'creation_quiz' implemente les methodes relatif a l'ecoute de la souris
		//creation_quiz.addMouseMotionListener(creation_quiz);
		
		setLayout(new GridLayout(1, 1));		// Layout grid (tableau)  1 colonne 1 ligne
		this.setContentPane(connexion);			// ajout du JPanel au JFrame (gridLayout)

		connexion.setOpaque(false);
		setVisible(true);
		SQL_Connect.tryConnect();	// !! LIGNE EN COMMENTAIRE JUSTE POUR TRAVAILLER EN DEHORS DE L'EPSI !!
		repaint();
		//this.goToConnexion("jouer");
	}
	
	/**
	 * Redirige sur INSCRIPTION
	 * @param selection
	 */
	public void goToInscription(String selection) {
		this.getContentPane().setVisible(false);
		this.setContentPane(inscription);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur CONNEXION SANS AFFICHER D'ALERTE
	 * @param selection
	 */
	public void goToConnexion(String selection) {
		this.getContentPane().setVisible(false);
		this.setContentPane(connexion);		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur CONNEXION
	 * @param selection
	 */
	public void goToConnexionAlerte(String selection) {
		int rep_deco = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment vous déconnecter ?");
		if(rep_deco == 0){
			this.getContentPane().setVisible(false);	
			this.setContentPane(connexion);
			this.getContentPane().setVisible(true);
			SQL_Connect.tryConnect();	
		}
	}
	
	/**
	 * Redirige sur l'ACCUEIL
	 * @param selection
	 */
	public void goToAccueil(String selection) {
		this.getContentPane().setVisible(false);
		this.setContentPane(accueil);
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur les STATISTIQUES
	 * @param selection
	 */
	public void goToStatistiques(String selection) {
		this.getContentPane().setVisible(false);
		statistiques.addMouseListener(statistiques);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		statistiques.addMouseMotionListener(statistiques);
		this.setContentPane(statistiques);		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur les CREDITS
	 * @param selection
	 */
	public void goToCredits(String selection) {
		this.getContentPane().setVisible(false);
		credits.addMouseListener(credits);				// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		credits.addMouseMotionListener(credits);
		this.setContentPane(credits);
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur JOUER
	 * @param selection
	 */
	public void goToJouer(String selection) {
		this.getContentPane().setVisible(false);
		creation_quiz.addMouseListener(creation_quiz);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		//creation_quiz.addMouseMotionListener(creation_quiz);
		this.setContentPane(creation_quiz);
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * QUITTE l'application
	 * @param selection
	 */
	public void goToQuitter(String selection) {
		int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?");
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
