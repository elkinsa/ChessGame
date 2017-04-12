package game.gameboard;

import java.util.LinkedList;

import game.gameboard.Pieces.X_Letters;
import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Knight;
import game.pieces.Pawn;
import game.pieces.Queen;
import game.pieces.Rook;

public class MoveDigest {
	private X_Letters oldX;
	private X_Letters newX;
	private int oldY = 0;
	private int newY = 0;
	
	private Boolean topPlayer = false;
	private Boolean valid = false;
	
	private Boolean leftCastle = false;
	private Boolean rightCastle = false;
	private Boolean enPassant = false;
	private Boolean promotion = false;
	
	private LinkedList<Pieces> board = new LinkedList<Pieces>();
	
	public X_Letters getOldX(){return oldX;}
	public X_Letters getNewX(){return newX;}
	public int getOldY(){return oldY;}
	public int getNewY(){return newY;}

	public MoveDigest(String move, Boolean top) {
		if(move.length()==4){
			this.topPlayer = top;
			this.oldY = Integer.parseInt(move.substring(1,2));
			this.newY = Integer.parseInt(move.substring(3));
			int oldXI = getLetterInt(move.charAt(0)); this.oldX = X_Letters.getValue(oldXI);
			int newXI = getLetterInt(move.charAt(2)); this.newX = X_Letters.getValue(newXI);
		}
		else{
			System.out.print("Input is not four characters long.");
		}
	}
	
	public Boolean getValidity(Board b) {
		LinkedList<Pieces> pL = b.getGameboard();
		for(int i=0; i < pL.size(); i++){
			board.add(pL.get(i));}
		valid = move();
		return valid;
	}
	
	private Boolean move(){
		Boolean moveable = false;
		
		char old = '-';
		
		for(int b=0; b < board.size(); b++){
			Pieces p = board.get(b);
			if(oldY==p.getYLoc()){
				if(p.getXLoc().equals(oldX)){
					old = p.getChar(); break;}}}
		
		if (old!='-'){
			if (Character.isUpperCase(old) && topPlayer){
				if(old == 'K'){ // if moving king
					King checkKing = new King();
					moveable = checkKing.getLegal(oldX, oldY, newX, newY, board, true);
					leftCastle = checkKing.getLeftCastle();
					rightCastle = checkKing.getRightCastle();}
				else if(old == 'N'){ //if moving knight
					Knight checkKnight = new Knight();
					moveable = checkKnight.getLegal(oldX, oldY, newX, newY, board, true);}
				else if(old == 'B'){ // if moving bishop
					Bishop checkBishop = new Bishop();
					moveable = checkBishop.getLegal(oldX, oldY, newX, newY, board, true);}
				else if(old == 'R'){ // if moving rook
					Rook checkRook = new Rook();
					moveable = checkRook.getLegal(oldX, oldY, newX, newY, board, true);}
				else if(old == 'P'){ // if moving pawn
					Pawn checkPawn = new Pawn();
					moveable = checkPawn.getLegal(oldX, oldY, newX, newY, board, true);
					enPassant = checkPawn.getEP();
					promotion = checkPawn.getPromo();}
				else if(old == 'Q'){ // if moving pawn
					Queen checkQueen = new Queen();
					moveable = checkQueen.getLegal(oldX, oldY, newX, newY, board, true);}
			} else if (Character.isLowerCase(old) && !topPlayer){
				if(old == 'k'){ // if moving king
					King checkKing = new King();
					moveable = checkKing.getLegal(oldX, oldY, newX, newY, board, false);
					leftCastle = checkKing.getLeftCastle();
					rightCastle = checkKing.getRightCastle();}
				else if(old == 'n'){ // if moving knight
					Knight checkKnight = new Knight();
					moveable = checkKnight.getLegal(oldX, oldY, newX, newY, board, false);}
				else if(old == 'b'){ // if moving bishop
					Bishop checkBishop = new Bishop();
					moveable = checkBishop.getLegal(oldX, oldY, newX, newY, board, false);}
				else if(old == 'r'){ // if moving rook
					Rook checkRook = new Rook();
					moveable = checkRook.getLegal(oldX, oldY, newX, newY, board, false);}
				else if(old == 'p'){ // if moving pawn
					Pawn checkPawn = new Pawn();
					moveable = checkPawn.getLegal(oldX, oldY, newX, newY, board, false);
					enPassant = checkPawn.getEP();
					promotion = checkPawn.getPromo();}
				else if(old == 'q'){ // if moving pawn
					Queen checkQueen = new Queen();
					moveable = checkQueen.getLegal(oldX, oldY, newX, newY, board, false);}
			} else { System.out.println("Not able to move an opponent's piece."); return false;}
			
			if(moveable){
				return true;}
			else { System.out.println("Not a legal move."); return false;}
		} 
		else { System.out.println("No piece at: " + oldX + oldY);return false;}
	}

	private int getLetterInt(char x){
		int r = 6;
		if(x=='A'){r=0;} else if(x=='B'){r=1;} else if(x=='C'){r=2;} else if(x=='D'){r=3;}
		else if(x=='E'){r=4;} else if(x=='F'){r=5;} else if(x=='G'){r=6;} else if(x=='H'){r=7;}
		return r;
	}
	
	public Boolean getLeftCastle() {return leftCastle;}
	public Boolean getRightCastle() {return rightCastle;}
	public Boolean getEP() {return enPassant;}
	public Boolean getPromo() {return promotion;}
	
}
