package game.gameboard;

public class Pieces{
	public enum PieceType {
		KING(0), QUEEN(1), BISHOP(2), KNIGHT(3), ROOK(4), PAWN(5);
		private int t;
		private PieceType(int newT) { t = newT; }

	    public static PieceType getValue(int comparison) {
	        for (PieceType types : PieceType.values()) {
	            if (types.t == comparison) return types; 
	        }
	        return null;
	    }
	 } 
	public enum PieceSide {BLACK, WHITE}
	public enum X_Letters {
		A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);
		
		private int x;
		private X_Letters(int newX) { x = newX; }

	    public static X_Letters getValue(int comparison) {
	        for (X_Letters letters : X_Letters.values()) {
	            if (letters.x == comparison) return letters; 
	        }
	        return null;
	    }
	}
	
	private PieceType type;
	private PieceSide side;
	private X_Letters xLoc;
	private int yLoc;
	private Boolean moved = false;
	private char pieceChar;
	
	public Pieces(PieceType t, PieceSide s, X_Letters x, int y){
		this.type = t;
		this.side = s;
		this.xLoc = x;
		this.yLoc = y;
		
		if (type.equals(PieceType.KING)){ 
			if(side.equals(PieceSide.BLACK)){ pieceChar = 'K';}
			else { pieceChar = 'k';}}
		else if (type.equals(PieceType.QUEEN)){ 
			if(side.equals(PieceSide.BLACK)){ pieceChar = 'Q';}
			else { pieceChar = 'q';}}
		else if (type.equals(PieceType.BISHOP)){ 
			if(side.equals(PieceSide.BLACK)){ pieceChar = 'B';}
			else { pieceChar = 'b';}}
		else if (type.equals(PieceType.KNIGHT)){ 
			if(side.equals(PieceSide.BLACK)){ pieceChar = 'N';}
			else { pieceChar = 'n';}}
		else if (type.equals(PieceType.ROOK)){
			if(side.equals(PieceSide.BLACK)){ pieceChar = 'R';}
			else { pieceChar = 'r';}}
		else if (type.equals(PieceType.PAWN)){ 
			if(side.equals(PieceSide.BLACK)){ pieceChar = 'P';}
			else { pieceChar = 'p';}}
	}
	
	public char getChar(){return this.pieceChar;}
	public PieceType getType(){return this.type;}
	public PieceSide getSide(){return this.side;}
	public X_Letters getXLoc(){return this.xLoc;}
	public int getYLoc(){return this.yLoc;}
	public Boolean getMoved(){return this.moved;}
	public void setMoved(Boolean m){moved=m;}
	
	public void setType(PieceType t){
		if (type.equals(PieceType.PAWN)){
			if(!t.equals(PieceType.PAWN)){
				this.type = t;
			} else {
				System.out.println("ERROR: Cannot change a pawn into a pawn.");
			}
		} else {
			System.out.println("ERROR: Cannot change a piece other than a pawn.");
		}
	}
	
	public void makeMove(X_Letters x, int y){
		this.xLoc = x;
		this.yLoc = y;
		this.moved = true;
	}
	
}