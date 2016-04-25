/**
 * 
 */
package com.tujk.istudy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.Book;

/**
 * title  : AddBookPlan.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-29
 */
public class AddBookNote extends BaseActivity {

	int page;
	String bookId;
	String comtent;
	Book book;
	
	TextView titleTextView;
	EditText noteEditText;
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		page = getIntent().getExtras().getInt("numberOfPage");
		bookId = getIntent().getExtras().getString("bookId");
		comtent = getIntent().getExtras().getString("bookNote");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_book_note);
		initUI();
		
		showPage();
	}
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		titleTextView = (TextView)findViewById(R.id.book_note_title_text);
		noteEditText = (EditText)findViewById(R.id.book_note_text_edit);
		
	}
	
	private void showPage(){
		book = DemoData.getBookById(bookId);
		titleTextView.setText(book.getTitle()+getResources().getString(R.string.book_note));	
		noteEditText.setText(comtent);
	}
	
	public void save(View v){
		Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
	}
	
	public void play(View v){
		Intent intent = new Intent();
		if(book.getType()==0){
			intent.setClass(this, OpenVideo.class);
		}else {
			intent.setClass(this, OpenPDFBook.class);
		}
		intent.putExtra("bookId", book.getId());
		intent.putExtra("numberOfPage", page);
		startActivity(intent);
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
