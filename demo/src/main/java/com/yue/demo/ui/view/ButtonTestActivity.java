package com.yue.demo.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.yue.demo.R;
import com.yue.demo.ui.view.customview.HalfCircleProgressView;

public class ButtonTestActivity extends Activity {

    HalfCircleProgressView progressView;
    int gear = 4;
    int maxGear = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_button_test);

        progressView = findViewById(R.id.progress);
        progressView.setMax(maxGear);
        progressView.setGear(gear);
        findViewById(R.id.btn_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gear--;
                progressView.setGear(getGear());
            }
        });
        findViewById(R.id.btn_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setRepeat(true);
                gear++;
                progressView.setGear(getGear());
            }
        });
    }

    private int getGear() {
        if (gear < 0) {
            gear = 0;
        } else if (gear > maxGear) {
            gear = maxGear;
        }
        return gear;
    }

}
