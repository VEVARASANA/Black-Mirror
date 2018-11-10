package com.example.motivation.if_hackathon;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaRecorder;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class RecordActivity extends Activity {

    ImageView btnRecord;
    MediaRecorder mediaRecorder;
    String path = "";
    Button btnStop;
    boolean isActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

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
                    mediaRecorder.stop();
                    finish();
                    Toast.makeText(getApplicationContext(), "녹음완료", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}