import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Correction extends JPanel implements MouseListener{
	private Quiz monQuiz;
	private Header header1;
	private Header_menu header2;
	private JLabel lb_titreCorrection;
	private Bouton_selection_question_correction boutonQuestion;
	private JLabel question;
	private JLabel reponse;
	private int cpt=0;
	private JButton terminer;
	private Fenetre fenetre;
	private JLabel titreQuiz;
	
	public Correction (Quiz quiz, Fenetre fen){
		setLayout(null);
		monQuiz = quiz;
		menuQuetions(monQuiz.getNb_questions());
		fenetre = fen;
		
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
        
        titreQuiz = new JLabel("Correction du quiz : "+monQuiz.getNom());
        titreQuiz.setForeground(Color.WHITE);
        titreQuiz.setFont(new Font("Arial", Font.PLAIN, 30));
        titreQuiz.setBounds(30, 120, 400, 50);
        add(titreQuiz);
        
		lb_titreCorrection = new JLabel("Correction");
		lb_titreCorrection.setForeground(Color.WHITE);
		lb_titreCorrection.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreCorrection.setBounds(575, 105, 400, 50);
		add(lb_titreCorrection);
		
		terminer = new JButton("Terminer");
		terminer.setForeground(Color.WHITE);
		terminer.setFont(new Font("Arial", Font.PLAIN, 20));
		terminer.setBackground(new Color(7, 92, 120));
		terminer.setBorder(null);
		
		terminer.setBounds(850, 500, 122, 36);
		terminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenetre.goToAccueil("texte");
			}
		});
		add(terminer);
	}
	

	public void menuQuetions(int num){		
		for(int i=0;i<num;i++){
			boutonQuestion = new Bouton_selection_question_correction(i, monQuiz, this);
			boutonQuestion.addMouseListener(boutonQuestion);
			boutonQuestion.setBounds(480+27*i, 60, 20, 20);
			add(boutonQuestion);
		}
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
	public void mouseClicked(MouseEvent arg0) {
		setBackground(Color.GRAY);
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
		
	}

}
