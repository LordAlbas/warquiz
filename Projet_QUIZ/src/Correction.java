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
	private JLabel bon;
	private JLabel mauvais;
	private JLabel rouge;
	private JLabel vert;
	private JLabel[] affTabLabel;
	public JPanel sousPanel;

	
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
        
        titreQuiz = new JLabel("<html>Correction du quiz : <br/>"+monQuiz.getNom()+"</html>");
        titreQuiz.setForeground(Color.WHITE);
        titreQuiz.setFont(new Font("Arial", Font.PLAIN, 30));
        titreQuiz.setBounds(30, 120, 535, 100);
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
		
		question = new JLabel("Selectionnez une question pour voir les réponses.");
		question.setBounds(30, 307, 753, 29);
		question.setForeground(Color.WHITE);
		question.setFont(new Font("Arial", Font.PLAIN, 20));
		add(question);

		sousPanel = new JPanel();
		sousPanel.setBounds(30, 347, 734, 368);
		sousPanel.setLayout(null);
		sousPanel.setOpaque(false);
		add(sousPanel);
		
		
		bon = new JLabel("La ou les bonne(s) réponse(s) sont écrites en ");
		bon.setBounds(30, 261, 300, 24);
		add(bon);
		bon.setForeground(Color.WHITE);
		bon.setFont(new Font("Arial", Font.PLAIN, 15));

		vert = new JLabel("VERT");
		vert.setBounds(352, 226, 52, 24);
		vert.setForeground(Color.GREEN);
		vert.setFont(new Font("Arial", Font.PLAIN, 15));
		add(vert);	
		
		mauvais = new JLabel("La ou les mauvaise(s) réponse(s) sont écrites en ");
		mauvais.setBounds(30, 226, 323, 24);
		add(mauvais);
		mauvais.setForeground(Color.WHITE);
		mauvais.setFont(new Font("Arial", Font.PLAIN, 15));

		rouge = new JLabel("ROUGE");
		rouge.setBounds(332, 255, 72, 36);
		rouge.setForeground(Color.RED);
		rouge.setFont(new Font("Arial", Font.PLAIN, 15));
		add(rouge);			
		
		
		reponse = new JLabel("");
		/*reponse.setBounds(30, 300, 450, 50);
		reponse.setForeground(Color.WHITE);
		reponse.setFont(new Font("Arial", Font.PLAIN, 20));
		add(reponse);
		*/
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
	
	/*
	 * création des tableaux de label de réponses
	 */
	public JLabel[] createTab(int i){
		JLabel[] TabLabel = new JLabel[monQuiz.getQuest(i).getNb_reponses()];
		 
		for(int v=0;v<monQuiz.getQuest(i).getNb_reponses();v++){
			if(monQuiz.getQuest(i).getReponse(v).getStatutRep() == false){
				TabLabel[v] = new JLabel(monQuiz.getQuest(i).getReponse(v).getTxtReponse());
				TabLabel[v].setForeground(Color.RED);
			}
			else{
				TabLabel[v] = new JLabel(monQuiz.getQuest(i).getReponse(v).getTxtReponse());
				TabLabel[v].setForeground(Color.GREEN);
			}
			
		}
		return TabLabel;
	}
	
	/*
	 * affichage des labels de réponses
	 */
	public void affLabel(JLabel[]tab, int num){
		for(int i=0;i<monQuiz.getQuest(num).getNb_reponses();i++){
			tab[i].setBounds(30, 30*i, 450, 50);
			//tab[i].setForeground(Color.WHITE);
			tab[i].setFont(new Font("Arial", Font.PLAIN, 20));
			sousPanel.add(tab[i]);
		}	
		
	}
	
	
	public void setReponse(int nbRep, String txt){
		reponse.setText(txt);
		reponse.repaint();
	}
	
	public void setCpt(int nb){
		cpt = nb;
	}
	public void mouseClicked(MouseEvent arg0) {
		
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
