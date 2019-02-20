package eveil;

public class Mob extends ItemDyn {
	public Mob (String nom, char orientation, Controller controller) {
		super(nom, controller);
		orientation_=orientation;
		vitesse_=5;
		
	}

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
		System.out.println("GOB : AW "+animWalk_);
		if ((animWalk_ >=maxAnimWalk_)) {
			animWalk_=0;
		} else {
			++animWalk_;
		}	
	}


	@Override
	public int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getImage() {
		image_="data/"+nom_+"_"+Character.toString(orientation_)+"_w_"+Integer.toString(handleAnimWalk())+".png";
		System.out.println("GOB : "+image_);
		return image_;
	}
	
	public char getAction() {return orientation_;}
	
	public void collisionAvec(Item i) {
		System.out.println(nom_+" se cogne contre");
		controller_.detruitItem(this);
	}
	
	public void collisionBord() {
		controller_.detruitItem(this);
	}
}
