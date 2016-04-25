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
import android.widget.Toast;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.DownloadListAdapter;
import com.tujk.istudy.adapter.EditDownloadListAdapter;
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
public class EditDownloadList extends BaseActivity {

	List<DownloadBook> downloadList;
	ListView deleteDownloadListview;
	Button downloadedButton;
	Button downloadingButton;
	Button deleteButton;
	
	
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
		setContentView(R.layout.edit_download_list);
		initUI();
		showDownloadListView();
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
		
		deleteDownloadListview = (ListView)findViewById(R.id.edit_download_listview);
		downloadedButton = (Button)findViewById(R.id.edit_downloaded_button);
		downloadingButton = (Button)findViewById(R.id.edit_downloading_button);
		deleteButton = (Button)findViewById(R.id.edit_download_delete_button);
	}
	
	/**
	 * 
	 */
	private void showDownloadListView() {
		// TODO Auto-generated method stub
		downloadList = DemoData.getDownLoadList();
		EditDownloadListAdapter adapter = new EditDownloadListAdapter(this, downloadList);
		deleteDownloadListview.setAdapter(adapter);
	}
	
	public void downloaded(View v){
		DownloadListAdapter.isDownloaded = true;
		MainMenu.gotoDownload = true;
		openNewActivity(MainMenu.class);
	}
	
	public void downloading(View v){
		DownloadListAdapter.isDownloaded = false;
		MainMenu.gotoDownload = true;
		openNewActivity(MainMenu.class);
	}

	public void delete(View v){
		Toast.makeText(this, R.string.delete_success, Toast.LENGTH_SHORT).show();
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
