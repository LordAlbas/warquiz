import javax.swing.*;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
	
	
public class Gif_anime extends JPanel{

	public Gif_anime() throws MalformedURLException{
        URL url = new URL("images/loading.gif");
        Icon gif = new ImageIcon(url);
        JLabel label = new JLabel(gif);
        setVisible(true);
        
        
        
        
        
        //JFrame f = new JFrame("Animation");
        //f.getContentPane().add(label);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.pack();
        //f.setLocationRelativeTo(null);
        //f.setVisible(true);	
	}
	
    public static void main(String[] args){}

}
