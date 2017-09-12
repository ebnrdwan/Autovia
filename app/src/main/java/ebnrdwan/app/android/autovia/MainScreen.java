package ebnrdwan.app.android.autovia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ebnrdwan.app.android.autovia.AccountSessions.LoginActivity;
import ebnrdwan.app.android.autovia.CarCare.CarCareActivity;
import ebnrdwan.app.android.autovia.Requests.MyRequests;
import ebnrdwan.app.android.autovia.RoadAssistance.RoadServicesActivity;
import ebnrdwan.app.android.autovia.Workshop.WorkshopActivity;
import ebnrdwan.app.android.autovia.info.About_activity;

public class MainScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    CardView roadCard;
    CardView carCard;
    CardView workshopCard;
    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();


    }


    private void initializeViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        roadCard = (CardView) findViewById(R.id.road_service_card);
        carCard = (CardView) findViewById(R.id.carcare_card);
        workshopCard = (CardView) findViewById(R.id.workshop_card);
        roadCard.setOnClickListener(this);
        carCard.setOnClickListener(this);
        workshopCard.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Requests_menu) {

            startActivity(new Intent(MainScreen.this, MyRequests.class));
            // Handle the camera action
        } else if (id == R.id.profile_menu) {

        } else if (id == R.id.signing) {
            startActivity(new Intent(MainScreen.this, LoginActivity.class));

        } else if (id == R.id.about_menu) {

            startActivity(new Intent(MainScreen.this, About_activity.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.road_service_card:
                Snackbar.make(findViewById(R.id.road_service_card), "you clicked on road service", Snackbar.LENGTH_SHORT).show();
                startActivity(new Intent(MainScreen.this, RoadServicesActivity.class));
                break;
            case R.id.carcare_card:
                startActivity(new Intent(MainScreen.this, CarCareActivity.class));
                break;
            case R.id.workshop_card:
                startActivity(new Intent(MainScreen.this, WorkshopActivity.class));
                break;


        }
    }
}
