package eveil;

import java.lang.String;
import java.util.Timer;

import javax.swing.ImageIcon;



public abstract class Personnage extends ItemDyn{

	//1 ou 2
	protected int num_;
	
	protected int pvs_;

	//dégâts du coup au corps-à-corps
	protected int force_;
	
	//portée du coup au corps-à-corps
	protected int porteeCC_;
		
	/*en marche, en train de frapper etc.
	i = idle, w = walking, 0 = hitting, m = mort ... */
	protected char etat_;
	
	protected Personnage adversaire_;
	
	/* vrai si le perso vient de se prendre un coup : 
	 * il ne peut pas s'en prendre un autre
	 * il faudrait faire une variable comme �a
	 * pour les collisions et une pour les coups
	 */
	protected boolean inRecovery_=false;

	//constructeur par défaut
	public Personnage() {	
		super();
		
		vitesse_=2;
		
		pvs_ = 0;
		force_ = 0;
		porteeCC_ = 0;
		nom_ = "Drassius";
		orientation_ = 'd';
	
		etat_ = 'i'; //idle
	}
	
	
	//constructeur du j1
	//dans le futur mettre un if en fonction du nom pour construire le perso
	public Personnage(int pvs, int force, int porteeCC, String nom) {
		super(nom);
		
		num_ = 1;
		pvs_ = pvs;
		force_ = force;
		porteeCC_ = porteeCC;
		orientation_ = 'd';
					
		etat_ = 'i'; //idle
		
		animWalk_ = 0;
	
		vitesse_=5;
		
		image_="data/"+nom_+"_d_w_0.png";
		
		//premières instanciations des dimensions (utiles pour la hitbox)
		setTaille(new ImageIcon(image_).getImage().getHeight(null));
		setLarg(new ImageIcon(image_).getImage().getWidth(null));
	}
	
	//constructeur du j2
	//dans le futur mettre un if en fonction du nom pour construire le perso
	public Personnage(int pvs, int force, int porteeCC, String nom, Personnage adversaire) {
		super(nom);
		
		num_ = 2;
		pvs_ = pvs;
		force_ = force;
		porteeCC_ = porteeCC;
		orientation_ = 'd';
		image_="data/"+nom_+"_d_w_0.png";
		etat_ = 'i'; //idle
		adversaire_ = adversaire;
		
		vitesse_=5;
		
		image_="data/"+nom_+"_d_w_0.png";
		
		//premières instanciations des dimensions (utiles pour la hitbox)
		setTaille(new ImageIcon(image_).getImage().getHeight(null));
		setLarg(new ImageIcon(image_).getImage().getWidth(null));
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
		
		animWalk_ = (animWalk_+1) % maxAnimWalk_;
		y_=y;
		hitBox_.setHB(x_, y_);
		
		yImage_ = y_ - 2*(taille_/3);
		}
		}
	

	

	public void setEtat(char etat) {
		etat_ = etat;
	}
	
	
	public void setRecovery(boolean b) {
		inRecovery_=b;
	}
	

	
	public int getNum() {return num_;}
	public char getEtat() {return etat_;}
	
	
	public String getImage() {
		//si le personnage est à l'arrêt
		if (etat_ == 'm') {image_ ="data/steve.jpeg";}
		else if (etat_ == 'i') {image_="data/"+nom_+"_"+Character.toString(orientation_)+"_w_0.png";}
		else {
			int NO=0;
			if (etat_ == 'w') {
				NO = handleAnimWalk();
				image_ = "data/"+nom_+"_"+Character.toString(orientation_)+"_"+Character.toString(etat_)+"_"+Integer.toString(NO)+".png";
				}
			if (etat_ == '0') {
				NO = handleTic();
				image_ = "data/"+nom_+"_"+Character.toString(orientation_)+"_"+Character.toString(etat_)+"_"+Integer.toString(NO)+".png";
				System.out.println(image_);
				}
			if (etat_ == '1') {
				NO = handleTic();
				image_ = "data/"+nom_+"_"+Character.toString(etat_)+"_"+Integer.toString(NO)+".png";
				System.out.println(image_);
				}
			//image_ = "data/"+nom_+"_"+Character.toString(orientation_)+"_"+Character.toString(etat_)+"_"+Integer.toString(NO)+".png";
		}
		ajusterAffichage();
		return image_;
	}
	
	
	
	public void decPvs(int degats) {
		pvs_ -= degats;
		//si ses pvs sont négatifs le personnage meurt
		if (pvs_ <= 0) {etat_ = 'm';}
		System.out.println("AIE il me reste "+pvs_);
	}
	
	/* renvoie vrai s'il va y avoir une collision avec l'adversaire */
	public boolean checkCollisionAdv() {return getHB().checkCollision(getVit(), adversaire_.getHB());}
	
	public void collisionAvec(Item i) {
		if (!inRecovery_) {
			System.out.println(nom_+ " se cogne avec "+i.getName());
			(new Timer()).scheduleAtFixedRate(new RecoveryTT(this), 0, 500);
		}
	}
	
	/* cette fonction ajuste l'affichage de chaque image, elle modifie des indicateurs
	 * différentiels en fonction de x et de y, qui seront pris en compte dans l'affichage 
	 */
	public abstract void ajusterAffichage();
	public abstract void coup0();
	public abstract void coup1();
	public abstract void coup2();		
}
