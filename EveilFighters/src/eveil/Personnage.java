package eveil;

import java.lang.String;
import javax.swing.ImageIcon;



public class Personnage {

	//1 ou 2
	protected int num_;
	
	protected int pvs_;

	//dégâts du coup au corps-à-corps
	protected int force_;
	
	//portée du coup au corps-à-corps
	protected int porteeCC_;
	
	//position du personnage suivant x
	protected int x_;
	
	//position du personnage suivant y
	protected int y_;
	
	//position de l'image suivant x (pour l'affichage)
	protected int xImage_;
	
	//position de l'image suivant y (pour l'affichage)
	protected int yImage_;
	
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
	
	/*attributs pour ajuster l'affichage des images (modifiés dans ajusterAffichage
	 * et utilisés par le PanelJoueur
	 */
	protected int ajustX_;
	protected int ajustY_;
	
	//constructeur par défaut
	public Personnage() {		
		vitessePerso_=2;
		pvs_ = 0;
		force_ = 0;
		porteeCC_ = 0;
		nom_ = "Drassius";
		orientation_ = 'd';
		image_=nom_+"_down_1.png";
		//premières instanciations des dimensions (utiles pour la hitbox)
		setTaille(new ImageIcon(image_).getImage().getHeight(null));
		setLarg(new ImageIcon(image_).getImage().getWidth(null));		
		animWalk_ = 0;
		compteurTic_ = 0;
		etat_ = 'i'; //idle
		ajustX_ = 0;
		ajustY_ = 0;
	}
	
	
	//constructeur du j1
	//dans le futur mettre un if en fonction du nom pour construire le perso
	public Personnage(int pvs, int force, int porteeCC, String nom) {
		vitessePerso_=3;
		num_ = 1;
		pvs_ = pvs;
		force_ = force;
		porteeCC_ = porteeCC;
		nom_ = nom;
		orientation_ = 'd';
		image_=nom_+"_d_w_0.png";
		//premières instanciations des dimensions (utiles pour la hitbox)
		setTaille(new ImageIcon(image_).getImage().getHeight(null));
		setLarg(new ImageIcon(image_).getImage().getWidth(null));	
		compteurTic_ = 0;
		etat_ = 'i'; //idle
		ajustX_ = 0;
		ajustY_ = 0;
	}
	
	//constructeur du j2
	//dans le futur mettre un if en fonction du nom pour construire le perso
	public Personnage(int pvs, int force, int porteeCC, String nom, Personnage adversaire) {
		vitessePerso_=3;
		num_ = 2;
		pvs_ = pvs;
		force_ = force;
		porteeCC_ = porteeCC;
		nom_ = nom;
		orientation_ = 'd';
		image_=nom_+"_d_w_0.png";
		//premières instanciations des dimensions (utiles pour la hitbox)
		setTaille(new ImageIcon(image_).getImage().getHeight(null));
		setLarg(new ImageIcon(image_).getImage().getWidth(null));	
		adversaire_ = adversaire;
		compteurTic_ = 0;
		etat_ = 'i'; //idle
		ajustX_ = 0;
		ajustY_ = 0;
	}
	
	/*pour finaliser la constrution du j1*/
	public void setAdversaire(Personnage adversaire) {adversaire_ = adversaire;}
	
	/*initPos est appelé dans fenêtre juste après la construction des joueurs : 
	 * on a leurs coordonnées pour la première fois : on en profite pour affecter
	 * aux coordonnées de l'image leurs premières valeurs et pour set la HitBox
	 */
	public void initPos(int x, int y) { 
		x_=x; 
		y_=y;
		xImage_ = x_; 
		yImage_ = y_ - 2*(taille_/3); 
		hitBox_ = new HitBox(x_, y_, taille_, largeur_);
		}
	
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
		hitBox_.setHB(x_, y_);
		
		xImage_ = x_;
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
		hitBox_.setHB(x_, y_);
		
		yImage_ = y_ - 2*(taille_/3);
		}
		}
	

	
	public void setOrientation(char dir) { orientation_ = dir;}	
	public void setTaille(int taille) {taille_ = taille*facteurGrandeur_;}
	public void setLarg(int largeur) {largeur_ = largeur*facteurGrandeur_;}
	public void setGrandeur(int taille, int largeur) {
		taille_ = taille*facteurGrandeur_;
		largeur_ = largeur*facteurGrandeur_;
		//hitBox_.setHB(x_, y_, taille_, largeur_);
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
	public int getXImage() {return xImage_;}
	public int getYImage() {return yImage_;}
	public int getAjustX() {return ajustX_;}
	public int getAjustY() {return ajustY_;}
	
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
		ajusterAffichage();
		return image_;
	}
	
	/* cette fonction ajuste l'affichage de chaque image, elle modifie des indicateurs
	 * différentiels en fonction de x et de y, qui seront pris en compte dans l'affichage 
	 */
	public void ajusterAffichage() {
		if (nom_ == "lucas") {
		if ((orientation_ == 'l')&&(etat_ == '0')&&(handleTic()==0)) { ajustX_ = -5;}
		else if ((orientation_ == 'l')&&(etat_ == '0')&&(handleTic()==1)) { ajustX_ = -5;}
		else if ((orientation_ == 'l')&&(etat_ == '0')&&(handleTic()==2)) { ajustX_ = -40;}
		else if ((orientation_ == 'l')&&(etat_ == '0')&&(handleTic()==3)) { ajustX_ = -50;}
		
		else if ((orientation_ == 'r')&&(etat_ == '0')&&(handleTic()==0)) { ajustX_ = -15;}
		else if ((orientation_ == 'r')&&(etat_ == '0')&&(handleTic()==1)) { ajustX_ = -15;}
		else if ((orientation_ == 'r')&&(etat_ == '0')&&(handleTic()==2)) { ajustX_ = 0;}
		else if ((orientation_ == 'r')&&(etat_ == '0')&&(handleTic()==3)) { ajustX_ = 0;}
		
		else if ((orientation_ == 'u')&&(etat_ == '0')&&(handleTic()==0)) { ajustY_ = 5;}
		else if ((orientation_ == 'u')&&(etat_ == '0')&&(handleTic()==1)) { ajustY_ = 5;}
		else if ((orientation_ == 'u')&&(etat_ == '0')&&(handleTic()==2)) { ajustY_ = -10;}
		else if ((orientation_ == 'u')&&(etat_ == '0')&&(handleTic()==3)) { ajustY_ = -20;}
		
		else if ((orientation_ == 'd')&&(etat_ == '0')&&(handleTic()==0)) { ajustY_ = -15;}
		else if ((orientation_ == 'd')&&(etat_ == '0')&&(handleTic()==1)) { ajustY_ = -13;}
		else if ((orientation_ == 'd')&&(etat_ == '0')&&(handleTic()==2)) { ajustY_ = 5;}
		else if ((orientation_ == 'd')&&(etat_ == '0')&&(handleTic()==3)) { ajustY_ = 5;}
		else { ajustX_ = 0; ajustY_=0;}
		} 
	}
	
	public void decPvs(int degats) {
		pvs_ -= degats;
		//si ses pvs sont négatifs le personnage meurt
		if (pvs_ <= 0) {etat_ = 'm';}
		System.out.println("AIE il me reste "+pvs_);
	}
	
}
