package com.tomaszow.hackathon.hackathon.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.application.BrainCodeApplication;
import com.tomaszow.hackathon.hackathon.fetcher.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //BrainCodeApplication brainCodeApplication = new BrainCodeApplication();

        //Parse.initialize(this, Constants.AppID, Constants.ClientKey);
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                        .applicationId(Constants.AppID)
                        .clientKey(Constants.ClientKey)
                        .server("https://parseapi.back4app.com")
                        .build()
        );

        //ParseObject test2 = new ParseObject("Measurement");

        /*
        ParseQuery query = new ParseQuery("Measurement");
        query.getInBackground("xWMyZ4YEGZ", new GetCallback() {
            public void done(ParseObject gameScore, ParseException e) {
                if (e == null) {
                    // gameScore will be your game score
                    int score = gameScore.getInt("score");
                    String playerName = gameScore.getString("playerName");
                    boolean cheatMode = gameScore.getBoolean("cheatMode");
                } else {
                    // something went wrong
                }
            }
        });
        */

        /*
        // Create the ParseUser
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername("joestevens");
        user.setPassword("secret123");
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
        */
        ParseUser.logInInBackground("joestevens", "secret123", new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    Log.d("TAG", "Hurra");
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
