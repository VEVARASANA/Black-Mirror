package com.example.motivation.if_hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
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

        swService = (SwitchCompat) findViewById(R.id.main_sw_service);
        swService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Intent intent = new Intent(MenuActivity.this, ScreenService.class);

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    startService(intent);
                } else {
                    stopService(intent);
                }
            }
        });

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

        SharedPreferences sf = getSharedPreferences(sfName, 0);
        SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요
        boolean isFirstLaunchAppChecked = false;
        editor.putBoolean("isFirstLaunchAppChecked", isFirstLaunchAppChecked); // 입력
        editor.commit(); // 파일에 최종 반영함

    }
}
