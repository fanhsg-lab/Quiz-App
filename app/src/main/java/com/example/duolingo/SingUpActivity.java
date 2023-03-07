package com.example.duolingo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpActivity extends AppCompatActivity {

    private EditText name,email,pass,confirmPass;
    private Button signUpB;
    private ImageView backB;
    private FirebaseAuth mAuth;
    private String emailStr,passStr, confirmPassStr, nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        name = findViewById(R.id.username);
        email = findViewById(R.id.emailID);
        confirmPass = findViewById(R.id.confirm_pass);
        pass = findViewById(R.id.password);
        signUpB = findViewById(R.id.signupB);
        backB = findViewById(R.id.backB);

        mAuth= FirebaseAuth.getInstance();

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()) {
                    singupNewUser();
                }

            }
        });
    }

    private boolean validate(){
        nameStr=name.getText().toString().trim();
        passStr=pass.getText().toString().trim();
        emailStr=email.getText().toString().trim();
        confirmPassStr=confirmPass.getText().toString().trim();

        if(nameStr.isEmpty())
        {
            name.setError("Enter Your Name");
            return false;
        }

        if(emailStr.isEmpty())
        {
            email.setError("Enter Email ID");
        }

        if(passStr.isEmpty())
        {
            pass.setError("Enter Password");
        }

        if(confirmPassStr.isEmpty())
        {
            confirmPass.setError("Enter Password");
        }

        if(passStr.compareTo(confirmPassStr)!=0)
        {
            Toast.makeText(SingUpActivity.this, "Passwords are not the same",Toast.LENGTH_SHORT).show();
            return false;
        }

    return true;

    }

    private void singupNewUser()
    {


        mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(SingUpActivity.this, "Sing Up Successfull ",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SingUpActivity.this,MainActivity.class);
                            startActivity(intent);
                            SingUpActivity.this.finish();


                        } else {
                            Log.e("edo", "createUserWithEmail:failure", task.getException());
                           Toast.makeText(SingUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}