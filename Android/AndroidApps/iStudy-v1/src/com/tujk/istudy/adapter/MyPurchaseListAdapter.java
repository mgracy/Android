/**
 * 
 */
package com.tujk.istudy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tujk.istudy.R;
import com.tujk.istudy.vo.Purchase;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class MyPurchaseListAdapter extends BaseAdapter {
	
	private List<Purchase> data = new ArrayList<Purchase>();
	LayoutInflater inflater = null;
	Context context;
	
	public MyPurchaseListAdapter(Context context,List<Purchase> data){
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
			convertView = inflater.inflate(R.layout.my_purchase_list_item, null);
			holder.poster = (ImageView)convertView.findViewById(R.id.my_purchase_list_item_image);
			holder.title = (TextView)convertView.findViewById(R.id.my_purchase_list_item_title);
			holder.desc  = (TextView)convertView.findViewById(R.id.my_purchase_list_item_desc);
			holder.time  = (TextView)convertView.findViewById(R.id.my_purchase_list_item_time);
			holder.price  = (TextView)convertView.findViewById(R.id.my_purchase_list_item_price);
			holder.checkBox = (CheckBox)convertView.findViewById(R.id.my_purchase_list_item_chk);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		Drawable posterImage = Drawable.createFromPath(data.get(position).getPoster());
		holder.poster.setBackgroundDrawable(posterImage);
		holder.title.setText(data.get(position).getTitle());
		holder.desc.setText(data.get(position).getDesc());
		holder.time.setText(data.get(position).getTime());
		holder.price.setText(data.get(position).getPrice()+"币");
		
		holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return convertView;
	}

	class ViewHolder{
		public ImageView poster;
		public TextView title;
		public TextView desc;
		public TextView time;
		public TextView price;
		public CheckBox checkBox;
	}
}
