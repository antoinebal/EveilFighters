package eveil;

import java.util.TimerTask;

public class RecoveryTT extends TimerTask {
	private int demisecondes_=0;
	private Personnage j_;
	
	public RecoveryTT(Personnage j) {
		super();
		j_=j;
		j_.setRecovery(true);
	}
	
	public void run() {
		demisecondes_++;
		
		
		if (demisecondes_>1) {
			j_.setRecovery(false);
			cancel();
		}
		
	}
	

}
