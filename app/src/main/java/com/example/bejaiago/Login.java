package com.example.bejaiago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bejaiago.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

ActivityLoginBinding binding;
DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper =new DatabaseHelper(this);
        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id =binding.loginId.getText().toString();
                String password=binding.loginPassword.getText().toString();
                if(id.equals("")||password.equals("")){
                    Toast.makeText(Login.this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                }else {
                    boolean checkedantifiant=databaseHelper.Checkidpassword(id,password);
                    if(checkedantifiant==true){
                        Toast.makeText(Login.this, "Login succeded!", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }else Toast.makeText(Login.this, "Invalid informations!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

    }
}