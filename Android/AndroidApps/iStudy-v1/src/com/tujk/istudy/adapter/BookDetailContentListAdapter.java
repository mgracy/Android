/**
 * 
 */
package com.tujk.istudy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tujk.istudy.R;
import com.tujk.istudy.vo.BookContent;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class BookDetailContentListAdapter extends BaseAdapter {
	
	private List<BookContent> data = new ArrayList<BookContent>();
	LayoutInflater inflater = null;

	public BookDetailContentListAdapter(Context context,List<BookContent> data){
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
			convertView = inflater.inflate(R.layout.detail_content_list_item, null);
			holder.title  = (TextView)convertView.findViewById(R.id.detail_content_list_title);
			holder.page  = (TextView)convertView.findViewById(R.id.detail_content_list_page);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.title.setText(data.get(position).getTitle());
		holder.page.setText("第"+(data.get(position).getPage()+1)+"页");
		
		return convertView;
	}

	class ViewHolder{
		public TextView title;
		public TextView page;
	}
}
