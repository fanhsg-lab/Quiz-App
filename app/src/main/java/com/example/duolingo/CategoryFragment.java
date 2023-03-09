package com.example.duolingo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

   private GridView catView;
    public static List<CategoryModel> catList = new ArrayList<>();

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container,false);

        catView = view.findViewById(R.id.cat_Grid);
        //παίρνω τις διάφορες κατηγορίες
        loadCategories();

        //τα βάζω στον adapter
        CategoryAdapter adapter = new CategoryAdapter(catList);
        catView.setAdapter(adapter);



        return view;
    }

    private void loadCategories(){


        catList.clear();

        catList.add(new CategoryModel("1","GK",20));
        catList.add(new CategoryModel("2","HISTORY",30));
        catList.add(new CategoryModel("3","ENGLISH",10));
        catList.add(new CategoryModel("4","SCIENCE",25));
        catList.add(new CategoryModel("5","MATHS",20));
    }
}