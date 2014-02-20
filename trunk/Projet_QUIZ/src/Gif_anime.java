import javax.swing.*;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
	
	
public class Gif_anime extends JPanel{


        private final Image image;

        public Gif_anime(Image image){
            this.image = image;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 94, 90, this);
        }



    }


