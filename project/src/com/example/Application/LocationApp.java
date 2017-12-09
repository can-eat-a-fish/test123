package com.example.Application;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class LocationApp extends Application {

	public LocationApp() {
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(getApplicationContext());
	}

}
