package practicum3.slidingpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A playable sliding puzzle game.
 */
public class SlidingPuzzle {
    /**
     * The board containing the current layout of tiles. The #0 is the open
     * space on the board into which a tile may slide.
     */
    private final int[] board;

    /**
     * The number of moves required to solve the puzzle.
     */
    private final int movesToSolve;

    /**
     * The size of the board.
     */
    private final int size;

    /**
     * The index of the open space on the board.
     */
    private int openIndex;

    /**
     * The moves that have been made up to this point.
     */
    private final List<Direction> moves;

    /**
     * Keeps track of previous configurations - will not allow moves that 
     * reset the board into a previous configuration.
     */
    private final Set<String> configurations;

    /**
     * Creates a new Sliding Puzzle with the specified 2D board.
     * 
     * @param board The board that containes the initial layout of tiles.
     * @param movesToSolve The number of moves required to solve the puzzle.
     */
    public SlidingPuzzle(int[][] board, int movesToSolve) {
        this(flatten(board), movesToSolve);
    }

    /**
     * Creates a new Sliding Puzzle with the specified flattened (1D) board.
     * 
     * @param board The board that containes the initial layout of tiles.
     * @param movesToSolve The number of moves required to solve the puzzle.
     */
    public SlidingPuzzle(int[] board, int movesToSolve) {
        this.board = Arrays.copyOf(board, board.length);
        this.size = (int)Math.sqrt(board.length);
        this.movesToSolve = movesToSolve;
        
        for(int i=0; i<board.length; i++) {
            if(board[i] == 0) {
                openIndex = i;
                break;
            }
        }

        moves = new ArrayList<>();
        configurations = new HashSet<>();
        configurations.add(toString());
    }

    /**
     * Copy constructor - creates a new Sliding Puzzle by making a deep copy
     * of the original.
     * 
     * @param original The orignal Sliding Puzzle that is being copied.
     */
    private SlidingPuzzle(SlidingPuzzle original) {
        this.board = Arrays.copyOf(original.board, original.board.length);
        this.movesToSolve = original.movesToSolve;
        this.size = original.size;
        this.openIndex = original.openIndex;
        this.moves = new ArrayList<>(original.moves);
        this.configurations = new HashSet<>(original.configurations);
    }

    /**
     * Returns a deep copy of this Sliding Puzzle.
     * 
     * @return A deep copy of this sliding puzzle.
     */
    public SlidingPuzzle deepCopy() {
        return new SlidingPuzzle(this);
    }

    /**
     * Returns true if the puzzle has been solved - the empty space should end
     * up in the top left corner.
     * 
     * @return True if the puzzle has been solved.
     */
    public boolean isSolved() {
        for(int i=1; i<board.length; i++) {
            if(board[i] < board[i-1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the list of moves that have been made so far.
     * 
     * @return The list of moves that have been made so far.
     */
    public List<Direction> getMoves() {
        return moves;
    }

    /**
     * Returns true if more moves have been made than are necessary to solve 
     * the puzzle.
     * 
     * @return True if more moves have been made than are necessary to solve 
     * the puzzle.
     */
    public boolean tooManyMoves() {
        return moves.size() > movesToSolve;
    }

    /**
     * Tries to make a move from the specified direction relative to the empty
     * space on the board.
     * 
     * @param from The direction from which the move is being made into the 
     * empty space on the board, i.e. above, below, left, or right.
     * 
     * @throws BadMoveException If the move is invalid because there is no tile
     * in the specified direction (i.e. it is off the edge of the board) or
     * the move puts the board into a redundant configuration (i.e. the board
     * was in this configuration previously).
     */
    public void move(Direction from) throws BadMoveException {
        int row = openIndex / size;
        int col = openIndex % size;

        switch(from) {
            case ABOVE:
                row--;
                break;
            case BELOW:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
        }

        if(row < 0 || row >= size || col < 0 || col >= size) {
            throw new BadMoveException(from);
        }

        int fromIndex = getIndex(row, col);
        swap(openIndex, fromIndex);

        String configuration = toString();
        if(configurations.contains(configuration)) {
            swap(openIndex, fromIndex);
            throw new BadMoveException(from, true);
        }
        configurations.add(configuration);
        openIndex = fromIndex;
        moves.add(from);
    }

    /**
     * Returns a 2D string representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int row=0; row<size; row++) {
            for(int col=0; col<size; col++) {
                int index = getIndex(row, col);
                int value = board[index];
                if(value == 0) {
                    builder.append("[  ]");
                } else if(value < 10) {
                    builder.append("[ " + value + "]");
                } else {
                    builder.append("[" + value + "]");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Helper method that converts a row and column coordinate into an index
     * in the flattened (1D) board.
     * 
     * @param row The row.
     * @param col The column.
     * 
     * @return The index in the flattened (1D) board that corresponds to the
     * specified row and column.
     */
    private int getIndex(int row, int col) {
        return row * size + col;
    }

    /**
     * Helper method that swaps two values on the board.
     * 
     * @param indexA The index of the first value.
     * @param indexB The index of the second value.
     */
    private void swap(int indexA, int indexB) {
        int tmp = board[indexA];
        board[indexA] = board[indexB];
        board[indexB] = tmp;
    }

    /**
     * Helper method that flattens a 2D array into a 1D array. The 2D array
     * must be square.
     * 
     * @param board The original 2D board.
     * 
     * @return The flattened (1D) board.
     */
    private static final int[] flatten(int[][] board) {
        int length = board.length * board[0].length;
        int[] flattened = new int[length];
        int index = 0;
        for(int[] row : board) {
            for(int value : row) {
                flattened[index++] = value;
            }
        }
        return flattened;
    }

    /**
     * Creates a random 8-tile puzzle and challenges the user to solve it by
     * making moves through the command line.
     * 
     * @param args Command line arguments. Not used.
     */
    public static void main(String[] args) {
        SlidingPuzzle puzzle = new SlidingPuzzle(Layout.EIGHT_6, 6);
        System.out.println(puzzle);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("You may move a tile by indicating its direction "
            + "relative to the open tile: ABOVE, BELOW, LEFT, or RIGHT. "
            + "QUIT to quit.");
        boolean stillPlaying = true;
        while(stillPlaying) {
            System.out.print("move >> ");
            String move = scanner.nextLine().toUpperCase();
            if(move.equals("QUIT") || move.equals("EXIT")) {
                stillPlaying = false;
            } else {
                try {
                    Direction direction = Direction.valueOf(move);
                    puzzle.move(direction);
                } catch(BadMoveException bme) {
                    System.out.println(bme.getMessage());
                } catch(IllegalArgumentException iae) {
                    System.out.println("I don't know what that means. "
                        + "Try again or QUIT to quit.");
                }
            }
            System.out.println("Moves: " + puzzle.getMoves());
            System.out.println(puzzle);
            if(puzzle.isSolved()) {
                System.out.println("You win!");
                stillPlaying = false;
            }

        }
        scanner.close();
        System.out.println("Goodbye!");
    }
}
