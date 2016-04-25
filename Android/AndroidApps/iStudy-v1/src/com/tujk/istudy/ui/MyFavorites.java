/**
 * 
 */
package com.tujk.istudy.ui;

import java.util.List;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.FavoriteBookListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.Book;

/**
 * title  : MyFavorites.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class MyFavorites extends BaseActivity{
	
	List<Book> favoriteList;
	ListView favoriteListView;

	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
	
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_favorite);
		initUI();
		showFavorite();
	};
	
	public void searchContent(View v){
		//do search
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		searchButton = (Button)findViewById(R.id.myfav_search_button);
		searchContent = (Button)findViewById(R.id.myfav_search_content);
		searchKeyword = (EditText)findViewById(R.id.myfav_search_textview);
		
		favoriteListView = (ListView)findViewById(R.id.my_favoriate_listview);
	}
	
	private void showFavorite(){
		favoriteList = DemoData.getMainListBooks();
		FavoriteBookListAdapter adapter = new FavoriteBookListAdapter(this, favoriteList);
		favoriteListView.setAdapter(adapter);
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return handler;
	}

}
