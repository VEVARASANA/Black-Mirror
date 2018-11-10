package com.example.motivation.if_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class LockScreenActivity extends AppCompatActivity {

    float priX;
    float priY;

    float nexX;
    float nexY;

    float resX;
    float resY;

    String array = "";
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    priX = curX;
                    priY = curY;
                    Log.d("test", "down");
                    String s1 = "" + priX;
                    Log.d("test",s1);
                    String s2 = "" + priY;
                    Log.d("test",s2);
                }else if(action == MotionEvent.ACTION_UP){
                    nexX = curX;
                    nexY = curY;
                    Log.d("test", "up");
                    String s1 = "" + nexX;
                    Log.d("test",s1);
                    String s2 = "" + nexY;
                    Log.d("test",s2);

                    resX = nexX - priX;
                    resY = nexY - priY;
                    String s3 = "" + resX;
                    Log.d("test",s3);
                    String s4 = "" + resY;
                    Log.d("test",s4);

                    if( resY - resX < 0 && resY + resX < 0){                 //up
                        Log.d("test", "1");
                        String s = "1";
                        array = array + s;
                        Log.d("test", "array = " + array);
                        i++;
                    }
                    else if( resY - resX > 0 && resY + resX < 0){          //leff
                        Log.d("test", "2");
                        String s = "2";
                       array = array + s;
                       Log.d("test", "array = " + array);
                        i++;
                    }
                    else if( resY - resX > 0 && resY + resX > 0){          //down
                        Log.d("test", "3");
                        String s = "3";
                        array = array + s;
                        Log.d("test", "array = " + array);
                        i++;
                    }
                    else if( resY - resX < 0 && resY + resX > 0){          //right
                        Log.d("test", "4");
                        String s = "4";
                        array = array + s;
                        Log.d("test", "array = " + array);
                        i++;
                    }

                    if(array.length() > 3){
                        Log.d("test","array = " + array);
                        switch(array){
                            case "1111":
                                Log.d("final","1111작동!");
                                break;
                            case "2222":
                                Log.d("final","2222작동!");
                                break;
                            case "3333":
                                Log.d("final","3333작동!");
                                break;
                            case "4444":
                                Log.d("final","4444작동!");
                                finish();
                                break;
                        }
                        array = "";
                    }
                }

                return true;
            }
        });
    }


}
