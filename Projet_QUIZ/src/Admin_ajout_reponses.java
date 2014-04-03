import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.List;


public class Admin_ajout_reponses extends JPanel  implements MouseListener, ItemListener{
	
	private Fenetre fenetre;
	private Quiz current_quiz;
	
	private JLabel txf_nomQuiz;
	private JLabel txf_nomQuest;
	
	private List list_reponses;
	static JTextField reponse;
	
	private JButton bt_retour;
	
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
		
		// creation du titre qui recupere le nom du Quiz (avec son JLabel qui va bien)
		JLabel lb_nomQuiz = new JLabel("Nom du Quiz : ");
		lb_nomQuiz.setForeground(Color.WHITE);
		lb_nomQuiz.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_nomQuiz.setBounds(20, 150, 140, 30);
		add(lb_nomQuiz);
		txf_nomQuiz = new JLabel(current_quiz.getNom());	// recupere le nom du quiz
		txf_nomQuiz.setForeground(Color.WHITE);
		txf_nomQuiz.setFont(new Font("Arial", Font.BOLD, 22));
		txf_nomQuiz.setBounds(160, 150, 500, 30);
		add(txf_nomQuiz);
		
		// creation du sous-titre qui recupere le nom de la question (avec son JLabel qui va bien)
		JLabel lb_nomQuest = new JLabel("Nom de la question : ");
		lb_nomQuest.setForeground(Color.WHITE);
		lb_nomQuiz.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_nomQuest.setBounds(20, 180, 140, 30);
		add(lb_nomQuest);
		txf_nomQuest = new JLabel(monQuiz.getQuest(numQuest).getQuestTxt());	// recupere le nom de la question
		txf_nomQuest.setForeground(Color.WHITE);
		txf_nomQuest.setFont(new Font("Arial", Font.BOLD, 16));
		txf_nomQuest.setBounds(160, 180, 500, 30);
		add(txf_nomQuest);
		
		// creation de la liste de reponses (type List)
		list_reponses = new List();
		list_reponses.setBounds(171, 312, 188, 319);
		list_reponses.addItemListener(this);
		add(list_reponses);
		
		short y = 0;
		while (y<10 && current_quiz.getQuest(numQuest).getReponse(y) != null) {
			System.out.println("Rep = "+current_quiz.getQuest(numQuest).getReponse(y));
			System.out.println("Add in List -> "+y);
			list_reponses.add(current_quiz.getQuest(numQuest).getReponse(y));
			y++;
		}
		
		//list_reponses.add("R�ponse A");
		
		reponse = new JTextField();
		reponse.setBounds(171, 247, 188, 20);
		reponse.setText("blabla");
		add(reponse);
		reponse.setColumns(10);
		
		// bouton d'ajout d'une reponse dans la liste
		Bouton_ajout_reponse bouton_addRep = new Bouton_ajout_reponse("Ajouter", list_reponses);
		bouton_addRep.setBounds(365, 246, 89, 23);
		add(bouton_addRep);
		
		/* plzzzz fait toi plaiz et netoie tout le code d'ajout et suppression des reponses !!!
		 * NEXT STEP c'est ca qu'il faut faire ->> gerer tout ce qui est QUIZ/QUESTION/REPONSES dans leurs classes correspondante
		 * et absolument PAS dans les classes de traitemets (ex: Admin_ajout_reponses / Bouton_suppr/ajout_reponse etc.)
		 * GO GO GO !!
		 */
		
		// bouton de suppression d'une reponse de la liste
		Bouton_suppr_reponse bouton_suppr = new Bouton_suppr_reponse("Supprimer", list_reponses);
		bouton_suppr.setBounds(365, 312, 89, 23);
		add(bouton_suppr);
		
		bt_retour = new JButton("Retour au Quiz");
		bt_retour.setBounds(700, 700, 130, 40);
		bt_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on retourne sur la fenetre Creation_quiz
				Creation_quiz creation_quiz = new Creation_quiz(fenetre, current_quiz);
				fenetre.getContentPane().setVisible(false);
				creation_quiz.addMouseListener(creation_quiz);
				fenetre.setContentPane(creation_quiz);
				fenetre.getContentPane().setVisible(true);
			}
		});
		add(bt_retour);
		
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
	
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent e) {
		//System.out.println(list.locationToIndex(e.getPoint()));
		//System.out.println(list.getSelectedValue());
		//System.out.println(list.getSelectedItem());
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public void itemStateChanged(ItemEvent arg0) {		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		//g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		
	}
}
