package com.example.bejaiago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bejaiago.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        binding.SingnupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.SingnupID.getText().toString();
                String password = binding.SingnupPassword.getText().toString();
                String confirmpassword = binding.SingnupConfirm.getText().toString();

                if (id.equals("") || password.equals("") || confirmpassword.equals("")) {
                    Toast.makeText(Signup.this, "All files are mandatory", Toast.LENGTH_SHORT).show();

                } else {
                    if (password.equals(confirmpassword)) {
                        boolean checkuserid = databaseHelper.Chekid(id);
                        if (checkuserid == false) {
                            boolean insert = databaseHelper.insertdata(id, password);
                            if (insert == true) {
                                Toast.makeText(Signup.this, "Signup succeded", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            } else
                                Toast.makeText(Signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(Signup.this, "Id already exists", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Signup.this, "Invalid password,try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.LoginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}