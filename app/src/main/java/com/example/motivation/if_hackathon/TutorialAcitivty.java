package com.example.motivation.if_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialAcitivty extends AppCompatActivity {

    Button btnNext;
    Button btnPrevious;
    Button btnStart;
    TextView textTitle;
    TextView textContent;
    ImageView imgContent;

    ImageView[] imgPosArr = new ImageView[5];
    int[] textTitleArr = {R.string.tutorial_title0, R.string.tutorial_title1, R.string.tutorial_title2, R.string.tutorial_title3, R.string.tutorial_title4};
    int[] textContentArr = {R.string.tutorial_content0, R.string.tutorial_content1, R.string.tutorial_content2, R.string.tutorial_content3, R.string.tutorial_content4};
    int[] imgContentArr = {R.drawable.img_purpose, R.drawable.img_phone, R.drawable.img_rec, R.drawable.img_help, R.drawable.img_warning};

    int curPos = 0;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        imgPosArr[0] = (ImageView) findViewById(R.id.tutorial_pos1);
        imgPosArr[1] = (ImageView) findViewById(R.id.tutorial_pos2);
        imgPosArr[2] = (ImageView) findViewById(R.id.tutorial_pos3);
        imgPosArr[3] = (ImageView) findViewById(R.id.tutorial_pos4);
        imgPosArr[4] = (ImageView) findViewById(R.id.tutorial_pos5);
        textTitle = findViewById(R.id.tutorial_explainTitle);
        textContent = findViewById(R.id.tutorial_subExplain);
        btnStart = (Button) findViewById(R.id.tutorial_startButton);
        imgContent = (ImageView) findViewById(R.id.tutorial_img);

        btnNext = (Button) findViewById(R.id.tutorial_nextButton);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curPos < 4) curPos++;
                nowPage(curPos);
            }
        });

        btnPrevious = (Button) findViewById(R.id.tutorial_previousButton);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curPos > 0) curPos--;
                nowPage(curPos);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialAcitivty.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nowPage(curPos);
    }

    private void nowPage(int curPos) {

        textTitle.setText(textTitleArr[curPos]);
        textContent.setText(textContentArr[curPos]);
        imgContent.setImageResource(imgContentArr[curPos]);

        if (curPos == 4) {
            btnStart.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.GONE);
        } else {
            btnStart.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
        }

        if(curPos == 0)
            btnPrevious.setVisibility(View.GONE);
        else
            btnPrevious.setVisibility(View.VISIBLE);

        for (i = 0; i < imgPosArr.length; i++) {
            if (i == curPos)
                imgPosArr[i].setImageResource(R.drawable.active_round);
            else
                imgPosArr[i].setImageResource(R.drawable.normal_round);
        }
    }
}
