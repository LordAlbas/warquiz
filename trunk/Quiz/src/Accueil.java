import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Accueil extends JPanel {
	
	private Image[] img;

	
	/**
	 * Constructeur
	 */
	public Accueil() {
		LoadImage("header");
		LoadImage("accueil_fond");
		
		
		//JPanel centre = new JPanel();
		//centre.setSize(864, 746);		// /!\ la barre du haut prend env. 22px de haut
		//JButton btn = new JButton("Yoo!");
		//this.add(btn);
	}
	
	/**
	 * Charge un fichier image dans la variable img
	 * @param _imageFile
	 */
	public void LoadImage(String _imageFile) {
		try {
			img = ImageIO.read(new File("images/"+_imageFile+".png"));	// Lecture du fichier .png
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	/**
	 * Paint
	 */
	public void paintComponent(Graphics g) {
			super.paintComponents(g);
			setBackground(new Color(250,150,50));			// couleur de background random pour test (r,g,b)
			//g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.drawImage(img, 0, 0, null);					// a 10x 10y du coin haut-gauche et de taille auto (observer=null)
			g.drawImage(img, 0, 0, null);
			//g.setColor(new Color(60,40,90));
			//g.fillRect(700, 400, 50, 270);					// a 700x 400y du coin, 50x270px
			
			//g.setColor(new Color(255, 255, 255));
			//g.setFont(new Font("Courier New", Font.BOLD, 36));
			//g.drawString("Yoo!", 400, 600);
	}
}

