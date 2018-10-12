package eveil;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Image;
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
	keyListener = new ClavierEcouteur(j1_, j2_);
	

	
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
			//System.out.println(j1_.getEtat());
			
			
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
		    j.setY((int) jFixe.getHB().getBG().getY()-3*(jFixe.getTaille()/4));
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
		    j.setY((int) (jFixe.getHB().getHG().getY()-j.getTaille()));
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
		    j.setX((int) jFixe.getHB().getHD().getX());
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
		    	j.setX((int) (jFixe.getHB().getHG().getX()-j.getLarg()));
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
			if ((jM.getHB().getHG().getX()-jM.getVit() < jF.getHB().getHD().getX())
				&&(jM.getHB().getHG().getX()-jM.getVit() > jF.getHB().getHG().getX())
				&&(jM.getHB().getHG().getY() < jF.getHB().getBG().getY())
				&&(jM.getHB().getBG().getY() > jF.getHB().getHG().getY())) {return true;} else {return false;}}
		if (dir == 'r') {
			if ((jM.getHB().getHD().getX()+jM.getVit() < jF.getHB().getHD().getX())
				&&(jM.getHB().getHD().getX()+jM.getVit() > jF.getHB().getHG().getX())
				&&(jM.getHB().getHG().getY() < jF.getHB().getBG().getY())
				&&(jM.getHB().getBG().getY() > jF.getHB().getHG().getY())) {return true;} else {return false;}}
		if (dir == 'u') {
			if ((jM.getHB().getHG().getY()-jM.getVit() < jF.getHB().getBG().getY())
				&&(jM.getHB().getHG().getY()+jM.getVit() > jF.getHB().getHG().getY())
				&&(jM.getHB().getHG().getX() < jF.getHB().getHD().getX())
				&&(jM.getHB().getHD().getX() > jF.getHB().getHG().getX())) {return true;} else {return false;}}
		if (dir == 'd') {
			if ((jM.getHB().getBG().getY()+jM.getVit() < jF.getHB().getBG().getY())
				&&(jM.getHB().getBG().getY()+jM.getVit() > jF.getHB().getHG().getY())
				&&(jM.getHB().getHG().getX() < jF.getHB().getHD().getX())
				&&(jM.getHB().getHD().getX() > jF.getHB().getHG().getX())) {return true;} else {return false;}}
			
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
		Personnage j1 = new Personnage(10, 1, 10, "lucas");
		Personnage j2 = new Personnage(10, 1, 10, "lucas", j1);
		j1.setAdversaire(j2);
		
		
		
		Fenetre fenetre = new Fenetre(j1, j2);
		
		/*List<Personnage> l = new ArrayList<Personnage>();
		l.add(j1);
		l.add(j2);
		System.out.println(l.get(0).getName());*/
		
		
	}
}