import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Bouton_selection_question_correction extends JButton implements MouseListener{
	private Quiz monQuiz;
	private int num_question;
	private Correction maCorr;
	private int aff_num_question;
	
	
	public Bouton_selection_question_correction(int _num_question,Quiz _quiz, Correction corr){
		monQuiz = _quiz;
		num_question = _num_question;
		maCorr= corr;
		aff_num_question = num_question+1;
		
		setText(""+aff_num_question);
		setBorder(null);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
	}
	
	public void AffQuestion(){
		//maCorr.setQuestion(question);
		//maCorr.repaint();
	}
	
	public void AffReponses(int i){
		//maCorr.setReponse(monQuiz.getQuest(num_question).getNb_reponses(), reponse[i].getTxtReponse());
		//maCorr.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		setBackground(Color.GRAY);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
