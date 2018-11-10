package com.example.motivation.if_hackathon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ScreenReceiver extends BroadcastReceiver {

    boolean IsOnLockScreen = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Intent i = new Intent(context, LockScreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            IsOnLockScreen = true;
        }

        if(intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON) && IsOnLockScreen==true){
            Log.d("nedia button", "JESTURETEST");
        }

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF) && IsOnLockScreen==true){
            Log.d("nedia button", "HOMEATEST");
        }
    }
}
