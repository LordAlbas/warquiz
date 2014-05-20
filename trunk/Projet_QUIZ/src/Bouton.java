import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener,ActionListener {

	private int diff;
	private String txt;
	public ArrayList<String>tableau_quiz;
	private int k =0;
	public static boolean ok =false;
	Statistiques statistiques;
	
	
	public Bouton(String texte) {
		txt = texte;
		setLayout(null);
		addMouseListener(this);
		setText(texte);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 20));
		setBackground(new Color(7, 92, 158));
		setSize(115, 36);
		setBorder(null);
		setFocusPainted(false);

		switch (txt) {
		case "Tous":
			diff = 0;
			k=0;
			break;
		case "Facile":
			diff = 1;
			k=0;
			break;
		case "Moyen":
			diff = 2;
			break;
		case "Difficile":
			diff = 3;
			k=0;
			break;
		}
	}

	public void mouseClicked(MouseEvent e) {
		Statistiques.list_quiz_stats_user.removeAll();
		switch (txt){
			case "Tous":
				for (int i=0; i<Statistiques.ListeQuizStats_user.length; i++) {
					Statistiques.list_quiz_stats_user.add(Statistiques.ListeQuizStats_user[i].getNom());
				}
				break;
			case "Facile":
				for (int j=0; j<Statistiques.ListeQuizStats_facile_user.length; j++) {
					if (Statistiques.ListeQuizStats_facile_user[j] != null)
						Statistiques.list_quiz_stats_user.add(Statistiques.ListeQuizStats_facile_user[j].getNom());
					
				}
				break;
			case "Moyen":
				for (int k=0; k<Statistiques.ListeQuizStats_moyen_user.length; k++) {
					if (Statistiques.ListeQuizStats_moyen_user[k] != null)
						Statistiques.list_quiz_stats_user.add(Statistiques.ListeQuizStats_moyen_user[k].getNom());
				}
				break;
			case "Difficile":
				for (int l=0; l<Statistiques.ListeQuizStats_difficile_user.length; l++) {
					if (Statistiques.ListeQuizStats_difficile_user[l] != null)
						Statistiques.list_quiz_stats_user.add(Statistiques.ListeQuizStats_difficile_user[l].getNom());
				}
				break;		
		}
	}
	
	public String getQuiz(int i){
		return tableau_quiz.get(i);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(diff == 0){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz[i].getNom()+" - [ "+Jouer.ListeQuiz[i].getNb_questions()+" question(s) ] - Temps : " + Jouer.ListeQuiz[i].getHeureQuiz() + "h "+ Jouer.ListeQuiz[i].getMinuteQuiz() + "m " + Jouer.ListeQuiz[i].getSecondeQuiz() +"s ");
			}
		}
		if(diff == 1){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz_facile.length; i++) {
				if (Jouer.ListeQuiz_facile[i] != null)
					Jouer.list_quizcree.add(Jouer.ListeQuiz_facile[i].getNom()+" - [ "+Jouer.ListeQuiz_facile[i].getNb_questions()+" question(s) ] - Temps : " + Jouer.ListeQuiz_facile[i].getHeureQuiz() + "h "+ Jouer.ListeQuiz_facile[i].getMinuteQuiz() + "m " + Jouer.ListeQuiz_facile[i].getSecondeQuiz() +"s ");
			}
		}
		if(diff == 2){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz_moyen.length; i++) {
				if (Jouer.ListeQuiz_moyen[i] != null)
					Jouer.list_quizcree.add(Jouer.ListeQuiz_moyen[i].getNom()+" - [ "+Jouer.ListeQuiz_moyen[i].getNb_questions()+" question(s) ] - Temps : " + Jouer.ListeQuiz_moyen[i].getHeureQuiz() + "h "+ Jouer.ListeQuiz_moyen[i].getMinuteQuiz() + "m " + Jouer.ListeQuiz_moyen[i].getSecondeQuiz() +"s ");
			}
		}
		if(diff == 3){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz_difficile.length; i++) {
				if (Jouer.ListeQuiz_difficile[i] != null)
				Jouer.list_quizcree.add(Jouer.ListeQuiz_difficile[i].getNom()+" - [ "+Jouer.ListeQuiz_difficile[i].getNb_questions()+" question(s) ] - Temps : " + Jouer.ListeQuiz_difficile[i].getHeureQuiz() + "h "+ Jouer.ListeQuiz_difficile[i].getMinuteQuiz() + "m " + Jouer.ListeQuiz_difficile[i].getSecondeQuiz() +"s ");
			}
		}
	}
}
