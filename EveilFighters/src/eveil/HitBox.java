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
	
	public HitBox() {
		hg_ = new Point (0, 0);
		hd_ = new Point (0, 0);
		bg_ = new Point (0, 0);
		bd_ = new Point (0, 0);
	}
	
	public HitBox(int x, int y, int taille, int largeur) {
		hg_ = new Point(x, y+3*(taille/4));
		hd_ = new Point(x+largeur, y+3*(taille/4));
		bg_ = new Point(x, y+taille);
		bd_ = new Point(x+largeur, y+taille);
		System.out.println("hg : "+hg_+"hd : "+hd_+"bg : "+bg_+"bd : "+bd_);
	}
	
	public void setHB(int x, int y, int taille, int largeur) {
		hg_.setLocation(x, y+3*(taille/4));
		hd_.setLocation(x+largeur, y+3*(taille/4));
		bg_.setLocation(x, y+taille);
		bd_.setLocation(x+largeur, y+taille);
		System.out.println("hg : "+hg_+"hd : "+hd_+"bg : "+bg_+"bd : "+bd_);
	}
	
	public Point getHG() { return hg_;}
	public Point getHD() { return hd_;}
	public Point getBG() { return bg_;}
	public Point getBD() { return bd_;}
		
}
