import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Header extends JPanel implements MouseListener, MouseMotionListener{
	
	String bouton_deco ="rien";				// defini l'image survolï¿½ï¿½ï¿½ bouton deco
	String bouton_option ="rien";
	private Fenetre fenetre;
	static String selection1;
	private Boolean warning = true;
	
	public Header(Fenetre fen){
		setLayout(null);
		setOpaque(false);
		fenetre = fen;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void mouseClicked(MouseEvent e) {
		
		
		
		if (warning){
			fenetre.goToAccueil(selection1);
		} else {
			JOptionPane.showMessageDialog(null,
				    "Impossible de retourner à l'accueil en cours de partie !",
				    "Erreur",
				    JOptionPane.WARNING_MESSAGE);
		}
		
		/*
		if(e.getX() >= 959 && e.getX() <= 1022 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			System.out.print("CO/DECO");
			selection1 = "decoreco";
			fenetre.goToConnexionAlerte(selection1); // on appel la fonction qui va changer de panel

		}	*/
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}	
	

	public void mouseDragged(MouseEvent arg0) {	}

	public void mouseMoved(MouseEvent e) {}
	
	public void setWarning(Boolean msg) { warning = msg; }
	public Boolean getWarning() { return warning; }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(Images.img_element[9], 0, 0, this.getWidth(), this.getHeight(), this);

    }

}
