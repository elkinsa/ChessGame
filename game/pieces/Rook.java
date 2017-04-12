package game.pieces;

import java.util.LinkedList;

import game.gameboard.Pieces;
import game.gameboard.Pieces.PieceSide;
import game.gameboard.Pieces.X_Letters;

public class Rook {
	
	/* 
	 * Can move left, right, up, & down.
	 * Cannot jump over other pieces.
	 * Cannot move to space occupied by the playing side's own piece.
	 */
	
	// check if legal move
	public Boolean getLegal(X_Letters oX, int oldY, X_Letters nX, int newY, LinkedList<Pieces> board, boolean top) {
		int oldX = oX.ordinal();
		int newX = nX.ordinal();
		
		Boolean oldP = false;
		Boolean newP = false;
		
		if(top){
			for (int i=0; i<board.size(); i++){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				if(oldX==thisX && oldY==thisY){oldP = true;}
				if(newX==thisX && newY==thisY){
					if(p.getSide().equals(PieceSide.BLACK)){return false;}
					newP = true;}
				if(oldP && newP){break;}}} 
		else {
			for (int i=board.size()-1; i>=0; i--){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				if(oldX==thisX && oldY==thisY){
					oldP = true;}
				if(newX==thisX && newY==thisY){
					if(p.getSide().equals(PieceSide.WHITE)){return false;}
					newP = true;}
				if(oldP && newP){break;}}}
		
		int xDiff = Math.abs(oldX-newX);
		int yDiff = Math.abs(oldY-newY);
		
		if(xDiff>0 && yDiff==0 || xDiff==0 && yDiff>0){
			int xMin = Math.min(oldX, newX);
			int yMin = Math.min(oldY, newY);
			
			for(int i=1; i < board.size(); i++){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				if(thisX==newX && thisY==newY){break;}
				else if(thisX==xMin+i && thisY==oldY && newY==oldY){return false;}
				else if(thisY==yMin+i && thisX==oldX && newX==oldX){return false;}
			}
			
			return true;
		}
		
		return false;
	}
		
	// find all possible moves
	

}
