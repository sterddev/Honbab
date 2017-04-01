package net.sterddev.honbab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.Vector;


public class DashboardFragment extends Fragment {

    static Boolean mcdst = false, bgkst = false, kfcst = false, sbwst = false, kkdst = false, stbst = false, gtfst = false, cfust = false, sevst = false;
    CardView mcd, bgk, kfc, sbw, kkd, stb, gtf, cfu, sev, suggest;
    private Context mContext;
    EditText edt;
    TextInputLayout edl;
    View v;
    MainActivity obj;
        @Override
        public void onAttach(final Activity activity) {
            super.onAttach(activity);
            mContext = activity;
            obj = (MainActivity) activity;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_dashboard, container, false);
            mcd = (CardView) v.findViewById(R.id.cv1); bgk = (CardView) v.findViewById(R.id.cv2);
            kfc = (CardView) v.findViewById(R.id.cv3); sbw = (CardView) v.findViewById(R.id.cv4);
            kkd = (CardView) v.findViewById(R.id.cv5); stb = (CardView) v.findViewById(R.id.cv6);
            gtf = (CardView) v.findViewById(R.id.cv5); cfu = (CardView) v.findViewById(R.id.cv6);
            sev = (CardView) v.findViewById(R.id.cv5);
            suggest = (CardView) v.findViewById(R.id.cv10);

            mcdst = false; bgkst = false; kfcst = false; sbwst = false; kkdst = false; stbst = false;
            suggest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                    LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View dialogView = inflater.inflate(R.layout.dialogue_suggest, null);
                    dialogBuilder.setView(dialogView);

                    edt = (EditText) dialogView.findViewById(R.id.textinput);
                    edl = (TextInputLayout) dialogView.findViewById(R.id.textlayout);

                    dialogBuilder.setTitle("Suggest Brand Name");
                    dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
                    final AlertDialog dialog = dialogBuilder.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(isvalid()) {
                                (new HttpTask()).execute(edt.getText().toString());
                                dialog.dismiss();
                            }
                        }
                    });
                }
            });
            mcd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mcdst) {
                        mcdst = false;
                        mcd.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        mcdst = true;
                        mcd.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            bgk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bgkst) {
                        bgkst = false;
                        bgk.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        bgkst = true;
                        bgk.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            kfc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(kfcst) {
                        kfcst = false;
                        kfc.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        kfcst = true;
                        kfc.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            sbw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sbwst) {
                        sbwst = false;
                        sbw.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        sbwst = true;
                        sbw.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            kkd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(kkdst) {
                        kkdst = false;
                        kkd.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        kkdst = true;
                        kkd.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            stb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(stbst) {
                        stbst = false;
                        stb.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        stbst = true;
                        stb.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            gtf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(gtfst) {
                        gtfst = false;
                        gtf.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        gtfst = true;
                        gtf.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            cfu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cfust) {
                        cfust = false;
                        cfu.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        cfust = true;
                        cfu.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            sev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sevst) {
                        sevst = false;
                        sev.setBackgroundResource(R.drawable.card_unselected);
                    } else {
                        sevst = true;
                        sev.setBackgroundResource(R.drawable.card_selected);
                    }
                }
            });

            return v;
        }

        @org.jetbrains.annotations.Contract(pure = true)
        static boolean cmcd(){ return mcdst; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean cbgk(){ return bgkst; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean ckfc(){ return kfcst; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean csbw(){ return sbwst; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean ckkd(){ return kkdst; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean cstb(){ return stbst; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean cgtf(){ return gtfst; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean ccfu(){ return cfust; }
        @org.jetbrains.annotations.Contract(pure = true)
        static boolean csev(){ return sevst; }


        ProgressDialog mDialog;
        private class HttpTask extends AsyncTask<String, Void, Boolean> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                mDialog = new ProgressDialog(mContext);
                mDialog.setIndeterminate(true);
                mDialog.setMessage("Please Wait...");
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
            }

            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    HttpPost postRequest = new HttpPost(Constants.GOOGLE_DRIVE_API_KEY);

                    Vector<NameValuePair> nameValue = new Vector<>();
                    nameValue.add(new BasicNameValuePair("sheet_name", "Brand"));
                    nameValue.add(new BasicNameValuePair("Brand", params[0]));

                    HttpEntity Entity = new UrlEncodedFormEntity(nameValue, "UTF-8");
                    postRequest.setEntity(Entity);

                    HttpClient mClient = new DefaultHttpClient();
                    mClient.execute(postRequest);

                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;
            }

            protected void onPostExecute(Boolean value) {
                super.onPostExecute(value);
                if (mDialog != null) {
                    mDialog.dismiss();
                    mDialog = null;
                }
                MainActivity.makeSnackBar("Suggestion Sent Successfully", Snackbar.LENGTH_SHORT);
            }
        }

    private boolean isvalid() {
        if (edt.getText().toString().trim().isEmpty()) {
            edl.setError("Please Enter More Than One Letter.");
            edt.requestFocus();
            return false;
        } else {
            edl.setErrorEnabled(false);
        }

        return true;
    }
}