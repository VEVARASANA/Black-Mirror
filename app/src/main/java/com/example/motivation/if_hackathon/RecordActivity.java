package com.example.motivation.if_hackathon;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

    MediaRecorder recorder;
    Button recordStartButton;
    Button recordStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recorder = new MediaRecorder();
        recordStartButton = (Button) findViewById(R.id.record_recordStart);
        recordStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRec();
            }
        });

        recordStopButton = (Button) findViewById(R.id.record_recordStop);
        recordStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRec();
            }
        });


    }

    public void startRec() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    10);
        } else {
            try {
                File file = Environment.getExternalStorageDirectory();

                String path = file.getAbsolutePath() + "/" + "recoder.mp3";

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                recorder.setOutputFile(path);
                recorder.prepare();
                recorder.start();
                Toast.makeText(this, "start Record", Toast.LENGTH_LONG).show();
            } catch (IllegalStateException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void stopRec() {
        try{
            recorder.stop();
        } catch (RuntimeException ex){

        }
        recorder.release();
        Toast.makeText(this, "stop Record", Toast.LENGTH_LONG).show();
    }
}


