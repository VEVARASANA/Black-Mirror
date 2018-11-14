package com.example.motivation.if_hackathon;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaRecorder;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class RecordActivity extends Activity {

    private static final int MILLISINFUTURE = 1500 * 1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;

    private int counter = 0, minute, second;
    private CountDownTimer countDownTimer;

    ImageView btnRecord;
    TextView textRecordTime;
    MediaRecorder mediaRecorder;
    String path = "";

    boolean isActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        textRecordTime = (TextView) findViewById(R.id.record_time);

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(RecordActivity.this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/siren.3gp";

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(path);

        btnRecord = (ImageView) findViewById(R.id.record_recordStart);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countDownTimer();
                countDownTimer.start();

                if (isActive == true) {
                    try {
                        mediaRecorder.prepare(); //녹음을 준비함 : 지금까지의 옵션에서 문제가 발생했는지 검사함
                        mediaRecorder.start();
                        Toast.makeText(getApplicationContext(), "녹음시작", Toast.LENGTH_LONG).show();
                        btnRecord.setImageResource(R.drawable.active_record);
                        btnRecord.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        Log.d("isActive", "isActive : " + isActive);
                        isActive = !isActive;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (isActive == false) {
                    isActive = !true;
                    Log.d("isActive", "isActive : " + isActive);
                    btnRecord.setImageResource(R.drawable.normal_record);
                    btnRecord.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    countDownTimer.onFinish();
                    mediaRecorder.stop();
                    finish();
                    Toast.makeText(getApplicationContext(), "녹음완료", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void countDownTimer() {

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {

            @Override
            public void onTick(long millisUntilFinished) {
                textRecordTime.setText(timeText(counter));
                Log.d("Record", timeText(counter).toString());
                counter++;
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
            }
        };
    }

    public String timeText(int count) {

        Log.d("TimeText", "" + count);
        minute = count / 60;
        second = count % 60;

        if (minute < 10 && second < 10)
            return "0" + minute + ":" + "0" + second;

        else if (minute < 10)
            return "0" + minute + ":" + second;

        else if (second < 10)
            return minute + ":" + "0" + second;

        else
            return minute + ":" + second;
    }
}