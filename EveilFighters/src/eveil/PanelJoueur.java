package eveil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelJoueur extends JPanel{
	private Fenetre win_;
	
	
	static public int HAUT_PERSO = 30;
	static public int LARG_PERSO = 30;
	
	//co du j1
	private int x1_;
	private int y1_;
	
	//co du j2
	private int x2_;
	private int y2_;
	
	
	public PanelJoueur(Fenetre win) {
		win_=win;
		x1_ = 0;
		y1_ = 0;
		x2_ = 120;
		y2_ = 120;
	}
	
	
	public void paintComponent(Graphics g) {
	//Calque
	g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	
	//Experience avec les strings
	/*g.setColor(new Color(152, 32, win_.getJamal()));
	if (win_.getJamal() <127) {
		g.setFont(new Font("Arial", 1, 20));
	} else {
		g.setFont(new Font("Calibri", 1, 20));
	}
	g.drawString("Maure", 10, 10);*/
	
	//Test du mouse listener
	/*
	int x = (int) win_.getX();
	int y = (int) win_.getY();
	System.out.println("valeur de x"+x);
	System.out.println("valeur de y"+y);
	g.drawImage(new ImageIcon("Nualia.jpeg").getImage(), x, y, largeurCarte_, hauteurCarte_, null);*/
		
	System.out.println(this.getSize());
	//Test du key listener
	x1_ = (int) win_.getX1();
	y1_ = (int) win_.getY1();	
	x2_ = (int) win_.getX2();
	y2_ = (int) win_.getY2();
	
	g.drawImage(new ImageIcon("steve.jpeg").getImage(), x1_, y1_, LARG_PERSO, HAUT_PERSO, null);
	g.drawImage(new ImageIcon("herobrine.png").getImage(), x2_, y2_, LARG_PERSO, HAUT_PERSO, null);
	
	}
	
	
	
}