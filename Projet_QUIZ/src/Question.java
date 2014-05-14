import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Question implements ActionListener {
	private Fenetre fenetre;
	private Admin_ajout_reponses admin_ajout_reponses;
	
	// donnees de la TABLE SQL
	private Quiz currentQuiz; // needed pour l'id_quiz
	private int id_question;  // unique seulement pour un quiz (donc de 0 a 9 pour 10questions)
	private String txt_question;
	private Image img_question;
	private int nb_reponses;
	private int nbr_reponses_juste;
	
	private Reponse[] reponses = new Reponse[10];
	
	public Question(String nomQuest, int i, Quiz curQuiz, Fenetre fen) {
		fenetre = fen;
		currentQuiz = curQuiz;
		txt_question = nomQuest;
		id_question = i;
		nb_reponses = 0;
		nbr_reponses_juste = 0;
	}
	
	/**
	 * Getters / Setters
	 */
	// id_question
	public int getIdQuestion() { return id_question; }
	public void setIdQuestion(int id) { id_question = id; }
	// txt_question
	public String getQuestTxt() { return txt_question; }
	public void setQuestTxt(String txt) { txt_question = txt; }
	// nb_reponses
	public int getNb_reponses() { return nb_reponses; }
	public void setNb_reponses(int nb) { nb_reponses = nb; }
	// nbr_reponses_juste
	public int getNbr_reponses_juste() { return nbr_reponses_juste; }
	public void setNbr_reponses_juste(int nbj) { nbr_reponses_juste = nbj; }
	// img_question
	public Image getImgQuestion() { return img_question; }
	public void setImgQuestion(Image img) { img_question = img; }
	// reponses[]
	public Reponse getReponse(int i) { return reponses[i]; }
	
	public Reponse addReponse(String rep) {
		int i = 0;
		while(i<10 && reponses[i] != null)
			i++;
		if (i<10) {
			reponses[i] = new Reponse(rep, i, this);
			nb_reponses++;
			return reponses[i];
		} else {
			return null;
		}
	}
	
	/**
	 * Supprime une reponse et decale toutes les suivantes.
	 * @param i
	 */
	public void delReponse(int i) {
		if (i < 9) {
			while (i<reponses.length-1 && reponses[i+1] != null) {
				reponses[i] = reponses[i+1];
				reponses[i].setIdReponse(reponses[i].getIdReponse() - 1);
				i++;
			}
		}
		reponses[i] = null;
		nb_reponses--;
	}
	
	public void actionPerformed(ActionEvent e) {
		admin_ajout_reponses = new Admin_ajout_reponses(fenetre, currentQuiz, id_question);
		//System.out.println("CLICK SUR QUEST num -> "+QuestNum);
		fenetre.getContentPane().setVisible(false);
		admin_ajout_reponses.addMouseListener(admin_ajout_reponses);
		fenetre.setContentPane(admin_ajout_reponses);
		fenetre.getContentPane().setVisible(true);
	}

}
