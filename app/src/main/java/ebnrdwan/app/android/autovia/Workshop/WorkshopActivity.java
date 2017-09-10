package ebnrdwan.app.android.autovia.Workshop;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;

import ebnrdwan.app.android.autovia.R;
import ebnrdwan.app.android.autovia.utility.ItemclickforRecycler;

public class WorkshopActivity extends AppCompatActivity  {

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
                Toast.makeText(WorkshopActivity.this, "service Test of item " + position, Toast.LENGTH_SHORT).show();
                BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
//                bottomSheet.showWithSheetView(LayoutInflater.from(WorkshopActivity.this).inflate(R.layout.custom_view, bottomSheet, false));
                Snackbar.make(findViewById(R.id.roadServieRecycler),"heey yeb",Snackbar.LENGTH_SHORT).show();
            }
        });

        WorkshopAdapter assistAdapter = new WorkshopAdapter(this, getmodels());
        rvRoad.setAdapter(assistAdapter);


    }
    private ArrayList<WorkshopModel> getmodels() {
        ArrayList<WorkshopModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new WorkshopModel("clean car", 100, "http"));
        modelArrayList.add(new WorkshopModel("clean car", 100, "http"));
        modelArrayList.add(new WorkshopModel("clean car", 100, "http"));
        modelArrayList.add(new WorkshopModel("clean car", 100, "http"));
        modelArrayList.add(new WorkshopModel("clean car", 100, "http"));
        modelArrayList.add(new WorkshopModel("clean car", 100, "http"));
        modelArrayList.add(new WorkshopModel("clean car", 100, "http"));
        return modelArrayList;
    }

}

