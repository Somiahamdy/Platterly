 package com.example.platterly;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.platterly.Favourites.view.FavFragment;
import com.example.platterly.mealsearch.view.SearchFragment;
import com.example.platterly.categories.view.CatFragment;
import com.example.platterly.home.view.HomeFragment;
import com.example.platterly.plan.view.PlanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

 public class MainActivity extends AppCompatActivity {
    private final int favid = R.id.fav_page;

     public static String CAT_Frg_TAG = "CatFragment";
     private CatFragment catFrag;
     private HomeFragment homeFrag;
     private FavFragment favFrag;
     private SearchFragment searchFrag;
     private PlanFragment planFragment;
     public static String MainFrag="MainFrag";

     public static String HOME_Frg_TAG = "HomeFragment";
     public static String FAV_Frg_TAG = "Favfragment";
     public static String SEARCH_FRG_TAG="Searchfragment";
     public static String PLAN_FRG_TAG="Planfragment";

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        catFrag = new CatFragment();
        homeFrag = new HomeFragment();
        favFrag = new FavFragment();
        searchFrag = new SearchFragment();
        planFragment=new PlanFragment();

        if (savedInstanceState == null) {
            loadFragment(homeFrag, HOME_Frg_TAG); // load home fragment by default
        }

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navhost);






        // Check if navHostFragment is null before proceeding
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            // Setup Bottom Navigation
             bottomNavigationView = findViewById(R.id.bottom_navigation);
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        } else {
            throw new IllegalStateException("NavHostFragment not found in MainActivity");
        }

        // Set a listener for the bottom navigation items

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.fav_page){
                Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                loadFragment(favFrag, FAV_Frg_TAG);
                return true;
            }else if(item.getItemId()==R.id.home_page){
                Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                loadFragment(homeFrag, HOME_Frg_TAG);
                return true;
            }else if(item.getItemId()==R.id.cat_page){
                Toast.makeText(MainActivity.this, "Categories", Toast.LENGTH_SHORT).show();
                loadFragment(catFrag, CAT_Frg_TAG);
                return true;
            }else if(item.getItemId()==R.id.search_page){
                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                loadFragment(searchFrag, SEARCH_FRG_TAG);
                return true;
            }else if(item.getItemId()==R.id.plan_page){
                Toast.makeText(MainActivity.this, "Plan", Toast.LENGTH_SHORT).show();
                loadFragment(planFragment, PLAN_FRG_TAG);

                return true;
            }
            return false;

        });

    }

     public void loadFragment(Fragment fragment, String tag) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

         // Replace the existing fragment with the new one
         fragmentTransaction.replace(R.id.navhost, fragment, tag);
         fragmentTransaction.addToBackStack(null); // Optional: add to backstack to allow back navigation
         fragmentTransaction.commit();
     }
}