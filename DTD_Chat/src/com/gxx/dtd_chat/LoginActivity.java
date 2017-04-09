package com.gxx.dtd_chat;

import com.gxx.base.BaseActivity;
import com.gxx.bean.UserBean;
import com.gxx.tools.StringUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText username,password;
	private TextView tv;
	private static final int REQUEST_CODE=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);
		init();
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
      username=(EditText) findViewById(R.id.login_username);
      password=(EditText) findViewById(R.id.login_password);
      tv=(TextView) findViewById(R.id.login_tv);
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		tv.setOnClickListener(this);
	}
//-----------------------------------------------------------------
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,RegisterActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==REQUEST_CODE&&resultCode==2)
		{
			String name=data.getStringExtra("username");
			username.setText(name);
		}
	}
	public void login_click(View v)
	{
		
		String username_text=username.getText().toString().trim();
		String password_text=password.getText().toString().trim();
		if(!StringUtils.StringisNull(username_text)&&!StringUtils.StringisNull(password_text))
		{
			UserBean ub=new UserBean();
			ub.setUsername(username_text);
			ub.setPassword(password_text);
			ub.login(new SaveListener<UserBean>() {

				@Override
				public void done(UserBean arg0, BmobException arg1) {
					// TODO Auto-generated method stub
					if(arg1==null)
					{
						toast(arg0.getUsername()+"  登录成功！");
						Intent intent=new Intent(LoginActivity.this, MainActivity.class);
						tiaozhuan(intent);
					}else{
						toast("登录失败，请重新输入");
						username.setText("");
						password.setText("");
						Log.i("gxx", arg1.getMessage());
					}
				}
			});
			
		}else{
			toast("用户名或者密码不能为空");
			username.setText("");
			password.setText("");
		}
		
	}

}
