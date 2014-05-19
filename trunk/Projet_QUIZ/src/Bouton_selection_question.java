import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;


public class Bouton_selection_question extends JButton implements MouseListener {
	
	private Quiz quiz;
	private int num_question;
	private Jouer_partie ma_partie;
	private JCheckBox[] TabCheck;
	
	/**
	 * Constructeur
	 * @param _num_question
	 * @param _quiz
	 * @param _partie
	 */
	public Bouton_selection_question(int _num_question, Quiz _quiz, Jouer_partie _partie){	
		quiz = _quiz;
		num_question = _num_question;
		ma_partie = _partie;
		TabCheck = createTabRep(num_question);
		
		setText(""+(num_question+1));
		setBorder(null);
		setBackground(new Color(23, 78, 116));
		setForeground(Color.WHITE);
	}
	
	/**
	 * Creer et renvoi un tableau de checkbox[nb_reponses].
	 * @param i
	 * @return tableau de JCheckBox
	 */
	public JCheckBox[] createTabRep(int i) {
		JCheckBox[] TabCheckRep = new JCheckBox[quiz.getQuest(i).getNb_reponses()];
		for (int v=0; v<TabCheckRep.length; v++) {
			TabCheckRep[v] = new JCheckBox("<html>"+quiz.getQuest(i).getReponse(v).getTxtReponse()+"</html>");
			TabCheckRep[v].setFont(new Font("Arial", Font.PLAIN, 18));
			TabCheckRep[v].setBackground(new Color(54, 90, 118));
			TabCheckRep[v].setForeground(Color.WHITE);
			TabCheckRep[v].setBounds((v%2)*ma_partie.sousPanel.getWidth()/2, 70*(v/2), ma_partie.sousPanel.getWidth()/2, 75);
		}
		return TabCheckRep;
	}
	
	/**
	 * Affichage du tableau cree ci-dessus dans la methode "createTabRep".
	 * @param tabrep
	 * @param num
	 */
	public void affCheckrep(JCheckBox[] tabrep, int num) {
		for (int i=0;i<quiz.getQuest(num).getNb_reponses();i++) {
			tabrep[i].setOpaque(false);
			ma_partie.sousPanel.add(tabrep[i]);
		}
	}
	
	/**
	 * Correspond a "mouseClicked()".
	 * Affiche le tableau des reponses de la question clicked
	 */
	public void switchQuest() {
		ma_partie.sousPanel.removeAll();
		affCheckrep(TabCheck, num_question);
		setBackground(Color.GRAY);
		ma_partie.setQuestion(quiz.getQuest(num_question).getQuestTxt());
		ma_partie.setNumQuest(num_question);
		ma_partie.repaint();
	}
	
	/**
	 * Verifie si la question a ete repondu.
	 * (s'arrete a la premiere reponse trouvee)
	 * @return
	 */
	public boolean isRepondu() {
		for (JCheckBox reponse : TabCheck) {
			if (reponse.isSelected())
				return true;
		}
		return false;
	}
	
	public JCheckBox getTabCheck(int id) {
		return TabCheck[id];
	}
	
	public int getTabCheckLength() {
		return TabCheck.length;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		switchQuest();
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
