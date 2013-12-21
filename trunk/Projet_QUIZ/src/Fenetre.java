import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	public static String titreFenetre = "(> o _ o )>          --[__W  A  R___Q  U  I  Z__]--          <[ x _ x <]";		// titre de la fenetre
	public static Dimension tailleFenetre = new Dimension(1030, 796);	// taille de la fenetre
	
	/**
	 * Constructeur
	 */
	public Fenetre() {
		setTitle(titreFenetre);
		setSize(tailleFenetre);
		setResizable(false);
		setLocationRelativeTo(null);			// la fenetre apparait au milieu de l'ecran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Accueil accueil = new Accueil();		// creation du JPanel accueil
		setLayout(new GridLayout(1, 1));		// Layout grid (tableau)  1 colonne 1 ligne
		accueil.addMouseListener(accueil);		// 'accueil' implemente les methodes relatif a l'ecoute de la souris
		this.setContentPane(accueil);			// ajout du JPanel au JFrame (gridLayout)
		
		setVisible(true);
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
