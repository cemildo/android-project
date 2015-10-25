package com.cemil.dogan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import domain.Gadget;
import domain.Loan;
import domain.Reservation;
import fragments.GadgetFragment;
import fragments.AusleihenDetailFragment;
import fragments.AusleihenFragment;
import fragments.SettingsFragment;
import fragments.DetailsFragment;
import service.Callback;
import service.LibraryService;


public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ItemClickListener  {

    private DrawerLayout drawer;
    private View content;
    private String header_email;
    private String header_name;
    private Toolbar toolbar;
    private GadgetFragment gadgetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget);

        //drawer_layout --> activity_main.xml
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        content = findViewById(R.id.content);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            header_email = extras.getString("email");
            header_name =  extras.getString("name");
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //populates hamburger menu
        toolbar.setTitle("Gadgets");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //gets the menu element, menu element has header and menu parts...
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        //getFragmentManager().beginTransaction().replace(R.id.content, new GadgetFragment()).commit();

        gadgetFragment = new GadgetFragment();
        getFragmentManager().beginTransaction().replace(R.id.content,gadgetFragment).addToBackStack(null).commit();

    }

    @Override
    public void onStart(){
        super.onStart();
        toolbar.setTitle("Gadgets");
    }


    private void mkSnack() {
        Snackbar snackbar = Snackbar.make(content, "Hello MGE!", Snackbar.LENGTH_LONG);
        snackbar.setAction("Do it again!", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mkSnack();
            }
        });
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu ){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //opens main menu and setting menu
    @Override
    public boolean  onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                TextView name = (TextView) findViewById(R.id.header_name);
                TextView email = (TextView) findViewById(R.id.header_email);
                name.setText(header_name);
                email.setText(header_email);
                break;
            case R.id.action_settings:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new SettingsFragment())
                        .commit();
                break;
            case R.id.action_logout:
                LibraryService.logout(new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean result) {
                        Toast.makeText(getBaseContext(), "Logged out", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getBaseContext(), "Could not be logged out", Toast.LENGTH_LONG).show();
                    }
                });


        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.drawerHome :
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new GadgetFragment())
                        .commit();
                break;
        case R.id.loans :
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new AusleihenFragment())
                    .commit();
            break;


        }
        mkSnack();
        menuItem.setChecked(true);
        drawer.closeDrawers();
        return true;
    }

    @Override
    public void onItemClicked(Loan currentLoan) {
        toolbar.setTitle(currentLoan.getGadget().getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle args = new Bundle();
        args.putSerializable("ausleihen", currentLoan);
        AusleihenDetailFragment detailsFragment = new AusleihenDetailFragment();
        detailsFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content, detailsFragment)
                .addToBackStack(null).commit();
    }



    @Override
    public void onItemClicked(Gadget currentGadget) {
        toolbar.setTitle(currentGadget.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle args = new Bundle();
        args.putSerializable("gadget", currentGadget);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content, detailsFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }
}
