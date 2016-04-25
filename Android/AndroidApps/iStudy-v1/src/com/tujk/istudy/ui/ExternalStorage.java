/**
 * 
 */
package com.tujk.istudy.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.CardListAdapter;
import com.tujk.istudy.vo.SdCardInfo;

/**
 * title  : ExternalStorage.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-29
 */
public class ExternalStorage extends BaseActivity {

	ListView cardListView;
	List<SdCardInfo> cardsInfo;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case -1:
				Toast.makeText(ExternalStorage.this, "外部存储卡不可用", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				showCardListView();
				break;
			default:
				break;
			}
		};
	};
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_storage);
		initUI();
		new SdCardCheckThread().start();
	}
	/**
	 * 
	 */
	protected void showCardListView() {
		// TODO Auto-generated method stub
		cardListView.setAdapter(new CardListAdapter(this, cardsInfo));
		
	}
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		cardListView = (ListView)findViewById(R.id.card_list_view);
		cardsInfo = new ArrayList<SdCardInfo>();
	}
	
	public void save(View v){
		Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
	}
	
	class SdCardCheckThread extends Thread{
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				sendMessage(-1);
			}else{
				File path = Environment.getExternalStorageDirectory();
				StatFs statFs = new StatFs(path.getPath());
				long blockSize = statFs.getBlockSize();
				long blockCount = statFs.getBlockCount();
				long availabeBlock = statFs.getAvailableBlocks();
				SdCardInfo sdCard = new SdCardInfo(path.getPath(), blockSize*blockCount/1024, blockSize*availabeBlock/1024);
				cardsInfo.add(sdCard);
				sendMessage(1);
			}
		}
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
