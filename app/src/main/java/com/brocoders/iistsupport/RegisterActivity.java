package com.brocoders.iistsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.brocoders.iistsupport.Models.Users;
import com.brocoders.iistsupport.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ProgressDialog progressDialog;
    private static final String emailPattern = "[a-zA-Z]+\\.+[a-zA-Z0-9._-]+[0-9]+@indoreinstitute+\\.+com+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        progressDialog=new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're Creating your Account");

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.etName.getText().toString().trim();
                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                binding.nameContainer.setError(null);
                binding.emailContainer.setError(null);
                binding.passContainer.setError(null);
                if(name.isEmpty()){
                    binding.nameContainer.setError("Name cannot be empty");
                    return;
                }

                if(!email.matches(emailPattern)){
                    binding.emailContainer.setError("Enter a valid Email");
                    return;
                }
                if(email.isEmpty()){
                    binding.emailContainer.setError("Email cannot be empty");
                    return;
                }

                if(password.isEmpty()){
                    binding.passContainer.setError("Password cannot be empty");
                    return;
                }

                if(password.length()<6){
                    binding.passContainer.setError("Password must contain minimum 6 characters");
                    return;
                }
                progressDialog.show();

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Users user = new Users(name, email, password);

                            String id = task.getResult().getUser().getUid();
                            database.getReference("User Data").child(id).setValue(user);

                            Toast.makeText(RegisterActivity.this,"User Created Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}