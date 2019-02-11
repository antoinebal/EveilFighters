package eveil;

import javax.swing.ImageIcon;

public class Item {
	protected int x_;	
	protected int y_;
	protected int xImage_;
	protected int yImage_;
	protected int taille_;
	protected int largeur_;
	protected String nom_;
	protected String image_;
	protected int facteurGrandeur_=3;
	protected HitBox hitBox_;
	protected int ajustX_;
	protected int ajustY_;
	
	public Item() {
		ajustX_ = 0;
		ajustY_ = 0;
	}
	
	//constructeur pour tout item
	public Item(String nom) {
		ajustX_ = 0;
		ajustY_ = 0;
		
		nom_ = nom;
		
		image_="data/"+nom_+".png";
		
		//premières instanciations des dimensions (utiles pour la hitbox)
		//OPTIM : faure cela que pour les items fixes car perso le refait
		setTaille(new ImageIcon(image_).getImage().getHeight(null));
		setLarg(new ImageIcon(image_).getImage().getWidth(null));
		
		System.out.println("TAILLE "+nom_+" : taille : "+taille_+"largeur : "+largeur_);
	}
	
	
	
	

	/* fonction à définir dans toute classe fille de Item qui est
	 * non abstraite (ex : arbre) et dans ItemDyn. Dans les deux cas il faut appeler un constructeur de HitBox différent
	 */
	
	public void setTaille(int taille) {taille_ = taille*facteurGrandeur_;}
	public void setLarg(int largeur) {largeur_ = largeur*facteurGrandeur_;}
	public void setGrandeur(int taille, int largeur) {
		taille_ = taille*facteurGrandeur_;
		largeur_ = largeur*facteurGrandeur_;
	}

	public int getX() {return x_;}
	public int getY() {return y_;}
	public int getTaille() {return taille_;}
	public int getLarg() {return largeur_;}
	public String getName() {return nom_;}
	public HitBox getHB() {return hitBox_;}
	public int getXImage() {return xImage_;}
	public int getYImage() {return yImage_;}
	public int getAjustX() {return ajustX_;}
	public int getAjustY() {return ajustY_;}
	public String getImage() {return "data/"+nom_+".png";}
	public void ajusterAffichage() {ajustX_ = 0 ; ajustY_ = 0;}
	
	public String toString() {return nom_+" : "+y_;}
	
	public void collisionAvec(Item i) {
		System.out.println(nom_+ " se cogne avec "+i.getName());
	}
	
	public void initPos(int x, int y) {
		x_=x; 
		y_=y;
		xImage_ = x_; 
		yImage_ = y_ - 2*(taille_/3); 
		hitBox_ = new HitBox(x_, y_, taille_, largeur_);
	}
}
