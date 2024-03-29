import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Fenetre extends JFrame {
	public static String titreFenetre = "(> o _ o )>          --[__W  A  R___Q  U  I  Z__]--          <[ x _ x <]";		// titre de la fenetre
	public static Dimension tailleFenetre = new Dimension(1030, 796);	// taille de la fenetre

	// Definition des JPanel de l'appli (en private pour le moment)
	public Statistiques statistiques ;
	public Statistiques_Admin statistiques_admin;
	public Connexion connexion;
	public Accueil accueil;
	public Credits credits;
	public Option option;
	public Jouer jouer;
	public Inscription inscription;
	public Gestion_quiz gestion_quiz;
	public Correction correction;
	public Quiz partie;
	public JPanel back;
	
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
		
		// creation du JPanel statistiques
		// creation du JPanel credits
		connexion = new Connexion(this);			// creation du JPanel conexion
		
		// ajout des mouseListener
		connexion.addMouseListener(connexion);		// 'connexion' implemente les methodes relatif a l'ecoute de la souris
		connexion.addMouseMotionListener(connexion);
		
		setLayout(new GridLayout(1, 1));		// Layout grid (tableau)  1 colonne 1 ligne
		this.setContentPane(connexion);			// ajout du JPanel au JFrame (gridLayout)
		
		connexion.setOpaque(false);
		setVisible(true);
		SQL_Connect.tryConnect();
		repaint();
	}
	
	/**
	 * Redirige sur INSCRIPTION
	 * @param selection
	 */
	public void goToInscription(int selection) {
		inscription = new Inscription(this, selection);
		inscription.addMouseListener(inscription);	// 'inscription' implemente les methodes relatif a l'ecoute de la souris
		inscription.addMouseMotionListener(inscription);
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
		Connexion.textField_pseudo.setText("");
		Connexion.textField_mdp.setText("");
		Connexion.erreur_log = false;
		Connexion.erreur_bdd = false;
		Connexion.bad_mdp = 0;
		Connexion.bad_pseudo = 0;
		Connexion.tentative = 0;
	}
	
	/**
	 * Redirige sur CONNEXION
	 * @param selection
	 */
	public void goToConnexionAlerte(String selection) {
		int rep_deco = JOptionPane.showConfirmDialog(null, 
				"<html>Voulez-vous vraiment vous d&eacute;connecter ?</html>", 
				"Deconnexion", 
				JOptionPane.YES_NO_OPTION);
		if(rep_deco == 0){
			this.getContentPane().setVisible(false);	
			this.setContentPane(connexion);
			this.getContentPane().setVisible(true);
			Connexion.textField_pseudo.setText("");
			Connexion.textField_mdp.setText("");
			Connexion.erreur_log = false;
			Connexion.erreur_bdd = false;
			Connexion.bad_mdp = 0;
			Connexion.bad_pseudo = 0;
			Connexion.tentative = 0;
		}
	}
	
	/**
	 * Redirige sur l'ACCUEIL
	 * @param selection
	 */
	public void goToAccueil(String selection) {
		accueil = new Accueil(this); // creation du JPanel accueil
		accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		accueil.addMouseMotionListener(accueil);
		this.getContentPane().setVisible(false);
		this.setContentPane(accueil);
		this.getContentPane().setVisible(true);
	}

	
	/**
	 * Fait un retour
	 * @param selection
	 */
	public void goToBack(JPanel jp) {
		back = jp;
		//back = new Option(this, jp); // creation du JPanel accueil
		//back.addMouseMotionListener(back);		// 'option' implemente les methodes relatif a l'ecoute de la souris
		this.getContentPane().setVisible(false);
		this.setContentPane(back);
		this.getContentPane().setVisible(true);
		repaint();
	}	
	
	/**
	 * Redirige sur OPTION
	 * @param selection
	 */
	public void goToOption(JPanel jp) {
		option = new Option(this, jp); // creation du JPanel accueil
		option.addMouseListener(option);		// 'option' implemente les methodes relatif a l'ecoute de la souris
		option.addMouseMotionListener(option);
		this.getContentPane().setVisible(false);
		this.setContentPane(option);
		this.getContentPane().setVisible(true);
	}	
	
	/**
	 * Redirige sur les STATISTIQUES
	 * @param selection
	 * @throws SQLException 
	 */
	public void goToStatistiques(String selection) throws SQLException {
		statistiques = new Statistiques(this);
		this.getContentPane().setVisible(false);
		statistiques.addMouseListener(statistiques);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		statistiques.addMouseMotionListener(statistiques);
		this.setContentPane(statistiques);		
		this.getContentPane().setVisible(true);
	}
	
	public void goToStatistiquesAdmin(String selection) throws SQLException {
		statistiques_admin = new Statistiques_Admin(this);
		this.getContentPane().setVisible(false);
		statistiques_admin.addMouseListener(statistiques_admin);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		statistiques_admin.addMouseMotionListener(statistiques_admin);
		this.setContentPane(statistiques_admin);		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur les CREDITS
	 * @param selection
	 */
	public void goToCredits(String selection) {
		credits = new Credits(this); 
		this.getContentPane().setVisible(false);
		credits.addMouseListener(credits);				// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		credits.addMouseMotionListener(credits);
		this.setContentPane(credits);
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur GERER
	 * @param selection
	 */
	public void goToGerer(String selection) {
		gestion_quiz = new Gestion_quiz(this);
		gestion_quiz.addMouseListener(gestion_quiz);
		this.getContentPane().setVisible(false);
		gestion_quiz.addMouseListener(gestion_quiz);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		this.setContentPane(gestion_quiz);
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur JOUER
	 * @param selection
	 */
	public void goToJouer(String selection) {
		jouer = new Jouer(this);
		jouer.addMouseListener(jouer);
		this.getContentPane().setVisible(false);
		jouer.addMouseListener(jouer);
		this.setContentPane(jouer);
		this.getContentPane().setVisible(true);
		
	}
	
	/**
	 * QUITTE l'application
	 * @param selection
	 */
	public void goToQuitter(String selection) {
		int rep = JOptionPane.showConfirmDialog(null, 
				"Voulez-vous vraiment quitter ?", 
				"Partir ? ... deja ?", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.WARNING_MESSAGE);
		if(rep == 0){
			this.dispose();
		}
	}
	
	public void goToCorrection(Quiz partie, Bouton_selection_question[] tabRep, int score, long temps){
		correction = new Correction(partie, tabRep, score, temps, this);
		correction.addMouseListener(correction);
		this.getContentPane().setVisible(false);
		correction.addMouseListener(correction);
		this.setContentPane(correction);
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * MAIN (le big bang de notre univer !!)
	 * @param args
	 */
	public static void main(String[] args) {
		Fenetre fenetre = new Fenetre();
		// et roule ma poule
	}


	
}
