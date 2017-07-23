package com.example.android.orynda.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.orynda.DB.DBHelper;
import com.example.android.orynda.DB.TaskContract;
import com.example.android.orynda.R;

/**
 * Created by админ on 23.07.2017.
 */

public class AddTaskActivity extends AppCompatActivity {
    private static final String TAG = AddTaskActivity.class.getSimpleName();
    private EditText taskTitle;
    private EditText taskDescription;
    private EditText taskReward;
    private EditText taskPunishment;
    private static EditText taskDueDate;
    private static EditText taskDueTime;
    private CheckBox reminder;
    private boolean isAlarmSet = false;
    private static String selectedTime;
    private static String selectedDate;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        DBHelper dbHelper = new DBHelper(this);
        mDb = dbHelper.getWritableDatabase();
        taskTitle = (EditText) findViewById(R.id.add_task_title);
        taskDescription = (EditText) findViewById(R.id.add_task_description);
        taskReward = (EditText) findViewById(R.id.add_task_reward);
        taskPunishment = (EditText) findViewById(R.id.add_task_punishment);
        taskDueDate = (EditText) findViewById(R.id.add_task_ending);
        taskDueTime = (EditText)findViewById(R.id.add_task_ending_time);
        reminder = (CheckBox)findViewById(R.id.set_task_alarm);
        reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isAlarmSet = true;
                }else{
                    isAlarmSet = false;
                }
            }
        });
        //add task date
        ImageView addTaskDate = (ImageView)findViewById(R.id.add_task_date);
        addTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerFragment().show(getSupportFragmentManager(), "Task Date");
            }
        });
        // delete task date
        ImageView deleteTaskDate = (ImageView)findViewById(R.id.delete_task_date);
        deleteTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDueDate.setText("");
            }
        });
        ImageView addTaskTime = (ImageView)findViewById(R.id.add_task_time);
        addTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePicker().show(getSupportFragmentManager(), "Task Time");
            }
        });
        ImageView deleteTaskTime = (ImageView)findViewById(R.id.delete_task_time);
        deleteTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDueTime.setText("");
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add_task){
            addTask();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            selectedTime =  String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
            taskDueTime.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int mon = c.get(Calendar.MONTH);
            int month = mon+1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            selectedDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
            taskDueDate.setText(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
        }
    }

    public void addTask(){
        String title = taskTitle.getText().toString();
        String description = taskDescription.getText().toString();
        String reward = taskReward.getText().toString();
        String punishment = taskPunishment.getText().toString();
        String dueDate = taskDueDate.getText().toString();
        String dueTime = taskDueTime.getText().toString();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(reward)||TextUtils.isEmpty(punishment)) {
            Toast.makeText(AddTaskActivity.this, R.string.invalid_input_values, Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(dueDate) || TextUtils.isEmpty(dueTime)) {
            Toast.makeText(AddTaskActivity.this, R.string.task_date_and_time, Toast.LENGTH_LONG).show();
        } else {
            addToDb(title,description,reward,punishment,dueDate,dueTime,isAlarmSet);
        }

    }
    public void addToDb(String title, String desc, String reward,String punishment, String dueDate, String dueTime, boolean reminder){
        ContentValues cv = new ContentValues();
        int remind = 0;
        if(reminder==true){
            remind=1;
        }else{
            remind = 0;
        }
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,title);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,desc);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,dueDate+" "+dueTime);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,reward);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,punishment);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,remind);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,0);
        mDb.insert(TaskContract.TaskEntity.TABLE_NAME, null, cv);
        clear();

    }

    public void clear(){
        Intent mIntent = new Intent(AddTaskActivity.this,MainActivity.class);
        startActivity(mIntent);
        taskTitle.getText().clear();
        taskDescription.getText().clear();
        taskReward.getText().clear();
        taskDueDate.getText().clear();
        taskDueTime.getText().clear();
        taskPunishment.getText().clear();
        taskPunishment.clearFocus();
    }

}
