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
	// pour lancer le timer, faire timerQuiz.start(), et il execute le 'actionPerformed' au bout de 'tempsQuiz' milisecond.
	
	/**
	 * Constructor
	 * @param nom
	 */
	public Quiz(String nom, Fenetre fen) {
		fenetre = fen;
		nomQuiz = nom;
		nb_questions = 0;
		difficulteQuiz = "default";
		
		timerQuiz = new Timer(tempsQuiz, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("FIN DU TEMPS DE GAME !!!!");
			}
		});
		timerQuiz.setRepeats(false);
		// a partir d'ici, le timer est initialiser et pret a partir
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
	public Question getQuest(int i) {
		return questQuiz[i];
	}
	public void setDifficulteQuiz(String diff) {
		difficulteQuiz = diff;
	}
	public String getDifficulteQuiz() {
		return difficulteQuiz;
	}
	public void setId(int id) {
		id_quiz = id;
	}
	public int getId() {
		return id_quiz;
	}
	public void setNb_questions(int nb) {
		nb_questions = nb;
	}
	public int getNb_questions() {
		return nb_questions;
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
	
	/**
	 * Creation d'un nouvel objet Question (linked to this quiz)
	 * Called by 'Creation_quiz.java'
	 * on method 'public void addQuestion()'
	 * @param nomQuest
	 * @return
	 */
	public Question ajoutQuestion(String nomQuest) {
		int i = nextNull();
		questQuiz[i] = new Question(nomQuest, i, this, fenetre);
		nb_questions++;
		return questQuiz[i];
	}
}
