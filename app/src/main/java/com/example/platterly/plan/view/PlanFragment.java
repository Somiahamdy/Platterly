package com.example.platterly.plan.view;

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
import android.widget.CalendarView;

import com.example.platterly.Favourites.presenter.FavPresenterImp;
import com.example.platterly.Favourites.view.FavAdapter;
import com.example.platterly.R;
import com.example.platterly.db.MealLocalDataSource;
import com.example.platterly.db.MealLocalDataSourceimp;
import com.example.platterly.mealsearch.presenter.MealSearchPresenter;
import com.example.platterly.mealsearch.view.MealSearchAdapter;
import com.example.platterly.mealsearch.view.MealSearchViewInterface;
import com.example.platterly.model.MealRepository;
import com.example.platterly.model.MealRepositoryImp;
import com.example.platterly.model.PlanMeal;
import com.example.platterly.network.RMealRemoteDataSource;
import com.example.platterly.network.RMealRemoteDataSourceImp;
import com.example.platterly.plan.presenter.PlanPresenterImp;
import com.example.platterly.plan.presenter.PlanPresenterInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class PlanFragment extends Fragment implements PlanViewInterface , onPlanClickListener  {
    RecyclerView planRecycler;
    PlanMealAdapter planMealAdapter;
    private Context context;
    private MealRepository mealrepo;
    PlanPresenterInterface iplanPresenter;
    RMealRemoteDataSource mealRemoteDataSource ;
    MealLocalDataSource mealLocalDataSource;
    PlanViewInterface iplanview;
    CalendarView calendarViewfrag;
    private String selectedDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=requireContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        planRecycler = view.findViewById(R.id.planrv);
        planRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        planRecycler.setLayoutManager(layoutManager);
        calendarViewfrag=view.findViewById(R.id.calendarViewfrag);
        iplanview=this;


        planMealAdapter = new PlanMealAdapter(context,this);
        mealRemoteDataSource= RMealRemoteDataSourceImp.getInstance();
        mealLocalDataSource = MealLocalDataSourceimp.getInstance(context);
        mealrepo = MealRepositoryImp.getInstance(mealRemoteDataSource,mealLocalDataSource);
        planRecycler.setAdapter(planMealAdapter);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        iplanPresenter= new PlanPresenterImp(mealrepo,this);
        calendarViewfrag.setOnDateChangeListener((view1, year, month, dayOfMonth) ->{
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            selectedDate = dateFormat.format(calendar.getTime());
            iplanPresenter.getMealByDate(context,selectedDate);
            //selectedDate = calendar.getTime();
            //planMeal=new PlanMeal(mealid,mealname,selectedDate,mealthumb);
        });

    }

    @Override
    public void onAddToPlanClick(PlanMeal planMeal) {

    }

    @Override
    public void openCalendar(PlanMeal planMeal) {

    }

    @Override
    public void onRemoveFromPlanClick(PlanMeal planMeal) {
        mealrepo.RemoveMealFromPlan(planMeal);
        iplanview.notifyAdapter();
    }

    @Override
    public void setMeals(List<PlanMeal> meals) {

    }

    @Override
    public void showerrors(String msg) {

    }

    @Override
    public void onDataChanged(List<PlanMeal> meal) {
        planMealAdapter.setList(meal);
        planMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAdapter() {
        planMealAdapter.notifyDataSetChanged();
    }
}