package com.example.platterly.mealsearch.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.platterly.Country.view.CountryAdapter;
import com.example.platterly.R;
import com.example.platterly.categories.view.AllCatAdapter;
import com.example.platterly.db.MealLocalDataSource;
import com.example.platterly.db.MealLocalDataSourceimp;
import com.example.platterly.home.view.HomeAdapter;
import com.example.platterly.meal.presenter.MealPresenter;
import com.example.platterly.meal.presenter.MealPresenterImp;
import com.example.platterly.meal.view.MealAdapter;
import com.example.platterly.mealsearch.presenter.MealSearchPresenter;
import com.example.platterly.mealsearch.presenter.MealSearchPresenterImp;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.model.MealRepositoryImp;
import com.example.platterly.network.RMealRemoteDataSource;
import com.example.platterly.network.RMealRemoteDataSourceImp;

import java.util.List;

public class MealSearchActivity extends AppCompatActivity implements MealSearchViewInterface,onMealSearchClickListener{
    public static String MealSearchTag="MealSearchActivity";
    RecyclerView mealrecycler;
    MealSearchAdapter msa;
    private Context context;
    private MealRepository mealrepo;
    MealSearchPresenter imealsearchPresenter;
    RMealRemoteDataSource mealRemoteDataSource ;
    MealLocalDataSource mealLocalDataSource;
    private String MealCat;
    private String MealCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_mealsearch);

        Intent incomingIntent = getIntent();
        MealCat=incomingIntent.getStringExtra(AllCatAdapter.MealCat);
        MealCountry=incomingIntent.getStringExtra(CountryAdapter.Mealcountry);
        mealrecycler = findViewById(R.id.mealsearchrv);
        mealrecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealrecycler.setLayoutManager(layoutManager);

        msa = new MealSearchAdapter(context,this);
        mealRemoteDataSource= RMealRemoteDataSourceImp.getInstance();
        mealLocalDataSource = MealLocalDataSourceimp.getInstance(context);
        mealrepo = MealRepositoryImp.getInstance(mealRemoteDataSource,mealLocalDataSource);
        mealrecycler.setAdapter(msa);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        imealsearchPresenter= new MealSearchPresenterImp(mealrepo,this);
        imealsearchPresenter.getMealSearch(MealCat);
        imealsearchPresenter.getMealSearchcountry(MealCountry);


    }

    @Override
    public void setMeals(List<Meal> meals) {
        msa.setList(meals);
        msa.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealAddFavListener() {

    }
}