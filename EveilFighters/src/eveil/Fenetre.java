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
	j1_.initPos(50, 50);
	j2_.initPos(500, 500);
		
	window_ = new JFrame("Eveil Fighters");
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
			//System.out.println("Etat :"+j1_.getEtat()+" X : "+j1_.getX()+" Y : "+j1_.getY());
			
			/* TESTS HITBOX*/
			/*System.out.println("1HG : "+j1_.getHB().getHG());
			System.out.println("1HD : "+j1_.getHB().getHD());
			System.out.println("1BG : "+j1_.getHB().getBG());
			System.out.println("1BD : "+j1_.getHB().getBD());*/
			
			/*System.out.println("1Taille de base : "+j1_.getHB().getTBase());
			System.out.println("1Largeur de base : "+j1_.getHB().getLBase());
			
			System.out.println("1Taille de base calculée zà gauche : "+(j1_.getHB().getBG().getY()-j1_.getHB().getHG().getY()));
			System.out.println("1Taille de base calculée à droite : "+(j1_.getHB().getBD().getY()-j1_.getHB().getHD().getY()));
			
			System.out.println("1Largeur de base calculée en haut : "+(j1_.getHB().getHD().getX()-j1_.getHB().getHG().getX()));
			System.out.println("1Largeur de base calculée en bas : "+(j1_.getHB().getBD().getX()-j1_.getHB().getBG().getX())); */
			
			/*System.out.println("2HG : "+j2_.getHB().getHG());
			System.out.println("2HD : "+j2_.getHB().getHD());
			System.out.println("2BG : "+j2_.getHB().getBG());
			System.out.println("2BD : "+j2_.getHB().getBD());*/
			
			/*System.out.println("2Taille de base : "+j2_.getHB().getTBase());
			System.out.println("2Largeur de base : "+j2_.getHB().getLBase());
			
			System.out.println("2Taille de base calculée zà gauche : "+(j2_.getHB().getBG().getY()-j2_.getHB().getHG().getY()));
			System.out.println("2Taille de base calculée à droite : "+(j2_.getHB().getBD().getY()-j2_.getHB().getHD().getY()));
			
			System.out.println("2Largeur de base calculée en haut : "+(j2_.getHB().getHD().getX()-j2_.getHB().getHG().getX()));
			System.out.println("2Largeur de base calculée en bas : "+(j2_.getHB().getBD().getX()-j2_.getHB().getBG().getX())); */
			
			//majPosition(liste itemsDyn)
			panel_.repaint();
			}
			
		
			
		}
		
	
	/* met à jour la position du joueur en arg en fonction des infos
	 * du key listener
	 */
	/* distinguer les cas joueurs et autre item dynamique */
	public void majPosition(Personnage j, Personnage jFixe) {
		if (keyListener.getUp(j.getNum())) {
			j.setOrientation('u');
		    if ((j.getY() - j.getVit())< 0) {
			j.setY(0);
		    } else if (j.checkCollisionAdv()) {
		    j.setY((int) jFixe.getHB().getBG().getY());
		    } else {
			j.setY(j.getY()-j.getVit());
		    }
		}
		else if (keyListener.getDown(j.getNum())) {
			j.setOrientation('d');
		    if ((j.getY() + j.getVit())> panel_.getHeight()-j.getHB().getTBase()) {
			j.setY(panel_.getHeight() -j.getHB().getTBase());
		    } else if (j.checkCollisionAdv()) {
		    j.setY((int) (jFixe.getHB().getHG().getY()-j.getHB().getTBase()));
		    } else {
			j.setY(j.getY()+j.getVit());
		    }
		}
		else if (keyListener.getLeft(j.getNum())) {
			j.setOrientation('l');
		    if ((j.getX() - j.getVit())< 0) {
			j.setX(0);
		    } else if (j.checkCollisionAdv()) {
		    j.setX((int) jFixe.getHB().getHD().getX());
		    } else {
			j.setX(j.getX()-j.getVit());
		    }
		}
		else if (keyListener.getRight(j.getNum())) {
			j.setOrientation('r');
		    if ((j.getX() + j.getVit())> window_.getWidth()-j.getHB().getLBase()) {
			j.setX(window_.getWidth()-j.getHB().getLBase());
		    } else if (j.checkCollisionAdv()) {
		    	j.setX((int) (jFixe.getHB().getHG().getX()-j.getHB().getLBase()));
		    } else {
			j.setX(j.getX()+j.getVit());
		    }
		}
		//MAJ des positions des items
	
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
		Personnage j1 = new Lucas();
		Personnage j2 = new Lucas(j1);
		j1.setAdversaire(j2);
		
		
		
		Fenetre fenetre = new Fenetre(j1, j2);
		
		//TEST ARRAYLIST : c'est ce qu'il faut utiliser
		/*Personnage j3 = new Personnage(10, 1, 10, "drassius", j1);
		Personnage j4 = new Personnage(10, 1, 10, "mazak", j1);
		Personnage j5 = new Personnage(10, 1, 10, "maure", j1);
		Personnage j6 = new Personnage(10, 1, 10, "derol", j1);
		
		List<Personnage> l = new ArrayList<Personnage>();
		l.add(j1);
		l.add(j2);
		l.add(j3);
		l.add(j4);
		l.add(j5);
		l.add(j6);
		System.out.println(l.get(2).getName());
		l.remove(j3);
		System.out.println(l.get(2).getName());
		int taille = l.size();
		for (int NO = 0 ; NO < taille ; NO++) { System.out.println(l.get(NO).getName());}*/
		//System.out.println(l.get(0).getName());
		
		
	}
}