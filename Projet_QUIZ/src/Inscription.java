import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Inscription extends JPanel implements MouseListener, MouseMotionListener{

	private Fenetre fenetre;
	static String selection; 				// defini quel bouton est selectionn�
	private JTextField textField_pseudo;	// création du champ pseudo
	private JPasswordField textField_mdp;	// création du cham mdp cypté
	private JPasswordField textField_mdp_conf;	// création du champ mdp confirmation
	private JTextField textField_mail; // création du champ mail


	
	public Inscription(Fenetre fen){
		fenetre = fen; // On récupère la classe Fenetre
		setLayout(null); // on met le layout en absolute pour mettre les JTextbox où on veut
	}
	
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(e.getX() >= 400 && e.getX() <= 585 && e.getY() >= 462 && e.getY() <= 510){ // changement de curseur sur le bouton retour
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		}
		else if(e.getX() >= 605 && e.getX() <= 790 && e.getY() >= 465 && e.getY() <= 513){ // changement de curseur sur le bouton valider
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}	
		else{
			setCursor(Cursor.getDefaultCursor());
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= 400 && e.getX() <= 585 && e.getY() >= 462 && e.getY() <= 510){ // Bouton de retour
			System.out.print("CONNEXION");
			fenetre.goToConnexion(selection); // on appel la fonction qui va changer de panel
		}		
		if(e.getX() >= 605 && e.getX() <= 790 && e.getY() >= 465 && e.getY() <= 513){ // Bouton de validation
			System.out.print("CONNEXION");
			fenetre.goToAccueil(selection); // on appel la fonction qui va changer de panel
		}			
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		/*
		 * On boucle sur tous les boutons (de [0] a [3]).
		 * Chaque bouton obtient une position X (hauteur) en fonction de son numero (i).
		 * Les positions en durs (ici 120 et 260) correspondent au coin haut-gauche du bloc de bouton.
		 */
		for (int i=0; i<4; i++) {
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
		}
		g.drawImage(Images.img_element[1], 140, 113, 8, 655, null);
		g.drawImage(Images.img_fond[2], 0, 0, this.getWidth(), this.getHeight(), null);	
		
		
		// Création du textBox PEUDO et placement + suppression du style
		textField_pseudo = new JTextField();
		textField_pseudo.setBounds(398, 288, 366, 36);
		add(textField_pseudo);
		textField_pseudo.setBorder(null);
		textField_pseudo.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_pseudo.setOpaque(false);
		
		// Création du textBox MDP et placement + suppression du style
		textField_mdp = new JPasswordField();
		textField_mdp.setBounds(398, 328, 366, 36);
		add(textField_mdp);
		textField_mdp.setBorder(null);
		textField_mdp.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mdp.setOpaque(false);	
		
		// Création du textBox MDP_CONF et placement + suppression du style
		textField_mdp_conf = new JPasswordField();
		textField_mdp_conf.setBounds(398, 368, 366, 36);
		add(textField_mdp_conf);
		textField_mdp_conf.setBorder(null);
		textField_mdp_conf.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mdp_conf.setOpaque(false);
		
		// Création du textBox MAIL et placement + suppression du style
		textField_mail = new JTextField();
		textField_mail.setBounds(398, 408, 366, 36);
		add(textField_mail);
		textField_mail.setBorder(null);
		textField_mail.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mail.setOpaque(false);
		
		repaint();		
	}
	

}
