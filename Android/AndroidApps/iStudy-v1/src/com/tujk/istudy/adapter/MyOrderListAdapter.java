/**
 * 
 */
package com.tujk.istudy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tujk.istudy.R;
import com.tujk.istudy.ui.Pay;
import com.tujk.istudy.vo.Order;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class MyOrderListAdapter extends BaseAdapter {
	
	private List<Order> data = new ArrayList<Order>();
	LayoutInflater inflater = null;
	Context context;
	
	public static boolean isPay = true;
	public MyOrderListAdapter(Context context,List<Order> data){
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.my_order_list_item, null);
			holder.orderId = (TextView)convertView.findViewById(R.id.my_order_item_no);
			holder.status  = (TextView)convertView.findViewById(R.id.my_order_item_status);
			holder.time  = (TextView)convertView.findViewById(R.id.my_order_item_time);
			holder.price  = (TextView)convertView.findViewById(R.id.my_order_item_price);
			holder.cancel = (Button)convertView.findViewById(R.id.my_order_item_cancel);
			holder.pay = (Button)convertView.findViewById(R.id.my_order_item_pay);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		final String id = data.get(position).getId();
		holder.orderId.setText(id);
		holder.status.setText(data.get(position).getStatus()==1?"已支付":"未支付");
		holder.time.setText(data.get(position).getTime());
		holder.price.setText(data.get(position).getPrice()+"币");
		if(!isPay){
			holder.cancel.setVisibility(View.VISIBLE);
			holder.cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, R.string.delete_success, Toast.LENGTH_SHORT).show();
				}
			});
			holder.pay.setVisibility(View.VISIBLE);
			holder.pay.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(context, Pay.class);
					intent.putExtra("orderId", id);
					context.startActivity(intent);
				}
			});
		}else{
			holder.cancel.setVisibility(View.GONE);
			holder.pay.setVisibility(View.GONE);
		}
		
		return convertView;
	}

	class ViewHolder{
		public TextView orderId;
		public TextView time;
		public TextView price;
		public TextView status;
		public Button cancel;
		public Button pay;
	}
}
