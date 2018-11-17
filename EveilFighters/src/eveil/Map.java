package eveil;

import java.util.ArrayList;

public class Map {
	protected String background_;
	protected ArrayList<Item> itemsMap_;
	
	public Map() {
		itemsMap_ = new ArrayList<Item>();
	}
	public Item getItemInd(int NO) {return itemsMap_.get(NO);}
	public String getBackGround() {return background_;}
}