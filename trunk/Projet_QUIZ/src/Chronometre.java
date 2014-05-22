import javax.swing.*;
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
	        // Mï¿½thode appelï¿½e ï¿½ chaque tic du timer
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
	      
	    // Crï¿½ation d'un timer qui gï¿½nï¿½re un tic
	    // chaque 1000 milliï¿½me de seconde soit une seconde ! hï¿½ ouais maggle !
	    return new Timer (1000, action);
	}
	
	public void finTimer(long chrono){
		if(chrono == 0){
			tpsRestant = "0 h 0 min 0 sec";
			timer.stop();
			JOptionPane.showMessageDialog(null, 
					"<html>Le temps est &eacute;coul&eacute;.<br/> Vous allez &eacute;tre redirig&eacute; vers la correction.</html>", 
					"Temps écoulé !", 
					JOptionPane.WARNING_MESSAGE);
			partie_en_cours.validerPartie();
		}
	}
	
	public void stopTimer(){
		timer.stop();
	}
	public void startTimer() {
		timer.start();
	}

}
