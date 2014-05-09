import java.lang.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

public class Chronometre {
	private Timer timer;
	private long sec;
	private long min;
	private long hrs;
	private long chrono;
	private long chronoInitial;
	private String tpsRestant;
	private Quiz monQuiz;
	private Fenetre fenetre;
	private Jouer_partie partie_en_cours;
	
	public Chronometre(Fenetre fen, Jouer_partie partie, Quiz quiz, long _hrs, long _min, long _sec){
		sec = _sec;
		min = _min;
		hrs = _hrs;
		monQuiz = quiz;
		fenetre = fen;
		partie_en_cours = partie;
		tpsRestant = hrs+" h "+min+" min "+sec+" sec";
		chronoInitial = hrs*3600 + min*60 + sec; 
		chrono = hrs*3600 + min*60 + sec;
		timer = createTimer();
		timer.start();
		System.out.println(timer);
	}
	
	public long getChronoInital(){
		return chronoInitial;
	}
	
	public long getChrono(){
		return chrono;
	}
	
	public String getTpsRestant(){
		return tpsRestant;
	}
	
	public long getSec(){
		return sec;
	}
	
	public long getMin(){
		return min;
	}
	
	public long getHrs(){
		return hrs;
	}
	
	private Timer createTimer (){
	    ActionListener action = new ActionListener (){
	        // Méthode appelée à chaque tic du timer
	        public void actionPerformed (ActionEvent event){
	        	chrono -= 1;
	        	sec = chrono%60;
	        	min = (chrono/60)%60;
	        	hrs = (chrono/3600)%60;
	        	//System.out.println("Timer : "+hrs+"h "+min+"min "+sec+"sec");
	        	finTimer(chrono);
	        	tpsRestant = hrs+" h "+min+" min "+sec+" sec";
	        }
	      };
	      
	    // Création d'un timer qui génère un tic
	    // chaque 1000 millième de seconde soit une seconde ! hé ouais maggle !
	    return new Timer (1000, action);
	}
	
	public void finTimer(long chrono){
		if(chrono == 0){
			tpsRestant = "0 h 0 min 0 sec";
			timer.stop();
			int rep_deco = JOptionPane.showConfirmDialog(null, 
					"<html>Le temps est écoulé, voulez vous voir <br/>la correction avant de quitter ?</html>", 
					"Temps écoulé !", 
					JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.WARNING_MESSAGE);
			if(rep_deco == 0){
				System.out.println("Redirige correction");
				fenetre.goToCorrection(monQuiz);
			}else {fenetre.goToAccueil("");}
		}
	}
	
	public void stopTimer(){
		timer.stop();
	}

}
