package ebnrdwan.app.android.autovia.Requests;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ebnrdwan.app.android.autovia.R;
import ebnrdwan.app.android.autovia.Requests.CarCareRequest.CarCare_RequestF;
import ebnrdwan.app.android.autovia.Requests.Road_Request.Road_RequestF;
import ebnrdwan.app.android.autovia.Requests.WorkshopRequest.Workshop_RequestF;

public class MyRequests extends AppCompatActivity {


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_requests);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        pagerAdapter.addFragments(new Road_RequestF(),"Road Assistance");
        pagerAdapter.addFragments(new CarCare_RequestF(),"Car Care");
        pagerAdapter.addFragments(new Workshop_RequestF(),"Workshop");
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


}
