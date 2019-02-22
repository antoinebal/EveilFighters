package eveil;

import java.util.TimerTask;

public class LifeLineTT extends TimerTask {
	private int demisecondes_=0;
	private Mob mob_;
	private int dureeVie_;
	
	public LifeLineTT(Mob mob, int dureeVie) {
		super();
		mob_=mob;
		dureeVie_=dureeVie;
	}
	
	public void run() {
		demisecondes_++;
		
		if (demisecondes_>dureeVie_) {
			mob_.destruction();
		}
		
	}
	

}
