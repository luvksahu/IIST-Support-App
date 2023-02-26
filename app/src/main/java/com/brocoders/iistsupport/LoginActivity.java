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

    private ActivityLoginBinding binding;
    private ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Dialog dialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String emailPattern = "[a-zA-Z]+\\.+[a-zA-Z0-9._-]+[0-9]+@indoreinstitute+\\.+com+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        // Initializations
        auth= FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("iistSupportApp",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Logging you in!!");


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                binding.emailContainer.setError(null);
                binding.passContainer.setError(null);
                if(email.isEmpty()){
                    binding.emailContainer.setError("Enter Email");
                    return;
                }

                if(!email.matches(emailPattern)){
                    binding.emailContainer.setError("Enter a valid Email");
                    return;
                }
                if(password.isEmpty()){
                    binding.passContainer.setError("Enter Password");
                    return;
                }

                progressDialog.show();

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    user = auth.getCurrentUser();
                                    verifyEmail();   // LKS Defined
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
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        binding.tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString().trim();
                binding.emailContainer.setError(null);
                if(email.isEmpty()){
                    binding.emailContainer.setError("Enter Email");
                    return;
                }
                if(!email.matches(emailPattern)){
                    binding.emailContainer.setError("Enter a valid Email");
                    return;
                }
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    showToast("We have sent you a mail to reset your password!");
                                }
                                else{
                                    showToast("Failed to send reset email!");
                                }
                            }
                        });
            }
        });

    }

    protected void verifyEmail(){
        if(user.isEmailVerified()){
            showToast("Logged in successfully");
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
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
                            dialog.dismiss();
                        }
                    }).show();

        }
    }

    protected void showToast(String text){
        Toast.makeText(LoginActivity.this, text+"", Toast.LENGTH_LONG).show();
    }

}