package com.badaruldin.check_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.badaruldin.check_application.ForgetPassword.ForgetPasswordEmailActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Retailer_login extends AppCompatActivity {

    TextInputLayout phoneNo, password;
    CountryCodePicker countryCodePicker;
    Button loginBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retailer_login);

        phoneNo = findViewById(R.id.phone_no);
        loginBtn = findViewById(R.id.login_btn);
        countryCodePicker = findViewById(R.id.countryCodeP);
        password = findViewById(R.id.login_password);
        progressBar = findViewById(R.id.progressBar);


    }

    public void loginTheUser(View view) {
        String _phoneNo=phoneNo.getEditText().getText().toString().trim();
        if (_phoneNo.isEmpty()){
            Toast.makeText(this, "Enter your PhoneNo first", Toast.LENGTH_SHORT).show();
            return;
        }
        if (_phoneNo.length()<10){
            Toast.makeText(this, "Enter complete PhoneNo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (_phoneNo.charAt(0)=='0'){
            _phoneNo=_phoneNo.substring(1);
        }
        String ccphoneNo="+"+countryCodePicker.getSelectedCountryCode()+_phoneNo;
        String passwordStr=password.getEditText().getText().toString().trim();


        Query checkUser= FirebaseDatabase.getInstance().getReference("User").orderByChild("PhoneNo").equalTo(ccphoneNo);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    phoneNo.setError(null);
                    phoneNo.setErrorEnabled(false);

                    //String systemPassword=snapshot.child()
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Retailer_login.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}

