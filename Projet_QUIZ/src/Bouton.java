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

public class Bouton extends JButton implements MouseListener {

	public static int diff;
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
	private String[] columnNames = {"Nom","Nombre de questions"};
	private ArrayList<String[]> Tab = new ArrayList<String[]>();
	Statistiques statistiques;
	
	
	public Bouton(String texte) {
		
		
		tableau_quiz = new ArrayList<String>();
		tableau_quiz.add("Test");
		String[] columnNames = { "Nom du Quiz", "Nombre de questions" }; // création
																			// des
																			// titres.
		// Object[][] data = {{"Quiz 1", "32"}};
		// Tableau = new JTable(data, columnNames);
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

		if(diff == 0){
			Jouer.list_quizcree.clear();
			for (short i=0; i<Jouer.ListeQuiz.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz[i].getNom());
			}
		}
		if(diff == 1){
			Jouer.list_quizcree.clear();
			for (short i=0; i<Jouer.ListeQuiz_facile.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz_facile[i].getNom());
			}
		}
		if(diff == 2){
			Jouer.list_quizcree.clear();
			for (short i=0; i<Jouer.ListeQuiz_moyen.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz_moyen[i].getNom());
			}
		}
		if(diff == 3){
			Jouer.list_quizcree.clear();
			for (short i=0; i<Jouer.ListeQuiz_difficile.length; i++) {
				Jouer.list_quizcree.add(Jouer.ListeQuiz_difficile[i].getNom());
			}
		}
		
		
		/*try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;"
							+ "database=BDD_B3I_groupe_5;"
							+ "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_quiz_diff = (Statement) conn.createStatement();
			if (diff == 0) {
				query_quiz_diff = "SELECT NOM_QUIZ, NB_QUESTION FROM QUIZ";
			} else {
				query_quiz_diff = "SELECT NOM_QUIZ, NB_QUESTION FROM QUIZ WHERE DIFFICULTE_QUIZ = "
						+ diff;
			}
			stmt_quiz_diff.executeQuery(query_quiz_diff);
			ResultSet rs_quiz_diff = stmt_quiz_diff.getResultSet();
			// int cpt_diff = 500;
			k=0;
			while (rs_quiz_diff.next()) {
				db_name_quiz_diff = rs_quiz_diff.getString("NOM_QUIZ"); // on récupère le nom
				db_nbquestion_quiz_diff = rs_quiz_diff.getString("NB_QUESTION"); // on récupère le nb de question
				// db_diff = rs_score_diff.getString("DIFFICULTE_QUIZ");
				// on récupère la difficulte
				quiz_diff = db_name_quiz_diff + " : " + db_nbquestion_quiz_diff + " questions";
				
				
				
				String[] inTab = { db_name_quiz_diff, db_nbquestion_quiz_diff };
				Tab.add(inTab);
				
				tableau_quiz.add(db_name_quiz_diff);
				//System.out.println(quiz_diff);
				
				
				//ajout des quiz dans tableau
				
				k++;
			}
			
			
			/*for(int v=0;v<tableau_quiz.size();v++){
				System.out.println(tableau_quiz.get(v));
			}
			*/
			
			/*Object[] data = Tab.toArray();
			Object[][] data2 = new Object[data.length][2];

			for (int i = 0; i < data.length; i++) {
				data2[i][0] = ((String[]) data[i])[0];
				data2[i][1] = ((String[]) data[i])[1];
			}
			
			Tableau = new Tableau(data2, columnNames);
			statistiques.AffTab(tableau_quiz);
			ok = true;
			
			repaint();
			//System.out.println(Tableau.getDonnees(0));
		} catch (ClassNotFoundException ee) {
			ee.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}*/
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
}
