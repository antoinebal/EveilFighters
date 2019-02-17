package eveil;

import java.awt.Color;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import utils.BinaryHeap;

public class Panel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller controller_;
	
	//joueur 1
	private Personnage j1_;
	
	//joueur 2
	private Personnage j2_;
	
	private ImageIcon ii1_;
	private ImageIcon ii2_;
	private ImageIcon backGround_;
	
	private BinaryHeap heap_;
	
	
	public Panel(Controller controller, Personnage j1, Personnage j2) {
		controller_=controller;
		
		j1_=j1;
		j2_=j2;
	
		backGround_ = new ImageIcon("data/"+controller.getMap().getBackground());
		
		/*
		testArbre = new Item("arbre");
		testArbre.setGrandeur(new ImageIcon(testArbre.getImage()).getImage().getHeight(null), new ImageIcon(testArbre.getImage()).getImage().getWidth(null));
		testArbre.initPos(100, 100);*/
	}
	
	
	//fonction de TEST : dessine la HB des joueurs uniquement
	public void dessineHB(Graphics g) {
		//TEST : pour dessiner les hitbox
		g.setColor(Color.RED);
		//g.drawRect((int)j1_.getHB().getHG().getX(), (int)j1_.getHB().getHG().getY(),j1_.getLarg(), (int) (j1_.getHB().getBG().getY()-j1_.getHB().getHG().getY()));
		//g.drawRect((int)j2_.getHB().getHG().getX(), (int)j2_.getHB().getHG().getY(),j2_.getLarg(), (int) (j2_.getHB().getBG().getY()-j2_.getHB().getHG().getY()));
		g.drawLine((int)j1_.getHB().getHG().getX(), (int)j1_.getHB().getHG().getY(), (int)j1_.getHB().getHD().getX(), (int)j1_.getHB().getHD().getY());
		g.drawLine((int)j1_.getHB().getBG().getX(), (int)j1_.getHB().getBG().getY(), (int)j1_.getHB().getBD().getX(), (int)j1_.getHB().getBD().getY());
		g.drawLine((int)j1_.getHB().getHG().getX(), (int)j1_.getHB().getHG().getY(), (int)j1_.getHB().getBG().getX(), (int)j1_.getHB().getBG().getY());
		g.drawLine((int)j1_.getHB().getHD().getX(), (int)j1_.getHB().getHD().getY(), (int)j1_.getHB().getBD().getX(), (int)j1_.getHB().getBD().getY());
		
		g.drawLine((int)j2_.getHB().getHG().getX(), (int)j2_.getHB().getHG().getY(), (int)j2_.getHB().getHD().getX(), (int)j2_.getHB().getHD().getY());
		g.drawLine((int)j2_.getHB().getBG().getX(), (int)j2_.getHB().getBG().getY(), (int)j2_.getHB().getBD().getX(), (int)j2_.getHB().getBD().getY());
		g.drawLine((int)j2_.getHB().getHG().getX(), (int)j2_.getHB().getHG().getY(), (int)j2_.getHB().getBG().getX(), (int)j2_.getHB().getBG().getY());
		g.drawLine((int)j2_.getHB().getHD().getX(), (int)j2_.getHB().getHD().getY(), (int)j2_.getHB().getBD().getX(), (int)j2_.getHB().getBD().getY());
		
	}
	
	//fonction de TEST : dessine la HB de tous les items
	public void dessineHBItems(Graphics g) {
		//TEST : pour dessiner les hitbox
		g.setColor(Color.RED);
		
		for (int i=0 ; i < controller_.getMap().nombreItems() ; i++) {
			Item item = controller_.getMap().getItemInd(i);
			g.drawLine((int)item.getHB().getHG().getX(), (int)item.getHB().getHG().getY(), (int)item.getHB().getHD().getX(), (int)item.getHB().getHD().getY());
			g.drawLine((int)item.getHB().getBG().getX(), (int)item.getHB().getBG().getY(), (int)item.getHB().getBD().getX(), (int)item.getHB().getBD().getY());
			g.drawLine((int)item.getHB().getHG().getX(), (int)item.getHB().getHG().getY(), (int)item.getHB().getBG().getX(), (int)item.getHB().getBG().getY());
			g.drawLine((int)item.getHB().getHD().getX(), (int)item.getHB().getHD().getY(), (int)item.getHB().getBD().getX(), (int)item.getHB().getBD().getY());		
		}
		
	}
	
	public void affichePersos(Graphics g) {
		//TODO : si pb de perf, ne maj la taille QUE si on a changé l'image du perso
		j1_.setGrandeur(ii1_.getImage().getHeight(null), ii1_.getImage().getWidth(null));
		j2_.setGrandeur(ii2_.getImage().getHeight(null), ii2_.getImage().getWidth(null));

		if (j1_.getY() < j2_.getY()) {
		g.drawImage(ii1_.getImage(), j1_.getXImage()+j1_.getAjustX(), j1_.getYImage()+j1_.getAjustY(), j1_.getLarg(), j1_.getTaille(), null);
		g.drawImage(ii2_.getImage(), j2_.getXImage()+j2_.getAjustX(), j2_.getYImage()+j2_.getAjustY(), j2_.getLarg(), j2_.getTaille(), null);
		} else {
		g.drawImage(ii2_.getImage(), j2_.getXImage()+j2_.getAjustX(), j2_.getYImage()+j2_.getAjustY(), j2_.getLarg(), j2_.getTaille(), null);
		g.drawImage(ii1_.getImage(), j1_.getXImage()+j1_.getAjustX(), j1_.getYImage()+j1_.getAjustY(), j1_.getLarg(), j1_.getTaille(), null);
		}
		
		
	}
	
	public void afficheItems(Graphics g) {
		heap_ = new BinaryHeap(controller_.getMap().nombreItems());
		
		//on remplit le tas
		for (int i=0 ; i < controller_.getMap().nombreItems() ; i++) {
			heap_.insert(controller_.getMap().getItemInd(i));
		}
		
		//on parcourt le tas
		while (!heap_.isEmpty()) {
			Item item = heap_.deleteMin();
			
			//VERIF COLLISION SI ITEM DYN
			/*if (item.getClass().getSuperclass().getSuperclass()!=null) {
				if (item.getClass().getSuperclass().getSuperclass().getSimpleName().equals("ItemDyn")) {
					controller_.majPositionItemDyn((ItemDyn)item);
				}
			}
			*/
			
			//AFFICHAGE DE L'ITEM
			ImageIcon ii = new ImageIcon(item.getImage());
			//TODO : OPTIM, peut �tre le faire que pour les joueurs
			item.setGrandeur(ii.getImage().getHeight(null), ii.getImage().getWidth(null));
			//on affiche l'image
			g.drawImage(ii.getImage(), item.getXImage()+item.getAjustX(), item.getYImage()+item.getAjustY(), item.getLarg(), item.getTaille(), null);
		}
	}
	
	public BinaryHeap getHeap() {return heap_;}
	
	
	
	public void paintComponent(Graphics g) {
	//Calque
	//remplacer par map peut être, qui est un Objet instancié à l'initialisation (qui contient une liste d'obstacles ou d'items)
	g.drawImage(backGround_.getImage(), 0, 0, this.getSize().width, this.getSize().height, null);
	
	afficheItems(g);
	//dessineHBItems(g);
	}
	
	
	
}