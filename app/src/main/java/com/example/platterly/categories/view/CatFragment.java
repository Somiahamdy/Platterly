package com.example.platterly.categories.view;

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

import com.example.platterly.MainActivity;
import com.example.platterly.R;
import com.example.platterly.categories.presenter.CatPresenter;
import com.example.platterly.categories.presenter.CatPresenterImp;
import com.example.platterly.model.CatRepository;
import com.example.platterly.model.CatRepositoryImp;
import com.example.platterly.model.Category;
import com.example.platterly.network.CatRemoteDataSource;
import com.example.platterly.network.CatRemoteDataSourceImp;

import java.util.List;


public class CatFragment extends Fragment implements CatViewInterface , onAllCatClickListener{
    RecyclerView allcatRecycler;
    AllCatAdapter allcatAdapter;
    private Context context;
    private CatRepository catrepo;
    CatPresenter icatPresenter;
    CatRemoteDataSource catRemoteDataSource ;
    CatViewInterface icatview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=requireContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allcatRecycler = view.findViewById(R.id.catrv);
        allcatRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        allcatRecycler.setLayoutManager(layoutManager);

        allcatAdapter = new AllCatAdapter(context,this);
        catRemoteDataSource= CatRemoteDataSourceImp.getInstance();
        catrepo = CatRepositoryImp.getInstance(catRemoteDataSource);
        allcatRecycler.setAdapter(allcatAdapter);
        // favAdapter = new FavAdapter(new ArrayList<>(),FavProductsActivity.CONTEXT_IGNORE_SECURITY,FavProductsActivity.class);
        icatPresenter= new CatPresenterImp(catrepo,this);
        icatPresenter.getAllCategories();

    }

    @Override
    public void setCategories(List<Category> categories) {
        allcatAdapter.setList(categories);
        allcatAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorResponse(String msgError) {
        Toast.makeText(context,msgError,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String txt) {
        Toast.makeText(context,txt,Toast.LENGTH_SHORT).show();
    }
}