import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;


public class Accueil extends JPanel implements MouseListener, MouseMotionListener {
	
	private int hauteur_bouton = 85;		// defini la taille en hauteur des boutons du menu
	private int largeur_bouton = 366;		// defini la taille en largeur
	private int ecart_bouton = 15;			// defini l'ecart entre les boutons
	public int X;							// defini la position X du click
	public int Y;							// defini la position Y du click
	String image_select = "rien";			// defini l'image survol� (rien si pas survol� sinon [nom_image]_hover)
	String bouton_deco ="rien";				// defini l'image survol� bouton deco
	int val_i;								// defini l'emplacement de l'image survol� dans le tableau img_bouton_hover
	static String selection; 				// defini quel bouton est selectionn�
	
	private Fenetre fenetre;
	
	/**
	 * Constructeur
	 */
	public Accueil(Fenetre fen) {//Fenetre fen en parametre pour relier � fenetre
		fenetre = fen; // on r�cup�re la classe m�re
	}
	
	/**
	 * Methodes obligatoires de l'interface MouseListener.
	 * Elles sont appelees automatiquement selon l'action de la souris
	 */
	public void mouseClicked(MouseEvent e) { // On r�cup�re X et Y au click
		//X = e.getX();
		//Y = e.getY();
		//System.out.print("X = "+ X);
		//System.out.print("Y = "+ Y);
		
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 260 && e.getY() <= 345){ // JOUER
			System.out.print("JOUER");
			selection = "jouer";
			fenetre.goToJouer(selection); // on appel la fonction qui va changer de panel
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
			fenetre.goToCredits(selection); // on appel la fonction qui va changer de panel

		}
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 560 && e.getY() <= 645){ // QUITTER
			System.out.print("QUITTER");
			selection = "quitter";
			fenetre.goToQuitter(selection); // on appel la fonction qui va changer de panel

		}	
	
		if(e.getX() >= 959 && e.getX() <= 1022 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			System.out.print("CO/DECO");
			selection = "decoreco";
			fenetre.goToConnexion(selection); // on appel la fonction qui va changer de panel

		}
		
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e){}
	
	public void mouseMoved(MouseEvent e){ // On r�cup�re constamment la position del a souris
		
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
				repaint(); // On re dessine
			}
			
	}
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		
		//g.drawImage(img_bouton[4], 960, 1, 46, 46, null);
		switch (bouton_deco){
		case "rien" :
			g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
			break;				
		case "CO/DECO_hover" :
			g.drawImage(Images.img_bouton_hover[4], 960, 1, 46, 46, null);
			bouton_deco = "rien";
			break;	
		}
		
		/*
		 * On boucle sur tous les boutons (de [0] a [3]).
		 * Chaque bouton obtient une position X (hauteur) en fonction de son numero (i).
		 * Les positions en durs (ici 120 et 260) correspondent au coin haut-gauche du bloc de bouton.
		 */
		for (int i=0; i<4; i++) {
			// On dessine l'image sans ruban si le curseur n'est pas dessus.
			switch (image_select){
			case "rien" :
				//System.out.print(image_select);
				g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
				break;
			case "JOUER_hover" :
				//System.out.print(image_select);
				g.drawImage(Images.img_bouton_hover[0], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
				image_select = "rien";
				break;
			case "STATS_hover" :
				if (val_i == i){
					//System.out.print(image_select);
					g.drawImage(Images.img_bouton_hover[1], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					image_select = "rien";
				}
				else{
					g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
				}
				break;
			case "CREDITS_hover" :
				if(val_i == i){
					//System.out.print(image_select);
					g.drawImage(Images.img_bouton_hover[2], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					image_select = "rien";
				}
				else{
					g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
				}
				break;					
			case "QUITTER_hover" :
				if(val_i == i){
					//System.out.print(image_select);
					g.drawImage(Images.img_bouton_hover[3], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
					image_select = "rien";
				}
				else{
					g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
				}
				break;
			}
		}
		
		g.drawImage(Images.img_element[1], 140, 113, 8, 655, null);
		
		//g.setColor(new Color(255, 255, 255));
		//g.setFont(new Font("Courier New", Font.BOLD, 36));
		//g.drawString("Yoo!", 400, 600);
	}
}
