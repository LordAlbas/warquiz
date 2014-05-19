import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Header extends JPanel implements MouseListener, MouseMotionListener{
	
	String bouton_deco ="rien";				// defini l'image survol��� bouton deco
	String bouton_option ="rien";
	private Fenetre fenetre;
	static String selection1;
	private Boolean inGame = false;
	private Boolean InCreateQuiz = false;
	public Header(Fenetre fen){
		setLayout(null);
		setOpaque(false);
		fenetre = fen;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setToolTipText("<html>Retour &agrave; l'accueil</html>");
		javax.swing.ToolTipManager.sharedInstance().setInitialDelay(1000);
	}

	public void mouseClicked(MouseEvent e) {
		
		if (inGame){
			JOptionPane.showMessageDialog(null,
				    "<html>Impossible de retourner &agrave; l'accueil en cours de partie !</html>",
				    "Erreur",
				    JOptionPane.WARNING_MESSAGE);
		}
		else if(InCreateQuiz){
			int choix = JOptionPane.showConfirmDialog(
				    null,
				    "<html>Si vous revenez &agrave; l'acceuil sans avoir valid&eacute; le quiz,<br/> les modifications seront d&eacute;finitivement perdues !<br/>Continuer ?</html>",
				    "Attention !",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.WARNING_MESSAGE);
			if(choix ==0){
				fenetre.goToAccueil(selection1);
				InCreateQuiz = false;
			}else{}
		}else{fenetre.goToAccueil(selection1);}
		
		/*
		if(e.getX() >= 959 && e.getX() <= 1022 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			System.out.print("CO/DECO");
			selection1 = "decoreco";
			fenetre.goToConnexionAlerte(selection1); // on appel la fonction qui va changer de panel

		}	*/
	}
	
	
	public void setInCreateQuiz(Boolean bool){
		InCreateQuiz = bool;
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}	
	

	public void mouseDragged(MouseEvent arg0) {	}

	public void mouseMoved(MouseEvent e) {}
	
	public void setInGame(Boolean msg) { inGame = msg; }
	public Boolean getInGame() { return inGame; }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(Images.img_element[Theme.getHeader1()], 0, 0, this.getWidth(), this.getHeight(), this);

    }

}
