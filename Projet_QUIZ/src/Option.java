import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Option extends JPanel implements MouseListener, MouseMotionListener{
	private Header header1;
	private Header_menu header2;
	private JPanel panel;
	private JLabel lb_titreOption;
	private JButton retour;
	private Fenetre fenetre;
	private JRadioButton t1;
	private JRadioButton t2;
	private JRadioButton t3;
	private Boolean check1;
	private Boolean check2;
	private Boolean check3;
	private JLabel lb_info;
	
	public  Option(Fenetre fen, JPanel jp){
		setLayout(null);
		panel = jp;
		fenetre = fen;
		
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
	    repaint();
	    
	    header2.setInOption(true);
	    
		lb_titreOption = new JLabel("<html>Selectionnez un th&egrave;me puis validez.</html>");
		lb_titreOption.setForeground(Images.couleurLabel);
		lb_titreOption.setFont(new Font("Arial", Font.PLAIN, 25));
		lb_titreOption.setBounds(290, 220, 500, 50);
		add(lb_titreOption);
		
		lb_info = new JLabel("Option");
		lb_info.setForeground(Color.WHITE);
		lb_info.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_info.setBounds(575, 105, 400, 50);
		add(lb_info);
		
		retour = new JButton("Valider");
		retour.setForeground(Color.WHITE);
		retour.setFont(new Font("Arial", Font.PLAIN, 20));
		retour.setBackground(new Color(7, 92, 120));
		retour.setBorder(null);
		retour.setBounds(870, 700, 122, 36);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				header2.setInOption(false);
				fenetre.goToBack(panel);
			}
		});
		add(retour);
		
		header2.setInOption(true);
		
		if(Theme.getTheme() == 0){
			check1 = true;
			check2 = false;
			check3 = false;
		}
		
		if(Theme.getTheme() == 3){
			check1 = false;
			check2 = true;
			check3 = false;
		}
		if(Theme.getTheme() == 5){
			check1 = false;
			check2 = false;
			check3 = true;
		}
		
		t1 = new JRadioButton("", check1);
		t1.setOpaque(false);
		t1.setForeground(Color.WHITE);
		t1.setBorder(null);
		t1.setBackground(Color.BLUE);
		t1.setFont(new Font("Arial", Font.PLAIN, 20));
		t1.setBounds(160, 512, 300, 23);
		add(t1);
		t1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme.setTheme(0);
				Theme.setHeader1(9);
				Theme.setHeader2(7);
				
				Theme.setJouer(0);
				Theme.setJouer_hover(0);
				
				Theme.setStatistiques(1);
				Theme.setStatistiques_hover(1);
				
				Theme.setCredits(2);
				Theme.setCredits_hover(2);
				
				Theme.setQuitter(3);
				Theme.setQuitter_hover(3);
				Theme.setGerer(7);
				Theme.setGerer_hover(7);
				Theme.setBarre(1);
				
				Theme.setOption(4);
				
				t2.setSelected(false);
				t3.setSelected(false);
				repaint();
			}
		});
		
		

		
		t2 = new JRadioButton("", check2);
		t2.setOpaque(false);
		t2.setForeground(Color.WHITE);
		t2.setBorder(null);
		t2.setBackground(Color.BLUE);
		t2.setFont(new Font("Arial", Font.PLAIN, 20));
		t2.setBounds(460, 512, 300, 23);
		add(t2);
		t2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme.setTheme(3);
				Theme.setHeader1(9);
				Theme.setHeader2(7);
				Theme.setJouer(0);
				Theme.setJouer_hover(0);
				
				Theme.setStatistiques(1);
				Theme.setStatistiques_hover(1);
				
				Theme.setCredits(2);
				Theme.setCredits_hover(2);
				
				Theme.setQuitter(3);
				Theme.setQuitter_hover(3);
				Theme.setGerer(7);
				Theme.setGerer_hover(7);
				Theme.setBarre(1);
				Theme.setOption(6);
				t1.setSelected(false);
				t3.setSelected(false);
				repaint();
			}
		});
		
		t3 = new JRadioButton("", check3);
		t3.setOpaque(false);
		t3.setForeground(Color.WHITE);
		t3.setBorder(null);
		t3.setBackground(Color.BLUE);
		t3.setFont(new Font("Arial", Font.PLAIN, 20));
		t3.setBounds(760, 512, 300, 23);
		add(t3);
		t3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme.setTheme(5);
				Theme.setHeader1(14);
				Theme.setHeader2(15);
				
				Theme.setJouer(10);
				Theme.setJouer_hover(10);
				
				Theme.setStatistiques(11);
				Theme.setStatistiques_hover(11);
				
				Theme.setCredits(12);
				Theme.setCredits_hover(12);
				
				Theme.setQuitter(13);
				Theme.setQuitter_hover(13);
				
				Theme.setGerer(17);
				Theme.setGerer_hover(17);
				
				Theme.setBarre(2);
				Theme.setOption(7);
				
				t1.setSelected(false);
				t2.setSelected(false);
				
				
				repaint();
			}
		});		
		
	}


	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getX() >= 140 && arg0.getX() <= 340 && arg0.getY() >= 350 && arg0.getY() <= 750) { //Defaut
			t1.setSelected(true);
			t2.setSelected(false);
			t3.setSelected(false);
			Theme.setTheme(0);
			Theme.setHeader1(9);
			Theme.setHeader2(7);
			
			Theme.setJouer(0);
			Theme.setJouer_hover(0);
			
			Theme.setStatistiques(1);
			Theme.setStatistiques_hover(1);
			
			Theme.setCredits(2);
			Theme.setCredits_hover(2);
			
			Theme.setQuitter(3);
			Theme.setQuitter_hover(3);
			Theme.setGerer(7);
			Theme.setGerer_hover(7);
			Theme.setBarre(1);
			Theme.setOption(4);
			repaint();
		}
		if (arg0.getX() >= 440 && arg0.getX() <= 640 && arg0.getY() >= 350 && arg0.getY() <= 750) { //carr�
			t1.setSelected(false);
			t2.setSelected(true);
			t3.setSelected(false);
			Theme.setTheme(3); // image num 3
			Theme.setHeader1(9);
			Theme.setHeader2(7);
			Theme.setJouer(0);
			Theme.setJouer_hover(0);
			
			Theme.setStatistiques(1);
			Theme.setStatistiques_hover(1);
			
			Theme.setCredits(2);
			Theme.setCredits_hover(2);
			
			Theme.setQuitter(3);
			Theme.setQuitter_hover(3);
			Theme.setGerer(7);
			Theme.setGerer_hover(7);
			Theme.setBarre(1);
			Theme.setOption(6);
			t1.setSelected(false);
			t3.setSelected(false);
			repaint();
		}
		if (arg0.getX() >= 740 && arg0.getX() <= 940 && arg0.getY() >= 350 && arg0.getY() <= 750) { //toxic
			t1.setSelected(false);
			t2.setSelected(false);
			t3.setSelected(true);
			Theme.setTheme(5);
			Theme.setHeader1(14);
			Theme.setHeader2(15);
			
			Theme.setJouer(10);
			Theme.setJouer_hover(10);
			
			Theme.setStatistiques(11);
			Theme.setStatistiques_hover(11);
			
			Theme.setCredits(12);
			Theme.setCredits_hover(12);
			
			Theme.setQuitter(13);
			Theme.setQuitter_hover(13);
			
			Theme.setGerer(17);
			Theme.setGerer_hover(17);
			
			Theme.setBarre(2);
			Theme.setOption(7);
			
			
			repaint();
		}		
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {
		if (arg0.getX() >= 140 && arg0.getX() <= 340 && arg0.getY() >= 350 && arg0.getY() <= 550) { //Defaut
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else if (arg0.getX() >= 440 && arg0.getX() <= 640 && arg0.getY() >= 350 && arg0.getY() <= 550) { //carr�
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getX() >= 740 && arg0.getX() <= 940 && arg0.getY() >= 350 && arg0.getY() <= 550){//toxic
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}else {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
			
		
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);							// dessine le fond d'ecran
		
		if(Theme.getBarre()==1){
			g.drawImage(Images.img_element[1], 140, 113, 8, 655, null);
		}
		
		
		g.drawImage(Images.img_fond[Theme.getOption()], 0, 0, this.getWidth(), this.getHeight(), null);	//fond du option
		
		
		g.drawImage(Images.img_element[10], 140, 350, 200, 200, null);
		g.drawImage(Images.img_element[11], 440, 350, 200, 200, null);
		g.drawImage(Images.img_element[17], 740, 350, 200, 200, null);
		
		
	}



}
