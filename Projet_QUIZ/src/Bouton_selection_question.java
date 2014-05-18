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
	private JCheckBox[] TabCheck;
	
	public Bouton_selection_question(int _num_question, Quiz _quiz, Jouer_partie _partie){	
		quiz = _quiz;
		num_question = _num_question;
		ma_partie = _partie;
		TabCheck = ma_partie.createTabRep(num_question);				// ICI ------
		
		setText(""+(num_question+1));
		setBorder(null);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
	}

	public void mouseClicked(MouseEvent arg0) {
		ma_partie.sousPanel.removeAll();
		ma_partie.affCheckrep(TabCheck, num_question);					// ICI -------
		ma_partie.setQuestion(quiz.getQuest(num_question).getQuestTxt());
		setBackground(Color.GRAY);
	}
	
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
