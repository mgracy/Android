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
import android.widget.ImageView;
import android.widget.TextView;

import com.tujk.istudy.R;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.ui.OpenPDFBook;
import com.tujk.istudy.ui.OpenVideo;
import com.tujk.istudy.vo.Book;
import com.tujk.istudy.vo.BookMarkValue;

/**
 * title  : MainBookListAdapter.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class BookNoteListAdapter extends BaseAdapter {
	
	private List<BookMarkValue> data = new ArrayList<BookMarkValue>();
	LayoutInflater inflater = null;
	Context context;
	
	public BookNoteListAdapter(Context context,List<BookMarkValue> data){
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
			convertView = inflater.inflate(R.layout.book_note_list_item, null);
			holder.name = (TextView)convertView.findViewById(R.id.book_note_listitem_text);
			holder.imageButton = (ImageView)convertView.findViewById(R.id.book_note_listitem_button);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		final BookMarkValue bookMark = data.get(position);
		if(data.get(position).getType()==1){//标签
			holder.name.setText(context.getResources().getString(R.string.book_marker) + (position+1));
			holder.imageButton.setBackgroundResource(R.drawable.play);
			holder.imageButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Book book = DemoData.getBookById(bookMark.getBookId());
					Intent intent = new Intent();
					if(book.getType()==0){
						intent.setClass(context, OpenVideo.class);
					}else {
						intent.setClass(context, OpenPDFBook.class);
					}
					intent.putExtra("bookId", book.getId());
					intent.putExtra("numberOfPage", bookMark.getNumberOfPage());
					context.startActivity(intent);
				}
			});
		}else{//笔记
			holder.name.setText(context.getResources().getString(R.string.book_note) + (position+1));
			holder.imageButton.setBackgroundResource(R.drawable.detailbtn);
		}
		
		
		return convertView;
	}

	class ViewHolder{
		public TextView name;
		public ImageView imageButton;
	}
}
