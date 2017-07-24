package com.example.android.orynda.Fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.orynda.Activity.ViewTaskActivity;
import com.example.android.orynda.Adapters.TaskAdapter;
import com.example.android.orynda.DB.DBHelper;
import com.example.android.orynda.DB.TaskContract;
import com.example.android.orynda.DB.Tasks;
import com.example.android.orynda.R;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by админ on 20.07.2017.
 */

public class MyListFragment extends Fragment {
    private TaskAdapter mAdapter;
    private static SQLiteDatabase mDb;
    private RecyclerView mRecyclerView;
    ArrayList<Tasks> taskList = new ArrayList<Tasks>();

    public MyListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DBHelper dbHelper = new DBHelper(getActivity());
        mDb = dbHelper.getWritableDatabase();

        Cursor mCursor = getAllTasks();
        taskList = setAllTasks(mCursor);


        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Tasks bak = taskList.get(position);
                Intent mIntent = new Intent(getActivity(),ViewTaskActivity.class);
                mIntent.putExtra("Bak",bak);
                mIntent.putExtra("Serial", (Serializable) bak);
                startActivity(mIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mAdapter = new TaskAdapter(getActivity(),mCursor);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public Cursor getAllTasks(){
        String query = "SELECT * FROM "+ TaskContract.TaskEntity.TABLE_NAME+" ORDER BY "+ TaskContract.TaskEntity.COLUMN_TASK_DEADLINE+" ASC";
        return mDb.rawQuery(query,null);
    }

    public ArrayList<Tasks> setAllTasks(Cursor cursor){
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        if(cursor==null)
            cursor.moveToFirst();


        while(cursor.moveToNext()){
           Tasks task = new Tasks();
            task.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.TaskEntity._ID)));
            task.setTitle(cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_TITLE)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_DESC)));
            task.setDeadline(cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE)));
            task.setReminder(cursor.getInt(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_REMINDER))==1);
            task.setReward(cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_REWARD)));
            task.setPunishment(cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT)));
            task.setSuccess(cursor.getInt(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS))==1);
            task.setCompleted(cursor.getInt(cursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED))==1);
            taskList.add(task);
        }

        return  taskList;
    }

  }
