package com.example.motivation.if_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    TextView explainTitle;
    TextView explain;
    ImageView explainImg;

    int curPos = 1;
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
        explainTitle = findViewById(R.id.tutorial_explainTitle);
        explain = findViewById(R.id.tutorial_subExplain);
        startServiceButton = (Button) findViewById(R.id.tutorail_startService);
        explainImg = (ImageView) findViewById(R.id.tutorial_img);

        activateRound(curPos);
        nextButton = (Button) findViewById(R.id.tutorial_nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curPos > 5)
                    curPos = 5;
                else
                    curPos++;

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
                explainTitle.setText(R.string.explain1);
                explain.setText(R.string.sub_explain1);
                pos1.setImageResource(R.drawable.active_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.normal_round);
                explainImg.setImageResource(R.drawable.tutorial_1);
                break;
            case 2:
                Log.d(TAG, "activateRound2 curPos : " + curPos);
                explainTitle.setText(R.string.explain2);
                explain.setText(R.string.sub_explain2);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.active_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.normal_round);
                explainImg.setImageResource(R.drawable.img_phone);
                break;
            case 3:
                Log.d(TAG, "activateRound3 curPos : " + curPos);
                explainTitle.setText(R.string.explain3);
                explain.setText(R.string.sub_explain3);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.active_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.normal_round);
                explainImg.setImageResource(R.drawable.img_rec);
                break;
            case 4:
                explainTitle.setText(R.string.explain4);
                explain.setText(R.string.sub_explain4);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.active_round);
                pos5.setImageResource(R.drawable.normal_round);
                explainImg.setImageResource(R.drawable.img_help);
                break;
            case 5:

                explainTitle.setText(R.string.explain5);
                explain.setText(R.string.sub_explain5);
                pos1.setImageResource(R.drawable.normal_round);
                pos2.setImageResource(R.drawable.normal_round);
                pos3.setImageResource(R.drawable.normal_round);
                pos4.setImageResource(R.drawable.normal_round);
                pos5.setImageResource(R.drawable.active_round);
                nextButton.setVisibility(View.GONE);
                explainImg.setImageResource(R.drawable.img_warning);
                startServiceButton.setVisibility(View.VISIBLE);
                startServiceButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TutorialAcitivty.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 6:
                Intent intent = new Intent(TutorialAcitivty.this, Password_1.class);
                startActivity(intent);
                break;

        }
    }
}
