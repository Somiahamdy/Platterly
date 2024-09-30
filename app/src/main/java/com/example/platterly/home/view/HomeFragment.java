package com.example.platterly.home.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.platterly.R;
import com.example.platterly.categories.presenter.CatPresenter;
import com.example.platterly.categories.presenter.CatPresenterImp;
import com.example.platterly.categories.view.AllCatAdapter;
import com.example.platterly.categories.view.CatViewInterface;
import com.example.platterly.db.MealLocalDataSource;
import com.example.platterly.db.MealLocalDataSourceimp;
import com.example.platterly.home.presenter.HomePresenter;
import com.example.platterly.home.presenter.HomePresenterImp;
import com.example.platterly.model.CatRepository;
import com.example.platterly.model.CatRepositoryImp;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.model.MealRepositoryImp;
import com.example.platterly.network.CatRemoteDataSource;
import com.example.platterly.network.CatRemoteDataSourceImp;
import com.example.platterly.network.RMealRemoteDataSource;
import com.example.platterly.network.RMealRemoteDataSourceImp;

import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface,onHomeClickListener{
    RecyclerView homeRecycler;
    HomeAdapter homeAdapter;
    private Context context;
    private MealRepository mealrepo;
    HomePresenter ihomePresenter;
    RMealRemoteDataSource mealRemoteDataSource ;
    MealLocalDataSource mealLocalDataSource;
    HomeViewInterface ihomeview;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=requireContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeRecycler = view.findViewById(R.id.homerv);
        homeRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        homeRecycler.setLayoutManager(layoutManager);

        homeAdapter = new HomeAdapter(context,this);
        mealRemoteDataSource= RMealRemoteDataSourceImp.getInstance();
        mealLocalDataSource = MealLocalDataSourceimp.getInstance(context);
        mealrepo = MealRepositoryImp.getInstance(mealRemoteDataSource,mealLocalDataSource);
        homeRecycler.setAdapter(homeAdapter);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        ihomePresenter= new HomePresenterImp(mealrepo,this);
        ihomePresenter.getRandomMeal();

    }

    @Override
    public void setSuggestionMeal(List<Meal> meals) {
        homeAdapter.setList(meals);
        homeAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrors(String error) {
        Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
    }
}