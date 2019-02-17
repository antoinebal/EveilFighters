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
	
	private ItemDyn j_;
	
	//private double RATIO_SUR_Y = 2/3;
	
	
	public HitBox() {
		hg_ = new Point (0, 0);
		hd_ = new Point (0, 0);
		bg_ = new Point (0, 0);
		bd_ = new Point (0, 0);
		
		tailleDeBase_=0;
		largeurDeBase_=0;
	}
	
	
	//constructeur pour item dynamique
	public HitBox(int x, int y, int taille, int largeur, ItemDyn j) {
		tailleDeBase_=taille-2*(taille/3);
		largeurDeBase_=largeur;
		
		hg_ = new Point(x, y);
		hd_ = new Point(x+largeurDeBase_, y);
		bg_ = new Point(x, y+tailleDeBase_);
		bd_ = new Point(x+largeurDeBase_, y+tailleDeBase_);
		System.out.println("hg : "+hg_+"hd : "+hd_+"bg : "+bg_+"bd : "+bd_);
		j_ = j;
	}
	
	//constructeur pour item fixe
	public HitBox(int x, int y, int taille, int largeur) {
		tailleDeBase_=taille-2*(taille/3);
		largeurDeBase_=largeur;
		
		hg_ = new Point(x, y);
		hd_ = new Point(x+largeurDeBase_, y);
		bg_ = new Point(x, y+tailleDeBase_);
		bd_ = new Point(x+largeurDeBase_, y+tailleDeBase_);
		System.out.println("hg : "+hg_+"hd : "+hd_+"bg : "+bg_+"bd : "+bd_);
		
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
	
	/*renvoie vrai si le mouvement de cette hitbox par ce différentiel
	 * provoquerait une collision avec hBF.
	 * Diff peut être la vitesse de l'item ou la portée d'un coup
	 * Cette fonction ne doit être appelée que si l'item est dynamique
	 */
	public boolean checkCollision(int diff, HitBox hBF) {
		if (j_.getOrient() == 'l') {
			if (((getHG().getX()-diff) < hBF.getHD().getX())
				&&((getHG().getX()-diff) > hBF.getHG().getX())
				&&(getHG().getY() < hBF.getBG().getY())
				&&(getBG().getY() > hBF.getHG().getY())) {return true;} else {return false;}}
		if (j_.getOrient() == 'r') {
			if (((getHD().getX()+diff) < hBF.getHD().getX())
				&&((getHD().getX()+diff) > hBF.getHG().getX())
				&&(getHG().getY() < hBF.getBG().getY())
				&&(getBG().getY() > hBF.getHG().getY())) {return true;} else {return false;}}
		if (j_.getOrient() == 'u') {
			if (((getHG().getY()-diff) < hBF.getBG().getY())
				&&((getHG().getY())-diff > hBF.getHG().getY())
				&&(getHG().getX() < hBF.getHD().getX())
				&&(getHD().getX() > hBF.getHG().getX())) {return true;} else {return false;}}
		if (j_.getOrient() == 'd') {
			if (((getBG().getY()+diff) < hBF.getBG().getY())
				&&((getBG().getY()+diff) > hBF.getHG().getY())
				&&(getHG().getX() < hBF.getHD().getX())
				&&(getHD().getX() > hBF.getHG().getX())) {return true;} else {return false;}}
			
	return false;
	}
	
	//appel�e par map : retourne true si les 2 hitbox se superposent
	public boolean estSuperposeeAvec(HitBox hBF) {
		if (((getHG().getX()) <= hBF.getHD().getX())
				&&((getHG().getX()) >= hBF.getHG().getX())
				&&(getHG().getY() <= hBF.getBG().getY())
				&&(getBG().getY() >= hBF.getHG().getY())) {return true;}
		
		if (((getHD().getX()) < hBF.getHD().getX())
				&&((getHD().getX()) >= hBF.getHG().getX())
				&&(getHG().getY() <= hBF.getBG().getY())
				&&(getBG().getY() >= hBF.getHG().getY())) {return true;}
		
		if (((getHG().getY()) <= hBF.getBG().getY())
				&&((getHG().getY()) >= hBF.getHG().getY())
				&&(getHG().getX() <= hBF.getHD().getX())
				&&(getHD().getX() >= hBF.getHG().getX())) {return true;}
		
		if (((getBG().getY()) <= hBF.getBG().getY())
				&&((getBG().getY()) >= hBF.getHG().getY())
				&&(getHG().getX() <= hBF.getHD().getX())
				&&(getHD().getX() >= hBF.getHG().getX())) {return true;}
		
		return false;
	}
	
	
		
}
