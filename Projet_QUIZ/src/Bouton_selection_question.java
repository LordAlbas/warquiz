import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;


public class Bouton_selection_question extends JButton implements MouseListener{
	
	private Quiz quiz;
	private int num_question;
	private Jouer_partie ma_partie;
	private int aff_num_question;
	private String question;
	private Reponse[] reponse;
	private JCheckBox[] TabCheck;
	
	public Bouton_selection_question(int _num_question, Quiz _quiz, Jouer_partie _partie){	
		quiz = _quiz;
		num_question = _num_question;
		ma_partie = _partie;
		aff_num_question = num_question+1;
		TabCheck = ma_partie.createTabRep(num_question);
		question = quiz.getQuest(num_question).getQuestTxt();
		reponse = new Reponse[quiz.getQuest(num_question).getNb_reponses()];
		
		setText(""+aff_num_question);
		setBorder(null);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
		
		for(int l=0; l<quiz.getQuest(num_question).getNb_reponses();l++){
			reponse[l] = quiz.getQuest(num_question).getReponse(l);
		}	
	}

	public void AffQuestion(){
		ma_partie.setQuestion(question);
		ma_partie.repaint();
	}
	
	/*public void AffReponses(int i){
		ma_partie.setReponse(quiz.getQuest(num_question).getNb_reponses(), reponse[i].getTxtReponse());
		ma_partie.repaint();
	}*/

	public void mouseClicked(MouseEvent arg0) {
		ma_partie.sousPanel.removeAll();
		ma_partie.affCheckrep(TabCheck, num_question);
		AffQuestion();
		setBackground(Color.GRAY);
	}
	
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
