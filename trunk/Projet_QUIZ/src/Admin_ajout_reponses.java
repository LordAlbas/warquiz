import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSeparator;

import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;


public class Admin_ajout_reponses extends JPanel  implements MouseListener, ItemListener{
	
	private Fenetre fenetre;
	private TextBox textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private List list;
	public Admin_ajout_reponses(Fenetre fen){
		setLayout(null);
		
		textField = new TextBox();
		textField.setBounds(756, 169, 204, 20);
		add(textField);

		list = new List();
		list.setBounds(296, 159, 188, 270);
		add(list);
		
		//String[] items = {"jhg", "kjhgb", "ouhbu", "bnn", "lknbg", "lhii", "eswe", "dxxc", "gv", "1 gv0"};
		
		list.add("gfch846gfcbh");
		list.add("gfch654gfcbh");
		list.add("gfch2213gfcbh");
		list.add("gfchg49849fcbh");
		list.add("gfch846gfcbh");
		list.add("gfch654gfcbh");
		list.add("gfch2213gfcbh");
		list.add("gfchg49849fcbh");
		list.add("gfch846gfcbh");
		list.add("gfch654gfcbh");
		list.add("gfch2213gfcbh");
		list.add("gfchg49849fcbh");
		list.add("gfch846gfcbh");
		list.add("gfch654gfcbh");
		list.add("gfch2213gfcbh");
		
		
		list.addItemListener(this);
		System.out.println();
		//add(list);
		//list.addMouseListener(this);
		JButton btnNewButton = new JButton("liste selected");
		btnNewButton.setBounds(502, 244, 89, 23);
		add(btnNewButton);
		btnNewButton.addMouseListener(this);
		


		//list.addMouseListener(items);
		
		fenetre = fen;
	}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent e) {
		//System.out.println(list.locationToIndex(e.getPoint()));
		//System.out.println(list.getSelectedValue());
		System.out.println(list.getSelectedItem());
	}
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
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
