import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Bouton_selection_question_correction extends JButton implements MouseListener{
	private Quiz monQuiz;
	private int num_question;
	private Correction maCorr;
	private JLabel[] TabLabel;
	private int etatBouton;			// 0 = pas vu (bleu)	1 = vu (gris)
	
	public Bouton_selection_question_correction(int _num_question, Quiz _quiz, Correction _corr){
		monQuiz = _quiz;
		num_question = _num_question;
		maCorr = _corr;
		
		setText(""+(num_question+1));
		setBorder(null);
		setBackground(new Color(54, 90, 118));
		setForeground(Color.WHITE);
		
		createTab(num_question);
	}
	
	/**
	 * Creation du tableau de JLabel reponse CORRECTION
	 * @param i
	 * @return
	 */
	public void createTab(int i) {
		TabLabel = new JLabel[monQuiz.getQuest(i).getNb_reponses()];
		
		for (int v=0; v<TabLabel.length; v++) {
			TabLabel[v] = new JLabel("<html>"+monQuiz.getQuest(i).getReponse(v).getTxtReponse()+"<html>");
			TabLabel[v].setSize(80, 300);
		}
	}
	
	public JLabel getTabLabel(int id) {
		return TabLabel[id];
	}
	
	public int getEtatBouton() {
		return etatBouton;
	}
	
	public void mouseClicked(MouseEvent e) {
		maCorr.sousPanel.removeAll();											// vire tout
		maCorr.setQuestion(monQuiz.getQuest(num_question).getQuestTxt());		// affiche la question
		maCorr.setNumQuest(num_question);
		maCorr.affchckPlayed(num_question);										// affiche les reponses jouees
		etatBouton = 1;												// passe le bouton en gris
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
