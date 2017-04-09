package com.gxx.dtd_chat;

import com.gxx.base.BaseActivity;
import com.gxx.bean.UserBean;
import com.gxx.tools.StringUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements OnCheckedChangeListener, OnFocusChangeListener {
	private EditText username, password, repassword, email;
	private RadioGroup rg;
	private String sex = "男";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_register);
		init();
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		username = (EditText) findViewById(R.id.register_username);
		password = (EditText) findViewById(R.id.register_password);
		repassword = (EditText) findViewById(R.id.register_repassword);
		email = (EditText) findViewById(R.id.register_email);
		rg = (RadioGroup) findViewById(R.id.register_sex);

	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		rg.setOnCheckedChangeListener(this);
		repassword.setOnFocusChangeListener(this);
	}

	// ------------------------------------------------------------------------------
	public void register1_click(View v) {
		
		String username_text=getT(username);
		String password_text=getT(password);
		String email_text=getT(email);
	    if(!StringUtils.StringisNull(username_text)&&!StringUtils.StringisNull(password_text)&&StringUtils.StringisEmail(email_text))
	    {
	    	
	    	//启动bmob的服务
	    	UserBean ub=new UserBean();
	    	ub.setUsername(username_text);
	    	ub.setPassword(password_text);
	    	ub.setEmail(email_text);
	    	ub.setSex(sex);
	    	ub.signUp(new SaveListener<UserBean>() {

				@Override
				public void done(UserBean arg0, BmobException arg1) {
					// TODO Auto-generated method stub
					if(arg1==null)
					{
						toast("注册成功");
						Intent data=new Intent();
						data.putExtra("username", arg0.getUsername());
						RegisterActivity.this.setResult(2, data);
						RegisterActivity.this.finish();
					}else{
						
						Log.i("gxx", arg1.getMessage());
					}
				}
			});
	    }else{
	    	toast("注册信息有误，重新输入");
	    	username.setText("");
	    	password.setText("");
	    	repassword.setText("");
	    	email.setText("");
	    	
	    }
		

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		sex = checkedId == R.id.register_nan ? "男" : "女";
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if (!hasFocus) {
			String password_text = password.getText().toString().trim();
			String repassword_text = repassword.getText().toString().trim();
			if (!password_text.equals(repassword_text)) {
				toast("两次密码输入不一样");
				password.setText("");
				repassword.setText("");
			}

		}

	}
	private String getT(EditText et)
	{
		return et.getText().toString().trim();
	}
}
