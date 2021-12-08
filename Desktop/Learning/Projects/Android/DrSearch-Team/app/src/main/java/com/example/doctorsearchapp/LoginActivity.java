package com.example.doctorsearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static String TAG = "LoginActivity";
    private Button etButton;
    private EditText etUsername;
    private EditText etPassword;
    private Button etSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = findViewById(R.id.usernameTxt);
        etPassword = findViewById(R.id.passTxt);
        etButton = findViewById(R.id.loginBtn);
        etSignup = findViewById(R.id.etSignup);
        etButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        // Sign up Button
        etSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                SignUp(username, password);
            }
        });
    }

    private void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user " + username);
        // TODO: Naviagte to the main activity if the user has signed in properly
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with Login", e);
                    return;
                }
                // TODO: Navigate to the main activity if the user has signed in properly
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SignUp(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.i(TAG, "Successful sign up");
                    Toast.makeText(LoginActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                    goMainActivity();
                } else {
                    // Sign up didn't succeed
                    Log.e(TAG, "Sign up failed!", e);
                    Toast.makeText(LoginActivity.this, "Sign up failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
