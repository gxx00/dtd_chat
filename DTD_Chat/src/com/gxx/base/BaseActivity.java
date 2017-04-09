package com.gxx.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public abstract class BaseActivity extends Activity{

	public abstract  void initViews();
	
	public void tiaozhuan(Intent intent)
	{
		startActivity(intent);
	}
	public abstract void initListeners();
	public void init()
	{
		initViews();
		initListeners();
	}
	public void toast(String text)
	{
		
		Toast.makeText(getApplicationContext(), text, 0).show();
	}
	
	
	
	
	
	
}
