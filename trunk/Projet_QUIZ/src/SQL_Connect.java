import java.sql.*;
import javax.swing.JPanel;

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
}