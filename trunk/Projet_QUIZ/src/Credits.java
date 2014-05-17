import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JComponent;
import javax.swing.JLabel;
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
	private JLabel lb_titreCredits; // Titre de la partie
	private JLabel credit1;
	private JLabel credit2;
	private JLabel credit3;
	private JLabel credit4;
	private JLabel credit5;
	private JLabel credit6;
	private JLabel credit7;
	private JLabel credit8;

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

        header2 = new Header_menu(fen, this);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
        
        credit1 = new JLabel("Chef de Projet & développeur :");
        credit1.setForeground(Images.couleurLabel);
        credit1.setFont(new Font("Arial", Font.PLAIN, 30));
        credit1.setBounds(60, 250, 500, 50);
		add(credit1);
		
        credit2 = new JLabel("CHARRUE Mérovée");
        credit2.setForeground(Color.WHITE);
        credit2.setFont(new Font("Arial", Font.BOLD, 25));
        credit2.setBounds(130, 290, 500, 50);
		add(credit2);
		
		credit6 = new JLabel("Développeur & Admin BDD :");
		credit6.setForeground(Images.couleurLabel);
		credit6.setFont(new Font("Arial", Font.PLAIN, 30));
		credit6.setBounds(60, 350, 500, 50);
		add(credit6);
		
        credit3 = new JLabel("CURTET Quentin");
        credit3.setForeground(Color.WHITE);
        credit3.setFont(new Font("Arial", Font.BOLD, 25));
        credit3.setBounds(130, 390, 500, 50);
		add(credit3);
		
		credit7 = new JLabel("Développeur & Lead Designer :");
		credit7.setForeground(Images.couleurLabel);
		credit7.setFont(new Font("Arial", Font.PLAIN, 30));
		credit7.setBounds(60, 450, 500, 50);
		add(credit7);
		
        credit4 = new JLabel("SINARDET Mickaël");
        credit4.setForeground(Color.WHITE);
        credit4.setFont(new Font("Arial", Font.BOLD, 25));
        credit4.setBounds(130, 490, 500, 50);
		add(credit4);
		
		credit8 = new JLabel("Rédaction quiz :");
		credit8.setForeground(Images.couleurLabel);
		credit8.setFont(new Font("Arial", Font.PLAIN, 30));
		credit8.setBounds(60, 550, 500, 50);
		add(credit8);
		
		credit5 = new JLabel("HASSAN HAMAD Fatouma");
		credit5.setForeground(Color.WHITE);
		credit5.setFont(new Font("Arial", Font.BOLD, 25));
		credit5.setBounds(130, 590, 500, 50);
		add(credit5);
		
		lb_titreCredits = new JLabel("Crédits");
		lb_titreCredits.setForeground(Color.WHITE);
		lb_titreCredits.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreCredits.setBounds(575, 105, 400, 50);
		add(lb_titreCredits);
		
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