package com.example.loginapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AfterLoginFragment extends Fragment {

    TextView textView;
    private FirebaseUser user;
    private DatabaseReference reference;
    private  String myResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_after_login, container, false);

        textView = (TextView) rootView.findViewById(R.id.textView3);
        Bundle bundle = this.getArguments();
        if(bundle!=null)
        {
            myResult = bundle.getString("Result");
        }
        textView.setText("Welcome to "+ myResult);


        /*FirebaseAuth user = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        userID = user.getUid();

        textView.setText("Welcome to "+userID);*/

        /*reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                reference.setValue(userProfile);

                if(userProfile != null){
                    String emailID = userProfile.getEmail();

                    textView.setText("Welcome to "+ emailID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}