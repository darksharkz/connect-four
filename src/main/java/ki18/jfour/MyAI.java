package ki18.jfour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyAI extends AI{

	@Override
	public void start(Board b) {
		int depth = 1;
		while(true){
			Move move = generateMove(b, depth);
			setBestMove(move);
			depth++;
		}		
	}
	
	private Move generateMove(Board b, int depth){
		return null;
	}
	
	private Move maximize(Board b, int depth){
		depth--;
		if(depth == 0){
			List<Move> moves = b.possibleMoves();
			Map<Move, Integer> scores = new HashMap<Move, Integer>();
		}
	}
	
	private Move minimize(Board b, int depth){
		depth--;
		if(depth == 0){
			
		}
	}
	
	private int scoring(Board b){
		int score = 0;
		Player turn = b.getCurrentPlayer();
		
		score = score + getVerticalPossibilities(b.getState(), turn);
		score = score + getHorizontalPossibilities(b.getState(), turn);
		score = score + getDiagonal45Possibilities(b.getState(), turn);
		score = score + getDiagonal315Possibilities(b.getState(), turn);
		
		return score;
	}
	
	private int getVerticalPossibilities(Player[][] board, Player turn){
		int score = 0;
		
		Player next = turn.next();
		for (int y = board.length - 1; y >= 3; y--) {
		      for (int x = 0; x < board[0].length; x++) {
		        if (board[y][x] != next &&
		            board[y - 1][x] != next &&
		            board[y - 2][x] != next &&
		            board[y - 3][x] != next)
		        {
		        	score++;
		        }
		      }
		    }
		
		return score;
	}
	
	private int getHorizontalPossibilities(Player[][] board, Player turn){
		int score = 0;
		
		Player next = turn.next();
		for (int x = 0; x < board[0].length - 3; x++) {
		      for (int y = board.length - 1; y >= 0; y--) {
		        if (board[y][x] != next &&
		            board[y][x + 1] != next &&
		            board[y][x + 2] != next &&
		            board[y][x + 3] != next)
		        {
		          score++;
		        }
		      }
		    }
		
		return score;
	}
	
	private int getDiagonal45Possibilities(Player[][] board, Player turn){
		int score = 0;
		
		Player next = turn.next();
	    for (int y = board.length - 1; y >= 3; y--) {
	        for (int x = 0; x < board[0].length - 4; x++) {
	          if (board[y][x] != next &&
	              board[y - 1][x] != next &&
	              board[y - 2][x] != next &&
	              board[y - 3][x] != next)
	          {
	            score++;
	          }
	        }
	      }
		
		return score;
	}
	
	private int getDiagonal315Possibilities(Player[][] board, Player turn){
		int score = 0;
		
		Player next = turn.next();
		for (int y = board.length - 1; y >= 3; y--) {
		      for (int x = board[0].length - 1; x >= 3; x--) {
		        if (board[y][x] != next &&
		            board[y - 1][x - 1] != next &&
		            board[y - 2][x - 2] != next &&
		            board[y - 3][x - 3] != next)
		        {
		          score++;
		        }
		      }
		    }
		
		return score;
	}

	@Override
	public String getDescription() {
		String result = "Simon Englert\n2136190\nMinimax mit Alpha-Beta-Pruning";
		return null;
	}

}
