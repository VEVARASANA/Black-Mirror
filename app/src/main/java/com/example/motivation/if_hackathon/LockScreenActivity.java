package com.example.motivation.if_hackathon;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class LockScreenActivity extends AppCompatActivity {

    float priX;
    float priY;

    float nexX;
    float nexY;

    float resX;
    float resY;

    String array = "";
    int i = 0;

    private static final int PERMISSIONS_REQUEST_READ_SMS = 100;
    final static String policeNumber = "tel:01022691061";

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_lock_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        final int permissonCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if (permissonCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(LockScreenActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSIONS_REQUEST_READ_SMS);

        }

        mediaRecorder = new MediaRecorder();

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record.3gp";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(path);
        mediaPlayer = new MediaPlayer();


        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    priX = curX;
                    priY = curY;
                    Log.d("test", "down");
                    String s1 = "" + priX;
                    Log.d("test", s1);
                    String s2 = "" + priY;
                    Log.d("test", s2);
                } else if (action == MotionEvent.ACTION_UP) {
                    nexX = curX;
                    nexY = curY;
                    Log.d("test", "up");
                    String s1 = "" + nexX;
                    Log.d("test", s1);
                    String s2 = "" + nexY;
                    Log.d("test", s2);

                    resX = nexX - priX;
                    resY = nexY - priY;
                    String s3 = "" + resX;
                    Log.d("test", s3);
                    String s4 = "" + resY;
                    Log.d("test", s4);

                    if (resY - resX < 0 && resY + resX < 0) {                 //up
                        Log.d("test", "1");
                        String s = "1";
                        array = array + s;
                        Log.d("test", "array = " + array);
                        i++;
                    } else if (resY - resX > 0 && resY + resX < 0) {          //leff
                        Log.d("test", "2");
                        String s = "2";
                        array = array + s;
                        Log.d("test", "array = " + array);
                        i++;
                    } else if (resY - resX > 0 && resY + resX > 0) {          //down
                        Log.d("test", "3");
                        String s = "3";
                        array = array + s;
                        Log.d("test", "array = " + array);
                        i++;
                    } else if (resY - resX < 0 && resY + resX > 0) {          //right
                        Log.d("test", "4");
                        String s = "4";
                        array = array + s;
                        Log.d("test", "array = " + array);
                        i++;
                    }

                    if (array.length() > 3) {
                        Log.d("test", "array = " + array);
                        switch (array) {
                            case "1111":
                                Log.d("final", "메세지 신고");
                                String test = "Test";
                                sendSMS(policeNumber, test);
                                callPermission();
                                break;
                            case "2222":
                                Log.d("final", "녹음");
                                try {
                                mediaRecorder.prepare(); //녹음을 준비함 : 지금까지의 옵션에서 문제가 발생했는지 검사함
                                mediaRecorder.start();
                                Toast.makeText(getApplicationContext(), "녹음시작", Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                                break;

                            case"2323":
                                mediaRecorder.stop();
                                Toast.makeText(getApplicationContext(), "녹음완료", Toast.LENGTH_LONG).show();
                                break;
                            case "3333":
                                Log.d("final", "사이렌");
                                try {
                                    mediaPlayer.setDataSource(path);
                                    mediaPlayer.prepare();
                                    mediaPlayer.start();
                                } catch (Exception e){
                                    Log.d("Lock", "play failed");
                                }
                                break;
                            case "3434":
                                mediaPlayer.stop();
                                break;
                            case "4444":
                                Log.d("final", "stop");
                                finish();
                                break;
                        }
                        final Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(1000);
                        array = "";
                    }
                }

                return true;
            }
        });
    }

    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.READ_SMS},
                    PERMISSIONS_REQUEST_READ_SMS);
        } else {
            // 해당 로직으로 이동
            sendSMS(policeNumber, "test");
        }
    }


    public void sendSMS(String smsNumber, String smsText) {
        PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT_ACTION"), 0);
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED_ACTION"), 0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        // 전송 성공
                        Toast.makeText(getApplicationContext(), "전송 완료", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        // 전송 실패
                        Toast.makeText(getApplicationContext(), "전송 실패", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        // 서비스 지역 아님
                        Toast.makeText(getApplicationContext(), "서비스 지역이 아닙니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        // 무선 꺼짐
                        Toast.makeText(getApplicationContext(), "무선(Radio)가 꺼져있습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        // PDU 실패
                        Toast.makeText(getApplicationContext(), "PDU Null", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT_ACTION"));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        // 도착 완료
                        Toast.makeText(getApplicationContext(), "SMS 도착 완료", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        // 도착 안됨
                        Toast.makeText(getApplicationContext(), "SMS 도착 실패", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_DELIVERED_ACTION"));

        SmsManager mSmsManager = SmsManager.getDefault();
        mSmsManager.sendTextMessage(smsNumber, null, smsText, sentIntent, deliveredIntent);
    }
}
