package com.example.todo.halper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskData implements InterfaceData {

    private SQLiteDatabase write;
    private SQLiteDatabase read;
    public TaskData(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        write = dbHelper.getWritableDatabase();
        read = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean save(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("name", task.getTaskName());

        try {
            write.insert(DbHelper.TABLE_TASK, null, cv);
            Log.i("INFO", " Task data save success ");

        } catch (Exception e) {
            Log.e("INFO", "Save data error " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("name", task.getTaskName());
        try {
            String[] args = {String.valueOf(task.getId())};
           write.update(DbHelper.TABLE_TASK, cv, "id=?", args);
            Log.i("INFO", " Task data update success ");

        } catch (Exception e) {
            Log.e("INFO", "update data error " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Task task) {
        try {
            String[] args = {String.valueOf(task.getId())};
            write.delete(DbHelper.TABLE_TASK, "id=?", args);
            Log.i("INFO", " Task data delete success ");

        } catch (Exception e) {
            Log.e("INFO", "delete data error " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Task> list() {
        List<Task> task = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABLE_TASK + " ;";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {
            Task tasks = new Task();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nameTask = c.getString(c.getColumnIndex("name"));

            tasks.setId(id);
            tasks.setTaskName(nameTask);

            task.add(tasks);
        }
        return task;
    }
}
