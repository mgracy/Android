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
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tujk.istudy.R;
import com.tujk.istudy.vo.SdCardInfo;

/**
 * title  : CardListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class CardListAdapter extends BaseAdapter {
	
	private List<SdCardInfo> data = new ArrayList<SdCardInfo>();
	LayoutInflater inflater = null;

	public CardListAdapter(Context context,List<SdCardInfo> data){
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
			convertView = inflater.inflate(R.layout.external_storage_item, null);
			holder.name  = (TextView)convertView.findViewById(R.id.card_item_name);
			holder.storage  = (TextView)convertView.findViewById(R.id.card_item_storage);
			holder.progressBar  = (ProgressBar)convertView.findViewById(R.id.card_item_progressbar);
			holder.checkBox = (CheckBox)convertView.findViewById(R.id.card_item_checkbox);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.name.setText("sd"+(position+1));
		holder.storage.setText((data.get(position).getTotalStorage()-data.get(position).getAvailableStorage())+"k/"+data.get(position).getTotalStorage()+"k");
		holder.progressBar.setProgress(100-(int)(data.get(position).getAvailableStorage()*100/data.get(position).getTotalStorage()));
		
		return convertView;
	}

	class ViewHolder{
		public TextView name;
		public TextView storage;
		public ProgressBar progressBar;
		public CheckBox checkBox;
	}
}
