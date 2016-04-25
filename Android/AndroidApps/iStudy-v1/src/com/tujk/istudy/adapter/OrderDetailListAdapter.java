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
import android.widget.ImageView;
import android.widget.TextView;

import com.tujk.istudy.R;
import com.tujk.istudy.vo.Book;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class OrderDetailListAdapter extends BaseAdapter {
	
	private List<Book> data = new ArrayList<Book>();
	LayoutInflater inflater = null;

	public OrderDetailListAdapter(Context context,List<Book> data){
		this.data = data;
		this.inflater = LayoutInflater.from(context);
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
			convertView = inflater.inflate(R.layout.order_detail_list_item, null);
			holder.poster = (ImageView)convertView.findViewById(R.id.order_detail_list_item_image);
			holder.title  = (TextView)convertView.findViewById(R.id.order_detail_list_item_title);
			holder.desc  = (TextView)convertView.findViewById(R.id.order_detail_list_item_desc);
			holder.time  = (TextView)convertView.findViewById(R.id.order_detail_list_item_time);
			holder.price = (TextView)convertView.findViewById(R.id.order_detail_list_item_price);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		Drawable background = Drawable.createFromPath(data.get(position).getPoster());
		holder.poster.setBackgroundDrawable(background);
		holder.title.setText(data.get(position).getTitle());
		holder.desc.setText(data.get(position).getDesc());
		holder.time.setText(data.get(position).getTime());
		holder.price.setText(data.get(position).getPrice() + "币");
		
		return convertView;
	}

	class ViewHolder{
		public ImageView poster;
		public TextView title;
		public TextView desc;
		public TextView time;
		public TextView price;
	}
}
