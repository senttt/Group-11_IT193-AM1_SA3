package com.structor.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TitlePage extends AppCompatActivity {

    private int account_type = -1;
    private String user;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_page);
        EditText log_user = findViewById(R.id.EditUser);
        EditText log_pass = findViewById(R.id.EditUserPassword);
        TextView log_hint = findViewById(R.id.LoginHint);

        Button btnClear = findViewById(R.id.ClearButton);
        btnClear.setOnClickListener(v -> {
            log_user.setText("");
            log_pass.setText("");
        });

        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(v -> {
            user = log_user.getText().toString();
            pass = log_pass.getText().toString();
            String message = user + pass;
            if (user.equals("admin") && pass.equals("admin")){
                account_type = 1;
                Intent intent = new Intent(TitlePage.this, MainActivity.class);
                intent.putExtra("account_type", account_type);
                startActivity(intent);
            }
            else if (user.equals("user") && pass.equals("user")){
                account_type = 0;
                Intent intent = new Intent(TitlePage.this, MainActivity.class);
                intent.putExtra("account_type", account_type);
                startActivity(intent);
            }
            else log_hint.setText(message);

        });
    }
}
