import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	public static String titreFenetre = "[ __M E G A__Q U I Z__ ]";		// titre de la fenetre
	public static Dimension tailleFenetre = new Dimension(1030, 796);	// taille de la fenetre
	
	public Fenetre() {
		setTitle(titreFenetre);
		setSize(tailleFenetre);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setLayout(new GridLayout(1, 1));		// Layout 1colonne 1ligne
		Accueil accueil = new Accueil();		// creation du JPanel accueil
		add(accueil);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Fenetre fenetre = new Fenetre();
		// et roule ma poule
	}
	
}

