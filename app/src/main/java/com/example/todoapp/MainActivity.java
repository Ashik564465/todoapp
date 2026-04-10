package com.example.todoapp;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    RecyclerView recyclerView;

    ArrayList<Task> taskList;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        taskList = SharedPrefManager.getTasks(this);

        adapter = new TaskAdapter(this, taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String taskText = etTask.getText().toString().trim();

            if (!taskText.isEmpty()) {
                taskList.add(new Task(taskText, false));
                adapter.notifyDataSetChanged();
                SharedPrefManager.saveTasks(this, taskList);
                etTask.setText("");
            } else {
                Toast.makeText(this, "Enter a task", Toast.LENGTH_SHORT).show();
            }
        });
    }
}