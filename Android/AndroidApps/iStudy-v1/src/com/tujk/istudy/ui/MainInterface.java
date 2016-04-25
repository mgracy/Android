/**
 * 
 */
package com.tujk.istudy.ui;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.MainBookGridAdapter;
import com.tujk.istudy.adapter.MainBookListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.Book;

/**
 * title  : MainInterface.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class MainInterface extends BaseActivity {
	
	Button list;
	
	RadioGroup mainBookRadioGroup;
	
	ListView mainBookGrid;
	ListView mainBookList;
	
	boolean isGridBookView = true;
	
	List<List<Book>> gridBookData;
	List<Book> listBookData ;
	MainBookGridAdapter gridAdapter;
	MainBookListAdapter listAdapter;
	
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_interface);
		initUI();
		
		showValidGridView();
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		mainBookGrid = (ListView)findViewById(R.id.main_book_scroll_grid);
		mainBookList = (ListView)findViewById(R.id.main_book_list);
		searchButton = (Button)findViewById(R.id.search);
		searchContent = (Button)findViewById(R.id.do_search);
		searchKeyword = (EditText)findViewById(R.id.searchText);
		list = (Button)findViewById(R.id.list);
		mainBookRadioGroup = (RadioGroup)findViewById(R.id.main_book_radio_group);
		mainBookRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.main_book_all:
					showAllGridView();
					break;
				case R.id.main_book_valid:
					showValidGridView();
					break;
				case R.id.main_book_invalid:
					showInvalidGridView();
					break;
				case R.id.main_book_recommend:
					showRecommendGridView();
					break;
				default:
					showValidGridView();
					break;
				}
			}
		});
		
	}

	/**
	 * 
	 */
	protected void showRecommendGridView() {
		// TODO Auto-generated method stub
		gridBookData = DemoData.getMainGridBooks();
		gridAdapter = new MainBookGridAdapter(this, gridBookData);
		mainBookGrid.setAdapter(gridAdapter);
	}

	/**
	 * 
	 */
	protected void showAllGridView() {
		// TODO Auto-generated method stub
		gridBookData = DemoData.getMainGridBooks();
		gridAdapter = new MainBookGridAdapter(this, gridBookData);
		mainBookGrid.setAdapter(gridAdapter);
	}

	/**
	 * 
	 */
	private void showValidGridView() {
		// TODO Auto-generated method stub
		gridBookData = DemoData.getMainGridBooks();
		gridAdapter = new MainBookGridAdapter(this, gridBookData);
		mainBookGrid.setAdapter(gridAdapter);
	}

	private void showInvalidGridView(){
		gridBookData = DemoData.getMainGridBooks();
		gridAdapter = new MainBookGridAdapter(this, gridBookData);
		mainBookGrid.setAdapter(gridAdapter);
	}
	
	/**
	 * @param id
	 */
	protected void gotoBookDetail(String id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra("bookId", id);
		intent.setClass(this, BookDetail.class);
		startActivity(intent);
	}

	private void showListView(){
		listBookData = DemoData.getMainListBooks();
		listAdapter = new MainBookListAdapter(this, listBookData);
		mainBookList.setAdapter(listAdapter);
		mainBookList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Book book = listBookData.get(position);
				gotoBookDetail(book.getId());
			}
		});
	}
	
	public void mainMenu(View v){
		MainMenu.slidingMenuView.clickMenu();
	}
	
	public void searchContent(View v){
		//do search
	}
	
	public void list(View v){
		if(isGridBookView){
			mainBookGrid.setVisibility(View.GONE);
			mainBookList.setVisibility(View.VISIBLE);
			showListView();
			list.setBackgroundResource(R.drawable.grid21);
			isGridBookView = false;
		}else{
			mainBookGrid.setVisibility(View.VISIBLE);
			mainBookList.setVisibility(View.GONE);
			showValidGridView();
			list.setBackgroundResource(R.drawable.list11);
			isGridBookView = true;
		}
	}
	
	public void operation(View v){
		new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.change_book_type)).setItems(R.array.main_category, new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Log.i("mainmenu", String.valueOf(which));
					dialog.cancel();
				}
			}).show(); 
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
