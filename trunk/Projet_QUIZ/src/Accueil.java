import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Accueil extends JPanel implements MouseListener, MouseMotionListener {
	
	private int hauteur_bouton = 85;		// defini la taille en hauteur des boutons du menu
	private int largeur_bouton = 366;		// defini la taille en largeur
	private int ecart_bouton = 15;			// defini l'ecart entre les boutons
	public int X;							// defini la position X du click
	public int Y;							// defini la position Y du click
	String image_select = "rien";			// defini l'image survolé (rien si pas survolé sinon [nom_image]_hover)
	String bouton_deco ="rien";				// defini l'image survolé bouton deco
	int val_i;								// defini l'emplacement de l'image survolé dans le tableau img_bouton_hover
	static String selection; 				// defini quel bouton est selectionné
	
	private Image[] img_fond = new Image[20];			// regroupe les images de fonds
	private Image[] img_element = new Image[20];		// regroupe les images qui forment les fenetre (barre du haut etc.)
	private Image[] img_bouton = new Image[20];			// regroupe les images des boutons
	private Image[] img_bouton_hover = new Image[20];	// regroupe les images des boutons quand la souris passe dessus
	private Fenetre fenetre;
	/**
	 * Constructeur
	 */
	public Accueil(Fenetre fen) {//Fenetre fen en parametre pour relier à fenetre
		initImage();	// initialisation des images
		fenetre = fen; // on récupère la classe mère
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
			img_bouton[4] = ImageIO.read(new File("images/deco.png"));
			img_bouton[5] = ImageIO.read(new File("images/barre_verticale.png"));
			
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
			img_bouton_hover[5] = ImageIO.read(new File("images/barre_verticale.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Methodes obligatoires de l'interface MouseListener.
	 * Elles sont appelees automatiquement selon l'action de la souris
	 */
	public void mouseClicked(MouseEvent e) { // On récupère X et Y au click
		//fen.gotoStat(); appel d'une future fonction goToStat pour afficher les stat
		//X = e.getX();
		//Y = e.getY();
		//System.out.print("X = "+ X);
		//System.out.print("Y = "+ Y);
		
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 260 && e.getY() <= 345){ // JOUER
			System.out.print("JOUER");
			selection = "jouer";
			fenetre.goToStatistiques(selection); // on appel la fonction qui va changer de panel
		}
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 360 && e.getY() <= 445){ // STATS
			System.out.print("STATS");
			selection = "statistiques";
			fenetre.goToStatistiques(selection); // on appel la fonction qui va changer de panel

			// ajout du JPanel au JFrame (gridLayout)	
		}			
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 460 && e.getY() <= 545){ // CREDITS
			System.out.print("CREDITS");
			selection = "credits";
			fenetre.goToStatistiques(selection); // on appel la fonction qui va changer de panel

		}
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 560 && e.getY() <= 645){ // QUITTER
			System.out.print("QUITTER");
			selection = "quitter";
			fenetre.goToStatistiques(selection); // on appel la fonction qui va changer de panel

		}	
	
		if(e.getX() >= 959 && e.getX() <= 1022 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			System.out.print("CO/DECO");
			selection = "decoreco";
			fenetre.goToStatistiques(selection); // on appel la fonction qui va changer de panel

		}
		
	}
	

	
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	

	
	public void mouseEntered(MouseEvent e) {
		//boutonHover(e.getLocationOnScreen());
	}
	
	public void mouseExited(MouseEvent e) {}
	

	public void mouseMoved(MouseEvent e){ // On récupère constamment la position del a souris
		
			if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 260 && e.getY() <= 345){ // JOUER
				//System.out.print("JOUER_hover");
				image_select = "JOUER_hover";

			}
			if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 360 && e.getY() <= 445){ // STATS
				//System.out.print("STATS_hover");
				image_select = "STATS_hover";
				val_i = 1;
				
			}			
			if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 460 && e.getY() <= 545){ // CREDITS
				//System.out.print("CREDITS_hover");
				image_select = "CREDITS_hover";
				val_i = 2;
			}
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
			
	}
	
	public void mouseDragged(MouseEvent e){}
	
	/**
	 * Methode appelee par mouseEntered(e)
	 * Selon la position de pt, change l'image du bouton en bouton_hover
	 * @param pt
	 */
	public void boutonHover(Point pt) {
		/*
		 * ici il faut un test pour verifier la position de pt sur un bouton et changer l'image.
		 * Inutile du coup ?
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
		
		
		
		//g.drawImage(img_bouton[4], 960, 1, 46, 46, null);
		switch (bouton_deco){
			case "rien" :
				g.drawImage(img_bouton[4], 960, 1, 46, 46, null);
				
				break;				
			case "CO/DECO_hover" :
				g.drawImage(img_bouton_hover[4], 960, 1, 46, 46, null);
				bouton_deco = "rien";
				break;	
		}

		
		for (int i=0; i<4; i++) {
			
			// On dessine l'image sans ruban si le curseur n'est pas dessus.
			switch (image_select){
			
				case "rien" :
					//System.out.print(image_select);
					g.drawImage(img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					break;
				case "JOUER_hover" :
					//System.out.print(image_select);
					g.drawImage(img_bouton_hover[0], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					image_select = "rien";
					break;
				case "STATS_hover" :
					if (val_i == i){
						//System.out.print(image_select);
						g.drawImage(img_bouton_hover[1], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
						image_select = "rien";
					}
					else{
						g.drawImage(img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					}
					break;
				case "CREDITS_hover" :
					if(val_i == i){
						//System.out.print(image_select);
						g.drawImage(img_bouton_hover[2], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
						image_select = "rien";
					}
					else{
						g.drawImage(img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					}
					break;					
				case "QUITTER_hover" :
					if(val_i == i){
						//System.out.print(image_select);
						g.drawImage(img_bouton_hover[3], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
						image_select = "rien";
					}
					else{
						g.drawImage(img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					}
					break;
			}
		}
			
		g.drawImage(img_bouton[5], 140, 113, 8, 655, null);
		
		// exemple de bouton survole par la souris (en dur pour test)
		//g.drawImage(img_bouton_hover[0], 120, 260+(0*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
		
		//g.setColor(new Color(255, 255, 255));
		//g.setFont(new Font("Courier New", Font.BOLD, 36));
		//g.drawString("Yoo!", 400, 600);
	}
}
