package com.example.duolingo;

import static com.example.duolingo.R.id.nav_host_fragment_content_main;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.duolingo.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FrameLayout main_frame;
    private TextView drawerProfileName, drawerProfileText;

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId())
                    {
                        case R.id.navigation_home:
                            setFragement(new CategoryFragment());
                           // bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                            return true;

                        case R.id.navigation_leaderboard:
                            setFragement(new LeaderBoardFragment());
                          //  bottomNavigationView.setSelectedItemId(R.id.navigation_leaderboard);
                            return true;

                        case R.id.navigation_account:
                            setFragement(new AccountFragment());
                           // bottomNavigationView.setSelectedItemId(R.id.navigation_account);
                            return true;


                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        bottomNavigationView=findViewById(R.id.bottom_nav_bar);
        main_frame= findViewById(R.id.main_frame);

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                //αλλαγή από stack overflow
                .setDrawerLayout(drawer)
                .build();


        drawerProfileName = navigationView.getHeaderView(0).findViewById(R.id.nav_drawer_name);
        drawerProfileText = navigationView.getHeaderView(0).findViewById(R.id.nav_drawer_text_img);

        String name = DbQuery.myProfile.getName();
        drawerProfileName.setText(name);

        drawerProfileText.setText(name.toUpperCase(Locale.ROOT).substring(0,1));

        setFragement(new CategoryFragment());

        NavController navController = Navigation.findNavController(this, nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setFragement(Fragment fragement)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(),fragement);
        transaction.commit();

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Log.d("edo","mphka1");
        int id = menuItem.getItemId();
        if (id == R.id.nav_home) {
            setFragement(new CategoryFragment());
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
            return true;
        }
        else if (id == R.id.nav_leaderboard) {
            // Handle the gallery action
            setFragement(new LeaderBoardFragment());
            bottomNavigationView.setSelectedItemId(R.id.navigation_leaderboard);
            return true;

        }
        else if (id == R.id.nav_account) {
            // Handle the slideshow action
            bottomNavigationView.setSelectedItemId(R.id.navigation_account);
            setFragement(new AccountFragment());
            return true;

        }



        DrawerLayout drawer = binding.drawerLayout;
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}