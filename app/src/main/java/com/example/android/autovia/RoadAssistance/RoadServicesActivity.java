package com.example.android.autovia.RoadAssistance;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.android.autovia.R;
import com.example.android.autovia.utility.ItemclickforRecycler;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;

public class RoadServicesActivity extends AppCompatActivity  {
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_services);
        RecyclerView rvRoad = (RecyclerView) findViewById(R.id.roadServieRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvRoad.setLayoutManager(linearLayoutManager);
//        rvRoad.setLayoutManager(new GridLayoutManager(this, 2));
        ItemclickforRecycler.addTo(rvRoad).setOnItemClickListener(new ItemclickforRecycler.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(RoadServicesActivity.this, "service Test of item " + position, Toast.LENGTH_SHORT).show();
                BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
//                bottomSheet.showWithSheetView(LayoutInflater.from(CarCareActivity.this).inflate(R.layout.custom_view, bottomSheet, false));

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                RoadRequestDialogF dialogF = new RoadRequestDialogF();
                dialogF.show(fragmentTransaction,"ALRT");
                Snackbar.make(findViewById(R.id.roadServieRecycler),"heey yeb",Snackbar.LENGTH_SHORT).show();
            }
        });

        RoadAssistAdapter assistAdapter = new RoadAssistAdapter(this, getmodels());
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

}

