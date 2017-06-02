package net.sterddev.honbab;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    String title, address, phone, category;
    TextView at, pt, ct, dt;
    double distance;
    boolean toggle = false;
    Toolbar toolbar;
    private Menu menu;
    SQLiteHelper SQLite;
    long now = System.currentTimeMillis();
    Date date = new Date(now);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLite = new SQLiteHelper(getApplicationContext(), "favourite.db", null, 1);

        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        address = intent.getExtras().getString("address");
        phone = intent.getExtras().getString("phone");
        category = intent.getExtras().getString("category");
        distance = intent.getExtras().getDouble("distance");

        dt = (TextView) findViewById(R.id.distance);
        at = (TextView) findViewById(R.id.address);
        pt = (TextView) findViewById(R.id.phone);
        ct = (TextView) findViewById(R.id.category);

        dt.setText("거리 : " + Double.toString(distance));
        at.setText("주소 : " + address);
        pt.setText("전화 번호 : " + phone);
        ct.setText("분류 : " + category);
        toolbar.setTitle(title);
        getSupportActionBar().setTitle(title);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favourite:
                if(toggle){
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String s = formatter.format(date);
                    menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_star_border));
                    menu.getItem(0).setTitle("Add as Favourite");
                    toggle = false;
                    SQLite.insert(s, title, address);
                } else {
                    menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favourite_black_24dp));
                    menu.getItem(0).setTitle("Remove From Favourite");
                    toggle = true;
                    SQLite.delete(title);
                }
                break;
            default:
                break;
        }

        return true;
    }
}
