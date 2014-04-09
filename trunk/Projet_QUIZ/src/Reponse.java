import java.awt.Image;


public class Reponse {
	
	private Question question;
	private int id_reponse;
	private String txt_reponse;
	private Image img_reponse;
	private boolean statut_rep; // 1=bonne reponse	0=mauvaise reponse
	
	public Reponse(Question quest) {
		question = quest;
	}
}
