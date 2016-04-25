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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tujk.istudy.R;
import com.tujk.istudy.vo.User;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class MyFriendsListAdapter extends BaseAdapter {
	
	private List<User> data = new ArrayList<User>();
	LayoutInflater inflater = null;
	Context context;
	
	public static boolean isFriends = true;

	public MyFriendsListAdapter(Context context,List<User> data){
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
			convertView = inflater.inflate(R.layout.my_friends_list_item, null);
			holder.poster = (ImageView)convertView.findViewById(R.id.my_friends_list_item_image);
			holder.name  = (TextView)convertView.findViewById(R.id.my_friends_list_item_title);
			holder.desc  = (TextView)convertView.findViewById(R.id.my_friends_list_item_desc);
			holder.sex  = (TextView)convertView.findViewById(R.id.my_friends_list_item_sex);
			holder.add = (ImageView)convertView.findViewById(R.id.my_friends_list_item_add);
			holder.delete = (ImageView)convertView.findViewById(R.id.my_friends_list_item_delete);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		Drawable background = Drawable.createFromPath(data.get(position).getImage());
		holder.poster.setBackgroundDrawable(background);
		holder.name.setText(data.get(position).getName());
		holder.desc.setText(data.get(position).getDesc());
		holder.sex.setText(data.get(position).getSex()==1?"男":"女");
		if(isFriends){
			holder.add.setVisibility(View.GONE);
			holder.delete.setVisibility(View.VISIBLE);
			holder.delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, R.string.delete_success, Toast.LENGTH_SHORT).show();
				}
			});
		}else{
			holder.delete.setVisibility(View.GONE);
			holder.add.setVisibility(View.VISIBLE);
			holder.add.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context,  R.string.add_success, Toast.LENGTH_SHORT).show();
				}
			});
		}
		
		return convertView;
	}

	class ViewHolder{
		public ImageView poster;
		public TextView name;
		public TextView desc;
		public TextView sex;
		public ImageView add;
		public ImageView delete;
	}
}
