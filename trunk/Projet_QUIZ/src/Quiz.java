import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;




public class Quiz {
	private Fenetre fenetre;
	private Question[] questQuiz = new Question[20];
	
	// donnees de la TABLE SQL
	private int id_quiz;
	private int nb_questions;
	private String login_admin;
	private String nomQuiz;
	private String difficulteQuiz;
	private String categorieQuiz;
	private int tempsQuiz = 5000;	// ceci est le temps limite du quiz
	public Timer timerQuiz; // cela est juste l'objet timer qui permet de decompter jusqu'au tempsQuiz.
	// pour lancer le timer, faire timerQuiz.start(), et il execute le actionPerformed au bout de tempsQuiz milisecond.
	
	/**
	 * Constructor
	 * @param nom
	 */
	public Quiz(String nom, Fenetre fen) {
		fenetre = fen;
		nomQuiz = nom;
		
		timerQuiz = new Timer(tempsQuiz, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("FIN DU TEMPS DE GAME !!!!");
			}
		});
		timerQuiz.setRepeats(false);
	}
	
	/**
	 * Getters / Setters
	 * @param nom
	 */
	public void setNom(String nom) {
		nomQuiz = nom;
	}
	public String getNom() {
		return nomQuiz;
	}
	public void setQuest(String txt, int i) {
		questQuiz[i].setQuestTxt(txt);
	}
	public Question getQuest(int i) {
		return questQuiz[i];
	}
	
	/**
	 * trouve la prochaine case de question vide.
	 * @return
	 */
	public int nextNull() {
		int i = 0;
		while(i<20 && questQuiz[i] != null) {
			i++;
		}
		return i;
	}
	
	public Question ajoutQuestion(String nomQuest) {
		int i = nextNull();
		questQuiz[i] = new Question(nomQuest, i, this, fenetre);
		return questQuiz[i];
	}
}
