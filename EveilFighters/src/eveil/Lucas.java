package eveil;

public class Lucas extends Personnage {

	public Lucas() {
		super(10, 1, 10, "red");
		vitesse_=10;
	}
	
	public Lucas(Personnage adversaire) {
		super(10, 1, 10, "lucas", adversaire);
		vitesse_=10;
	}
	
	public Lucas(int y) {y_=y;}
	
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
		return 3;
	}
	
	public void coup0() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso aie accès au joueur pour prendre ses cos
		 */
		//faire un grand if en fonction du nom
		//appeler les fonctions recevoir coup si jamais l'autre joueur est touché
		if (etat_ != '0') {
		etat_ = '0';
		System.out.println("Je donne un coup d'epee.");
		if (getHB().checkCollision(getVit(), adversaire_.getHB())) {adversaire_.decPvs(force_);} else {System.out.println("LOUPE");}}
	}
	
	public void coup1() {
		/*ce coup dépendra des coordonnées de l'adversaire peut être, auquel cas
		 * il faudrait que le perso est accès au joueur pour prendre ses cos
		 */
		etat_ = '1';
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
		if ((compteurTic_ >=maxTic_)) {
		setEtat('i');
		compteurTic_=0;
		} else {
		++compteurTic_;
		}
		}
	}
	
	/* cette fonction ajuste l'affichage de chaque image, elle modifie des indicateurs
	 * différentiels en fonction de x et de y, qui seront pris en compte dans l'affichage 
	 */
	public void ajusterAffichage() {
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
