import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Ceci est une classe non objet, elle n'est jamais instanciée.
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
	
	public static Color couleurLabel = new Color(250,130,100);  // Couleur des label... modif bienvenue!
	
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
			img_fond[3] = ImageIO.read(new File("images/accueil_fond1.png"));	// Lecture du fichier .png
			img_fond[4] = ImageIO.read(new File("images/option_panel.png"));	// Lecture du fichier .png
			
			img_fond[5] = ImageIO.read(new File("images/accueil_fond_use.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * charge les images d'elements dans img_element[]
		 */
		try {
			img_element[0] = ImageIO.read(new File("images/header1.png"));
			img_element[1] = ImageIO.read(new File("images/barre_verticale.png"));
			//img_element[2] = ImageIO.read(new File("images/background_textbox.png"));
			img_element[3] = ImageIO.read(new File("images/invalide.png"));
			img_element[4] = ImageIO.read(new File("images/information_connexion.png"));
			img_element[5] = ImageIO.read(new File("images/information_pseudo_mdp.png"));
			img_element[6] = ImageIO.read(new File("images/information_bdd.png"));
			img_element[7] = ImageIO.read(new File("images/header_bleu.png"));
			img_element[8] = ImageIO.read(new File("images/fond_chargement.png"));
			img_element[9] = ImageIO.read(new File("images/header_logo.png"));
			img_element[10] = ImageIO.read(new File("images/theme_defaut.png"));
			img_element[11] = ImageIO.read(new File("images/theme_carre.png"));
			img_element[12] = ImageIO.read(new File("images/information_bad_combi_mdp.png"));
			img_element[13] = ImageIO.read(new File("images/information_pseudo_isset.png"));
		
			img_element[14] = ImageIO.read(new File("images/header_logo_use.png"));
			img_element[15] = ImageIO.read(new File("images/barre_bleu_header_use.png"));
			
			img_element[16] = ImageIO.read(new File("images/barre_verticale_use.png"));
			img_element[17] = ImageIO.read(new File("images/theme_toxic.png"));
			
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
			img_bouton[7] = ImageIO.read(new File("images/accueil_bouton_gerer.png"));
			img_bouton[8] = ImageIO.read(new File("images/bouton_filtre.png"));
			img_bouton[9] = ImageIO.read(new File("images/delete_icon.png"));
			// theme use
			img_bouton[10] = ImageIO.read(new File("images/accueil_bouton_jouer_use.png"));
			img_bouton[11] = ImageIO.read(new File("images/accueil_bouton_statistiques_use.png"));
			img_bouton[12] = ImageIO.read(new File("images/accueil_bouton_ credit_use.png"));
			img_bouton[13] = ImageIO.read(new File("images/accueil_bouton_quitter_use.png"));
			img_bouton[17] = ImageIO.read(new File("images/accueil_bouton_gerer_use.png"));
			
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
			img_bouton_hover[7] = ImageIO.read(new File("images/accueil_bouton_gerer_mouseover.png"));
			//theme use
			img_bouton_hover[10] = ImageIO.read(new File("images/accueil_bouton_jouer_mouseover_use.png"));
			img_bouton_hover[11] = ImageIO.read(new File("images/accueil_bouton_statistiques_mouseover_use.png"));
			img_bouton_hover[12] = ImageIO.read(new File("images/accueil_bouton_credit_mouseover_use.png"));
			img_bouton_hover[13] = ImageIO.read(new File("images/accueil_bouton_quitter_mouseover_use.png"));
			img_bouton_hover[17] = ImageIO.read(new File("images/accueil_bouton_gerer_mouseover_use.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}