package com.tomaszow.hackathon.hackathon.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.fetcher.Constants;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLogInButton;
    private Button mCrateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setUI();
    }

    private void setUI() {
        mEmailEditText = (EditText) findViewById(R.id.activityLogIn_EditText_Email);
        mPasswordEditText = (EditText) findViewById(R.id.activityRegister_EditText_Password);
        mLogInButton = (Button) findViewById(R.id.activityLogIn_Button_LogIn);
        mCrateAccountButton = (Button) findViewById(R.id.activityLogIn_Button_Register);

        mLogInButton.setOnClickListener(this);
        mCrateAccountButton.setOnClickListener(this);
    }

    private void LogIn() {
        ParseUser.logInInBackground("joestevens", "secret123", new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    startHomeActivity();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });

    }

    private void startHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeIntent);
    }

    private void Register() {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(registerIntent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.activityLogIn_Button_LogIn:
                LogIn();
                break;
            case R.id.activityLogIn_Button_Register:
                Register();
                break;
        }
    }
}
