package eveil;

import javax.swing.JFrame;

public class Fenetre {
	private PanelJoueur panel_;


	//joueur 1
	private Personnage j1_;
	
	//joueur 2
	private Personnage j2_;
	
	private SourisEcouteur mouseListener;
	private ClavierEcouteur keyListener;
	
	private JFrame window_;

	
	public Fenetre(Personnage j1, Personnage j2) {
	
		//j1 et j2 init
		j1_ = j1;
		j2_ = j2;
		j1_.setPos(0, 0);
		j2_.setPos(500, 500);
		
		window_ = new JFrame("Choix personnage");
		panel_ = new PanelJoueur(this, j1_, j2_);

	
	mouseListener = new SourisEcouteur();
	keyListener = new ClavierEcouteur();
	

	
	//Configuration de la fenêtre
	window_.setSize(1000, 700);
	window_.setVisible(true);
	//quand la frame se ferme le programme se termine
	window_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//la fenêtre pop au centre de l'écran
	window_.setLocationRelativeTo(null);
	
	//on set panel du joueur à la window
	window_.setContentPane(panel_);
	
	//config listeners
	window_.addMouseListener(mouseListener);
	window_.addKeyListener(keyListener);
	
	play();
	}
	
	public void play() {
		while (true) {
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				System.out.println("Problème dans le sleep");
			}
			
			//checker collision
			//notif partie jeu (ou dans le keyListener)
			majPosition(j1_, j2_);
			majPosition(j2_, j1_);
			panel_.repaint();
			}
			
		
			
		}
		
	
	/* met à jour la position du joueur en arg en fonction des infos
	 * du key listener
	 */
	public void majPosition(Personnage j, Personnage jFixe) {
		if (keyListener.getUp(j.getNum())) {
		    if ((j.getY() - j.getVit())< 0) {
			j.setY(0);
		    } else if (checkCollision('u', j, jFixe)) {
		    j.setY(jFixe.getY()+jFixe.getTaille());
		    } else {
			j.setY(j.getY()-j.getVit());
		    }
		}
		else if (keyListener.getDown(j.getNum())) {
		    if ((j.getY() + j.getVit())> panel_.getHeight()-j.getTaille()) {
			j.setY(panel_.getHeight() -j.getTaille());
		    } else if (checkCollision('d', j, jFixe)) {
		    j.setY(jFixe.getY()-j.getTaille());
		    } else {
			j.setY(j.getY()+j.getVit());
		    }
		}
		else if (keyListener.getLeft(j.getNum())) {
		    if ((j.getX() - j.getVit())< 0) {
			j.setX(0);
		    } else if (checkCollision('l',j, jFixe)) {
		    j.setX(jFixe.getX()+jFixe.getLarg());
		    } else {
			j.setX(j.getX()-j.getVit());
		    }
		}
		else if (keyListener.getRight(j.getNum())) {
		    if ((j.getX() + j.getVit())> window_.getWidth()-j.getLarg()) {
			j.setX(window_.getWidth()-j.getLarg());
		    } else if (checkCollision('r', j, jFixe)) {
		    	j.setX(jFixe.getX()-j.getLarg());
		    } else {
			j.setX(j.getX()+j.getVit());
		    }
		}
	
	}
	
	/* params : dir pour la direction dans laquelle il faut checker les collisions
	 * jM est le personnage mobile, jF le fixe
	 * Renvoie vrai s'il y a collision : c'est à la fonction appelante
	 * de rectifier la position des personnages*/
	boolean checkCollision(char dir, Personnage jM, Personnage jF) {
		if (dir == 'l') {
			if ((jM.getX()-jM.getVit() < jF.getX()+jF.getLarg())
				&&(jM.getX()-jM.getVit() > jF.getX())
				&&(jM.getY() < jF.getY()+jF.getTaille())
				&&(jM.getY()+jM.getTaille() > jF.getY())) {return true;} else {return false;}}
		if (dir == 'r') {
			if ((jM.getX()+jM.getLarg()+jM.getVit() < jF.getX()+jF.getLarg())
				&&(jM.getX()+jM.getLarg()+jM.getVit() > jF.getX())
				&&(jM.getY() < jF.getY()+jF.getTaille())
				&&(jM.getY()+jM.getTaille() > jF.getY())) {return true;} else {return false;}}
		if (dir == 'u') {
			if ((jM.getY()-jM.getVit() < jF.getY()+jF.getTaille())
				&&(jM.getY()+jM.getVit() > jF.getY())
				&&(jM.getX() < jF.getX()+jF.getLarg())
				&&(jM.getX()+jM.getLarg() > jF.getX())) {return true;} else {return false;}}
		if (dir == 'd') {
			if ((jM.getY()+jM.getTaille()+jM.getVit() < jF.getY()+jF.getTaille())
				&&(jM.getY()+jM.getTaille()+jM.getVit() > jF.getY())
				&&(jM.getX() < jF.getX()+jF.getLarg())
				&&(jM.getX()+jM.getLarg() > jF.getX())) {return true;} else {return false;}}
			
	return false;
	}
	
	public static void main(String[] args) {
		Personnage j1 = new Personnage(1, 0, 0, 0, "inspecteur");
		Personnage j2 = new Personnage(2, 0, 0, 0, "inspecteur");
		
		Fenetre fenetre = new Fenetre(j1, j2);
		
	}
}