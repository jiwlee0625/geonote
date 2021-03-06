package com.jiwlee0625.geonote;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment3 = new SettingsFragment();
    final Fragment fragment2 = new MapsFragment();
    final Fragment fragment1 = new NotesFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_notes:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.navigation_maps:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;
                case R.id.navigation_settings:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };
    public void onClickLogOutBttn(View v) {
        FirebaseAuth.getInstance().signOut();
        goToLogin();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.fragmentContainer, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragmentContainer, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragmentContainer, fragment1, "1").commit();


    }

    @Override
    public void onStart() {
        super.onStart();
    }
    public void goToLogin() {
        Intent goToLogInIntent = new Intent(this, LoginActivity.class);
        startActivity(goToLogInIntent);
    }
}
