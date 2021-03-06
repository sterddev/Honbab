package net.sterddev.honbab;

import android.Manifest;
import android.app.*;
import android.content.*;
import android.location.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.google.android.gms.common.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.gun0912.tedpermission.*;

import java.io.IOException;
import java.util.*;

public class MainActivity extends AppCompatActivity implements
        ConnectionCallbacks,
        OnConnectionFailedListener,
        LocationListener{
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    private Location mLocation;
    TextView loc;
    Toolbar toolbar;
    FloatingActionButton fab;
    double lat, lng;
    String CURR = "Dashboard";
    static View viewPos = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.anim_in, R.animator.anim_out);

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    if(!CURR.equals("Dashboard")) {
                        CURR = "Dashboard";
                        fragmentTransaction.replace(R.id.frag, new DashboardFragment());
                        fab.show();
                    }
                    break;
                case R.id.navigation_favourite:
                    if(!CURR.equals("Favourite")) {
                        CURR = "Favourite";
                        fragmentTransaction.replace(R.id.frag, new FavouriteFragment());
                        fab.hide();
                    }
                    break;
                case R.id.navigation_settings:
                    if(!CURR.equals("Settings")) {
                        CURR = "Settings";
                        fragmentTransaction.replace(R.id.frag, new SettingsFragment());
                        fab.hide();
                    }
                    break;
            }

            fragmentTransaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frag, new DashboardFragment());
        fragmentTransaction.commit();
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        viewPos = findViewById(R.id.cl);
        loc = (TextView) findViewById(R.id.loc);
        loc.setText("Finding Location");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(1000);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DashboardFragment.cmcd() || DashboardFragment.cbgk() || DashboardFragment.ckfc()
                        || DashboardFragment.csbw() || DashboardFragment.ckkd() || DashboardFragment.cstb()
                        || DashboardFragment.cgtf() || DashboardFragment.ccfu() || DashboardFragment.csev()) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lng", lng);
                    intent.putExtra("mcd", DashboardFragment.cmcd()); intent.putExtra("bgk", DashboardFragment.cbgk()); intent.putExtra("kfc", DashboardFragment.ckfc());
                    intent.putExtra("sbw", DashboardFragment.csbw()); intent.putExtra("kkd", DashboardFragment.ckkd()); intent.putExtra("stb", DashboardFragment.cstb());
                    intent.putExtra("gtf", DashboardFragment.cgtf()); intent.putExtra("cfu", DashboardFragment.ccfu()); intent.putExtra("sev", DashboardFragment.csev());
                    startActivity(intent);
                } else {
                    makeSnackBar("Invalid Settings. Choose more than one item.", Snackbar.LENGTH_LONG);
                }
            }
        });

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            }


        };


        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission, you can not use this service\n\nPlease turn on permissions at [Permission]")
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

    }

    public static void makeSnackBar(String str, int length){
        Snackbar.make(viewPos, str, length).show();
    }
    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress ="Can't find current location.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                address = geocoder.getFromLocation(lat, lng, 1);

                if (address != null && address.size() > 0) {
                    String currentLocationAddress = address.get(0).getAdminArea() + " " + address.get(0).getLocality() + " "
                            + address.get(0).getThoroughfare();
                    nowAddress  = currentLocationAddress;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nowAddress;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation != null) {
            loc.setText(getAddress(this, mLocation.getLatitude(), mLocation.getLongitude()));
            lat = mLocation.getLatitude();
            lng = mLocation.getLongitude();
        } else {
            loc.setText("Can't find current location.");
        }
    }


    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    /**
     * If locationChanges change lat and long
     *
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }
}
