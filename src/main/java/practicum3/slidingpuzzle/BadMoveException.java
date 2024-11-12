package practicum3.slidingpuzzle;

/**
 * An exception that indicates that a move the player attempted to make is bad,
 * either because the a tile does not exist (i.e. it is off the egde of the
 * board), or it puts the puzzle into a redundant configuration (i.e. returns 
 * it to a previous layout) and is therefore not a part of the optimal solution.
 */
public class BadMoveException extends Exception {
    /**
     * Used to indicate that there is no tile in the specified direction 
     * relative to the empty space on the board.
     * 
     * @param from The direction from which the move was attempted.
     */
    public BadMoveException(Direction from) {
        this(from, false);
    }

    /**
     * Used to indicate the type of bade move that was made.
     * 
     * @param from The direction from which the move was attempted.
     * @param redundant Indicates that the move returned the board to a layout
     * that it had been in previously, and thus cannot be part of the optimal
     * solution.
     */
    public BadMoveException(Direction from, boolean redundant) {
        super((redundant ? "Redundant move: " : "Cannot move from: ") + from);
    }
    
}
