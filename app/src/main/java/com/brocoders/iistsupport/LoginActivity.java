package com.brocoders.iistsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.brocoders.iistsupport.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Dialog dialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String emailPattern = "[a-zA-Z0-9._-]+@indoreinstitute+\\.+com+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        // Initializations
        auth= FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        sharedPreferences = getSharedPreferences("iistSupportApp",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Logging you in!!");

        if(auth.getCurrentUser()!=null){
            verifyEmail(); // I created this method for reusability
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

                                    verifyEmail();
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

    protected void verifyEmail(){
        if(user.isEmailVerified()){
            showToast("Logged in succesfully");
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{

            if(!sharedPreferences.getString("verificationMail", "").equals("SENT")){
                user.sendEmailVerification();
                editor.putString("verificationMail", "SENT").commit();
            }
            dialog = new AlertDialog.Builder(LoginActivity.this)
                    .setIcon(R.drawable.ic_warning)
                    .setTitle("Verify email")
                    .setMessage("Check your email to verify account")
                    .setPositiveButton("Resend link", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            user.sendEmailVerification();
                            showToast("Email sent");    // LKS Defined
                            recreate();
                        }
                    }).setNegativeButton("Verified", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            verifyEmail();
                        }
                    }).show();

        }
    }

    protected void showToast(String text){
        Toast.makeText(LoginActivity.this, text+"", Toast.LENGTH_SHORT).show();
    }

}