package com.example.platterly.Favourites.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        ImageButton removeFav;
        //WebView webView;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            nameview = v.findViewById(R.id.favnameview);

            img = v.findViewById(R.id.favplanimg);

            constraintlayout = v.findViewById(R.id.fav_row);
            removeFav=v.findViewById(R.id.removefavbtn);
            //addToFav= v.findViewById(R.id.mealaddfav);

        }
    }


    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fav_layout,parent,false);
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
        holder.removeFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Remove Favorite")
                        .setMessage("Are you sure you want to remove this meal from favorites?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // User confirmed, remove from favorites
                            mealfavlistener.removeFromFav(meal.get(position));
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        holder.constraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    // Network is available, start the activity
                    Intent mealsearchintent = new Intent(context, RMealActivity.class);
                    mealsearchintent.putExtra(HomeAdapter.MealID, meal.get(position).getIdMeal());
                    context.startActivity(mealsearchintent);
                } else {
                    // No internet connection, show a toast
                    Toast.makeText(context, "No Internet Connection. Please try again later.", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(context,meal.get(position).getStrCategory(),Toast.LENGTH_SHORT).show();
                //Intent mealsearchintent = new Intent(context, RMealActivity.class);
               // mealsearchintent.putExtra(HomeAdapter.MealID,meal.get(position).getIdMeal());

                //context.startActivity(mealsearchintent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return meal.size();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                return capabilities != null &&
                        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            } else {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        }
        return false;
    }
}
