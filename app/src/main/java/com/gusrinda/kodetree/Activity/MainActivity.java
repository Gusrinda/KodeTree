package com.gusrinda.kodetree.Activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.gusrinda.kodetree.Fragment.AccountFragment;
import com.gusrinda.kodetree.Fragment.AddFragment;
import com.gusrinda.kodetree.Fragment.HomeFragment;
import com.gusrinda.kodetree.Fragment.LocationFragment;
import com.gusrinda.kodetree.Fragment.TriviaFragment;
import com.gusrinda.kodetree.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private  BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav = findViewById(R.id.nav_view);
        nav.setOnNavigationItemSelectedListener(this);
        nav.setSelectedItemId(R.id.navigation_home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_tambah:
                fragment = new AddFragment();
                break;

            case R.id.navigation_lokasi:
                fragment = new LocationFragment();
                break;

            case R.id.navigation_trivia:
                fragment = new TriviaFragment();
                break;

            case R.id.navigation_akun:
                fragment = new AccountFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
