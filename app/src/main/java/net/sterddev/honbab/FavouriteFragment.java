package net.sterddev.honbab;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.id.list;
import static android.content.Context.MODE_PRIVATE;


public class FavouriteFragment extends Fragment {
    View v;
    TextView tv;
    SQLiteHelper SQLite;
    ListAdapter adapter;
    ListView list;
    ArrayList<HashMap<String, String>> personList;
    private static final String TAG_TITLE = "title";
    private static final String TAG_ADDRESS ="address";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        list = (ListView) v.findViewById(R.id.listView);
        //SQLite = new SQLiteHelper(getActivity().getApplicationContext(), "favourite.db", null, 1);
        //tv = (TextView) v.findViewById(R.id.list);
        //tv.setText(SQLite.getResult());
        //showList();
        return v;
    }

    /*protected void showList(){

        try {

            SQLiteDatabase db = getReadableDatabase();
            SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);


            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = ReadDB.rawQuery("SELECT * FROM FAVOURITE", null);

            if (c != null) {


                if (c.moveToFirst()) {
                    do {

                        //테이블에서 두개의 컬럼값을 가져와서
                        String TITLE = c.getString(c.getColumnIndex("title"));
                        String ADDRESS = c.getString(c.getColumnIndex("address"));

                        //HashMap에 넣
                        HashMap<String,String> persons = new HashMap<String, String>();

                        persons.put(TAG_TITLE, TITLE);
                        persons.put(TAG_ADDRESS, ADDRESS);

                        //ArrayList에 추가합니다..
                        personList.add(persons);

                    } while (c.moveToNext());
                }
            }

            ReadDB.close();


            //새로운 apapter를 생성하여 데이터를 넣은 후..
            adapter = new SimpleAdapter(
                    v.getContext(), personList, R.layout.list_item,
                    new String[]{TAG_TITLE,TAG_ADDRESS},
                    new int[]{ R.id.name, R.id.phone}
            );


            //화면에 보여주기 위해 Listview에 연결합니다.
            list.setAdapter(adapter);


        } catch (SQLiteException se) {
            Toast.makeText(getActivity().getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }

    }*/
}