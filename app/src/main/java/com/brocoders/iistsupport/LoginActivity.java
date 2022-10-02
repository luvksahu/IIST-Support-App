package com.brocoders.iistsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import com.brocoders.iistsupport.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;
    private static final String emailPattern = "[a-zA-Z0-9._-]+@indoreinstitute+\\.+com+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        auth= FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Logged in to your Account");

        if(auth.getCurrentUser()!=null){
            if(user.isEmailVerified()){
                Toast.makeText(LoginActivity.this, "Logged in succesfully",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else{
                user.sendEmailVerification();
                Toast.makeText(LoginActivity.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
            }
        }

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                if(email.isEmpty()){
                    binding.etEmail.setError("Enter Email");
                    return;
                }

//                if(!email.matches(emailPattern)){
//                    binding.etEmail.setError("Enter a valid Email");
//                    return;
//                }
                if(password.isEmpty()){
                    binding.etPassword.setError("Enter Password");
                    return;
                }

                progressDialog.show();

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){

                                    if(user.isEmailVerified()){
                                        Toast.makeText(LoginActivity.this, "Logged in succesfully",Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        user.sendEmailVerification();
                                        Toast.makeText(LoginActivity.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}