import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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

public class Bouton_Stats extends JButton implements MouseListener {

	private int diff;
	private String txt;
	public ArrayList<String>tableau_quiz;
	
	public Bouton_Stats(String texte) {
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
			break;
		case "Facile":
			diff = 1;
			break;
		case "Moyen":
			diff = 2;
			break;
		case "Difficile":
			diff = 3;
			break;
		}
	}

	public String getQuiz(int i){
		return tableau_quiz.get(i);
	}
	
	public void mouseClicked(MouseEvent e) {
		Statistiques_Admin.list_quiz_stats.removeAll();
		switch (txt){	
			case "Tous":
				for (int i=0; i<Statistiques_Admin.ListeQuizStats.length; i++) {
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats[i].getNom());
				}
				break;
			case "Facile":
				for (int j=0; j<Statistiques_Admin.ListeQuizStats_facile.length; j++) {
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_facile[j].getNom());
				}
				break;
			case "Moyen":
				for (int k=0; k<Statistiques_Admin.ListeQuizStats_moyen.length; k++) {
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_moyen[k].getNom());
				}
				break;
			case "Difficile":
				for (int l=0; l<Statistiques_Admin.ListeQuizStats_difficile.length; l++) {
					Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_difficile[l].getNom());
				}
				break;		
		}
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
	public void actionPerformed(ActionEvent e) {
		System.out.println(diff);
		if(diff==0){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (int i=0; i<Statistiques_Admin.ListeQuizStats.length; i++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats[i].getNom());
			}
		}
		else if(diff==1){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (int j=0; j<Statistiques_Admin.ListeQuizStats_facile.length; j++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_facile[j].getNom());
			}
		}
		else if(diff==2){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (int k=0; k<Statistiques_Admin.ListeQuizStats_moyen.length; k++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_moyen[k].getNom());
			}
		}
		else if(diff==3){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (int l=0; l<Statistiques_Admin.ListeQuizStats_difficile.length; l++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_difficile[l].getNom());
			}
		}
	}
	
}
