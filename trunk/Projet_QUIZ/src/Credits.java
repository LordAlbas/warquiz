import java.awt.Graphics;
import java.sql.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;	

import javax.swing.JPanel;


public class Credits extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	
	private Fenetre fenetre;
	String image_select = "rien";			// defini l'image survol� (rien si pas survol� sinon [nom_image]_hover)
	String bouton_deco ="rien";				// defini l'image survol� bouton deco
	String bouton_retour ="rien";
	int val_i;								// defini l'emplacement de l'image survol� dans le tableau img_bouton_hover
	static String selection; 				// defini quel bouton est selectionn�
	JButton bouton = new JButton("Lancer Requête");

	
	/**
	 * Constructor
	 */
	public Credits(Fenetre fen) {
		this.add(bouton);                                      //Ajout du bouton à la fenêtre
	    bouton.setBounds(500, 500, 100, 30);                     //Position et dimension du bouton
	    bouton.addActionListener(this); 
		fenetre = fen;  // on r�cup�re la classe m�re
	}
	
	/**
	 * Methodes override du MouseListener
	 */
	public void mouseClicked(MouseEvent e) {
		// SELECT ZONE BOUTON_RETOUR
		if(e.getX() >= 1 && e.getX() <= 85 && e.getY() >= 685 && e.getY() <= 768){ // STATS
			selection = "accueil";
			fenetre.goToAccueil(selection); // on appel la fonction qui va changer de panel
			
		}
		// SELECT ZONE WARQUIZ
		if(e.getX() >= 1 && e.getX() <= 400 && e.getY() >= 1 && e.getY() <= 130){ // STATS
			selection = "accueil";
			fenetre.goToAccueil(selection); // on appel la fonction qui va changer de panel
		}
	}
	public void executeRequest(){
		Connection conn = null;
		try {
            // Load the SQLServerDriver class, build the
            // connection string, and get a connection	
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
            Statement requete = conn.createStatement();
            ResultSet resultatTest = requete.executeQuery("select * from ADMIN"); 
            System.out.println(resultatTest);
		} 	
		catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {
		if(e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 560 && e.getY() <= 645){ // QUITTER
			//System.out.print("QUITTER_hover");
			image_select = "QUITTER_hover";
			val_i = 3;
		}	
		repaint(); // On re dessine
	
		if(e.getX() >= 959 && e.getX() <= 1022 && e.getY() >= 1 && e.getY() <= 47){ // CO/DECO
			//System.out.print("CO/DECO_hover");
			bouton_deco = "CO/DECO_hover";
			//val_i = 4;
			repaint(); // On re dessine
		}	
		if(e.getX() >= 1 && e.getX() <= 85 && e.getY() >= 685 && e.getY() <= 768){ // retour
			bouton_retour = "retour_hover";
			//val_i = 4;
			repaint(); // On re dessine
		}			
	}
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);						// dessine le fond d'ecran
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		
		switch (bouton_deco){
		case "rien" :
			g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
			break;				
		case "CO/DECO_hover" :
			g.drawImage(Images.img_bouton_hover[4], 960, 1, 46, 46, null);
			bouton_deco = "rien";
			break;	
		}
		
		switch (bouton_retour)	{
		case "rien" :
			g.drawImage(Images.img_bouton[5], 1, 685, 84, 83, null);
			break;				
		case "retour_hover" :
			g.drawImage(Images.img_bouton_hover[5], 1, 685, 84, 83, null);
			bouton_retour = "rien";
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bouton)                                                            //On s'assure que l'événement provient du bouton
	    {
	       executeRequest();                                                                    //Lance la requête
	    }
	}
	
}
