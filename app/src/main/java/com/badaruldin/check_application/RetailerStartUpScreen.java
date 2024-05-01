package com.badaruldin.check_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.badaruldin.check_application.SignUp.SignUp_First;

public class RetailerStartUpScreen extends AppCompatActivity {
    TextView loginBtn,signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retailer_start_up_screen);
        loginBtn=findViewById(R.id.login_textview);
        signupBtn=findViewById(R.id.signup_TextView);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SignUp_First.class);
                Pair[] pairs=new Pair[4];
                pairs[0]=new Pair<View,String>(signupBtn,"back_press_trasnition");
                pairs[1]=new Pair<View,String>(signupBtn,"create_account_trasnition");
                pairs[2]=new Pair<View,String>(signupBtn,"next_btn_trasnition");
                pairs[3]=new Pair<View,String>(signupBtn,"login_btn_trasnition");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(RetailerStartUpScreen.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Retailer_login.class);
                Pair[] pair=new Pair[1];//it is to pair the values and then used it in animation
                loginBtn=findViewById(R.id.login_textview);
                pair[0]=new Pair<View,String>(loginBtn,"login_animation");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(RetailerStartUpScreen.this,pair);
                startActivity(intent,options.toBundle());
            }
        });
    }
}