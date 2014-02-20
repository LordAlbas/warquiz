import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSeparator;

import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.SystemColor;


public class Creation_quiz extends JPanel implements MouseListener, MouseMotionListener{
	
	/*
	 * ATTRIBUTS DE CLASSES
	 */
	// pour les dialogues entre classes
	private Fenetre fenetre;
	private Admin_ajout_reponses admin_ajout_reponses;
	private Admin_ajout_questions admin_ajout_questions;
	
	// pour rien
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	// pour les hover sur les images boutons
	private String selection;
	private String bouton_deco ="rien";
	private String bouton_retour ="rien";
	private String image_select;
	private int val_i;
	
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
		admin_ajout_reponses = new Admin_ajout_reponses(fenetre);
		admin_ajout_questions = new Admin_ajout_questions(fenetre);
		
		/*
		 * JLabel affichage NOM QUIZ
		 */
		JLabel lb_nomQuiz = new JLabel("QUIZ Nºxx : \"Mega Quiz sur les Ponney\"");
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
		
		/*
		 * Bouton d'AJOUT REPONSES (va disparaitre soon...)
		 */
		JButton ajout_reponses = new JButton("Ajouter des reponses");
		ajout_reponses.setBounds(700, 240, 200, 40);
		ajout_reponses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.getContentPane().setVisible(false);
				admin_ajout_reponses.addMouseListener(admin_ajout_reponses);
				fenetre.setContentPane(admin_ajout_reponses);
				fenetre.getContentPane().setVisible(true);
			}
		});
		add(ajout_reponses);
		
		/*
		textField = new JTextField();
		textField.setBounds(280, 300, 204, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(280, 373, 204, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(280, 430, 204, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		
		Choice choice = new Choice();
		choice.setBounds(280, 489, 204, 27);
		add(choice);
		*/
		
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
			tabQuest[i].setBounds(280, 200+(i*22), 200, 20);
			add(tabQuest[i]);
			repaint();
			// print de debug du tabQuest
			for (byte j = 0; j<20; j++) {
				System.out.println("\t tabQuest["+j+"] = "+tabQuest[j]);
			}
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
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
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
