package com.example.sk.sedlak.appka;
 
import java.util.ArrayList;

import sk.sedlak.appka.imageadapter.GridViewAdapter;
import sk.sedlak.appka.sliderhelper.AppConstant;
import sk.sedlak.appka.sliderhelper.Utils;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
 
public class Tab2PhotoFragment extends Fragment {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setHasOptionsMenu(true);
    }
 
    private Utils utils;
    private ArrayList<String> imagePaths = new ArrayList<String>();
    private GridViewAdapter adapter;
    private GridView gridView;
    private int columnWidth;
 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        
    	gridView = (GridView)inflater.inflate(R.layout.grid_view, container, false);
 
       
 
        utils = new Utils(getActivity());
 
        
        InitializeGridLayout();
 
        
        imagePaths = utils.getFilePaths();
 
        
        adapter = new GridViewAdapter(getActivity(), imagePaths,
                columnWidth);
 
        
        ((GridView) gridView).setAdapter(adapter);
        
        return gridView;
    }
 
    private void InitializeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AppConstant.GRID_PADDING, r.getDisplayMetrics());
 
        columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);
 
        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }
    
    @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.tab2_photo_fragment, menu);
	}
 
}