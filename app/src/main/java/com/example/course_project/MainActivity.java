package com.example.course_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void SignUp(View view) {

        Intent main2Signup = new Intent(this,sign_up_page.class);
        startActivity(main2Signup);
    }

    public void Sign_in(View view) {
        Intent m2login = new Intent(this , login_page.class);
        startActivity(m2login);
    }
}