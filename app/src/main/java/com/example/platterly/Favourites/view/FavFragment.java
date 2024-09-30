package com.example.platterly.Favourites.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.platterly.Favourites.presenter.FavPresenterImp;
import com.example.platterly.Favourites.presenter.FavPresenterInterface;
import com.example.platterly.R;
import com.example.platterly.db.MealLocalDataSource;
import com.example.platterly.db.MealLocalDataSourceimp;
import com.example.platterly.home.presenter.HomePresenter;
import com.example.platterly.home.presenter.HomePresenterImp;
import com.example.platterly.home.view.HomeAdapter;
import com.example.platterly.home.view.HomeViewInterface;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.model.MealRepositoryImp;
import com.example.platterly.network.RMealRemoteDataSource;
import com.example.platterly.network.RMealRemoteDataSourceImp;

import java.util.List;


public class FavFragment extends Fragment implements FavViewInterface , onFavClickListener {
    RecyclerView favRecycler;
    FavAdapter favAdapter;
    private Context context;
    private MealRepository mealrepo;
    FavPresenterInterface ifavPresenter;
    RMealRemoteDataSource mealRemoteDataSource ;
    MealLocalDataSource mealLocalDataSource;
    FavViewInterface ifavView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=requireContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favRecycler = view.findViewById(R.id.favrv);
        favRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favRecycler.setLayoutManager(layoutManager);

        favAdapter = new FavAdapter(context,this);
        mealRemoteDataSource= RMealRemoteDataSourceImp.getInstance();
        mealLocalDataSource = MealLocalDataSourceimp.getInstance(context);
        mealrepo = MealRepositoryImp.getInstance(mealRemoteDataSource,mealLocalDataSource);
        favRecycler.setAdapter(favAdapter);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        ifavPresenter= new FavPresenterImp(mealrepo,this);
        ifavPresenter.getFavMeals(context);
       /* mealrepo.getStoredFavMeals().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                showMeals(meals);
            }
        });*/
    }

    @Override
    public void showMeals(List<Meal> meals) {
        favAdapter.setList(meals);
        favAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDataChanged(List<Meal> meal) {
        favAdapter.setList(meal);
        favAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAdapter() {
        favAdapter.notifyDataSetChanged();

    }

    @Override
    public void removeFromFav(Meal meal) {

    }
}