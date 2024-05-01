package com.badaruldin.check_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    LinearLayout linearLayout;
    TextView[] dots;
    Button lets_start_btn;
    Animation animation;
    int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_boarding);


        viewPager=findViewById(R.id.slider);
        linearLayout=findViewById(R.id.linearLayout);
        lets_start_btn=findViewById(R.id.lets_start_btn);

        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }
    private void addDots(int position) {
        dots = new TextView[4];
        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(30);


            linearLayout.addView(dots[i]);
        }
        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.purple_700));
        }
    }
        ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDots(position);
                currentPosition=position;
                if (position==0)
                {
                    lets_start_btn.setVisibility(View.INVISIBLE);

                }
                else if (position==1)
                {
                    lets_start_btn.setVisibility(View.INVISIBLE);


                }
                else if (position==2)
                {
                    lets_start_btn.setVisibility(View.INVISIBLE);

                }
                else if (position==3)
                {
                    animation= AnimationUtils.loadAnimation(OnBoarding.this,R.anim.left_to_right);

                    lets_start_btn.setVisibility(View.VISIBLE);
                    lets_start_btn.setAnimation(animation);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
           
        };

    public void skipFun(View view){
       // Intent move=new Intent(this,user_dashboard.class);
        finish();
        startActivity(new Intent(this, user_dashboard.class));
    }
    public void nextFun(View view){
        viewPager.setCurrentItem(currentPosition+1);


    }

}