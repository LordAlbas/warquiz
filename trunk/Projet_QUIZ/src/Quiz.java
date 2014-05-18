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
	private String heureQuiz = "";	
	private String minuteQuiz = "";
	private String secondeQuiz = "";// ceci est le temps limite du quiz
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
	}
	
	/**
	 * Getters / Setters
	 */
	// nomQuiz
	public void setNom(String nom) { nomQuiz = nom; }
	public String getNom() { return nomQuiz; }
	// difficulteQuiz
	public void setDifficulteQuiz(String diff) { difficulteQuiz = diff; }
	public String getDifficulteQuiz() { return difficulteQuiz; }
	// difficulteQuizInt
	public int getDifficulteQuizInt() {
		switch (difficulteQuiz) {
		case "facile":
			return 1;
		case "moyen":
			return 2;
		case "difficile":
			return 3;
		default:
			return 0;
		}
	}
	// login_admin
	public void setLoginAdmin(String logAdmin) { login_admin = logAdmin; }
	public String getLoginAdmin() { return login_admin; }
	// id_quiz
	public void setId(int id) { id_quiz = id; }
	public int getId() { return id_quiz; }
	// categorieQuiz
	public void setCategorieQuiz(String cat) { categorieQuiz = cat; }
	public String getCategorieQuiz() { return categorieQuiz; }
	// nb_questions
	public void setNb_questions(int nb) { nb_questions = nb; }
	public int getNb_questions() { return nb_questions; }
	// tempsQuiz
	public void setHeureQuiz(String heure) { heureQuiz = heure; }
	public String getHeureQuiz() { return heureQuiz; }
	public void setMinuteQuiz(String minute) { minuteQuiz = minute; }
	public String getMinuteQuiz() { return minuteQuiz; }
	public void setSecondeQuiz(String seconde) { secondeQuiz = seconde; }
	public String getSecondeQuiz() { return secondeQuiz; }
	// questQuiz[i]
	public Question getQuest(int i) { return questQuiz[i]; }
	
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
		if (i<20) {
			questQuiz[i] = new Question(nomQuest, i, this, fenetre);
			nb_questions++;
			return questQuiz[i];
		} else {
			return null;
		}
	}
	
	public void delQuestion(int id) {
		if (id < 19) {
			while (id<questQuiz.length-1 && questQuiz[id+1] != null) {
				questQuiz[id] = questQuiz[id+1];
				questQuiz[id].setIdQuestion(questQuiz[id].getIdQuestion()-1);
				id++;
			}
		}
		questQuiz[id] = null;
		nb_questions--;
	}
}
