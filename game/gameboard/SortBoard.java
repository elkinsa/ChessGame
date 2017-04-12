package game.gameboard;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SortBoard {
	
	private LinkedList<Pieces> board;

	public SortBoard(LinkedList<Pieces> gameboard) {
		this.board = gameboard;
		Collections.sort(board, new Sorted());
	}
	

	public LinkedList<Pieces> getSortedBoard() {
		return board;
	}

}

class Sorted implements Comparator<Pieces>{
	 
    @Override
    public int compare(Pieces piece1, Pieces piece2) {
    	if((piece1.getXLoc().ordinal()>piece2.getXLoc().ordinal())
    			|| (piece1.getYLoc()<piece2.getYLoc())){ return 1;} 
        return -1;
    }
}