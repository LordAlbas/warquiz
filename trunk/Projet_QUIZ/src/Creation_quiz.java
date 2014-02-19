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
	
	private Fenetre fenetre;
	private Admin_ajout_reponses admin_ajout_reponses;
	private Admin_ajout_questions admin_ajout_questions;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public Creation_quiz(Fenetre fen){
		setLayout(null);
		admin_ajout_reponses = new Admin_ajout_reponses(fenetre);
		admin_ajout_questions = new Admin_ajout_questions(fenetre);
		fenetre = fen;
		
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
				fenetre.getContentPane().setVisible(false);
				admin_ajout_questions.addMouseListener(admin_ajout_questions);
				fenetre.setContentPane(admin_ajout_questions);
				fenetre.getContentPane().setVisible(true);
			}
		});
		add(ajout_question);
		
		/*
		 * Bouton d'AJOUT REPONSES
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
	
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
	}
}
