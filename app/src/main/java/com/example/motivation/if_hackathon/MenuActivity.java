package com.example.motivation.if_hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class MenuActivity extends AppCompatActivity {

    SwitchCompat swService;
    Button btnRecord;
    Button btnReport;
    Button btnSetting;
    ActionBar actionBar;
    String sfName = "myFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.menu);

        SharedPreferences sf = getSharedPreferences(sfName, 0);
        SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요해요
        boolean isFirstLaunchAppChecked = false;//앱을 처음으로 켰는지 확인해요
        editor.putBoolean("isFirstLaunchAppChecked", isFirstLaunchAppChecked); // 입력

        swService = (SwitchCompat) findViewById(R.id.main_sw_service);

        btnRecord = (Button) findViewById(R.id.main_btn_record);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

        btnReport = (Button) findViewById(R.id.main_btn_report);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        btnSetting = (Button) findViewById(R.id.main_btn_setting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        editor.commit(); // 파일에 최종 반영함
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요해요

        swService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Intent intent = new Intent(MenuActivity.this, ScreenService.class);
            SharedPreferences sf = getSharedPreferences(sfName, 0);
            SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요해요
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    startService(intent);
                    Log.d("isSwServiceChecked","true");
                    editor.putBoolean("isSwServiceChecked", isChecked); //마지막 값을 저장해요
                } else {
                    stopService(intent);
                    Log.d("isSwServiceChecked","false");
                    editor.putBoolean("isSwServiceChecked", isChecked); //마지막 값을 저장해요
                }
                editor.commit();
            }
        });

        boolean isSwServiceChecked = sf.getBoolean("isSwServiceChecked",false);

        if(isSwServiceChecked == true){
            Log.d("isSwServiceChecked","true");
            swService.setChecked(true);
        }else if(isSwServiceChecked == false){
            Log.d("isSwServiceChecked","false");
            swService.setChecked(false);
        }
    }
}
