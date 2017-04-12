package game;

import java.util.Scanner;

import game.gameboard.Board;
import game.gameboard.MoveDigest;
import game.gameboard.Pieces.PieceType;
import game.gameboard.Pieces.X_Letters;

public class MyGame{

	private boolean gameOver = false; 
	private boolean compTurn = false; // is it the computer's turn?
	private boolean twoPlayer = true; // are we playing a two player game?
	
	private boolean userOneWin = false;
	private boolean userTwoWin = false;
	private boolean compWin = false;

	private Board board = new Board();
	
	public MyGame(){
		Scanner input = new Scanner(System.in);

		// while loop to be uncommented once computer AI implemented
		/*while(true){
			// check how many players
			System.out.println("\n Want to play a one or two player game? (1/2)");
			String oneTwo = input.next();
			if(oneTwo.equals("1")){ // one player competes against computer
				// ask user who goes first
				System.out.println("\n Do you want to move first? (y/n) ");
				String yesNo = input.next();
				
				if (yesNo.equals("y")){break;} // user plays first 
				else if (yesNo.equals("n")){compTurn=true; break;} // computer plays first
				else { System.out.println("Invalid input.");}
			}
			else if(oneTwo.equals("2")){ // two players compete against each other
				twoPlayer = true; break;
			}
			else { System.out.println("Invalid input.");}
		}*/
		
		board.initialBoardSetup();
		
		System.out.println("Moves should be enteredas in this example: A2B2 "
		+ "\n with A2 being the current piece location "
		+ "\n and B2 being the new location.\n");
		
		while(!(gameOver)){
			int i = 0; // used to 
			int j;
			if(twoPlayer){j=2;} else{j=1;} // j is # of players
			
			board.printMap();

			// get user input & make move if valid
			if (twoPlayer || !compTurn){
				while(i<j){ 
					Boolean valid = false;
					Boolean top = false;
					
					if(i==0 && twoPlayer){ System.out.println("\nPlayer 1's Turn!"); top = false;}
					else if(i==1 && twoPlayer){ System.out.println("\nPlayer 2's Turn!"); top = true;}
					
					// ask input
					System.out.println("\n Please enter a move:");
					String move = input.next();
					
					// check if input is valid
					MoveDigest md = new MoveDigest(move, top);
					valid = md.getValidity(board);
					if(valid){
						i++;
						X_Letters oldX = md.getOldX();
						X_Letters newX = md.getNewX();
						int oldY = md.getOldY();
						int newY = md.getNewY();
						PieceType type = null;
						if(md.getEP()){ board.capture(oldX, oldY);}
						if(md.getPromo()){ 
							System.out.println("What would you like to promote your pawn to?");
							System.out.println("Enter num for KING(0), QUEEN(1), BISHOP(2), KNIGHT(3), ROOK(4)");
							int num = input.nextInt();
							type = PieceType.getValue(num);
						}
						board.move(oldX, newX, oldY, newY, type);
						if(md.getLeftCastle()){
							board.move(X_Letters.A, X_Letters.D, oldY, newY, null);}
						else if(md.getRightCastle()){
							board.move(X_Letters.H, X_Letters.F, oldY, newY, null);}
						board.printMap();
						if(!twoPlayer){compTurn=true;}
					}
					else{System.out.println("Try Again.");}
				}
			}
			
			// if one player computer finds and plays move
			if(compTurn){
				initMiniMax();
				compTurn = false;
				board.printMap();
			}
		}

		System.out.println("GAME OVER!");
		
		// print out the winner
		if(compWin){System.out.println("Computer wins!");}
		else if(userOneWin && !twoPlayer){System.out.println("You win!");}
		else if(userOneWin && twoPlayer){System.out.println("Player 1 wins!");}
		else if(userTwoWin){System.out.println("Player 2 wins!");}
		else{System.out.println("There was an error determining the winner.");}
		
		input.close();
	}

	private void initMiniMax() {
		// determine computer move
		
		// check if chosen move is valid
	
	}
}