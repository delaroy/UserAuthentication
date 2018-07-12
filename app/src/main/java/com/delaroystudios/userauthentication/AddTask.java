package com.delaroystudios.userauthentication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.userauthentication.model.Message;
import com.delaroystudios.userauthentication.networking.api.services.UserService;
import com.delaroystudios.userauthentication.networking.generator.DataServiceGenerator;
import com.delaroystudios.userauthentication.utils.PreferenceUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.delaroystudios.userauthentication.helper.Convert.convertToTwelveHrs;
import static com.delaroystudios.userauthentication.helper.Convert.convertToTwentyfourHrs;
import static com.delaroystudios.userauthentication.utils.Constants.USER_BASE_URL;

/**
 * Created by delaroy on 7/11/18.
 */

public class AddTask extends AppCompatActivity implements  TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private EditText editTaskTitle,description;
    private Button submit;
    private TextView dateText, timeText;
    private Calendar calendar;
    private int year, month, hour, minute, day, yearReal, minuteReal;
    private String title;
    private String time;
    private String date;
    private ProgressDialog pDialog;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_layout);

        editTaskTitle = findViewById(R.id.editTaskTitle);
        description = findViewById(R.id.description);
        dateText = (TextView) findViewById(R.id.set_date);
        timeText = (TextView) findViewById(R.id.set_time);

        username = PreferenceUtils.getUsername(this);

        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        month++;

        date = day + "/" + month + "/" + year;
        time = hour + ":" + minute;


        submit = findViewById(R.id.submit);

        dateText.setText(date);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTask();
            }
        });

        initpDialog();
    }

    // On clicking Time picker
    public void setTime(View v){
            Calendar now = Calendar.getInstance();
            TimePickerDialog tpd = TimePickerDialog.newInstance(
                    this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    false
            );
            tpd.setThemeDark(false);
            tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    // On clicking Date picker
    public void setDate(View v){
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    // Obtain time from time picker
    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        hour = hourOfDay;
        minuteReal = minute;
        if (minute < 10) {
            time = hourOfDay + ":" + "0" + minuteReal;
        } else {
            time = hourOfDay + ":" + minuteReal;
        }
        try {
            timeText.setText(convertToTwelveHrs(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Obtain date from date picker
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        day = dayOfMonth;
        month = monthOfYear;
        yearReal = year;
        date = dayOfMonth + "/" + monthOfYear + "/" + yearReal;
        dateText.setText(date);
    }

    private void submitTask(){
      showpDialog();
      String taskTitle = editTaskTitle.getText().toString().trim();
      String taskDesc = description.getText().toString().trim();
      String taskTime = timeText.getText().toString();
      String taskDate = dateText.getText().toString();
      if (taskTitle.isEmpty()){
          editTaskTitle.setError("Please fill a task title");
      }else if (taskDesc.isEmpty()){
          description.setError("Please fill task description");
      }else{
          UserService taskService = DataServiceGenerator.createService(UserService.class, getApplication(), USER_BASE_URL);
          Call<Message> call = taskService.createTask(taskTitle, username, taskDate, taskTime, taskDesc);
          
          call.enqueue(new Callback<Message>() {
              @Override
              public void onResponse(Call<Message> call, Response<Message> response) {
                  hidepDialog();
                  if (response.isSuccessful()){
                      if (response.body() != null){
                          Message message = response.body();
                          Boolean error = message.getError();
                          String responseMessage = message.getMessage();

                          Toast.makeText(AddTask.this, responseMessage, Toast.LENGTH_SHORT).show();
                          emptyInputEditText();
                      }
                  }else{
                      Toast.makeText(AddTask.this, "unable to add record", Toast.LENGTH_SHORT).show();
                  }
              }

              @Override
              public void onFailure(Call<Message> call, Throwable t) {
                  hidepDialog();
                  Toast.makeText(AddTask.this, "unable to add task", Toast.LENGTH_SHORT).show();

              }
          });
      }
    }

    protected void initpDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(true);
    }


    protected void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }

    private void emptyInputEditText() {
        editTaskTitle.setText("");
        description.setText("");
    }



}
