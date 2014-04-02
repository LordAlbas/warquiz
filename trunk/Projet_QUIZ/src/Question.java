import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Question implements ActionListener {
	private Fenetre fenetre;
	private Admin_ajout_reponses admin_ajout_reponses;
	private Quiz currentQuiz;
	private String QuestTxt;
	private int QuestNum;
	
	public Question(String nomQuest, int i, Quiz curQuiz, Fenetre fen) {
		fenetre = fen;
		currentQuiz = curQuiz;
		QuestTxt = nomQuest;
		QuestNum = i;
	}
	
	public String getQuestTxt() {
		return QuestTxt;
	}
	public void setQuestTxt(String txt) {
		QuestTxt = txt;
	}
	
	public void actionPerformed(ActionEvent e) {
		admin_ajout_reponses = new Admin_ajout_reponses(fenetre, currentQuiz, QuestNum);
		//System.out.println("CLICK SUR QUEST num -> "+QuestNum);
		fenetre.getContentPane().setVisible(false);
		admin_ajout_reponses.addMouseListener(admin_ajout_reponses);
		fenetre.setContentPane(admin_ajout_reponses);
		fenetre.getContentPane().setVisible(true);
	}

}
