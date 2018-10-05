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
	
	protected String nom_;
	
	//constructeur par défaut
	public Personnage() {
		x_=0;
		y_=0;
		
		vitessePerso_=3;
		
		pvs_ = 0;
		force_ = 0;
		porteeCC_ = 0;
		nom_ = "Drassius";
		
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
	}
	
	public void setX(int x) { x_=x;}
	public void setY(int y) { y_=y;}
	public void setPos(int x, int y) { x_=x; y_=y;}
	
	public int getX() {return x_;}
	public int getY() {return y_;}
	public int getVit() {return vitessePerso_; }
	
	public int getNum() {return num_;}
}
