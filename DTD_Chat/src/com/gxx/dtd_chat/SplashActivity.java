package com.gxx.dtd_chat;

import com.gxx.base.BaseActivity;
import com.gxx.tools.NetUtils;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import cn.org.bjca.ui.dialog.CommonDialog;
import cn.org.bjca.ui.dialog.CommonDialog.OnSweetClickListener;

public class SplashActivity extends BaseActivity implements AnimationListener {
	private LinearLayout ll;
	private AnimationSet set;
	private boolean isNet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_splash);
		initViews();
		initAnim();
	}

	private void initAnim() {
		// TODO Auto-generated method stub
		set = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.set_splash);
		set.setAnimationListener(this);
		ll.setAnimation(set);
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		ll = (LinearLayout) findViewById(R.id.splash_ll);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		isNet=NetUtils.isNetConnected(this);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if(isNet)
		{
			
			Intent intent=new Intent(this,LoginActivity.class);
			//��ת��loginactivity
			tiaozhuan(intent);
			this.finish();
		}else{
			
			CommonDialog  dialog=new CommonDialog(this, CommonDialog.ERROR_TYPE);
			dialog.setCancelText("ȡ��");
			dialog.setConfirmText("ȷ��");
			dialog.setTitleText("����");
			dialog.setContentText("�����ֻ�û����������ȥ���ã�");
			dialog.setCancelClickListener(new OnSweetClickListener() {
				
				@Override
				public void onClick(CommonDialog commonDialog) {
					// TODO Auto-generated method stub
					SplashActivity.this.finish();
				}
			});
			dialog.setConfirmClickListener(new OnSweetClickListener() {
				
				@Override
				public void onClick(CommonDialog commonDialog) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(Settings.ACTION_SETTINGS);
					//����  ��ת������ҳ��
					tiaozhuan(intent);
					SplashActivity.this.finish();
				}
			});
			dialog.show();
		}
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		
	}

}
