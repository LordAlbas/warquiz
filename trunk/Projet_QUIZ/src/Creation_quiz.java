import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Quiz monQuiz;
	
	// pour les hover sur les images boutons
	private String selection;
	private String bouton_deco ="rien";
	private String bouton_retour ="rien";
	private String image_select;
	private int val_i;
	private Header header1;
	private Header_menu header2;
		
	// nom du quiz qui se fait traiter actuelement
	private JLabel lb_nomQuiz;
	
	// selection de la difficulte du quiz
	private String[] diff = {"default", "facile", "moyen", "difficile"};
	private JComboBox cb_difficulte;
	
	// pour gestions des questions
	private JButton[] tabQuest = new JButton[20];
	
	/**
	 * CONSTRUCTOR
	 * @param fen
	 */
	public Creation_quiz(Fenetre fen, Quiz quiz){
		setLayout(null);
		
		/*
		 * Initialisation des attributs de classes
		 */
		fenetre = fen;
		monQuiz = quiz;
		
		/*
		 * JLabel affichage NOM QUIZ
		 */
		JLabel lb_titre = new JLabel("Nom du Quiz :");
		lb_titre.setForeground(Color.WHITE);
		lb_titre.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_titre.setBounds(565, 125, 400, 30);
		add(lb_titre);
		// ICI ON ENVOI LE NOM DU QUIZ (ou nom racourci)
		lb_nomQuiz = new JLabel(monQuiz.getNom());
		lb_nomQuiz.setForeground(Color.WHITE);
		lb_nomQuiz.setFont(new Font("Arial", Font.BOLD, 32)); 
		lb_nomQuiz.setBounds(615, 155, 400, 36);
		add(lb_nomQuiz);
		
		/*
		 * Selection de la difficulte du Quiz
		 */
		JLabel lb_diff = new JLabel("<html>Choix de la difficult&eacute;e du Quiz :</html>");
		lb_diff.setForeground(Color.WHITE);
		lb_diff.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_diff.setBounds(676, 240, 400, 30);
		add(lb_diff);
		// UTILISATION D'UNE COMBOBOX
		cb_difficulte = new JComboBox(diff);
		cb_difficulte.setSelectedItem(monQuiz.getDifficulteQuiz());
		cb_difficulte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(cb_difficulte.getSelectedItem());
				monQuiz.setDifficulteQuiz(cb_difficulte.getSelectedItem().toString());
			}
		});
		cb_difficulte.setBounds(720, 275, 100, 40);
		add(cb_difficulte);

		/*
		 * Bouton d'AJOUT QUESTIONS
		 */
		JButton ajout_question = new JButton("Ajouter une question");
		ajout_question.setBounds(785, 380, 200, 40);
		ajout_question.addActionListener(new ActionListener() {
			// AJOUTE UNE QUESTION AU CLICK
			public void actionPerformed(ActionEvent e) {
				String nomQuest = (String)JOptionPane.showInputDialog("Texte de la question :");
				if ((nomQuest != null) && (nomQuest.length() > 0)) {
					// ATTENTION il faut securiser les inputs data en regard des requetes SQL qui vont utiliser ces data
					// ca se fait avec des PREPARED_STATEMENT !!
					
					// Ajout d'une question au tableau de boutons (local) (il se charge lui-meme de l'ajouter a l'objet Quiz)
					addQuestion(nextNull(tabQuest), tabQuest, nomQuest);
				} else {
					System.out.println("Aucune string retournee");
				}
			}
		});
		add(ajout_question);
		
		/*
		 * Creation et positionnement des boutons de questions deja creees
		 */
		JLabel lb_quest = new JLabel("Liste des questions :");
		lb_quest.setForeground(Color.WHITE);
		lb_quest.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_quest.setBounds(280, 200, 400, 30);
		add(lb_quest);
		short y = 0;
		while (y < 20 && quiz.getQuest(y) != null) {
			tabQuest[y] = new JButton(quiz.getQuest(y).getQuestTxt());
			tabQuest[y].addActionListener(quiz.getQuest(y));
			tabQuest[y].setBounds(280, 230+(y*22), 200, 20);
			add(tabQuest[y]);
			y++;
		}
		repaint();
		
		
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
	public void addQuestion(int i, JButton[] tabQ, String nomQuest) {
		boolean existAlready = false;
		//System.out.println("Next NULL = tabQuest["+i+"], ecriture dedans ...");
		if (i < 20) {
			for (byte j=0; j<i; j++) {
				//System.out.println("["+j+"] {{ "+nomQuest+" }} VS {{ "+tabQuest[j].getText()+" }}");
				if (tabQuest[j].getText().equals(nomQuest))
					existAlready = true;
			}
			if (!existAlready) {
				tabQuest[i] = new JButton(nomQuest);
				Question maQuest = monQuiz.ajoutQuestion(nomQuest);
				tabQuest[i].addActionListener(maQuest);
				tabQuest[i].setBounds(280, 230+(i*22), 200, 20);
				add(tabQuest[i]);
				repaint();
			} else {
				System.out.println("Question deja existante dans la liste.");
			}
			
			// print de debug du tabQuest
			/*for (byte j = 0; j<20; j++) {
				System.out.println("\t tabQuest["+j+"] = "+tabQuest[j]);
			}*/
		} else {
			System.out.println("tableau plein j'imagine (i = "+i+")");
		}
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