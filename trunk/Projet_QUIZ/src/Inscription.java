import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Inscription extends JPanel implements MouseListener, MouseMotionListener{

	private Fenetre fenetre;
	
	static String selection,dbUsername_user; 				// defini quel bouton est selectionn�
    public String query_ajout,query_user;
    
	private JTextField textField_pseudo;	// création du champ pseudo
	private JPasswordField textField_mdp;	// création du cham mdp cypté
	private JPasswordField textField_mdp_conf;	// création du champ mdp confirmation
	private JTextField textField_mail; // création du champ mail
	
	public int bad_pseudo=0; // le champ n'est pas encore invalide
	public int bad_mdp=0; // le champ n'est pas encore invalide
	public int bad_mdp_conf=0; // le champ n'est pas encore invalide
	public int bad_mail=0; // le champ n'est pas encore invalide
	public int bad_mdp_mdpConf=0; //vérification du mot de passe
	public int tentative = 0;  //le nombre de tentative d'authentification
	public int pseudo_existe = 0; // pseudo deja existant
    
	public static boolean erreur_log,recherche_bdd, erreur_bdd = false;
    public static boolean erreur_requete = true;
    

	public Inscription(Fenetre fen){
		fenetre = fen; // On récupère la classe Fenetre
		setLayout(null); // on met le layout en absolute pour mettre les JTextbox où on veut
		
		// Création du textBox PEUDO et placement + suppression du style
		textField_pseudo = new JTextField();
		textField_pseudo.setBounds(398, 288, 366, 36);
		add(textField_pseudo);
		textField_pseudo.setBorder(null);
		textField_pseudo.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_pseudo.setOpaque(false);
		
		// Création du textBox MDP et placement + suppression du style
		textField_mdp = new JPasswordField();
		textField_mdp.setBounds(398, 328, 366, 36);
		add(textField_mdp);
		textField_mdp.setBorder(null);
		textField_mdp.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mdp.setOpaque(false);	
		
		// Création du textBox MDP_CONF et placement + suppression du style
		textField_mdp_conf = new JPasswordField();
		textField_mdp_conf.setBounds(398, 368, 366, 36);
		add(textField_mdp_conf);
		textField_mdp_conf.setBorder(null);
		textField_mdp_conf.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mdp_conf.setOpaque(false);
		
		// Création du textBox MAIL et placement + suppression du style
		textField_mail = new JTextField();
		textField_mail.setBounds(398, 408, 366, 36);
		add(textField_mail);
		textField_mail.setBorder(null);
		textField_mail.setFont(new Font("Arial", Font.PLAIN, 25)); 
		textField_mail.setOpaque(false);
		
		this.pushKeyboard();
	}
	
	public void pushKeyboard(){ // On défini les action des touches  ENTER	
		textField_pseudo.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				if(evt.getKeyCode() == evt.VK_ENTER ){
					if(textField_pseudo.getText().length() != 0){	// Si le PSEUDO est entré	
						if(textField_mdp.getPassword().length == 0){	// MDP est pas entré
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							tentative++;
							repaint();
						}
						if(textField_mdp_conf.getPassword().length == 0){	// MDP_CONF est pas entré
							textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp_conf=1;
							tentative++;
							repaint();
						}
						if(textField_mail.getText().length() == 0){	// MAIL est pas entré
							textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mail=1;
							tentative++;
							repaint();
						}
						else if(textField_mdp.getPassword().length != 0 && textField_mdp_conf.getPassword().length != 0 && textField_mail.getText().length() != 0 && textField_mdp_conf.getText().equals(textField_mdp.getText())){
							//Try catch permettant de se connecter selon si on est admin ou user
							try {
								recherche_bdd=true;
								repaint();
					        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
					            query_user = "SELECT login_usr FROM UTILISATEUR";
					            Statement stmt_user = (Statement) conn.createStatement();
					            stmt_user.executeQuery(query_user);
					            ResultSet rs_user = stmt_user.getResultSet();
					            while(rs_user.next()){
					            	dbUsername_user = rs_user.getString("login_usr");
					            	if (dbUsername_user.equals(textField_pseudo.getText())){
					            		pseudo_existe = 1;
					            		fenetre.goToInscription(selection);
					            	}
					            	else{
					            		query_ajout = "INSERT INTO UTILISATEUR (LOGIN_USR,MDP_USR,ADR_MAIL_USR,NB_QUIZ_JOUE,NB_PARTIE_JOUE)"
						            			+ "VALUES (?,?,?,?,?);";
					            		PreparedStatement stmt_ajout = (PreparedStatement) conn.prepareStatement(query_ajout);
					            		stmt_ajout.setString(1, textField_pseudo.getText());
							            stmt_ajout.setString(2, textField_mdp.getText());
							            stmt_ajout.setString(3, textField_mail.getText());
							            stmt_ajout.setInt(4, 0);
							            stmt_ajout.setInt(5, 0);
							            stmt_ajout.executeUpdate();
							            stmt_ajout.close();
							            pseudo_existe = 0;
							            fenetre.goToConnexion(selection);
					            	}	            
					            }
							}
							catch (ClassNotFoundException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        } catch (SQLException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        }
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}else{
						textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_pseudo=1;
						tentative++;
						repaint();
						if(textField_mdp.getPassword().length == 0){
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							repaint();
						}
						if(textField_mdp_conf.getPassword().length == 0){
							textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp_conf=1;
							repaint();
						}
						if(textField_mail.getText().length() == 0){
							textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mail=1;
							repaint();
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}
				}
			}
		});
		textField_mdp.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				if(evt.getKeyCode() == evt.VK_ENTER ){
					if(textField_mdp.getPassword().length != 0){	// Si le MDP est entré
						if(textField_pseudo.getText().length() == 0){	// PSEUDO est pas entré
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							tentative++;
							repaint();
						}
						if(textField_mdp_conf.getPassword().length == 0){	// MDP_CONF est pas entré
							textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp_conf=1;
							tentative++;
							repaint();
						}
						if(textField_mail.getText().length() == 0){	// MAIL est pas entré
							textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mail=1;
							tentative++;
							repaint();
						}
						else if(textField_pseudo.getText().length() != 0 && textField_mdp_conf.getPassword().length != 0 && textField_mail.getText().length() != 0 && textField_mdp_conf.getText().equals(textField_mdp.getText())){
							//Try catch permettant de se connecter selon si on est admin ou user
							try {
								recherche_bdd=true;
								repaint();
					        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
					            query_user = "SELECT login_usr FROM UTILISATEUR";
					            Statement stmt_user = (Statement) conn.createStatement();
					            stmt_user.executeQuery(query_user);
					            ResultSet rs_user = stmt_user.getResultSet();
					            while(rs_user.next()){
					            	dbUsername_user = rs_user.getString("login_usr");
					            	if (dbUsername_user.equals(textField_pseudo.getText())){
					            		pseudo_existe = 1;
					            		fenetre.goToInscription(selection);
					            	}
					            	else{
					            		query_ajout = "INSERT INTO UTILISATEUR (LOGIN_USR,MDP_USR,ADR_MAIL_USR,NB_QUIZ_JOUE,NB_PARTIE_JOUE)"
						            			+ "VALUES (?,?,?,?,?);";
					            		PreparedStatement stmt_ajout = (PreparedStatement) conn.prepareStatement(query_ajout);
					            		stmt_ajout.setString(1, textField_pseudo.getText());
							            stmt_ajout.setString(2, textField_mdp.getText());
							            stmt_ajout.setString(3, textField_mail.getText());
							            stmt_ajout.setInt(4, 0);
							            stmt_ajout.setInt(5, 0);
							            stmt_ajout.executeUpdate();
							            stmt_ajout.close();
							            pseudo_existe = 0;
							            fenetre.goToConnexion(selection);
					            	}	            
					            }
							}
							catch (ClassNotFoundException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        } catch (SQLException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        }
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}else{
						textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_mdp=1;
						tentative++;
						repaint();
						if(textField_pseudo.getText().length() == 0){
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							repaint();
						}
						if(textField_mdp_conf.getPassword().length == 0){
							textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp_conf=1;
							repaint();
						}
						if(textField_mail.getText().length() == 0){
							textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mail=1;
							repaint();
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}
				}
			}
		});
		textField_mdp_conf.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				if(evt.getKeyCode() == evt.VK_ENTER ){
					if(textField_mdp_conf.getPassword().length != 0){	// Si le MDP_CONF est entré
						if(textField_pseudo.getText().length() == 0){	// PSEUDO est pas entré
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							tentative++;
							repaint();
						}
						if(textField_mdp.getPassword().length == 0){	// MDP est pas entré
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							tentative++;
							repaint();
						}
						if(textField_mail.getText().length() == 0){	// MAIL est pas entré
							textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mail=1;
							tentative++;
							repaint();
						}
						else if(textField_pseudo.getText().length() != 0 && textField_mdp.getPassword().length != 0 && textField_mail.getText().length() != 0 && textField_mdp_conf.getText().equals(textField_mdp.getText())){
							//Try catch permettant de se connecter selon si on est admin ou user
							try {
								recherche_bdd=true;
								repaint();
					        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
					            query_user = "SELECT login_usr FROM UTILISATEUR";
					            Statement stmt_user = (Statement) conn.createStatement();
					            stmt_user.executeQuery(query_user);
					            ResultSet rs_user = stmt_user.getResultSet();
					            while(rs_user.next()){
					            	dbUsername_user = rs_user.getString("login_usr");
					            	if (dbUsername_user.equals(textField_pseudo.getText())){
					            		pseudo_existe = 1;
					            		fenetre.goToInscription(selection);
					            	}
					            	else{
					            		query_ajout = "INSERT INTO UTILISATEUR (LOGIN_USR,MDP_USR,ADR_MAIL_USR,NB_QUIZ_JOUE,NB_PARTIE_JOUE)"
						            			+ "VALUES (?,?,?,?,?);";
					            		PreparedStatement stmt_ajout = (PreparedStatement) conn.prepareStatement(query_ajout);
					            		stmt_ajout.setString(1, textField_pseudo.getText());
							            stmt_ajout.setString(2, textField_mdp.getText());
							            stmt_ajout.setString(3, textField_mail.getText());
							            stmt_ajout.setInt(4, 0);
							            stmt_ajout.setInt(5, 0);
							            stmt_ajout.executeUpdate();
							            stmt_ajout.close();
							            pseudo_existe = 0;
							            fenetre.goToConnexion(selection);
					            	}	            
					            }
							}
							catch (ClassNotFoundException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        } catch (SQLException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        }
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}else{
						textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_mdp_conf=1;
						tentative++;
						repaint();
						if(textField_pseudo.getText().length() == 0){
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							repaint();
						}
						if(textField_mdp.getPassword().length == 0){
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							repaint();
						}
						if(textField_mail.getText().length() == 0){
							textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mail=1;
							repaint();
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}
				}
			}
		});
		textField_mail.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyPressed(java.awt.event.KeyEvent evt){
				if(evt.getKeyCode() == evt.VK_ENTER ){
					if(textField_mail.getText().length() != 0){	// Si le MAIL est entré	
						if(textField_pseudo.getText().length() == 0){	// PSEUDO est pas entré
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							tentative++;
							repaint();
						}
						if(textField_mdp.getPassword().length == 0){	// MDP est pas entré
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							tentative++;
							repaint();
						}
						if(textField_mdp_conf.getPassword().length == 0){	// MDP_CONF est pas entré
							textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp_conf=1;
							tentative++;
							repaint();
						}
						else if(textField_pseudo.getText().length() != 0 && textField_mdp_conf.getPassword().length != 0 && textField_mdp.getPassword().length != 0 && textField_mdp_conf.getText().equals(textField_mdp.getText())){
							//Try catch permettant de se connecter selon si on est admin ou user
							try {
								recherche_bdd=true;
								repaint();
					        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
					            query_user = "SELECT login_usr FROM UTILISATEUR";
					            Statement stmt_user = (Statement) conn.createStatement();
					            stmt_user.executeQuery(query_user);
					            ResultSet rs_user = stmt_user.getResultSet();
					            while(rs_user.next()){
					            	dbUsername_user = rs_user.getString("login_usr");
					            	if (dbUsername_user.equals(textField_pseudo.getText())){
					            		pseudo_existe = 1;
					            		fenetre.goToInscription(selection);
					            	}
					            	else{
					            		query_ajout = "INSERT INTO UTILISATEUR (LOGIN_USR,MDP_USR,ADR_MAIL_USR,NB_QUIZ_JOUE,NB_PARTIE_JOUE)"
						            			+ "VALUES (?,?,?,?,?);";
					            		PreparedStatement stmt_ajout = (PreparedStatement) conn.prepareStatement(query_ajout);
					            		stmt_ajout.setString(1, textField_pseudo.getText());
							            stmt_ajout.setString(2, textField_mdp.getText());
							            stmt_ajout.setString(3, textField_mail.getText());
							            stmt_ajout.setInt(4, 0);
							            stmt_ajout.setInt(5, 0);
							            stmt_ajout.executeUpdate();
							            stmt_ajout.close();
							            pseudo_existe = 0;
							            fenetre.goToConnexion(selection);
					            	}	            
					            }
							}
							catch (ClassNotFoundException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        } catch (SQLException eeee) {
					            eeee.printStackTrace();
					            erreur_bdd = true;
					            recherche_bdd=false;
					            erreur_requete=true;
					            repaint();
					        }
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}else{
						textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						bad_mail=1;
						tentative++;
						repaint();
						if(textField_pseudo.getText().length() == 0){
							textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_pseudo=1;
							repaint();
						}
						if(textField_mdp.getPassword().length == 0){
							textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp=1;
							repaint();
						}
						if(textField_mdp_conf.getPassword().length == 0){
							textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
							bad_mdp_conf=1;
							repaint();
						}
						else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
							bad_mdp_mdpConf=1;
							tentative++;
							repaint();
						}
					}
				}
			}
		});
	}
	
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(e.getX() >= 400 && e.getX() <= 585 && e.getY() >= 462 && e.getY() <= 510){ // changement de curseur sur le bouton retour
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else if(e.getX() >= 605 && e.getX() <= 790 && e.getY() >= 465 && e.getY() <= 513){ // changement de curseur sur le bouton valider
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}	
		else{
			setCursor(Cursor.getDefaultCursor());
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(textField_pseudo.isFocusOwner() == true){ // Si le champ pseudo est selectionné on réinitialise le style
			bad_pseudo=0;
			textField_pseudo.setBorder(null);
			repaint();
		}
		if(textField_mdp.isFocusOwner() == true){ // Si le champ mdp est selectionné on réinitialise le style
			bad_mdp=0;
			textField_mdp.setBorder(null);
			repaint();
		}
		if(textField_mdp_conf.isFocusOwner() == true){ // Si le champ mdp_conf est selectionné on réinitialise le style
			bad_mdp_conf=0;
			textField_mdp_conf.setBorder(null);
			repaint();
		}
		if(textField_mail.isFocusOwner() == true){ // Si le champ mail est selectionné on réinitialise le style
			bad_mail=0;
			textField_mail.setBorder(null);
			repaint();
		}
		if(e.getX() >= 400 && e.getX() <= 585 && e.getY() >= 462 && e.getY() <= 510){ // Bouton de retour
			fenetre.goToConnexion(selection); // on appel la fonction qui va changer de panel
		}		
		if(e.getX() >= 605 && e.getX() <= 790 && e.getY() >= 465 && e.getY() <= 513){ // Bouton de validation
			if(textField_mdp.getPassword().length != 0){
				if(textField_pseudo.getText().length() == 0){
					textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_pseudo=1;
					tentative++;
					repaint();
				}
				else if(textField_mdp_conf.getPassword().length == 0){
					textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_mdp_conf=1;
					tentative++;
					repaint();
				}
				else if(textField_mail.getText().length() == 0){
					textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_mail=1;
					tentative++;
					repaint();
				}
				else if(textField_pseudo.getText().length() != 0 && textField_mdp_conf.getPassword().length != 0 && textField_mail.getText().length() != 0 && textField_mdp_conf.getText().equals(textField_mdp.getText())){
					//Try catch permettant de se connecter selon si on est admin ou user
					try {
						recherche_bdd=true;
						repaint();
			        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			            Connection conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			            query_user = "SELECT login_usr FROM UTILISATEUR";
			            Statement stmt_user = (Statement) conn.createStatement();
			            stmt_user.executeQuery(query_user);
			            ResultSet rs_user = stmt_user.getResultSet();
			            while(rs_user.next()){
			            	dbUsername_user = rs_user.getString("login_usr");
			            	if (textField_pseudo.getText().equals(dbUsername_user)){
			            		pseudo_existe = 1;
			            		fenetre.goToInscription(selection);
			            	}
			            	else{
			            		query_ajout = "INSERT INTO UTILISATEUR (LOGIN_USR,MDP_USR,ADR_MAIL_USR,NB_QUIZ_JOUE,NB_PARTIE_JOUE)"
				            			+ "VALUES (?,?,?,?,?);";
			            		PreparedStatement stmt_ajout = (PreparedStatement) conn.prepareStatement(query_ajout);
			            		stmt_ajout.setString(1, textField_pseudo.getText());
					            stmt_ajout.setString(2, textField_mdp.getText());
					            stmt_ajout.setString(3, textField_mail.getText());
					            stmt_ajout.setInt(4, 0);
					            stmt_ajout.setInt(5, 0);
					            stmt_ajout.executeUpdate();
					            stmt_ajout.close();
					            pseudo_existe = 0;
					            fenetre.goToConnexion(selection);
			            	}	            
			            }
					}
					catch (ClassNotFoundException eeee) {
			            eeee.printStackTrace();
			            erreur_bdd = true;
			            recherche_bdd=false;
			            erreur_requete=true;
			            repaint();
			        } catch (SQLException eeee) {
			            eeee.printStackTrace();
			            erreur_bdd = true;
			            recherche_bdd=false;
			            erreur_requete=true;
			            repaint();
			        }
				}
				else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
					bad_mdp_mdpConf=1;
					tentative++;
					repaint();
				}
			}else if(textField_mdp.getPassword().length == 0){
				textField_mdp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
				bad_mdp=1;
				tentative++;
				repaint();
				if(textField_pseudo.getText().length() == 0){
					textField_pseudo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_pseudo=1;
					repaint();
				}
				if(textField_mdp_conf.getPassword().length == 0){
					textField_mdp_conf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_mdp_conf=1;
					repaint();
				}
				if(textField_mail.getText().length() == 0){
					textField_mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					bad_mail=1;
					repaint();
				}
				else if(!textField_mdp_conf.getText().equals(textField_mdp.getText()) || textField_mdp.getPassword().length != textField_mdp_conf.getPassword().length){ //Si les MDP sont pas égaux
					bad_mdp_mdpConf=1;
					tentative++;
					repaint();
				}
			}
		}			
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Images.img_fond[Theme.getTheme()], 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(Images.img_element[0], 0, 0, this.getWidth(), (int)(this.getHeight() / 6.1230), null);		// dessine le header
		g.drawImage(Images.img_bouton[6], 901, 0, 46, 46, null);
		g.drawImage(Images.img_bouton[4], 967, 0, 46, 46, null);
		/*
		 * On boucle sur tous les boutons (de [0] a [3]).
		 * Chaque bouton obtient une position X (hauteur) en fonction de son numero (i).
		 * Les positions en durs (ici 120 et 260) correspondent au coin haut-gauche du bloc de bouton.
		 */
		for (int i=0; i<4; i++) {
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
			g.drawImage(Images.img_bouton[i], 120, 260+(i*(Images.hauteur_bouton+Images.ecart_bouton)), Images.largeur_bouton, Images.hauteur_bouton, null);
		}
		g.drawImage(Images.img_element[1], 140, 113, 8, 655, null);
		g.drawImage(Images.img_fond[2], 0, 0, this.getWidth(), this.getHeight(), null);	
		
		if(bad_pseudo==1){
			g.drawImage(Images.img_element[3], 768, 284, 170, 46, null);	
		}
		if(bad_mdp==1){
			g.drawImage(Images.img_element[3], 768, 322, 170, 46, null);	
		}
		if(bad_mdp_conf==1){
			g.drawImage(Images.img_element[3], 768, 360, 170, 46, null);	
		}
		if(bad_mail==1){
			g.drawImage(Images.img_element[3], 768, 398, 170, 46, null);	
		}
		if(bad_mdp_mdpConf==1){
			g.drawImage(Images.img_element[12], 140, 50, 800, 130, null);	
		}
		if(pseudo_existe == 1){
			g.drawImage(Images.img_element[13], 140, 50, 800, 130, null);
		}
		repaint();		
	}
}