package com.example.platterly.mealsearch.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.platterly.R;
import com.example.platterly.categories.presenter.CatPresenter;
import com.example.platterly.categories.presenter.CatPresenterImp;
import com.example.platterly.categories.view.AllCatAdapter;
import com.example.platterly.categories.view.CatViewInterface;
import com.example.platterly.db.MealLocalDataSource;
import com.example.platterly.db.MealLocalDataSourceimp;
import com.example.platterly.home.presenter.HomePresenter;
import com.example.platterly.home.view.HomeAdapter;
import com.example.platterly.home.view.HomeViewInterface;
import com.example.platterly.mealsearch.presenter.MealSearchPresenter;
import com.example.platterly.mealsearch.presenter.MealSearchPresenterImp;
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


public class SearchFragment extends Fragment implements onMealSearchClickListener,MealSearchViewInterface {
    private Button countrybtn;
    private Button catbtn;
    private Button namebtn;
    private RadioButton cntrybtn,namesbtn,catsbtn,ingsbtn;
    private Spinner srchspinner;
    private SearchView search;
    RecyclerView searchRecycler;
    MealSearchAdapter searchAdapter;
    private Context context;
    private MealRepository mealrepo;
    MealSearchPresenter imealPresenter;
    RMealRemoteDataSource mealRemoteDataSource ;
    MealLocalDataSource mealLocalDataSource;
    MealSearchViewInterface imealsearchview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search=view.findViewById(R.id.searchView);
        int searchEditTextId = search.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = search.findViewById(searchEditTextId);

        searchEditText.setTextColor(Color.BLACK);
        searchEditText.setHintTextColor(Color.GRAY);

        srchspinner=view.findViewById(R.id.searchspinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.spinner_items, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        srchspinner.setAdapter(adapter);



        searchRecycler = view.findViewById(R.id.searchrv);
        searchRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        searchRecycler.setLayoutManager(layoutManager);

        searchAdapter = new MealSearchAdapter(context,this);
        mealRemoteDataSource= RMealRemoteDataSourceImp.getInstance();
        mealLocalDataSource= MealLocalDataSourceimp.getInstance(context);
        mealrepo = MealRepositoryImp.getInstance(mealRemoteDataSource,mealLocalDataSource);
        searchRecycler.setAdapter(searchAdapter);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        imealPresenter= new MealSearchPresenterImp(mealrepo,this);

        srchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedItem = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                if(position==0){
                    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            imealPresenter.getMealSearchname(s);
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            return false;
                        }
                    });
                }else if(position==1){
                    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            imealPresenter.getMealSearch(s);
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            return true;
                        }
                    });
                }else if(position==2){
                    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            imealPresenter.getMealSearchcountry(s);
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            return true;
                        }
                    });
                }else if(position==3){
                    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            imealPresenter.getMealSearchIng(s);
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            return true;
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something here if needed
            }
        });

    }

    @Override
    public void onMealAddFavListener() {

    }

    @Override
    public void setMeals(List<Meal> meals) {
     searchAdapter.setList(meals);
     searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}