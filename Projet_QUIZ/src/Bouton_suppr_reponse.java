import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Bouton_suppr_reponse extends JButton implements MouseListener{

	public void bouton_suppr_reponse(){
		JButton bouton = new JButton();
		bouton.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(Admin_ajout_reponses.list.getItemCount() > 0){
			System.out.println("DEMANDE SUPPRESSION");
			Admin_ajout_reponses.list.remove(Admin_ajout_reponses.list.getSelectedItem());
			//Admin_ajout_reponses.list.removeAll();
		}else {System.out.println("ERREUR SUPPRESSION");}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
