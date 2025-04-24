package com.dts.casemanagerfrontendandroid.ui.updatetaskactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.dts.casemanagerfrontendandroid.R;
import com.dts.casemanagerfrontendandroid.databinding.ActivityUpdateTaskBinding;
import com.dts.casemanagerfrontendandroid.model.Task;
import com.dts.casemanagerfrontendandroid.ui.mainactivity.MainActivityViewModel;

public class UpdateTaskActivity extends AppCompatActivity {
    private ActivityUpdateTaskBinding binding;
    private UpdateTaskClickHandler handler;
    private Task task;
    private static final String TASK_KEY = "task";
    private final String TAG = "UpdateTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        task = getIntent().getParcelableExtra(TASK_KEY, Task.class);
        Log.i(TAG, "onCreate: task extracted from intent via getParcelableExtra: " + task);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_update_task
        );

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handler = new UpdateTaskClickHandler(task, this, viewModel);

        binding.setTask(task);

        binding.setClickHandler(handler);
    }
}