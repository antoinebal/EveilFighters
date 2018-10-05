package eveil;

import javax.swing.JFrame;

public class Fenetre {
	private PanelJoueur panel_;

	//coordonnées joueur 1
	private double x1_;
	private double y1_;
	
	//coordonnées joueur 2
	private double x2_;
	private double y2_;
	
	//vitesse
	static private int vitesseJoueurs_ = 3;
	
	private SourisEcouteur mouseListener;
	private ClavierEcouteur keyListener;
	
	private JFrame window_;

	
	public Fenetre() {
	window_ = new JFrame("Choix personnage");
	panel_ = new PanelJoueur(this);

	
	//Pour le mouseListener
	x1_ = 0;
	y1_ = 0;
	x2_ = 120;
	y2_ = 120;
	
	mouseListener = new SourisEcouteur();
	keyListener = new ClavierEcouteur();
	

	
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
	System.out.println(window_.getWidth());
	play();
	}
	
	public void play() {
		while (true) {
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				
				System.out.println("Problème dans le sleep");
			}
			
			//checker collision
			//notif partie jeu (ou dans le keyListener)
			majPosition();
			panel_.repaint();
			}
			
			
		}
		
	
	
	public double getX1() { return x1_;}
	public double getY1() { return y1_;}
	
	public double getX2() { return x2_;}
	public double getY2() { return y2_;}
	
	/* met à jour la position des deux joueurs en fonction des infos
	 * du key listener
	 */
	public void majPosition() {
		System.out.println(window_.getSize());
		if (keyListener.getUp1()) {
			System.out.println("up1");
			if ((y1_ - vitesseJoueurs_)< 0) {y1_ = 0;}
			else {y1_-=vitesseJoueurs_;}
		} else 	if (keyListener.getDown1()) {
			if ((y1_ + vitesseJoueurs_)> panel_.getHeight()-panel_.HAUT_PERSO) {y1_ = panel_.getHeight() -panel_.HAUT_PERSO;}
			else {y1_+=vitesseJoueurs_;}
		} else 	if (keyListener.getLeft1()) {
			if ((x1_ - vitesseJoueurs_)< 0) {x1_=0;}
			else {x1_-=vitesseJoueurs_;}
		} else 	if (keyListener.getRight1()) {
			if ((x1_ + vitesseJoueurs_)> window_.getWidth()-panel_.LARG_PERSO) {x1_ = window_.getWidth()-panel_.LARG_PERSO;}
			else {x1_+=vitesseJoueurs_;}
		}
		
		if (keyListener.getUp2()) {
			if ((y2_ - vitesseJoueurs_)< 0) {y2_ = 0;}
			else {y2_-=vitesseJoueurs_;}
		} else 	if (keyListener.getDown2()) {
			if ((y2_ + vitesseJoueurs_)> panel_.getHeight()-panel_.HAUT_PERSO) {y2_ = panel_.getHeight()-panel_.HAUT_PERSO;}
			else {y2_+=vitesseJoueurs_;}
		} else 	if (keyListener.getLeft2()) {
			if ((x2_ - vitesseJoueurs_)< 0) {x2_=0;}
			else {x2_-=vitesseJoueurs_;}
		} else 	if (keyListener.getRight2()) {
			if ((x2_ + vitesseJoueurs_)> window_.getWidth()-panel_.LARG_PERSO) {x2_ = window_.getWidth()-panel_.LARG_PERSO;}
			else {x2_+=vitesseJoueurs_;}
		}
	
	}
	
	
	public static void main(String[] args) {
		
		Fenetre fenetre = new Fenetre();
		
	}
}