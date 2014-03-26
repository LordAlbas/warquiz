import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Gestion_quiz extends JPanel implements MouseListener, ItemListener {
	
	/**
	 * ATTRIBUTS DE CLASSES
	 */
	private Fenetre fenetre;
	
	private String bouton_deco ="rien";
	private String bouton_retour ="rien";
	
	private Header header1;
	private Header_menu header2;
	
	private JLabel lb_titreGestion;
	private JLabel lb_descrGestion;
	private JLabel lb_titreListe;
	
	private JButton bt_creerQuiz;
	private JButton bt_modifQuiz;
	private JButton bt_supprQuiz;
	
	private List list_quizcree;
	
	public Gestion_quiz(Fenetre fen) {
		setLayout(null);
		fenetre = fen;
		
		// Titre et sous-titre en haut a droite
		lb_titreGestion = new JLabel("Gestion des Quiz");
		lb_titreGestion.setForeground(Color.WHITE);
		lb_titreGestion.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreGestion.setBounds(575, 105, 400, 50);
		add(lb_titreGestion);
		
		lb_descrGestion = new JLabel("<html>Cette section vous permet de gerer et de creer vos propres quiz.</html>");
		lb_descrGestion.setForeground(Color.WHITE);
		lb_descrGestion.setFont(new Font("Arial", Font.PLAIN, 20));
		lb_descrGestion.setBounds(635, 165, 320, 50);
		add(lb_descrGestion);
		
		// Titre de la liste (au centre)
		lb_titreListe = new JLabel("Liste de vos quiz");
		lb_titreListe.setForeground(Color.WHITE);
		lb_titreListe.setFont(new Font("Arial", Font.PLAIN, 18));
		lb_titreListe.setBounds(155, 275, 300, 20);
		add(lb_titreListe);
		
		// liste des quiz creer par cet admin
		list_quizcree = new List();
		list_quizcree.setBounds(150, 300, 188, 319);
		list_quizcree.addItemListener(this);
		list_quizcree.setBackground(new Color(54, 90, 118));
		add(list_quizcree);
		
		// il faut remplir la liste avec une requete du style "recuperer tout les quiz creer par cet admin"
		list_quizcree.add("quiz qui rox");
		list_quizcree.add("quiz qui rox un peu moins");
		
		// bouton de creation / modification / suppression des quiz de la liste
		bt_creerQuiz = new JButton("Creer quiz");
		bt_creerQuiz.setBounds(370, 350, 120, 35);
		bt_creerQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String nonQuiz = (String)JOptionPane.showInputDialog(frame,"Complete the sentence:\n" + "\"Green eggs and...\"","Customized Dialog",JOptionPane.PLAIN_MESSAGE,icon,possibilities,"ham");
				String nonQuiz = (String)JOptionPane.showInputDialog("Nom du quiz :");
				
				if ((nonQuiz != null) && (nonQuiz.length() > 0)) {
					System.out.println("Entry = "+nonQuiz);
					list_quizcree.add(nonQuiz);
				} else {
					System.out.println("Aucune string retournee");
				}
			}
		});
		add(bt_creerQuiz);
		
		bt_supprQuiz = new JButton("Modifier quiz");
		bt_supprQuiz.setBounds(370, 395, 120, 35);
		bt_supprQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("click sur Modification de quiz");
			}
		});
		add(bt_supprQuiz);
		
		bt_modifQuiz = new JButton("Supprimer quiz");
		bt_modifQuiz.setBounds(370, 440, 120, 35);
		bt_modifQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("click sur Suppression de quiz");
			}
		});
		add(bt_modifQuiz);

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
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
	// pour la liste des quiz cree
	public void itemStateChanged(ItemEvent arg0) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[0], 0, 0, this.getWidth(), this.getHeight(), null);
		//g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		
		switch (bouton_deco){
		case "rien" :
			g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
			break;				
		case "CO/DECO_hover" :
			g.drawImage(Images.img_bouton_hover[4], 960, 1, 46, 46, null);
			bouton_deco = "rien";
			break;
		}
		
		switch (bouton_retour)	{
		case "rien" :
			g.drawImage(Images.img_bouton[5], 1, 685, 84, 83, null);
			break;				
		case "retour_hover" :
			g.drawImage(Images.img_bouton_hover[5], 1, 685, 84, 83, null);
			bouton_retour = "rien";
			break;
		}
	}

}
