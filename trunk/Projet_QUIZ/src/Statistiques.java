import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Statistiques extends JPanel implements MouseListener{


	
	private Image[] img_fond = new Image[20];			// regroupe les images de fonds
	private Image[] img_element = new Image[20];		// regroupe les images qui forment les fenetre (barre du haut etc.)
	
	/**
	 * Constructeur
	 */
	public Statistiques() {
		initImage();	// initialisation des images
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
	}	
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran
		g.drawImage(img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		
	}	
	
	
	
	
	
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
