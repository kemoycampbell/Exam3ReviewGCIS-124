package practicum3.slidingpuzzle;

/**
 * Provides some puzzles of various sizes that are solvable. Can be used to 
 * make a sliding puzzle, e.g. new SlidingPuzzle(Layout.THREE_3, 3)
 */
public class Layout {
    /**
     * A 3-tile (2x2) puzzle solvable in 3 moves.
     */
    public static final int[][] THREE_3 = {
        {2, 0},
        {3, 1}
    };

    /**
     * An 8-tile puzzle (3x3) solvable in 6 moves.
     */
    public static final int[][] EIGHT_6 = {
        {3, 1, 0},
        {6, 4, 2},
        {7, 8, 5}
    };

    /**
     * An 8-tile (3x3) puzzle solvable in 31 moves.
     */
    public static final int[][] EIGHT_31_A = {
        {8, 6, 7}, 
        {2, 5, 4}, 
        {3, 0, 1}
    };

    /**
     * An 8-tile (3x3) puzzle solvable in 31 moves.
     */
    public static final int[][] EIGHT_31_B = {
        {6, 4, 7}, 
        {8, 5, 0}, 
        {3, 2, 1}
    };

    /**
     * A 15-tile (4x4) puzzle solvable in 6 moves.
     */
    public static final int[][] FIFTEEN_6 = {
        { 1,  5,  2,  3}, 
        { 4,  6, 10,  7}, 
        { 8,  9, 11, 15}, 
        {12, 13, 14,  0}
    };   
}
