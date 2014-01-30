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
	private int hauteur_bouton = 85;		// defini la taille en hauteur des boutons du menu
	private int largeur_bouton = 366;		// defini la taille en largeur
	private int ecart_bouton = 15;			// defini l'ecart entre les boutons
	public Connexion(Fenetre fen) {
		fenetre = fen;
	}
	/**
	 * Implement les mehtodes pour MouseListener et MouseMotionListener.
	 */
	public void mouseClicked(MouseEvent e) {}
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
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(hauteur_bouton+ecart_bouton)), largeur_bouton, hauteur_bouton, null);
		}
		
		g.drawImage(Images.img_fond[1], 0, 0, this.getWidth(), this.getHeight(), null);	
		
		
	}
	
}
