package com.dts.casemanagerfrontendandroid.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.dts.casemanagerfrontendandroid.ui.createtask.CreateTaskActivity;

public class MainActivityClickHandler {
    private Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onCreateBtnClick(View view) {
        Intent intent = new Intent(view.getContext(), CreateTaskActivity.class);
        context.startActivity(intent);
    }
}
