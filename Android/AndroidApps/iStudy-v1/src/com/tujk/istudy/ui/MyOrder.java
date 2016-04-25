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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.MyOrderListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.Order;

/**
 * title  : MyOrder.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class MyOrder extends BaseActivity {

	RadioGroup myOrderRadioGroup;
	RadioButton payButton;
	RadioButton nopayButton;
	
	List<Order> myOrderList;
	ListView payListView;
	ListView nopayListView;
	
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
		setContentView(R.layout.my_order);
		initUI();
		showPayedOrder();
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
		payButton = (RadioButton)findViewById(R.id.my_order_pay_button);
		nopayButton = (RadioButton)findViewById(R.id.my_order_no_pay_button);
		payListView = (ListView)findViewById(R.id.my_order_pay);
		nopayListView = (ListView)findViewById(R.id.my_order_nopay);
		
		myOrderRadioGroup = (RadioGroup)findViewById(R.id.my_order_radio_group);
		myOrderRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.my_order_pay_button:
					showPayedOrder();
					break;
				case R.id.my_order_no_pay_button:
					showNoPayOrder();
					break;
				default:
					showPayedOrder();
					break;
				}
			}
		});
		
		searchButton = (Button)findViewById(R.id.my_order_search_button);
		searchContent = (Button)findViewById(R.id.my_order_search_content);
		searchKeyword = (EditText)findViewById(R.id.my_order_search_textview);
	}
	
	private void showPayedOrder(){
		MyOrderListAdapter.isPay = true;
		myOrderList = DemoData.getOrders(1);
		MyOrderListAdapter adapter = new MyOrderListAdapter(this, myOrderList);
		payListView.setVisibility(View.VISIBLE);
		payListView.setAdapter(adapter);
		nopayListView.setVisibility(View.GONE);
	}
	
	private void showNoPayOrder(){
		MyOrderListAdapter.isPay = false;
		myOrderList = DemoData.getOrders(0);
		MyOrderListAdapter adapter = new MyOrderListAdapter(this, myOrderList);
		payListView.setVisibility(View.GONE);
		nopayListView.setVisibility(View.VISIBLE);
		nopayListView.setAdapter(adapter);
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
