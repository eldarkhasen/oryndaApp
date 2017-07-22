package com.example.android.orynda.DB;

import android.provider.BaseColumns;

/**
 * Created by админ on 22.07.2017.
 */

public class TaskContract {
    public static class TaskEntity implements BaseColumns {
        public static final String TABLE_NAME = "Tasks";
        public static final String COLUMN_TASK_TITLE = "title";
        public static final String COLUMN_TASK_DESC = "description";
        public static final String COLUMN_TASK_DEADLINE = "deadline";
        public static final String COLUMN_TASK_REWARD = "reward";
        public static final String COLUMN_TASK_PUNISHMENT = "punishment";
        public static final String COLUMN_TASK_REMINDER = "reminder";
        public static final String COLUMN_TASK_COMPLETED = "completed";
        public static final String COLUMN_TASK_SUCCESS = "success";
    }
}
