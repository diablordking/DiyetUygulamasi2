package com.example.statistic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.example.customvision_sample.ClassifierActivity;
import com.example.hotornot.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Statisticpage extends AppCompatActivity                 implements NavigationView.OnNavigationItemSelectedListener, stats.OnFragmentInteractionListener, profil.OnFragmentInteractionListener ,news.OnFragmentInteractionListener,mainFragment.OnFragmentInteractionListener
    {



    private TextView mTextMessage;
        private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseReference  databaseReferenceCustomers = FirebaseDatabase.getInstance().getReference("customers");
        setContentView(R.layout.activity_statisticpage);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        Fragment  selectedFragment = mainFragment.newInstance("ds","33");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
        mAuth = FirebaseAuth.getInstance();

        mTextMessage = (TextView) findViewById(R.id.message);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = mainFragment.newInstance("ds","33");
                                break;
                            case R.id.navigation_dashboard:
                                selectedFragment = stats.newInstance("sda","das");
                                break;
                            case R.id.navigation_notifications:
                                selectedFragment = profil.newInstance("sda","das");
                                break;
                            case R.id.take_photo:
                                startActivity(new Intent(Statisticpage.this, MainActivity.class));
                                return true;

                            case R.id.action_logout:
                                Toast.makeText(Statisticpage.this, "Log Out", Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                                Intent intent = new Intent(Statisticpage.this, LoginActivity.class);
                                startActivity(intent);
                                return true;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

    }


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return false;
        }

        @Override
        public void onFragmentInteraction(Uri uri) {

        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }
    }

