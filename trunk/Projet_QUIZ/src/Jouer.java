import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;


public class Jouer extends JPanel implements MouseListener, MouseMotionListener{

	private Fenetre fenetre;
	
	/**
	 * Constructeur
	 */
	public Jouer(Fenetre fen) {
		fenetre = fen;  // on r�cup�re la classe m�re
	}
	
	/**
	 * Methodes override du MouseListener
	 */
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		
	}
	
}
