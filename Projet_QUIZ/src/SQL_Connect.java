import java.sql.*;
import javax.swing.*;
import java.awt.Graphics;

public class SQL_Connect extends JPanel{

	private Fenetre fenetre;
	private static String selection; 
	
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
		} catch (Exception e) {
            System.out.println(e.getMessage());
            Connexion.erreur_bdd=true;
            Connexion.recherche_bdd=false;  
		}
	}
	public static void testAdminUser() { //Méthode de connexion pour vérifier si tu es admin ou user
		Connection conn = null;
		try {
			Connexion.recherche_bdd=true;
			//repaint();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://193.252.48.189\\SQLEXPRESS:1433;" + "database=BDD_B3I_groupe_5;" + "user=b3i_groupe_5;" + "password=123Soleil");
			Statement stmt_admin = (Statement) conn.createStatement();
            Statement stmt_user = (Statement) conn.createStatement();
            Connexion.query_admin = "SELECT login_admin, mdp_admin FROM ADMIN";
            Connexion.query_user = "SELECT login_usr, mdp_usr FROM UTILISATEUR";
            stmt_admin.executeQuery(Connexion.query_admin);
            stmt_user.executeQuery(Connexion.query_user);
            ResultSet rs_admin = stmt_admin.getResultSet();
            ResultSet rs_user = stmt_user.getResultSet();
            
            while(rs_admin.next()){
            	Connexion.dbUsername_admin = rs_admin.getString("login_admin");
            	Connexion.dbPassword_admin = rs_admin.getString("mdp_admin");
            	Connexion.recherche_bdd = true;
            	Connexion.erreur_log = false;
                
                if(Connexion.dbUsername_admin.equals(Connexion.textField_pseudo.getText()) && Connexion.dbPassword_admin.equals(Connexion.textField_mdp.getText())){
                	Connexion.login = true;
                	Connexion.connexion_admin = true;
                	Connexion.fenetre.goToAccueil(selection);
                	Connexion.login_general = Connexion.dbUsername_admin;
                }
                else{
                	Connexion.erreur_log = true;
                }
            }
       
            //repaint(); 
            while(rs_user.next()){
            	Connexion.dbUsername_user = rs_user.getString("login_usr");
            	Connexion.dbPassword_user = rs_user.getString("mdp_usr");
            	Connexion.recherche_bdd = true;
            	Connexion.erreur_log = false;
            	//repaint();
                if(Connexion.dbUsername_user.equals(Connexion.textField_pseudo.getText()) && Connexion.dbPassword_user.equals(Connexion.textField_mdp.getPassword())){
                	Connexion.login = true;
                	Connexion.fenetre.goToAccueil(selection);
                	Connexion.login_general = Connexion.dbUsername_user;
                }
                else{
                	Connexion.erreur_log = true;
                }
            }
            Connexion.recherche_bdd=false;
            //repaint(); 
        } catch (SQLException eeee) {
        	eeee.printStackTrace();
        	Connexion.erreur_bdd = true;
        	Connexion.recherche_bdd=false;
            //repaint();
        } catch (ClassNotFoundException eeee) {
			eeee.printStackTrace();
			Connexion.erreur_bdd = true;
			Connexion.recherche_bdd=false;
            //repaint();
		}
	}
}