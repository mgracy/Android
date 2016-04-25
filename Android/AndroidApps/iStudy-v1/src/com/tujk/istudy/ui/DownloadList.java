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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.DownloadListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.DownloadBook;

/**
 * title  : DownloadList.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class DownloadList extends BaseActivity {

	List<DownloadBook> downloadList;
	RadioGroup downloadRadioGroup;
	ListView downloadedListview;
	ListView downloadingListview;
	
	
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
		setContentView(R.layout.download_list);
		initUI();
		showDownloadingListView();
	}
	
	public void searchContent(View v){
		//do search
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		searchButton = (Button)findViewById(R.id.download_search_button);
		searchKeyword = (EditText)findViewById(R.id.download_search_textview);
		searchContent = (Button)findViewById(R.id.download_search_content);
		
		downloadRadioGroup = (RadioGroup)findViewById(R.id.download_radio_group);
		downloadRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.downloaded_radio:
					showDoloadedListView();
					break;
				case R.id.downloading_radio:
					showDownloadingListView();
					break;
				default:
					showDownloadingListView();
					break;
				}
			}
		});
		downloadedListview = (ListView)findViewById(R.id.downloaded_listview);
		downloadingListview = (ListView)findViewById(R.id.downloading_listview);;
	}
	
	/**
	 * 
	 */
	private void showDoloadedListView() {
		// TODO Auto-generated method stub
		DownloadListAdapter.isDownloaded = true;
		downloadList = DemoData.getDownLoadList();
		downloadedListview.setVisibility(View.VISIBLE);
		downloadingListview.setVisibility(View.GONE);
		DownloadListAdapter adapter = new DownloadListAdapter(this, downloadList);
		downloadedListview.setAdapter(adapter);
	}

	/**
	 * 
	 */
	private void showDownloadingListView() {
		// TODO Auto-generated method stub
		DownloadListAdapter.isDownloaded = false;
		downloadList = DemoData.getDownLoadList();
		downloadingListview.setVisibility(View.VISIBLE);
		downloadedListview.setVisibility(View.GONE);
		DownloadListAdapter adapter = new DownloadListAdapter(this, downloadList);
		downloadingListview.setAdapter(adapter);
	}

	public void editDownload(View v){
		MainMenu.gotoEditDownload = true;
		openNewActivity(MainMenu.class);
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
