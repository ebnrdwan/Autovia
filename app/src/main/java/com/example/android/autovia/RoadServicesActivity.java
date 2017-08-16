package com.example.android.autovia;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.android.autovia.RoadAssistance.ItemClickinterface;
import com.example.android.autovia.RoadAssistance.RoadAssistAdapter;
import com.example.android.autovia.RoadAssistance.RoadServiceModel;

import java.util.ArrayList;

public class RoadServicesActivity extends AppCompatActivity implements ItemClickinterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_services);
        RecyclerView rvRoad = (RecyclerView) findViewById(R.id.roadServieRecycler);
        rvRoad.setLayoutManager(new GridLayoutManager(this, 2));
        ItemclickforRecycler.addTo(rvRoad).setOnItemClickListener(new ItemclickforRecycler.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(RoadServicesActivity.this, "service Test of item " + position, Toast.LENGTH_SHORT).show();
//                BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
//                bottomSheet.showWithSheetView(LayoutInflater.from(RoadServicesActivity.this).inflate(R.layout.custom_view, bottomSheet, false));
                Snackbar.make(findViewById(R.id.roadServieRecycler),"heey yeb",Snackbar.LENGTH_SHORT).show();
            }
        });

        RoadAssistAdapter assistAdapter = new RoadAssistAdapter(this, getmodels(),this);
        rvRoad.setAdapter(assistAdapter);


    }
    private ArrayList<RoadServiceModel> getmodels() {
        ArrayList<RoadServiceModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        return modelArrayList;
    }

    @Override
    public void OnClickListenerInterface(int position) {
        Toast.makeText(RoadServicesActivity.this, "service Test of item " + position, Toast.LENGTH_SHORT).show();
    }
}

