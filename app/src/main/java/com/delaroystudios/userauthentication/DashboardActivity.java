package com.delaroystudios.userauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.delaroystudios.userauthentication.adapter.TaskAdapter;
import com.delaroystudios.userauthentication.model.Task;
import com.delaroystudios.userauthentication.model.Tasks;
import com.delaroystudios.userauthentication.model.UserLogin;
import com.delaroystudios.userauthentication.networking.api.services.UserService;
import com.delaroystudios.userauthentication.networking.generator.DataServiceGenerator;
import com.delaroystudios.userauthentication.utils.PreferenceUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.delaroystudios.userauthentication.utils.Constants.USER_BASE_URL;

/**
 * Created by delaroy on 6/26/18.
 */

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private String userName;
    private RelativeLayout profileLoadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String userEmail = PreferenceUtils.getEmail(this);
        String individualName = PreferenceUtils.getName(this);
        userName = PreferenceUtils.getUsername(this);
        profileLoadingScreen = findViewById(R.id.profileLoadingScreen);

        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddTask.class);
                startActivity(intent);
            }
        });

        fetchTasks();
    }

    private void fetchTasks(){
        UserService fetchService = DataServiceGenerator.createService(UserService.class, getApplication(), USER_BASE_URL);
        Call<Tasks> call = fetchService.fetchTask(userName);

        call.enqueue(new Callback<Tasks>() {
            @Override
            public void onResponse(Call<Tasks> call, Response<Tasks> response) {
                profileLoadingScreen.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()){
                    if (response.body() != null){
                        List<Task> taskList = response.body().getTasks();
                        recyclerView.setAdapter(new TaskAdapter(getApplicationContext(), taskList));
                    }else{
                        Toast.makeText(DashboardActivity.this, "No records", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Tasks> call, Throwable t) {
                profileLoadingScreen.setVisibility(View.INVISIBLE);
                Toast.makeText(DashboardActivity.this, "No records", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
