package com.example.lab1;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.lab1.fragments.CuadrosFragment;
import com.example.lab1.fragments.HomeFragment;
import com.example.lab1.fragments.MapaFragment;
import com.example.lab1.fragments.NotificacionFragment;
import com.example.lab1.fragments.ScannerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private HomeFragment homeFragment = null;
    private CuadrosFragment cuadrosFragment = null;
    private MapaFragment mapaFragment = null;
    private NotificacionFragment notificacionFragment = null;
    private ScannerFragment scannerFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_home) {
                    homeFragment = HomeFragment.newInstance("", "");
                    loadFragment(homeFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_cuadros) {
                    cuadrosFragment = CuadrosFragment.newInstance();
                    loadFragment(cuadrosFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_mapa) {
                    mapaFragment = MapaFragment.newInstance("", "");
                    loadFragment(mapaFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_notificacion) {
                    notificacionFragment = NotificacionFragment.newInstance("", "");
                    loadFragment(notificacionFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_scanner) {
                    scannerFragment = ScannerFragment.newInstance("", "");
                    loadFragment(scannerFragment);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        if (fragmentManager != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main, fragment);
            fragmentTransaction.commit();
        }
    }
}
