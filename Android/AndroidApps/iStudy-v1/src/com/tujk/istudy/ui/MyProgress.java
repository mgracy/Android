/**
 * 
 */
package com.tujk.istudy.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.MyPlanListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.Plan;

/**
 * title  : MyProgress.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class MyProgress extends BaseActivity {
	
	RadioButton endButton;
	RadioButton startButton;
	RadioGroup myProgressRadioGroup;
	
	TextView timeTextView;
	
	List<Plan> planList;
	ListView endListView;
	ListView startListView;
	
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat format = new SimpleDateFormat("MM yyyy");
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_progress);
		initUI();
		showEndProgress();
	};
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		endButton = (RadioButton)findViewById(R.id.my_progress_end_radio);
		startButton = (RadioButton)findViewById(R.id.my_progress_start_radio);
		endListView = (ListView)findViewById(R.id.my_progress_end_listview);
		startListView = (ListView)findViewById(R.id.my_progress_start_listview);
		
		searchButton = (Button)findViewById(R.id.my_plan_search_button);
		searchContent = (Button)findViewById(R.id.my_plan_search_content);
		searchKeyword = (EditText)findViewById(R.id.my_plan_search_textview);
		
		timeTextView = (TextView)findViewById(R.id.my_progress_time);
		timeTextView.setText(format.format(calendar.getTime()));
		
		myProgressRadioGroup = (RadioGroup)findViewById(R.id.my_progress_radio_group);
		myProgressRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.my_progress_end_radio:
					showEndProgress();
					break;
				case R.id.my_progress_start_radio:
					showStartProgress();
					break;
				default:
					showEndProgress();
					break;
				}
			}
		});
	}
	
	/**
	 * 
	 */
	private void showStartProgress() {
		// TODO Auto-generated method stub
		planList = DemoData.getMyPlan();
		MyPlanListAdapter adapter = new MyPlanListAdapter(this,planList);
		endListView.setVisibility(View.GONE);
		startListView.setVisibility(View.VISIBLE);
		startListView.setAdapter(adapter);
	}

	/**
	 * 
	 */
	private void showEndProgress() {
		// TODO Auto-generated method stub
		planList = DemoData.getMyPlan();
		MyPlanListAdapter adapter = new MyPlanListAdapter(this,planList);
		startListView.setVisibility(View.GONE);
		endListView.setVisibility(View.VISIBLE);
		endListView.setAdapter(adapter);
	}

	public void timeToLeft(View v){
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		timeTextView.setText(format.format(calendar.getTime()));
	}
	
	public void timeToRight(View v){
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		timeTextView.setText(format.format(calendar.getTime()));
	}
	
	public void choseTime(View v){
		showDialog(1);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, monthOfYear);
				calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				timeTextView.setText(format.format(calendar.getTime()));
			}
		},calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#getHandler()
	 */
	@Override
	protected Handler getHandler() {
		// TODO Auto-generated method stub
		return handler;
	}

}
