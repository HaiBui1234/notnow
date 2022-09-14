package com.example.assignment_buiquanghaiph28562;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.assignment_buiquanghaiph28562.fragment.fragmentGioiThieu.fragmentGioiThieuz;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangChi.fragmentKhoangChiz;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.fragmentKhoangthuz;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentThongKe.fragmentThongKez;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                0, 0);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.isDrawerOpen(navigationView);
            }
        });
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new fragmentKhoangthuz());
    }

    public void anhXa() {
        drawerLayout = findViewById(R.id.id_DrawerLayout);
        toolbar = findViewById(R.id.id_tooBar);
        navigationView = findViewById(R.id.id_navigation);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.thu) {
            replaceFragment(new fragmentKhoangthuz());
            drawerLayout.closeDrawer(navigationView);
        } else if (id == R.id.chi) {
            replaceFragment(new fragmentKhoangChiz());
            drawerLayout.closeDrawer(navigationView);
        } else if (id == R.id.thongKe) {
            replaceFragment(new fragmentThongKez());
            drawerLayout.closeDrawer(navigationView);
        } else if (id == R.id.gioiThieu) {
            replaceFragment(new fragmentGioiThieuz());
            drawerLayout.closeDrawer(navigationView);
        } else if (id == R.id.thoat) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thoát");
            builder.setMessage("Bạn có chắc muốn thoát");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 System.exit(0);
                }
            });
            builder.setNegativeButton("No",null);
            builder.show();
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_frameLayout, fragment);
        transaction.commit();
    }
}