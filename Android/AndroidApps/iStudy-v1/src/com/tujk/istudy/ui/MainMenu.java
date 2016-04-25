package com.tujk.istudy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.tujk.istudy.BaseActivityGroup;
import com.tujk.istudy.R;
import com.tujk.istudy.ui.widget.SlidingMenuView;

/**
 * title  : MainMenu.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class MainMenu extends BaseActivityGroup {
	
	public static boolean gotoHome = false;
	public static boolean gotoFriends = false;
	public static boolean gotoFavorite = false;
	public static boolean gotoDownload = false;
	public static boolean gotoEditDownload = false;
	
	
	public static SlidingMenuView slidingMenuView;
	ViewGroup tabcontent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        slidingMenuView = (SlidingMenuView) findViewById(R.id.sliding_menu_view);
        
        tabcontent = (ViewGroup) slidingMenuView.findViewById(R.id.sliding_body);
        showTab("main");
    }
    
    /* (non-Javadoc)
     * @see com.tujk.istudy.BaseActivityGroup#onResume()
     */
    @Override
    protected void onResume() {
    	super.onResume();
    	if(gotoHome){
    		showTab("main");
    		gotoHome = false;
    	}
    	
    	if(gotoFriends){
    		showTab("friends");
    		gotoFriends = false;
    	}
    	
    	if(gotoFavorite){
    		showTab("favorite");
    		gotoFavorite = false;
    	}
    	
    	if(gotoDownload){
    		showTab("download");
    		gotoDownload = false;
    	}
    	
    	if(gotoEditDownload){
    		showTab("editDownload");
    		gotoEditDownload = false;
    	}
    }
    
    public void hideMenu(View view){
    	slidingMenuView.scrollRight();
    }
    
    public void showMenu(View view){
    	slidingMenuView.scrollLeft();
    }
    
    public void myFavorites(View view){
    	Intent i = new Intent(this,MyFavorites.class);
    	View v = getLocalActivityManager().startActivity(MyFavorites.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		slidingMenuView.scrollRight();
    }
    public void myProgress(View view){
    	Intent i = new Intent(this,MyProgress.class);
    	View v = getLocalActivityManager().startActivity(MyProgress.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		slidingMenuView.scrollRight();
    }
    public void downloadList(View view){
    	Intent i = new Intent(this,DownloadList.class);
    	View v = getLocalActivityManager().startActivity(DownloadList.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		slidingMenuView.scrollRight();
    }
    public void myFriends(View view){
    	Intent i = new Intent(this,MyFriends.class);
    	View v = getLocalActivityManager().startActivity(MyFriends.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		slidingMenuView.scrollRight();
    }
    public void myCar(View view){
    	Intent i = new Intent(this,MyPurchase.class);
    	View v = getLocalActivityManager().startActivity(MyPurchase.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		slidingMenuView.scrollRight();
    }
    public void myOrder(View view){
    	Intent i = new Intent(this,MyOrder.class);
    	View v = getLocalActivityManager().startActivity(MyOrder.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		slidingMenuView.scrollRight();
    }
    public void setting(View view){
    	Intent i = new Intent(this,Setting.class);
    	View v = getLocalActivityManager().startActivity(Setting.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		slidingMenuView.scrollRight();
    }
    
    private void showTab(String name){
    	Intent i = null;
    	View v = null;
    	if("main".equals(name)){
    		i = new Intent(this,MainInterface.class);
    		v = getLocalActivityManager().startActivity(MainInterface.class.getName(), i).getDecorView();
    	}else if("friends".equals(name)){
    		i = new Intent(this,MyFriends.class);
    		v = getLocalActivityManager().startActivity(MyFriends.class.getName(), i).getDecorView();
    	}else if("favorite".equals(name)){
    		i = new Intent(this,MyFavorites.class);
    		v = getLocalActivityManager().startActivity(MyFavorites.class.getName(), i).getDecorView();
    	}else if("download".equals(name)){
    		i = new Intent(this,DownloadList.class);
    		v = getLocalActivityManager().startActivity(DownloadList.class.getName(), i).getDecorView();
    	}else if("editDownload".equals(name)){
    		i = new Intent(this,EditDownloadList.class);
    		v = getLocalActivityManager().startActivity(EditDownloadList.class.getName(), i).getDecorView();
    	}
		tabcontent.removeAllViews();
		tabcontent.addView(v);
    }

	@Override
	protected Handler getHandler() {
		return null;
	}
}
