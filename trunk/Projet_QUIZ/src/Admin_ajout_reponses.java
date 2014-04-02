import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.List;


public class Admin_ajout_reponses extends JPanel  implements MouseListener, ItemListener{
	
	private Fenetre fenetre;
	private Quiz current_quiz;
	
	private JTextField txf_nomQuiz;
	private JTextField txf_nomQuest;
	
	private JTextField textField_1;
	private JTextField textField_2;
	private List list_reponses;
	static JTextField reponse;
	
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
		txf_nomQuiz = new JTextField();
		txf_nomQuiz.setBounds(150, 150, 493, 30);
		txf_nomQuiz.setText(current_quiz.getNom());		// recupere le nom du Quiz
		add(txf_nomQuiz);
		txf_nomQuiz.setColumns(10);		// Ca sert a quoi ? rep svp
		
		// creation du sous-titre qui recupere le nom de la question (avec son JLabel qui va bien)
		JLabel lb_nomQuest = new JLabel("Nom de la question : ");
		lb_nomQuest.setForeground(Color.WHITE);
		lb_nomQuiz.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_nomQuest.setBounds(20, 180, 140, 30);
		add(lb_nomQuest);
		txf_nomQuest = new JTextField();
		txf_nomQuest.setBounds(150, 180, 493, 30);
		txf_nomQuest.setText(monQuiz.getQuest(numQuest));		// recupere le nom de la question
		add(txf_nomQuest);
		
		// creation de la liste de reponses (type List)
		list_reponses = new List();
		list_reponses.setBounds(171, 312, 188, 319);
		list_reponses.addItemListener(this);
		add(list_reponses);
		
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
		
		// bouton de suppression d'une reponse de la liste
		Bouton_suppr_reponse bouton_suppr = new Bouton_suppr_reponse("Supprimer", list_reponses);
		bouton_suppr.setBounds(365, 312, 89, 23);
		add(bouton_suppr);
		
		JLabel lblRponse = new JLabel("R�ponse");
		lblRponse.setText("bloublou");
		lblRponse.setBounds(171, 224, 46, 14);
		add(lblRponse);
		
		JLabel lblA = new JLabel("A");
		lblA.setBounds(145, 250, 23, 14);
		add(lblA);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setBounds(102, 122, 46, 14);
		add(lblQuestion);
		
		//list.addMouseListener(items);
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
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		//g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		
	}
}
