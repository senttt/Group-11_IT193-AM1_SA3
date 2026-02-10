package com.structor.appdev;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.structor.appdev.model.User;
import com.structor.appdev.retrofit.RetrofitService;
import com.structor.appdev.retrofit.UserApi;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private Button btnUpdate;
    private Button btnRemove;
    private Button btnAdopt;
    private User user;
    private UserApi userApi;
    private EditText editTextAge;
    private EditText editTextSex;
    private EditText editTextBreed;
    private EditText editTextBirthdate;
    private EditText editTextPhotonum;
    private EditText editTextDescription;
    private int account_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        account_type = getIntent().getIntExtra("account_type", 0);
        // Retrieve the user object passed from MainActivity
        user = getIntent().getParcelableExtra("user");
        btnUpdate = findViewById(R.id.btn_update);
        btnRemove = findViewById(R.id.btn_remove);
        btnAdopt = findViewById(R.id.btn_adopt);

        if(account_type != 1) {
            btnRemove.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnRemove.setVisibility(View.GONE);
            btnRemove.setVisibility(View.GONE);
        }

        if (user != null) {
            // Initialize UI elements
            editTextUsername = findViewById(R.id.edit_dog_name);
            editTextEmail = findViewById(R.id.edit_breed);
            editTextAge = findViewById(R.id.edit_age);
            editTextSex = findViewById(R.id.edit_gender);
            editTextBreed = findViewById(R.id.edit_breed);
            editTextBirthdate = findViewById(R.id.edit_birth);

            editTextDescription = findViewById(R.id.edit_desc);

            // Display user information
            editTextUsername.setText(user.getUsername());
            editTextEmail.setText(user.getEmail());
            editTextAge.setText(String.valueOf(user.getAge())); // Convert int to String
            editTextSex.setText(user.getSex());
            editTextBreed.setText(user.getBreed());
            editTextBirthdate.setText(user.getBirthdate());

            editTextDescription.setText(user.getDescription());

            // Initialize RetrofitService and UserApi
            RetrofitService retrofitService = new RetrofitService();
            userApi = retrofitService.getRetrofit().create(UserApi.class);
            btnAdopt.setOnClickListener(view -> {
                Toast.makeText(EditUserActivity.this,"Sending Adoption Request.",Toast.LENGTH_LONG).show();
                Toast.makeText(EditUserActivity.this, "Adoption Request Sent!",Toast.LENGTH_LONG).show();
            });

            // Set onClickListener for Update button
            btnUpdate.setOnClickListener(view -> {
                // Update user information
                user.setUsername(editTextUsername.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setAge(Integer.parseInt(editTextAge.getText().toString())); // Parse String to int
                user.setSex(editTextSex.getText().toString());
                user.setBreed(editTextBreed.getText().toString());
                user.setBirthdate(editTextBirthdate.getText().toString());

                user.setDescription(editTextDescription.getText().toString());

                Call<User> call = userApi.updateUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EditUserActivity.this, "User updated successfully", Toast.LENGTH_SHORT).show();
                            finish(); // Close activity after successful update
                        } else {
                            Toast.makeText(EditUserActivity.this, "Failed to update user", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(EditUserActivity.this, "Failed to update user: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Logger.getLogger(EditUserActivity.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                    }
                });
            });

            // Set onClickListener for Remove button
            btnRemove.setOnClickListener(view -> {
                Call<Void> call = userApi.deleteUser(user.getId());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EditUserActivity.this, "User removed successfully", Toast.LENGTH_SHORT).show();
                            finish(); // Close activity after successful removal
                        } else {
                            Toast.makeText(EditUserActivity.this, "Failed to remove user", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditUserActivity.this, "Failed to remove user: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Logger.getLogger(EditUserActivity.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                    }
                });
            });
        } else {
            Toast.makeText(this, "User object not found", Toast.LENGTH_SHORT).show();
            finish(); // Finish activity if user object not found
        }
    }
}
