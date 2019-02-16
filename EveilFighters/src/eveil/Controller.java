package eveil;



import java.util.Random;

import javax.swing.JFrame;




public class Controller {
    //***PARAMETRES***
    private int FREQUENCY = 20;
	
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
	map_ = new Map(this, "grass.png");
	map_.addItem(j1_);
	map_.addItem(j2_);
		
	window_ = new JFrame("Eveil Fighters");
	panel_ = new Panel(this, j1_, j2_);
	
	mouseListener = new SourisEcouteur();
	keyListener = new ClavierEcouteur(j1_, j2_);
		
	//Configuration de la fenêtre
	window_.setSize(1000, 700);
	
	//quand la frame se ferme le programme se termine
	window_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//la fenêtre pop au centre de l'écran
	window_.setLocationRelativeTo(null);
	//on set panel du joueur à la window
	window_.setContentPane(panel_);
	//config listeners
	window_.addMouseListener(mouseListener);
	window_.addKeyListener(keyListener);
	
	window_.setVisible(true);
	
    }
	
    public void play() {
	while (true) {
			
	    try {
		Thread.sleep(FREQUENCY);
	    } catch (InterruptedException e) {
				
		System.out.println("Problème dans le sleep");
	    }
	    //checker collision
	    //notif partie jeu (ou dans le keyListener)
	   // majPosition(j1_, j2_);
	    //majPosition(j2_, j1_);
	    
	  //on màj la position des items dynamiques
	  	for (int i=0 ; i < getMap().nombreItems() ; i++) {
	  		Item item = getMap().getItemInd(i);
	  		if (item.getClass().getSuperclass().getSuperclass()!=null) {
				if (item.getClass().getSuperclass().getSuperclass().getSimpleName().equals("ItemDyn")) {
					majPositionItemDyn((ItemDyn) item);
				}
	  		}
	  	}
	    
	    
	    horlogeAnim();
	    gereCoups();
	
	   // majPosition(liste itemsDyn)
	    panel_.repaint();
	}		
    }
		
	
    /* met à jour la position du joueur en arg en fonction des infos
     * du key listener
     */
    /* distinguer les cas joueurs et autre item dynamique */
    public void majPosition(Personnage j, Personnage jFixe) {
    char action = getAction(j);
	if (action=='u') {
	    j.setOrientation('u');
	    if ((j.getY() - j.getVit())< 0) {
		j.setY(0);
	    } else if (j.checkCollisionAdv()) {
		j.setY((int) jFixe.getHB().getBG().getY());
	    } else {
		j.setY(j.getY()-j.getVit());
	    }
	}
	else if (action=='d') {
	    j.setOrientation('d');
	    if ((j.getY() + j.getVit())> panel_.getHeight()-j.getHB().getTBase()) {
		j.setY(panel_.getHeight() -j.getHB().getTBase());
	    } else if (j.checkCollisionAdv()) {
		j.setY((int) (jFixe.getHB().getHG().getY()-j.getHB().getTBase()));
	    } else {
		j.setY(j.getY()+j.getVit());
	    }
	}
	else if (action=='l') {
	    j.setOrientation('l');
	    if ((j.getX() - j.getVit())< 0) {
		j.setX(0);
	    } else if (j.checkCollisionAdv()) {
		j.setX((int) jFixe.getHB().getHD().getX());
	    } else {
		j.setX(j.getX()-j.getVit());
	    }
	}
	else if (action=='r') {
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
	
    public char getAction(ItemDyn iDyn) {
	//si idyn est un personnage on regarde le key listener
	if (iDyn.getClass().getSuperclass().getSimpleName().equals("Personnage")) {
	    char orient;
		if (keyListener.getUp(iDyn.getNum())) {
		orient='u';
	    }
	    else if (keyListener.getDown(iDyn.getNum())) {
	    iDyn.setOrientation('d');
	    orient='d';
	    }
	    else if (keyListener.getLeft(iDyn.getNum())) {
	    orient='l';
	    }
	    else if (keyListener.getRight(iDyn.getNum())) {
	    orient='r';
	    } else {
		//aucun bouton de direction n'a été pressé pour ce joueur
	    return '0';
	    }
		iDyn.setOrientation(orient);
		return orient;
	} else {
	    return iDyn.getAction();
	}
    }
	
	
    /* cette fonction regarde si l'item dynamique courant
     * cogne un ou plusieurs items restant dans le tas
     */
    //OPTIM : NE PEUT ËTRE CHERCHER QU'A PARTIR D'UN CERTAIN INDEX
    public HitBox factorCollision(ItemDyn iDyn) {
	//on verif si collision avec chaque item restant dans le tas
	HitBox hbCollision=null;
	for (int i=0 ; i < getMap().nombreItems() ; i++) {
	    //si il y a une collision avec l'élément courant
		Item item = map_.getItemInd(i);
		//si l'objet est différent et si il y a collision, on renvoie une hitbox
	    if ((!iDyn.equals(item))&&(iDyn.checkCollisionAvec(item))) {
		iDyn.collisionAvec(item);
		item.collisionAvec(iDyn);
		hbCollision=item.getHB();
	    }
	}
	return hbCollision;
    }
    
    public int BORD_HAUT=0;  
    public int BORD_BAS(Item i) {
    	return panel_.getHeight()-i.getHB().getTBase();
    }   
    public int BORD_GAUCHE=0;    
    public int BORD_DROIT(Item i) {
    	return window_.getWidth()-i.getHB().getLBase();
    }
    
	
	
	
    //A FINIR? IL Y A UN PBBBBBBBBBB
    //distinguer les joueurs des items dyn?
    public void majPositionItemDyn(ItemDyn iDyn) {
		
	char action = getAction(iDyn);
		
	if (action!='0') {
	    HitBox hbPercutee = factorCollision(iDyn);
	    if (action=='u') {
		iDyn.setOrientation('u');
		if ((iDyn.getY() - iDyn.getVit())< BORD_HAUT) {
		    iDyn.setY(0);
		} else if (hbPercutee!=null) {
		    iDyn.setY((int) hbPercutee.getBG().getY());
		} else {
		    iDyn.setY(iDyn.getY()-iDyn.getVit());
		}
	    }
	    else if (action=='d') {
		iDyn.setOrientation('d');
		if ((iDyn.getY() + iDyn.getVit())> BORD_BAS(iDyn)) {
		    iDyn.setY(panel_.getHeight() -iDyn.getHB().getTBase());
		} else if (hbPercutee!=null) {
		    iDyn.setY((int) (hbPercutee.getHG().getY()-iDyn.getHB().getTBase()));
		} else {
		    iDyn.setY(iDyn.getY()+iDyn.getVit());
		}
	    }
	    else if (action=='l') {
		iDyn.setOrientation('l');
		if ((iDyn.getX() - iDyn.getVit())< BORD_GAUCHE) {
		    iDyn.setX(0);
		} else if (hbPercutee!=null) {
		    iDyn.setX((int) hbPercutee.getHD().getX());
		} else {
		    iDyn.setX(iDyn.getX()-iDyn.getVit());
		}
	    }
	    else if (action=='r') {
		iDyn.setOrientation('r');
		if ((iDyn.getX() + iDyn.getVit())> BORD_DROIT(iDyn)) {
		    iDyn.setX(window_.getWidth()-iDyn.getHB().getLBase());
		} else if (hbPercutee!=null) {
		    iDyn.setX((int) (hbPercutee.getHG().getX()-iDyn.getHB().getLBase()));
		} else {
		    iDyn.setX(iDyn.getX()+iDyn.getVit());
		}
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
    
    
    public void foret() {
    	Random rand = new Random();
    	for (int i =0 ; i < 20 ; i++) {
    		Item arbre = new Item("arbre");
    		int x = rand.nextInt(panel_.getHeight());
    		int y = rand.nextInt(window_.getWidth());
    		arbre.initPos(x, y);
    		map_.addItem(arbre);
    	}
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
	
	Item iMaz = new Item("mazak");
	iMaz.initPos(400, 100);
	controller.getMap().addItem(iMaz);
	
	Item iEr = new Item("eran");
	iEr.initPos(100, 200);
	controller.getMap().addItem(iEr);
	
	controller.foret();
		
	
	controller.play();

    }
}
