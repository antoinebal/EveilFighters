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
	
	public PanelJoueur(Fenetre win, Personnage j1, Personnage j2) {
		win_=win;
		j1_=j1;
		j2_=j2;
		
		/*x1_ = j1_.getX();
		y1_ = j1_.getY();
		x2_ = j2_.getX();
		y2_ = j2_.getY();*/
	}
	
	
	public void paintComponent(Graphics g) {
	//Calque
	//remplacer par map peut être, qui est un Objet instancié à l'initialisation (qui contient une liste d'obstacles ou d'items)
	g.setColor(Color.GREEN);
	g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	
	
	/*x1_ = j1_.getX();
	y1_ = j1_.getY();	
	x2_ = j2_.getX();
	y2_ = j2_.getY();*/
	
	j1_.tic();
	j2_.tic();
	
	ii1_ = new ImageIcon(j1_.getImage());
	ii2_ = new ImageIcon(j2_.getImage());
	
	//TODO : si pb de perf, ne maj la taille QUE si on a changé l'image du perso
	j1_.setGrandeur(ii1_.getImage().getHeight(null), ii1_.getImage().getWidth(null));
	j2_.setGrandeur(ii2_.getImage().getHeight(null), ii2_.getImage().getWidth(null));

	
	g.drawImage(ii1_.getImage(), j1_.getX(), j1_.getY(), j1_.getLarg(), j1_.getTaille(), null);
	g.drawImage(ii2_.getImage(), j2_.getX(), j2_.getY(), j2_.getLarg(), j2_.getTaille(), null);
	
	
	//TEST : pour dessiner les hitbox
	g.setColor(Color.RED);
	g.drawRect((int)j1_.getHB().getHG().getX(), (int)j1_.getHB().getHG().getY(),j1_.getLarg(), (int) (j1_.getHB().getBG().getY()-j1_.getHB().getHG().getY()));
	g.drawRect((int)j2_.getHB().getHG().getX(), (int)j2_.getHB().getHG().getY(),j2_.getLarg(), (int) (j2_.getHB().getBG().getY()-j2_.getHB().getHG().getY()));
	}
	
	
	
}