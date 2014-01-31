import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSeparator;

import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.SystemColor;


public class Creation_quiz extends JPanel implements MouseListener, MouseMotionListener{
	
	private Fenetre fenetre;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public Creation_quiz(Fenetre fen){
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(756, 169, 204, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(280, 373, 204, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(280, 430, 204, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		
		Choice choice = new Choice();
		choice.setBounds(280, 489, 204, 27);
		add(choice);
		
		JLabel label = new JLabel("TITRE DU QUIZ");
		
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.PLAIN, 25)); 
		label.setBounds(621, 136, 177, 22);
		add(label);
		
		
		
		fenetre = fen;
	}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header	
		g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
	}
}
