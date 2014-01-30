import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Class de connexion, s'affiche en tout debut de programme en pop-up style.
 * Invite l'utilisateur a se connecter avant toute action.
 * @author Merovee
 */
public class Connexion extends JPanel implements MouseListener, MouseMotionListener {
	
	private Fenetre fenetre;
	static String selection; 				// defini quel bouton est selectionn�
	
	/**
	 * Constructor
	 * @param fen
	 */
	public Connexion(Fenetre fen) {
		fenetre = fen;
	}
	/**
	 * Implement les mehtodes pour MouseListener et MouseMotionListener.
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= 400 && e.getX() <= 585 && e.getY() >= 462 && e.getY() <= 510){ // JOUER
			System.out.print("INSCRIPTION");
			fenetre.goToInscription(selection); // on appel la fonction qui va changer de panel
		}
		if(e.getX() >= 605 && e.getX() <= 790 && e.getY() >= 465 && e.getY() <= 513){ // Bouton de validation
			System.out.print("CONNEXION");
			fenetre.goToAccueil(selection); // on appel la fonction qui va changer de panel
		}		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
	/**
	 * PAINT
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
		
		/*
		 * On boucle sur tous les boutons (de [0] a [3]).
		 * Chaque bouton obtient une position X (hauteur) en fonction de son numero (i).
		 * Les positions en durs (ici 120 et 260) correspondent au coin haut-gauche du bloc de bouton.
		 */
		for (int i=0; i<4; i++) {
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
		}
		
		g.drawImage(Images.img_fond[1], 0, 0, this.getWidth(), this.getHeight(), null);	
		
		
	}
	
}
