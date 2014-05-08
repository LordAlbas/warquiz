import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Bouton extends JButton implements MouseListener,ActionListener {

	private int diff;
	private String db_quiz_diff;
	private String query_quiz_diff;
	private String quiz_diff;
	private String db_name_quiz_diff;
	private String db_nbquestion_quiz_diff;
	private String txt;
	public static Tableau Tableau;
	private Object[][] data;
	public ArrayList<String>tableau_quiz;
	private int k =0;
	public static boolean ok =false;
	Statistiques statistiques;
	
	
	public Bouton(String texte) {
		txt = texte;
		System.out.println(txt);
		setLayout(null);
		addMouseListener(this);
		setText(texte);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 20));
		setBackground(new Color(7, 92, 158));
		setSize(80, 36);
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
		System.out.println(diff);
	}

	public void mouseClicked(MouseEvent e) {

		
		//Statistiques.list_quiz_stats.removeAll();
		
		switch (txt){
			case "Tous":
				
				for (int i=0; i<Statistiques_Admin.ListeQuizStats.length; i++) {
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats[i].getNom());
				}
				break;
			case "Facile":
				//Statistiques_Admin.list_quiz_stats.removeAll();
				System.out.println("taille FACILE = "+Statistiques_Admin.ListeQuizStats_facile.length);
				for (int j=0; j<Statistiques_Admin.ListeQuizStats_facile.length; j++) {
					
					System.out.println("Nom = "+Statistiques_Admin.ListeQuizStats_facile[j].getNom());
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_facile[j].getNom());
					
				}
				break;
			case "Moyen":
				//Statistiques_Admin.list_quiz_stats.removeAll();
				System.out.println("taille MOYEN = "+Statistiques_Admin.ListeQuizStats_moyen.length);
				for (int k=0; k<Statistiques_Admin.ListeQuizStats_moyen.length; k++) {
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_moyen[k].getNom());
				}
				break;
			case "Difficile":
				//Statistiques_Admin.list_quiz_stats.removeAll();
				System.out.println("taille DIFFICILE = "+Statistiques_Admin.ListeQuizStats_difficile.length);
				for (int l=0; l<Statistiques_Admin.ListeQuizStats_difficile.length; l++) {

					System.out.println("l = "+l);
					System.out.println(Statistiques_Admin.ListeQuizStats_difficile[l].getNom());
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_difficile[l].getNom());
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
		// g.drawImage(Images.img_bouton[8], 0, 0,getWidth(),getHeight(), null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(diff);
		if(diff == 0){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz[i].getNom());
			}
		}
		if(diff == 1){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz_facile.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz_facile[i].getNom());
			}
		}
		if(diff == 2){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz_moyen.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz_moyen[i].getNom());
			}
		}
		if(diff == 3){
			Jouer.list_quizcree.removeAll();
			for (short i=0; i<Jouer.ListeQuiz_difficile.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz_difficile[i].getNom());
			}
		}
	}
}
