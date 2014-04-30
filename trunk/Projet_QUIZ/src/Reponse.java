import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Reponse implements ActionListener {
	
	// donnees de la TABLE SQL
	private Question question;	// needed pour l'id_question
	private int id_reponse;		// unique seulement pour une Question (donc de 0 a 9 pour 10reponses)
	private String txt_reponse;
	private Image img_reponse;
	private boolean statut_rep; // 1=bonne reponse	0=mauvaise reponse
	
	/**
	 * Constructor
	 * @param quest
	 */
	public Reponse(String nomRep, int rangRep, Question quest) {
		question = quest;
		txt_reponse = nomRep;
		id_reponse = rangRep;
		statut_rep = false;
	}
	
	/**
	 * GETTERz / SETTERz
	 * 
	 */
	// id_reponse
	public int getIdReponse() { return id_reponse; }
	// txt_reponse
	public String getTxtReponse() { return txt_reponse; }
	public void setTxtReponse(String txt) { txt_reponse = txt; }
	// img_reponse
	public Image getImgReponse() { return img_reponse; }
	public void setImgReponse(Image img) { img_reponse = img; }
	// statut_rep
	public boolean getStatutRep() { return statut_rep; }
	public void setStatutRep(boolean strep) { statut_rep = strep; }
	// faire un 'getQuestion()' si besoin mais a priori no need.

	public void actionPerformed(ActionEvent e) {
		// On coche (ou decoche) la case, on change alors le statut de la reponse.
		statut_rep = (statut_rep) ? false : true;
	}
}
