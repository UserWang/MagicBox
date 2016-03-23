package com.wjd.magicbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.common.api.APIParams;
import com.android.common.utils.Constants;
import com.squareup.okhttp.Request;
import com.wjd.magicbox.entity.JokeEntity;
import com.wjd.magicbox.entity.WeatherEntity;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

/**
 * Created by wangjundong on 2015/12/10.
 */
public class WeatherActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private TextView mSkTv;
    private TextView mTodayTv;
    private WeatherEntity.ResultEntity.SkEntity mSkEntity;
    private WeatherEntity.ResultEntity.TodayEntity mTodayEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        findViews();
        configViews();
        loadData();

    }

    private void findViews() {
        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);
        mSkTv = (TextView) findViewById(R.id.id_weather_sk_tv);
        mTodayTv = (TextView) findViewById(R.id.id_weather_today_tv);
    }

    private void configViews() {
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.mipmap.icon_back);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        String weather_url = APIParams.WEATHER_BASE_URL + "cityname=北京&" + "key=" + Constants.APP_KEY;
        Log.d("wjd", "weather_url = " + weather_url);
        new OkHttpRequest.Builder()
                .url(weather_url)
                .get(new ResultCallback<WeatherEntity>() {
                    @Override
                    public void onResponse(WeatherEntity response) {
                        mSkEntity = response.getResult().getSk();
                        mTodayEntity = response.getResult().getToday();
                        String sk = "当前温度 " + mSkEntity.getTemp() + " \n" + mSkEntity.getWind_direction() + mSkEntity.getWind_strength() + "\n" + "当前湿度 " + mSkEntity.getHumidity();
                        mSkTv.setText(sk);

                        String today = "今日天气:\n" + mTodayEntity.getWeather() + " " + mTodayEntity.getTemperature() + "\n" + mTodayEntity.getWind() + "\n";
                        mTodayTv.setText(today);
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        //TODO:错误提示
                    }

                });


    }
}
