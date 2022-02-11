package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText inputEmail1,inputPassword;
    Button loginButton;
    String emailPattern = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    GoogleSignInClient mGoogleSignInClient;

    ImageView btnGoogle;
    ImageView btnFacebook;
    AfterLoginFragment afterLoginFragment;
    LoginAlreadyFragment loginAlreadyFragment;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private  boolean isLoggedIn = false;

    TextView createnewAccount;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail1 =(EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.buttonLogin);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = mUser.getUid();
        afterLoginFragment = new AfterLoginFragment();
        loginAlreadyFragment = new LoginAlreadyFragment();

        Bundle bundle = new Bundle();
        bundle.putString("Result",userId);
        AfterLoginFragment afterLoginFragment = new AfterLoginFragment();
        afterLoginFragment.setArguments(bundle);


        btnGoogle = findViewById(R.id.buttonGoogle);
        btnFacebook = findViewById(R.id.buttonFacebook);

        createnewAccount = findViewById(R.id.createNewAccount);
        createnewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
                addFragment(MainActivity.this,afterLoginFragment);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GoogleSignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FacebookAuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        if(!isLoggedIn){
            //Toast.makeText(MainActivity.this, "Already Logged in", Toast.LENGTH_SHORT).show();
            addFragment(MainActivity.this,loginAlreadyFragment);


        }

    }


    public void performLogin() {
        String email = inputEmail1.getText().toString();
        String password = inputPassword.getText().toString();

        if (!email.matches(emailPattern)) {
            inputEmail1.setError("Enter Context Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("Enter proper password");
        } else {
            progressDialog.setMessage("Please wait while Login");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                         sendUserDetailsToFragment();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserDetailsToFragment() {

        Bundle bundle = new Bundle();
        bundle.putString("Result",userId);
        AfterLoginFragment afterLoginFragment = new AfterLoginFragment();
        afterLoginFragment.setArguments(bundle);
    }

    public void addFragment(Activity activity,Fragment fragment)
    {
        FragmentTransaction transaction = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }
}