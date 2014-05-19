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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Gestion_quiz extends JPanel implements MouseListener, ItemListener {
	
	/**
	 * ATTRIBUTS DE CLASSES
	 */
	private Fenetre fenetre;
	private Creation_quiz creation_quiz;
	private Quiz monQuiz;
	private Quiz[] mesQuiz;
	
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
	
	/**
	 * Constructor
	 * @param fen
	 */
	public Gestion_quiz(Fenetre fen) {
		setLayout(null);
		fenetre = fen;
		
		// on recupere tout les quiz qui existe pour les afficher
		SQL_Requete_Quiz maRequete = new SQL_Requete_Quiz(fenetre);
		maRequete.recup_Quiz();
		mesQuiz = maRequete.getMesQuiz();
		
		// Titre et sous-titre en haut a droite
		lb_titreGestion = new JLabel("Gestion des Quiz");
		lb_titreGestion.setForeground(Images.couleurLabel);
		lb_titreGestion.setFont(new Font("Arial", Font.PLAIN, 42));
		lb_titreGestion.setBounds(575, 105, 400, 50);
		add(lb_titreGestion);
		
		// desciption dans la barre de droite
		lb_descrGestion = new JLabel("<html>Cette section vous permet de g&eacute;rer et de cr&eacute;er vos propres quiz.</html>");
		lb_descrGestion.setForeground(Color.WHITE);
		lb_descrGestion.setFont(new Font("Arial", Font.PLAIN, 20));
		lb_descrGestion.setBounds(655, 175, 320, 50);
		add(lb_descrGestion);
		
		// Consigne en haut a gauche
		JLabel lb_consigne = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;Ici vous pouvez visualiser l'ensemble des quiz de "
				+ "l'application. Vous ne pouvez modifier et/ou supprimer uniquement les quiz qui vous appartienent.<br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;Les quiz affich&eacute;s entre parenth&egrave;ses ne vous appartiennent pas.</html>");
		lb_consigne.setForeground(Color.WHITE);
		lb_consigne.setFont(new Font("Arial", Font.PLAIN, 16));
		lb_consigne.setBounds(70, 165, 440, 100);
		add(lb_consigne);
		
		// Titre de la liste (au centre)
		lb_titreListe = new JLabel("Liste de vos quiz");
		lb_titreListe.setForeground(Images.couleurLabel);
		lb_titreListe.setFont(new Font("Arial", Font.PLAIN, 24));
		lb_titreListe.setBounds(95, 288, 300, 20);
		add(lb_titreListe);
		
		// liste des quiz creer par cet admin
		list_quizcree = new List();
		list_quizcree.setBounds(85, 321, 444, 360);
		list_quizcree.addItemListener(this);
		list_quizcree.setBackground(new Color(54, 90, 118));
		list_quizcree.setForeground(Color.WHITE);
		add(list_quizcree);
		
		// remplissage de la liste avec une requete du style "recuperer tout les quiz creer par cet admin"
		for (short i=0; i<mesQuiz.length; i++) {
			if (mesQuiz[i].getLoginAdmin().equals(Connexion.login_general)) {
				list_quizcree.add(mesQuiz[i].getNom());
			} else {
				list_quizcree.add("(_"+mesQuiz[i].getNom()+"_)");
			}
		}
		list_quizcree.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel info = new JLabel("Selectionnez un quiz parmis la liste ci-dessus pour le modifier/supprimer");
		info.setFont(new Font("Arial", Font.PLAIN, 12));
		info.setForeground(Images.couleurLabel);
		info.setBounds(105, 695, 405, 14);
		add(info);
		
		// bouton de creation / modification / suppression des quiz de la liste
		bt_creerQuiz = new JButton("Creer quiz");
		bt_creerQuiz.setBounds(855, 379, 120, 35);
		bt_creerQuiz.setBackground(new Color(7, 92, 158));
		bt_creerQuiz.setForeground(Color.WHITE);
		bt_creerQuiz.setBorder(null);
		bt_creerQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomQuiz = (String)JOptionPane.showInputDialog(null, 
																	"Nom du quiz :", 
																	"Creation Quiz",
																	JOptionPane.QUESTION_MESSAGE);
				if ((nomQuiz != null) && (nomQuiz.length() > 0)) {
					// On ecrit pas encore dans la liste, ca sera fait avec une requete SQL au chargement de cette page
					// donc tant que le quiz en creation n'a pas ete valide, il n'apparaitra pas ici.
					// creation d'un nouveau quiz
					monQuiz = new Quiz(nomQuiz, fenetre);
					// qui est le parametre de Creation_quiz
					creation_quiz = new Creation_quiz(fenetre, monQuiz);
					// changement de fenetre sur Creation_quiz
					fenetre.getContentPane().setVisible(false);
					creation_quiz.addMouseListener(creation_quiz);
					fenetre.setContentPane(creation_quiz);
					fenetre.getContentPane().setVisible(true);
				} else {
					//System.out.println("Aucune string retournee");
				}
			}
		});
		add(bt_creerQuiz);
		
		bt_supprQuiz = new JButton("Modifier quiz");
		bt_supprQuiz.setBounds(855, 425, 120, 35);
		bt_supprQuiz.setBackground(new Color(7, 92, 120));
		bt_supprQuiz.setForeground(Color.WHITE);
		bt_supprQuiz.setBorder(null);
		bt_supprQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list_quizcree.getItemCount() > 0 && list_quizcree.getSelectedItem() != null) {
					// On recupere le Quiz choisi en entier (avec Questions et Reponses comprises)
					SQL_Requete_Quiz maRequete = new SQL_Requete_Quiz(fenetre);
					monQuiz = maRequete.getMyQuiz(mesQuiz[list_quizcree.getSelectedIndex()].getId());
					
					// nouvelle instance de creation_quiz qui recupere l'objet quiz choisi
					creation_quiz = new Creation_quiz(fenetre, monQuiz);
					fenetre.getContentPane().setVisible(false);
					creation_quiz.addMouseListener(creation_quiz);
					fenetre.setContentPane(creation_quiz);
					fenetre.getContentPane().setVisible(true);
				} else {
					System.out.println("Erreur dans modifier quiz !");
				}
			}
		});
		bt_supprQuiz.setEnabled(false);
		add(bt_supprQuiz);
		
		bt_modifQuiz = new JButton("Supprimer quiz");
		bt_modifQuiz.setBounds(855, 471, 120, 35);
		bt_modifQuiz.setBackground(new Color(7, 92, 120));
		bt_modifQuiz.setForeground(Color.WHITE);
		bt_modifQuiz.setBorder(null);
		bt_modifQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rep = JOptionPane.showConfirmDialog(null, 
						"<html><b>Attention :</b><br/>"
						+ "Vous etes sur le point de supprimer un Quiz.<br/>"
						+ "Souhaitez-vous continuer ?</html>", 
						"Suppression Quiz", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.WARNING_MESSAGE);
				if (rep == 0) {
					if (list_quizcree.getItemCount() > 0 && list_quizcree.getSelectedItem() != null) {
						SQL_Requete_Quiz maRequete = new SQL_Requete_Quiz(fenetre);
						maRequete.deleteQuiz(mesQuiz[list_quizcree.getSelectedIndex()].getId());
						list_quizcree.remove(list_quizcree.getSelectedItem());
					} else {
						//System.out.println("Erreur dans la suppression de quiz !");
					}
				}
			}
		});
		bt_modifQuiz.setEnabled(false);
		add(bt_modifQuiz);
		

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
	public void itemStateChanged(ItemEvent arg0) {
		/*
		 * grise ou degrise les bouton MODIFIER et SUPPRIMER suivant si on selectionne un quiz ou pas.
		 * Il faut faire attention a l'id_admin du quiz, ne pas degriser un quiz qui ne nous appartient pas.
		 */
		if (list_quizcree.getSelectedItem() != null) {
			// si le login du quiz et le meme que le login de la session (alors le quiz nous appartient et on peut le modif/suppr)
			if (mesQuiz[list_quizcree.getSelectedIndex()].getLoginAdmin().equals(Connexion.login_general)) {
				bt_modifQuiz.setEnabled(true);
				bt_modifQuiz.setBackground(Color.RED);
				bt_supprQuiz.setEnabled(true);
				bt_supprQuiz.setBackground(new Color(219,113,0));
			} else {
				bt_modifQuiz.setBackground(new Color(7, 92, 120));
				bt_modifQuiz.setEnabled(false);
				bt_supprQuiz.setBackground(new Color(7, 92, 120));
				bt_supprQuiz.setEnabled(false);
			}
		} else {
			bt_modifQuiz.setBackground(new Color(7, 92, 120));
			bt_modifQuiz.setEnabled(false);
			bt_supprQuiz.setBackground(new Color(7, 92, 120));
			bt_supprQuiz.setEnabled(false);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);
		switch (bouton_deco){
		case "rien" :
			g.drawImage(Images.img_bouton[4], 960, 1, 46, 46, null);
			break;				
		case "CO/DECO_hover" :
			g.drawImage(Images.img_bouton_hover[4], 960, 1, 46, 46, null);
			bouton_deco = "rien";
			break;
		}
	}
}
