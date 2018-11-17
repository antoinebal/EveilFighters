package eveil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelJoueur extends JPanel{
	private Fenetre win_;
	
	//joueur 1
	private Personnage j1_;
	
	//joueur 2
	private Personnage j2_;

	
	//co du j1
	//private int x1_;
	//private int y1_;
	
	//co du j2
	//private int x2_;
	//private int y2_;
	
	private ImageIcon ii1_;
	private ImageIcon ii2_;
	private ImageIcon backGround_;
	
	
	public PanelJoueur(Fenetre win, Personnage j1, Personnage j2) {
		win_=win;
		j1_=j1;
		j2_=j2;
	
		backGround_ = new ImageIcon("grass.png");
		/*x1_ = j1_.getX();
		y1_ = j1_.getY();
		x2_ = j2_.getX();
		y2_ = j2_.getY();*/
		
		/*
		testArbre = new Item("arbre");
		testArbre.setGrandeur(new ImageIcon(testArbre.getImage()).getImage().getHeight(null), new ImageIcon(testArbre.getImage()).getImage().getWidth(null));
		testArbre.initPos(100, 100);*/
	}
	
	
	//fonction de TEST
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
	
	/* tics d'horloge pour l'animation */
	public void horlogeAnim() {
		j1_.tic();
		j2_.tic();
	}
	
	public void paintComponent(Graphics g) {
	//Calque
	//remplacer par map peut être, qui est un Objet instancié à l'initialisation (qui contient une liste d'obstacles ou d'items)
	g.drawImage(backGround_.getImage(), 0, 0, this.getSize().width, this.getSize().height, null);
	
	
	/*x1_ = j1_.getX();
	y1_ = j1_.getY();	
	x2_ = j2_.getX();
	y2_ = j2_.getY();*/
	
	horlogeAnim();
	
	ii1_ = new ImageIcon(j1_.getImage());
	ii2_ = new ImageIcon(j2_.getImage());
	
	
	//g.drawImage(new ImageIcon(testArbre.getImage()).getImage(), testArbre.getXImage()+testArbre.getAjustX(), testArbre.getYImage()+testArbre.getAjustY(), testArbre.getLarg(), testArbre.getTaille(), null);
	//System.out.println("J1 ETAT : "+j1_.getEtat()+" ORIENTATION : "+j1_.getOrient());
	affichePersos(g);
	dessineHB(g);
	
	//test arbre
	/*
	g.drawLine((int)testArbre.getHB().getHG().getX(), (int)testArbre.getHB().getHG().getY(), (int)testArbre.getHB().getHD().getX(), (int)testArbre.getHB().getHD().getY());
	g.drawLine((int)testArbre.getHB().getBG().getX(), (int)testArbre.getHB().getBG().getY(), (int)testArbre.getHB().getBD().getX(), (int)testArbre.getHB().getBD().getY());
	g.drawLine((int)testArbre.getHB().getHG().getX(), (int)testArbre.getHB().getHG().getY(), (int)testArbre.getHB().getBG().getX(), (int)testArbre.getHB().getBG().getY());
	g.drawLine((int)testArbre.getHB().getHD().getX(), (int)testArbre.getHB().getHD().getY(), (int)testArbre.getHB().getBD().getX(), (int)testArbre.getHB().getBD().getY());*/
	}
	
	
	
}