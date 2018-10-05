package eveil;

import javax.swing.JFrame;

public class Fenetre {
	private PanelJoueur panel_;


	//joueur 1
	private Personnage j1_;
	
	//joueur 2
	private Personnage j2_;
	
	private SourisEcouteur mouseListener;
	private ClavierEcouteur keyListener;
	
	private JFrame window_;

	
	public Fenetre(Personnage j1, Personnage j2) {
	
		//j1 et j2 init
		j1_ = j1;
		j2_ = j2;
		j1_.setPos(0, 0);
		j2_.setPos(500, 500);
		
		window_ = new JFrame("Choix personnage");
		panel_ = new PanelJoueur(this, j1_, j2_);

	
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
			majPosition(j1_);
			majPosition(j2_);
			panel_.repaint();
			}
			
		
			
		}
		
	
	/* met à jour la position du joueur en arg en fonction des infos
	 * du key listener
	 */
	public void majPosition(Personnage j) {
		if (keyListener.getUp(j.getNum())) {
		    if ((j.getY() - j.getVit())< 0) {
			j.setY(0);
		    } else {
			j.setY(j.getY()-j.getVit());
		    }
		}
		if (keyListener.getDown(j.getNum())) {
		    if ((j.getY() + j.getVit())> panel_.getHeight()-panel_.HAUT_PERSO) {
			j.setY(panel_.getHeight() -panel_.HAUT_PERSO);
		    } else {
			j.setY(j.getY()+j.getVit());
		    }
		}
		if (keyListener.getLeft(j.getNum())) {
		    if ((j.getX() - j.getVit())< 0) {
			j.setX(0);
		    } else {
			j.setX(j.getX()-j.getVit());
		    }
		}
		if (keyListener.getRight(j.getNum())) {
		    if ((j.getX() + j.getVit())> window_.getWidth()-panel_.LARG_PERSO) {
			j.setX(window_.getWidth()-panel_.LARG_PERSO);
		    } else {
			j.setX(j.getX()+j.getVit());
		    }
		}
	
	}
	
	
	public static void main(String[] args) {
		Personnage j1 = new Personnage(1, 0, 0, 0, "Steve");
		Personnage j2 = new Personnage(2, 0, 0, 0, "Herobrine");
		
		Fenetre fenetre = new Fenetre(j1, j2);
		
	}
}