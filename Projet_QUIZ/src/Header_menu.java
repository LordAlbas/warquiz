import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;


public class Header_menu extends JPanel implements MouseListener, MouseMotionListener{
	
	String bouton_deco ="rien";				// defini l'image survol��� bouton deco
	String bouton_option ="rien";
	private Fenetre fenetre;
	static String selection1; 
	public JLabel textMessage;
	private Connexion conn;

	
	public Header_menu(Fenetre fen){
		setLayout(null);
		setOpaque(false);
		JLabel textMessage = new JLabel("Bienvenue" + " " );
		
		textMessage.setForeground(Color.WHITE);
		textMessage.setFont(new Font("Arial", Font.PLAIN, 18));
		
		
		textMessage.setBounds(55, 15, 200, 18);
		add(textMessage);
		fenetre = fen;
		repaint();
	}
	

	
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		
		if(e.getX() >= 450 && e.getX() <= 514 && e.getY() >= 1 && e.getY() <= 47){ // OPT
			bouton_option = "OPTION_hover";
		}else{bouton_option = "rien";}		
		
		if(e.getX() >= 515 && e.getX() <= 578 && e.getY() >= 1 && e.getY() <= 47){ // DECO
			bouton_deco = "DECO_hover";
		}else{bouton_deco = "rien";}
		repaint();
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= 526 && e.getX() <= 578 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			selection1 = "decoreco";

				fenetre.goToConnexionAlerte(selection1); // on appel la fonction qui va changer de panel


		}	
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Images.img_element[7], 0, 0, this.getWidth(), this.getHeight(), this);
        	
		switch (bouton_option){
		case "rien" :
			g.drawImage(Images.img_bouton[6], 457, 0, 46, 46, null);
			break;				
		case "OPTION_hover" :
			g.drawImage(Images.img_bouton_hover[6], 457, 0, 46, 46, null);
			bouton_option = "rien";
			break;		
		}
		
		switch (bouton_deco){
		case "rien" :
			g.drawImage(Images.img_bouton[4], 523, 0, 46, 46, null);
			break;				
		case "DECO_hover" :
			g.drawImage(Images.img_bouton_hover[4], 523, 0, 46, 46, null);
			bouton_deco = "rien";
			break;	
		}		
    }
}