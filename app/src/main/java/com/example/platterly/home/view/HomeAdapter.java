package com.example.platterly.home.view;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.platterly.MainActivity;
import com.example.platterly.R;
import com.example.platterly.categories.view.AllCatAdapter;
import com.example.platterly.categories.view.onAllCatClickListener;
import com.example.platterly.meal.view.RMealActivity;
import com.example.platterly.model.Category;
import com.example.platterly.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meal;
    private static final String TAG="homeviewer";
    private onHomeClickListener homelistener;
    public static String MealID="MealID";


    public HomeAdapter( Context context, onHomeClickListener _listener) {
        meal = new ArrayList<>();
        this.context = context;
        this.homelistener=_listener;
    }

    public void setList(List<Meal> meals) {
        this.meal=meals;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameview ,areaview , catview;
        ImageView img;
        ConstraintLayout constraintlayout;
        View layout;


        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            nameview = v.findViewById(R.id.rmealnameview);
            areaview = v.findViewById(R.id.mealareaview);
            catview = v.findViewById(R.id.rmealcatview);

            img = v.findViewById(R.id.rmealimg);

            constraintlayout = v.findViewById(R.id.home_row);

        }
    }
    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.home_layout,parent,false);
        HomeAdapter.ViewHolder vh = new HomeAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameview.setText(meal.get(position).getStrMeal());
        holder.catview.setText(meal.get(position).getStrCategory());
        //holder.areaview.setText(meal.get(position).getStrArea());
        Glide.with(context).load(meal.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(150,150)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);

        holder.constraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,meal.get(position).getStrCategory(),Toast.LENGTH_SHORT).show();
                Intent mealintent = new Intent(context, RMealActivity.class);
                mealintent.putExtra(HomeAdapter.MealID,meal.get(position).getIdMeal());

                context.startActivity(mealintent);
               // mainActivity.loadFragment();

            }
        });
    }



    @Override
    public int getItemCount() {
        return meal.size();
    }
}
