import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class Bouton_suppr_reponse extends JButton implements MouseListener{
	
	private List list_reponses;
	
	public Bouton_suppr_reponse(String name, List list_rep){
		super(name);
		addMouseListener(this);
		list_reponses = list_rep;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(list_reponses.getItemCount() > 0){
			System.out.println("DEMANDE SUPPRESSION");
			list_reponses.remove(list_reponses.getSelectedItem());
		} else {
			System.out.println("ERREUR SUPPRESSION");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}