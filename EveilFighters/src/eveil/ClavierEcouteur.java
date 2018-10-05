package eveil;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClavierEcouteur implements KeyListener {
	
	//player1
	private boolean up1_;
	private boolean down1_;
	private boolean left1_;
	private boolean right1_;
	
	//player 2
	private boolean up2_;
	private boolean down2_;
	private boolean left2_;
	private boolean right2_;
	
	public ClavierEcouteur() {
		up1_ = false;
		down1_ = false;
		left1_ = false;
		right1_ = false;
		
		up2_ = false;
		down2_ = false;
		left2_ = false;
		right2_ = false;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==38) { up1_ = true;} 
		if (arg0.getKeyCode()==40) { down1_ = true;}
		if (arg0.getKeyCode()==37) { left1_ = true;}
		if (arg0.getKeyCode()==39) { right1_ = true;}
		
		if (arg0.getKeyCode()==90) { up2_ = true;} //z
		if (arg0.getKeyCode()==83) { down2_ = true;} //s
		if (arg0.getKeyCode()==81) { left2_ = true;} //q
		if (arg0.getKeyCode()==68) { right2_ = true;} //d
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode()==38) { up1_ = false;} 
		if (arg0.getKeyCode()==40) { down1_ = false;}
		if (arg0.getKeyCode()==37) { left1_ = false;}
		if (arg0.getKeyCode()==39) { right1_ = false;}
		
		if (arg0.getKeyCode()==90) { up2_ = false;} //z
		if (arg0.getKeyCode()==83) { down2_ = false;} //s
		if (arg0.getKeyCode()==81) { left2_ = false;} //q
		if (arg0.getKeyCode()==68) { right2_ = false;} //d
		
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
	

	
}