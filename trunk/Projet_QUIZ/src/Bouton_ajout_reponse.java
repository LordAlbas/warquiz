import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Bouton_ajout_reponse extends JButton implements MouseListener {
	
	private List list_reponses;
	
	/**
	 * CONSTRUCTOR
	 * @param name
	 */
	public Bouton_ajout_reponse(String name, List list_rep){
		super(name);
		addMouseListener(this);
		list_reponses = list_rep;
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(list_reponses.getItemCount() < 10 && Admin_ajout_reponses.reponse.getText().length() != 0){
			list_reponses.add(Admin_ajout_reponses.reponse.getText());
			Admin_ajout_reponses.reponse.setText("");
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}