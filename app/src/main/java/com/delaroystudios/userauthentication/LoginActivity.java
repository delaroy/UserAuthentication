package com.delaroystudios.userauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.delaroystudios.userauthentication.helper.InputValidation;
import com.delaroystudios.userauthentication.model.Message;
import com.delaroystudios.userauthentication.model.UserLogin;
import com.delaroystudios.userauthentication.networking.api.services.UserService;
import com.delaroystudios.userauthentication.networking.generator.DataServiceGenerator;
import com.delaroystudios.userauthentication.utils.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.delaroystudios.userauthentication.utils.Constants.USER_BASE_URL;

/**
 * Created by delaroy on 6/26/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
        initpDialog();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextUsername = (TextInputEditText) findViewById(R.id.textInputEditTextUsername);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyData();
                break;
            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyData() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.error_message_email))) {
            return;
        }else if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }else{
            String email = textInputEditTextUsername.getText().toString().trim();
            String password = textInputEditTextPassword.getText().toString().trim();

            login(email, password);
        }
    }

    private void login(String email, String password){
        showpDialog();
        UserService loginService = DataServiceGenerator.createService(UserService.class, getApplication(), USER_BASE_URL);
        Call<UserLogin> call = loginService.userLogin(email, password);

        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if (response.isSuccessful()){
                    hidepDialog();
                    if (response.body() != null){
                        UserLogin message = response.body();

                        String name = message.getName();
                        String email = message.getEmail();
                        String username = message.getUsername();
                        boolean error = message.getError();

                        if (error == Boolean.FALSE){
                            PreferenceUtils.saveName(name, getApplicationContext());
                            PreferenceUtils.saveEmail(email, getApplicationContext());
                            PreferenceUtils.saveUsername(username, getApplicationContext());

                            Toast.makeText(getApplicationContext(), "user authenticated successfully " + email, Toast.LENGTH_SHORT)
                                    .show();
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Invalid username or password " + email, Toast.LENGTH_SHORT)
                                    .show();
                            emptyInputEditText();
                        }

                    }
                }else {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(), "error user", Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                hidepDialog();
                Toast.makeText(getApplicationContext(), "error registering user " + t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
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

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextUsername.setText(null);
        textInputEditTextPassword.setText(null);
    }

}
