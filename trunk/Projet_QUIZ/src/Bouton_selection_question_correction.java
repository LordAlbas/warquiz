import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Bouton_selection_question_correction extends JButton implements MouseListener{
	private Quiz monQuiz;
	private int num_question;
	private Correction maCorr;
	private JLabel[] TabLabel;
	
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
			TabLabel[v] = new JLabel(monQuiz.getQuest(i).getReponse(v).getTxtReponse());
			/*
			TabLabel[v].setForeground(Color.RED);
			if (monQuiz.getQuest(i).getReponse(v).getStatutRep())
				TabLabel[v].setForeground(Color.GREEN);
			*/
		}
	}
	
	/**
	 * affiche le tableau de JLabel reponse CORRECTION
	 */
	// je m'en sert pas de cette methode, je la laisse pour si jamais on change de facon d'afficher.
	public void affLabel() {
		for (int i=0;i<TabLabel.length;i++) {
			TabLabel[i].setBounds(30, 30*i, 450, 50);
			TabLabel[i].setFont(new Font("Arial", Font.PLAIN, 20));
			maCorr.sousPanel.add(TabLabel[i]);
		}
	}
	
	public JLabel getTabLabel(int id) {
		return TabLabel[id];
	}
	
	public void mouseClicked(MouseEvent e) {
		maCorr.sousPanel.removeAll();											// vire tout
		maCorr.setQuestion(monQuiz.getQuest(num_question).getQuestTxt());		// affiche la question
		//affLabel();	// affiche les reponses correction (NO NEED)
		maCorr.affchckPlayed(num_question);										// affiche les reponses jouees
		setBackground(Color.GRAY);												// passe le bouton en gris
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
