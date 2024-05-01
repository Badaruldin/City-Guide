package com.badaruldin.check_application.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.badaruldin.check_application.R;
import com.badaruldin.check_application.Retailer_login;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SignUp_Fourth_Verification_otp extends AppCompatActivity {

    TextView backBtn,code,display_endterd_no;
    Button verifyBtn,loginBtn;
    PinView pinViewBoxs;
    String codeBySys,fullname,username,email,dateofBirth,ccphoneno,gender,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_fourth_verification_code);

        display_endterd_no=findViewById(R.id.No_disp_textView);
        loginBtn=findViewById(R.id.login_btn);

        fullname=getIntent().getStringExtra("fullname");
        username=getIntent().getStringExtra("username");
        email=getIntent().getStringExtra("email");
        dateofBirth=getIntent().getStringExtra("date");
        password=getIntent().getStringExtra("password");
        gender=getIntent().getStringExtra("gender");
        ccphoneno=getIntent().getStringExtra("ccphoneno");


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        pinViewBoxs=findViewById(R.id.pinViewBoxs);

        display_endterd_no.setText("Enter OTP sented on "+ccphoneno);
        sendVerificationCodeToUser(ccphoneno);

    }

    private void sendVerificationCodeToUser(String phoneNumber) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build();
PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeBySys=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                pinViewBoxs.setText(code);
                callVerifyCode(code);
            }
        }
        
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            registerUser();
            Toast.makeText(SignUp_Fourth_Verification_otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();        }
    };

    private void registerUser() {
        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        DatabaseReference reference=rootNode.getReference("User");
//Traditional method with the help of HashMap
//        HashMap<String,String> hashMapRefrence=new HashMap<>();
//        hashMapRefrence.put("FullName",fullname);
//        hashMapRefrence.put("UserName",username);
//        hashMapRefrence.put("Email",email);
//        hashMapRefrence.put("Password",password);
//        hashMapRefrence.put("DOB",dateofBirth);
//        hashMapRefrence.put("Gender",gender);
//        hashMapRefrence.put("PhoneNo",ccphoneno);
//
//        reference.child(ccphoneno).setValue(hashMapRefrence);

        DatabaseUserRegisterHelperClass newUserData=new DatabaseUserRegisterHelperClass(fullname,username,email,dateofBirth,ccphoneno,gender,password);
        reference.child(ccphoneno).setValue(newUserData);
    }

    private void callVerifyCode(String code) {
        PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(codeBySys,code);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp_Fourth_Verification_otp.this, "Succesfully Register the user", Toast.LENGTH_SHORT).show();
                        loginBtn.setVisibility(View.VISIBLE);

                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(SignUp_Fourth_Verification_otp.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    public void callVerifyFunction(View view) {
        String code=pinViewBoxs.getText().toString().trim();
        if (!code.isEmpty()){
            callVerifyCode(code);
        }
    }

    public void LoginFunction(View view) {
        startActivity(new Intent( getApplicationContext(), Retailer_login.class));
    }
}

//    val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)       // Phone number to verify
//            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//            .setActivity(this)                 // Activity (for callback binding)
//            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
//            .build()
//PhoneAuthProvider.verifyPhoneNumber(options)