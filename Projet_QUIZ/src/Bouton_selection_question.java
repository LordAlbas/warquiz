import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Bouton_selection_question extends JButton implements MouseListener{
	
	private Quiz quiz;
	private int num_question;
	private Jouer_partie partie_en_cours;
	private int aff_num_question;
	private String question;
	private Reponse[] reponse;

	
	public Bouton_selection_question(int _num_question, Quiz _quiz, Jouer_partie _partie){
		
		
		quiz = _quiz;
		num_question = _num_question;
		partie_en_cours = _partie;
		aff_num_question = num_question+1;
		System.out.println(quiz.getQuest(num_question).getQuestTxt());
		question = quiz.getQuest(num_question).getQuestTxt();
	
		
		setText(""+aff_num_question);
		setBorder(null);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
	}

	public void AffQuestion(){
		partie_en_cours.setQuestion(question);
		partie_en_cours.repaint();
	}
	/*
	public void AffReponses(int i){
		partie_en_cours.setReponse(quiz.getQuest(num_question).getNb_reponses(), reponse[i]);
		partie_en_cours.repaint();
	}
*/
	public void mouseClicked(MouseEvent arg0) {
		
		System.out.println("Clic sur :"+aff_num_question+" : "+question);
		setBackground(Color.GRAY);
		
		/*
		AffQuestion();
		for(int m=0;m<nbR;m++){
			AffReponses(m);
		}
		*/
	}
	
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
