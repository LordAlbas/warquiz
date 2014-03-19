

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;


public class Accueil extends JPanel implements MouseListener, MouseMotionListener {
	
	private String image_select = "rien";			// defini l'image survol� (rien si pas survol� sinon [nom_image]_hover)
	
	private int val_i;								// defini l'emplacement de l'image survol� dans le tableau img_bouton_hover
	static String selection; 				// defini quel bouton est selectionn�

	private Fenetre fenetre;
	
	private Header header1;
	private Header_menu header2;	

	/**
	 * Constructeur
	 * @param fen
	 */
	public Accueil(Fenetre fen) {
		fenetre = fen; // on recupere la classe mere
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
	 * Methodes obligatoires de l'interface MouseListener.
	 * Elles sont appelees automatiquement selon l'action de la souris
	 */
	public void mouseClicked(MouseEvent e) {
		// On recupere X et Y au click
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 260 && e.getY() <= 345) { // JOUER
			System.out.print("JOUER");
			selection = "jouer";
			fenetre.goToJouer(selection); // on appel la fonction qui va changer de panel
		}
		
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 360 && e.getY() <= 445) { // STATS
			System.out.print("STATS");
			selection = "statistiques";
			fenetre.goToStatistiques(selection); // on appel la fonction qui va changer de panel
		}
		
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 460 && e.getY() <= 545) { // CREDITS
			System.out.print("CREDITS");
			selection = "credits";
			fenetre.goToCredits(selection); // on appel la fonction qui va changer de panel
		}
		
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 560 && e.getY() <= 645) { // QUITTER
			System.out.print("QUITTER");
			selection = "quitter";
			fenetre.goToQuitter(selection); // on appel la fonction qui va changer de panel
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e){}
	
	public void mouseMoved(MouseEvent e) { // On recupere constamment la position de la souris
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 260 && e.getY() <= 345) { // JOUER
			//System.out.print("JOUER_hover");
			image_select = "JOUER_hover";
			val_i = 0;
		}
		
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 360 && e.getY() <= 445) { // STATS
			//System.out.print("STATS_hover");
			image_select = "STATS_hover";
			val_i = 1;
		}
		
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 460 && e.getY() <= 545) { // CREDITS
			//System.out.print("CREDITS_hover");
			image_select = "CREDITS_hover";
			val_i = 2;
		}
		if (e.getX() >= 120 && e.getX() <= 490 && e.getY() >= 560 && e.getY() <= 645) { // QUITTER
			//System.out.print("QUITTER_hover");
			image_select = "QUITTER_hover";
			val_i = 3;
		}
		
		repaint(); // On re dessine
	}
	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);							// dessine le fond d'ecran
		//g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);	// dessine le header
		
		/*
		 * On boucle sur tous les boutons (de [0] a [3]).
		 * Chaque bouton obtient une position X (hauteur) en fonction de son numero (i).
		 * Les positions en durs (ici 120 et 260) correspondent au coin haut-gauche du bloc de bouton.
		 */
		for (int i=0; i<4; i++) {
			// On dessine l'image sans ruban si le curseur n'est pas dessus.
			switch (image_select){
			case "rien" :
				// 7 = gérer
				// 0 = jouer
				if(Connexion.connexion_admin){	
					if(i==0){
						g.drawImage(Images.img_bouton[7], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					}
					else{
						g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					}
				} else {
					g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
				}
				break;
				
			case "JOUER_hover" :
				if (val_i == i){
					if(Connexion.connexion_admin && image_select=="JOUER_hover"){
						g.drawImage(Images.img_bouton_hover[7], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					}
					else{
						g.drawImage(Images.img_bouton_hover[0], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					}					
					image_select = "rien";
				} else {
					if(Connexion.connexion_admin && image_select=="JOUER_hover"){
						g.drawImage(Images.img_bouton_hover[7], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					} else {
						g.drawImage(Images.img_bouton_hover[0], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					}
				}
				break;
				
			case "STATS_hover" :
				if (val_i == i){
					g.drawImage(Images.img_bouton_hover[1], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					image_select = "rien";
				}
				else if(i==0 && Connexion.connexion_admin){
					g.drawImage(Images.img_bouton[7], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
				} else {
					g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
				}
				break;
				
			case "CREDITS_hover" :
				if(val_i == i){
					g.drawImage(Images.img_bouton_hover[2], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					image_select = "rien";
				}
				else if(i==0 && Connexion.connexion_admin){
					g.drawImage(Images.img_bouton[7], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
				} else {
					g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
				}
				break;
				
			case "QUITTER_hover" :
				if(val_i == i){
					g.drawImage(Images.img_bouton_hover[3], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
					image_select = "rien";
				}
				else if(i==0 && Connexion.connexion_admin){
					g.drawImage(Images.img_bouton[7], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
				} else {
					g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
				}
				break;
			}
		}
		
		g.drawImage(Images.img_element[1], 140, 113, 8, 655, null);
		
		//g.setColor(new Color(255, 255, 255));
		//g.setFont(new Font("Courier New", Font.BOLD, 36));
		//g.drawString("Yoo!", 400, 600);
	}
}
