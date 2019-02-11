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
	
	public ItemDyn(String nom) {
		super(nom);
	}
	
	public void initPos(int x, int y) {
		x_=x; 
		y_=y;
		xImage_ = x_; 
		yImage_ = y_ - 2*(taille_/3); 
		hitBox_ = new HitBox(x_, y_, taille_, largeur_, this);
	}
	
	public void setOrientation(char dir){ orientation_ = dir;}
	public int getVit() {return vitesse_; }
	public char getOrient()	{return orientation_;}
	//public abstract boolean checkCollision(Item i);
	
	public abstract int handleAnimWalk();
	public abstract int handleTic();
	public abstract void tic();
	public abstract void setX(int x);
	public abstract void setY(int y);
	
	/* renvoie vrai s'il va y avoir une collision avec l'item en argument */
	public boolean checkCollisionAvec(Item item) {return getHB().checkCollision(getVit(), item.getHB());}
	
	public static void main(String[] args) {
		Personnage j1 = new Lucas();

		
		j1.initPos(60, 90);
		
		
		Item iArbre = new Item("arbre");
		iArbre.initPos(500, 500);
		
		if (j1.checkCollisionAvec(iArbre)) {
			System.out.println("BOOM");
		} else {
			System.out.println("OLE");
		}
		
		System.out.println(j1.getClass().getSuperclass().getSuperclass().getName());

	}
	
}
