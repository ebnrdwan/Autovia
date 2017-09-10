package ebnrdwan.app.android.autovia.Requests.CarCareRequest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ebnrdwan.app.android.autovia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarCare_RequestF extends Fragment {


    public CarCare_RequestF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_car_care__request, container, false);
         RecyclerView recyclerRequest= (RecyclerView) root.findViewById(R.id.care_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerRequest.setLayoutManager(linearLayoutManager);
        ArrayList<Care_Request_Model> list = new ArrayList<>();
        list.add(new Care_Request_Model("233","Car Care","20-4-2020","",1));
        list.add(new Care_Request_Model("233","Car Care","20-4-2020","total cost is 33",2));
        list.add(new Care_Request_Model("233","Car Care","20-4-2020","no available service in the mean time",3));

       Care_Request_Adapter adapter = new Care_Request_Adapter(getContext(), list);
        recyclerRequest.setAdapter(adapter);

        return root;


    }

}
