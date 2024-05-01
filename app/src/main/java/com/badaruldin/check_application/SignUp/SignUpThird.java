package com.badaruldin.check_application.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.badaruldin.check_application.R;
import com.badaruldin.check_application.Retailer_login;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUpThird extends AppCompatActivity {

    Button nextBtn, loginBtn;
    TextView backBtn, createAccountTV;
    TextInputLayout phoneNo;
    CountryCodePicker countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_third);
    }

    public void callNextScreen(View view) {
        nextBtn = findViewById(R.id.next_btn);
        loginBtn = findViewById(R.id.login_btn);
        backBtn = findViewById(R.id.back_press_tv);
        createAccountTV = findViewById(R.id.create_account_ET);

        countryCode = findViewById(R.id.countryCodeP);
        phoneNo = findViewById(R.id.phone_no);

        String fullnameS = getIntent().getStringExtra("fullname");
        String usernameS = getIntent().getStringExtra("username");
        String emailS = getIntent().getStringExtra("email");
        String passwordS = getIntent().getStringExtra("password");
        String genderS = getIntent().getStringExtra("gender");
        String dateOfBirthS = getIntent().getStringExtra("date");

        if (!ValidPhoneNo()) {
            return;
        }

        String phonenoStr = phoneNo.getEditText().getText().toString().trim();

        String ccphonenoS = "+"+countryCode.getSelectedCountryCode() +phonenoStr;

        Intent intent = new Intent(getApplicationContext(), SignUp_Fourth_Verification_otp.class);

        intent.putExtra("fullname", fullnameS);
        intent.putExtra("username", usernameS);
        intent.putExtra("email", emailS);
        intent.putExtra("password", passwordS);
        intent.putExtra("gender", genderS);
        intent.putExtra("date", dateOfBirthS);
        intent.putExtra("ccphoneno", ccphonenoS);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(nextBtn, "next_btn_trasnition");
        pairs[1] = new Pair<View, String>(loginBtn, "login_btn_trasnition");
        pairs[2] = new Pair<View, String>(backBtn, "back_press_trasnition");
        pairs[3] = new Pair<View, String>(createAccountTV, "create_account_trasnition");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpThird.this, pairs);
        startActivity(intent, options.toBundle());

        startActivity(intent);
    }

    private boolean ValidPhoneNo() {
        phoneNo = findViewById(R.id.phone_no);
        String pno = phoneNo.getEditText().getText().toString().trim();
        if (pno.length() < 10) {
            phoneNo.setError("enter your complete phone no");
            return false;
        }
        else {
            phoneNo.setError(null);
            phoneNo.setErrorEnabled(false);
            return true;

        }
    }

}