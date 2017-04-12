package game.pieces;

import java.util.LinkedList;

import game.gameboard.Pieces;
import game.gameboard.Pieces.PieceSide;
import game.gameboard.Pieces.X_Letters;

public class Queen {
	
	/* 
	 * Combined rules of Rook and Bishop.
	 * i.e.: 
	 	* Can move left, right, up, down, and diagonal.
	 	* Cannot jump over other pieces.
	 	* Cannot move to space occupied by the playing side's own piece. 
	 */
	
	// check if legal move
	public Boolean getLegal(X_Letters oX, int oldY, X_Letters nX, int newY, LinkedList<Pieces> board, boolean top) {
		int oldX = oX.ordinal();
		int newX = nX.ordinal();
		
		Pieces oldP = null;
		Pieces newP = null;
		
		if(top){
			for (int i=0; i<board.size(); i++){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				if(oldX==thisX && oldY==thisY){oldP = p;}
				if(newX==thisX && newY==thisY){
					if(p.getSide().equals(PieceSide.BLACK)){return false;}
					newP = p;}
				if(oldP!=null && newP!=null){break;}}} 
		else {
			for (int i=board.size()-1; i>=0; i--){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				if(oldX==thisX && oldY==thisY){
					oldP = p;}
				if(newX==thisX && newY==thisY){
					if(p.getSide().equals(PieceSide.WHITE)){return false;}
					newP = p;}
				if(oldP!=null && newP!=null){break;}}}
		
		int xDiff = Math.abs(oldX-newX);
		int yDiff = Math.abs(oldY-newY);
		
		if(xDiff==yDiff){
			int xMin = Math.min(oldX, newX);
			int yMin = Math.min(oldY, newY);
			int xMax = Math.max(oldX, newX);
			int yMax = Math.max(oldY, newY);
			
			for(int i=1; i < board.size(); i++){
				Pieces p = board.get(i);
				int thisX = p.getXLoc().ordinal();
				int thisY = p.getYLoc();
				
				int x,y;
				if((newY-oldY)>0){y=yMax-i;} else{y=yMin+i;}
				if((newX-oldX)>0){x=xMax-i;} else{x=xMin+i;}
				
				if(thisX==newX && thisY==newY){break;}
				else if(thisX==x && thisY==y){return false;}
			}
			return true;
		} else if(xDiff>0 && yDiff==0 || xDiff==0 && yDiff>0){
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
