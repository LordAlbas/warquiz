import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	public static String titreFenetre = "(> o _ o )>          --[__W  A  R___Q  U  I  Z__]--          <[ x _ x <]";		// titre de la fenetre
	public static Dimension tailleFenetre = new Dimension(1030, 796);	// taille de la fenetre
	Statistiques statistiques ;
	Accueil accueil;
	/**
	 * Constructeur
	 */
	public Fenetre() {
		setTitle(titreFenetre);
		setSize(tailleFenetre);
		setResizable(false);
		setLocationRelativeTo(null);			// la fenetre apparait au milieu de l'ecran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		accueil = new Accueil(this);		// creation du JPanel accueil
		statistiques = new Statistiques(this); // creation du JPanel statistiques
		Credits credits = new Credits();        // creation du JPanel credits
		
		setLayout(new GridLayout(1, 1));		// Layout grid (tableau)  1 colonne 1 ligne
		accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		accueil.addMouseMotionListener(accueil);
		this.setContentPane(accueil);			// ajout du JPanel au JFrame (gridLayout)
		
		
		
		


		
		setVisible(true);
	}
		public void goToAccueil(String selection){
			this.getContentPane().setVisible(false);
			accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
			accueil.addMouseMotionListener(accueil);
			this.setContentPane(accueil);				// ajout du JPanel au JFrame (gridLayout)		
			this.getContentPane().setVisible(true);
		}
	
		public void goToStatistiques(String selection){
			this.getContentPane().setVisible(false);
			statistiques.addMouseListener(statistiques);	// 'statistiques' implemente les methodes relatif a l'ecoute de la souris
			statistiques.addMouseMotionListener(statistiques);
			this.setContentPane(statistiques);				// ajout du JPanel au JFrame (gridLayout)		

			this.getContentPane().setVisible(true);
		}
		
		/*public void goToCredits(String selection){
			credits.addMouseListener(credits);			// 'credits' implemente les methodes relatif a l'ecoute de la souris
			//accueil.addMouseMotionListener(credits);
			this.setContentPane(credits);				// ajout du JPanel au JFrame (gridLayout)			
		}
		**/
		
	
	
	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Fenetre fenetre = new Fenetre();
		// et roule ma poule
	}
	
}
