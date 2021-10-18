package com.yue.demo;

import android.content.Intent;
import android.os.Bundle;

/**
 * @author chengyue
 * @description
 * @date 2021/10/18 22:05
 * @modify by chengyue on 2021/10/18 22:05
 */
public class SplashActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注意, 这里并没有setContentView, 单纯只是用来跳转到相应的Activity.
        // 目的是减少首屏渲染

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
