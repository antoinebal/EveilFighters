package eveil;

import java.lang.String;
//import javax.swing.ImageIcon;



public class Personnage {

	//1 ou 2
	protected int num_;
	
	protected int pvs_;

	//dégâts du coup au corps-à-corps
	protected int force_;
	
	//portée du coup au corps-à-corps
	protected int porteeCC_;
	
	//position suivant x
	protected int x_;
	
	//position suivant y
	protected int y_;
	
	//vitesse de déplacement
	protected int vitessePerso_;
	
	//taille du perso
	protected int taille_;
	protected int largeur_;
	
	protected String nom_;
	
	protected String image_;
	
	/*cet attribut permet l'animation de la marche
	 * il est incrémenté à chaque appel de setX et setY*/ 
	protected int animWalk_;
	
	/*mémoire pour savoir dans quelle direction le personnage est
	 * en train de se déplacer. Peut être égal à 'u', 'd', 'l', et 'r' */
	protected char orientation_;
	
	/*pointeur vers l'adversaire (pour lui donner des coups*/
	protected Personnage adversaire_;
	
	/*compte le temps en fonction de l'avancée du programme*/
	protected int compteurTic_;
	
	/*en marche, en train de frapper etc.
	0 = idle, 1 = walking, 2 = hitting, 3 = mort ... */
	protected int etat_;
	
	//protected ImageIcon imageIcon_;
	
	/* facteur appelé dans setLarg et setTaille, eux mêmes appelés
	 * par le PanelJoueur pour dire au personnage de prendre en compte
	 * la taille de son image dans ses largeurs et tailles
	 */
	protected int facteurGrandeur_=4;
	
	protected HitBox hitBox_;

	
	//constructeur par défaut
	public Personnage() {
		x_=0;
		y_=0;
		
		vitessePerso_=2;
		
		pvs_ = 0;
		force_ = 0;
		porteeCC_ = 0;
		nom_ = "Drassius";
		
		//taille_ = 40;
		//largeur_ = 35;
		
		orientation_ = 'd';
		
		image_=nom_+"_down_1.png";
		animWalk_ = 0;
		
		compteurTic_ = 0;
		etat_ = 0; //idle
		
		hitBox_ = new HitBox();

	}
	
	
	//constructeur du j1
	//dans le futur mettre un if en fonction du nom pour construire le perso
	public Personnage(int pvs, int force, int porteeCC, String nom) {
		x_=0;
		y_=0;
		
		vitessePerso_=3;
		
		num_ = 1;
		pvs_ = pvs;
		force_ = force;
		porteeCC_ = porteeCC;
		nom_ = nom;
		
		//taille_ = 40;
		//largeur_ = 40;
		
		orientation_ = 'd';
		
		image_=nom_+"_down_1.png";
		
		compteurTic_ = 0;
		etat_ = 0; //idle
		
		hitBox_ = new HitBox();
	}
	
	//constructeur du j2
	//dans le futur mettre un if en fonction du nom pour construire le perso
	public Personnage(int pvs, int force, int porteeCC, String nom, Personnage adversaire) {
		x_=120;
		y_=120;
		
		vitessePerso_=3;
		
		num_ = 2;
		pvs_ = pvs;
		force_ = force;
		porteeCC_ = porteeCC;
		nom_ = nom;
		
		//taille_ = 40;
		//largeur_ = 40;
		
		orientation_ = 'd';
		
		image_=nom_+"_down_1.png";
		
		adversaire_ = adversaire;
		
		compteurTic_ = 0;
		etat_ = 0; //idle
		
		hitBox_ = new HitBox();
	}
	
	/*pour finaliser la constrution du j1*/
	public void setAdversaire(Personnage adversaire) {adversaire_ = adversaire;}
	
	
	/* je passe l'était à 1 (walking) pour montrer que le
	 * personnage bute contre le mur
	 */
	public void setX(int x) { 
		//On ne fait rien si on est en train de frapper ou autre
		if (etat_ <= 1) {
		etat_=1;
		/*si l'orientation n'est pas u et que l'on a monté,
		 * on change l'orientation
		 */
		if ((orientation_ != 'l')&&(x < x_)) {
			orientation_ = 'l';
		}
		
		/*si l'orientation n'est pas d et que l'on a descendu,
		 * on change l'orientation
		 */
		if ((orientation_ != 'r')&&(x > x_)) {
			orientation_ = 'r';
		}
		
		animWalk_ = (animWalk_+1) % 120;
		x_=x;
		
		//on maj la hitbox
		hitBox_.setHB(x_, y_, taille_, largeur_);
		}
	}
	/* je passe l'était à 1 (walking) pour montrer que le
	 * personnage bute contre le mur
	 */
	public void setY(int y) { 
		//On ne fait rien si on est en train de frapper ou autre
		if (etat_ <= 1) {
		etat_ = 1;
		/*si l'orientation n'est pas u et que l'on a monté,
		 * on change l'orientation
		 */
		if ((y < y_)) {
			orientation_ = 'u';
		}
		
		/*si l'orientation n'est pas d et que l'on a descendu,
		 * on change l'orientation
		 */
		if ((y > y_)) {
			orientation_ = 'd';
		}
		
		animWalk_ = (animWalk_+1) % 120;
		y_=y;
		hitBox_.setHB(x_, y_, taille_, largeur_);
		}
		}
	public void setPos(int x, int y) { x_=x; y_=y;}
	
	//dir = 'u' 'd' 'l' 'r'
	public void setOrientation(char dir) { orientation_ = dir;}
	
	public void setTaille(int taille) {taille_ = taille*facteurGrandeur_; hitBox_.setHB(x_, y_, taille_, largeur_);}
	public void setLarg(int largeur) {largeur_ = largeur*facteurGrandeur_; hitBox_.setHB(x_, y_, taille_, largeur_);}
	public void setGrandeur(int taille, int largeur) {
		taille_ = taille*facteurGrandeur_;
		largeur_ = largeur*facteurGrandeur_;
		hitBox_.setHB(x_, y_, taille_, largeur_);
	}
	
	public int handleAnimWalk() {
		if (animWalk_ < 30 ) {return 0;}
		if (animWalk_ < 60 ) {return 1;}
		if (animWalk_ < 90 ) {return 0;}
		if (animWalk_ < 120 ) {return 2;}
		return -1;
	}
	
	public int handleTic() {
		if (compteurTic_ < 10 ) {return 0;}
		if (compteurTic_ < 20 ) {return 1;}
		if (compteurTic_ < 30 ) {return 2;}
		if (compteurTic_ < 40 ) {return 3;}
		return -1;
	}
	
	public void coup0() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso est accès au joueur pour prendre ses cos
		 */
		//faire un grand if en fonction du nom
		//appeler les fonctions recevoir coup si jamais l'autre joueur est touché
		etat_ = 2;
		System.out.println("Je donne un coup d'épée.");
		if (orientation_ == 'l') {
			if ((getX()-porteeCC_ < adversaire_.getX()+adversaire_.getLarg())
				&&(getX()-porteeCC_ > adversaire_.getX())
				&&(getY() < adversaire_.getY()+adversaire_.getTaille())
				&&(getY()+getTaille() > adversaire_.getY())) {adversaire_.decPvs(force_);} else {System.out.println("LOUPE");}}
		if (orientation_ == 'r') {
			if ((getX()+getLarg()+porteeCC_ < adversaire_.getX()+adversaire_.getLarg())
				&&(getX()+getLarg()+porteeCC_ > adversaire_.getX())
				&&(getY() < adversaire_.getY()+getTaille())
				&&(getY()+getTaille() > adversaire_.getY())) {adversaire_.decPvs(force_);} else {System.out.println("LOUPE");}}
		if (orientation_ == 'u') {
			if ((getY()-porteeCC_ < adversaire_.getY()+adversaire_.getTaille())
				&&(getY()+porteeCC_ > adversaire_.getY())
				&&(getX() < adversaire_.getX()+adversaire_.getLarg())
				&&(getX()+getLarg() > adversaire_.getX())) {adversaire_.decPvs(force_);} else {System.out.println("LOUPE");}}
		if (orientation_ == 'd') {
			if ((getY()+getTaille()+porteeCC_ < adversaire_.getY()+adversaire_.getTaille())
				&&(getY()+getTaille()+porteeCC_ > adversaire_.getY())
				&&(getX() < adversaire_.getX()+adversaire_.getLarg())
				&&(getX()+getLarg() > adversaire_.getX())) {adversaire_.decPvs(force_);} else {System.out.println("LOUPE");}}
		}
	
	
	public void coup1() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso est accès au joueur pour prendre ses cos
		 */
		etat_ = 2;
		System.out.println("Je lance une boule de feu.");
	}
	
	public void coup2() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso est accès au joueur pour prendre ses cos
		 */
		etat_ = 2;
		System.out.println("J'appelle mes sbires.");
	}
	
	public void tic() {
		//si on est à l'arrêt, en train de marcher ou mort on ne remet pas setEtat à 0
		if ((etat_!=0)&(etat_!=1)&(etat_!=3)) {
		if ((compteurTic_ >=40)) {
		setEtat(0);
		compteurTic_=0;
		} else {
		++compteurTic_;
		}
		}
	}

	public void setEtat(int etat) {
		etat_ = etat;
	}
	
	public int getX() {return x_;}
	public int getY() {return y_;}
	public int getVit() {return vitessePerso_; }
	
	public int getNum() {return num_;}
	
	public int getTaille() {return taille_;}
	public int getLarg() {return largeur_;}
	
	public String getName() {return nom_;	}
	
	public int getEtat() {return etat_;}
	public char getOrient() {return orientation_;}
	public HitBox getHB() {return hitBox_;}
	
	public String getImage() {
		//si le personnage est à l'arrêt
		if (etat_ == 0) {
		if (orientation_ == 'u') {
			image_ = nom_+"_up_0.png";
			
		}
		if (orientation_ == 'd') {
			image_ = nom_+"_down_0.png";
		}
		if (orientation_ == 'l') {
			image_ = nom_+"_left_0.png";
		}
		if (orientation_ == 'r') {
			image_ = nom_+"_right_0.png";
		}
		}
		
		
		//si le personnage est en train de marcher
		if (etat_ == 1) {
		if (orientation_ == 'u') {
			image_ = nom_+"_up_"+Integer.toString(handleAnimWalk())+".png";
		}
		if (orientation_ == 'd') {
			image_ = nom_+"_down_"+Integer.toString(handleAnimWalk())+".png";
		}
		if (orientation_ == 'l') {
			image_ = nom_+"_left_"+Integer.toString(handleAnimWalk())+".png";
		}
		if (orientation_ == 'r') {
			image_ = nom_+"_right_"+Integer.toString(handleAnimWalk())+".png";
		}
		}
		//si les personnage est en train de frapper
		//j'ai mis des up partout pour le TEST
		if (etat_ == 2) {
		if (orientation_ == 'u') {
			image_ = nom_+"_up_C0_"+Integer.toString(handleTic())+".png";
			//return "steve.jpeg";
		}
		if (orientation_ == 'd') {
			image_ = nom_+"_down_C0_"+Integer.toString(handleTic())+".png";
			//return "steve.jpeg";
		}
		if (orientation_ == 'l') {
			image_ = nom_+"_left_C0_"+Integer.toString(handleTic())+".png";
			//return "steve.jpeg";
		}
		if (orientation_ == 'r') {
			image_ = nom_+"_right_C0_"+Integer.toString(handleTic())+".png";
			//return "steve.jpeg";
		}
		
		}
		
		//si le personnage est mort
		if (etat_ == 3) { image_ = "steve.jpeg";}
		
	
		/*imageIcon_.setDescription(image);
		System.out.println(imageIcon_.getDescription());
		largeur_ = imageIcon_.getImage().getWidth(null);
		taille_ = imageIcon_.getImage().getHeight(null);*/
		
		return image_;
	}
	
	void decPvs(int degats) {
		pvs_ -= degats;
		//si ses pvs sont négatifs le personnage meurt
		if (pvs_ <= 0) {etat_ = 3;}
		System.out.println("AIE il me reste "+pvs_);
	}
	
}
