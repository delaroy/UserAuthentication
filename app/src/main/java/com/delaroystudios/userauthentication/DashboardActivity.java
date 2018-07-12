package com.delaroystudios.userauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.delaroystudios.userauthentication.utils.PreferenceUtils;

/**
 * Created by delaroy on 6/26/18.
 */

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String userEmail = PreferenceUtils.getEmail(this);
        String individualName = PreferenceUtils.getName(this);
        String userName = PreferenceUtils.getUsername(this);

        recyclerView = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddTask.class);
                startActivity(intent);
            }
        });


    }

}
