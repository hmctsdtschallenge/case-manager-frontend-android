package com.dts.casemanagerfrontendandroid.ui.createtask;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.dts.casemanagerfrontendandroid.R;
import com.dts.casemanagerfrontendandroid.databinding.ActivityCreateTaskBinding;
import com.dts.casemanagerfrontendandroid.model.Task;
import com.dts.casemanagerfrontendandroid.ui.mainactivity.MainActivityViewModel;

public class CreateTaskActivity extends AppCompatActivity {
    private ActivityCreateTaskBinding binding;
    private CreateTaskClickHandler handler;
    private Task task;
    private final String TAG = "CreateTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        task = new Task();

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_create_task
        );

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handler = new CreateTaskClickHandler(task, this, viewModel);

        binding.setTask(task);
        binding.setClickHandler(handler);
    }
}