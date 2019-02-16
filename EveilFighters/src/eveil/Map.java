package eveil;

import java.util.ArrayList;

public class Map {
	private String background_;
	private ArrayList<Item> lItems_;
	private Controller controller_;
	
	public Map(Controller controller, String background) {
		lItems_ = new ArrayList<Item>();
		background_=background;
		controller_=controller;
	}
	
	public void addItem(Item i) {
		String nomClasse = i.getClass().getSimpleName();
		if (nomClasse.equals("Item")||nomClasse.equals("ItemDyn")) {
			if (positionValide(i)) {
				lItems_.add(i);
			} else {
				System.out.print(i.getName()+" n'est pas dans la map.");
			} 
		}else {
			lItems_.add(i);
		}
	}
	
	public Item getItemInd(int NO) {return lItems_.get(NO);}
	public String getBackground() {return background_;}
	public int nombreItems() {return lItems_.size();}
	
	//retourne vrai si i est dans la map et si pas de superposition
	public boolean positionValide(Item i) {
		
		//est-il dans la map?
		if (i.getHB().getHG().getY()<controller_.BORD_HAUT) {
			return false;
		}
		if (i.getHB().getBG().getY()>controller_.BORD_BAS(i)) {
			return false;
		}
		if (i.getHB().getBG().getX()<controller_.BORD_GAUCHE) {
			return false;
		}
		if (i.getHB().getBD().getX()>controller_.BORD_DROIT(i)) {
			return false;
		}
		
		//se superpose-t-il avec un autre item?
		int no=0;
		boolean bon=true;
		while (no<lItems_.size()&&bon) {
			if (i.getHB().estSuperposeeAvec(lItems_.get(no).getHB())) {
				bon=false;
				
			}
			no++;
		}
		
		return bon;
	}
}