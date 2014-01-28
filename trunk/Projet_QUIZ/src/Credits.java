import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Credits extends JPanel implements MouseListener, MouseMotionListener{

	private int hauteur_bouton = 85;		// defini la taille en hauteur des boutons du menu
	private int largeur_bouton = 366;		// defini la taille en largeur
	private Image[] img_fond = new Image[20];			// regroupe les images de fonds
	private Image[] img_element = new Image[20];		// regroupe les images qui forment les fenetre (barre du haut etc.)
	private int ecart_bouton = 15;			// defini l'ecart entre les boutons	
	private Image[] img_bouton = new Image[20];			// regroupe les images des boutons
	private Image[] img_bouton_hover = new Image[20];	// regroupe les images des boutons quand la souris passe dessus
	private Fenetre fenetre;
	String image_select = "rien";			// defini l'image survolé (rien si pas survolé sinon [nom_image]_hover)
	String bouton_deco ="rien";				// defini l'image survolé bouton deco
	String bouton_retour ="rien";
	int val_i;								// defini l'emplacement de l'image survolé dans le tableau img_bouton_hover
	static String selection; 				// defini quel bouton est selectionné
	
	/**
	 * Constructeur
	 */
	public Credits(Fenetre fen) {
		initImage();	// initialisation des images
		fenetre = fen;  // on récupère la classe mère
	}	
	
	/**
	 * Initialise les banques d'images a partir des fichiers .png
	 */
	public void initImage() {
		/*
		 * charge les images de fonds dans img_fond[]
		 */
		try {
			img_fond[0] = ImageIO.read(new File("images/accueil_fond.png"));	// Lecture du fichier .png
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * charge les images d'elements dans img_element[]
		 */
		try {
			img_element[0] = ImageIO.read(new File("images/header.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			img_bouton[0] = ImageIO.read(new File("images/accueil_bouton_jouer.png"));
			img_bouton[1] = ImageIO.read(new File("images/accueil_bouton_statistiques.png"));
			img_bouton[2] = ImageIO.read(new File("images/accueil_bouton_credit.png"));
			img_bouton[3] = ImageIO.read(new File("images/accueil_bouton_quitter.png"));
			img_bouton[4] = ImageIO.read(new File("images/deco.png"));
			img_bouton[5] = ImageIO.read(new File("images/retour.png"));
			
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
			img_bouton_hover[4] = ImageIO.read(new File("images/deco_hover.png"));
			img_bouton_hover[5] = ImageIO.read(new File("images/retour_hover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
			g.drawImage(img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran
			g.drawImage(img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
			
			switch (bouton_deco){
			case "rien" :
				g.drawImage(img_bouton[4], 960, 1, 46, 46, null);
				
				break;				
			case "CO/DECO_hover" :
				g.drawImage(img_bouton_hover[4], 960, 1, 46, 46, null);
				bouton_deco = "rien";
				break;	
			}		
				
				
			switch (bouton_retour)	{
			case "rien" :
				g.drawImage(img_bouton[5], 1, 685, 84, 83, null);
				break;				
			case "retour_hover" :
				g.drawImage(img_bouton_hover[5], 1, 685, 84, 83, null);
				bouton_retour = "rien";
				break;	
			
			}
		
	}	
	
	public void mouseClicked(MouseEvent e) {
		// SELECT ZONE BOUTON_RETOUR
		if(e.getX() >= 1 && e.getX() <= 85 && e.getY() >= 685 && e.getY() <= 768){ // STATS
			selection = "accueil";
			fenetre.goToAccueil(selection); // on appel la fonction qui va changer de panel
		}
		// SELECT ZONE WARQUIZ
		if(e.getX() >= 1 && e.getX() <= 400 && e.getY() >= 1 && e.getY() <= 130){ // STATS
			selection = "accueil";
			fenetre.goToAccueil(selection); // on appel la fonction qui va changer de panel
		}		
		
		
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 560 && e.getY() <= 645){ // QUITTER
			//System.out.print("QUITTER_hover");
			image_select = "QUITTER_hover";
			val_i = 3;
		}	
		repaint(); // On re dessine
	
		if(e.getX() >= 959 && e.getX() <= 1022 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			//System.out.print("CO/DECO_hover");
			bouton_deco = "CO/DECO_hover";
			//val_i = 4;
			repaint(); // On re dessine
		}	
		if(e.getX() >= 1 && e.getX() <= 85 && e.getY() >= 685 && e.getY() <= 768){ // retour
			bouton_retour = "retour_hover";
			//val_i = 4;
			repaint(); // On re dessine
		}			
	}
}
