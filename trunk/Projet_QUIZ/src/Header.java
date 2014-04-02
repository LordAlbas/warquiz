import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;


public class Header extends JPanel implements MouseListener, MouseMotionListener{
	
	String bouton_deco ="rien";				// defini l'image survol� bouton deco
	String bouton_option ="rien";
	private Fenetre fenetre;
	static String selection1; 

	public Header(Fenetre fen){
		setLayout(null);
		setOpaque(false);
		fenetre = fen;
	}

	public void mouseClicked(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fenetre.goToAccueil(selection1);
		
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

	public void mouseMoved(MouseEvent e) {
		
		if(e.getX() >= 905 && e.getX() <= 969 && e.getY() >= 1 && e.getY() <= 47){ // OPT
			//System.out.print("OPT_hover");
			bouton_option = "OPTION_hover";
		}else{bouton_option = "rien";}		
		
		if(e.getX() >= 970 && e.getX() <= 1022 && e.getY() >= 1 && e.getY() <= 47){ // DECO
			//System.out.print("DECO_hover");
			bouton_deco = "DECO_hover";
		}else{bouton_deco = "rien";}
		repaint();
	}


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(Images.img_element[9], 0, 0, this.getWidth(), this.getHeight(), this);
        /**
		switch (bouton_deco){
		case "rien" :
			g.drawImage(Images.img_bouton[4], 970, 0, 46, 46, null);
			break;				
		case "DECO_hover" :
			g.drawImage(Images.img_bouton_hover[4], 970, 0, 46, 46, null);
			bouton_deco = "rien";
			break;	
		}
		
		switch (bouton_option){
		case "rien" :
			g.drawImage(Images.img_bouton[6], 905, 0, 46, 46, null);
			break;				
		case "OPTION_hover" :
			g.drawImage(Images.img_bouton_hover[6], 905, 0, 46, 46, null);
			bouton_option = "rien";
			break;	
		}	*/
    }

}
