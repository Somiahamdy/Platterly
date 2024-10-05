package com.example.platterly.meal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.platterly.R;
import com.example.platterly.db.MealLocalDataSource;
import com.example.platterly.db.MealLocalDataSourceimp;
import com.example.platterly.home.view.HomeAdapter;
import com.example.platterly.meal.presenter.MealPresenter;
import com.example.platterly.meal.presenter.MealPresenterImp;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.model.MealRepositoryImp;
import com.example.platterly.model.PlanMeal;
import com.example.platterly.network.RMealRemoteDataSource;
import com.example.platterly.network.RMealRemoteDataSourceImp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RMealActivity extends AppCompatActivity implements MealViewInterface,onMealClickListener {
    public static String RMealTag="RMealActivity";
    RecyclerView mealrecycler;
    MealAdapter ma;
    private Context context;
    private MealRepository mealrepo;
    MealPresenter imealPresenter;
    RMealRemoteDataSource mealRemoteDataSource ;
   MealLocalDataSource mealLocalDataSource;
    private String MealID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_rmeal);
        Intent incomingIntent = getIntent();
        MealID=incomingIntent.getStringExtra(HomeAdapter.MealID);
        mealrecycler = findViewById(R.id.rmeallrv);
        mealrecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealrecycler.setLayoutManager(layoutManager);

        ma = new MealAdapter(context,this);
        mealRemoteDataSource= RMealRemoteDataSourceImp.getInstance();
        mealLocalDataSource = MealLocalDataSourceimp.getInstance(context);
        mealrepo = MealRepositoryImp.getInstance(mealRemoteDataSource,mealLocalDataSource);
        mealrecycler.setAdapter(ma);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        imealPresenter= new MealPresenterImp(mealrepo,this);
        imealPresenter.getMealDetails(MealID);


    }


    @Override
    public void setMeals(List<Meal> meals) {
        ma.setList(meals);
        ma.notifyDataSetChanged();
    }


    @Override
    public void onMealAddFavListener(Meal meal) {
      imealPresenter.insertMeal(meal);
    }

    @Override
    public void onMealRemoveFavListener(Meal meal) {
        imealPresenter.deleteMeal(meal);
    }

    @Override
    public void onOpenCalendar(PlanMeal planMeal) {
        //CalendarFragment calendarFragment = new CalendarFragment(planMeal);
        //calendarFragment.show(getSupportFragmentManager(),"calendar fragment");

    }
}