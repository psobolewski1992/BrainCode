package com.tomaszow.hackathon.hackathon.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.fetcher.Constants;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserNameEditText;
    private EditText mUserFirstNameEditText;
    private EditText mUserLastNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mPhoneEditText;
    private Button mCreateAccountButton;
    private String mUsername, mFirstName, mLastName, mEmail, mPassword, mPhone;

    public static final int SELECT_PHOTO  = 100;
    private ImageView mAvatarImageView;


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
        mAvatarImageView = (ImageView) findViewById(R.id.activityRegister_ImageView_Avatar);
        mAvatarImageView.setOnClickListener(this);
    }

    private void createNewAccount() {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        checkForm();
        user.setUsername(mUsername);
        user.setPassword(mPassword);
        user.setEmail(mEmail);
        // Set custom properties
        user.put("phone", mPhone);
        user.put("firstName", mFirstName);
        user.put("lastName", mLastName);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    LogIn();
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
        mPhone = mPhoneEditText.getText().toString();
    }

    private void LogIn() {
        startHomeActivity();
    }

    private void startHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeIntent);
    }

    private void lunchPhotoPickerIntet() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        mAvatarImageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.activityRegister_Button_CreateAcctoun:
                createNewAccount();
                break;
            case R.id.activityRegister_ImageView_Avatar:
                lunchPhotoPickerIntet();
                break;
        }
    }
}
