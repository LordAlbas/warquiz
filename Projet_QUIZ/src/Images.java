import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Ceci est une classe non objet, elle n'est jamais instancié.
 * C'est la methode static initImage() qui est appelée (depuis le constructeur
 * de Fenetre) et charge les images dans les attributs static.
 * Pour appeler les attributs, c'est 'Class.attributsStatic'.
 * (Ex: 'Images.img_fond[0]' )
 * 
 * @author Merovee
 *
 */
public class Images {
	
	public static Image[] img_fond = new Image[20];			// regroupe les images de fonds
	public static Image[] img_element = new Image[20];		// regroupe les images qui forment les fenetre (barre du haut etc.)
	public static Image[] img_bouton = new Image[20];			// regroupe les images des boutons
	public static Image[] img_bouton_hover = new Image[20];	// regroupe les images des boutons quand la souris passe dessus
	
	public static int hauteur_bouton = 85;		// defini la taille en hauteur des boutons du menu
	public static int largeur_bouton = 366;		// defini la taille en largeur
	public static int ecart_bouton = 15;			// defini l'ecart entre les boutons
	
	/**
	 * Initialise les banques d'images a partir des fichier .png
	 */
	public static void initImage() {
		/*
		 * charge les images de fonds dans img_fond[]
		 */
		try {
			img_fond[0] = ImageIO.read(new File("images/accueil_fond.png"));	// Lecture du fichier .png
			img_fond[1] = ImageIO.read(new File("images/connexion.png"));	// Lecture du fichier .png
			img_fond[2] = ImageIO.read(new File("images/inscription.png"));	// Lecture du fichier .png
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * charge les images d'elements dans img_element[]
		 */
		try {
			img_element[0] = ImageIO.read(new File("images/header5.png"));
			img_element[1] = ImageIO.read(new File("images/barre_verticale.png"));
			//img_element[2] = ImageIO.read(new File("images/background_textbox.png"));
			img_element[3] = ImageIO.read(new File("images/invalide.png"));
			img_element[4] = ImageIO.read(new File("images/information_connexion.png"));
			img_element[5] = ImageIO.read(new File("images/information_pseudo_mdp.png"));
			img_element[6] = ImageIO.read(new File("images/information_bdd.png"));
			img_element[7] = ImageIO.read(new File("images/header_bleu.png"));
			img_element[8] = ImageIO.read(new File("images/fond_chargement.png"));
			img_element[9] = ImageIO.read(new File("images/header_logo.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * charge les images de boutons dans img_bouton[]
		 */
		try {
			img_bouton[0] = ImageIO.read(new File("images/accueil_bouton_jouer.png"));
			img_bouton[1] = ImageIO.read(new File("images/accueil_bouton_statistiques.png"));
			img_bouton[2] = ImageIO.read(new File("images/accueil_bouton_credit.png"));
			img_bouton[3] = ImageIO.read(new File("images/accueil_bouton_quitter.png"));
			img_bouton[4] = ImageIO.read(new File("images/bouton_deco.png"));
			img_bouton[5] = ImageIO.read(new File("images/retour.png"));
			img_bouton[6] = ImageIO.read(new File("images/bouton_option.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * charge les images de boutons en mouseover dans img_bouton_hover[]
		 */
		try {
			img_bouton_hover[0] = ImageIO.read(new File("images/accueil_bouton_jouer_mouseover.png"));
			img_bouton_hover[1] = ImageIO.read(new File("images/accueil_bouton_statistiques_mouseover.png"));
			img_bouton_hover[2] = ImageIO.read(new File("images/accueil_bouton_credit_mouseover.png"));
			img_bouton_hover[3] = ImageIO.read(new File("images/accueil_bouton_quitter_mouseover.png"));
			img_bouton_hover[4] = ImageIO.read(new File("images/bouton_deco_hover.png"));
			img_bouton_hover[5] = ImageIO.read(new File("images/retour_hover.png"));
			img_bouton_hover[6] = ImageIO.read(new File("images/bouton_option_hover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
