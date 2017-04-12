package game.pieces;

import java.util.LinkedList;

import game.gameboard.Pieces;
import game.gameboard.Pieces.PieceType;
import game.gameboard.Pieces.PieceSide;
import game.gameboard.Pieces.X_Letters;

public class King {
	
	/* 
	 * Can move one square up, down, left, right, or diagonally.
	 * Can never move itself into check. (not implemented)
	 * If a king has not moved before,
	 	* it can move two squares toward an unmoved rook if there are no pieces in between them.
	 	* The rook then moves to the other side of the king. 
	 	* ex:
	 		* initial: 				first option: 			second option:
		 	* r - - - k - - r		- - k r - - - r			r - - - - r k -
	 */
	
	private Boolean leftCastle = false;
	private Boolean rightCastle = false;
	
	// check if legal move
	public Boolean getLegal(X_Letters oX, int oldY, X_Letters nX, int newY, LinkedList<Pieces> board, boolean top) {
		int oldX = oX.ordinal();
		int newX = nX.ordinal();
		
		Pieces oldP = null;
		Pieces newP = null;
		
		int oldPieceLoc = 0;
		
		if(top){
			for (int i=0; i<board.size(); i++){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				if(oldX==thisX && oldY==thisY){oldP = p; oldPieceLoc=i;}
				if(newX==thisX && newY==thisY){
					if(p.getSide().equals(PieceSide.BLACK)){return false;}
					newP = p;}
				if(oldP!=null && newP!=null){break;}}} 
		else {
			for (int i=board.size()-1; i>=0; i--){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				if(oldX==thisX && oldY==thisY){oldP = p; oldPieceLoc=i;}
				if(newX==thisX && newY==thisY){
					if(p.getSide().equals(PieceSide.WHITE)){return false;}
					newP = p;}
				if(oldP!=null && newP!=null){break;}}}
		
		int xDiff = Math.abs(oldX-newX);
		int yDiff = Math.abs(oldY-newY);
		
		
		
		if(xDiff==2 && yDiff==0 && oldP.getMoved()==false){ // check for castle
			Pieces checkRookOne = null;
			Pieces checkRookTwo = null;
			
			if(top){
				for (int i=0; i<board.size(); i++){
					Pieces p = board.get(i);
					if(p.getType().equals(PieceType.ROOK)){
						int thisX = p.getXLoc().ordinal();
						int thisY = p.getYLoc();
						if(oldY==thisY){
							if(oldX>thisX){checkRookOne = p;}
							else if(thisX>oldX){checkRookTwo=p;}
						}
						if(checkRookOne!=null && checkRookTwo!=null){break;}
				}}}
			else {
				for (int i=board.size()-1; i>=0; i--){
					Pieces p = board.get(i);
					if(p.getType().equals(PieceType.ROOK)){
						int thisX = p.getXLoc().ordinal();
						int thisY = p.getYLoc();
						if(oldY==thisY){
							if(oldX>thisX){checkRookOne = p;}
							else if(thisX>oldX){checkRookTwo=p;}
						}
						if(checkRookOne!=null && checkRookTwo!=null){break;}
				}}}
			if(oldX>newX && !checkRookOne.getMoved()){
				leftCastle = true;
				return true;
			} else if(oldX<newX && !checkRookTwo.getMoved()){
				rightCastle = true;
				return true;
			} else {return false;}} 
		else if(yDiff>1 || xDiff>1){return false;}
		
		
		return true;
	}	
		
	// find all possible moves



	// GETS
	public Boolean getLeftCastle(){return leftCastle;}
	public Boolean getRightCastle(){return rightCastle;}
}
