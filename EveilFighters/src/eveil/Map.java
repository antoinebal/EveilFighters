package eveil;

import java.util.ArrayList;

public class Map {
	protected String background_;
	protected ArrayList<Item> lItems_;
	
	public Map(String background) {
		lItems_ = new ArrayList<Item>();
		background_=background;
	}
	
	public void addItem(Item i) {
		lItems_.add(i);
	}
	
	public Item getItemInd(int NO) {return lItems_.get(NO);}
	public String getBackground() {return background_;}
	public int nombreItems() {return lItems_.size();}
}