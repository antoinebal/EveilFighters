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
	private int x1_;
	private int y1_;
	
	//co du j2
	private int x2_;
	private int y2_;
	
	
	public PanelJoueur(Fenetre win, Personnage j1, Personnage j2) {
		win_=win;
		j1_=j1;
		j2_=j2;
		
		x1_ = 0;
		y1_ = 0;
		x2_ = 120;
		y2_ = 120;
	}
	
	
	public void paintComponent(Graphics g) {
	//Calque
	//remplacer par map peut être, qui est un Objet instancié à l'initialisation (qui contient une liste d'obstacles ou d'items)
	g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	
	
	x1_ = j1_.getX();
	y1_ = j1_.getY();	
	x2_ = j2_.getX();
	y2_ = j2_.getY();
	
	g.drawImage(new ImageIcon(j1_.getImage()).getImage(), x1_, y1_, j1_.getLarg(), j1_.getTaille(), null);
	g.drawImage(new ImageIcon(j2_.getImage()).getImage(), x2_, y2_, j2_.getLarg(), j2_.getTaille(), null);
	
	}
	
	
	
}