package com.example.kabelo.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Year;

public class MainActivity extends AppCompatActivity {

    // player 1: 0 ,player 2: 1 ,empty: 2
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    String winner;
    boolean gameActive = true;

    public void dropIn(View view)
    {

        ImageView counter = (ImageView) view;
        int counterTapped = Integer.parseInt(counter.getTag().toString());

        if (gameState[counterTapped] == 2 && gameActive) {

            gameState[counterTapped] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);

                activePlayer = 1;
            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winPos : winningPositions) {
                if (gameState[winPos[0]] == gameState[winPos[1]] && gameState[winPos[1]] == gameState[winPos[2]] && gameState[winPos[0]] != 2) {
                    if (activePlayer == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    gameActive = false;

                    TextView textView = (TextView) findViewById(R.id.textView);
                    Button button = (Button) findViewById(R.id.button);

                    textView.setText(winner + " has won");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(view.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view)
    {
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(view.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gameActive = true;

        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        for (int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
