package com.yue.demo.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;

import com.iflytek.android.framework.base.BaseApplication;
import com.yue.demo.util.LogUtil;

public class MyApplication extends BaseApplication {
	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this.getApplicationContext();
		// 初始化日志开关
		LogUtil.setDebugMode(true);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	public static Context getAppContext() {
		return mContext;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	/**
	 * 应用是否处于debug模式
	 * 
	 * @return
	 */
	public static boolean isDebugMode() {
		ApplicationInfo info = getAppContext().getApplicationInfo();
		return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
	}
	
	
}
