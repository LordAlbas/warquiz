import java.sql.*;

import javax.swing.*;

public class SQL_Connect extends JPanel{

	private Fenetre fenetre;
	private static String selection;
	public static String login_admin; //variable prenant en compte les donnees de la colonne login_admin dans la BDD
	public static String mdp_admin; //variable prenant en compte les donnees de la colonne mdp_admin dans la BDD
	public static String login_usr; //variable prenant en compte les donnees de la colonne login_usr dans la BDD
	public static String mdp_usr; //variable prenant en compte les donnees de la colonne mdp_usr dans la BDD
	public static String query_admin; //variable dans lesquelles sont plac�es les requetes
	public static String query_user; //variable dans lesquelles sont plac�es les requetes
	public static String status;
	public static int etat = 0;
	
	public SQL_Connect(Fenetre fen) {
		fenetre = fen;
		fenetre.goToAccueil(selection);
	}
	    
	public static void tryConnect() {
		Connection conn = null;
		try {
			Connexion.recherche_bdd=true;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
            Connexion.recherche_bdd=false;
		} 
		catch (Exception e) {
            Connexion.erreur_bdd=true;
            Connexion.recherche_bdd=false;
		}
		
	}
	public static void testAdminUser() { //M�thode de connexion pour v�rifier si tu es admin ou user
		Connection conn = null;
		try {
			Connexion.recherche_bdd=true;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_admin = (Statement) conn.createStatement();
            Statement stmt_user = (Statement) conn.createStatement();
            query_admin = "SELECT login_admin, mdp_admin FROM ADMIN";
            query_user = "SELECT login_usr, mdp_usr FROM UTILISATEUR";
            stmt_admin.executeQuery(query_admin);
            stmt_user.executeQuery(query_user);
            
            ResultSet rs_admin = stmt_admin.getResultSet();
            ResultSet rs_user = stmt_user.getResultSet();
            
	            while(rs_admin.next()){
	            	Connexion.dbUsername_admin = rs_admin.getString("login_admin");
	            	Connexion.dbPassword_admin = rs_admin.getString("mdp_admin");
	            	
	                if(Connexion.dbUsername_admin.equals(Connexion.textField_pseudo.getText()) && Connexion.dbPassword_admin.equals(Connexion.textField_mdp.getText())){
	                	Connexion.login = true;
	                	Connexion.connexion_admin = true;
	                	Connexion.fenetre.goToAccueil(selection);
	                	Connexion.dbUsername_admin = Connexion.textField_pseudo.getText();
	                	Connexion.login_general = Connexion.dbUsername_admin;
	                	status = "ADMIN";
	                }
	                else{
	                	Connexion.erreur_log = true;
	                }
	                Connexion.recherche_bdd = true;
	            	Connexion.erreur_log = false;
	            }
	            
	            while(rs_user.next()){
	            	Connexion.dbUsername_user = rs_user.getString("login_usr");
	            	Connexion.dbPassword_user = rs_user.getString("mdp_usr");
	            	
	                if(Connexion.dbUsername_user.equals(Connexion.textField_pseudo.getText()) && Connexion.dbPassword_user.equals(Connexion.textField_mdp.getText())){
	                	Connexion.login = true;
	                	Connexion.connexion_admin = false;
	                	Connexion.fenetre.goToAccueil(selection);
	                	Connexion.login_general = Connexion.dbUsername_user;
	                	status = "USER";
	                }
	                else{
	                	Connexion.erreur_log = true;
	                }
	                Connexion.recherche_bdd = true;
	            	Connexion.erreur_log = false;
	            }
            
            Connexion.recherche_bdd=false;
        } catch (SQLException eeee) {
        	eeee.printStackTrace();
        	Connexion.erreur_bdd = true;
        	Connexion.recherche_bdd=false;
        } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
			Connexion.erreur_bdd = true;
			Connexion.recherche_bdd=false;
		}		
	}
}
