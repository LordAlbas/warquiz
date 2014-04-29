import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

/**
 * Class de connexion, s'affiche en tout debut de programme en pop-up style.
 * Invite l'utilisateur a se connecter avant toute action.
 * @author Merovee
 */

public class Connexion extends JPanel implements MouseListener, MouseMotionListener, FocusListener{
	
	public static Fenetre fenetre;
    public JLayeredPane layeredPane;
    public Gif_anime panel;
	
	public static JTextField textField_pseudo;	// création du champ pseudo
	public static JPasswordField textField_mdp;	// création du cham mdp cypté
	
	public int bad_pseudo=0; // le champ n'est pas encore invalide
	public int bad_mdp=0; // le champ n'est pas encore invalide
	public int tentative = 0; //le nombre de tentative d'authentification
	
	static String selection; 	// defini quel bouton est selectionne
	public static String dbUsername_admin, dbPassword_admin; // Variables permettant de comparer le champ de login/mdp avec les champs de la bdd de la table ADMIN
	public static String dbUsername_user, dbPassword_user; // Variables permettant de comparer le champ de login/mdp avec les champs de la bdd de la table UTILISATEUR
	public static String login_general;
	
	public static boolean login = false;
    public static boolean erreur_log = false;
    public static boolean recherche_bdd = false;
    public static boolean erreur_bdd = false;
    public static boolean connexion_admin = false; 
    
    
    
    
    public static String login_admin; //variable prenant en compte les donnees de la colonne login_admin dans la BDD
	public static String mdp_admin; //variable prenant en compte les donnees de la colonne mdp_admin dans la BDD
	public static String login_usr; //variable prenant en compte les donnees de la colonne login_usr dans la BDD
	public static String mdp_usr; //variable prenant en compte les donnees de la colonne mdp_usr dans la BDD
	public static String query_admin; //variable dans lesquelles sont placées les requetes
	public static String query_user; //variable dans lesquelles sont placées les requetes
	public static String status;
    
    
	/**
	 * Constructor
	 * @param fen
	 * @throws IOException 
	 */
	public Connexion(Fenetre fen) {	
		fenetre = fen;
		setLayout(null); // on met le layout en absolute pour mettre les JTextbox où on veut

		

		// Création du textBox pseudo et placement + suppression du style
		textField_pseudo = new JTextField();
		textField_pseudo.addFocusListener(this); 
		textField_pseudo.addMouseListener(this);
		textField_pseudo.setBounds(398, 328, 366, 36);
		add(textField_pseudo);
		textField_pseudo.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_pseudo.setOpaque(false);
		
		// Création du textBox pseudo et placement + suppression du style
		textField_mdp = new JPasswordField();
		textField_mdp.setBounds(398, 392, 366, 36);
		textField_mdp.addMouseListener(this);
		add(textField_mdp);
		textField_mdp.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mdp.setOpaque(false);
		this.pushKeyboard();

		/**
		 * Ajout de l'image de chargement du loading
		 */
		//file:///C:/Users/Sinardet/Documents/ProjetOracle/warquiz/Projet_QUIZ/images/loading.gif
		//URL url = new URL("images/loading.gif");
		
        panel = new Gif_anime(load("file:///C:/Users/Sinardet/Documents/ProjetOracle/warquiz/Projet_QUIZ/images/chargement_anime.gif"));
        panel.setBounds(646, 632, 95, 90);
        add(panel);  	
	}

	 private Image load(final String url) {
	       try {
	           final Toolkit tk = Toolkit.getDefaultToolkit();
	           final Image img = tk.createImage(new URL(url));
	           tk.prepareImage(img, -1, -1, null);
	           System.out.println("PASSAGE IMG GIF");
	           return img;
	       }catch (Exception e) {
	           e.printStackTrace();
	           return null;
	       }
	  }
	 
	/**
	 * Défini les actions du bouton ENTREE
	 */
	public void pushKeyboard(){ // On défini les action des touches  ENTER	
		textField_pseudo.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				if(evt.getKeyCode() == evt.VK_ENTER ){
					if(textField_pseudo.getText().length() != 0){	// Si le PSEUDO est entré						
						if(textField_mdp.getText().length() == 0){	// MDP est pas entré
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							tentative++;
							repaint();
							
						}
						else if(textField_mdp.getText().length() != 0){ // Sinon si le MDP est entré
							//Méthode permettant de se connecter selon si on est admin ou user
							SQL_Connect.testAdminUser();
						}
					}else{
						textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_pseudo=1;
						tentative++;
						repaint();
						if(textField_mdp.getText().length() == 0){
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
						if(textField_pseudo.getText().length() == 0){
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							tentative++;
							repaint();
						}
						else if(textField_pseudo.getText().length() != 0){
							//Méthode permettant de se connecter selon si on est admin ou user
							SQL_Connect.testAdminUser();
						}
					}else{
						textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_mdp=1;
						tentative++;
						repaint();
						if(textField_pseudo.getText().length() == 0){
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
			tentative = 0;
			fenetre.goToInscription(selection); // on appel la fonction qui va changer de panel
		}
		if(e.getX() >= 605 && e.getX() <= 790 && e.getY() >= 465 && e.getY() <= 513){ // Bouton de validation
			if(textField_mdp.getPassword().length != 0){
				if(textField_pseudo.getText().length() == 0){
					textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_pseudo=1;
					tentative++;
					repaint();
				}
				else if(textField_pseudo.getText().length() != 0){
					//Méthode permettant de se connecter selon si on est admin ou user
					SQL_Connect.testAdminUser();
				}
			}else{
				System.out.println(" ENTER PSEUDO ERROR ");
				textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
				bad_mdp=1;
				tentative++;
				repaint();
				if(textField_pseudo.getText().length() == 0){
					textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_pseudo=1;
					repaint();
				}
			}
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
		g.drawImage(Images.img_fond[1], 0, 0, this.getWidth(), this.getHeight(), null);	
		
		// On affiche ou non la banderole info
		if(bad_pseudo==1){
			g.drawImage(Images.img_element[3], 768, 326, 170, 46, null);	
		}
		if(bad_mdp==1){
			g.drawImage(Images.img_element[3], 768, 390, 170, 46, null);	
		}
		if(erreur_log){
			erreur_bdd = false;
			g.drawImage(Images.img_element[5], 140, 50, 800, 130, null);
		}
		if(erreur_bdd){
			erreur_log = false;
			g.drawImage(Images.img_element[6], 140, 50, 800, 130, null);
		}
		if(tentative >= 3){
			erreur_log = false;
			repaint();
			g.drawImage(Images.img_element[4], 140, 50, 800, 130, null);	
		}
        if(recherche_bdd == true){ // image de chargement.
        	panel.setVisible(true);
        	g.drawImage(Images.img_element[8], 646, 629, 350, 100, null);	
        	repaint();
        }
        if (recherche_bdd == false){	
        	panel.setVisible(false);
        	repaint();
        }
	}
}