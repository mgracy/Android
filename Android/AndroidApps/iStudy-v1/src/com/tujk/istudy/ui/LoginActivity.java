/**
 * 
 */
package com.tujk.istudy.ui;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;

/**
 * title  : LoginActivity.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-4-26
 */
public class LoginActivity extends BaseActivity {

	EditText username;
	EditText password;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				break;
			default:
				break;
			}
		};
	};
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initUI();
	}
	
	
	public void login(View v){
//		Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
		openNewActivity(MainMenu.class);
		this.finish();
	}
	
	public void forgetPassword(View v){
		openNewActivity(FindPassword.class);
	}
	
	public void register(View v){
		openNewActivity(Register.class);
		this.finish();
	}
	
	
	/**
	 * 
	 */
	protected void initUI() {
		username = (EditText)findViewById(R.id.login_username);
		password = (EditText)findViewById(R.id.login_password);
	}





	@Override
	protected Handler getHandler() {
		return handler;
	}

}
