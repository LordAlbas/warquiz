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
	private Boolean check1;
	private Boolean check2;
	
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
	    
		lb_titreOption = new JLabel("Option");
		lb_titreOption.setForeground(Color.WHITE);
		lb_titreOption.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreOption.setBounds(575, 105, 400, 50);
		add(lb_titreOption);
		
		retour = new JButton("Valider");
		retour.setForeground(Color.WHITE);
		retour.setFont(new Font("Arial", Font.PLAIN, 20));
		retour.setBackground(new Color(7, 92, 120));
		retour.setBorder(null);
		retour.setBounds(870, 700, 122, 36);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenetre.goToBack(panel);
			}
		});
		add(retour);
		
		
		if(Theme.getTheme() == 0){
			check1 = true;
			check2 = false;
		}
		
		if(Theme.getTheme() == 3){
			check1 = false;
			check2 = true;
		}
		
		
		t1 = new JRadioButton("  Selectionner ce thème", check1);
		t1.setOpaque(false);
		t1.setForeground(Color.WHITE);
		t1.setBorder(null);
		t1.setBackground(Color.BLUE);
		t1.setFont(new Font("Arial", Font.PLAIN, 20));
		t1.setBounds(400, 300, 250, 23);
		add(t1);
		t1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme.setTheme(0);
				t2.setSelected(false);
				repaint();
			}
		});
		
		header2.setInOption(true);

		
		t2 = new JRadioButton("Selectionner ce thème", check2);
		t2.setOpaque(false);
		t2.setForeground(Color.WHITE);
		t2.setBorder(null);
		t2.setBackground(Color.BLUE);
		t2.setFont(new Font("Arial", Font.PLAIN, 20));
		t2.setBounds(400, 600, 250, 23);
		add(t2);
		t2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme.setTheme(3);
				t1.setSelected(false);
				repaint();
			}
		});
		
		
		
	}


	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getX() >= 160 && arg0.getX() <= 360 && arg0.getY() >= 200 && arg0.getY() <= 400) { //Defaut
			t1.setSelected(true);
			t2.setSelected(false);
			Theme.setTheme(0);
			repaint();
		}
		if (arg0.getX() >= 160 && arg0.getX() <= 360 && arg0.getY() >= 500 && arg0.getY() <= 700) { //carré
			t1.setSelected(false);
			t2.setSelected(true);
			Theme.setTheme(3);
			repaint();
		}		
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {
		if (arg0.getX() >= 160 && arg0.getX() <= 360 && arg0.getY() >= 200 && arg0.getY() <= 400) { //Defaut
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else if (arg0.getX() >= 160 && arg0.getX() <= 360 && arg0.getY() >= 500 && arg0.getY() <= 700) { //carré
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		
		// le fond et les elements sont en fonction de la taille de la fenetre, donc pas de soucis de redimensionnement de la fenetre
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);							// dessine le fond d'ecran
		g.drawImage(Images.img_element[1], 140, 113, 8, 655, null);
		g.drawImage(Images.img_fond[4], 0, 0, this.getWidth(), this.getHeight(), null);	
		g.drawImage(Images.img_element[10], 160, 200, 200, 200, null);
		g.drawImage(Images.img_element[11], 160, 500, 200, 200, null);
		
		
	}



}
