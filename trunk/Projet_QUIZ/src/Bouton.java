import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Bouton extends JButton implements MouseListener{

	public Bouton (String texte){
		//setOpaque(false);
		setLayout(null);
		/*
		JLabel l_texte = new JLabel ();
		l_texte.setText(texte);
		l_texte.setForeground(Color.WHITE);
		l_texte.setFont(new Font("Arial", Font.PLAIN, 20));
		l_texte.setBorder(null);
		*/
		//add(l_texte);
		
		setText(texte);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 20));
		setBackground(new Color(7,92,158));
		setSize(80, 36);
		setBorder(null);
		

		setFocusPainted(false);
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void paintComponents(Graphics g) {

		super.paintComponents(g);
		//g.drawImage(Images.img_bouton[8], 0, 0,getWidth(),getHeight(), null);
		
	}
}
