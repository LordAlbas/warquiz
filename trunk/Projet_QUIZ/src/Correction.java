import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Correction extends JPanel implements MouseListener{
	private Quiz monQuiz;
	private Header header1;
	private Header_menu header2;
	private JLabel lb_titreCorrection;
	private Bouton_selection_question_correction boutonQuestion[];
	private JLabel question;
	private JButton terminer;
	private Fenetre fenetre;
	private JLabel titreQuiz;
	private JLabel bon;
	private JLabel mauvais;
	private JLabel lb_numQuest;
	public JPanel sousPanel;
	private int monScore;
	private long monTemps;		// contient le temps de jeu de la partie en milliseconde.
	private Bouton_selection_question[] chbx_tabRep;	// CONTIENT les reponses jouee par le user (a comparer avec monQuiz).
	
	public Correction (Quiz quiz, Bouton_selection_question[] user_tabRep, int score, long temps, Fenetre fen) {
		setLayout(null);
		fenetre = fen;
		monQuiz = quiz;					// le quiz
		chbx_tabRep = user_tabRep;		// les reponses du user
		monScore = score;
		monTemps = temps;
		
		boutonQuestion = new Bouton_selection_question_correction[monQuiz.getNb_questions()];
		menuQuetions();	// Creation des boutons de question en haut a droite
		
		/*
		 * Nom du quiz
		 */
		lb_titreCorrection = new JLabel("<html><u>Correction</u></html>");								// label "Correction"
		lb_titreCorrection.setForeground(Images.couleurLabel);
		lb_titreCorrection.setFont(new Font("Arial", Font.BOLD, 35));
		lb_titreCorrection.setBounds(600, 130, 400, 50);
		add(lb_titreCorrection);
		JLabel lb_nomQuiz = new JLabel("Quiz ["+monQuiz.getDifficulteQuiz()+"]");	// Quiz [difficulte]
		lb_nomQuiz.setFont(new Font("Arial", Font.PLAIN, 24));
		lb_nomQuiz.setForeground(Images.couleurLabel);
		lb_nomQuiz.setBounds(600, 100, 400, 50);
        add(lb_nomQuiz);
		JLabel lb_titrePartie = new JLabel("<html>"+monQuiz.getNom()+"</html>");	// Nom du quiz (sur 2 lignes max)
		lb_titrePartie.setForeground(Color.WHITE);
		lb_titrePartie.setFont(new Font("Arial", Font.PLAIN, 35));
		lb_titrePartie.setBounds(660, 185, 400, 80);
		add(lb_titrePartie);
		
		/*
		 * Affichage du SCORE
		 */
		JLabel lb_score = new JLabel("<html>Votre score :</html>");
		lb_score.setForeground(Images.couleurLabel);
		lb_score.setFont(new Font("Arial", Font.PLAIN, 35));
		lb_score.setBounds(760, 300, 400, 40);
		add(lb_score);
		JLabel lb_scorePartie = new JLabel("<html>"+monScore+" / 100</html>");			// SCORE sur 100
		lb_scorePartie.setForeground(Color.WHITE);
		lb_scorePartie.setHorizontalAlignment(SwingConstants.CENTER);
		lb_scorePartie.setFont(new Font("Arial", Font.BOLD, 40));
		lb_scorePartie.setBounds(790, 350, 180, 60);
		add(lb_scorePartie);
		JLabel lb_tempsPartie = new JLabel("<html>"+parseTemps(monTemps)+"</html>");	// TEMPS (h min sec)
		lb_tempsPartie.setForeground(Color.WHITE);
		lb_tempsPartie.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tempsPartie.setFont(new Font("Arial", Font.BOLD, 24));
		lb_tempsPartie.setBounds(775, 410, 240, 60);
		add(lb_tempsPartie);
        
        /*
         * Bouton retour accueil (fin de correction).
         */
		terminer = new JButton("Terminer");
		terminer.setForeground(Color.WHITE);
		terminer.setFont(new Font("Arial", Font.PLAIN, 20));
		terminer.setBackground(new Color(7, 92, 120));
		terminer.setBorder(null);
		
		terminer.setBounds(860, 550, 122, 36);
		terminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenetre.goToAccueil("texte");
			}
		});
		add(terminer);
		
		/*
		 * JLabel d'explication dans leur sous-panel particulier
		 */
		JPanel sousPanelExpl = new JPanel();
		sousPanelExpl.setBounds(30, 160, 520, 100);
		sousPanelExpl.setLayout(null);
		sousPanelExpl.setOpaque(false);
		//sousPanelExpl.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		add(sousPanelExpl);
		bon = new JLabel("<html>La ou les bonne(s) r&eacute;ponse(s) sont &eacute;crites en "			// explication GREEN
				+ "<span style='color:green;'>VERT</span></html>");
		bon.setForeground(Color.WHITE);
		bon.setFont(new Font("Arial", Font.PLAIN, 18));
		bon.setBounds(0, 0, sousPanelExpl.getWidth()/2, sousPanelExpl.getHeight());
		sousPanelExpl.add(bon);
		
		mauvais = new JLabel("<html>La ou les mauvaise(s) r&eacute;ponse(s) sont &eacute;crites en "	// explication RED
				+ "<span style='color:red;'>ROUGE</span></html>");
		mauvais.setForeground(Color.WHITE);
		mauvais.setFont(new Font("Arial", Font.PLAIN, 18));
		mauvais.setBounds(sousPanelExpl.getWidth()/2, 0, sousPanelExpl.getWidth()/2, sousPanelExpl.getHeight());
		sousPanelExpl.add(mauvais);
		
		/*
		 * Label d'affichage de la question
		 */
		lb_numQuest = new JLabel("Q #10");
        lb_numQuest.setFont(new Font("Arial", Font.ITALIC, 20));
        lb_numQuest.setForeground(Images.couleurLabel);
        lb_numQuest.setBounds(20, 260, 90, 30);
        lb_numQuest.setHorizontalAlignment(SwingConstants.CENTER);
        lb_numQuest.setVisible(false);
        add(lb_numQuest);
		question = new JLabel("<html>Selectionnez une question en haut &agrave; gauche pour voir les r&eacute;ponses.</html>");
		question.setBounds(60, 280, 520, 70);
		question.setForeground(Color.WHITE);
		question.setFont(new Font("Arial", Font.PLAIN, 20));
		add(question);
		
		/*
		 * SousPanel pour afficher les reponses en groupe easy
		 */
		sousPanel = new JPanel();
		sousPanel.setBounds(40, 330, 620, 390);
		sousPanel.setLayout(null);
		sousPanel.setOpaque(false);
		add(sousPanel);
		
		//****Inclusion du Header en 2 parties ****
        header1 = new Header(fen);
        header1.setBounds(0, 0, 444, 130);
        header1.addMouseListener(header1);
        header1.addMouseMotionListener(header1);
        this.add(header1);
        
        header2 = new Header_menu(fen, this);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
	}
	
	/**
	 * Instancie et affiche les boutons de question en haut a droite.
	 * @param num
	 */
	public void menuQuetions() {
		for (int i=0;i<boutonQuestion.length;i++) {
			boutonQuestion[i] = new Bouton_selection_question_correction(i, monQuiz, this);
			boutonQuestion[i].addMouseListener(boutonQuestion[i]);
			boutonQuestion[i].setBounds(480+27*i, 60, 20, 20);
			add(boutonQuestion[i]);
		}
	}
	
	/**
	 * Set le texte de la question et redessine.
	 * @param txt
	 */
	public void setQuestion(String txt) {
		question.setText("<html>"+txt+"</html>");
		repaint();
	}
	
	public void setNumQuest(int num) {
		lb_numQuest.setText("Q #"+(num+1));
		lb_numQuest.setVisible(true);
	}
	
	/**
	 * Affiche les reponses jouees par l'user a coter de la correction
	 * @param idQuest
	 */
	public void affchckPlayed(int idQuest) {
		for (byte i=0; i<chbx_tabRep[idQuest].getTabCheckLength(); i++) {
			// disable les checkBox et enleve le text (car il est grisee).
			chbx_tabRep[idQuest].getTabCheck(i).setEnabled(false);
			chbx_tabRep[idQuest].getTabCheck(i).setText(null);
			
			// mise en couleur des JLabel de correction (rouge si on s'est trompee, vert si on a juste).
			boutonQuestion[idQuest].getTabLabel(i).setBounds(((i%2)*sousPanel.getWidth()/2)+40, 70*(i/2), (sousPanel.getWidth()/2)-40, 75);
			boutonQuestion[idQuest].getTabLabel(i).setFont(new Font("Arial", Font.PLAIN, 20));
			boutonQuestion[idQuest].getTabLabel(i).setForeground(Color.GREEN);
			if (monQuiz.getQuest(idQuest).getReponse(i).getStatutRep() != chbx_tabRep[idQuest].getTabCheck(i).isSelected())
				boutonQuestion[idQuest].getTabLabel(i).setForeground(Color.RED);
			
			// affiche le JLabel juste a coter du checkbox.
			sousPanel.add(chbx_tabRep[idQuest].getTabCheck(i));
			sousPanel.add(boutonQuestion[idQuest].getTabLabel(i));
		}
	}
	
	public String parseTemps(long tempsGame) {
		long sec = tempsGame%60;
    	long min = (tempsGame/60)%60;
    	long hrs = (tempsGame/3600)%60;
    	return (hrs+" h "+min+" min "+sec+" sec");
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		
	}
}
