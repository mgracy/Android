/**
 * 
 */
package com.tujk.istudy.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;

/**
 * title  : Setting.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class Setting extends BaseActivity {

	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
	}
	
	public void modifyInfo(View v){
		openNewActivity(UserInfo.class);
	}
	public void modifyPassword(View v){
			openNewActivity(PasswordSetting.class);
	}
	public void memoryManage(View v){
		openNewActivity(ExternalStorage.class);
	}
	public void infoTips(View v){
		openNewActivity(InfoTips.class);
	}
	public void clearMemory(View v){
		Toast.makeText(this, R.string.clear_memory_success, Toast.LENGTH_SHORT).show();
	}
	public void about(View v){
		openNewActivity(About.class);
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
