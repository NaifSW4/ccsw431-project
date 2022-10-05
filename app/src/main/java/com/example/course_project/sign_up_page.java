

package com.example.course_project;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up_page extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email ;
    private EditText fName ;
    private EditText lName ;
    private EditText password ;
    private EditText ConfirmPassword ;
    private ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        email = (EditText) findViewById(R.id.email);
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        password = (EditText) findViewById(R.id.password);
        ConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


    }


    private void registerUser() {
        String userEmail = email.getText().toString().trim();
        String firstName = fName.getText().toString().trim();
        String lastName = lName.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String C_Password = ConfirmPassword.getText().toString().trim();

        if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Please enter valid email ! ");
            email.requestFocus();
            return;
        }
        if(firstName.isEmpty()) {
            fName.setError("First name is required ! ");
            fName.requestFocus();
            return;
        }
        if(lastName.isEmpty()) {
            lName.setError("Last name is required ! ");
            lName.requestFocus();
            return;
        }
        if(userPassword.isEmpty()) {
            password.setError("Password is required ! ");
            password.requestFocus();
            return;
        }
        if(userPassword.length() < 6 ) {
            password.setError("minimum password length should be 6 characters ! ");
            password.requestFocus();
            return;
        }
        if(!C_Password.equalsIgnoreCase(userPassword)) {
            ConfirmPassword.setError("The password is not confirm ! ");
            ConfirmPassword.requestFocus();
            return;
        }


    }

    public void Register(View view) {
        String userEmail = email.getText().toString().trim();
        String firstName = fName.getText().toString().trim();
        String lastName = lName.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String C_Password = ConfirmPassword.getText().toString().trim();

        if(userEmail.isEmpty()) {
            email.setError("Email is required !");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Please enter valid email ! ");
            email.requestFocus();
            return;
        }
        if(firstName.isEmpty()) {
            fName.setError("First name is required ! ");
            fName.requestFocus();
            return;
        }
        if(lastName.isEmpty()) {
            lName.setError("Last name is required ! ");
            lName.requestFocus();
            return;
        }
        if(userPassword.isEmpty()) {
            password.setError("Password is required ! ");
            password.requestFocus();
            return;
        }
        if(userPassword.length() < 6 ) {
            password.setError("minimum password length should be 6 characters ! ");
            password.requestFocus();
            return;
        }
        if(!C_Password.equalsIgnoreCase(userPassword)) {
            ConfirmPassword.setError("The password is not confirm ! ");
            ConfirmPassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(userEmail , userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(userEmail , firstName , lastName , userPassword);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(sign_up_page.this , "User has been registered successfully !" , Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(sign_up_page.this , "Failed to register ! Try again !" , Toast.LENGTH_LONG).show();
                                                        //progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(sign_up_page.this , "Failed to register ! Try again !" , Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });



    }
}
