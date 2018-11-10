package com.example.motivation.if_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        View view = findViewById(R.id.view);



        view.setOnTouchListener(new View.OnTouchListener() {

            float priX;
            float priY;

            float nexX;
            float nexY;

            float curX;
            float curY;

            int[] array;
            int i = 0;

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                curX = motionEvent.getX();
                curY = motionEvent.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    priX = curX;
                    priY = curY;
                }else if(action == MotionEvent.ACTION_UP){
                    nexX = curX;
                    nexY = curY;

                    float resX = nexX - priX;
                    float resY = nexY - priY;

                    if( (resY - resX) > 0 && (resY + resX) > 0){                 //up
                        array[i] = 1;
                    } else if( (resY - resX) > 0 && (resY + resX) < 0){          //left
                        array[i] = 2;
                    } else if( (resY - resX) < 0 && (resY + resX) < 0){          //down
                        array[i] = 3;
                    } else if( (resY - resX) < 0 && (resY + resX) > 0){          //right
                        array[i] = 4;
                    }

                    if(i < 3){
                        i++;
                        Log.d("test", "k");
                    }else{
                        Log.d("array",array.toString());
                        i = 0;
                    }

                }

                return true;
            }
        });

    }


}
