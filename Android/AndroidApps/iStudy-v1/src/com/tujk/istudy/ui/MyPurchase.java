/**
 * 
 */
package com.tujk.istudy.ui;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.MyPurchaseListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.Purchase;

/**
 * title  : MyPurchase.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class MyPurchase extends BaseActivity {
	
	List<Purchase> myPurchase;
	ListView myPurchaseListView ;
	
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
		setContentView(R.layout.my_purchase);
		initUI();
		showMyPurchaseListView();
	};
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		searchButton = (Button)findViewById(R.id.my_purchase_search_button);
		searchContent = (Button)findViewById(R.id.my_purchase_search_content);
		searchKeyword = (EditText)findViewById(R.id.my_purchase_search_textview);
		
		myPurchaseListView = (ListView)findViewById(R.id.my_purchase_listview);
	}
	
	public void searchContent(View v){
		//do search
	}
	
	private void showMyPurchaseListView(){
		myPurchase = DemoData.getMyPurchae();
		MyPurchaseListAdapter adapter = new MyPurchaseListAdapter(this,myPurchase);
		myPurchaseListView.setAdapter(adapter);
	}
	public void operation(View v){
		new AlertDialog.Builder(this).setItems(R.array.purchase_operation, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(which == 0){
//					Toast.makeText(MyPurchase.this, R.string.purchae_success, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					intent.setClass(MyPurchase.this, OrderDetail.class);
					intent.putExtra("xxx", "xxx");
					startActivity(intent);
				}else if(which == 1){
					Toast.makeText(MyPurchase.this, R.string.delete_success, Toast.LENGTH_SHORT).show();
				}
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
