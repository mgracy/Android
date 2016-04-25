/**
 * 
 */
package com.tujk.istudy.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;

/**
 * title  : Register.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class Register extends BaseActivity {
	
	EditText username;
	EditText password;
	EditText repassword;
	EditText email;
	
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		iniuUI();
		
	}
	
	public void registe(View v) {
		Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
		openNewActivity(MainMenu.class);
		this.finish();
	}

	/**
	 * 
	 */
	protected void iniuUI() {
		// TODO Auto-generated method stub
		username = (EditText)findViewById(R.id.registe_username);
		password = (EditText)findViewById(R.id.registe_password);
		repassword = (EditText)findViewById(R.id.registe_repassword);
		email = (EditText)findViewById(R.id.registe_email);
	}

	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
