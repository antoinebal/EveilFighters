package eveil;

/* cette classe rassemble les objets qui bougeront sur la carte
 * donc les boules de feu, les gobelins, les goules
 */
public abstract class ItemDyn extends Item {
	
	//la classe fille instancie tous les attributs à sa guise
	protected int vitesse_;
	protected int animWalk_;
	protected char orientation_;
	protected int compteurTic_;
	protected int maxAnimWalk_;
	protected int palierAnimWalk_;
	protected int maxTic_ ;
	protected int palierTic_;

	public ItemDyn() {
		super();
	}
	
	public void initPos(int x, int y) {
		x_=x; 
		y_=y;
		xImage_ = x_; 
		yImage_ = y_ - 2*(taille_/3); 
		hitBox_ = new HitBox(x_, y_, taille_, largeur_, this);
	}
	
	public abstract void setX(int x);
	public abstract void setY(int y);
	public void setOrientation(char dir){ orientation_ = dir;}
	public abstract int handleAnimWalk();
	public abstract int handleTic();
	public abstract void tic();
	public int getVit() {return vitesse_; }
	public char getOrient()	{return orientation_;}
	//public abstract boolean checkCollision(Item i);
	
}
