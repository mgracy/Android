/**
 * 
 */
package com.tujk.istudy.ui;

import java.util.Collections;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tujk.istudy.BaseActivity;
import com.tujk.istudy.R;
import com.tujk.istudy.adapter.MyFriendsListAdapter;
import com.tujk.istudy.data.DemoData;
import com.tujk.istudy.vo.User;

/**
 * title  : MyFriends.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-26
 */
public class MyFriends extends BaseActivity {

	RadioButton classmate;
	RadioButton friends;
	RadioGroup myFriendsRadioGroup;
	
	List<User> myFriendsList = null;
	
	ListView classmateListView;
	ListView friendsListView;
	
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
		setContentView(R.layout.my_friends);
		initUI();
		showFriends();
	};
	
	
	/**
	 * 
	 */
	private void showFriends() {
		Log.i("mainmenu","friends");
		// TODO Auto-generated method stub
		MyFriendsListAdapter.isFriends = true;
		myFriendsList = DemoData.getFriends();
		friendsListView.setVisibility(View.VISIBLE);
		MyFriendsListAdapter adapter = new MyFriendsListAdapter(this, myFriendsList);
		friendsListView.setAdapter(adapter);
		classmateListView.setVisibility(View.GONE);
	}

	private void showClassmate() {
		Log.i("mainmenu","classmate");
		MyFriendsListAdapter.isFriends = false;
		myFriendsList = DemoData.getFriends();
		Collections.shuffle(myFriendsList);
		classmateListView.setVisibility(View.VISIBLE);
		MyFriendsListAdapter adapter = new MyFriendsListAdapter(this, myFriendsList);
		classmateListView.setAdapter(adapter);
		friendsListView.setVisibility(View.GONE);
	}

	public void searchContent(View v){
		//do search
	}
	
	/* (non-Javadoc)
	 * @see com.tujk.istudy.BaseActivity#initUI()
	 */
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		classmate = (RadioButton)findViewById(R.id.my_friends_classmate_radio);
		friends = (RadioButton)findViewById(R.id.my_friends_friend_radio);
		classmateListView = (ListView)findViewById(R.id.my_friends_classmate_listview);
		friendsListView = (ListView)findViewById(R.id.my_friends_friend_listview);
		myFriendsRadioGroup = (RadioGroup)findViewById(R.id.my_friends_radio_group);
		myFriendsRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.my_friends_classmate_radio:
					showClassmate();
					break;
				case R.id.my_friends_friend_radio:
					showFriends();
					break;
				default:
					showFriends();
					break;
				}
			}
		});
		
		searchButton = (Button)findViewById(R.id.my_friends_search_button);
		searchContent = (Button)findViewById(R.id.my_friends_search_content);
		searchKeyword = (EditText)findViewById(R.id.my_friends_textview);
	}
	
	
	
	public void operation(View v){
		new AlertDialog.Builder(this).setItems(R.array.sex, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(which == 0){//chose man
					
				}else if(which == 1){//chose woman
					
				}
				dialog.cancel();
			}
		}).show();
		
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
