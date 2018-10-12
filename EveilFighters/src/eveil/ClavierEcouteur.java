package eveil;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClavierEcouteur implements KeyListener {
	
	//player1
	private boolean up1_;
	private boolean down1_;
	private boolean left1_;
	private boolean right1_;
	private boolean coup0_1_;
	private boolean coup1_1_;
	private boolean coup2_1_;
	
	//player 2
	private boolean up2_;
	private boolean down2_;
	private boolean left2_;
	private boolean right2_;
	private boolean coup0_2_;
	private boolean coup1_2_;
	private boolean coup2_2_;
	
	//pour changer l'état des joueurs
	private Personnage j1_;
	private Personnage j2_;
	
	public ClavierEcouteur(Personnage j1, Personnage j2) {
		up1_ = false;
		down1_ = false;
		left1_ = false;
		right1_ = false;
		
		up2_ = false;
		down2_ = false;
		left2_ = false;
		right2_ = false;
		
		j1_ = j1;
		j2_ = j2;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==38) { up1_ = true;} //arrows
		if (arg0.getKeyCode()==40) { down1_ = true;} 
		if (arg0.getKeyCode()==37) { left1_ = true;}
		if (arg0.getKeyCode()==39) { right1_ = true;}
		
		if (arg0.getKeyCode()==96) { coup0_1_ = true;} //0
		if (arg0.getKeyCode()==97) { coup1_1_ = true;} //1
		if (arg0.getKeyCode()==98) { coup2_1_ = true;} //2
		
		if (arg0.getKeyCode()==90) { up2_ = true;} //z
		if (arg0.getKeyCode()==83) { down2_ = true;} //s
		if (arg0.getKeyCode()==81) { left2_ = true;} //q
		if (arg0.getKeyCode()==68) { right2_ = true;} //d
		
		if (arg0.getKeyCode()==32) { coup0_2_ = true;} //space
		if (arg0.getKeyCode()==86) { coup1_1_ = true;} //v
		if (arg0.getKeyCode()==78) { coup2_1_ = true;} //n
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		/*si le joueur était en état walking on le met en IDLE*/
		if (arg0.getKeyCode()==38) { up1_ = false; if (j1_.getEtat()=='w') {j1_.setEtat('i');}} //arrows
		if (arg0.getKeyCode()==40) { down1_ = false; if (j1_.getEtat()=='w') {j1_.setEtat('i');}}
		if (arg0.getKeyCode()==37) { left1_ = false; if (j1_.getEtat()=='w') {j1_.setEtat('i');}}
		if (arg0.getKeyCode()==39) { right1_ = false; if (j1_.getEtat()=='w') {j1_.setEtat('i');}}
		
		if (arg0.getKeyCode()==96) { coup0_1_ = false;} //0
		if (arg0.getKeyCode()==97) { coup1_1_ = false;} //1
		if (arg0.getKeyCode()==98) { coup2_1_ = false;} //2
		
		/*si le joueur était en état walking on le met en IDLE*/
		if (arg0.getKeyCode()==90) { up2_ = false; if (j2_.getEtat()=='w') {j2_.setEtat('i');}} //z
		if (arg0.getKeyCode()==83) { down2_ = false; if (j2_.getEtat()=='w') {j2_.setEtat('i');}} //s
		if (arg0.getKeyCode()==81) { left2_ = false; if (j2_.getEtat()=='w') {j2_.setEtat('i');}} //q
		if (arg0.getKeyCode()==68) { right2_ = false; if (j2_.getEtat()=='w') {j2_.setEtat('i');}} //d
		
		if (arg0.getKeyCode()==32) { coup0_2_ = false;} //space
		if (arg0.getKeyCode()==86) { coup1_1_ = false;} //v
		if (arg0.getKeyCode()==78) { coup2_1_ = false;} //n
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//ces guetteurs renvoient le up en fonction du num du joueur
	public boolean getUp(int p) {
	if (p==1) {return up1_;} else { return up2_;}}
	public boolean getDown(int p) { 
	if (p==1) {return down1_;} else { return down2_;}}
	public boolean getLeft(int p) { 
	if (p==1) {return left1_;} else { return left2_;}}
	public boolean getRight(int p) {
	if (p==1) {return right1_;} else { return right2_;}}
	
	//on replace les attributs à 0 une fois qu'ils sont lus pour pas que les fonctions coups
	//soient appelés plein de fois
	public boolean getC0(int p) {
	if (p==1) {
		if (coup0_1_) {
			coup0_1_ = false;
			return true;
		} else {return false;}
	} else { 
		if (coup0_2_) {
			coup0_2_ = false;
			return true;
		} else {return false;}
	}
	}
	
	public boolean getC1(int p) {
	if (p==1) {
		if (coup1_1_) {
			coup1_1_ = false;
			return true;
		} else {return false;}
	} else { 
		if (coup1_2_) {
			coup1_2_ = false;
			return true;
		} else {return false;}
	}
	}
	
	public boolean getC2(int p) {
	if (p==1) {
		if (coup2_1_) {
			coup2_1_ = false;
			return true;
		} else {return false;}
	} else { 
		if (coup2_2_) {
			coup2_2_ = false;
			return true;
		} else {return false;}
	}
	}
}