package com.example.motivation.if_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class LockScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

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
                    Log.d("test", "k");
                }else if(action == MotionEvent.ACTION_UP){
                    nexX = curX;
                    nexY = curY;
                    Log.d("test", "k");

                    float resX = nexX - priX;
                    float resY = nexY - priY;

                    if( (resY - resX) > 0 && (resY + resX) > 0){                 //up
                        array[i] = 1;
                        Log.d("test", "k");
                    } else if( (resY - resX) > 0 && (resY + resX) < 0){          //left
                        array[i] = 2;
                        Log.d("test", "k");
                    } else if( (resY - resX) < 0 && (resY + resX) < 0){          //down
                        array[i] = 3;
                        Log.d("test", "k");
                    } else if( (resY - resX) < 0 && (resY + resX) > 0){          //right
                        array[i] = 4;
                        Log.d("test", "k");
                    }

                    if(i < 3){
                        i++;
                        Log.d("test", "k");
                    }else{
                        Log.d("test",array.toString());
                        i = 0;
                    }

                }

                return true;
            }
        });
    }


}
