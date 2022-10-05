package com.example.course_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class login_page extends AppCompatActivity {

    TextView newAccount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        newAccount = (TextView) findViewById(R.id.newAccount);

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_page = new Intent(login_page.this , sign_up_page.class);
                startActivity(signup_page);
            }
        });

    }
}