package no.ntnu.assignment.two;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import sheep.game.Game;

public class MainActivity extends Activity {
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get resolution of display
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // Set main constants
        Config.WINDOW_HEIGHT = dm.heightPixels;
        Config.WINDOW_WIDTH  = dm.widthPixels;

        // Calculate the line width/heights
        float lineWidth  = Config.WINDOW_WIDTH  * Config.PERCENTAGE;
        float lineHeight = Config.WINDOW_HEIGHT * Config.PERCENTAGE;


        // Choose the largest to be the new grid size
        Config.GRID_SIZE = (int)(lineHeight > lineWidth ? lineHeight : lineWidth);

        // Find the width of the board
        Config.BOARD_WIDTH = Config.WINDOW_WIDTH - Config.GRID_SIZE * 2;


        init();
    }

    private void init() {
        mGame = new Game(this, null);

        // Push the main state.
        mGame.pushState(PongMachine.getInstance());

        // View the game.
        setContentView(mGame);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mGame.surfaceDestroyed(null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        PongMachine.getInstance().reset();

        init();
    }

}
