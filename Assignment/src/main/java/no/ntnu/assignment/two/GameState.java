package no.ntnu.assignment.two;

/**
 * Created by bvx89 on 2/4/14.
 */
public class GameState {
    // Always start with 0 for both contestants
    private int mPlayerOneScore = 0;
    private int mPlayerTwoScore = 0;

    private boolean mPlayerOneStarts;

    public GameState(int playerOneScore, int playerTwoScore, boolean playerOneStarts) {
        mPlayerOneScore = playerOneScore;
        mPlayerTwoScore = playerTwoScore;
        mPlayerOneStarts = playerOneStarts;
    }

    /**
     * Takes in the players score, and decides randomly which one
     * will start the game
     *
     * @param playerOneScore
     * @param playerTwoScore
     */
    public GameState(int playerOneScore, int playerTwoScore) {
        this(playerOneScore, playerTwoScore, Math.random() > 0.5);
    }

    /**
     * Creates a new GameState with zero in scores, and decides randomly
     * which one will start the game
     */
    public GameState() {
        this(0, 0, Math.random() > 0.5);
    }


    public boolean isPlayerOneStarting() {
        return mPlayerOneStarts;
    }

    public void setPlayerOneStarting(boolean mPlayerOneStarts) {
        this.mPlayerOneStarts = mPlayerOneStarts;
    }


}
