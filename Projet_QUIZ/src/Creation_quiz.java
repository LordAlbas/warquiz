import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	// 65,105,225  => royal blue
	// 205,92,92   => indian red
	// 50,205,50   => lime green
	// 250,130,100 => plus ou moins rouge-orange light
	
	// variables de positionnement
	private int marginLeft = 160;
	private int cellSpace = 24;
	JLabel lb_hint = new JLabel("");
	
	// selection de la difficulte du quiz
	private String[] diff = {"default", "facile", "moyen", "difficile"};
	private JComboBox<String> cb_difficulte,cb_seconde,cb_heure,cb_minute;
	// pour gestions des questions
	private JButton[] tabQuest = new JButton[20];
	
	private JLabel[] lb_numQuest = new JLabel[20];
	private JLabel[] bt_suppr = new JLabel[20];
	private JLabel[] lb_nbRep = new JLabel[20];
	
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
		lb_titre.setForeground(Images.couleurLabel);
		lb_titre.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_titre.setBounds(555, 105, 400, 30);
		add(lb_titre);
		// ICI ON ENVOI LE NOM DU QUIZ (ou nom racourci)
		lb_nomQuiz = new JLabel(monQuiz.getNom());
		lb_nomQuiz.setForeground(Color.WHITE);
		lb_nomQuiz.setFont(new Font("Arial", Font.BOLD, 32)); 
		lb_nomQuiz.setBounds(605, 135, 400, 36);
		add(lb_nomQuiz);
		
		/*
		 * Selection de la difficulte du Quiz
		 */
		JLabel lb_diff = new JLabel("<html>Choix de la difficult&eacute; du Quiz :</html>");
		lb_diff.setForeground(Images.couleurLabel);
		lb_diff.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_diff.setBounds(650, 200, 400, 30);
		add(lb_diff);
		// UTILISATION D'UNE COMBOBOX
		cb_difficulte = new JComboBox<String>(diff);
		cb_difficulte.setBackground(new Color(54, 90, 118));
		cb_difficulte.setForeground(Color.WHITE);
		cb_difficulte.setFont(new Font("Arial", Font.PLAIN, 22));
		((JLabel)cb_difficulte.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		cb_difficulte.setBorder(null);
		cb_difficulte.setSelectedItem(monQuiz.getDifficulteQuiz());
		cb_difficulte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monQuiz.setDifficulteQuiz(cb_difficulte.getSelectedItem().toString());
			}
		});
		cb_difficulte.setBounds(720, 235, 200, 40);
		add(cb_difficulte);

		/*
		 * Bouton d'AJOUT QUESTIONS
		 */
		JButton ajout_question = new JButton("Ajouter une question");
		ajout_question.setBounds(795, 440, 200, 40);
		ajout_question.setFont(new Font("Arial", Font.PLAIN, 20));
		ajout_question.setBackground(new Color(219,113,0));
		ajout_question.setBorder(null);
		ajout_question.setForeground(Color.WHITE);
		ajout_question.addActionListener(new ActionListener() {
			// AJOUTE UNE QUESTION AU CLICK
			public void actionPerformed(ActionEvent e) {
				String nomQuest = (String)JOptionPane.showInputDialog(null,
						"Texte de la question :",
						"Creation Quiz",
						JOptionPane.QUESTION_MESSAGE);
				if ((nomQuest != null) && (nomQuest.length() > 0)) {
					// ATTENTION il faut securiser les inputs data en regard des requetes SQL qui vont utiliser ces data
					// ca se fait avec des PREPARED_STATEMENT !!
					
					// Ajout d'une question au tableau de boutons (local) (il se charge lui-meme de l'ajouter a l'objet Quiz)
					addQuestion(nextNull(tabQuest), nomQuest);
				} else {
					//System.out.println("Aucune string retournee");
				}
			}
		});
		add(ajout_question);
		
		/*
		 * Creation et positionnement des boutons de questions deja existante a l'arrivee sur cette page
		 */
		JLabel lb_quest = new JLabel("Liste des questions :");
		lb_quest.setForeground(Images.couleurLabel);
		lb_quest.setFont(new Font("Arial", Font.PLAIN, 22));
		lb_quest.setHorizontalAlignment(SwingConstants.CENTER);
		lb_quest.setBounds(marginLeft, 180, 300, 30);
		add(lb_quest);
		short y = 0;
		while (y < 20 && monQuiz.getQuest(y) != null) {
			tabQuest[y] = new JButton(monQuiz.getQuest(y).getQuestTxt());
			tabQuest[y].addActionListener(monQuiz.getQuest(y));
			tabQuest[y].setBounds(marginLeft, 220+(y*cellSpace), 300, 20);
			tabQuest[y].setFont(new Font("Arial", Font.PLAIN, 15));
			tabQuest[y].setBackground(new Color(7, 92, 120));
			tabQuest[y].setBorder(null);
			tabQuest[y].setForeground(Color.WHITE);
			add(tabQuest[y]);
			addNumQuestion(y);
			addNbReponses(y);
			addSuppr(y);
			y++;
		}
		
		/* 
		 * Ajout des combobox pour les timers
		 */
		JLabel lb_temps = new JLabel("<html>Temps du Quiz :</html>");
		lb_temps.setForeground(Images.couleurLabel);
		lb_temps.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_temps.setBounds(750, 320, 400, 30);
		add(lb_temps);
		
		JLabel lb_heure = new JLabel("<html>h</html>");
		lb_heure.setForeground(Color.WHITE);
		lb_heure.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_heure.setBounds(815, 370, 25, 30);
		add(lb_heure);
		cb_heure = new JComboBox();
		for (short i=0 ; i<10; i++){
			cb_heure.addItem(""+i);
		}
		cb_heure.setSelectedItem(monQuiz.getHeureQuiz());
		if (monQuiz.getHeureQuiz() == null)		// si heure vide, on selectionne le premier choix (h=0 au lieu de h=null, plus de plantage)
			cb_heure.setSelectedIndex(0);
		cb_heure.setBackground(new Color(54, 90, 118));
		cb_heure.setForeground(Color.WHITE);
		cb_heure.setFont(new Font("Arial", Font.PLAIN, 22));
		((JLabel)cb_heure.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		cb_heure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monQuiz.setHeureQuiz(cb_heure.getSelectedItem().toString());
			}
		});
		cb_heure.setBounds(765, 360, 50, 40);
		add(cb_heure);
		
		JLabel lb_minute = new JLabel("<html>min</html>");
		lb_minute.setForeground(Color.WHITE);
		lb_minute.setFont(new Font("Arial", Font.PLAIN, 22));
		lb_minute.setBounds(880, 370, 35, 30);
		add(lb_minute);	
		cb_minute = new JComboBox();
		for (short i=0 ; i<60; i++){
			cb_minute.addItem(""+i);
		}
		cb_minute.setSelectedItem(monQuiz.getMinuteQuiz());
		if (monQuiz.getMinuteQuiz() == null)
			cb_minute.setSelectedIndex(0);
		cb_minute.setBackground(new Color(54, 90, 118));
		cb_minute.setForeground(Color.WHITE);
		cb_minute.setFont(new Font("Arial", Font.PLAIN, 22));
		((JLabel)cb_minute.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		cb_minute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monQuiz.setMinuteQuiz(cb_minute.getSelectedItem().toString());
			}
		});
		cb_minute.setBounds(830, 360, 50, 40);
		add(cb_minute);
		
		JLabel lb_seconde = new JLabel("<html>sec</html>");
		lb_seconde.setForeground(Color.WHITE);
		lb_seconde.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_seconde.setBounds(965, 370, 35, 30);
		add(lb_seconde);
		cb_seconde = new JComboBox();
		for (short i=0 ; i<60; i++){
			cb_seconde.addItem(""+i);
		}
		cb_seconde.setSelectedItem(monQuiz.getSecondeQuiz());
		if (monQuiz.getSecondeQuiz() == null)
			cb_seconde.setSelectedIndex(0);
		cb_seconde.setBackground(new Color(54, 90, 118));
		cb_seconde.setForeground(Color.WHITE);
		cb_seconde.setFont(new Font("Arial", Font.PLAIN, 22));
		((JLabel)cb_seconde.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		cb_seconde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monQuiz.setSecondeQuiz(cb_seconde.getSelectedItem().toString());
			}
		});
		cb_seconde.setBounds(915, 360, 50, 40);
		add(cb_seconde);
		
		/*
		 * Si aucune question pour le moment, mettre un petit message.
		 */
		if (tabQuest[0] == null) {
			setHintMsg();
		}
		
		/*
		 * Petit JLabel en pied de page
		 */
		JLabel lb_footer = new JLabel("* Maximum : 20 questions.");
		lb_footer.setForeground(Images.couleurLabel);
		lb_footer.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_footer.setHorizontalAlignment(SwingConstants.CENTER);
		lb_footer.setBounds(marginLeft, 705, 300, 20);
		add(lb_footer);
		
		JLabel lb_footer2 = new JLabel("<html>Cliquez sur une question pour ajouter des r&eacute;ponses.</html>");
		lb_footer2.setForeground(Images.couleurLabel);
		lb_footer2.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_footer2.setHorizontalAlignment(SwingConstants.CENTER);
		lb_footer2.setBounds(marginLeft, 720, 350, 20);
		add(lb_footer2);
		
		/*
		 * Bouton pour Valider le Quiz
		 */
		JButton bt_valider = new JButton("Valider le Quiz");
		bt_valider.setBounds(860, 570, 136, 40);
		bt_valider.setFont(new Font("Arial", Font.PLAIN, 20));
		bt_valider.setBackground(new Color(27, 113, 16));
		bt_valider.setBorder(null);
		bt_valider.setForeground(Color.WHITE);
		
		bt_valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateQuiz();
			}
		});
		add(bt_valider);
		
		repaint();
		
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
        
        header1.setInCreateQuiz(true);
	}
	
	public void setHintMsg() {
		lb_hint.setText("<html>&emsp;Ce quiz ne contient pour le moment aucune question.<br/><br/>&emsp;Aidez-vous du bouton sur votre droite pour commencer &agrave; ajouter des questions.</html>");
		lb_hint.setForeground(Color.WHITE);
		lb_hint.setFont(new Font("Arial", Font.PLAIN, 16));
		lb_hint.setBounds(marginLeft, 250, 300, 100);
		add(lb_hint);
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
	 */
	public void addQuestion(int i, String nomQuest) {
		String err_msg = "";
		boolean existAlready = false;
		if (i < 20) {
			for (byte j=0; j<i; j++) {
				if (tabQuest[j].getText().equals(nomQuest))
					existAlready = true;
			}
			if (!existAlready) {
				lb_hint.setText("");
				tabQuest[i] = new JButton(nomQuest);
				Question maQuest = monQuiz.ajoutQuestion(nomQuest);
				tabQuest[i].addActionListener(maQuest);
				tabQuest[i].setFont(new Font("Arial", Font.PLAIN, 15));
				tabQuest[i].setBackground(new Color(7, 92, 120));
				tabQuest[i].setBorder(null);
				tabQuest[i].setForeground(Color.WHITE);
				tabQuest[i].setBounds(marginLeft, 220+(i*cellSpace), 300, 20);
				add(tabQuest[i]);
				addNumQuestion(i);
				addNbReponses(i);
				addSuppr(i);
				repaint();
			} else {
				err_msg = "<html>Cette question existe d&eacute;j&agrave; dans ce quiz !</html>";
			}
		} else {
			err_msg = "Le nombre de question maximum est atteint !";
		}
		if (err_msg != "")
			JOptionPane.showMessageDialog(null, 
					err_msg, 
					"Erreur dans l'ajout de question", 
					JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Ajoute un numero de question a gauche du bouton de la question
	 */
	public void addNumQuestion(int i) {
		lb_numQuest[i] = new JLabel("# "+(i+1));
		lb_numQuest[i].setForeground(Color.WHITE);
		lb_numQuest[i].setFont(new Font("Arial", Font.PLAIN, 16)); 
		lb_numQuest[i].setBounds(marginLeft-70, 220+(i*cellSpace), 50, 20);
		lb_numQuest[i].setHorizontalAlignment(SwingConstants.RIGHT);
		add(lb_numQuest[i]);
	}
	
	/**
	 * Ajoute les "nb_rep / nb_repJuste" a droite du bouton de la question
	 */
	public void addNbReponses(int i) {
		lb_nbRep[i] = new JLabel("<html>(<span style='color:green;'>"+monQuiz.getQuest(i).getNbr_reponses_juste()+"</span>/<span style='color:red;'>"+monQuiz.getQuest(i).getNb_reponses()+"</span>)</html>");
		lb_nbRep[i].setForeground(Color.WHITE);
		lb_nbRep[i].setFont(new Font("Arial", Font.PLAIN, 16)); 
		lb_nbRep[i].setBounds(marginLeft+320, 220+(i*cellSpace), 50, 20);
		add(lb_nbRep[i]);
	}
	
	/**
	 * Dessine les croix de suppression des questions.
	 * @param i
	 */
	public void addSuppr(int i) {
		bt_suppr[i] = new JLabel(""+i);
		ImageIcon btSuppr = new ImageIcon(Images.img_bouton[9]);
		bt_suppr[i].setIcon(btSuppr);
		bt_suppr[i].setBounds(marginLeft+370, 220+(i*cellSpace), 20, 20);
		bt_suppr[i].addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				JLabel yo = (JLabel) e.getSource();
				int id_quest = Integer.parseInt(yo.getText());
				monQuiz.delQuestion(id_quest);
				
				/*
				 * /!\ need del les rep de la quest /!\
				 * (ou alors la suppression de la question suffit a perdre les references aux reponses et donc les supprimes auto..)
				 */
				
				// On decale toute les lignes suivantes..
				while (id_quest<tabQuest.length-1 && tabQuest[id_quest+1] != null) {
					decaleTout(id_quest);
					id_quest++;
				}
				// ..et on supprime la derniere ligne.
				rmQuestNbrepNum(id_quest);
				if (monQuiz.getNb_questions() <= 0)
					setHintMsg();
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		add(bt_suppr[i]);
	}
	
	/**
	 * Private : Called uniquement dans le MouseListener de "addSuppr()".
	 * @param id
	 */
	private void rmQuestNbrepNum(int id) {
		this.remove(tabQuest[id]);
		this.remove(lb_numQuest[id]);
		this.remove(lb_nbRep[id]);
		this.remove(bt_suppr[id]);
		tabQuest[id] = null;
		lb_numQuest[id] = null;
		lb_nbRep[id] = null;
		bt_suppr[id] = null;
		this.repaint();
	}
	
	/**
	 * Private : Called uniquement dans le while du MouseListener de "addSuppr()".
	 * @param id_quest
	 */
	private void decaleTout(int id_quest) {
		remove(tabQuest[id_quest]);
		remove(lb_nbRep[id_quest]);
		
		tabQuest[id_quest].setText(tabQuest[id_quest+1].getText());			// Decale le texte du bouton
		for (ActionListener al : tabQuest[id_quest].getActionListeners()) {
			tabQuest[id_quest].removeActionListener(al);					// Enleve les ancien ActionListener (en theorie, un seul)
		}
		tabQuest[id_quest].addActionListener(monQuiz.getQuest(id_quest));	// Rajoute le nouveau ActionListener
		tabQuest[id_quest].setFont(new Font("Arial", Font.PLAIN, 15));
		tabQuest[id_quest].setBackground(new Color(7, 92, 120));
		tabQuest[id_quest].setBorder(null);
		tabQuest[id_quest].setForeground(Color.WHITE);
		lb_nbRep[id_quest].setText(lb_nbRep[id_quest+1].getText());			// Decale le compte des rep_juste et rep_total
		
		add(tabQuest[id_quest]);
		add(lb_nbRep[id_quest]);
		
		fenetre.repaint();
	}
	
	/**
	 * Verifie l'etat du quiz en preparation de validation.
	 * Renvoi une string non-vide si quelque chose ne va pas.
	 * @return
	 */
	public String testValidationQuiz() {
		boolean nbRepJusteNull = false;
		boolean nbRepNull = false;
		String msg = "";
		if (monQuiz.getNb_questions() <= 0)
			msg = "- Le quiz ne contient aucune question pour le moment !<br/>";
		if (cb_difficulte.getSelectedItem().toString() == "default")
			msg += "- La difficult&eacute; du quiz n'a pas &eacute;t&eacute; d&eacute;finie !<br/>";
		if (cb_heure.getSelectedIndex() == 0 && cb_minute.getSelectedIndex() == 0 && cb_seconde.getSelectedIndex() == 0)
			msg += "- Le temps du quiz n'a pas &eacute;t&eacute; d&eacute;fini !<br/>";
		for (byte i=0; i<monQuiz.getNb_questions(); i++) {
			if (monQuiz.getQuest(i).getNbr_reponses_juste() <= 0)
				nbRepJusteNull = true;
			if (monQuiz.getQuest(i).getNb_reponses() <= 0)
				nbRepNull = true;
		}
		if (nbRepNull)
			msg += "- Le quiz contient des questions sans r&eacute;ponses !<br/>";
		if (nbRepJusteNull)
			msg += "- Le quiz comporte des questions sans bonnes r&eacute;ponses !<br/>";
		return msg;
	}
	
	/**
	 * Validation du QUIZ
	 * Called from click sur le bouton valider
	 */
	public void validateQuiz() {
		monQuiz.setHeureQuiz(cb_heure.getSelectedItem().toString());		// recupere les h/min/sec meme si elles n'ont pas ete changer
		monQuiz.setMinuteQuiz(cb_minute.getSelectedItem().toString());
		monQuiz.setSecondeQuiz(cb_seconde.getSelectedItem().toString());
		
		String error_msg = testValidationQuiz();
		if (error_msg != "")					// Si la validation renvoi une string non-vide c'est que quelque chose ne va pas.
			JOptionPane.showMessageDialog(null, 
					"<html>"+error_msg+"</html>", 
					"Quiz non valide", 
					JOptionPane.ERROR_MESSAGE);
		else {
			SQL_Requete_Quiz reqQuiz = new SQL_Requete_Quiz(fenetre);
			
			if (reqQuiz.setModifQuiz(monQuiz)) {	// Envoi le quiz en validation c'est a dire dans la BDD !
													// Si tout est OK, on affiche un pop-up et on revient a gestion_quiz.
				JOptionPane.showMessageDialog(null, 
						"<html>Votre quiz &agrave; bien &eacute;t&eacute; enregistr&eacute; !<br/>"
						+ "Les utilisateurs peuvent maintenant y jouer.</html>", 
						"Quiz Valid&eacute;", 
						JOptionPane.INFORMATION_MESSAGE);
				Gestion_quiz goToGestion = new Gestion_quiz(fenetre);
				fenetre.getContentPane().setVisible(false);
				goToGestion.addMouseListener(goToGestion);
				fenetre.setContentPane(goToGestion);
				fenetre.getContentPane().setVisible(true);
			} else {								// ...Sinon (la requete n'est pas passé) on affiche un message d'erreur et gfy.
				JOptionPane.showMessageDialog(null, 
						"<html><h1>Fail !</h1>"
						+ "Une erreur est survenue au moment<br/>"
						+ "de l'insertion en base de votre quiz !<br/>"
						+ "La sauvegarde n'a pas eu lieu ... =(<br/><br/>"
						+ "Contactez un developpeur et esp&eacute;rez<br/>"
						+ "que vous n'avez pas mis le feu &agrave; nos<br/>"
						+ "donn&eacute;es ..!</html>", 
						"Erreur dans l'insertion du Quiz", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);		// dessine le header	
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
	}
}