package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitapplitcation.Entities.User;
import com.example.fitapplitcation.Utils.PubDataBase;


public class UserProfileActivity extends AppCompatActivity {
Button homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        homeBtn=findViewById(R.id.home);

        // Fetch authenticated user's email
        //String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        SharedPreferences preferences = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        String userEmail = preferences.getString("email", "");
        // Fetch user data from Room database based on email using AsyncTask
        new FetchUserAsyncTask(this).execute(userEmail);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private class FetchUserAsyncTask extends AsyncTask<String, Void, User> {
        private Context context;

        // Constructor to receive context from the enclosing activity
        public FetchUserAsyncTask(Context context) {
            this.context = context;
        }
        @Override
        protected User doInBackground(String... params) {
            String userEmail = params[0];
            PubDataBase appDatabase = PubDataBase.getInstance(context);
            return appDatabase.userDao().getUserByEmail(userEmail);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            // Update TextViews with user details
            TextView nameTextView = findViewById(R.id.nameTextView);
            TextView mobileTextView = findViewById(R.id.mobile);
            TextView emailTextView = findViewById(R.id.email);
            TextView dobTextView = findViewById(R.id.dob);

            // Set TextViews with user details
            nameTextView.setText(user.getName());
            mobileTextView.setText(user.getMobile());
            emailTextView.setText(user.getEmail());
            dobTextView.setText(user.getDateOfBirth());
            // ... Set other TextViews with user details
        }
    }
}
