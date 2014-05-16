import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.TimerTask;

import javax.swing.*;

public class Jouer_partie extends JPanel implements MouseListener, MouseMotionListener {
	private Fenetre fenetre;
	private Quiz current_quiz;
	
	// pour les hover sur les images boutons
	private String selection;
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
	private Quiz monQuiz;
	Button boutonMenu;
	Bouton_selection_question boutonQuestion;
	private int cpt =0;
	private JLabel TxtVite;
	private JLabel reponse;
	private JLabel question;

	
	public Jouer_partie(Fenetre fen, Quiz quiz) {
		fenetre = fen;
		current_quiz = quiz;
		sqlRQ = new SQL_Requete_Quiz(fen);
		setLayout(null);
		
		monQuiz = sqlRQ.getMyQuiz(current_quiz.getId()); // mon quiz
		menuQuetions(monQuiz.getNb_questions());
		
		
		lb_titrePartie = new JLabel(monQuiz.getNom()+" "+monQuiz.getNb_questions());
		lb_titrePartie.setForeground(Color.WHITE);
		lb_titrePartie.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titrePartie.setBounds(575, 105, 400, 50);
		add(lb_titrePartie);
		
		
		
		//****Inclusion du Header en 2 parties ****
        header1 = new Header(fen);
        header1.setBounds(0, 0, 444, 130);
        header1.addMouseListener(header1);
        header1.addMouseMotionListener(header1);
        this.add(header1); 
     

        header2 = new Header_menu(fen);
        header2.setBounds(444, 0, 580, 58);
        header2.addMouseListener(header2);
        header2.addMouseMotionListener(header2);
        this.add(header2); 
        //****************************************
        
        JButton bt_valider_rep = new JButton("<html>Valider la réponse</html>");
        bt_valider_rep.setBounds(840, 570, 170, 40);
        bt_valider_rep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String error_msg = "<html>Veuillez choisir une r&eacute;ponse !</html>";
				if (error_msg != "")
					JOptionPane.showMessageDialog(null, 
							error_msg, 
							"Pas de réponse", 
							JOptionPane.ERROR_MESSAGE);
				else
					;// validereponse(); et mise dans le tableau de la reponse
			}
		});
		add(bt_valider_rep);
        
        //Chronometre dur�e partie    
        chrono = new Chronometre (fen, this, monQuiz, SQL_Requete_Quiz.getHeures(monQuiz.getId()), SQL_Requete_Quiz.getMin(monQuiz.getId()), SQL_Requete_Quiz.getSec(monQuiz.getId()));
        
        
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
        
        question = new JLabel(monQuiz.getQuest(0).getQuestTxt());
        question.setBounds(10, 250, 1000, 36);
        question.setFont(new Font("Arial", Font.PLAIN, 20));
        question.setForeground(Color.WHITE);
        add(question);
        
        TxtVite = new JLabel("<html>Pensez &agrave;<br/>valider !</html>");
        TxtVite.setHorizontalAlignment(SwingConstants.CENTER); 
        TxtVite.setFont(new Font("Arial", Font.PLAIN, 35));
        TxtVite.setForeground(Color.RED);
        TxtVite.setBounds(835, 653, 200, 70);
        add(TxtVite);
        TxtVite.setVisible(false);
	}
	
	public void setQuestion(String txt){
		question.setText(txt);
		question.repaint();
	}
	
	public void setReponse(int nbRep, String txt){
			
			
			reponse = new JLabel(txt);
			add(reponse);
			reponse.setBounds(20, 300+20*cpt, 1000, 36);
			reponse.setFont(new Font("Arial", Font.PLAIN, 15));
			reponse.setForeground(Color.WHITE);
			reponse.repaint();
			cpt++;
		
	}
	
	public void setCpt(int nb){
		cpt = nb;
	}
	
	/*public void clearLabel (){
		
		reponse.removeAll();
	}*/
	
	public void menuQuetions(int num){		
		for(int i=0;i<num;i++){
			
			boutonQuestion = new Bouton_selection_question(i, monQuiz, this);
			boutonQuestion.addMouseListener(boutonQuestion);
			boutonQuestion.setBounds(480+27*i, 60, 20, 20);
			add(boutonQuestion);
			/*
			boutonMenu = new Button(""+(i+1));
			boutonMenu.addMouseListener(this);
			boutonMenu.setBackground(Color.BLUE);
			boutonMenu.setForeground(Color.WHITE);
			boutonMenu.setBounds(480+27*i, 60, 20, 20);
			add(boutonMenu);*/
		}
	}
	
	
	
	private Timer createTimer (){
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
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		
	}
}