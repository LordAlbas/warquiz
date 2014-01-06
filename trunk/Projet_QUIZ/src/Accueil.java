import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Accueil extends JPanel implements MouseListener {
	
	private int hauteur_bouton = 85;		// defini la taille en hauteur des boutons du menu
	private int largeur_bouton = 366;		// defini la taille en largeur
	private int ecart_bouton = 15;			// defini l'ecart entre les boutons
	
	private Image[] img_fond = new Image[20];			// regroupe les images de fonds
	private Image[] img_element = new Image[20];		// regroupe les images qui forment les fenetre (barre du haut etc.)
	private Image[] img_bouton = new Image[20];			// regroupe les images des boutons
	private Image[] img_bouton_hover = new Image[20];	// regroupe les images des boutons quand la souris passe dessus
	
	/**
	 * Constructeur
	 */
	public Accueil() {//Fenetre fen en parametre pour relier à fenetre
		initImage();	// initialisation des images
		
	}
	
	/**
	 * Initialise les banques d'images a partir des fichier .png
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
		
		/*
		 * charge les images de boutons dans img_bouton[]
		 */
		try {
			img_bouton[0] = ImageIO.read(new File("images/accueil_bouton_jouer.png"));
			img_bouton[1] = ImageIO.read(new File("images/accueil_bouton_statistiques.png"));
			img_bouton[2] = ImageIO.read(new File("images/accueil_bouton_credit.png"));
			img_bouton[3] = ImageIO.read(new File("images/accueil_bouton_quitter.png"));
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methodes obligatoires de l'interface MouseListener.
	 * Elles sont appelees automatiquement selon l'action de la souris
	 */
	public void mouseClicked(MouseEvent e) {
		//fen.gotoStat(); appel d'une future fonction goToStat pour afficher les stat
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {
		boutonHover(e.getLocationOnScreen());
	}
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * Methode appelee par mouseEntered(e)
	 * Selon la position de pt, change l'image du bouton en bouton_hover
	 * @param pt
	 */
	public void boutonHover(Point pt) {
		/*
		 * ici il faut un test pour verifier la position de pt sur un bouton et changer l'image.
		 */
		
	}
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran
		g.drawImage(img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		
		/*
		 * On boucle sur tous les boutons (de [0] a [3]).
		 * Chaque bouton obtient une position X (hauteur) en fonction de son numero (i).
		 * Les positions en durs (ici 120 et 250) correspondent au coin haut-gauche du bloc de bouton.
		 */
		for (int i=0; i<4; i++) {
			g.drawImage(img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
		}
		
		// exemple de bouton survole par la souris (en dur pour test)
		g.drawImage(img_bouton_hover[0], 120, 260+(0*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
		
		//g.setColor(new Color(255, 255, 255));
		//g.setFont(new Font("Courier New", Font.BOLD, 36));
		//g.drawString("Yoo!", 400, 600);
	}
}
