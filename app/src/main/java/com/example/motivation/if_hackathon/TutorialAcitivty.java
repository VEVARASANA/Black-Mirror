package com.example.motivation.if_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TutorialAcitivty extends AppCompatActivity {

    ImageView pos1;
    ImageView pos2;
    ImageView pos3;
    ImageView pos4;
    ImageView pos5;
    Button nextButton;

    int curPos = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        pos1 = (ImageView) findViewById(R.id.tutorial_pos1);
        pos2 = (ImageView) findViewById(R.id.tutorial_pos2);
        pos3 = (ImageView) findViewById(R.id.tutorial_pos3);
        pos4 = (ImageView) findViewById(R.id.tutorial_pos4);
        pos5 = (ImageView) findViewById(R.id.tutorial_pos5);

        nextButton = (Button) findViewById(R.id.tutorial_nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curPos++;
                switch (curPos){
                    case 1:
                        pos1.setImageResource(R.drawable.active_round);
                    case 2:
                        pos1.setImageResource(R.drawable.active_round);
                    case 3:
                        pos1.setImageResource(R.drawable.active_round);
                    case 4:
                        pos1.setImageResource(R.drawable.active_round);
                    case 5:
                        pos1.setImageResource(R.drawable.active_round);
                        //
                }
            }
        });
    }
}
