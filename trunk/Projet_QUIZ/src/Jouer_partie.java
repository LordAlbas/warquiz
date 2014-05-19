import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;

import javax.swing.*;

public class Jouer_partie extends JPanel implements MouseListener, MouseMotionListener {
	private Fenetre fenetre;
	private Quiz monQuiz;
	
	// pour les hover sur les images boutons
	private String bouton_deco ="rien";
	private String bouton_retour ="rien";
	private String image_select;
	private JLabel lb_titrePartie;
	private Header header1;
	private Header_menu header2;
	private Timer timer;
	private JLabel Chronometre;
	private Chronometre chrono;
	private int tempsInitial;
	private int tempsRestant;
	private SQL_Requete_Quiz sqlRQ;
	Button boutonMenu;
	private JButton bt_prevQuest;
	private JButton bt_nextQuest;
	private Bouton_selection_question boutonQuestion[];
	private int id_numQuest;
	private JLabel TxtVite;
	private JLabel lb_numQuest;
	private JLabel question;
	public JPanel sousPanel;
	
	public Jouer_partie(Fenetre fen, Quiz quiz) {
		setLayout(null);
		id_numQuest = 0;
		fenetre = fen;
		
		sqlRQ = new SQL_Requete_Quiz(fen);
		monQuiz = sqlRQ.getMyQuiz(quiz.getId());	// mon quiz
		
		boutonQuestion = new Bouton_selection_question[monQuiz.getNb_questions()];
		
		/*
		 * Nom du quiz
		 */
		JLabel lb_nomQuiz = new JLabel("Quiz ["+monQuiz.getDifficulteQuiz()+"]");
		lb_nomQuiz.setFont(new Font("Arial", Font.PLAIN, 24));
		lb_nomQuiz.setForeground(Images.couleurLabel);
		lb_nomQuiz.setBounds(600, 100, 400, 50);
        add(lb_nomQuiz);
		lb_titrePartie = new JLabel("<html>"+monQuiz.getNom()+"</html>");
		lb_titrePartie.setForeground(Color.WHITE);
		lb_titrePartie.setFont(new Font("Arial", Font.PLAIN, 35));
		lb_titrePartie.setBounds(630, 150, 400, 80);
		add(lb_titrePartie);
		
		/*
		 * Bouton VALIDER LA GAME
		 */
		JButton bt_valider_rep = new JButton("<html>Valider la partie</html>");
        bt_valider_rep.setBounds(840, 490, 170, 40);
        bt_valider_rep.setBackground(new Color(27, 113, 16));
        bt_valider_rep.setBorder(null);
        bt_valider_rep.setForeground(Color.WHITE);
        bt_valider_rep.setFont(new Font("Arial", Font.PLAIN, 20));
        bt_valider_rep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();												// PAUSE des chronos
				chrono.stopTimer();
				int rep = JOptionPane.showConfirmDialog(null, 				// AFFICHE attention quitter
						"<html><b>Fini ?</b><br/><br/>"
						+ "Par&eacute;(e) &agrave; obtenir votre r&eacute;sultat ?<br/>"
						+ "Cliquez OUI et voyez la correction par vous-m&ecirc;me !</html>", 
						"Quitter partie", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.INFORMATION_MESSAGE);
				if (rep == 0) {												// si OK on redirige
					if (isReponduPartout()) {
						fenetre.goToCorrection(monQuiz, boutonQuestion, calculScore(), calculTemps());
					} else {
						timer.start();
						chrono.startTimer();
					}
				} else {													// si CANCEL on reprend les chronos
					timer.start();
					chrono.startTimer();
				}
			}
		});
		add(bt_valider_rep);
		
		/*
		 * Bouton QUITTER PARTIE
		 */
        JButton bt_leave_game = new JButton("<html>Quitter la partie</html>");
        bt_leave_game.setBounds(840, 570, 170, 40);
        
        bt_leave_game.setBackground(Color.RED);
        bt_leave_game.setBorder(null);
        bt_leave_game.setForeground(Color.WHITE);
        bt_leave_game.setFont(new Font("Arial", Font.PLAIN, 20));
        
        bt_leave_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();												// PAUSE des chronos
				chrono.stopTimer();
				int rep = JOptionPane.showConfirmDialog(null, 				// AFFICHE attention quitter
						"<html><b>Attention :</b><br/>"
						+ "Vous &ecirc;tes sur le point de quitter la partie en cours.<br/>"
						+ "Souhaitez-vous quitter ?</html>", 
						"Quitter partie", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.WARNING_MESSAGE);
				if (rep == 0) {												// si OK on redirige
					fenetre.goToJouer("mabite");
				} else {													// si CANCEL on reprend les chronos
					timer.start();
					chrono.startTimer();
				}
			}
		});
		add(bt_leave_game);
		
        /*
         * numero de la question
         */
        lb_numQuest = new JLabel("Q #rien du tout");
        lb_numQuest.setFont(new Font("Arial", Font.ITALIC, 32));
        lb_numQuest.setForeground(Images.couleurLabel);
        lb_numQuest.setBounds(145, 184, 90, 40);
        lb_numQuest.setHorizontalAlignment(SwingConstants.CENTER);
        add(lb_numQuest);
        
        /*
         * Bouton PREVIOUS et NEXT
         */
        bt_prevQuest = new JButton("<");
        bt_prevQuest.setFont(new Font("Arial", Font.BOLD, 18));
        bt_prevQuest.setForeground(Color.WHITE);
        bt_prevQuest.setBorder(null);
        bt_prevQuest.setBackground(new Color(54, 90, 118));
		bt_prevQuest.setBounds(110, 189, 20, 30);
		bt_prevQuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id_numQuest-1 >= 0 && id_numQuest < boutonQuestion.length)
					boutonQuestion[id_numQuest-1].switchQuest();
			}
		});
		add(bt_prevQuest);
        bt_nextQuest = new JButton(">");
        bt_nextQuest.setFont(new Font("Arial", Font.BOLD, 18));
        bt_nextQuest.setForeground(Color.WHITE);
        bt_nextQuest.setBorder(null);
        bt_nextQuest.setBackground(new Color(54, 90, 118));
		bt_nextQuest.setBounds(250, 189, 20, 30);
		bt_nextQuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id_numQuest >= 0 && id_numQuest+1 < boutonQuestion.length)
					boutonQuestion[id_numQuest+1].switchQuest();
			}
		});
		add(bt_nextQuest);
		
        /*
		 * JLabel qui contient le nom de la QUESTION (ou bien le message par defaut).
		 */
        question = new JLabel("<html>Selectionnez une question pour voir les r&eacute;ponses.</html>");
        question.setBounds(60, 250, 510, 70);
        question.setFont(new Font("Arial", Font.PLAIN, 20));
        question.setForeground(Color.WHITE);
        add(question);
        
        /*
         * SousPanel contient les reponses de la question en cours.
         * Plus simple pour changer de contenu dynamiquement (sousPanel.removeAll()).
         */
        sousPanel = new JPanel();
		sousPanel.setBounds(40, 330, 620, 390);
		sousPanel.setLayout(null);
		sousPanel.setOpaque(false);
		//sousPanel.setBorder(BorderFactory.createLineBorder(Color.red));		// bordure de test .. a commenter !
		add(sousPanel);
        
		/*
         * Chronometre implementé dans la class Chronometre.java    
         */
        chrono = new Chronometre (fen, this, monQuiz, SQL_Requete_Quiz.getHeures(monQuiz.getId()), SQL_Requete_Quiz.getMin(monQuiz.getId()), SQL_Requete_Quiz.getSec(monQuiz.getId()));
        
        /*
         * Chronometre local ... (apparement il en faut un pour le temps et un pour les couleurs ..).
         */
        //System.out.println(chrono.getTpsRestant());
        Chronometre = new JLabel(chrono.getTpsRestant());
        Chronometre.setForeground(Color.BLACK);
        Chronometre.setOpaque(true);
        Chronometre.setHorizontalAlignment(JLabel.CENTER);
        Chronometre.setFont(new Font("Arial", Font.PLAIN, 20));
        Chronometre.setBackground(Color.GREEN);
        Chronometre.setBounds(850, 732, 174, 36);
        add(Chronometre);
        timer = createTimer();
        timer.start();
        
		
        /*
         * Text qui s'affiche quand plus beaucoup de temps.
         */
        TxtVite = new JLabel("<html>Pensez &agrave;<br/>finir !</html>");
        TxtVite.setHorizontalAlignment(SwingConstants.CENTER); 
        TxtVite.setFont(new Font("Arial", Font.PLAIN, 35));
        TxtVite.setForeground(Color.RED);
        TxtVite.setBounds(835, 653, 200, 70);
        add(TxtVite);
        TxtVite.setVisible(false);
        
        //****Inclusion du Header en 2 parties ****
        header1 = new Header(fen);
        header1.setBounds(0, 0, 444, 130);
        header1.addMouseListener(header1);
        header1.addMouseMotionListener(header1);
        this.add(header1); 
        
        header1.setInGame(true);

        header2 = new Header_menu(fen, this);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
        
        header2.setInGame(true);
        
        creer_menuQuetions();					// Creation des tableau de reponses
        boutonQuestion[0].switchQuest();		// Affiche la premiere question des le debut de la game.
	}
	
	public Bouton_selection_question[] getBoutonQuestion() {
		return boutonQuestion;
	}
	public void setNumQuest(int num) {
		id_numQuest = num;
		lb_numQuest.setText("Q #"+(num+1));
		bt_prevQuest.setEnabled(num-1 >= 0);
		bt_nextQuest.setEnabled(num+1 < boutonQuestion.length);
		
		// lieu arbitraire pour checker les reponses au fur et a mesure
		for (byte i=0; i<boutonQuestion.length; i++) {
			if (i == id_numQuest) {
				boutonQuestion[i].setBackground(Color.ORANGE);
			} else {
				if (boutonQuestion[i].getEtatBouton() == 1) {
					boutonQuestion[i].setBackground(Color.GRAY);
				} else {
					boutonQuestion[i].setBackground(new Color(23, 78, 116));
				}
				
			}
			if (boutonQuestion[i].isRepondu())
				boutonQuestion[i].setBackground(new Color(27, 113, 16));
		}
	}
	public void setQuestion(String txt) {
		question.setText("<html>"+txt+"</html>");
		//repaint();
	}
	
	/**
	 * Creer un Bouton_selection_question pour chaque question.
	 * Called localement au debut du constructeur.
	 * @param nb_quest
	 */
	public void creer_menuQuetions() {
		for (int i=0; i<boutonQuestion.length; i++) {
			boutonQuestion[i] = new Bouton_selection_question(i, monQuiz, this);
			boutonQuestion[i].addMouseListener(boutonQuestion[i]);
			boutonQuestion[i].setBounds(480+27*i, 60, 20, 20);
			add(boutonQuestion[i]);
		}
	}
	
	public boolean isReponduPartout() {
		boolean repondu = true;
		String str_err = "<html>Quiz non recevable.<br/><br/>";
		for (byte i=0; i<boutonQuestion.length; i++) {
			if (!boutonQuestion[i].isRepondu()) {
				repondu = false;
				str_err += "La question n&deg;"+(i+1)+" n'a pas de r&eacute;ponse.<br/>";
			}
		}
		str_err += "<br/>Si vous bloquez, cochez au hasard ! ;)</html>";
		if (!repondu) {
			JOptionPane.showMessageDialog(null, 
					str_err, 
					"Partie pas fini", 
					JOptionPane.ERROR_MESSAGE);
		}
		return repondu;
	}
	
	public int calculScore() {
		int score = 0;
		for (byte z=0; z<boutonQuestion.length; z++) {		// pour chaque Question
			boolean questJuste = true;
			for (byte k=0; k<boutonQuestion[z].getTabCheckLength(); k++) {		// pour chaque Reponse de la Quest
				if (boutonQuestion[z].getTabCheck(k).isSelected() != monQuiz.getQuest(z).getReponse(k).getStatutRep())
					questJuste = false;
			}
			if (questJuste)
				score++;
		}
		// ici "score" vaut le nombre de question juste (genre 7 sur 10questions)
		
		score *= 100;	// donc ici on a genre 700
		
		// alors on calcul un pourcentage pour avoir un score sur 100.
		// exemple 700 / 10 question alors ca fait 70% ce qui reste un integer et qui est juste ... NICE!
		double resultat = (score/monQuiz.getNb_questions());
		
		score = (int)Math.round(resultat);
		
		// user => quiz deja joue ? null : quizJoue++
		//			table JOUER where JOUER.login_user == connexion.login_generel
		//			AND JOUER.id_quiz == monQuiz.getID;
		//		Si oui (deja jouee) => nb_partieJouer++
		//		Si non (pas jouee) => nb_partieJouer++ && nb_quizJouer++
		//		Dans tout les cas => JOUER.insertInto (login_usr, id_quiz, score, tempsh,min,sec);
		
		/*
		 * TABLE JOUER
		 * 		login_usr			(varchar)				VS.			login_usr	courant
		 * 		id_quiz				(int)					VS. 		id_quiz		courant
		 * 		score_usr_quiz		(int)
		 * 		heure_usr_quiz		(int)
		 * 		minute_usr_quiz		(int)
		 * 		seconde_usr_quiz	(int)
		 */
		
		/*
		 * TABLE UTILISATEUR
		 * 		nb_partie_joue	(int)	(+1)
		 * 		nb_quiz_joue	(int)	(+1 ou pas)
		 * 		(login_usr)		(varchar)
		 * 		(mdp_usr)		(varchar)
		 * 		(adr_mail_usr)	(varchar)
		 */
		
		return score;
	}
	
	/**
	 * Renvoi le temps de duree de la partie (type long, en millisec).
	 * Called au moment de la validation du quiz (soit fin de chrono soit bouton valider).
	 * @return
	 */
	public long calculTemps() {
		long temps = chrono.getChronoInital() - chrono.getChrono();
		return temps;
	}
	
	/**
	 * Timer local.
	 * Gestion de la couleur du timer pas local.
	 * @return objet timer
	 */
	private Timer createTimer () {
		ActionListener action = new ActionListener() {
			// M�thode appel�e � chaque tic du timer
			public void actionPerformed (ActionEvent event){
				Chronometre.setText(chrono.getTpsRestant());
				
				tempsInitial = (int)chrono.getChronoInital();
				tempsRestant = (int)chrono.getChrono();
				int case1 = tempsInitial/2;
				int case2 = tempsInitial-(3*(tempsInitial/4));
				
				if(tempsRestant > case1){
					Chronometre.setForeground(Color.BLACK);
					Chronometre.setBackground(Color.GREEN);
				}
				else if((tempsRestant <= case1) && tempsRestant > case2){
					Chronometre.setForeground(Color.BLACK);
					Chronometre.setBackground(Color.ORANGE);
				}
				else {
					Chronometre.setForeground(Color.WHITE);
					Chronometre.setBackground(Color.RED);
					TxtVite.setVisible(true);
					TxtVite.repaint();
				}
				Chronometre.repaint();
			}
		};
		
		// Cr�ation d'un timer qui g�n�re un tic
		// chaque 1000 milli�me de seconde soit une seconde ! h� ouais maggle !
		return new Timer (1000, action);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		
	}
}