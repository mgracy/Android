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
import android.widget.Button;
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
public class UserInfo extends BaseActivity {
	
	TextView birthday;
	Calendar calendar = Calendar.getInstance();
	Button sexButton;
	boolean male = true;

	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		initUI();
	}
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		birthday = (TextView)findViewById(R.id.user_info_birthday);
		sexButton = (Button)findViewById(R.id.user_info_sex);
		if(male){
			sexButton.setBackgroundResource(R.drawable.lbs_male);
		}else{
			sexButton.setBackgroundResource(R.drawable.lbs_female);
		}
	}
	
	public void save(View v){
		Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		return new DatePickerDialog(this, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, monthOfYear);
				calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				birthday.setText(format.format(calendar.getTime()));
			}
		},calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public void choseDate(View v){
		showDialog(1);
	}
	
	public void changeSex(View v){
		if(male){
			sexButton.setBackgroundResource(R.drawable.lbs_female);
			male = false;
		}else{
			sexButton.setBackgroundResource(R.drawable.lbs_male);
			male = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	public void back(View v){
		this.finish();
	}
}
