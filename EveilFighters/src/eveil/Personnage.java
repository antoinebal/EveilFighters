package eveil;

import java.lang.String;

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
	
	//constructeur par défaut
	public Personnage() {
		x_=0;
		y_=0;
		
		vitessePerso_=2;
		
		pvs_ = 0;
		force_ = 0;
		porteeCC_ = 0;
		nom_ = "Drassius";
		
		taille_ = 50;
		largeur_ = 50;
		
		orientation_ = 'd';
		image_ = nom_+"_down_1";
		animWalk_ = 0;
	}
	
	
	//dans le futur mettre un if en fonction du nom pour construire le perso
	public Personnage(int num, int pvs, int force, int porteeCC, String nom) {
		x_=0;
		y_=0;
		
		vitessePerso_=3;
		
		num_ = num;
		pvs_ = pvs;
		force_ = force;
		porteeCC_ = porteeCC;
		nom_ = nom;
		
		taille_ = 50;
		largeur_ = 50;
		
		orientation_ = 'd';
		image_ = nom_+"_down_1.png";
	}
	
	public void setX(int x) { 
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
		
		animWalk_ = (animWalk_+15) % 120;
		x_=x;
		}
	public void setY(int y) { 
		
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
		}
	public void setPos(int x, int y) { x_=x; y_=y;}
	
	//dir = 'u' 'd' 'l' 'r'
	public void setOrientation(char dir) { orientation_ = dir;}
	
	public int handleAnimWalk() {
		if (animWalk_ < 30 ) {return 1;}
		if (animWalk_ < 60 ) {return 2;}
		if (animWalk_ < 90 ) {return 1;}
		if (animWalk_ < 120 ) {return 4;}
		return -1;
	}
	
	public int getX() {return x_;}
	public int getY() {return y_;}
	public int getVit() {return vitessePerso_; }
	
	public int getNum() {return num_;}
	
	public int getTaille() {return taille_;}
	public int getLarg() {return largeur_;}
	
	public String getImage() {
		if (orientation_ == 'u') {
			return nom_+"_up_"+Integer.toString(handleAnimWalk())+".png";
		}
		if (orientation_ == 'd') {
			return nom_+"_down_"+Integer.toString(handleAnimWalk())+".png";
		}
		if (orientation_ == 'l') {
			return nom_+"_left_"+Integer.toString(handleAnimWalk())+".png";
		}
		if (orientation_ == 'r') {
			return nom_+"_right_"+Integer.toString(handleAnimWalk())+".png";
		}
		return "erreur";
	}
}
