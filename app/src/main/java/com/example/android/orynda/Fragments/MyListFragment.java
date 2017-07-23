package com.example.android.orynda.Fragments;

import android.content.ContentValues;
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

import com.example.android.orynda.Adapters.TaskAdapter;
import com.example.android.orynda.DB.DBHelper;
import com.example.android.orynda.DB.TaskContract;
import com.example.android.orynda.DB.Tasks;
import com.example.android.orynda.R;


import java.util.ArrayList;
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
                Toast.makeText(getContext(), bak.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();

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
        return mDb.query(
                TaskContract.TaskEntity.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
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
            Log.d("New Task:",task.getTitle());
            taskList.add(task);
        }



        return  taskList;
    }

    public void insertFakeData(SQLiteDatabase db){
        if(db==null)
            return;

        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"First to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"First to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"First to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"First to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"First to do");
        list.add(cv);

        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);

        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        cv = new ContentValues();
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_TITLE,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DESC,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE,"06-08-2017 12:30");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REMINDER,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_REWARD,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT,"Second to do");
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_COMPLETED,0);
        cv.put(TaskContract.TaskEntity.COLUMN_TASK_SUCCESS,"Second to do");
        list.add(cv);
        try{
            db.beginTransaction();
            db.delete (TaskContract.TaskEntity.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(TaskContract.TaskEntity.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
            Log.e("List Size", String.valueOf(list.size()));
        }catch (SQLException e){

        }finally{
            db.endTransaction();
        }
    }
}
