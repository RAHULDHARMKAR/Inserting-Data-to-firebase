package com.example.firstassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText name, username, email, password, contact;
    Button insert;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        contact = findViewById(R.id.contact);
        insert = findViewById(R.id.insert);
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String NAME = name.getText().toString();
                final String USERNAME = username.getText().toString();
                final String EMAIL = email.getText().toString();
                final String PASSWORD = password.getText().toString();
                final String CONTACT = contact.getText().toString();
                String id = databaseReference.push().getKey();
                Model model = new Model(id, NAME, USERNAME, EMAIL, PASSWORD, CONTACT);
                databaseReference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
}