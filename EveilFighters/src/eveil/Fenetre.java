package eveil;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Fenetre {
	private PanelJoueur panel_;


	//joueur 1
	private Personnage j1_;
	
	//joueur 2
	private Personnage j2_;
	
	private SourisEcouteur mouseListener;
	private ClavierEcouteur keyListener;
	
	private JFrame window_;
	
	//private list Item;

	
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
			gereCoups();
			//majPosition(liste itemsDyn)
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
			j.setOrientation('u');
		    } else if (checkCollision('u', j, jFixe)) {
		    j.setY(jFixe.getY()+jFixe.getTaille());
		    j.setOrientation('u');
		    } else {
			j.setY(j.getY()-j.getVit());
		    }
		}
		else if (keyListener.getDown(j.getNum())) {
		    if ((j.getY() + j.getVit())> panel_.getHeight()-j.getTaille()) {
			j.setY(panel_.getHeight() -j.getTaille());
			j.setOrientation('d');
		    } else if (checkCollision('d', j, jFixe)) {
		    j.setY(jFixe.getY()-j.getTaille());
		    j.setOrientation('d');
		    } else {
			j.setY(j.getY()+j.getVit());
		    }
		}
		else if (keyListener.getLeft(j.getNum())) {
		    if ((j.getX() - j.getVit())< 0) {
			j.setX(0);
			j.setOrientation('l');
		    } else if (checkCollision('l',j, jFixe)) {
		    j.setX(jFixe.getX()+jFixe.getLarg());
		    j.setOrientation('l');
		    } else {
			j.setX(j.getX()-j.getVit());
		    }
		}
		else if (keyListener.getRight(j.getNum())) {
		    if ((j.getX() + j.getVit())> window_.getWidth()-j.getLarg()) {
			j.setX(window_.getWidth()-j.getLarg());
			j.setOrientation('r');
		    } else if (checkCollision('r', j, jFixe)) {
		    	j.setX(jFixe.getX()-j.getLarg());
		    	j.setOrientation('r');
		    } else {
			j.setX(j.getX()+j.getVit());
		    }
		}
		//MAJ des positions des items
	
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
	
	/* écoute le clavierEcouteur et appelle les fonctions coupX des personnages
	 * si jamais ils ont donné un coup
	 */
	public void gereCoups() {
		if (keyListener.getC0(j1_.getNum())) { j1_.coup0();}
		if (keyListener.getC1(j1_.getNum())) { j1_.coup1();}
		if (keyListener.getC2(j1_.getNum())) { j1_.coup2();}
		
		if (keyListener.getC0(j2_.getNum())) { j2_.coup0();}
		if (keyListener.getC1(j2_.getNum())) { j2_.coup1();}
		if (keyListener.getC2(j2_.getNum())) { j2_.coup0();}
	}
	
	public static void main(String[] args) {
		Personnage j1 = new Personnage(0, 0, 0, "inspecteur");
		Personnage j2 = new Personnage(0, 0, 0, "inspecteur", j1);
		j1.setAdversaire(j2);
		Fenetre fenetre = new Fenetre(j1, j2);
		/*List<Personnage> l = new ArrayList<Personnage>();
		l.add(j1);
		l.add(j2);
		System.out.println(l.get(0).getName());*/
		
		
	}
}