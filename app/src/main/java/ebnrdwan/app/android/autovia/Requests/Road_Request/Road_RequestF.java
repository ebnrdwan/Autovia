package ebnrdwan.app.android.autovia.Requests.Road_Request;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ebnrdwan.app.android.autovia.R;

public class Road_RequestF extends Fragment {
    Road_Request_Adapter adapter;
    RecyclerView recyclerRequest;

    public Road_RequestF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_road_request, container, false);

       recyclerRequest= (RecyclerView) root.findViewById(R.id.recycler_road);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerRequest.setLayoutManager(linearLayoutManager);
        ArrayList<Road_Request_Model> list = new ArrayList<>();
        list.add(new Road_Request_Model("33", "Urgent", "20-10-2017", 1));
        list.add(new Road_Request_Model("33", "Urgent", "20-10-2017", 2));
        list.add(new Road_Request_Model("33", "Urgent", "20-10-2017", 3));
        list.add(new Road_Request_Model("33", "Urgent", "20-10-2017", 1));
        list.add(new Road_Request_Model("33", "Urgent", "20-10-2017", 4));
      adapter = new Road_Request_Adapter(getContext(), list);
        recyclerRequest.setAdapter(adapter);

        return root;
    }


}
