package com.tujk.istudy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tujk.istudy.ui.MainMenu;

/**
 * title  : BaseActivity.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-4-26
 */
public abstract class BaseActivity extends Activity{	
	protected final int DIALOG_MENU = 30;
	protected final static int DIALOG_MENU_1=31;
	protected final static int DIALOG_MENU_2=32;
	protected boolean back = false;
	
	protected Button searchButton;
	protected Button searchContent;
	protected EditText searchKeyword;
	
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
	}
	
	abstract protected Handler getHandler();
	
	protected synchronized boolean sendMessage(int nMSGID) {
		boolean ret = false;
		if (getHandler() == null) {
			return ret;
		}
		
		if (!getHandler().hasMessages(nMSGID)) {
			Message msg = new Message();
			msg.what = nMSGID;
			ret = getHandler().sendMessage(msg);
		} else {
			ret = getHandler().sendMessage(getHandler().obtainMessage(nMSGID));
		}
		return ret;
	}
	

	protected void onKeyMenu(){}
	protected void onKeySearch(){}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_MENU){
			 onKeyMenu();
			 return true;
		}else if(keyCode==KeyEvent.KEYCODE_SEARCH){
			 onKeySearch();
			 return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(""+this.getClass().getName(),"onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(""+this.getClass().getName(),"onPause");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(""+this.getClass().getName(),"onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(""+this.getClass().getName(),"onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(""+this.getClass().getName(),"onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(""+this.getClass().getName(),"onStop");
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		Log.i(""+this.getClass().getName(),"finish");
	}
	
	protected void openNewActivity(Class clazz){
		Log.i(" -NEW- ","start " + clazz.getName() + " activity ");
		Intent intent = new Intent();
		intent.setClass(this, clazz);
		startActivity(intent);
	}
	
	protected void initUI(){};
	
	public void gotoMenu(View v){
		MainMenu.slidingMenuView.clickMenu();
	}
	
	public void gotoHome(View v){
		MainMenu.gotoHome = true;
		openNewActivity(MainMenu.class);
	}
	
	public void searchButton(View v){
		searchContent.setVisibility(View.VISIBLE);
		searchButton.setVisibility(View.GONE);
		searchKeyword.setVisibility(View.VISIBLE);
	}
	
	public void back(View v){
		this.finish();
	}
}
