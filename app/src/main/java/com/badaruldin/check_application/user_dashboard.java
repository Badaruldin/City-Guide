package com.badaruldin.check_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class user_dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView featuredRecycler,mostviewedRecycler,catagoriesRecycler;
    RecyclerView.Adapter adapter;
    ImageView menueButton;
    LinearLayoutCompat contentView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        //line to turn off auto keyboard selection
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        featuredRecycler=findViewById(R.id.featured_recycler);
        mostviewedRecycler=findViewById(R.id.mostviewed_recycler);
        catagoriesRecycler=findViewById(R.id.catagories_recycler);
        contentView=findViewById(R.id.contentView);

        //Hooks for menu or Drawer Layout & Navigation Menu
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);
        menueButton=findViewById(R.id.menu_click_btn);

//      fucntion for Drawer Layout
        navigationView();
        animationOfDrawer();


        //fucntions for Recycler View
        featuredRecycler();
        mostviewedRecycler();
        catagoriesRecycler();
    }

    //Drawer Layout functions
    private void animationOfDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.purple_200));//to add color of remanining screen of Drawer
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //the whole code of this function is a bit difficult, so its been copy pasted.hehehehe
                float End_Scale =0.5f;
                float diffScaledOffset=slideOffset*(1-End_Scale);
                float offsetScale=1-diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                float xOffset=drawerView.getWidth()*slideOffset;
                float xOffsetDiff=contentView.getWidth()*diffScaledOffset/2;
                float xTranslation=xOffset-xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }
        });

    }

    private void navigationView() {
        navigationView.bringToFront();
        navigationView.setCheckedItem(R.id.home);
        navigationView.setNavigationItemSelectedListener(this);

        menueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.openDrawer(GravityCompat.START);
                //}
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.all_categories:
                startActivity(new Intent(getApplicationContext(),AllCategories.class));
                break;
        }

        return true;
    }

//RecyclerViews functions
    private void catagoriesRecycler() {
        catagoriesRecycler.setHasFixedSize(true);
        catagoriesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<FeaturedHelperClass> featuredLocationsDA=new ArrayList<>();
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img_3,"Hospital","The only place for its special mutton and cleanliness."));
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img,"Malls","The home of dairy products are now on a single click"));
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img_2,"Cafes","Offers all guys to buy all kind of stuff."));

        adapter=new FeaturedAdapterClassforCat(featuredLocationsDA);
        catagoriesRecycler.setAdapter(adapter);
    }

    private void mostviewedRecycler() {
        //i use featured_card_design in this function as i use featuredRecycler design.
        mostviewedRecycler.setHasFixedSize(true);
        mostviewedRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<FeaturedHelperClass> featuredLocationsDA=new ArrayList<>();
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img_2,"Mutton Shop","The only place for its special mutton and cleanliness."));
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img,"Milk Shop","The home of dairy products are now on a single click"));
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img_3,"ZEE 11 Mart","Offers all guys to buy all kind of stuff."));

        adapter=new FeaturedAdapterClass(featuredLocationsDA);
        mostviewedRecycler.setAdapter(adapter);
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<FeaturedHelperClass> featuredLocationsDA=new ArrayList<>();
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img,"TasteMakers","The only place for its special taste and enviornment."));
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img_2,"HomeTasters","The which gives you touch to your mom's food taste."));
        featuredLocationsDA.add(new FeaturedHelperClass(R.drawable.featured_img_3,"Chill & Spice","Offers young guns to some chilli food."));

        adapter=new FeaturedAdapterClass(featuredLocationsDA);
        featuredRecycler.setAdapter(adapter);

    }


    public void RetailerStartUP(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));

    }
}
