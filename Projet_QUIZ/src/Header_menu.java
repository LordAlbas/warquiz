import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;
//import com.jgoodies.forms.factories.DefaultComponentFactory;


public class Header_menu extends JPanel implements MouseListener, MouseMotionListener{

	String bouton_deco ="rien";				// defini l'image survolï¿½ï¿½ï¿½ bouton deco
	String bouton_option ="rien";
	private Fenetre fenetre;
	static String selection1; 
	public JLabel textMessage;
	private Connexion conn;
	private JPanel panel;
	private Boolean inOpt = false;
	private Boolean inGame = false;
	private JPanel PrePanel;
	
	public Header_menu(Fenetre fen, JPanel jp){
		setLayout(null);
		setOpaque(false);
		JLabel textMessage = new JLabel("Bienvenue" + " " + Connexion.getLoginGeneral() +" ["+Connexion.getStatus()+"]");
		panel = jp;
		
		if(!inOpt){
			PrePanel = panel;
		}

		
		textMessage.setForeground(Color.WHITE);
		textMessage.setFont(new Font("Arial", Font.PLAIN, 18));
		
		
		textMessage.setBounds(55, 15, 250, 18);
		add(textMessage);
		fenetre = fen;
		repaint();
	}
	
	public void setInOption(Boolean bool){
		inOpt = bool;
	}
	
	public void setInGame(Boolean bool){
		inGame = bool;
	}

	
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(e.getX() >= 450 && e.getX() <= 514 && e.getY() >= 1 && e.getY() <= 47){ // OPT
			bouton_option = "OPTION_hover";	
			if(inOpt){
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		}
		
		
		else if(e.getX() >= 515 && e.getX() <= 578 && e.getY() >= 1 && e.getY() <= 47){ // DECO
			if(inOpt){
				bouton_option = "OPTION_hover";
			}
			bouton_deco = "DECO_hover";
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		else{
			
			if(inOpt){
				bouton_option = "OPTION_hover";
				bouton_deco = "rien";
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else{
				bouton_option = "rien";
				bouton_deco = "rien";
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			bouton_deco = "rien";
			//setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= 450 && e.getX() <= 514 && e.getY() >= 1 && e.getY() <= 47){ // OPT
			if(inOpt){
				fenetre.goToBack(PrePanel);
			}
			else{
				fenetre.goToOption(panel);
			}
		}
		if(e.getX() >= 526 && e.getX() <= 578 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			if (inGame){
				JOptionPane.showMessageDialog(null,
					    "<html>Impossible de vous déconnecter en cours de partie !<br/>Utilisez le bouton de retour puis déconnectez vous.</html>",
					    "Erreur",
					    JOptionPane.WARNING_MESSAGE);
			}else{
				selection1 = "decoreco";
				inGame = false;
				fenetre.goToConnexionAlerte(selection1); // on appel la fonction qui va changer de panel				
			}

		}
		
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Images.img_element[Theme.getHeader2()], 0, 0, this.getWidth(), this.getHeight(), this);
        	
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