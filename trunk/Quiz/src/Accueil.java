import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Accueil extends JPanel {
	
	private Image[] img_fond = new Image[20];
	private Image[] img_element = new Image[20];
	
	/**
	 * Constructeur
	 */
	public Accueil() {
		initImage();		// initialisation des images
		
	}
	
	/**
	 * Initialise la banque d'images a partir des fichier .png
	 */
	public void initImage() {
		try {
			/*
			 * Ici on fera une boucle FOR si possible pour charger tout les img_fond
			 */
			img_fond[0] = ImageIO.read(new File("images/accueil_fond.png"));	// Lecture du fichier .png
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			/*
			 * Pareil ici pour tout les calques d'elements (tabs, connexion, etc.)
			 */
			img_element[0] = ImageIO.read(new File("images/header.png"));	// Lecture du fichier .png
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
			super.paintComponents(g);
			setBackground(new Color(250,150,50));			// couleur de background random pour test (r,g,b)
			//g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.drawImage(img_fond[0], 0, 0, null);											// dessine le fond d'ecran
			g.drawImage(img_element[0], 0, 0, 1024, img_element[0].getHeight(null), null);	// dessine le header
			
			//g.setColor(new Color(255, 255, 255));
			//g.setFont(new Font("Courier New", Font.BOLD, 36));
			//g.drawString("Yoo!", 400, 600);
	}
}
