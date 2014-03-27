import javax.swing.JButton;


public class Quiz {
	
	private String nomQuiz;
	private String[] questQuiz = new String[20];
	
	/**
	 * Constructor
	 * @param nom
	 */
	public Quiz(String nom) {
		nomQuiz = nom;
		
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
		questQuiz[i] = txt;
	}
	public String getQuest(int i) {
		return questQuiz[i];
		// !! Line 64 in Admin_ajout_rep !! returns the last question instead of the current one!
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
	
	public void ajoutQuestion(String nomQuest) {
		questQuiz[nextNull()] = nomQuest;
	}
}
