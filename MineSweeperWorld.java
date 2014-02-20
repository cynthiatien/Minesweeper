import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;







import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class MineSweeperWorld extends World{
	
	int elapsedTime = 0;
	Timer tigger = new Timer(1000, new ClockTicker());
	
	int numClicks = 0;
	int numRevealed = 0;
	public MineSweeperWorld(int r, int c, int mines){
		super(new BoundedGrid( r, c));
		placeMines(mines);
		placeNumbers();
		placeTiles();
	}
		
	class ClockTicker implements ActionListener{
		
		@Override
		public void actionPerformed (ActionEvent x){
		elapsedTime++;
		setMessage("Elapsed Time:" +elapsedTime);
		}
	}	
	
	private void placeMines(int mines){
		Grid g = getGrid();
		int row = (int) (Math.random()*g.getNumRows());
		int col = (int) (Math.random()*g.getNumCols());
		
		Location loc = new Location(row,col);
		this.add(loc, new Mine());
		
		Random r =new Random();
		row= r.nextInt(this.getGrid().getNumRows());
		col= r.nextInt(this.getGrid().getNumCols());
		loc = new Location(row,col);
		this.add(loc,new Mine());
		
		while(mines >0){
			this.add(this.getRandomEmptyLocation(), new Mine());
			mines--;
		}
	}
	
	
	private void placeNumbers() {
		Grid g = getGrid();
		for(int x=0; x<g.getNumRows(); x++){
			for(int y=0; y<g.getNumCols(); y++){
				Location loc = new Location(x,y);
				int row = loc.getRow();
				int col = loc.getCol();
				Object o = g.get(loc);
				if(!(o instanceof Mine)){
					int count = 0;
					for(int r =row-1; r<row+2; r++){
						for(int c =col-1; c<col+2; c++){
							if(r>=0 && r<g.getNumRows() && c>=0 && c<g.getNumCols()){
								Location loc1 = new Location(r,c);
								if(g.get(loc1) instanceof Mine)
									count++;
							}
						}
					}	
				add(loc,count);	
			}
		}
	}	
}			
	
	
	private void placeTiles (){
	Grid g = getGrid();
		for(int x=0; x<g.getNumRows(); x++){
			for( int y=0; y<g.getNumCols(); y++){
				Location loc = new Location(x,y);
				add(loc,new Tile(g.get(loc))); 
			}
		}
	}

	@Override
	public boolean locationClicked (Location loc){
//		if(numClicks == 0){
//			if(g.get(loc) instanceof Mine){ 
//				this.add(loc, null);
//				this.add(this.getRandomEmptyLocation(), new Mine());
//			}else
//				reveal(loc);
//		}else{
//			if(!(g.get(loc) instanceof Tile)){}
//			else{
//				if(((Tile) g.get(loc)).getThing() instanceof Mine)
//					endGame();
//				else
//					reveal(loc);
//			}
//		}
		setMessage("You just clicked: " +loc);
		numClicks++;
		reveal(loc);
		return true;	
	}

	private void endGame() {
		Grid g = getGrid();
		this.setMessage("END GAME");
		ArrayList<Location>locs: new ArrayList<Location>();
		for(Location x: locs)
		reveal(Location loc);
	}



	private void reveal(Location loc) {
		Grid g = getGrid();
		if(((Tile) g.get(loc)).getThing().equals(0)){
			remove(loc);
			zeroClicked(loc);
		}
		else if(((Tile) g.get(loc)).getThing() instanceof Mine){
			endGame();
		}
		else{
			add(loc, ((Tile) g.get(loc)).getThing());
		}	
	}
	
	private void zeroClicked(Location loc){
		Grid g = getGrid();
		ArrayList <Location>locs = g.getOccupiedAdjacentLocations(loc);
		for(Location loc1:locs){
			reveal(loc1);
		}
	}
		
	private void rightClick() {
		// TODO Auto-generated method stub
		
	}


	private void revealTile(Location loc){
		Grid g = getGrid();
		if(((Tile) g.get(loc)).getThing() instanceof Mine){
			add(loc, ((Tile) g.get(loc)).getThing());
		}
		else{
			if(((Tile) g.get(loc)).getThing().equals(0)){
				add(loc, ((Tile) g.get(loc)).getThing());
			}
			else{
				add(loc, ((Tile) g.get(loc)).getThing());
			}
		}
	}	
}