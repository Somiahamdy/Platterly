package com.example.platterly.meal.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.platterly.R;
import com.example.platterly.home.view.HomeAdapter;
import com.example.platterly.model.Meal;
import com.example.platterly.model.PlanMeal;
import com.example.platterly.plan.view.PlanActivity;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meal;
    private static final String TAG="mealviewer";
    private onMealClickListener meallistener;
    public static boolean fav=false;
    public static String MEALPLANNAME="MealName";
    public PlanMeal planMeal;
    public static String MealThumb="mealthumb";
    //public



    public MealAdapter(Context context, onMealClickListener _listener){
        meal = new ArrayList<>();
        this.context = context;
        this.meallistener=_listener;
    }
    public void setList(List<Meal> meals) {
        this.meal=meals;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameview ,areaview , catview , desview;
        ImageView img;
        ConstraintLayout constraintlayout;
        View layout;
        WebView webView;
        ImageButton addToFav;
        ImageButton openplan;
        RecyclerView ingredientRecycler;
        //PlanMeal planMeal;



        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            nameview = v.findViewById(R.id.rmealnameview);
            areaview = v.findViewById(R.id.mealareaview);
            catview = v.findViewById(R.id.rmealcatview);
            desview=v.findViewById(R.id.mealdesview);

            img = v.findViewById(R.id.rmealimg);

            constraintlayout = v.findViewById(R.id.meal_row);
            webView=v.findViewById(R.id.mealview);
            addToFav = v.findViewById(R.id.mealaddfav);
            openplan = v.findViewById(R.id.opencalendar);
            ingredientRecycler=v.findViewById(R.id.ingrv);

        }
    }

    @NonNull
    @Override
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.meal_layout,parent,false);
        MealAdapter.ViewHolder vh = new MealAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameview.setText(meal.get(position).getStrMeal());
        holder.catview.setText(meal.get(position).getStrCategory());
        holder.areaview.setText(meal.get(position).getStrArea());
        holder.desview.setText(meal.get(position).getStrInstructions());
        Glide.with(context).load(meal.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(150,150)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);
        meal.get(position).setIngredientsFromFields();
        meal.get(position).setMeasuresFromFields();
        List<String> ingredients =  meal.get(position).getIngredients() != null ? meal.get(position).getIngredients() : new ArrayList<>();
        List<String> measures = meal.get(position).getMeasures() != null ? meal.get(position).getMeasures() : new ArrayList<>();


        IngredientAdapter ingredientAdapter = new IngredientAdapter(context, ingredients, measures);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        holder.ingredientRecycler.setLayoutManager(linearLayoutManager);
        holder.ingredientRecycler.setAdapter(ingredientAdapter);

        holder.constraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(context,meal.get(position).getStrCategory(),Toast.LENGTH_SHORT).show();

            }
        });


        holder.webView.getSettings().setJavaScriptEnabled(true);
        String URL=meal.get(position).getStrYoutube().replace("watch?v=","embed/");
        holder.webView.loadUrl(URL);

        if (meal.get(position).getFavourite()) {
            holder.addToFav.setImageResource(R.drawable.baseline_favorite_24);  // Change icon to indicate removal
        } else {
            holder.addToFav.setImageResource(R.drawable.baseline_favorite_border_24);  // Change icon to indicate addition
        }

        holder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meal.get(position).getFavourite()) {
                    meal.get(position).setFavourite(false);  // Remove from plan
                    holder.addToFav.setImageResource(R.drawable.baseline_favorite_border_24);  // Change icon to "Add"
                    meallistener.onMealRemoveFavListener(meal.get(position));  // Notify listener to handle removal
                    Toast.makeText(context, " removed from Favourites", Toast.LENGTH_SHORT).show();
                } else {
                    meal.get(position).setFavourite(true);  // Add to plan
                    holder.addToFav.setImageResource(R.drawable.baseline_favorite_24);  // Change icon to "Remove"
                    meallistener.onMealAddFavListener(meal.get(position));  // Notify listener to handle addition
                    Toast.makeText(context, " added to Favourites", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.openplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //planMeal.setMealID(meal.get(position).getIdMeal());
               // planMeal.setMealName(meal.get(position).getStrMeal());

                //meallistener.onOpenCalendar(planMeal);

                Intent mealCalenderIntent = new Intent(context, PlanActivity.class);
                mealCalenderIntent.putExtra(HomeAdapter.MealID,meal.get(position).getIdMeal());
                mealCalenderIntent.putExtra(MealAdapter.MEALPLANNAME,meal.get(position).getStrMeal());
                mealCalenderIntent.putExtra(MealAdapter.MealThumb,meal.get(position).getStrMealThumb());
                context.startActivity(mealCalenderIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meal.size();
    }
}
