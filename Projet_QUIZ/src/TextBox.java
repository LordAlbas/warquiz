import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;


public class TextBox extends JTextField implements MouseListener, MouseMotionListener{
	
	JTextField test;
	
	

	
	public TextBox (){
		
		//test.setText("nijonijoni");
		//test.setBorder(null);
		
		//test.setOpaque(false);


	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		test = new JTextField();
		add(test);
		g.drawImage(Images.img_element[2], 0, 0, this.getWidth(), this.getHeight(), null);
		
		test.setFont(new Font("Arial", Font.PLAIN, 10)); 
		this.setBorder(null);
		
		repaint();
	}
	
}
