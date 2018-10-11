/* Name : Mayuri Waghmode
 * Date : 9/11/2018 
 * Time : 3:29 PM
 * Purpose : The game of Pig
 */
import java.util.Random;
import java.util.Scanner;

public class PigGame {
	
	static int humanTotalScore = 0;
	static int computerTotalScore = 0;
	public static final String HUMAN = "human";
	public static final String COMPUTER = "computer";

	Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to the game of Pig!\n\n");
		PigGame game = new PigGame();
		game.humanTurn();
	}
	/*Method humanTurn : Human plays the game and decides whether to roll again or hold and 
	 * if, total score is >= 100 human WINS otherwise computer gets to play
	 */
	public void humanTurn() {
		int  turnScore = 0;
		String rollOrHold ;
		System.out.println("It is human's turn.\n--------------------");
		turnScore = roll(turnScore,HUMAN);
		while(humanTotalScore<100 && computerTotalScore<100) {
			System.out.println("Enter 'r' to roll again, 'h' to hold.");
			rollOrHold = in.next();
			if(rollOrHold.equals("r"))
				turnScore = roll(turnScore,HUMAN);
			else if(rollOrHold.equals("h")) {
				humanTotalScore += turnScore;
				turnScore = 0;
				bothScore();
				if(humanTotalScore>=100) 
					System.out.println("YOU WIN!");
				else
					computerTurn();
			}
		}
		
	}
	/*Method computerTurn : Computer plays the game and if turn score is >= 20 computer is forced to hold and 
	 * if ,  total score is >= 100 computer WINS otherwise human gets to play
	 */
	public void computerTurn() {
		int turnScore = 0;
		System.out.println("It is the computer's turn.\n--------------------------");
		turnScore = roll(turnScore,COMPUTER);
		if(turnScore>=20) {
			computerTotalScore += turnScore;
			System.out.printf("The current turnScore = %d >= 20, computer holds.\n",turnScore);
			bothScore();
			if(computerTotalScore>=100) 
				System.out.println("YOU LOSE!");
			else
				humanTurn();
		}	
		
	}
	
	//Method roll : performs roll operation for human and computer. If dice rolls 1 turn score is 0 and the player loses its turn
	public int roll(int turnScore, String player) {
	    int dice;
	    if(player == HUMAN) {
	    	dice = randomNumber();
	    	System.out.println("You rolled: "+dice);
			if(dice ==1) {
				turnScore = 0;
				System.out.println("You lose your turn! Your current turn gets 0 points.");
				bothScore();
				computerTurn();
			}
			else {
				turnScore +=dice;
				System.out.printf("Your turn score is %d and your total score is %d\n",turnScore,humanTotalScore);
				System.out.printf("If you hold, you will have %d points.\n",(humanTotalScore+turnScore));
			}
	    }
	    else if(player == COMPUTER)
	    	while(turnScore<20) {
	    		dice = randomNumber();
				System.out.printf("The computer rolled:%d\n",dice);
				if(dice ==1) {
					turnScore = 0;
					System.out.println("The computer lost its turn! Computer's current turn gets 0 points.");
					bothScore();
					humanTurn();
					break;
				}else {
					turnScore += dice;
				}
			}
		return turnScore;
	}
	
	/*Method randomNumber: Roll Dice and 
	 * return random number between 1 - 6*/
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
	
	/*Method bothScore: Display human total score and computer total score*/
	public void bothScore() {
		System.out.println("\n=================================");
		System.out.printf("Your total score is %d\n",humanTotalScore);
		System.out.printf("Computer's total score is %d\n",computerTotalScore);
		System.out.println("=================================\n");
}
}
