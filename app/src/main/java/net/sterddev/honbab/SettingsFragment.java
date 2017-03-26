package net.sterddev.honbab;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class SettingsFragment extends Fragment {

    FrameLayout fl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        //fl = (FrameLayout) v.findViewById(R.id.pref_info);
        getFragmentManager().beginTransaction().replace(R.id.pref_info, new pref()).commit();
        return v;
    }
    public static class pref extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_settings);

            /*final String key = Integer.toString(100 * j + i);
            final Preference cl = findPreference(key);
            if (cl != null)
                cl.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    public boolean onPreferenceClick(Preference pref) {
                        Intent myIntent = new Intent(getActivity(), ScriptActivity.class);
                        myIntent.putExtra("ep", key);
                        startActivity(myIntent);
                        return true;
                    }
                });
              */
        }

    }
}