package com.example.motivation.if_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialAcitivty extends AppCompatActivity {

    ImageView pos1;
    ImageView pos2;
    ImageView pos3;
    ImageView pos4;
    ImageView pos5;
    Button nextButton;
    Button previousButton;
    Button startServiceButton;
    TextView explainTextView;

    int curPos = 0;
    String TAG = "TutorialActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        pos1 = (ImageView) findViewById(R.id.tutorial_pos1);
        pos2 = (ImageView) findViewById(R.id.tutorial_pos2);
        pos3 = (ImageView) findViewById(R.id.tutorial_pos3);
        pos4 = (ImageView) findViewById(R.id.tutorial_pos4);
        pos5 = (ImageView) findViewById(R.id.tutorial_pos5);
        explainTextView = findViewById(R.id.tutorial_explain);
        startServiceButton = (Button) findViewById(R.id.tutorail_startService);

        nextButton = (Button) findViewById(R.id.tutorial_nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curPos > 5)
                    curPos = 5;
                else
                    curPos++;

                Log.d(TAG, "curPos : " + curPos);
                activateRound(curPos);
            }
        });

        previousButton = (Button) findViewById(R.id.tutorial_previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curPos < 0)
                    curPos = 1;
                else
                    curPos--;

                Log.d(TAG, "curPos : " + curPos);
                activateRound(curPos);
            }
        });

    }

    private void activateRound(int curPos) {
        switch (curPos) {
            case 1:
                Log.d(TAG, "activateRound1 curPos : " + curPos);
                explainTextView.setText(R.string.explain1);
                pos1.setImageResource(R.drawable.active_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.normal_round);
                break;
            case 2:
                Log.d(TAG, "activateRound2 curPos : " + curPos);
                explainTextView.setText(R.string.explain2);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.active_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.normal_round);
                break;
            case 3:
                Log.d(TAG, "activateRound3 curPos : " + curPos);
                explainTextView.setText(R.string.explain3);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.active_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.normal_round);
                break;
            case 4:
                explainTextView.setText(R.string.explain4);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.active_round);
                pos5.setImageResource(R.drawable.normal_round);
                break;
            case 5:

                explainTextView.setText(R.string.explain5);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.active_round);
                startServiceButton.setVisibility(View.VISIBLE);
                startServiceButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case 6:

        }
    }
}
