package game.gameboard;

import java.util.LinkedList;

import game.gameboard.Pieces.PieceSide;
import game.gameboard.Pieces.PieceType;
import game.gameboard.Pieces.X_Letters;

public class Board {
	private LinkedList<Pieces> gameboard = new LinkedList<Pieces>();

	public Board(){
		
	}
	
	// get & set for gameboards
	public LinkedList<Pieces> getGameboard() {return gameboard;}
	public void setGameBoard(LinkedList<Pieces> newBoard){this.gameboard = newBoard;}
	
	// print current chess map
	public void printMap(){
		System.out.println("\n    (computer)\n");
		for (int i=8; i>0; i--){
			System.out.print(i+"  ");
			for (int j=0; j<8; j++){
				char c = '-';
				for(int b=0; b < gameboard.size(); b++){
					Pieces p = gameboard.get(b);
					if(i==p.getYLoc()){
						if(p.getXLoc().equals(X_Letters.getValue(j))){
							c = p.getChar(); break;}}}
				System.out.print(c + " ");
			}
			System.out.println();
		}
		System.out.println("   ---------------");
		System.out.println("   A B C D E F G H");
		System.out.println("\n      (human)");
	}

	// make move
	public void move(X_Letters oldX, X_Letters newX, int oldY, int newY, PieceType type) {
		Pieces oldP = null;
		Pieces newP = null;
		
		for(int i=0; i < gameboard.size(); i++){
			Pieces checkPiece = gameboard.get(i);
			X_Letters thisX = checkPiece.getXLoc();
			int thisY = checkPiece.getYLoc();
			if(thisX.equals(oldX) && thisY==oldY){oldP = checkPiece;}
			else if(thisX.equals(newX) && thisY==newY){
				newP = checkPiece;
				capture(thisX,thisY);} // if new location isn't empty capture the piece
			if(oldP!=null && newP!=null){break;}
		}
		
		if(type!=null){oldP.setType(type);}
		
		oldP.setMoved(true);
		oldP.makeMove(newX,newY);
		SortBoard sb = new SortBoard(gameboard);
		gameboard = sb.getSortedBoard();
	}
	
	// capture piece
	public void capture(X_Letters x, int y) {
		for(int i=0; i < gameboard.size(); i++){
			Pieces checkPiece = gameboard.get(i);
			X_Letters thisX = checkPiece.getXLoc();
			int thisY = checkPiece.getYLoc();
			if(thisX.equals(x) && thisY==y){gameboard.remove(i); break;}
		}
	}
	

	// setup for new game
	public void initialBoardSetup(){
		// BLACK pieces
			// NOBILITY
			Pieces blackRookOne = new Pieces(PieceType.ROOK, PieceSide.BLACK, X_Letters.A, 8);
			Pieces blackKnightOne = new Pieces(PieceType.KNIGHT, PieceSide.BLACK, X_Letters.B, 8);
			Pieces blackBishopOne = new Pieces(PieceType.BISHOP, PieceSide.BLACK, X_Letters.C, 8);
			Pieces blackQueen = new Pieces(PieceType.QUEEN, PieceSide.BLACK, X_Letters.D, 8);
			Pieces blackKing = new Pieces(PieceType.KING, PieceSide.BLACK, X_Letters.E, 8);
			Pieces blackBishopTwo = new Pieces(PieceType.BISHOP, PieceSide.BLACK, X_Letters.F, 8);
			Pieces blackKnightTwo = new Pieces(PieceType.KNIGHT, PieceSide.BLACK, X_Letters.G, 8);
			Pieces blackRookTwo = new Pieces(PieceType.ROOK, PieceSide.BLACK, X_Letters.H, 8);
				// add nobility
				gameboard.add(blackRookOne); gameboard.add(blackKnightOne); 
				gameboard.add(blackBishopOne); gameboard.add(blackQueen); 
				gameboard.add(blackKing); gameboard.add(blackBishopTwo); 
				gameboard.add(blackKnightTwo); gameboard.add(blackRookTwo);
			// PAWNS
			Pieces blackPawnOne = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.A, 7);
			Pieces blackPawnTwo = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.B, 7);
			Pieces blackPawnThree = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.C, 7);
			Pieces blackPawnFour = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.D, 7);
			Pieces blackPawnFive = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.E, 7);
			Pieces blackPawnSix = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.F, 7);
			Pieces blackPawnSeven = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.G, 7);
			Pieces blackPawnEight = new Pieces(PieceType.PAWN, PieceSide.BLACK, X_Letters.H, 7);
				// add pawns
				gameboard.add(blackPawnOne); gameboard.add(blackPawnTwo); 
				gameboard.add(blackPawnThree); gameboard.add(blackPawnFour); 
				gameboard.add(blackPawnFive); gameboard.add(blackPawnSix);
				gameboard.add(blackPawnSeven); gameboard.add(blackPawnEight); 
		// WHITE pieces
			// NOBILITY
			Pieces whiteRookOne = new Pieces(PieceType.ROOK, PieceSide.WHITE, X_Letters.A, 1);
			Pieces whiteKnightOne = new Pieces(PieceType.KNIGHT, PieceSide.WHITE, X_Letters.B, 1);
			Pieces whiteBishopOne = new Pieces(PieceType.BISHOP, PieceSide.WHITE, X_Letters.C, 1);
			Pieces whiteQueen = new Pieces(PieceType.QUEEN, PieceSide.WHITE, X_Letters.D, 1);
			Pieces whiteKing = new Pieces(PieceType.KING, PieceSide.WHITE, X_Letters.E, 1);
			Pieces whiteBishopTwo = new Pieces(PieceType.BISHOP, PieceSide.WHITE, X_Letters.F, 1);
			Pieces whiteKnightTwo = new Pieces(PieceType.KNIGHT, PieceSide.WHITE, X_Letters.G, 1);
			Pieces whiteRookTwo = new Pieces(PieceType.ROOK, PieceSide.WHITE, X_Letters.H, 1);
				// add nobility
				gameboard.add(whiteRookOne); gameboard.add(whiteKnightOne); 
				gameboard.add(whiteBishopOne); gameboard.add(whiteQueen); 
				gameboard.add(whiteKing); gameboard.add(whiteBishopTwo); 
				gameboard.add(whiteKnightTwo); gameboard.add(whiteRookTwo);
			// PAWNS
			Pieces whitePawnOne = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.A, 2);
			Pieces whitePawnTwo = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.B, 2);
			Pieces whitePawnThree = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.C, 2);
			Pieces whitePawnFour = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.D, 2);
			Pieces whitePawnFive = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.E, 2);
			Pieces whitePawnSix = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.F, 2);
			Pieces whitePawnSeven = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.G, 2);
			Pieces whitePawnEight = new Pieces(PieceType.PAWN, PieceSide.WHITE, X_Letters.H, 2);
				// add pawns
				gameboard.add(whitePawnOne); gameboard.add(whitePawnTwo); 
				gameboard.add(whitePawnThree); gameboard.add(whitePawnFour); 
				gameboard.add(whitePawnFive); gameboard.add(whitePawnSix);
				gameboard.add(whitePawnSeven); gameboard.add(whitePawnEight); 
	}
}