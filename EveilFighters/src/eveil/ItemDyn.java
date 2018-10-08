package eveil;

/* cette classe rassemble les objets qui bougeront sur la carte
 * donc les boules de feu, les gobelins, les goules
 */
public class ItemDyn {
	private String image_;
	
	private String name_;
	
	private int vitesse_;
	
	private int x_;
	
	private int y_;
	
	private int orientation_;
	
	//pirorité pour affichage au premier plan
	private int priorite2D;
	
	private int animMove_;
	
	
}
