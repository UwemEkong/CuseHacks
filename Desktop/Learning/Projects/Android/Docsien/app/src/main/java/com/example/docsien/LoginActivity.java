package com.example.docsien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = etUsername.getEditableText().toString();
                String enteredPassword = etPassword.getEditableText().toString();
                loginUser(enteredUsername, enteredPassword);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getEditableText().toString();
                String password = etPassword.getEditableText().toString();
                registerUser(username, password);
            }
        });
    }

    // Login user
    private void loginUser(String enteredUsername, String enteredPassword) {
        reference = FirebaseDatabase.getInstance().getReference("Patient");

        // Check if user exists
        Query checkUser = reference.orderByChild("username").equalTo(enteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Check if password matches
                    String passwordFromDB = snapshot.child(enteredUsername).child("password").getValue().toString();
                    if (enteredPassword.equals(passwordFromDB)) {
                        Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();

                        // Log user in
                        String medication = snapshot.child(enteredPassword).child("medication").getValue().toString();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("medication", medication);

                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                }
                else  {
                    Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Register username and password
    private void registerUser(String username, String password) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Patient");

        if (username.length() == 0 || password.length() == 0) {
            Toast.makeText(LoginActivity.this, "Enter username and password", Toast.LENGTH_SHORT).show();
        }
        else {
            Patient newPatient = new Patient(username, password, "amoxicillin");

            reference.child(username).setValue(newPatient);

            Toast.makeText(LoginActivity.this, "Account created! Please login.", Toast.LENGTH_SHORT).show();
        }
    }
}