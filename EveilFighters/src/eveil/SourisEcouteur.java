package eveil;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SourisEcouteur implements MouseListener {
	
	private boolean clicGauche_;
	private boolean clicDroit_;
	private Point position_;

	
	public SourisEcouteur() {
		clicGauche_= false;
		clicDroit_ = false;
		position_ = new Point(0, 0);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton()==MouseEvent.BUTTON1) {
			clicGauche_ = true;
			position_.setLocation(arg0.getPoint());
		} else if (arg0.getButton()==MouseEvent.BUTTON2) {
			clicDroit_ = true;
		}	 
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
		if (arg0.getButton()==MouseEvent.BUTTON1) {
			clicGauche_ = true;
		} else if (arg0.getButton()==MouseEvent.BUTTON2) {
			clicDroit_ = true;
		}
		
	}
	
	public boolean getClicGauche() {return clicGauche_;}

	public boolean getClicDroit() {return clicDroit_;}
	
	public Point getPosition() {return position_;}
	
}