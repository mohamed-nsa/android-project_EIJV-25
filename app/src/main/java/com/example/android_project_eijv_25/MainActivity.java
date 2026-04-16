package com.example.android_project_eijv_25;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        //setSupportActionBar(findViewById(R.id.my_toolbar));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment == null) throw new AssertionError();
        mapFragment.getMapAsync(this);

        String[] languages = getResources().getStringArray(R.array.options_carte);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, languages);
        AutoCompleteTextView autocompleteTV = findViewById(R.id.autoCompleteTextView);
        autocompleteTV.setAdapter(arrayAdapter);
    }

    // Afficher marqueur sur Amiens quand le map démarre
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        addMarkerOnMap(googleMap,49.894067,2.295753,"Amiens");
    }

    // Ajouter marqueur sur la map
    public void addMarkerOnMap(@NonNull GoogleMap googleMap, double latitude, double longtitude, String title){
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longtitude))
                .title(title));
    }
}