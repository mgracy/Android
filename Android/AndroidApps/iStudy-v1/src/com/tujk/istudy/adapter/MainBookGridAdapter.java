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
import android.widget.ImageView;

import com.tujk.istudy.R;
import com.tujk.istudy.ui.BookDetail;
import com.tujk.istudy.vo.Book;

/**
 * title  : MainBookAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class MainBookGridAdapter extends BaseAdapter {
	
	private List<List<Book>> data = new ArrayList<List<Book>>();
	LayoutInflater inflater = null;
	Context context ;

	public MainBookGridAdapter(Context context,List<List<Book>> data){
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
			convertView = inflater.inflate(R.layout.main_book_grid_list_item, null);
			holder.poster1 = (ImageView)convertView.findViewById(R.id.main_book_item_image1);
			holder.poster2 = (ImageView)convertView.findViewById(R.id.main_book_item_image2);
			holder.poster3 = (ImageView)convertView.findViewById(R.id.main_book_item_image3);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		final List<Book> rowData = data.get(position);
		int size = rowData.size();
		if(size==1){
			Drawable background1 = Drawable.createFromPath(rowData.get(0).getPoster());
			holder.poster1.setBackgroundDrawable(background1);
			holder.poster1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String bookId = rowData.get(0).getId();
					gotoBookDetail(bookId);
				}
			});
			holder.poster2.setVisibility(View.GONE);
			holder.poster3.setVisibility(View.GONE);
		}else if(size==2){
			Drawable background1 = Drawable.createFromPath(rowData.get(0).getPoster());
			holder.poster1.setBackgroundDrawable(background1);
			holder.poster1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String bookId = rowData.get(0).getId();
					gotoBookDetail(bookId);
				}
			});
			Drawable background2 = Drawable.createFromPath(rowData.get(1).getPoster());
			holder.poster2.setBackgroundDrawable(background2);
			holder.poster2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String bookId = rowData.get(1).getId();
					gotoBookDetail(bookId);
				}
			});
			holder.poster3.setVisibility(View.GONE);
			
		}else if(size==3){
			Drawable background1 = Drawable.createFromPath(rowData.get(0).getPoster());
			holder.poster1.setBackgroundDrawable(background1);
			holder.poster1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String bookId = rowData.get(0).getId();
					gotoBookDetail(bookId);
				}
			});
			Drawable background2 = Drawable.createFromPath(rowData.get(1).getPoster());
			holder.poster2.setBackgroundDrawable(background2);
			holder.poster2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String bookId = rowData.get(1).getId();
					gotoBookDetail(bookId);
				}
			});
			Drawable background3 = Drawable.createFromPath(rowData.get(2).getPoster());
			holder.poster3.setBackgroundDrawable(background3);
			holder.poster3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String bookId = rowData.get(2).getId();
					gotoBookDetail(bookId);
				}
			});
		}
		
		
		return convertView;
	}

	class ViewHolder{
		public ImageView poster1;
		public ImageView poster2;
		public ImageView poster3;
	}
	
	protected void gotoBookDetail(String id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra("bookId", id);
		intent.setClass(context, BookDetail.class);
		context.startActivity(intent);
	}
}
