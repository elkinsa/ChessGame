package game.pieces;

import java.util.LinkedList;
import game.gameboard.Pieces;
import game.gameboard.Pieces.PieceSide;
import game.gameboard.Pieces.PieceType;
import game.gameboard.Pieces.X_Letters;

public class Pawn {
	
	/* 
	 * If the pawn has not moved at all, it can choose to move forward two spaces.
	 * Whether it has moved at all or not, a pawn can move one space forward if the square is empty.
	 * Pawn can move diagonally forward if it is capturing an opponent's piece.
	 * Special rules: 
		 * If the pawn has reached the opposite end of the board it can be promoted to any piece.
		 * en passant: (not implemented)
		 	* If the opponent's piece has moved forward two spaces and is located horizontally to the current
		 		* player's pawn, the current player may move their pawn diagonally, capturing the pawn next to
		 		* in the process as demonstrated below (P being player (top) & p being opponent (bottom)):
		 		*  initial: 			opponent move: 			player move:
		 		*  - - - -				- - - -					- - - -
		 		*  - - P -				- p P -					- - - -
		 		*  - - - -				- - - -					- P - -
		 		*  - p - -				- - - -					- - - -
	 */
	
	private Boolean enPassant = false;
	private Boolean promotion = false;
	private int enPassantListI;
	
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
		
		Boolean twoSpace;
		
		if(yDiff==2){twoSpace=true;}
		else if(yDiff==1){twoSpace=false;}
		else {return false;}
		
		if(top && newY==1){promotion = true;}
		else if(!top && newY==8){promotion = true;}
		
		if(xDiff==0 && newP==null){ // check for legal forward move
			if(twoSpace&&oldP.getMoved()){return false;}
			return true;
		} else if(xDiff==1 & yDiff==1 && newP!=null){ // check diagonal moves
			if(top){
				if(oldY<newY){return false;}
				else if(newP.getSide().equals(PieceSide.WHITE)){return true;} }
			else{
				if(oldY>newY){return false;}
				else if(newP.getSide().equals(PieceSide.BLACK)){return true;} }
			
			int num = newX - oldX;
			
			if(newY != oldY){return false;}
			
			Pieces p = board.get(oldPieceLoc+num);
			
			
			/*if((board.get(oldPieceLoc+num).getType()==PieceType.PAWN 
				&& board.get(oldPieceLoc+num).getSide()==PieceSide.WHITE)){
					enPassant=true; enPassantListI=oldPieceLoc-1; return true;}*/
		}
		
		return false;
	}
		
	// find all possible moves


	// GETS
	public Boolean getEP(){return enPassant;}
	public int getEPI(){return enPassantListI;}
	public Boolean getPromo(){return promotion;}	
}
