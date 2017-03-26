package net.sterddev.honbab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    private ArrayList<String> titl = new ArrayList<>();
    private ArrayList<String> snip = new ArrayList<>();
    double hlat, hlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        hlat = intent.getExtras().getDouble("lat");
        hlng = intent.getExtras().getDouble("lng");

    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(hlat, hlng))
                .title("현재 위치")
                .snippet("위도 :" + Double.toString(hlat) + ", 경도 :" + Double.toString(hlng))
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("ic_nav",100,100)));
        // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
        mMap.addMarker(markerOptions).showInfoWindow();

        //latlngs.add(new LatLng(35.857026, 128.649967)); //맥날 만촌DT
        latlngs.add(new LatLng(35.818717,128.5388877)); titl.add(new String("크리스피크림도넛 롯데상인점")); snip.add(new String("대구광역시 달서구 월배로 232 지하 1층"));
        for(int i = 0 ; i < latlngs.size(); i++) {
            options.position(latlngs.get(i));
            options.title(titl.get(i));
            options.snippet(snip.get(i));
            options.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("ic_pin_red", 100, 100)));
            googleMap.addMarker(options);
        }

        //mMap.addMarker(new MarkerOptions().position(new LatLng(hlat, hlng)).title("현재 위치"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(hlat, hlng)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(zoom);
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
}
