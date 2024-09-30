package com.example.platterly.Favourites.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.platterly.R;
import com.example.platterly.home.view.HomeAdapter;
import com.example.platterly.meal.view.RMealActivity;
import com.example.platterly.mealsearch.view.MealSearchAdapter;
import com.example.platterly.mealsearch.view.onMealSearchClickListener;
import com.example.platterly.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meal;
    private static final String TAG="mealfavviewer";
    private onFavClickListener mealfavlistener;
    public static String MEALID="mealid";

    public FavAdapter(Context context, onFavClickListener _listener){
        meal = new ArrayList<>();
        this.context = context;
        this.mealfavlistener=_listener;
    }
    public void setList(List<Meal> meals) {
        this.meal=meals;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameview ;
        ImageView img;
        ConstraintLayout constraintlayout;
        View layout;
        ImageButton addToFav;
        //WebView webView;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            nameview = v.findViewById(R.id.mealnamesearch);

            img = v.findViewById(R.id.mealsearchimg);

            constraintlayout = v.findViewById(R.id.mealsearch_row);
            //addToFav= v.findViewById(R.id.mealaddfav);

        }
    }


    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.mealsearch,parent,false);
        FavAdapter.ViewHolder vh = new FavAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameview.setText(meal.get(position).getStrMeal());
        Glide.with(context).load(meal.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(150,150)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);
        holder.constraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,meal.get(position).getStrCategory(),Toast.LENGTH_SHORT).show();
                Intent mealsearchintent = new Intent(context, RMealActivity.class);
                mealsearchintent.putExtra(HomeAdapter.MealID,meal.get(position).getIdMeal());

                context.startActivity(mealsearchintent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return meal.size();
    }

}
