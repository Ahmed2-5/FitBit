package com.example.fitapplitcation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapplitcation.Entities.User;

import com.example.fitapplitcation.Utils.PubDataBase;
import com.google.android.material.textfield.TextInputEditText;


public class RegisterActivity extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword, editTextName, editTextMobile, editTextDOB;
    Button buttonReg;
    ProgressBar progressBar;
    TextView textView;
    //FirebaseAuth mAuth;
    private PubDataBase db;


    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is signed in (non-null) and update UI accordingly.

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextName = findViewById(R.id.name);
        editTextMobile = findViewById(R.id.mobile);
        editTextDOB = findViewById(R.id.dateOfBirth);

        buttonReg = findViewById(R.id.btn_register);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });
       /* buttonReg.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);

            String email, password,name,mobile,dob;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());
            name = String.valueOf(editTextName.getText());
            dob = String.valueOf(editTextDOB.getText());
            mobile = String.valueOf(editTextMobile.getText());




            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this, "Enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Register the user with Firebase Authentication
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            // User registered with Firebase Authentication
                            Toast.makeText(RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                            // Create a User object for Room database
                            User newUser = new User();
                            newUser.setPassword(password);
                            newUser.setEmail(email);
                            newUser.setName(name);
                            newUser.setMobile(mobile);
                            newUser.setDateOfBirth(dob);

                            // Insert the user into the Room database on a background thread
                            new AsyncTask<Void, Void, Void>() {
                                @Override
                                protected Void doInBackground(Void... voids) {
                                    db.userDao().insert(newUser);
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    // Log the product details
                                    Log.d("UserDetails", "email: " + email);
                                    Log.d("UserDetails", "password: " + password);
                                    Log.d("ProductDetails", "name: " + name);
                                    Log.d("ProductDetails", "DOB: " + dob);
                                    Log.d("ProductDetails", "mobile: " + mobile);
                                    Intent mainIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(mainIntent);

                                    // Close the activity or perform any other necessary actions
                                    finish();
                                }
                            }.execute();

                            // Continue with any other actions you need to perform after registration
                        } else {
                            // If sign-in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }*/
        buttonReg.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);

            String email, password, name, mobile, dob;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());
            name = String.valueOf(editTextName.getText());
            dob = String.valueOf(editTextDOB.getText());
            mobile = String.valueOf(editTextMobile.getText());

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this, "Enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a User object for Room database
            User newUser = new User();
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setMobile(mobile);
            newUser.setDateOfBirth(dob);

            // Insert the user into the Room database on a background thread
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    db = PubDataBase.getInstance(RegisterActivity.this);
                    db.userDao().insert(newUser);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    progressBar.setVisibility(View.GONE);
                    // Log the user details
                    Log.d("UserDetails", "email: " + email);
                    Log.d("UserDetails", "password: " + password);
                    Log.d("UserDetails", "name: " + name);
                    Log.d("UserDetails", "DOB: " + dob);
                    Log.d("UserDetails", "mobile: " + mobile);

                    Toast.makeText(RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                    Intent mainIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(mainIntent);

                    // Close the activity or perform any other necessary actions
                    finish();
                }
            }.execute();
        });

    }
}
