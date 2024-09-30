package com.example.platterly.categories.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.platterly.R;
import com.example.platterly.home.view.HomeAdapter;
import com.example.platterly.meal.view.RMealActivity;
import com.example.platterly.mealsearch.view.MealSearchActivity;
import com.example.platterly.model.Category;
import com.example.platterly.model.MealResponse;

import java.util.ArrayList;
import java.util.List;

public class AllCatAdapter extends RecyclerView.Adapter<AllCatAdapter.ViewHolder>{
    private final Context context;
    private List<Category> cat;
    private static final String TAG="allcatviewer";
    private  onAllCatClickListener catlistener;
    public static String MealCat="MEALCAT";

    public AllCatAdapter( Context context, onAllCatClickListener _listener) {
        cat = new ArrayList<>();
        this.context = context;
        this.catlistener=_listener;
    }

    public void setList(List<Category> categories) {
        this.cat=categories;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameview ,desview;
        ImageView img;
        ConstraintLayout constraintlayout;
        View layout;


        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            nameview = v.findViewById(R.id.catnameview);
            //desview = v.findViewById(R.id.catdesview);

            img = v.findViewById(R.id.imageView);

            constraintlayout = v.findViewById(R.id.cat_row);

        }
    }
    @NonNull
    @Override
    public AllCatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cat_layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameview.setText(cat.get(position).getStrCategory());
        //holder.desview.setText(cat.get(position).getStrCategoryDescription());
        Glide.with(context).load(cat.get(position).getStrCategoryThumb())
                .apply(new RequestOptions().override(150,150)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);
        holder.constraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,cat.get(position).getStrCategory(),Toast.LENGTH_SHORT).show();
                Intent mealintent = new Intent(context, MealSearchActivity.class);
                mealintent.putExtra(AllCatAdapter.MealCat,cat.get(position).getStrCategory());

                context.startActivity(mealintent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cat.size();
    }
}
