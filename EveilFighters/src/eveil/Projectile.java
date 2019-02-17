package eveil;

public class Projectile extends ItemDyn{
	

	
	
	public Projectile (String nom, char orientation, Controller controller) {
		super(nom, controller);
		orientation_=orientation;
		vitesse_=20;
		
	}

	@Override
	public int handleAnimWalk() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int handleTic() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void tic() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getImage() {
		return "data/"+nom_+".png";
	}
	
	public char getAction() {return orientation_;}
	
	public void collisionAvec(Item i) {
		System.out.println(nom_+" dans le mille");
		controller_.detruitItem(this);
	}
	
	public void collisionBord() {
		controller_.detruitItem(this);
	}

}
