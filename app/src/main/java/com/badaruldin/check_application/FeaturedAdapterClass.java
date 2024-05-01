package com.badaruldin.check_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//this is been main class which is created
public class FeaturedAdapterClass extends RecyclerView.Adapter<FeaturedAdapterClass.FeaturedViewHolder> {
    ArrayList<FeaturedHelperClass> featuredLocationDA;//Declyring Dynamic array, so it can be assigned to Constructor.

    public FeaturedAdapterClass(ArrayList<FeaturedHelperClass> featuredLocation) {
        this.featuredLocationDA = featuredLocation;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //as the parameters shows that we need to create a View with Layout_Inflator to fetch our CARD design.
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder=new FeaturedViewHolder(view);//assign created view to Sub class's Constructor
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass=featuredLocationDA.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDesc());


    }

    @Override
    public int getItemCount() {
        return featuredLocationDA.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,desc;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.featured_image);
            title=itemView.findViewById(R.id.featured_title);
            desc=itemView.findViewById(R.id.featured_desc);
        }
    }
}
