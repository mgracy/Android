/**
 * 
 */
package com.tujk.istudy.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;

/**
 * title  : AddBookPlan.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-29
 */
public class AddBookPlan extends BaseActivity {

	Calendar calendar = Calendar.getInstance();
	TextView startDate;
	TextView endDate;
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_book_plan);
		initUI();
	}
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		startDate = (TextView)findViewById(R.id.book_plan_start_date);
		endDate   = (TextView)findViewById(R.id.book_plan_end_date);
	}
	
	public void choseStartDate(View v){
		showDialog(1);
	}
	
	public void choseEndDate(View v){
		showDialog(2);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case 1:
			return new DatePickerDialog(this, new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.MONTH, monthOfYear);
					calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					startDate.setText(format.format(calendar.getTime()));
				}
			}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		case 2:
			return new DatePickerDialog(this, new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.MONTH, monthOfYear);
					calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					endDate.setText(format.format(calendar.getTime()));
				}
			}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		default:
			return  null;
		}
	}
	
	public void save(View v){
		Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
	}
	
	public void cancel(View v){
		this.finish();
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
