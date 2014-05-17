import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Bouton_selection_question_correction extends JButton implements MouseListener{
	private Quiz monQuiz;
	private int num_question;
	private Correction maCorr;
	private int aff_num_question;
	private Reponse[]reponse;
	private String questionTxt;
	private JLabel[]TabLabel;
	
	public Bouton_selection_question_correction(int _num_question,Quiz _quiz, Correction corr){
		monQuiz = _quiz;
		num_question = _num_question;
		maCorr= corr;
		aff_num_question = num_question+1;
		
		setText(""+aff_num_question);
		setBorder(null);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);

		TabLabel = maCorr.createTab(num_question);
			
		questionTxt = monQuiz.getQuest(num_question).getQuestTxt();
		reponse = new Reponse[monQuiz.getQuest(num_question).getNb_reponses()];
		for(int i=0;i<monQuiz.getQuest(num_question).getNb_reponses();i++){
			reponse[i] = monQuiz.getQuest(num_question).getReponse(i);
		}
		

		
		
	}
	
	public void AffQuestion(){
		maCorr.setQuestion(questionTxt);
		maCorr.repaint();
	}


	
	public void mouseClicked(MouseEvent e) {
		maCorr.sousPanel.removeAll();
		maCorr.affLabel(TabLabel, num_question);
		
		
		AffQuestion();
		/*
		for (int k=0;k<monQuiz.getQuest(num_question).getNb_reponses();k++){
			AffReponses(k);
		}
		*/
		setBackground(Color.GRAY);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
