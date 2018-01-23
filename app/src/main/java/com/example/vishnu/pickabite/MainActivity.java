package com.example.vishnu.pickabite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    TextView txt;
    Button bt1,bt2;
    EditText email,password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView)findViewById(R.id.signup);
        email=(EditText) findViewById(R.id.emaillogin);
        bt2=(Button)findViewById(R.id.login);
        password=(EditText)findViewById(R.id.loginpass);
        auth = FirebaseAuth.getInstance();
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });
        bt1 = (Button)findViewById(R.id.signups);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                if(TextUtils.isEmpty(Email)&&TextUtils.isEmpty(Password))
                {
                    Toast.makeText(getApplicationContext(),"Please Fill all the Details",Toast.LENGTH_LONG).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.

                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                            Toast.makeText(getApplicationContext(),"Password should contain more than characters",Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(MainActivity.this,"Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        /*Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                        startActivity(intent);*/
                                        Toast.makeText(getApplicationContext(),"succesfull login",Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }

}
