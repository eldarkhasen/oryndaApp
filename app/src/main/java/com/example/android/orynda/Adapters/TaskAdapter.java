package com.example.android.orynda.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.orynda.DB.TaskContract;
import com.example.android.orynda.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by админ on 22.07.2017.
 */

public class TaskAdapter  extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private Context mContext;
    // TODO (1) Replace the mCount with a Cursor field called mCursor
    private Cursor mCursor;

    public TaskAdapter(Context context, Cursor cursor){
        this.mContext = context;
        // TODO (3) Set the local mCursor to be equal to cursor
        mCursor = cursor;
    }

    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.task_row, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.TaskViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)) {
            return;
        }
        Long id = mCursor.getLong(mCursor.getColumnIndex(TaskContract.TaskEntity._ID));
        String taskTitle = mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_TITLE));
        String deadline = mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskEntity.COLUMN_TASK_DEADLINE));
        String strLong = "";

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = new java.util.Date();
            Date date2 = sdf.parse(deadline);
            long diff = date2.getTime() - date1.getTime();
            long lol = Math.round(diff/1000/60/60/24d);

            if(lol!=0&&lol>0){
                strLong = lol+" күн қалды";
            }else{
                long hour = Math.round(diff/1000/60/60d);
                if(hour!=0&&hour>0){
                    strLong = hour+" cағат қалды";
                }else{
                    long min = Math.round(diff/1000/60d);
                    strLong = min+" минут қалды";
                }
            }

            Log.e("TEST", strLong);
        }catch(Exception e){
            Log.e("TEST", "Exception", e);
        }
        holder.taskTitle.setText(taskTitle);
        holder.deadline.setText(strLong);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor cursor) {
        if (mCursor == null) {
            mCursor.close();
        }
        mCursor = cursor;
        this.notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView taskTitle;
        TextView deadline;
        public TaskViewHolder(View itemView) {
            super(itemView);
            taskTitle = (TextView) itemView.findViewById(R.id.task_title);
            deadline = (TextView) itemView.findViewById(R.id.task_deadline);
        }
    }
}
