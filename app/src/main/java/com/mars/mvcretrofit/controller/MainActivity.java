package com.mars.mvcretrofit.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mars.mvcretrofit.R;
import com.mars.mvcretrofit.entity.Weather;
import com.mars.mvcretrofit.loaddingview.NetWorkUtil;
import com.mars.mvcretrofit.model.MainModelWeather;
import com.mars.mvcretrofit.model.MainModle;
import com.mars.mvcretrofit.model.OnWeatherListener;
import com.mars.mvcretrofit.view.MainView;
import com.mars.mvcretrofit.view.RequestWeatherView;

/**
 * Created by gu on 2016/05/11
 * ━━━━━━女神出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    女神保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p/>
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class MainActivity extends AppCompatActivity implements RequestWeatherView,OnWeatherListener {
    private MainView mainView;//对应的View层
    private static MainModle mainModle = null;//对应的Modle抽象类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = new MainView(this,this);//实例化View
        if(null == mainModle){
            mainModle = new MainModelWeather();//实例化抽象，多态
        }
    }

    @Override
    public void onSuccess(Weather weather) {
       mainView.showSuccess(weather);
    }

    @Override
    public void onError() {
        mainView.showFailed();
    }

    @Override
    public void sendRequest(String num) {
        if(NetWorkUtil.isNetWorkConnected(this)) {
            mainModle.getWeather(this, num, this);
        }else {
            mainView.showNoNet();
        }
    }
}
