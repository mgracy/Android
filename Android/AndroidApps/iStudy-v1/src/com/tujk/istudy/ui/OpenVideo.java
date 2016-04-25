package com.tujk.istudy.ui;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.db.BookNote;
import com.tujk.istudy.utils.Constants;

/**
 * title  : VideoActivity.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-4-26
 */
public class OpenVideo extends BaseActivity implements OnErrorListener,
		OnCompletionListener {

	String bookId;
	int playPoint = 0;
	boolean showHeader = false;
	
	private VideoView videoView;
	private Uri uri;
	private int positionWhenPaused = -1;

	private MediaController mediaController;
	private RelativeLayout headerLayout;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case -1:
				headerLayout.setVisibility(View.GONE);
				showHeader = false;
				break;
			case 1:
				headerLayout.setVisibility(View.VISIBLE);
				showHeader = true;
				break;
			default:
				break;
			}
		};
	};
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bookId = getIntent().getExtras().getString("bookId");
		playPoint = getIntent().getExtras().getInt("numberOfPage",0);
		setContentView(R.layout.movie_viewer);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		init();
		new WaitingThread(true).start();
	}

	private void init() {
		videoView = (VideoView) findViewById(R.id.movie_container);
		headerLayout = (RelativeLayout)findViewById(R.id.movie_container_header);
		uri = Uri.parse(Constants.SYSTEM_DIR + "/video.mp4");
		mediaController = new MediaController(this);
		videoView.setMediaController(mediaController);
		videoView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				headerLayout.setVisibility(View.VISIBLE);
				new WaitingThread().start();
				return false;
			}
		});
	}

	public void onStart() {
		// Play Video
		videoView.setVideoURI(uri);
		if(playPoint!=0){
			videoView.seekTo(playPoint);
		}
		videoView.start();
		super.onStart();
	}

	public void onPause() {
		// Stop video when the activity is pause.
		positionWhenPaused = videoView.getCurrentPosition();
		videoView.stopPlayback();

		super.onPause();
	}

	public void onResume() {
		// Resume video player
		if (positionWhenPaused >= 0) {
			videoView.seekTo(positionWhenPaused);
			positionWhenPaused = -1;
		}

		super.onResume();
	}
	
	public void addBookMark(View v){
		BookNote bookNote = new BookNote(this).open();
		bookNote.addBookNote(bookId, "", "", videoView.getCurrentPosition(), 1);
		Toast.makeText(this, R.string.bookmark_success, Toast.LENGTH_SHORT).show();
		bookNote.close();
	}
	
	public void addNote(View v){
		final Dialog d = new Dialog(this);
		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	d.setContentView(R.layout.add_book_note_2);
    	Button cancel = (Button)d.findViewById(R.id.add_book_note_cancel);
    	cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				d.dismiss();
			}
		});
    	Button save = (Button)d.findViewById(R.id.add_book_note_save);
    	final EditText bookNoteEditText = (EditText)d.findViewById(R.id.add_book_note_edittext);
    	save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				d.dismiss();
				String note = bookNoteEditText.getText().toString();
				if(note==null || "".equals(note)){
					Toast.makeText(OpenVideo.this, R.string.enter_note_text, Toast.LENGTH_SHORT).show();
				}else{
					BookNote bookNote = new BookNote(OpenVideo.this).open();
					bookNote.addBookNote(bookId, "", note, videoView.getCurrentPosition(), 2);
					Toast.makeText(OpenVideo.this, R.string.add_book_note_success, Toast.LENGTH_SHORT).show();
					bookNote.close();
				}
				
			}
		});
    	
    	d.show();
	}

	@Override
	protected Handler getHandler() {
		return handler;
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		this.finish();
	}

	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		return false;
	}
	
	class WaitingThread extends Thread{
		boolean first = false;
		public WaitingThread(){}
		public WaitingThread(boolean first){
			this.first = first;
		}
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				if(first){
					Thread.sleep(1500);
					sendMessage(1);
				}
				Thread.sleep(3000);
				sendMessage(-1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
