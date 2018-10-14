package eveil;

public abstract class Item {
	protected int x_;	
	protected int y_;
	protected int xImage_;
	protected int yImage_;
	protected int taille_;
	protected int largeur_;
	protected String nom_;
	protected String image_;
	protected int facteurGrandeur_;
	protected HitBox hitBox_;
	protected int ajustX_;
	protected int ajustY_;
	
	public Item() {
		taille_ = 0;
		largeur_ = 0;
		facteurGrandeur_ = 4;
		ajustX_ = 0;
		ajustY_ = 0;
	}

	/* fonction à définir dans toute classe fille de Item qui est
	 * non abstraite (ex : arbre) et dans ItemDyn. Dans les deux cas il faut appeler un constructeur de HitBox différent
	 */
	public abstract void initPos(int x, int y);
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
	abstract public String getImage();
	abstract public void ajusterAffichage();
}
