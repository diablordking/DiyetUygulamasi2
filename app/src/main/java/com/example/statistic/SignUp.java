package com.example.statistic;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.R;
import com.example.model.Calorie;
import com.example.model.Dater;
import com.example.model.Report;
import com.example.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUp extends AppCompatActivity {

    private EditText inputEmail, inputPassword,inputage,inputsurname,inputweight ,inputheight;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private EditText editText;
    private DatabaseReference databaseReferenceCustomers;
    private Spinner     spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputage = (EditText) findViewById(R.id.age);
        inputweight = (EditText) findViewById(R.id.weight);
        inputheight = (EditText) findViewById(R.id.height);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    databaseReferenceCustomers = FirebaseDatabase.getInstance().getReference("users");
                                    String id = auth.getUid();
                                    User user = new User(id,inputEmail.getText().toString(),inputweight.getText().toString(),inputheight.getText().toString(),inputage.getText().toString());
                                    databaseReferenceCustomers.child(id).setValue(user);

                                    Date date = new Date();
                                    Date newDate = new Date(date.getTime() + (604800000L * 2) + (24 * 60 * 60));
                                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                                    final String stringdate = dt.format(newDate);
                                    long x = new Date().getTime();
                                    Calorie c1 = new Calorie(3);
                                    Dater d1 = new Dater(x,c1);
                                    databaseReferenceCustomers = FirebaseDatabase.getInstance().getReference("report");
                                    Report report = new Report(d1);
                                    databaseReferenceCustomers.child(auth.getUid()).setValue(report);



                                    startActivity(new Intent(SignUp.this, Statisticpage.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}