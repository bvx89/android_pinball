package no.ntnu.assignment.two;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import sheep.game.Game;

public class MainActivity extends Activity {

    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;

    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get resolution of display
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        WINDOW_HEIGHT = dm.heightPixels;
        WINDOW_WIDTH  = dm.widthPixels;

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
