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
 * title  : FindPassword.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class FindPassword extends BaseActivity {
	
	EditText email;

	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_password);
		
		initUI();
	}

	/**
	 * 
	 */
	protected void initUI() {
		// TODO Auto-generated method stub
		email = (EditText)findViewById(R.id.find_password_email);
	}

	public void findPassword(View v){
		Toast.makeText(this, "请登录你的邮箱重置密码", Toast.LENGTH_SHORT).show();
	}
}
