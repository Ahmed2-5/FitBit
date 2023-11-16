package com.example.fitapplitcation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitapplitcation.Entities.User;
import com.example.fitapplitcation.Utils.PubDataBase;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLog;
    ProgressBar progressBar;
    TextView textView;
    //FirebaseAuth mAuth;
    TextView forgetPassword;

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is signed in (non-null) and update UI accordingly.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLog = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.registerNow);
        forgetPassword = findViewById(R.id.forgotPass);
        //FirebaseApp.initializeApp(this);



        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Forgot Password" button click
                showForgotPasswordDialog();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }

        });
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Execute login in a background thread or AsyncTask
                new LoginAsyncTask(LoginActivity.this).execute(email, password);
            }
        });

    }
    private class LoginAsyncTask extends AsyncTask<String, Void, User> {
        private Context context; // Add a context variable
        private String email;
        private String password;

        // Constructor to receive context from the enclosing activity
        public LoginAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected User doInBackground(String... params) {
            email = params[0];
            password = params[1];

            // Pass the correct context here
            PubDataBase appDatabase = PubDataBase.getInstance(context);
            return appDatabase.userDao().getUserByEmailAndPassword(email, password);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            if (user != null) {
                Toast.makeText(context, "Authentication Success.", Toast.LENGTH_SHORT).show();
                saveLoginCreds(email, password);

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else {
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showForgotPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Password");
        final View forgotPasswordLayout = getLayoutInflater().inflate(R.layout.dialog_forgot_password, null);
        builder.setView(forgotPasswordLayout);
        final TextInputEditText emailEditText = forgotPasswordLayout.findViewById(R.id.editTextEmail);

        builder.setPositiveButton("Reset Password", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = emailEditText.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Password reset email sent. Check your email.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Failed to send reset email. Check your email.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void saveLoginCreds(String email, String password) {
        SharedPreferences preferences = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }


}
