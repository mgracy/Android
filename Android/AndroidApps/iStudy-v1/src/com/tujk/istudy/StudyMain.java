package com.tujk.istudy;

import android.os.Bundle;
import android.os.Handler;

import cn.sharesdk.framework.AbstractWeibo;

import com.tujk.istudy.ui.LoginActivity;
import com.tujk.istudy.ui.MainMenu;

/**
 * title  : StudyMain.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-22
 */
public class StudyMain extends BaseActivity {

	Handler hanlder = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
//				gotoMainMenu();
				gotoLogin();
				break;
			default:
//				gotoMainMenu();
				break;
			}
			super.handleMessage(msg);
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_main);
		AbstractWeibo.initSDK(this);
		new WaitingThread().start();
	}

	/**
	 * 
	 */
	protected void gotoLogin() {
		// TODO Auto-generated method stub
		this.finish();
		openNewActivity(LoginActivity.class);
	}

	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		AbstractWeibo.stopSDK(this);
	}
	
	/**
	 * 
	 */
	protected void gotoMainMenu() {
		// TODO Auto-generated method stub
		openNewActivity(MainMenu.class);
		this.finish();
	}

	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return hanlder;
	}

	class WaitingThread extends Thread{
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(10);
				sendMessage(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
