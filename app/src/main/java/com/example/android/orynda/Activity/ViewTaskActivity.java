package com.example.android.orynda.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.orynda.DB.DBHelper;
import com.example.android.orynda.DB.TaskContract;
import com.example.android.orynda.DB.Tasks;
import com.example.android.orynda.R;

/**
 * Created by админ on 23.07.2017.
 */

public class ViewTaskActivity extends AppCompatActivity {
    private TextView taskTitle;
    private TextView taskDeadline;
    private TextView taskDesc;
    private TextView taskReward;
    private TextView taskPunishment;
    private boolean isAlarmSet;
    private TextView reminderState;

    private SQLiteDatabase mDb;
    private Tasks task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        DBHelper dbHelper = new DBHelper(this);
        mDb = dbHelper.getWritableDatabase();
        task = getTask();
        taskTitle = (TextView) findViewById(R.id.view_task_name);
        taskDesc = (TextView) findViewById(R.id.view_task_description);
        taskReward = (TextView) findViewById(R.id.view_task_reward);
        taskPunishment = (TextView) findViewById(R.id.view_task_punishment);
        taskDeadline = (TextView) findViewById(R.id.view_task_due_date);
        reminderState = (TextView)findViewById(R.id.alarm_state);
            if(task!=null){
                taskTitle.setText(task.getTitle());
                taskDesc.setText(task.getDescription());
                taskReward.setText(task.getReward());
                taskPunishment.setText(task.getPunishment());
                if(TextUtils.isEmpty(task.getDeadline())){
                    taskDeadline.setText("Уақыт берілмеген");
                }else{
                    taskDeadline.setText(task.getDeadline());
                }
                if(task.isReminder()){
                    reminderState.setText("Хабарлама қосулы");

                }else{
                    reminderState.setText("Хабарлама өшірулі");
                }
            }else{
                Toast.makeText(ViewTaskActivity.this, "Ақпарат дұрыс енгізілмеген", Toast.LENGTH_LONG).show();
            }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_delete_task){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Тапсырманы жою керек па?");
            builder.create();
            builder.setPositiveButton("ЖОЮ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteSingleRowInDatabase(task.getId());
                    Intent listTaskIntent = new Intent(ViewTaskActivity.this, MainActivity.class);
                    startActivity(listTaskIntent);

                }
            });
            builder.setNegativeButton("ҚАЙТАРУ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ViewTaskActivity.this, "Тапсырма қайтарылды", Toast.LENGTH_LONG).show();
                }
            });
            builder.show();
            return true;
        }
        if(id == R.id.action_edit_task){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteSingleRowInDatabase(Long id) {
        String query = "DELETE FROM "+ TaskContract.TaskEntity.TABLE_NAME+" WHERE "+ TaskContract.TaskEntity._ID+" = "+id+" ";
        SQLiteDatabase mDb;
        DBHelper dbHelper = new DBHelper(this);
        mDb = dbHelper.getWritableDatabase();
        mDb.execSQL(query);
        mDb.close();

    }


    public Tasks getTask(){
        Intent intent = getIntent();
        Tasks task  = (Tasks) intent.getSerializableExtra("Bak");
        return task;
    }


}
