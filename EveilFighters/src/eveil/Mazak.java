package eveil;

public class Mazak extends Personnage {

	Mazak() {
		super(16, 2, 15, "mazak");
		vitesse_=8;
	}
	
	@Override
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

	@Override
	//coup de DEGAT
	public void coup0() {
		if (etat_ != '0') {
			etat_ = '0';
			System.out.println("Je donne un coup d'epee.");
			if (getHB().checkCollision(getVit(), adversaire_.getHB())) {
				adversaire_.decPvs(force_);
				} else {
				System.out.println("LOUPE");
			}
		}
		
	}

	@Override
	//FOUDRE
	public void coup1() {
		//très grande portée
		
	}

	@Override
	//RAGE
	public void coup2() {
		/*lancer un time avec RageTT
		 * Mazak devient très rapide et ses coups font très mal
		 * rentrer en collision avec lui fait également des dégâts
		 */
		
	}

	@Override
	public int handleAnimWalk() {
		if (animWalk_ <  palierAnimWalk_) {return 0;}
		if (animWalk_ < 2*palierAnimWalk_ ) {return 1;}
		if (animWalk_ < 3*palierAnimWalk_ ) {return 0;}
		if (animWalk_ < maxAnimWalk_ ) {return 2;}
		return -1;
	}

	@Override
	public int handleTic() {
		if (compteurTic_ < palierTic_) {return 0;}
		if (compteurTic_ < 2*palierTic_) {return 1;}
		if (compteurTic_ < 3*palierTic_) {return 2;}
		if (compteurTic_ < maxTic_) {return 3;}
		return 3;
	}

	@Override
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
	
}
