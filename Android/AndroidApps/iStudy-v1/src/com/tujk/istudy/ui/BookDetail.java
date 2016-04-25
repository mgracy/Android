/**
 * 
 */
package com.tujk.istudy.ui;

import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.AbstractWeibo;
import cn.sharesdk.framework.WeiboActionListener;
import cn.sharesdk.sina.weibo.SinaWeibo;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.BookDetailContentListAdapter;
import com.tujk.istudy.adapter.BookNoteListAdapter;
import com.tujk.istudy.adapter.MainBookListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.db.BookNote;
import com.tujk.istudy.vo.Book;
import com.tujk.istudy.vo.BookContent;
import com.tujk.istudy.vo.BookMarkValue;

/**
 * title  : BookDetail.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class BookDetail extends BaseActivity implements WeiboActionListener{
	
	String bookId = "";
	
	ImageView poster;
	TextView title;
	TextView desc;
	TextView author;
	TextView progress;
	TextView rating;
	ProgressBar progressBar;
	
//	ImageButton play;
//	ImageButton download;
//	ImageButton share;
//	ImageButton favolite;
//	ImageButton plan;
//	ImageButton friends;
	
	RadioGroup radioGroup;
	RadioButton content;
	RadioButton sign;
	RadioButton note;
	RadioButton recommend;
	
	List<BookContent> contentList;
	List<Book> recommendList;
	List<BookMarkValue> bookMarkerList;
	ListView contentListView;
	ListView signListView;
	ListView noteListView;
	ListView recommendListView;
	
	BookDetailContentListAdapter contentAdapter;
	MainBookListAdapter recommendAdapter;
	BookNoteListAdapter noteAdapter;
	BookNoteListAdapter bookMarkerAdapter;
	
	
	Handler hanlder = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case -1:
				Toast.makeText(BookDetail.this, "weibo share error.", Toast.LENGTH_SHORT).show();
			case 0:
				Toast.makeText(BookDetail.this, "weibo share cancel.", Toast.LENGTH_SHORT).show();
			case 1:
				Toast.makeText(BookDetail.this, "weibo share success.", Toast.LENGTH_SHORT).show();
			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_detail);
		bookId = getIntent().getExtras().getString("bookId");
		initUI();
		showBookDetail();
		showBookContentListView();
	}
	
	/**
	 * 
	 */
	Book book;
	private void showBookDetail() {
		// TODO Auto-generated method stub
		book = DemoData.getBookById(bookId);
		poster.setBackgroundDrawable(Drawable.createFromPath(book.getPoster()));
		title.setText(book.getTitle());
		desc.setText(book.getDesc());
		author.setText(book.getAuthor());
		progress.setText(book.getProgress()+"%");
		rating.setText(book.getRating()+"%");
		progressBar.setProgress(book.getRating());
	}

	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		poster			 	= (ImageView)findViewById(R.id.book_detail_image);
		title	 			= (TextView)findViewById(R.id.book_detail_title);
		desc  				= (TextView)findViewById(R.id.book_detail_desc);
		author  			= (TextView)findViewById(R.id.book_detail_author);
		progress  			= (TextView)findViewById(R.id.book_detail_progress);
		rating  			= (TextView)findViewById(R.id.book_detail_rating);
		content 			= (RadioButton)findViewById(R.id.book_detail_content_radio);
		sign 				= (RadioButton)findViewById(R.id.book_detail_sign_radio);
		note 				= (RadioButton)findViewById(R.id.book_detail_note_radio);
		recommend 			= (RadioButton)findViewById(R.id.book_detail_recommend_radio);
		contentListView 	= (ListView)findViewById(R.id.book_detail_content_list);
		signListView 		= (ListView)findViewById(R.id.book_detail_sign_list);
		noteListView 		= (ListView)findViewById(R.id.book_detail_note_list);
		recommendListView 	= (ListView)findViewById(R.id.book_detail_recommend_list);
		progressBar			= (ProgressBar)findViewById(R.id.book_detail_progressbar);
		radioGroup			= (RadioGroup)findViewById(R.id.book_detail_radio_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.book_detail_content_radio:
					showBookContentListView();
					break;
				case R.id.book_detail_sign_radio:
					showBookSignListVIew();
					break;
				case R.id.book_detail_note_radio:
					showBookNoteListView();
					break;
				case R.id.book_detail_recommend_radio:
					showRecommendListView();		
					break;
				default:
					showBookContentListView();
					break;
				}
			}
		});
		
		searchButton 		= (Button)findViewById(R.id.detail_search_button);
		searchContent  		= (Button)findViewById(R.id.detail_search_content);
		searchKeyword		= (EditText)findViewById(R.id.detail_search_textview);
	}
	
	/**
	 * 
	 */
	protected void showRecommendListView() {
		// TODO Auto-generated method stub
		recommendList = DemoData.getMainListBooks();
		contentListView.setVisibility(View.GONE);
		signListView.setVisibility(View.GONE);
		noteListView.setVisibility(View.GONE);
		recommendListView.setVisibility(View.VISIBLE);
		
		recommendAdapter = new MainBookListAdapter(this, recommendList);
		recommendListView.setAdapter(recommendAdapter);
		recommendListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				bookId = recommendList.get(position).getId();
				showBookDetail();
			}
		});
	}
	
	/**
	 * 
	 */
	protected void showBookNoteListView() {
		// TODO Auto-generated method stub
		BookNote bookNote = new BookNote(this).open();
		bookMarkerList = bookNote.getBookmarks(bookId, 2);
		contentListView.setVisibility(View.GONE);
		signListView.setVisibility(View.GONE);
		noteListView.setVisibility(View.VISIBLE);
		recommendListView.setVisibility(View.GONE);
		
		noteAdapter = new BookNoteListAdapter(this, bookMarkerList);
		noteListView.setAdapter(noteAdapter);
		noteListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				BookMarkValue bookMark = bookMarkerList.get(position);
				Intent intent = new Intent();
				intent.setClass(BookDetail.this, AddBookNote.class);
				intent.putExtra("bookId", book.getId());
				intent.putExtra("numberOfPage", bookMark.getNumberOfPage());
				intent.putExtra("bookNote", bookMark.getComment());
				startActivity(intent);
			}
		});
		bookNote.close();
	}

	/**
	 * 
	 */
	protected void showBookSignListVIew() {
		// TODO Auto-generated method stub
		BookNote bookNote = new BookNote(this).open();
		bookMarkerList = bookNote.getBookmarks(bookId, 1);
		contentListView.setVisibility(View.GONE);
		signListView.setVisibility(View.VISIBLE);
		noteListView.setVisibility(View.GONE);
		recommendListView.setVisibility(View.GONE);
		
		bookMarkerAdapter = new BookNoteListAdapter(this, bookMarkerList);
		signListView.setAdapter(bookMarkerAdapter);
		bookNote.close();
	}

	/**
	 * 
	 */
	protected void showBookContentListView() {
		// TODO Auto-generated method stub
		contentList = DemoData.getBookContents();
		contentListView.setVisibility(View.VISIBLE);
		signListView.setVisibility(View.GONE);
		noteListView.setVisibility(View.GONE);
		recommendListView.setVisibility(View.GONE);
		
		contentAdapter = new BookDetailContentListAdapter(this, contentList);
		contentListView.setAdapter(contentAdapter);
		contentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				BookContent bookContent = contentList.get(position);
				Intent intent = new Intent();
				if(book.getType()==0){
					intent.setClass(BookDetail.this, OpenVideo.class);
				}else {
					intent.setClass(BookDetail.this, OpenPDFBook.class);
				}
				intent.putExtra("bookId", book.getId());
				intent.putExtra("numberOfPage", bookContent.getPage());
				BookDetail.this.startActivity(intent);
			}
		});
	}

	public void searchContent(View v){
		
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		BookNote bookNote = new BookNote(this).open();
		if(sign.isChecked()){
			bookMarkerList = bookNote.getBookmarks(bookId, 1);
			bookMarkerAdapter.notifyDataSetChanged();
		}else if(note.isChecked()){
			bookMarkerList = bookNote.getBookmarks(bookId, 2);
			noteAdapter.notifyDataSetChanged();
		}
		bookNote.close();
	}
	
	public void play(View v){
		Intent intent = new Intent();
		if(book.getType()==1){
			intent.setClass(this, OpenPDFBook.class);
		}else{
			intent.setClass(this, OpenVideo.class);
		}
		intent.putExtra("bookId", book.getId());
		startActivity(intent);
	}
	public void download(View v){
		Toast.makeText(this, R.string.downloading, Toast.LENGTH_SHORT).show();
	}
	public void plan(View v){
		Intent intent = new Intent();
		intent.setClass(this, AddBookPlan.class);
		intent.putExtra("bookId", book.getId());
		intent.putExtra("bookTitle", book.getTitle());
		startActivity(intent);
	}
	public void share(View v){
//		Dialog dialog = new Dialog(this);
//		dialog.setContentView(R.layout.share_dialog);
//		dialog.show();
		AbstractWeibo.initSDK(this);
		AbstractWeibo weibo = AbstractWeibo.getWeibo(this, SinaWeibo.NAME);
		String weiboId = weibo.getDb().getWeiboId();
		if(weiboId==null || "".equals(weiboId)){
			Log.i("mainmenu","not authorize");
			weibo.setWeiboActionListener(this);
			weibo.authorize();
		}else{
			new ShareThread().start();
		}
	}
	public void friends(View v){
		MainMenu.gotoHome = false;
		MainMenu.gotoFriends = true;
		openNewActivity(MainMenu.class);
	}
	public void favorite(View v){
		Toast.makeText(this, R.string.favorite_success, Toast.LENGTH_SHORT).show();
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return hanlder;
	}

	/* (non-Javadoc)
	 * @see cn.sharesdk.framework.WeiboActionListener#onCancel(cn.sharesdk.framework.AbstractWeibo, int)
	 */
	@Override
	public void onCancel(AbstractWeibo arg0, int arg1) {
		// TODO Auto-generated method stub
//		sendMessage(0);
	}

	/* (non-Javadoc)
	 * @see cn.sharesdk.framework.WeiboActionListener#onComplete(cn.sharesdk.framework.AbstractWeibo, int, java.util.HashMap)
	 */
	@Override
	public void onComplete(AbstractWeibo arg0, int arg1,
			HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
//		sendMessage(1);
	}

	/* (non-Javadoc)
	 * @see cn.sharesdk.framework.WeiboActionListener#onError(cn.sharesdk.framework.AbstractWeibo, int, java.lang.Throwable)
	 */
	@Override
	public void onError(AbstractWeibo arg0, int arg1, Throwable arg2) {
		// TODO Auto-generated method stub
//		sendMessage(-1);
	}

	class ShareThread extends Thread{
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			AbstractWeibo.initSDK(BookDetail.this);
			AbstractWeibo weibo = AbstractWeibo.getWeibo(BookDetail.this, SinaWeibo.NAME);
			weibo.setWeiboActionListener(BookDetail.this);
			SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
			sp.text = "iStudy Sina Interface Test.";
//			sp.imagePath = Constants.SYSTEM_DIR + "poster1.jpg";
			weibo.share(sp);
		}
	}
}
