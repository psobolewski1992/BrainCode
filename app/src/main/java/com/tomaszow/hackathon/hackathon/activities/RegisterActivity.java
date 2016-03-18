package com.tomaszow.hackathon.hackathon.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.tomaszow.hackathon.hackathon.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserNameEditText;
    private EditText mUserFirstNameEditText;
    private EditText mUserLastNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mPhoneEditText;
    private Button mCreateAccountButton;
    private String mUsername, mFirstName, mLastName, mEmail, mPassword, mPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUI();
    }

    private void setUI() {
        mUserNameEditText = (EditText) findViewById(R.id.activityRegister_EditText_UserName);
        mUserFirstNameEditText = (EditText) findViewById(R.id.activityRegister_EditText_FirstName);
        mUserLastNameEditText = (EditText) findViewById(R.id.activityRegister_EditText_LastName);
        mEmailEditText = (EditText) findViewById(R.id.activityRegister_EditText_Email);
        mPasswordEditText = (EditText) findViewById(R.id.activityRegister_EditText_Password);
        mPhoneEditText = (EditText) findViewById(R.id.activityRegister_EditText_Phone);
        mCreateAccountButton = (Button) findViewById(R.id.activityRegister_Button_CreateAcctoun);
        mCreateAccountButton.setOnClickListener(this);
    }

    private void createNewAccount() {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        checkForm();;
        user.setUsername(mUsername);
        user.setPassword(mPassword);
        user.setEmail("email@example.com");
        // Set custom properties
        user.put("phone", "650-253-0000");
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

    private void checkForm() {
        mUsername = mUserNameEditText.getText().toString();
        mFirstName = mUserFirstNameEditText.getText().toString();
        mLastName = mUserLastNameEditText.getText().toString();
        mPassword = mPasswordEditText.getText().toString();
        mEmail = mEmailEditText.getText().toString();
        mPhone = mPasswordEditText.getText().toString();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.activityRegister_Button_CreateAcctoun:
                createNewAccount();
                break;
        }
    }
}
