package com.delaroystudios.userauthentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.delaroystudios.userauthentication.utils.PreferenceUtils;

/**
 * Created by delaroy on 6/26/18.
 */

public class DashboardActivity extends AppCompatActivity {
    private AppCompatTextView name, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String userEmail = PreferenceUtils.getEmail(this);
        String individualName = PreferenceUtils.getName(this);
        String userName = PreferenceUtils.getUsername(this);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);

        if (userEmail != null || !userEmail.isEmpty()){
            name.setText(individualName);
            username.setText(userName);
        }else{
            Toast.makeText(this, "empty values", Toast.LENGTH_SHORT).show();
        }


    }

}
