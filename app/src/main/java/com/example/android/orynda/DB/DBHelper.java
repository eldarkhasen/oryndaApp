package com.example.android.orynda.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by админ on 22.07.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "toDoList.db";
    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table to hold waitlist data
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + TaskContract.TaskEntity.TABLE_NAME + " (" +
                TaskContract.TaskEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract.TaskEntity.COLUMN_TASK_TITLE + " TEXT NOT NULL, " +
                TaskContract.TaskEntity.COLUMN_TASK_DESC + " TEXT NOT NULL, " +
                TaskContract.TaskEntity.COLUMN_TASK_DEADLINE + " TEXT NOT NULL, " +
                TaskContract.TaskEntity.COLUMN_TASK_REWARD + " TEXT NOT NULL, " +
                TaskContract.TaskEntity.COLUMN_TASK_PUNISHMENT + " TEXT NOT NULL, " +
                TaskContract.TaskEntity.COLUMN_TASK_REMINDER + " INTEGER DEFAULT 0, " +
                TaskContract.TaskEntity.COLUMN_TASK_COMPLETED + " INTEGER DEFAULT 0, " +
                TaskContract.TaskEntity.COLUMN_TASK_SUCCESS + " INTEGER DEFAULT 0 " +" ); ";
        db.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskContract.TaskEntity.TABLE_NAME);
        onCreate(db);
    }
}
