import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class de connexion, s'affiche en tout debut de programme en pop-up style.
 * Invite l'utilisateur a se connecter avant toute action.
 * @author Merovee
 */
public class Connexion extends JPanel implements MouseListener, MouseMotionListener, FocusListener{
	
	private Fenetre fenetre;
	static String selection; 				// defini quel bouton est selectionn�
	private JTextField textField_pseudo;	// création du champ pseudo
	private JPasswordField textField_mdp;	// création du cham mdp cypté
	public int bad_pseudo=0; // le champ n'est pas encore invalide
	public int bad_mdp=0; // le champ n'est pas encore invalide
	/**
	 * Constructor
	 * @param fen
	 * @throws IOException 
	 */
	public Connexion(Fenetre fen) {	
		fenetre = fen;
		setLayout(null); // on met le layout en absolute pour mettre les JTextbox où on veut
		this.setOpaque(false);
		

		
		// Création du textBox pseudo et placement + suppression du style
		textField_pseudo = new JTextField();
		textField_pseudo.addFocusListener(this); 
		textField_pseudo.addMouseListener(this);
		textField_pseudo.setBounds(398, 328, 366, 36);
		add(textField_pseudo);
		textField_pseudo.setBorder(null);
		textField_pseudo.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_pseudo.setOpaque(false);
		// Création du textBox pseudo et placement + suppression du style
		textField_mdp = new JPasswordField();
		textField_mdp.setBounds(398, 392, 366, 36);
		textField_mdp.addMouseListener(this);
		add(textField_mdp);
		textField_mdp.setBorder(null);
		textField_mdp.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mdp.setOpaque(false);
		//textField_pseudo.setFocusTraversalKeysEnabled(false);
		//repaint();
		this.pushKeyboard();
	
	
	} 
	/**
	 * Défini les actions du bouton ENTREE
	 */
	public void pushKeyboard(){ // On défini les action des touches  ENTER	
		textField_pseudo.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				if(evt.getKeyCode() == evt.VK_ENTER ){
					if(textField_pseudo.getText().length() != 0){							
						System.out.println(" ENTER PSEUDO ");						
						//fenetre.goToAccueil(selection);	
						if(textField_mdp.getText().length() == 0){
							System.out.println(" ENTER PSEUDO ERROR ");
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							repaint();
						}
					}else{
						System.out.println(" ENTER PSEUDO ERROR ");
						textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_pseudo=1;
						repaint();
						if(textField_mdp.getText().length() == 0){
							System.out.println(" ENTER PSEUDO ERROR ");
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							repaint();
						}
					}
				}
			}
		});	
		textField_mdp.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				if(evt.getKeyCode() == evt.VK_ENTER ){
					if(textField_mdp.getText().length() != 0){
						System.out.println(" ENTER PSEUDO ");
						//fenetre.goToAccueil(selection);	
						if(textField_pseudo.getText().length() == 0){
							System.out.println(" ENTER PSEUDO ERROR ");
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							repaint();
						}
					}else{
						System.out.println(" ENTER PSEUDO ERROR ");
						textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_mdp=1;
						repaint();
						//textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 36, 1, 0, icon));
						if(textField_pseudo.getText().length() == 0){
							System.out.println(" ENTER PSEUDO ERROR ");
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							repaint();
						}
					}
				}
			}
		});			
	}


	
	/**
	 * Implement les mehtodes pour MouseListener et MouseMotionListener.
	 */
	public void mouseClicked(MouseEvent e) {
		if(textField_pseudo.isFocusOwner() == true){ // Si le champ pseudo est selectionné on réinitialise le style
			bad_pseudo=0;
			textField_pseudo.setBorder(null);
			repaint();
		}
		if(textField_mdp.isFocusOwner() == true){ // Si le champ mdp est selectionné on réinitialise le style
			bad_mdp=0;
			textField_mdp.setBorder(null);
			repaint();
		}
		if(e.getX() >= 400 && e.getX() <= 585 && e.getY() >= 462 && e.getY() <= 510){ // Bouton d'inscription
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
	public void mouseMoved(MouseEvent e) {

		if(e.getX() >= 400 && e.getX() <= 585 && e.getY() >= 462 && e.getY() <= 510){ // changement de curseur sur le bouton inscription
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		}
		else if(e.getX() >= 605 && e.getX() <= 790 && e.getY() >= 465 && e.getY() <= 513){ // changement de curseur sur le bouton valider
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}	
		else{
			setCursor(Cursor.getDefaultCursor());
		}
	}
	public void focusGained(FocusEvent e) {}		
	public void focusLost(FocusEvent arg0) {}
	
	
	
	
	/**
	 * PAINT
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		//g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
		//g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		/*
		 * On boucle sur tous les boutons (de [0] a [3]).
		 * Chaque bouton obtient une position X (hauteur) en fonction de son numero (i).
		 * Les positions en durs (ici 120 et 260) correspondent au coin haut-gauche du bloc de bouton.
		 */
		//for (int i=0; i<4; i++) {
		//	g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
		//	g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
		//	g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
		//	g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
		//}
		//g.drawImage(Images.img_element[1], 140, 113, 8, 655, null);
		g.drawImage(Images.img_fond[1], 0, 0, this.getWidth(), this.getHeight(), null);	
		
		// On affiche ou non la banderole info
		if(bad_pseudo==1){
			g.drawImage(Images.img_element[3], 768, 326, 170, 46, null);	
		}
		if(bad_mdp==1){
			g.drawImage(Images.img_element[3], 768, 390, 170, 46, null);	
		}

		
	}

}
