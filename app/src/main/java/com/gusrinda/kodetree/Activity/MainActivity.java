package com.gusrinda.kodetree.Activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.gusrinda.kodetree.Fragment.AccountFragment;
import com.gusrinda.kodetree.Fragment.AddFragment;
import com.gusrinda.kodetree.Fragment.HomeFragment;
import com.gusrinda.kodetree.Fragment.LocationFragment;
import com.gusrinda.kodetree.Fragment.TriviaFragment;
import com.gusrinda.kodetree.Model.User;
import com.gusrinda.kodetree.R;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, User.UserValueListener {


    private  BottomNavigationView nav;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav = findViewById(R.id.nav_view);
        welcome = findViewById(R.id.welcomeText);
        nav.setOnNavigationItemSelectedListener(this);
        nav.setSelectedItemId(R.id.navigation_home);
        User.getCurrentUser(this);

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

    @Override
    public User onUserChange(User user) {
        welcome.setText("Selamat Datang ,"+user.getUsername());

        return user;
    }
}
