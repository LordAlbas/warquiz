import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;


public class Fenetre extends JFrame {
	public static String titreFenetre = "(> o _ o )>          --[__W  A  R___Q  U  I  Z__]--          <[ x _ x <]";		// titre de la fenetre
	public static Dimension tailleFenetre = new Dimension(1030, 796);	// taille de la fenetre

	Statistiques statistiques ;
	Accueil accueil;
	Credits credits;
	Jouer jouer;
	Inscription inscription;
	Connexion connexion;
	Admin_ajout_reponses creation_quiz;
	private JLayeredPane jlp = new JLayeredPane();
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
		//Images img = new Images();
		
		
		accueil = new Accueil(this);			// creation du JPanel accueil
		statistiques = new Statistiques(this); // creation du JPanel statistiques
		credits = new Credits(this);        	// creation du JPanel credits
		jouer = new Jouer(this);				// creation du JPanel jouer
		inscription = new Inscription(this);	// creation du JPanel inscription
		connexion = new Connexion(this);			// creation du JPanel conexion
		creation_quiz = new Admin_ajout_reponses(this);
		
		
		
		accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		accueil.addMouseMotionListener(accueil);	
		connexion.addMouseListener(connexion);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		connexion.addMouseMotionListener(connexion);
		inscription.addMouseListener(inscription);	// 'inscription' implemente les methodes relatif a l'ecoute de la souris
		inscription.addMouseMotionListener(inscription);
		creation_quiz.addMouseListener(creation_quiz);	// 'inscription' implemente les methodes relatif a l'ecoute de la souris
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
	public void goToInscription(String selection){
		this.getContentPane().setVisible(false);
		this.setContentPane(inscription);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur CONNEXION SANS AFFICHER D'ALERTE
	 * @param selection
	 */
	public void goToConnexion(String selection){
		this.getContentPane().setVisible(false);
		this.setContentPane(connexion);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
		//jlp.add(accueil, new Integer(0));
	
		//jlp.setPreferredSize(new Dimension (200, 200));
		
	
		
		//this.add(jlp);
		//jlp.setVisible(true);
		//jlp.add(connexion, new Integer (1));
		//jlp.moveToFront(connexion);
		//jlp.setOpaque(false);
		
		
	}	
	/**
	 * Redirige sur CONNEXION
	 * @param selection
	 */
	public void goToConnexionAlerte(String selection){
		int rep_deco;
		 rep_deco = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment vous déconnecter ?");

		if(rep_deco == 0){
			this.getContentPane().setVisible(false);	
			this.setContentPane(connexion);				// ajout du JPanel au JFrame (gridLayout)

			this.getContentPane().setVisible(true);
			//jlp.add(accueil, new Integer(0));
			//jlp.add(connexion, new Integer(1));
			//jlp.setVisible(true);
		}
	}	
	/**
	
	/**
	 * Redirige sur l'ACCUEIL
	 * @param selection
	 */
	public void goToAccueil(String selection){
		this.getContentPane().setVisible(false);
		this.setContentPane(accueil);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * Redirige sur les STATISTIQUES
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
	 * Redirige sur les CREDITS
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
	 * Redirige sur JOUER
	 * @param selection
	 */
	public void goToJouer(String selection){
		this.getContentPane().setVisible(false);
		creation_quiz.addMouseListener(creation_quiz);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
		//creation_quiz.addMouseMotionListener(creation_quiz);
		this.setContentPane(creation_quiz);				// ajout du JPanel au JFrame (gridLayout)		
		this.getContentPane().setVisible(true);
	}
	
	/**
	 * QUITTE l'application
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
