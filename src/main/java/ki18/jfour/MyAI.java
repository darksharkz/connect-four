package ki18.jfour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyAI extends AI{
	
	private static Player turn;

	@Override
	public void start(Board b) {
		turn = b.getCurrentPlayer();
		int depth = 1;
		while(depth <= 1){
			Move move = generateMove(b, depth);
			setBestMove(move);
			System.out.println(depth);
			depth++;			
		}		
	}
	
	private Move generateMove(Board b, int depth){
		int score = Integer.MIN_VALUE;
		Move move = null;
		
		for(Move m : b.possibleMoves()){
			int s = minimize(b.executeMove(m), depth - 1);
			if (s > score){
				score = s;
				move = m;
			}
		}
		
		return move;
	}
	
	private int maximize(Board b, int depth){
		if(depth == 0){
			return scoring(b);
		}
		else{
			int score = Integer.MIN_VALUE;
			for(Move m : b.possibleMoves()){
				int s = minimize(b.executeMove(m), depth - 1);
				if (s > score){
					score = s;
				}
			}
			return score;
		}
	}
	
	private int minimize(Board b, int depth){
		if(depth == 0){
			return scoring(b);
		}
		else{
			int score = Integer.MAX_VALUE;
			for(Move m : b.possibleMoves()){
				int s = maximize(b.executeMove(m), depth - 1);
				if (s < score){
					score = s;
				}
			}
			return score;
		}
	}
	
	private int scoring(Board b){
		int score = 0;
		
		if(b.getWinner() == turn){
			return 1000;
		}
		if(b.getWinner() == turn.next()){
			return -1000;
		}
		
		score = score + getVerticalPossibilities(b.getState(), turn) - getVerticalPossibilities(b.getState(), turn.next());
		score = score + getHorizontalPossibilities(b.getState(), turn) - getHorizontalPossibilities(b.getState(), turn.next());
		score = score + getDiagonal45Possibilities(b.getState(), turn) - getDiagonal45Possibilities(b.getState(), turn.next());
		score = score + getDiagonal315Possibilities(b.getState(), turn) - getDiagonal315Possibilities(b.getState(), turn.next());
		
		//System.out.println("Scoring for player " + turn + ": " + score);
		//System.out.println(b);		
		
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
