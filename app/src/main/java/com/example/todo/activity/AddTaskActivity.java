package com.example.todo.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.halper.TaskData;
import com.example.todo.model.Task;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {
    private TextInputEditText editTask;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_actvty);

        editTask = findViewById(R.id.textTask);

        currentTask = (Task) getIntent().getSerializableExtra("taskSelection");

        if (currentTask != null) {
            editTask.setText(currentTask.getTaskName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveItem :

                TaskData taskData = new TaskData(getApplicationContext());

                if (currentTask != null) {
                    String taskName = editTask.getText().toString();
                    if (!taskName.isEmpty()) {
                        Task  task = new Task();
                        task.setTaskName(taskName);
                        task.setId(currentTask.getId());

                        if (taskData.update(task)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Task successfully update", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error update task", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else  {
                    String taskName = editTask.getText().toString();
                    if (!taskName.isEmpty()) {
                        Task task = new Task();
                        task.setTaskName(taskName);

                        if (taskData.save(task)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Task successfully saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error save task", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}