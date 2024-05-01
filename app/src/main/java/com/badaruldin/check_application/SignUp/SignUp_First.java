package com.badaruldin.check_application.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.badaruldin.check_application.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp_First extends AppCompatActivity {

    Button nextBtn, loginBtn;
    TextView backBtn;
    TextView createAccountTV;
    TextInputLayout fullname,username,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_first);
        fullname=findViewById(R.id.fullname_tiet);
        username=findViewById(R.id.username_tiet);
        email=findViewById(R.id.email_tiet);
        password=findViewById(R.id.password_tiet);

        //for not to show keyboard in beginning or without click
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void callNextScreen(View view) {
        nextBtn = findViewById(R.id.next_btn);
        loginBtn = findViewById(R.id.login_btn);
        backBtn = findViewById(R.id.back_press_tv);
        createAccountTV = findViewById(R.id.create_account_ET);

        if (! ValidFullName() | !ValidUserName() | !ValidEmail() |!ValidPassword()) {
            return;
        }

        Intent intent = new Intent(getApplicationContext(), SignUp_Two.class);
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(backBtn, "back_press_trasnition");
        pairs[1] = new Pair<View, String>(createAccountTV, "create_account_trasnition");
        pairs[2] = new Pair<View, String>(nextBtn, "next_btn_trasnition");
        pairs[3] = new Pair<View, String>(loginBtn, "login_btn_trasnition");

        String fnameS=fullname.getEditText().getText().toString().trim();
        String unameS=username.getEditText().getText().toString().trim();
        String emailS=email.getEditText().getText().toString().trim();
        String passwordS=password.getEditText().getText().toString().trim();

        intent.putExtra("fullname",fnameS);
        intent.putExtra("username",unameS);
        intent.putExtra("email",emailS);
        intent.putExtra("password",passwordS);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp_First.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private boolean ValidFullName(){
        String fname=fullname.getEditText().getText().toString().trim();
        String checkSpace="\\A\\w{1,15}\\z";
        if(fname.isEmpty()){
            fullname.setError("Please enter your full name");
            return false;
        }
        else if(fname.length()>15){
            fullname.setError("Name can not be exceeds to 15 characters");
            return false;
        }
        else if (!fname.matches(checkSpace)){
            fullname.setError("Can not allow Space");
            return false;
        }
        else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean ValidUserName(){
        String uname=username.getEditText().getText().toString().trim();
        String checkSpace="\\A\\w{1,15}\\z";
        if(uname.isEmpty()){
            username.setError("Please enter your full name");
            return false;
        }
        else if(uname.length()>20){
            username.setError("User Name can not be exceeds to 15 characters");
            return false;
        }
        else if (!uname.matches(checkSpace)){
            username.setError("Can not contain spaces");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean ValidEmail(){
        String emailT=email.getEditText().getText().toString().trim();
        String checkEmail="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailT.isEmpty()){
            email.setError("Please enter your Email address");
            return false;
        }
        else if(emailT.length()>25){
            email.setError("Email can not be exceeds to 25 characters");
            return false;
        }
        else if (!emailT.matches(checkEmail)){
            email.setError("Invalid Email");
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean ValidPassword(){
        String passwordS=password.getEditText().getText().toString().trim();
        String checkPassword="^"+
                "(?=.*[a-zA-Z])"+
                "(?=.*[@#%^&+=])"+
                "(?=\\S+$)"+
                ".{4,}"+
                "$";
                //"[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(passwordS.isEmpty()){
            password.setError("Please set your password");
            return false;
        }
        else if(passwordS.length()>25){
            password.setError("Password cannot be exceeds to 25 characters");
            return false;
        }
        else if (!passwordS.matches(checkPassword)){
            password.setError("Password atleast contains 4 letters with special some special character");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

}