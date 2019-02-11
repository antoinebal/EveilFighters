package eveil;


import java.util.ArrayList;
import javax.swing.JFrame;




public class Controller {
	private Panel panel_;

	//joueur 1
	private Personnage j1_;
	
	//joueur 2
	private Personnage j2_;
	
	private SourisEcouteur mouseListener;
	private ClavierEcouteur keyListener;
	
	private JFrame window_;
	
	private Map map_;
	
	//private list Item;

	
	public Controller(Personnage j1, Personnage j2) {
	
	//j1 et j2 init
	j1_ = j1;
	j2_ = j2;
	j1_.initPos(50, 50);
	j2_.initPos(500, 500);
	
	//on cr�e la liste et ajoute les joueurs
	map_ = new Map("grass.png");
	map_.addItem(j1_);
	map_.addItem(j2_);
		
	window_ = new JFrame("Eveil Fighters");
	panel_ = new Panel(this, j1_, j2_);
	
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
			horlogeAnim();
			gereCoups();
	
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
	}
	
	
	public void majPositionItemDyn(ItemDyn id) {
		//on verif si collision avec chaque item restant dans le tas
		for (int i=0 ; i < panel_.getHeap().getSize() ; i++) {
			//si il y a une collision avec l'élément courant
			if (id.checkCollisionAvec(panel_.getHeap().getInd(i))) {
				id.collisionAvec(panel_.getHeap().getInd(i));
				panel_.getHeap().getInd(i).collisionAvec(id);
			}
		}
		
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
	
	/* tics d'horloge pour l'animation */
	public void horlogeAnim() {
		j1_.tic();
		j2_.tic();
	}
	
	//appel�e depuis le Panel
	public Map getMap() {return map_;}
	
	public static void main(String[] args) {
		Personnage j1 = new Lucas();
		Personnage j2 = new Lucas(j1);
		j1.setAdversaire(j2);	
		
		Controller controller = new Controller(j1, j2);
		
		Item iArbre = new Item("arbre");
		iArbre.initPos(60, 90);
		
		controller.getMap().addItem(iArbre);
		controller.play();

	}
}