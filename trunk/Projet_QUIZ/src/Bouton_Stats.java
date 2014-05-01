import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
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
	
	
	public Bouton_Stats(String texte) {
		txt = texte;
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

	public void mouseClicked(MouseEvent e) {
		if(diff == 0){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (short i=0; i<Statistiques_Admin.ListeQuizStats.length; i++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats[i].getNom());
			}
		}
		if(diff == 1){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (short i=0; i<Statistiques_Admin.ListeQuizStats_facile.length; i++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_facile[i].getNom());
			}
		}
		if(diff == 2){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (short i=0; i<Statistiques_Admin.ListeQuizStats_moyen.length; i++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_moyen[i].getNom());
			}
		}
		if(diff == 3){
			Statistiques_Admin.list_quiz_stats.removeAll();
			for (short i=0; i<Statistiques_Admin.ListeQuizStats_difficile.length; i++) {
				Statistiques_Admin.list_quiz_stats.add(Statistiques_Admin.ListeQuizStats_difficile[i].getNom());
			}
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
}
