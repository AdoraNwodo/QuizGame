package com.example.nenneadora.quizgame;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HardFragment extends ListFragment {

    // Array of category names
    Category[] categories = new Category[]{
            Category.ZOBIAWA,
            Category.GENERAL};

    // Array of integers points to images stored in /res/drawable/
    int[] icons = new int[]{
            R.drawable.zobiawa,
            R.drawable.general
    };

    // Integer point to arrow image
    int arrow = R.drawable.go;
    TextView textViewCategory;
    View rowmodel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<>();

        for(int i=0;i<categories.length;i++){
            HashMap<String, String> hm = new HashMap<>();
            hm.put("categories", categories[i].toString());
            hm.put("icons", Integer.toString(icons[i]));
            hm.put("arrow", Integer.toString(arrow));
            aList.add(hm);
        }
       // HashMap<String, String> hm = new HashMap<>();
       // hm.put("categories",null);
       // hm.put("icons",null);
       // hm.put("arrow",null);
      //  aList.add(hm);

        // Keys used in Hashmap
        String[] from = { "icons","categories","arrow" };

        // Ids of views in listview_layout
        int[] to = { R.id.imageViewModel,R.id.textViewModel,R.id.imageViewArrow};


        rowmodel = inflater.inflate(R.layout.rowmodel, null, false);
        textViewCategory = (TextView) rowmodel.findViewById(R.id.textViewModel);

        Typeface Custom = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Walkway.ttf");
        textViewCategory.setTypeface(Custom);

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.rowmodel, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        try{
            Intent intent = new Intent(getActivity(), GameActivity.class);
            intent.putExtra("Level", Level.EXPERT);
            intent.putExtra("Category", categories[position]);
            startActivity(intent);
        }
        catch(Exception e){}
    }


}
