package com.dts.casemanagerfrontendandroid.ui.mainactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dts.casemanagerfrontendandroid.R;
import com.dts.casemanagerfrontendandroid.databinding.ActivityMainBinding;
import com.dts.casemanagerfrontendandroid.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Task> tasks;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main
        );

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        getAllTasks();
    }

    private void getAllTasks() {
        mainActivityViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> stockItems) {
                MainActivity.this.tasks = (ArrayList<Task>) stockItems;
                displayTasksInRecyclerView();
            }
        });
    }

    private void displayTasksInRecyclerView() {
        recyclerView = activityMainBinding.recyclerView;
        taskAdapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(taskAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

    }
}