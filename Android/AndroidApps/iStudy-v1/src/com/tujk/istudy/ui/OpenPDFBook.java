package com.tujk.istudy.ui;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tujk.android.lib.pdf.PagesView;
import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.db.BookNote;
import com.tujk.istudy.pdf.Actions;
import com.tujk.istudy.pdf.Bookmark;
import com.tujk.istudy.pdf.Options;
import com.tujk.istudy.pdf.PDFPagesProvider;
import com.tujk.istudy.pdf.Recent;
import com.tujk.istudy.utils.Constants;

import cx.hell.android.lib.pdf.PDF;

/**
 * title  : PDFBookActivity.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-4-26
 */
public class OpenPDFBook extends BaseActivity implements OnClickListener{//implements SensorEventListener {
	
	private final static String TAG = "cx.hell.android.pdfview";
	
	private final static int[] zoomAnimations = {
		R.anim.zoom_disappear, R.anim.zoom_almost_disappear, R.anim.zoom
	};
	
	private final static int[] pageNumberAnimations = {
		R.anim.page_disappear, R.anim.page_almost_disappear, R.anim.page, 
		R.anim.page_show_always
	};
	
	private PDF pdf = null;
	private PagesView pagesView = null;

	private PDFPagesProvider pdfPagesProvider = null;
	private Actions actions = null;
	
	private Handler zoomHandler = null;
	private Handler pageHandler = null;
	private Runnable zoomRunnable = null;
	private Runnable pageRunnable = null;

	private EditText pageNumberInputField = null;
	
	
	private RelativeLayout activityLayout = null;
	private boolean eink = false;	

	// currently opened file path
	private String filePath = "/";
	
	// zoom buttons, layout and fade animation
	private ImageButton zoomDownButton;
	private ImageButton zoomWidthButton;
	private ImageButton zoomUpButton;
	private Animation zoomAnim;
	private LinearLayout zoomLayout;

	// page number display
	private TextView pageNumberTextView;
	private Animation pageNumberAnim;
	
	private RelativeLayout headerRelativeLayout;
	private Button backButton;
	private Button signButton;
	private Button noteButton;
	private Button homeButton;
	private Button gotoPageButton;
	
	private int box = 2;

	public boolean showZoomOnScroll = false;
	
	private int fadeStartOffset = 7000; 
	
	private int colorMode = Options.COLOR_MODE_NORMAL;

	private static final int ZOOM_COLOR_NORMAL = 0;
	private static final int ZOOM_COLOR_RED = 1;
	private static final int ZOOM_COLOR_GREEN = 2;
	private static final int[] zoomUpId = {
		R.drawable.btn_zoom_up, R.drawable.red_btn_zoom_up, R.drawable.green_btn_zoom_up
	};
	private static final int[] zoomDownId = {
		R.drawable.btn_zoom_down, R.drawable.red_btn_zoom_down, R.drawable.green_btn_zoom_down		
	};
	private static final int[] zoomWidthId = {
		R.drawable.btn_zoom_width, R.drawable.red_btn_zoom_width, R.drawable.green_btn_zoom_width		
	};

	private boolean history = false;
	
	String bookId = "";
	int numberOfPage = 0;
	
	/**
     * Called when the activity is first created.
     * initialize dialog fast, then move file loading to other thread
     * add progress bar for file load
     * add progress icon for file rendering
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bookId = getIntent().getExtras().getString("bookId");
        numberOfPage = getIntent().getExtras().getInt("numberOfPage",0);
        
        super.onCreate(savedInstanceState);
        
		Options.setOrientation(this);
		SharedPreferences options = PreferenceManager.getDefaultSharedPreferences(this);

		this.box = Integer.parseInt(options.getString(Options.PREF_BOX, "2"));
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // Get display metrics
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
        // use a relative layout to stack the views
        activityLayout = new RelativeLayout(this);
        
        // the PDF view
        this.pagesView = new PagesView(this);
        activityLayout.addView(pagesView);
        
        startPDF(options);
        if (!this.pdf.isValid()) {
        	finish();
        }
        
        addPageHeader();
        this.pageNumberTextView = new TextView(this);
        this.pageNumberTextView.setTextSize(8f*metrics.density);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, 
        		RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp.addRule(RelativeLayout.BELOW, headerRelativeLayout.getId());
        activityLayout.addView(this.pageNumberTextView, lp);
        
		// display this
        this.setContentView(activityLayout);
        
        
        // send keyboard events to this view
        pagesView.setFocusable(true);
        pagesView.setFocusableInTouchMode(true);

        this.zoomHandler = new Handler();
        this.pageHandler = new Handler();
        this.zoomRunnable = new Runnable() {
        	public void run() {
        		fadeZoom();
        	}
        };
        this.pageRunnable = new Runnable() {
        	public void run() {
        		fadePage();
        	}
        };
    }

    
    private void addPageHeader(){
    	headerRelativeLayout  = new RelativeLayout(this);
    	headerRelativeLayout.setId(10001);
    	headerRelativeLayout.setBackgroundResource(R.drawable.nav11);
    	
    	backButton = new Button(this);
    	backButton.setId(10009);
    	backButton.setOnClickListener(this);
    	backButton.setBackgroundResource(R.drawable.left_arrow);
    	RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
         		RelativeLayout.LayoutParams.WRAP_CONTENT, 
         		RelativeLayout.LayoutParams.WRAP_CONTENT);
    	lp.leftMargin = 5;
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        headerRelativeLayout.addView(backButton,lp);
    	
        LinearLayout linearLayout = new LinearLayout(this);
        lp = new RelativeLayout.LayoutParams(
         		RelativeLayout.LayoutParams.WRAP_CONTENT, 
         		RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        headerRelativeLayout.addView(linearLayout,lp);
        
        noteButton = new Button(this);
        noteButton.setOnClickListener(this);
        noteButton.setId(10002);
        noteButton.setBackgroundResource(R.drawable.notes);
        lp = new RelativeLayout.LayoutParams(
         		RelativeLayout.LayoutParams.WRAP_CONTENT, 
         		RelativeLayout.LayoutParams.WRAP_CONTENT);
    	lp.rightMargin = 5;
    	linearLayout.addView(noteButton,lp);
        
        signButton = new Button(this);
        signButton.setOnClickListener(this);
        signButton.setBackgroundResource(R.drawable.bookmark);
        lp = new RelativeLayout.LayoutParams(
         		RelativeLayout.LayoutParams.WRAP_CONTENT, 
         		RelativeLayout.LayoutParams.WRAP_CONTENT);
    	lp.rightMargin = 5;
    	linearLayout.addView(signButton,lp);
        
        homeButton = new Button(this);
        homeButton.setOnClickListener(this);
        homeButton.setId(10002);
        homeButton.setBackgroundResource(R.drawable.home11);
        lp = new RelativeLayout.LayoutParams(
          		RelativeLayout.LayoutParams.WRAP_CONTENT, 
          		RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = 5;
        linearLayout.addView(homeButton,lp);
        
        gotoPageButton = new Button(this);
        gotoPageButton.setOnClickListener(this);
        gotoPageButton.setBackgroundResource(R.drawable.operation11);
        lp = new RelativeLayout.LayoutParams(
          		RelativeLayout.LayoutParams.WRAP_CONTENT, 
          		RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = 5;
        linearLayout.addView(gotoPageButton,lp);
        
        lp = new RelativeLayout.LayoutParams(
          		RelativeLayout.LayoutParams.MATCH_PARENT, 
          		RelativeLayout.LayoutParams.WRAP_CONTENT);
        activityLayout.addView(this.headerRelativeLayout,lp);
         
    }
    
	/** 
	 * Save the current page before exiting
	 */
	@Override
	protected void onPause() {
		super.onPause();
//		saveLastPage();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		SharedPreferences options = PreferenceManager.getDefaultSharedPreferences(this);

		history  = options.getBoolean(Options.PREF_HISTORY, true);
		boolean eink = options.getBoolean(Options.PREF_EINK, false);
		this.pagesView.setEink(eink);
		if (eink)
    		this.setTheme(android.R.style.Theme_Light);
		this.pagesView.setNook2(options.getBoolean(Options.PREF_NOOK2, false));
		
		if (options.getBoolean(Options.PREF_KEEP_ON, false))
			this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		else
			this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
		actions = new Actions(options);
		this.pagesView.setActions(actions);

		setZoomLayout(options);
		
		this.pagesView.setZoomLayout(zoomLayout);
		
		this.showZoomOnScroll = options.getBoolean(Options.PREF_SHOW_ZOOM_ON_SCROLL, false);
		this.pagesView.setSideMargins(
				Integer.parseInt(options.getString(Options.PREF_SIDE_MARGINS, "0")));
		this.pagesView.setTopMargin(
				Integer.parseInt(options.getString(Options.PREF_TOP_MARGIN, "0")));

		this.pagesView.setDoubleTap(Integer.parseInt(options.getString(Options.PREF_DOUBLE_TAP, 
				""+Options.DOUBLE_TAP_ZOOM_IN_OUT)));
		
		int newBox = Integer.parseInt(options.getString(Options.PREF_BOX, "2"));
		if (this.box != newBox) {
//			saveLastPage();
			this.box = newBox;
	        startPDF(options);
//	        this.pagesView.goToBookmark();
		}

        this.colorMode = Options.getColorMode(options);
        this.eink = options.getBoolean(Options.PREF_EINK, false);
        this.pageNumberTextView.setBackgroundColor(Options.getBackColor(colorMode));
        this.pageNumberTextView.setTextColor(Options.getForeColor(colorMode));
        this.pdfPagesProvider.setGray(Options.isGray(this.colorMode));
        this.pdfPagesProvider.setExtraCache(1024*1024*Options.getIntFromString(options, Options.PREF_EXTRA_CACHE, 0));
        this.pdfPagesProvider.setOmitImages(options.getBoolean(Options.PREF_OMIT_IMAGES, false));
		this.pagesView.setColorMode(this.colorMode);		
		
		this.pdfPagesProvider.setRenderAhead(options.getBoolean(Options.PREF_RENDER_AHEAD, true));
		this.pagesView.setVerticalScrollLock(options.getBoolean(Options.PREF_VERTICAL_SCROLL_LOCK, false));
		this.pagesView.invalidate();
		int zoomAnimNumber = Integer.parseInt(options.getString(Options.PREF_ZOOM_ANIMATION, "2"));
		
		if (zoomAnimNumber == Options.ZOOM_BUTTONS_DISABLED)
			zoomAnim = null;
		else 
			zoomAnim = AnimationUtils.loadAnimation(this,
				zoomAnimations[zoomAnimNumber]);		
		int pageNumberAnimNumber = Integer.parseInt(options.getString(Options.PREF_PAGE_ANIMATION, "3"));
		
		if (pageNumberAnimNumber == Options.PAGE_NUMBER_DISABLED)
			pageNumberAnim = null;
		else 
			pageNumberAnim = AnimationUtils.loadAnimation(this,
				pageNumberAnimations[pageNumberAnimNumber]);		

		fadeStartOffset = 1000 * Integer.parseInt(options.getString(Options.PREF_FADE_SPEED, "7"));
		
		if (options.getBoolean(Options.PREF_FULLSCREEN, false))
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		else
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.pageNumberTextView.setVisibility(pageNumberAnim == null ? View.GONE : View.VISIBLE);
		
		this.zoomLayout.setVisibility(zoomAnim == null ? View.GONE : View.VISIBLE);
        
        showAnimated(true);
	}

    /**
     * Set handlers on zoom level buttons
     */
    private void setZoomButtonHandlers() {
    	this.zoomDownButton.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				pagesView.doAction(actions.getAction(Actions.LONG_ZOOM_IN));
				return true;
			}
    	});
    	this.zoomDownButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pagesView.doAction(actions.getAction(Actions.ZOOM_IN));
			}
    	});
    	this.zoomWidthButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pagesView.zoomWidth();
			}
    	});
    	this.zoomWidthButton.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				pagesView.zoomFit();
				return true;
			}
    	});
    	this.zoomUpButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pagesView.doAction(actions.getAction(Actions.ZOOM_OUT));
			}
    	});
    	this.zoomUpButton.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				pagesView.doAction(actions.getAction(Actions.LONG_ZOOM_OUT));
				return true;
			}
    	});
    }

    private void startPDF(SharedPreferences options) {
	    this.pdf = this.getPDF();
	    if (!this.pdf.isValid()) {
	    	Log.v(TAG, "Invalid PDF");
	    	if (this.pdf.isInvalidPassword()) {
	    		Toast.makeText(this, "This file needs a password", Toast.LENGTH_LONG).show();
	    	}
	    	else {
	    		Toast.makeText(this, "Invalid PDF file", Toast.LENGTH_LONG).show();
	    	}
	    	return;
	    }
	    this.colorMode = Options.getColorMode(options);
	    this.pdfPagesProvider = new PDFPagesProvider(this, pdf,        		
	    		Options.isGray(this.colorMode), 
	    		options.getBoolean(Options.PREF_OMIT_IMAGES, false),
	    		options.getBoolean(Options.PREF_RENDER_AHEAD, true));
	    pagesView.setPagesProvider(pdfPagesProvider);
	    Bookmark b = new Bookmark(this).open();
	    pagesView.setStartBookmark(b, filePath,numberOfPage);
	    b.close();
    }

    /**
     * Return PDF instance wrapping file referenced by Intent.
     * Currently reads all bytes to memory, in future local files
     * should be passed to native code and remote ones should
     * be downloaded to local tmp dir.
     * @return PDF instance
     */
    private PDF getPDF() {
//        final Intent intent = getIntent();
//		Uri uri = intent.getData(); 
    	Uri uri = Uri.fromFile(new File(Constants.SYSTEM_DIR + "/test.pdf"));
		filePath = uri.getPath();
		Log.i("TAG--TAG", filePath);
		if (uri.getScheme().equals("file")) {
			if (history) {
				Recent recent = new Recent(this);
				recent.add(0, filePath);
				recent.commit();
			}
			return new PDF(new File(filePath), this.box);
    	} else if (uri.getScheme().equals("content")) {
    		ContentResolver cr = this.getContentResolver();
    		FileDescriptor fileDescriptor;
			try {
				fileDescriptor = cr.openFileDescriptor(uri, "r").getFileDescriptor();
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e); 
			}
    		return new PDF(fileDescriptor, this.box);
    	} else {
    		throw new RuntimeException("don't know how to get filename from " + uri);
    	}
    }

	/**
     * Intercept touch events to handle the zoom buttons animation
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	int action = event.getAction();
    	if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
	    	showPageNumber(true);
    		if (showZoomOnScroll) {
		    	showZoom();
	    	}
    	}
		return super.dispatchTouchEvent(event);    	
    };
    
    public boolean dispatchKeyEvent(KeyEvent event) {
    	int action = event.getAction();
    	if (action == KeyEvent.ACTION_UP || action == KeyEvent.ACTION_DOWN) {
    		if (!eink)
    			showAnimated(false);
    	}
		return super.dispatchKeyEvent(event);    	
    };
    
    public void showZoom() {
    	if (zoomAnim == null) {
    		zoomLayout.setVisibility(View.GONE);
    		return;
    	}
    	
    	zoomLayout.clearAnimation();
    	zoomLayout.setVisibility(View.VISIBLE);
    	zoomHandler.removeCallbacks(zoomRunnable);
    	zoomHandler.postDelayed(zoomRunnable, fadeStartOffset);
    }
    
    private void fadeZoom() {
    	if (eink || zoomAnim == null) {
    		zoomLayout.setVisibility(View.GONE);
    	}
    	else {
    		zoomAnim.setStartOffset(0);
    		zoomAnim.setFillAfter(true);
    		zoomLayout.startAnimation(zoomAnim);
    	}
    }
    
    public void showPageNumber(boolean force) {
    	if (pageNumberAnim == null) {
    		pageNumberTextView.setVisibility(View.GONE);
    		return;
    	}
    	
    	pageNumberTextView.setVisibility(View.VISIBLE);
    	String newText = ""+(this.pagesView.getCurrentPage()+1)+"/"+
				this.pdfPagesProvider.getPageCount();
    	
    	if (!force && newText.equals(pageNumberTextView.getText()))
    		return;
    	
		pageNumberTextView.setText(newText);
    	pageNumberTextView.clearAnimation();

    	pageHandler.removeCallbacks(pageRunnable);
    	pageHandler.postDelayed(pageRunnable, fadeStartOffset);
    }
    
    private void fadePage() {
    	if (eink || pageNumberAnim == null) {
    		pageNumberTextView.setVisibility(View.GONE);
    	}
    	else {
    		pageNumberAnim.setStartOffset(0);
    		pageNumberAnim.setFillAfter(true);
    		pageNumberTextView.startAnimation(pageNumberAnim);
    	}
    }    
    
    /**
     * Show zoom buttons and page number
     */
    public void showAnimated(boolean alsoZoom) {
    	if (alsoZoom)
    		showZoom();
    	showPageNumber(true);
    }
    
    /**
     * Show error message to user.
     * @param message message to show
     */
    private void errorMessage(String message) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	AlertDialog dialog = builder.setMessage(message).setTitle(getResources().getString(R.string.error)).create();
    	dialog.show();
    }
    
    /**
     * Called from menu when user want to go to specific page.
     */
    private void showGotoPageDialog() {
    	final Dialog d = new Dialog(this);
    	d.setTitle(R.string.goto_page_dialog_title);
    	LinearLayout contents = new LinearLayout(this);
    	contents.setOrientation(LinearLayout.VERTICAL);
    	TextView label = new TextView(this);
    	final int pagecount = this.pdfPagesProvider.getPageCount();
    	label.setText("从  " + 1 + " 到 " + pagecount +"页");
    	this.pageNumberInputField = new EditText(this);
    	this.pageNumberInputField.setInputType(InputType.TYPE_CLASS_NUMBER);
    	this.pageNumberInputField.setText("" + (this.pagesView.getCurrentPage() + 1));
    	Button goButton = new Button(this);
    	goButton.setBackgroundResource(R.drawable.segment51);
    	goButton.setText(R.string.goto_page_go_button);
    	goButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int pageNumber = -1;
				try {
					pageNumber = Integer.parseInt(OpenPDFBook.this.pageNumberInputField.getText().toString())-1;
				} catch (NumberFormatException e) {
					/* ignore */
					OpenPDFBook.this.errorMessage(getResources().getString(R.string.invalid_page_number));
				}
				d.dismiss();
				if (pageNumber >= 0 && pageNumber < pagecount) {
					OpenPDFBook.this.gotoPage(pageNumber);

				} else {
					OpenPDFBook.this.errorMessage(getResources().getString(R.string.invalid_page_number));
				}
			}
    	});
    	Button page1Button = new Button(this);
    	page1Button.setBackgroundResource(R.drawable.segment51);
    	page1Button.setText(getResources().getString(R.string.first_page));
    	page1Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				d.dismiss();
				OpenPDFBook.this.gotoPage(0);
			}
    	});
    	Button lastPageButton = new Button(this);
    	lastPageButton.setBackgroundResource(R.drawable.segment51);
    	lastPageButton.setText(getResources().getString(R.string.last_page));
    	lastPageButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				d.dismiss();
				OpenPDFBook.this.gotoPage(pagecount-1);
			}
    	});
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	params.leftMargin = 50;
    	params.rightMargin = 50;
    	params.bottomMargin = 2;
    	params.topMargin = 2;
    	contents.addView(label, params);
    	contents.addView(pageNumberInputField, params);
    	contents.addView(goButton, params);
    	contents.addView(page1Button, params);
    	contents.addView(lastPageButton, params);
    	d.setContentView(contents);
    	d.show();
    }
    
    private void showNoteDialog(){
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
    	final EditText bookNoteEditText = (EditText)d.findViewById(R.id.add_book_note_edittext);
    	Button save = (Button)d.findViewById(R.id.add_book_note_save);
    	save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				d.dismiss();
				String note = bookNoteEditText.getText().toString();
				if(note==null || "".equals(note)){
					Toast.makeText(OpenPDFBook.this, R.string.enter_note_text, Toast.LENGTH_SHORT).show();
				}else{
					BookNote bookNote = new BookNote(OpenPDFBook.this).open();
					bookNote.addBookNote(bookId, "", note, pagesView.getCurrentPage(),2);
					Toast.makeText(OpenPDFBook.this, R.string.add_book_note_success, Toast.LENGTH_SHORT).show();
					bookNote.close();
				}
			}
		});
    	
    	d.show();
    }
    
    private void gotoPage(int page) {
    	Log.i(TAG, "rewind to page " + page);
    	if (this.pagesView != null) {
    		this.pagesView.scrollToPage(page);
            showAnimated(true);
    	}
    }
    
   /**
     * Save the last page in the bookmarks
     */
//    private void saveLastPage() {
//    	BookmarkEntry entry = this.pagesView.toBookmarkEntry();
//        Bookmark b = new Bookmark(this.getApplicationContext()).open();
//        b.setLast(filePath, entry);
//        b.close();
//        Log.i(TAG, "last page saved for "+filePath);    
//    }
    
        
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      Log.i(TAG, "onConfigurationChanged(" + newConfig + ")");
    }
    
    private void setZoomLayout(SharedPreferences options) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
        int colorMode = Options.getColorMode(options);
        int mode = ZOOM_COLOR_NORMAL;
        
        if (colorMode == Options.COLOR_MODE_GREEN_ON_BLACK) {
        	mode = ZOOM_COLOR_GREEN;
        }
        else if (colorMode == Options.COLOR_MODE_RED_ON_BLACK) {
        	mode = ZOOM_COLOR_RED;
        }

        // the zoom buttons
    	if (zoomLayout != null) {
    		activityLayout.removeView(zoomLayout);
    	}
    	
        zoomLayout = new LinearLayout(this);
        zoomLayout.setOrientation(LinearLayout.HORIZONTAL);
		zoomDownButton = new ImageButton(this);
		zoomDownButton.setImageDrawable(getResources().getDrawable(zoomDownId[mode]));
		zoomDownButton.setBackgroundColor(Color.TRANSPARENT);
		zoomLayout.addView(zoomDownButton, (int)(80 * metrics.density), (int)(50 * metrics.density));
		zoomWidthButton = new ImageButton(this);
		zoomWidthButton.setImageDrawable(getResources().getDrawable(zoomWidthId[mode]));
		zoomWidthButton.setBackgroundColor(Color.TRANSPARENT);
		zoomLayout.addView(zoomWidthButton, (int)(58 * metrics.density), (int)(50 * metrics.density));
		zoomUpButton = new ImageButton(this);		
		zoomUpButton.setImageDrawable(getResources().getDrawable(zoomUpId[mode]));
		zoomUpButton.setBackgroundColor(Color.TRANSPARENT);
		zoomLayout.addView(zoomUpButton, (int)(80 * metrics.density), (int)(50 * metrics.density));
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, 
        		RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        setZoomButtonHandlers();
		activityLayout.addView(zoomLayout,lp);
    }
    

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		if(v.equals(backButton) ){
			this.finish();
			Log.i("mainmenu", "backButton click");
		}else if(v.equals(signButton)){
			BookNote bookNote = new BookNote(this).open();
			bookNote.addBookNote(bookId, "", "", this.pagesView.getCurrentPage(),1);
			Toast.makeText(this, R.string.bookmark_success, Toast.LENGTH_SHORT).show();
			bookNote.close();
		}else if(v.equals(noteButton)){
			showNoteDialog();
		}else if(v.equals(homeButton)){
			MainMenu.gotoHome = true;
			openNewActivity(MainMenu.class);
		}else if(v.equals(gotoPageButton)){
			showGotoPageDialog();
		}
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
