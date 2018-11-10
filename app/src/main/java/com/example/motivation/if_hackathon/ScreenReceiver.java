package com.example.motivation.if_hackathon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class ScreenReceiver extends BroadcastReceiver {

    boolean IsOnLockScreen = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)&&IsOnLockScreen==false) {
            Intent i = new Intent(context, LockScreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            IsOnLockScreen = true;
        }

        KeyEvent ke = (KeyEvent)intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
        if (ke .getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            System.out.println("I got volume up event");
        }else if (ke .getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
            System.out.println("I got volume key down event");
        }


    }


}
