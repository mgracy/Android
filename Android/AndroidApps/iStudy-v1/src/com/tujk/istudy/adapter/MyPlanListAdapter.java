/**
 * 
 */
package com.tujk.istudy.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tujk.istudy.R;
import com.tujk.istudy.ui.OpenVideo;
import com.tujk.istudy.vo.Plan;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class MyPlanListAdapter extends BaseAdapter {
	
	private List<Plan> data = new ArrayList<Plan>();
	LayoutInflater inflater = null;
	Context context;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public MyPlanListAdapter(Context context,List<Plan> data){
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
			convertView = inflater.inflate(R.layout.my_progress_list_item, null);
			holder.title = (TextView)convertView.findViewById(R.id.my_progress_list_item_title);
			holder.startDate  = (TextView)convertView.findViewById(R.id.my_progress_list_item_startdate);
			holder.endDate  = (TextView)convertView.findViewById(R.id.my_progress_list_item_enddate);
			holder.play = (ImageView)convertView.findViewById(R.id.my_progress_list_item_play);
			holder.delete = (ImageView)convertView.findViewById(R.id.my_progress_list_item_delete);
			holder.progress  = (TextView)convertView.findViewById(R.id.my_progress_list_item_progress);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		final String bookId = data.get(position).getId();
		holder.title.setText(data.get(position).getBookName()+"课程的计划");
		holder.startDate.setText(format.format(data.get(position).getStartDate()));
		holder.endDate.setText(format.format(data.get(position).getEndDate()));
		int i = new Date().getDay() - data.get(position).getStartDate().getDay();
		holder.progress.setText("已进行"+i+"天"+data.get(position).getProgress()+"%");
		
		holder.play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("bookId", bookId);
				intent.setClass(context, OpenVideo.class);
				context.startActivity(intent);
			}
		});
		
		holder.delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, R.string.delete_success, Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	class ViewHolder{
		public TextView title;
		public TextView startDate;
		public TextView endDate;
		public ImageView play;
		public ImageView delete;
		public TextView progress;
	}
}
