package com.mars.mvcretrofit.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mars.mvcretrofit.R;
import com.mars.mvcretrofit.controller.MainActivity;
import com.mars.mvcretrofit.entity.Weather;
import com.mars.mvcretrofit.loaddingview.LoadingState;
import com.mars.mvcretrofit.loaddingview.LoadingView;
import com.mars.mvcretrofit.loaddingview.OnRetryListener;


public class MainView {
    private MainActivity activity;
    RequestWeatherView iRequest;
    private EditText editNum;
    private Button btnGo;
    private LoadingView loadding;
    private TextView showText;

    public MainView(MainActivity activity,RequestWeatherView iRequest){
        this.activity = activity;this.iRequest = iRequest;
        initView();
    }
    public void btn_go(){
        loadding.setState(LoadingState.STATE_LOADING);
        loadding.setVisibility(View.VISIBLE);
        showText.setVisibility(View.GONE);
        iRequest.sendRequest(editNum.getText().toString().trim());
    }
    private void initView() {
        editNum = (EditText) activity.findViewById(R.id.edit_num);
        btnGo = (Button) activity.findViewById(R.id.btn_go);
        loadding = (LoadingView) activity.findViewById(R.id.loadding);
        showText = (TextView) activity.findViewById(R.id.show_text);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_go();
            }
        });
        loadding.withLoadedEmptyText("≥﹏≤ , 连条毛都没有 !").withEmptyIco(R.mipmap.disk_file_no_data).withBtnEmptyEnnable(false)
                .withErrorIco(R.mipmap.ic_chat_empty).withLoadedErrorText("(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了 !").withbtnErrorText("去找回她!!!")
                .withLoadedNoNetText("你挡着信号啦o(￣ヘ￣o)☞ᗒᗒ 你走").withNoNetIco(R.mipmap.ic_chat_empty).withbtnNoNetText("网弄好了，重试")
                .withLoadingIco(R.drawable.loading_animation).withLoadingText("加载中...").withOnRetryListener(new OnRetryListener() {
            @Override
            public void onRetry(){
                btn_go();
            }
        }).build();
        loadding.setVisibility(View.GONE);
        showText.setVisibility(View.GONE);
    }
    public void showSuccess(Weather w){
        loadding.setVisibility(View.GONE);
        showText.setVisibility(View.VISIBLE);
        showText.setText(w.toString());
    }
    public void showFailed(){
        loadding.setState(LoadingState.STATE_ERROR);
        loadding.setVisibility(View.VISIBLE);
        showText.setVisibility(View.GONE);
    }
    public void showNoNet(){
        loadding.setState(LoadingState.STATE_NO_NET);
        loadding.setVisibility(View.VISIBLE);
        showText.setVisibility(View.GONE);
    }
}
