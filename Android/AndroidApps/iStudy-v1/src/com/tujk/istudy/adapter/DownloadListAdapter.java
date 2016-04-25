/**
 * 
 */
package com.tujk.istudy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tujk.istudy.R;
import com.tujk.istudy.ui.OpenVideo;
import com.tujk.istudy.vo.DownloadBook;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class DownloadListAdapter extends BaseAdapter {
	
	private List<DownloadBook> data = new ArrayList<DownloadBook>();
	LayoutInflater inflater = null;
	Context context;
	
	public static boolean isDownloaded = false;
	
	public DownloadListAdapter(Context context,List<DownloadBook> data){
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
			convertView = inflater.inflate(R.layout.download_book_list_item, null);
			holder.bookLayout = (RelativeLayout)convertView.findViewById(R.id.download_book_layout);
			holder.subBookLayout = (RelativeLayout)convertView.findViewById(R.id.download_sub_book_layout);
			holder.poster = (ImageView)convertView.findViewById(R.id.download_book_list_item_image);
			holder.bookTitle = (TextView)convertView.findViewById(R.id.download_book_list_item_title);
			holder.desc = (TextView)convertView.findViewById(R.id.download_book_list_item_desc);
			holder.time  = (TextView)convertView.findViewById(R.id.download_book_list_item_time);
			
			holder.subBookTitle = (TextView)convertView.findViewById(R.id.download_sub_book_list_item_title);
			holder.progress  = (TextView)convertView.findViewById(R.id.download_sub_book_list_item_progress);
			holder.progressBar  = (ProgressBar)convertView.findViewById(R.id.download_sub_book_list_item_progress_bar);
			holder.button1 = (ImageButton)convertView.findViewById(R.id.download_sub_book_list_item_button1);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		final ImageButton button = holder.button1;
		final DownloadBook book = data.get(position);
		if(!book.isSubBook()){
			holder.subBookLayout.setVisibility(View.GONE);
			holder.bookLayout.setVisibility(View.VISIBLE);
			Drawable posterImage = Drawable.createFromPath(book.getPoster());
			holder.poster.setBackgroundDrawable(posterImage);
			holder.bookTitle.setText(book.getTitle());
			holder.desc.setText(book.getDesc());
			holder.time.setText(book.getTime());
		}else {
			holder.subBookLayout.setVisibility(View.VISIBLE);
			holder.bookLayout.setVisibility(View.GONE);
			holder.subBookTitle.setText(book.getSubTitle());
			holder.progress.setText(book.getDownloadProgress() + "%");
			holder.progressBar.setProgress(book.getDownloadProgress());
			if(isDownloaded){
				holder.button1.setBackgroundResource(R.drawable.play);
				holder.button1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.putExtra("bookId", book.getId());
						intent.setClass(context, OpenVideo.class);
						context.startActivity(intent);
					}
				});
			}else{
				holder.button1.setBackgroundResource(R.drawable.cancel_download);
				holder.button1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(!book.isCancelDownload()){
							book.setCancelDownload(true);
							button.setBackgroundResource(R.drawable.download_yuan);
							Toast.makeText(context, R.string.cancel_success, Toast.LENGTH_SHORT).show();
						}else{
							book.setCancelDownload(false);
							button.setBackgroundResource(R.drawable.cancel_download);
							Toast.makeText(context, R.string.continue_download, Toast.LENGTH_SHORT).show();
						}
					}
				});
				
			}
		}
		
		return convertView;
	}

	class ViewHolder{
		public RelativeLayout bookLayout;
		public RelativeLayout subBookLayout;
		public ImageView poster;
		public TextView bookTitle;
		public TextView subBookTitle;
		public TextView desc;
		public TextView progress;
		public TextView time;
		public ImageButton button1;
		public ProgressBar progressBar;
	}
}
