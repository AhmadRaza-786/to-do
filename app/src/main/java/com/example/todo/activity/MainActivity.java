package com.example.todo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.adapter.TaskAdapter;
import com.example.todo.halper.RecyclerItemClickListener;
import com.example.todo.halper.TaskData;
import com.example.todo.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter mTaskAdapter;
    private List<Task> mTaskList = new ArrayList<>();
    private Task taskSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addOnItemTouchListener(new
                RecyclerItemClickListener(getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Task taskSelection = mTaskList.get(position);

                        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                        intent.putExtra("taskSelection", taskSelection);

                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        taskSelection = mTaskList.get(position);

                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setCancelable(false);

                        dialog.setTitle("Confirm delete");
                        dialog.setMessage("Do you want to delete the task " + taskSelection.getTaskName() + " ?");

                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TaskData taskData = new TaskData(getApplicationContext());

                                if (taskData.delete(taskSelection)) {
                                    loadItemTask();
                                    Toast.makeText(getApplicationContext(), "Task delete successfully", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Error delete task", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        dialog.setNegativeButton("No", null);
                        dialog.create();
                        dialog.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }

                ));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
            }
        });
    }

   public void loadItemTask() {

      TaskData taskData = new TaskData(getApplicationContext());
      mTaskList = taskData.list();


       mTaskAdapter = new TaskAdapter(mTaskList);

       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setHasFixedSize(true);
       recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
       recyclerView.setAdapter(mTaskAdapter);
    }

    @Override
    protected void onStart() {
        loadItemTask();
        super.onStart();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}