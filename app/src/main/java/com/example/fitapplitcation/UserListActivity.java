package com.example.fitapplitcation;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapplitcation.Entities.Publication;
import com.example.fitapplitcation.Entities.User;
import com.example.fitapplitcation.Utils.PubDataBase;


import java.util.List;

public class UserListActivity extends AppCompatActivity implements AdapterListener {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private PubDataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(this);  // Initialization with listener
        recyclerView.setAdapter(userAdapter);

        loadUsers();
    }

    private void loadUsers() {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... voids) {
                // Fetch the list of users from the Room database
                return db.userDao().getAllUsers();
            }

            @Override
            protected void onPostExecute(List<User> userList) {
                // Update the UI with the fetched user list
                userAdapter.setUserList(userList);
                userAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void OnUpdate(Publication publication) {

    }

    @Override
    public void OnDelete(int idp, int post) {

    }

    @Override
    public void onDelete(String email, int position) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                // Delete the user from the Room database
                db.userDao().deleteByEmail(params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // Update the UI after deletion
                userAdapter.removeUser(position);
            }
        }.execute(email);
    }
}