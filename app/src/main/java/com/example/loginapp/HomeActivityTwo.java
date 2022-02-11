package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivityTwo extends AppCompatActivity {

    private  String userID;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        textView = (TextView) findViewById(R.id.textView4);
        String myresult = getIntent().getExtras().getString("Result");
        textView.setText(myresult);

        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Save");
        userID = user.getUid();

        final TextView greetingtextView = (TextView) findViewById(R.id.greeting);
        final TextView emailText = (TextView) findViewById(R.id.emailAddress);

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String email = userProfile.email;

                    greetingtextView.setText("Welcome");
                    emailText.setText(email);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivityTwo.this, "Something Wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}