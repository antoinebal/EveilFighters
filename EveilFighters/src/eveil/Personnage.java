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
	i = idle, w = walking, 0 = hitting, m = mort ... */
	protected char etat_;
	
	//protected ImageIcon imageIcon_;
	
	/* facteur appelé dans setLarg et setTaille, eux mêmes appelés
	 * par le PanelJoueur pour dire au personnage de prendre en compte
	 * la taille de son image dans ses largeurs et tailles
	 */
	protected int facteurGrandeur_=4;
	
	protected HitBox hitBox_;
	
	/* indique si le personnage est en train de faire une action.
	 * Typiquement, il sert à ne pas prendre en compte certaines actions lorsque
	 * le personnage est occupé.
	Pour l'instant je ne m'en suis pas encore servi
	 */
	protected boolean occupe_;

	/* valeur max de animWalk pour les animations
	 * de marche
	 */
	protected int maxAnimWalk_ = 100;
	protected int palierAnimWalk_ = maxAnimWalk_/4;
	
	/* valeur max de tic pour les animations de coup*/
	protected int maxTic_ = 40;
	protected int palierTic_ = maxTic_/4;
	
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
		etat_ = 'i'; //idle
		
		hitBox_ = new HitBox();

		occupe_ = false;
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
		
		image_=nom_+"_d_w_0.png";
		
		compteurTic_ = 0;
		etat_ = 'i'; //idle
		
		hitBox_ = new HitBox();
		
		occupe_ = false;
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
		
		image_=nom_+"_d_w_0.png";
		
		adversaire_ = adversaire;
		
		compteurTic_ = 0;
		etat_ = 'i'; //idle
		
		hitBox_ = new HitBox();
		
		occupe_ = false;
	}
	
	/*pour finaliser la constrution du j1*/
	public void setAdversaire(Personnage adversaire) {adversaire_ = adversaire;}
	
	
	/* je passe l'était à 1 (walking) pour montrer que le
	 * personnage bute contre le mur
	 */
	public void setX(int x) { 
		//On ne fait rien si on est en train de frapper ou autre
		if ((etat_ == 'i')||(etat_ == 'w')) {
		etat_='w';
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
		
		animWalk_ = (animWalk_+1) % maxAnimWalk_;
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
		if ((etat_ == 'i')||(etat_ == 'w')) {
		etat_ = 'w';
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
		
		animWalk_ = (animWalk_+1) % maxAnimWalk_;
		y_=y;
		hitBox_.setHB(x_, y_, taille_, largeur_);
		}
		}
	public void setPos(int x, int y) { x_=x; y_=y;}
	public void setOrientation(char dir) { orientation_ = dir;}	
	public void setTaille(int taille) {taille_ = taille*facteurGrandeur_; hitBox_.setHB(x_, y_, taille_, largeur_);}
	public void setLarg(int largeur) {largeur_ = largeur*facteurGrandeur_; hitBox_.setHB(x_, y_, taille_, largeur_);}
	public void setGrandeur(int taille, int largeur) {
		taille_ = taille*facteurGrandeur_;
		largeur_ = largeur*facteurGrandeur_;
		hitBox_.setHB(x_, y_, taille_, largeur_);
	}
	
	public int handleAnimWalk() {
		if (animWalk_ <  palierAnimWalk_) {return 0;}
		if (animWalk_ < 2*palierAnimWalk_ ) {return 1;}
		if (animWalk_ < 3*palierAnimWalk_ ) {return 0;}
		if (animWalk_ < maxAnimWalk_ ) {return 2;}
		return -1;
	}
	
	public int handleTic() {
		if (compteurTic_ < palierTic_) {return 0;}
		if (compteurTic_ < 2*palierTic_) {return 1;}
		if (compteurTic_ < 3*palierTic_) {return 2;}
		if (compteurTic_ < maxTic_) {return 3;}
		return -1;
	}
	
	public void coup0() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso est accès au joueur pour prendre ses cos
		 */
		//faire un grand if en fonction du nom
		//appeler les fonctions recevoir coup si jamais l'autre joueur est touché
		if (etat_ != '0') {
		etat_ = '0';
		occupe_ = true;
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
	}
	
	public void coup1() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso est accès au joueur pour prendre ses cos
		 */
		etat_ = '0';
		System.out.println("Je lance une boule de feu.");
	}
	
	public void coup2() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso est accès au joueur pour prendre ses cos
		 */
		etat_ = '0';
		System.out.println("J'appelle mes sbires.");
	}
	
	public void tic() {
		//si on est à l'arrêt, en train de marcher ou mort on ne remet pas setEtat à 0
		if ((etat_!='i')&(etat_!='w')&(etat_!='m')) {
		if ((compteurTic_ >=40)) {
		setEtat('i');
		compteurTic_=0;
		} else {
		++compteurTic_;
		}
		}
	}

	public void setEtat(char etat) {
		etat_ = etat;
	}
	
	public int getX() {return x_;}
	public int getY() {return y_;}
	public int getVit() {return vitessePerso_; }
	public int getNum() {return num_;}
	public int getTaille() {return taille_;}
	public int getLarg() {return largeur_;}
	public String getName() {return nom_;	}
	public char getEtat() {return etat_;}
	public char getOrient() {return orientation_;}
	public HitBox getHB() {return hitBox_;}
	
	public String getImage() {
		//si le personnage est à l'arrêt
		if (etat_ == 'm') {image_ ="steve.jpeg";}
		else if (etat_ == 'i') {image_=nom_+"_"+Character.toString(orientation_)+"_w_0.png";}
		else {
			int NO=0;
			if (etat_ == 'w') {NO = handleAnimWalk();}
			if (etat_ == '0') {NO = handleTic();}
			image_ = nom_+"_"+Character.toString(orientation_)+"_"+Character.toString(etat_)+"_"+Integer.toString(NO)+".png";
		}
		return image_;
	}
	
	void decPvs(int degats) {
		pvs_ -= degats;
		//si ses pvs sont négatifs le personnage meurt
		if (pvs_ <= 0) {etat_ = 'm';}
		System.out.println("AIE il me reste "+pvs_);
	}
	
}
