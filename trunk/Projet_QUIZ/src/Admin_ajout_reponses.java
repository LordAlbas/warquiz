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
	static List list;
	static JTextField reponse;
	private JTextField textField_3;
	public Admin_ajout_reponses(Fenetre fen){
		setLayout(null);
		
		//textField = new TextBox();
		//textField.setBounds(756, 169, 204, 20);
		//add(textField);

		list = new List();
		list.setBounds(171, 312, 188, 319);
		add(list);
		
		//String[] items = {"jhg", "kjhgb", "ouhbu", "bnn", "lknbg", "lhii", "eswe", "dxxc", "gv", "1 gv0"};
		
		//list.add("Réponse A");
		//list.add("Réponse B");
		//list.add("Réponse C");
		//list.add("Réponse D");
		//list.add("Réponse E");
		//list.add("Réponse F");
		//list.add("Réponse G");
		//list.add("Réponse H");
		//list.add("Réponse I");
		//list.add("Réponse K");
		//list.add("Réponse L");
		//list.add("Réponse M");
		//list.add("Réponse N");
		//list.add("Réponse O");
		//list.add("Réponse P");
		//list.add("Réponse Q");
		//list.add("Réponse R");
		//list.add("Réponse S");
		//list.add("Réponse T");
		//list.add("Réponse U");
		
		list.addItemListener(this);
		System.out.println();
		//add(list);
		//list.addMouseListener(this);
		Bouton_suppr_reponse bouton_suppr = new Bouton_suppr_reponse();
		bouton_suppr.setBounds(365, 312, 89, 23);
		add(bouton_suppr);
		bouton_suppr.addMouseListener(bouton_suppr);
		bouton_suppr.setText("Supprimer");
		
		
		reponse = new JTextField();
		reponse.setBounds(171, 247, 188, 20);
		add(reponse);
		reponse.setColumns(10);
		
		Bouton_ajout_reponse button = new Bouton_ajout_reponse();
		button.setBounds(365, 246, 89, 23);
		add(button);
		button.addMouseListener(button);
		button.setText("Ajouter");
		
		JLabel lblRponse = new JLabel("Réponse");
		lblRponse.setBounds(171, 224, 46, 14);
		add(lblRponse);
		
		JLabel lblA = new JLabel("A");
		lblA.setBounds(145, 250, 23, 14);
		add(lblA);
		
		textField_3 = new JTextField();
		textField_3.setBounds(96, 147, 493, 51);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setBounds(102, 122, 46, 14);
		add(lblQuestion);
		


		//list.addMouseListener(items);
		
		fenetre = fen;
	}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent e) {
		//System.out.println(list.locationToIndex(e.getPoint()));
		//System.out.println(list.getSelectedValue());
		//System.out.println(list.getSelectedItem());
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
