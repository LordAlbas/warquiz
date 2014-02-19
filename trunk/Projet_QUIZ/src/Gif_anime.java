import javax.swing.*;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
	
	
public class Gif_anime extends JPanel{


        private Image image;

        public Gif_anime(Image image){
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image, 100, 100, this);
        }

        @Override
       public Dimension getPreferredSize(){
            return new Dimension(this.getWidth(), this.getHeight());
        }

    }


