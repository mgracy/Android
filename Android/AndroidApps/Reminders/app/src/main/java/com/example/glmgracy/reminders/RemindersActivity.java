package com.example.glmgracy.reminders;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class RemindersActivity extends AppCompatActivity {

    private ListView mListView;
    private RemindersDbAdapter mDbAdapter;
    private RemindersSimpleCursorAdapter mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        mListView = (ListView) this.findViewById(R.id.reminders_list_view);
        mListView.setDivider(null);
        mDbAdapter = new RemindersDbAdapter(this);
        mDbAdapter.open();
//        if(savedInstanceState == null){
//            //Clear all data
//            mDbAdapter.deleteAllReminders();
//            //Add some data
//            insertSomeReminders();
//        }


        Cursor cursor = mDbAdapter.fetchAllReminders();

        String[] from = new String[]{ RemindersDbAdapter.COL_CONTNET};
        int[] to = new int[]{ R.id.row_text};
        mCursorAdapter = new RemindersSimpleCursorAdapter(
                RemindersActivity.this,
                R.layout.reminders_row,
                cursor,
                from,
                to,
                0
        );
        mListView.setAdapter(mCursorAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RemindersActivity.this);
                ListView modeListView = new ListView(RemindersActivity.this);
                String[] modes = new String[] { "Edit Reminder", "Delete Reminder", "Schedule Reminder" };
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(RemindersActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, modes);
                modeListView.setAdapter(modeAdapter);
                builder.setView(modeListView);
                final Dialog dialog = builder.create();
                dialog.show();
                modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    int nId = getIdFromPostion(masterListPosition);
                    Reminder reminder = mDbAdapter.fetchReminderById(nId);
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0){
                            fireCustomDialog(reminder);
                        }else if(position == 1) {
                            mDbAdapter.deleteReminderById(getIdFromPostion(masterListPosition));
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllReminders());
                        }else{
                            final Date today = new Date();
//                            new TimePickerDialog(RemindersActivity.this, null, today.getHours(), today.getMinutes(),false).show();
                            TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener(){
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    Date alarm = new Date(today.getYear(), today.getMonth(), today.getDate(), hourOfDay, minute);
                                    scheduleReminder(alarm.getTime(), reminder.getContent());
                                }
                            };

                            new TimePickerDialog(RemindersActivity.this, listener,today.getHours(),today.getMinutes(),false).show();
                        }

                        dialog.dismiss();
                    }
                });
            }
        });
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener(){
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.cam_menu, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_item_delete_reminder:
                            for(int nC = mCursorAdapter.getCount() - 1; nC >= 0; nC --){
                                if(mListView.isItemChecked(nC)){
                                    mDbAdapter.deleteReminderById(getIdFromPostion(nC));
                                }
                            }
                            mode.finish();
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllReminders());
                            return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                this,
//                R.layout.reminders_row,
//                R.id.row_text,
//                new String[]{"first record", "second record", "third record"}
//        );
//        mListView.setAdapter(arrayAdapter);
    }

    private void scheduleReminder(long time, String content){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, ReminderAlarmReceiver.class);
        alarmIntent.putExtra(ReminderAlarmReceiver.REMINDER_TEXT, content);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, broadcast);
    }
    private int getIdFromPostion(int nC) {
        return (int)mCursorAdapter.getItemId(nC);
    }

    private void insertSomeReminders() {
        mDbAdapter.createReminder("Buy Learn Android Studio", true);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Buy Learn Android Studio", true);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Buy Learn Android Studio", true);
    }

    private void fireCustomDialog(final Reminder reminder){
        //custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);

        TextView titleView = (TextView) dialog.findViewById(R.id.custom_title);
        final EditText editCustom = (EditText) dialog.findViewById(R.id.custom_edit_reminder);
        Button commitButton = (Button)dialog.findViewById(R.id.custom_button_commit);
        final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.custom_check_box);
        LinearLayout rootLayout = (LinearLayout) dialog.findViewById(R.id.custom_root_layout);
        final boolean isEditOperation = (reminder!=null);

        //this is for an edit
        if(isEditOperation){
            titleView.setText("Edit Reminder");
            checkBox.setChecked(reminder.getImportant()==1);
            editCustom.setText(reminder.getContent());
            rootLayout.setBackgroundColor(getResources().getColor(R.color.blue));
        }

        commitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String reminderText = editCustom.getText().toString();
                if(isEditOperation){
                    Reminder reminderEdited = new Reminder(reminderText,reminder.getId(), checkBox.isChecked()? 1 : 0);
                    mDbAdapter.updateReminder(reminderEdited);
                }else{
                    mDbAdapter.createReminder(reminderText, checkBox.isChecked());
                }
                mCursorAdapter.changeCursor(mDbAdapter.fetchAllReminders());
                dialog.dismiss();
            }
        });
        Button buttonCancel = (Button)dialog.findViewById(R.id.custom_button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminders, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new:
//                Log.d(getLocalClassName(), "create new Reminder");
                fireCustomDialog(null);
                return true;
            case R.id.action_about:
                fireAboutDialog();
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return false;
        }
//        return super.onOptionsItemSelected(item);
    }

    private void fireAboutDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_about);
        dialog.show();
    }
}
