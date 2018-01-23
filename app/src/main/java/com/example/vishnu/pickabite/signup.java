package com.example.vishnu.pickabite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by vishnu on 21/1/18.
 */

public class signup extends AppCompatActivity {

    private EditText et1, et2, et3, et4, et5, et6;
    private Button bt1;
    private FirebaseAuth auth;
    TextView Txt1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        et1 = (EditText) findViewById(R.id.fullName);
        et2 = (EditText) findViewById(R.id.userEmailId);
        et3 = (EditText) findViewById(R.id.mobileNumber);
        et4 = (EditText) findViewById(R.id.location);
        et5 = (EditText) findViewById(R.id.password);
        et6 = (EditText) findViewById(R.id.confirmPassword);
        bt1 = (Button) findViewById(R.id.signUpBtn);
        Txt1 = (TextView)findViewById(R.id.already_user);
        auth = FirebaseAuth.getInstance();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = et1.getText().toString();
                String email = et2.getText().toString();
                String mobilenumber = et3.getText().toString();
                String location = et4.getText().toString();
              String password = et5.getText().toString().trim();
               String confirmpassword = et6.getText().toString().trim();
                if (TextUtils.isEmpty(fullname) && TextUtils.isEmpty(email) && TextUtils.isEmpty(location)&&TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(getApplicationContext(), "Improper Information", Toast.LENGTH_SHORT).show();

                }


             else if (!password.equals(confirmpassword)) {
                    Toast.makeText(getApplicationContext(), "The both passwords should be equal", Toast.LENGTH_LONG).show();

                }
               else if (password.length() < 6 && confirmpassword.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(signup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(signup.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(signup.this, MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                }

            }
        });
        Txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
