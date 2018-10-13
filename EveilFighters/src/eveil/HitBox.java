package eveil;

import java.awt.Point;

public class HitBox {
		
	//Point en haut à gauche de la hitbox
	private Point hg_;
		
	//Point en haut à droite de la hitbox
	private Point hd_;

	//Point en bas à gauche de la hitbox
	private Point bg_;
	
	//Point en bas à droite de la hitbox
	private Point bd_;
	
	//Pour que la taille de la HitBox soit fixe
	private int tailleDeBase_;
	private int largeurDeBase_;
	
	
	public HitBox() {
		hg_ = new Point (0, 0);
		hd_ = new Point (0, 0);
		bg_ = new Point (0, 0);
		bd_ = new Point (0, 0);
		
		tailleDeBase_=0;
		largeurDeBase_=0;
	}
	
	public HitBox(int x, int y, int taille, int largeur) {
		tailleDeBase_=taille-2*(taille/3);
		largeurDeBase_=largeur;
		
		hg_ = new Point(x, y);
		hd_ = new Point(x+largeurDeBase_, y);
		bg_ = new Point(x, y+tailleDeBase_);
		bd_ = new Point(x+largeurDeBase_, y+tailleDeBase_);
		//System.out.println("hg : "+hg_+"hd : "+hd_+"bg : "+bg_+"bd : "+bd_);
	}
	
	public void setHB(int x, int y) {
		
		
		hg_ = new Point(x, y);
		hd_ = new Point(x+largeurDeBase_, y);
		bg_ = new Point(x, y+tailleDeBase_);
		bd_ = new Point(x+largeurDeBase_, y+tailleDeBase_);
		//System.out.println("hg : "+hg_+"hd : "+hd_+"bg : "+bg_+"bd : "+bd_);
	}
	
	public Point getHG() { return hg_;}
	public Point getHD() { return hd_;}
	public Point getBG() { return bg_;}
	public Point getBD() { return bd_;}
	public int getTBase() { return tailleDeBase_;}
	public int getLBase() { return largeurDeBase_;}
		
}
