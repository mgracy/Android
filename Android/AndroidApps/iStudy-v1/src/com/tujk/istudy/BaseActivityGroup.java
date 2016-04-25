package com.tujk.istudy;

import android.app.ActivityGroup;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;

/**
 * title  : BaseActivityGroup.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public abstract class BaseActivityGroup extends ActivityGroup {

	protected static final int DIALOG_MENU = 30;
	protected final static int DIALOG_MENU_1=31;
	protected final static int DIALOG_MENU_2=32;
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
//		LayoutInflater layoutInflater = LayoutInflater.from(this);
//		View alert = layoutInflater.inflate(R.layout.quite_alert, null);
//		   
//		new AlertDialog.Builder(this).setTitle(R.string.tips).setView( 
//				alert).setPositiveButton(R.string.system_confirm,
//		         new DialogInterface.OnClickListener() { 
//		        	 
//		             public void onClick(DialogInterface dialog, int which) {
//		                 finish();
//		             }
//		         }).setNegativeButton(R.string.system_cancel,
//		         new DialogInterface.OnClickListener() {
//		             public void onClick(DialogInterface dialog, int which) {
//		                  //do nothing
//		             }
//		         }).show();
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
	protected void onPause() {
		super.onPause();
		Log.i(""+this.getClass().getName(),"onPause");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(""+this.getClass().getName(),"onResume");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(""+this.getClass().getName(),"onDestroy");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(""+this.getClass().getName(),"onRestart");
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
}
