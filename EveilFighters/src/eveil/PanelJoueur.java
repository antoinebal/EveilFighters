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
	
	static public int HAUT_PERSO = 30;
	static public int LARG_PERSO = 30;
	
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
	g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	
	//Test du key listener
	x1_ = (int) j1_.getX();
	y1_ = (int) j1_.getY();	
	x2_ = (int) j2_.getX();
	y2_ = (int) j2_.getY();
	
	g.drawImage(new ImageIcon("steve.jpeg").getImage(), x1_, y1_, LARG_PERSO, HAUT_PERSO, null);
	g.drawImage(new ImageIcon("herobrine.png").getImage(), x2_, y2_, LARG_PERSO, HAUT_PERSO, null);
	
	}
	
	
	
}