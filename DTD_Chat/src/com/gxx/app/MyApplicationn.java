package com.gxx.app;

import android.app.Application;
import cn.bmob.v3.Bmob;

public class MyApplicationn extends Application {
	private static MyApplicationn instance;
	private static final String BMOB_APPKEY="00cb858e6f5cb2169c47324b3d4c1b92";
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance=this;
		Bmob.initialize(this, BMOB_APPKEY);
	}
	public static MyApplicationn getInsance()
	{
		
		return instance;
	}

}
