import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.JPanel;

public class Credits extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	
	private Fenetre fenetre;
	private String image_select = "rien";			// defini l'image survol� (rien si pas survol� sinon [nom_image]_hover)
	private String bouton_deco ="rien";				// defini l'image survol� bouton deco
	private String bouton_retour ="rien";
	private int val_i;								// defini l'emplacement de l'image survol� dans le tableau img_bouton_hover
	private static String selection; 				// defini quel bouton est selectionn�
	private Header header1;
	private Header_menu header2;
	/**
	 * Constructor
	 */
	public Credits(Fenetre fen) {
		
		fenetre = fen;  // on r�cup�re la classe m�re
		setLayout(null);
		//****Inclusion du Header en 2 parties ****
        header1 = new Header(fen);
        header1.setBounds(0, 0, 444, 130);
        header1.addMouseListener(header1);
        header1.addMouseMotionListener(header1);
        this.add(header1); 

        header2 = new Header_menu(fen);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
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
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}