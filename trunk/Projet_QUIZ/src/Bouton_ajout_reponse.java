import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Bouton_ajout_reponse extends JButton implements MouseListener{

	
	
	public void Bouton_ajout_reponse(){
		JButton bouton = new JButton();
		bouton.addMouseListener((MouseListener) bouton);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Admin_ajout_reponses.list.getItemCount() < 10 && Admin_ajout_reponses.reponse.getText().length() != 0){
			Admin_ajout_reponses.list.add(Admin_ajout_reponses.reponse.getText());
			Admin_ajout_reponses.reponse.setText("");
		}else{System.out.println("ERRUER LIST PLEINE");}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
