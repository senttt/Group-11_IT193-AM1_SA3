package com.structor.appdev;

import android.os.Bundle;
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

public class AddDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        initializeComponents();
    }

    private void initializeComponents() {
        EditText input_username = findViewById(R.id.add_dog_name);
        EditText input_email = findViewById(R.id.add_breed);
        EditText input_age = findViewById(R.id.add_age);
        EditText input_sex = findViewById(R.id.add_gender);
        EditText input_breed = findViewById(R.id.add_breed);
        EditText input_birthdate = findViewById(R.id.add_date);
        EditText input_photonum = findViewById(R.id.add_photo);
        EditText input_description = findViewById(R.id.add_desc);
        Button btn_add = findViewById(R.id.form_buttonAdd);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        btn_add.setOnClickListener(view -> {
            String username = String.valueOf(input_username.getText());
            String email = String.valueOf(input_email.getText());
            int age = Integer.parseInt(input_age.getText().toString());
            String sex = String.valueOf(input_sex.getText());
            String breed = String.valueOf(input_breed.getText());
            String birthdate = String.valueOf(input_birthdate.getText());
            int photonum = Integer.parseInt(input_photonum.getText().toString());
            String description = String.valueOf(input_description.getText());

            User user = new User(username, email, age, birthdate, breed, sex, photonum, description);

            Call<User> call = userApi.save(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(AddDog.this, "Save successful", Toast.LENGTH_SHORT).show();
                    finish(); // Finish the activity after adding the user
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(AddDog.this, "Save Failed", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(AddDog.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                }
            });
        });
    }
}
