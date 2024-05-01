package com.badaruldin.check_application.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.badaruldin.check_application.R;

import java.util.Calendar;

public class SignUp_Two extends AppCompatActivity {

    Button nextBtn, loginBtn;
    TextView backBtn, createAccountTV;
    DatePicker datePicker;
    RadioGroup radioGroup;
    RadioButton selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_two);
        radioGroup = findViewById(R.id.radioGroup);
        datePicker = findViewById(R.id.datePicker);


    }

    public void callNextScreen(View view) {
        nextBtn = findViewById(R.id.next_btn);
        loginBtn = findViewById(R.id.login_btn);
        backBtn = findViewById(R.id.back_press_tv);
        createAccountTV = findViewById(R.id.create_account_ET);

        if (!ValidateAge() | !ValidateGender())
        {return;}

        selectedGender=findViewById(radioGroup.getCheckedRadioButtonId());
        String gender=selectedGender.getText().toString().trim();

        int day=datePicker.getDayOfMonth();
        int month=datePicker.getMonth();
        int year=datePicker.getYear();

        String date=day+"/"+month+"/"+year;


        Intent intentRec=getIntent();
        String fname=intentRec.getStringExtra("fullname");
        String username=intentRec.getStringExtra("username");
        String email=intentRec.getStringExtra("email");
        String password=intentRec.getStringExtra("password");

        Intent intent = new Intent(getApplicationContext(), SignUpThird.class);
        Pair[] pairs = new Pair[4];

        intent.putExtra("fullname",fname);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        intent.putExtra("gender",gender);
        intent.putExtra("date",date);


        pairs[0] = new Pair<View, String>(nextBtn, "next_btn_trasnition");
        pairs[1] = new Pair<View, String>(loginBtn, "login_btn_trasnition");
        pairs[2] = new Pair<View, String>(backBtn, "back_press_trasnition");
        pairs[3] = new Pair<View, String>(createAccountTV, "create_account_trasnition");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp_Two.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private boolean ValidateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Select your age first", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 14) {
            Toast.makeText(this, "Your are a little kid.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }
}