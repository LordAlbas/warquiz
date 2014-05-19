import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Admin_ajout_reponses extends JPanel  implements MouseListener, ItemListener{
	
	private Fenetre fenetre;
	private Quiz current_quiz;
	private int idQuest;
	
	private JLabel txf_nomQuiz;
	private JLabel txf_nomQuest;
	
	//private List list_reponses;
	private JTextField[] reponses = new JTextField[10];
	private JCheckBox[] statusRep = new JCheckBox[10];
	private JLabel[] bt_suppr = new JLabel[10];
	private JLabel[] lb_nbRep = new JLabel[10];
	
	private JButton bt_retour;
	
	private char questChar;
		
	Header header1;
	Header_menu header2;
	
	/**
	 * CONSTRUCTOR
	 * @param fen
	 * @param quiz
	 */
	public Admin_ajout_reponses(Fenetre fen, Quiz monQuiz, int numQuest){
		setLayout(null);
		
		// recuperation des attributs
		fenetre = fen;
		current_quiz = monQuiz;
		idQuest = numQuest;
		questChar = 'A';
		
		/*
		 * Creation du titre qui recupere le nom du Quiz (avec son JLabel qui va bien)
		 */
		JLabel lb_nomQuiz = new JLabel("Nom du Quiz : ");
		lb_nomQuiz.setForeground(Images.couleurLabel);
		lb_nomQuiz.setFont(new Font("Arial", Font.PLAIN, 22)); 
		lb_nomQuiz.setBounds(565, 125, 400, 30);
		add(lb_nomQuiz);
		txf_nomQuiz = new JLabel(current_quiz.getNom());	// recupere le nom du quiz
		txf_nomQuiz.setForeground(Color.WHITE);
		txf_nomQuiz.setFont(new Font("Arial", Font.BOLD, 32));
		txf_nomQuiz.setBounds(615, 155, 400, 36);
		add(txf_nomQuiz);
		
		/*
		 * Bouton d'AJOUT REPONSES
		 */
		JButton ajout_question = new JButton("<html>Ajouter une r&eacute;ponse</html>");
		ajout_question.setBounds(795, 395, 200, 40);
		ajout_question.setBackground(new Color(27, 113, 16));
		ajout_question.setForeground(Color.WHITE);
		ajout_question.setBorder(null);
		ajout_question.setFont(new Font("Arial", Font.PLAIN, 20));
		ajout_question.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ajoute une reponse au moment du click
				addReponse();
			}
		});
		add(ajout_question);
		
		/*
		 * Creation du sous-titre qui recupere le nom de la Question
		 */
		JLabel lb_nomQuest = new JLabel("Nom de la question : ");
		lb_nomQuest.setForeground(Images.couleurLabel);
		lb_nomQuest.setFont(new Font("Arial", Font.PLAIN, 22));
		lb_nomQuest.setHorizontalAlignment(SwingConstants.CENTER);
		lb_nomQuest.setBounds(80, 190, 300, 30);
		add(lb_nomQuest);
		txf_nomQuest = new JLabel("<html>"+monQuiz.getQuest(idQuest).getQuestTxt()+"</html>");	// recupere le nom de la question
		txf_nomQuest.setForeground(Color.WHITE);
		txf_nomQuest.setFont(new Font("Arial", Font.BOLD, 20));
		txf_nomQuest.setVerticalAlignment(SwingConstants.NORTH);
		txf_nomQuest.setBounds(60, 225, 500, 70);
		add(txf_nomQuest);
		
		/*
		 * Recuperation et Creation des reponses deja existante
		 */
		short y = 0;
		// current_quiz.getQuest(idQuest).getReponse(y) est vide ...
		// => Quiz.Question.Reponses[i]
		while (y<10 && current_quiz.getQuest(idQuest).getReponse(y) != null) {
			reponses[y] = new JTextField(current_quiz.getQuest(idQuest).getReponse(y).getTxtReponse());
			reponses[y].setBounds(65+((y%2)*315), 330+((y/2)*70), 230, 23);
			add(reponses[y]);
			addNbRep(y);
			statusRep[y] = new JCheckBox();
			statusRep[y].setBounds(35+((y%2)*315), 328+((y/2)*70), 25, 25);
			statusRep[y].setOpaque(false);
			statusRep[y].setSelected(current_quiz.getQuest(idQuest).getReponse(y).getStatutRep());
			statusRep[y].addActionListener(current_quiz.getQuest(idQuest).getReponse(y));
			addSuppr(y);
			add(statusRep[y]);
			y++;
		}
		
		/*
		 * Bouton pour retourner a la page du Quiz
		 */
		bt_retour = new JButton("Retour au Quiz");
		bt_retour.setBounds(855, 570, 140, 40);
		bt_retour.setBackground(new Color(7, 92, 158));
		bt_retour.setForeground(Color.WHITE);
		bt_retour.setBorder(null);
		bt_retour.setFont(new Font("Arial", Font.PLAIN, 20));
		bt_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean existAlready = false;
				boolean isEmpty = false;
				for (byte j=0; j<reponses.length-1; j++) {				// boucle les reponses de 0 a n-1
					if (reponses[j] != null && reponses[j].getText().isEmpty())
						isEmpty = true;
					for (byte i=(byte)(j+1); i<reponses.length; i++) {	// boucle les reponses de 1 a n
						if (reponses[i] != null && reponses[i].getText().isEmpty())
							isEmpty = true;
						if (reponses[i] != null && reponses[j].getText().equals(reponses[i].getText()))
							existAlready = true;
					}
				}
				if (existAlready || isEmpty) {
					// TODO pop-up d'erreur "il y a des reponses en double (txtQuest = txtQuest)".
					if (existAlready)
						JOptionPane.showMessageDialog(null,
							    "<html>Impossible de valider vos réponses !<br/>- Il semble qu'au moins 2 réponses soient identiques !</html>",
							    "Erreur",
							    JOptionPane.WARNING_MESSAGE);
						System.out.println("Erreur -> Duplicata dans les reponses ! (doublon ou triplon ou quadruplon ou quintuplon ou niktameruplon..)");
					// TODO pop-up d'erreur "il y a des reponses vide !".
					if (isEmpty)
						JOptionPane.showMessageDialog(null,
							    "<html>Impossible de valider vos réponses !<br/>- Il semble qu'au moins 1 réponse soit vide !</html>",
							    "Erreur",
							    JOptionPane.WARNING_MESSAGE);
						System.out.println("Erreur -> Des reponses sont carement vides !");
				} else {
					// On sauve l'etat actuel
					short i=0;
					short y=0;
					while (i<10 && reponses[i] != null) {
						current_quiz.getQuest(idQuest).getReponse(i).setTxtReponse(reponses[i].getText());
						current_quiz.getQuest(idQuest).getReponse(i).setStatutRep(statusRep[i].isSelected());
						if (statusRep[i].isSelected())
							y++;
						i++;
					}
					current_quiz.getQuest(idQuest).setNbr_reponses_juste(y);
					
					// ..puis on retourne sur la fenetre Creation_quiz
					Creation_quiz creation_quiz = new Creation_quiz(fenetre, current_quiz);
					fenetre.getContentPane().setVisible(false);
					creation_quiz.addMouseListener(creation_quiz);
					fenetre.setContentPane(creation_quiz);
					fenetre.getContentPane().setVisible(true);
				}
			}
		});
		add(bt_retour);
		
		/*
		 * aussi, NEED repartir les classes dans des packages differents (clarete++)
		 * ex:	Package CLASSES (les classes de DATA ex: quiz, question, joueur, etc)
		 * 		Package BTN (toutes les classes de boutons)
		 * 		Package TRAITEMENT (les classes de traitements comme celle-ci, creation_quiz etc.)
		 * 		Package SQL (tous les acces a la BDD)
		 */
		
		//****Inclusion du Header en 2 parties ****
        header1 = new Header(fen);
        header1.setBounds(0, 0, 444, 130);
        header1.addMouseListener(header1);
        header1.addMouseMotionListener(header1);
        this.add(header1); 
     

        header2 = new Header_menu(fen,this);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
		header1.setInCreateQuiz(true);
	}
	
	public void addReponse() {
		byte i = 0;
		while (i<10 && reponses[i] != null)
			i++;
		if (i<10) {
			reponses[i] = new JTextField("Write here..");
			reponses[i].setBounds(65+((i%2)*315), 330+((i/2)*70), 230, 23); // calcul dont je suis fier. (no need 'if')
			add(reponses[i]);
			addNbRep(i);
			addChkBox(i);
			addSuppr(i);
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, 
						"<html>Nombre limite de r&eacute;ponses atteint !<br/>Maximum : 10 r&eacute;ponses. </html>", 
						"Erreur dans l'ajout de reponse", 
						JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Ajoute un label qui numerote les question de A a J.
	 * @param i
	 */
	public void addNbRep(int i) {
		lb_nbRep[i] = new JLabel("<html>Reponse <b><span style='color:rgb(250,130,100);'>"+(questChar)+"</span>/</b></html>");
		lb_nbRep[i].setForeground(Color.WHITE);
		lb_nbRep[i].setFont(new Font("Arial", Font.PLAIN, 16)); 
		lb_nbRep[i].setBounds(50+((i%2)*315), 300+((i/2)*70), 200, 25);
		add(lb_nbRep[i]);
		questChar++;
	}
	
	/**
	 * Ajoute une checkbox a coter de la reponses pour definir si elle est juste ou non.
	 * @param i
	 */
	public void addChkBox(int i) {
		Reponse rep = current_quiz.getQuest(idQuest).addReponse(reponses[i].getText());
		statusRep[i] = new JCheckBox();
		statusRep[i].addActionListener(rep);
		statusRep[i].setBounds(35+((i%2)*315), 328+((i/2)*70), 25, 25);
		statusRep[i].setOpaque(false);
		add(statusRep[i]);
	}
	
	/**
	 * Dessine les croix de suppression des reponses.
	 * @param i
	 */
	public void addSuppr(int i) {
		bt_suppr[i] = new JLabel(""+i);
		ImageIcon btSuppr = new ImageIcon(Images.img_bouton[9]);
		bt_suppr[i].setIcon(btSuppr);
		bt_suppr[i].setBounds(300+((i%2)*315), 333+((i/2)*70), 20, 20);
		bt_suppr[i].addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				JLabel yo = (JLabel) e.getSource();
				int id_rep = Integer.parseInt(yo.getText());
				current_quiz.getQuest(idQuest).delReponse(id_rep);
				if (id_rep < 9) {
					while (id_rep<reponses.length-1 && reponses[id_rep+1] != null) {
						reponses[id_rep].setText(reponses[id_rep+1].getText());
						statusRep[id_rep].setSelected(statusRep[id_rep+1].isSelected());
						id_rep++;
					}
				}
				rmRepStatNum(id_rep);
				reponses[id_rep] = null;
				statusRep[id_rep] = null;
				bt_suppr[id_rep] = null;
				lb_nbRep[id_rep] = null;
				questChar--;
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		add(bt_suppr[i]);
	}
	
	public void rmRepStatNum(int id) {
		this.remove(reponses[id]);
		this.remove(statusRep[id]);
		this.remove(bt_suppr[id]);
		this.remove(lb_nbRep[id]);
		this.repaint();
	}
	
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public void itemStateChanged(ItemEvent arg0) {		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);		// dessine le header	
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
	}
}
