import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;


public class Creation_quiz extends JPanel implements MouseListener, MouseMotionListener{
	
	/**
	 * ATTRIBUTS DE CLASSES
	 */
	// pour les dialogues entre classes
	private Fenetre fenetre;
	private Admin_ajout_reponses admin_ajout_reponses;
	
	// pour les hover sur les images boutons
	private String selection;
	private String bouton_deco ="rien";
	private String bouton_retour ="rien";
	private String image_select;
	private int val_i;
	private Header header1;
	private Header_menu header2;
	
	// objet quiz qui se fait traiter actuelement
	private JLabel lb_nomQuiz;
	
	// pour gestions des questions
	private JButton[] tabQuest = new JButton[20];
	
	/**
	 * CONSTRUCTOR
	 * @param fen
	 */
	public Creation_quiz(Fenetre fen){
		setLayout(null);
		
		/*
		 * Initialisation des attributs de classes
		 */
		fenetre = fen;
		
		/*
		 * JLabel affichage NOM QUIZ
		 */
		// ICI ON ENVOI LE NOM DU QUIZ (ou nom racourci)
		lb_nomQuiz = new JLabel("Mega Quiz de la mort qui tue (pro only plz no noob no leaver)");
		lb_nomQuiz.setForeground(Color.WHITE);
		lb_nomQuiz.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_nomQuiz.setBounds(555, 125, 400, 30);
		add(lb_nomQuiz);
		
		/*
		 * Bouton d'AJOUT QUESTIONS
		 */
		JButton ajout_question = new JButton("Ajouter des questions");
		ajout_question.setBounds(700, 180, 200, 40);
		ajout_question.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// AJOUTE UNE QUESTION AU CLICK
				addQuestion(nextNull(tabQuest), tabQuest);
			}
		});
		add(ajout_question);
		
		
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
	 * Parcour le tabQuestion de JButton pour trouver la prochaine case vide (null)
	 * @param tabQ
	 * @return
	 */
	public int nextNull(JButton[] tabQ) {
		int i = 0;
		while(i<20 && tabQ[i] != null) {
			i++;
		}
		return i;
	}
	
	/**
	 * Ecrit dans la prochaine case vide de tabQuestion un nouveau bouton
	 * (sauf si le tableau est plein)
	 * @param i
	 * @param tabQ
	 */
	public void addQuestion(int i, JButton[] tabQ) {
		if (i < 20) {
			System.out.println("Next NULL = tabQuest["+i+"], ecriture dedans ...");
			tabQuest[i] = new JButton("Yo!");
			tabQuest[i].addActionListener(new ActionListener() {
				// ACTIONLISTENER DES BOUTONS DE QUESTIONS
				// redirige sur la page admin_ajout_reponses.
				public void actionPerformed(ActionEvent e) {
					fenetre.getContentPane().setVisible(false);
					//admin_ajout_reponses = new Admin_ajout_reponses(fenetre, fenetre.creation_quiz);
					admin_ajout_reponses.addMouseListener(admin_ajout_reponses);
					fenetre.setContentPane(admin_ajout_reponses);
					fenetre.getContentPane().setVisible(true);
				}
			});
			tabQuest[i].setBounds(280, 200+(i*22), 200, 20);
			add(tabQuest[i]);
			repaint();
			
			// print de debug du tabQuest
			/*for (byte j = 0; j<20; j++) {
				System.out.println("\t tabQuest["+j+"] = "+tabQuest[j]);
			}*/
		} else {
			System.out.println("tableau plein j'imagine (i = "+i+")");
		}
	}
	
	/**
	 * Getter pour le text du label lb_nomQuiz
	 * @return
	 */
	public String getLb_nomQuiz() {
		return lb_nomQuiz.getText();
	}
	
	public void mouseDragged(MouseEvent arg0) {}
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
	
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= 1 && e.getX() <= 85 && e.getY() >= 685 && e.getY() <= 768){ // Retour
			System.out.print("Retour");
			selection = "accueil";
			fenetre.goToAccueil(selection); // on appel la fonction qui va changer de panel
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		//g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		
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
}