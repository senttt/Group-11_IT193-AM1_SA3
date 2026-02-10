package com.structor.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.structor.appdev.model.CustomAdapter;
import com.structor.appdev.model.User;
import com.structor.appdev.retrofit.RetrofitService;
import com.structor.appdev.retrofit.UserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listViewUsers;
    private ArrayAdapter<User> userArrayAdapter;
    private CustomAdapter customAdapter;
    private List<User> userList;
    private User user;
    private Button btnAddDog; // Add button reference
    private Button btnLogout;
    private int account_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewUsers = findViewById(R.id.list_view_users);
        account_type = getIntent().getIntExtra("account_type", 0);

        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> logout());

        btnAddDog = findViewById(R.id.btn_add_dog); // Initialize the button
        btnAddDog.setOnClickListener(v -> openAddDogActivity()); // Set OnClickListener

        if (account_type != 1){
            btnAddDog.setEnabled(false);
            btnAddDog.setVisibility(View.GONE);
        }

        listViewUsers.setOnItemClickListener((parent, view, position, id) -> {
            User selectedUser = userList.get(position);
            // Start EditUserActivity with selected user
            Intent intent = new Intent(MainActivity.this, EditUserActivity.class);
            intent.putExtra("user", selectedUser);
            intent.putExtra("account_type",account_type);
            startActivity(intent);
        });

        // Initialize ArrayAdapter
        userArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listViewUsers.setAdapter(userArrayAdapter);

        // Fetch and populate users
        fetchUsers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchUsers(); // Refresh user list onResume
    }

    private void fetchUsers() {
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        Call<List<User>> call = userApi.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userList = response.body();
                    userArrayAdapter.clear(); // Clear existing items
                    userArrayAdapter.addAll(userList); // Add new items
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to open AddDogActivity
    private void openAddDogActivity() {
        Intent intent = new Intent(MainActivity.this, AddDog.class);
        startActivity(intent);
    }

    //Method to logout back to Title
    private void logout() {
        Intent intent = new Intent(MainActivity.this, TitlePage.class);
        startActivity(intent);
    }
}
