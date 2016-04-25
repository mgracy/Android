/**
 * 
 */
package com.tujk.istudy.ui;

import java.util.List;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.OrderDetailListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.Book;

/**
 * title  : MyOrder.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class OrderDetail extends BaseActivity {

	
	List<Book> orderBooksList;
	ListView orderBookListView;
	TextView orderNo;
	TextView price;
	
	String orderNumber;
	
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
		orderNumber = getIntent().getExtras().getString("orderNumber");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_detail);
		initUI();
		showOrderDetail();
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
		orderBookListView = (ListView)findViewById(R.id.order_detail_list_view);
		orderNo = (TextView)findViewById(R.id.order_detail_number);
		price = (TextView)findViewById(R.id.order_detail_price);
		
		searchButton = (Button)findViewById(R.id.my_order_search_button);
		searchContent = (Button)findViewById(R.id.my_order_search_content);
		searchKeyword = (EditText)findViewById(R.id.my_order_search_textview);
	}
	
	private void showOrderDetail(){
		if(orderNumber!=null && !"".equals(orderNumber)){
			orderNo.setText(orderNumber);
		}
		orderBooksList = DemoData.getMainListBooks();
		OrderDetailListAdapter adapter = new OrderDetailListAdapter(this, orderBooksList);
		orderBookListView.setAdapter(adapter);
	}
	
	public void cancel(View v){
		this.finish();
	}
	public void pay(View v){
		Intent intent = new Intent();
		intent.setClass(this, Pay.class);
		intent.putExtra("orderId", "No138735293");
		startActivity(intent);
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
