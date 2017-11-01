package com.sampson.yjj.xianba.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.base.BaseActivity;

public class WeatherAreaActivity extends BaseActivity {
    @Override
    public int getContentViewId() {
        return R.layout.activity_weather_area;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//     init();

    }
    private void init(){
        switch (getIntent().getFlags()){
            case 0:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                if(prefs.getString("weather",null)!=null)
                {
                    Intent intent = new Intent(this, WeatherActivity.class);
                    startActivity(intent);
//            finish();

                }
                break;
            case 1:
//                Intent intent = new Intent(this, WeatherActivity.class);
//                startActivity(intent);
                break;
        }
    }
}
