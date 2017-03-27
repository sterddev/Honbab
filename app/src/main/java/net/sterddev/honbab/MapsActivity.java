package net.sterddev.honbab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import net.daum.mf.map.api.MapView;

public class MapsActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey(Constants.DAUM_MAPS_API_KEY);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.map_view);
        container.addView(mapView);


    }

}
