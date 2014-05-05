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
	private Question objQuestion;
	private String[] reponse;
	int nbR;
	
	public Bouton_selection_question(int _num_question, Quiz _quiz, Jouer_partie _partie){
		
		
		quiz = _quiz;
		num_question = _num_question;
		partie_en_cours = _partie;
		aff_num_question = num_question+1;
		objQuestion = quiz.getQuest(num_question);
		/*
		question = new Question[quiz.getNb_questions()];
		//on récupère toutes les questions du quiz
		for(int q=0; q<quiz.getNb_questions();q++){
			question[q] = quiz.getQuest(q);
			
			reponse = new Reponse[quiz.getNb_questions()][question[q].getNb_reponses()];
			for(int k=0;k<question[q].getNb_reponses();k++){
				reponse[q][k] = question[q].getReponse(k);
			}
		}
		*/
		nbR =  quiz.getQuest(num_question).getNb_reponses()/2;
		reponse = new String [nbR];
		
		question = quiz.getQuest(num_question).getQuestTxt();
		for(int q=0;q<nbR;q++){
			reponse[q] = quiz.getQuest(num_question).getReponse(q).getTxtReponse();
		}
		
		
		setText(""+aff_num_question);
		setBorder(null);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
	}

	public void AffQuestion(){
		partie_en_cours.setQuestion(question);
		partie_en_cours.repaint();
	}
	
	public void AffReponses(int i){
		partie_en_cours.setReponse(/*quiz.getQuest(num_question).getNb_reponses()*/nbR, reponse[i]);
		partie_en_cours.repaint();
	}

	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Clic sur :"+aff_num_question+" : "+question);
		setBackground(Color.GRAY);
		
		AffQuestion();
		for(int m=0;m<nbR;m++){
			AffReponses(m);
		}
		
	}
	
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
