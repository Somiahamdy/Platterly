package com.example.platterly.home.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.platterly.Country.presenter.CountryPresenter;
import com.example.platterly.Country.presenter.CountryPresenterImp;
import com.example.platterly.Country.view.CountryAdapter;
import com.example.platterly.Country.view.CountryViewInterface;
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
import com.example.platterly.model.Country;
import com.example.platterly.model.CountryRepositoryImp;
import com.example.platterly.model.CountryRespository;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.model.MealRepositoryImp;
import com.example.platterly.network.CatRemoteDataSource;
import com.example.platterly.network.CatRemoteDataSourceImp;
import com.example.platterly.network.RMealRemoteDataSource;
import com.example.platterly.network.RMealRemoteDataSourceImp;

import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface,onHomeClickListener, CountryViewInterface, onCountryClickListener {
    RecyclerView homeRecycler;
    RecyclerView countryRecycler;

    HomeAdapter homeAdapter;
    private Context context;
    private MealRepository mealrepo;
    HomePresenter ihomePresenter;
    RMealRemoteDataSource mealRemoteDataSource ;
    MealLocalDataSource mealLocalDataSource;
    HomeViewInterface ihomeview;

    CountryAdapter countryAdapter;
    CountryRespository countryRepo;
    CountryPresenter countryPresenter;
    CountryViewInterface icountryView;



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
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        homeRecycler.setLayoutManager(layoutManager1);

        countryRecycler = view.findViewById(R.id.countriesrv);
        countryRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        countryRecycler.setLayoutManager(layoutManager2);


        homeAdapter = new HomeAdapter(context,this);
        countryAdapter = new CountryAdapter(context,this);

        mealRemoteDataSource= RMealRemoteDataSourceImp.getInstance();
        mealLocalDataSource = MealLocalDataSourceimp.getInstance(context);
        mealrepo = MealRepositoryImp.getInstance(mealRemoteDataSource,mealLocalDataSource);
        countryRepo = CountryRepositoryImp.getInstance(mealRemoteDataSource);
        homeRecycler.setAdapter(homeAdapter);
        countryRecycler.setAdapter(countryAdapter);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        ihomePresenter= new HomePresenterImp(mealrepo,this);
        ihomePresenter.getRandomMeal();

        countryPresenter=new CountryPresenterImp(countryRepo,this);
        countryPresenter.getAllCountries();

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

    @Override
    public void setCountries(List<Country> countries) {
        Log.d("HomeFragment", "Country list received with size: " + countries.size());

        countryAdapter.setList(countries);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String errorMsg) {

    }
}