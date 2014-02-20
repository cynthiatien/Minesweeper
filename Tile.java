import java.awt.Color;
import java.util.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Tile {
	int type;
	private Object thing;
	public Tile(Object o){
		thing = o;
	}
	
	public Object getThing(){
		return thing;
	}


}
