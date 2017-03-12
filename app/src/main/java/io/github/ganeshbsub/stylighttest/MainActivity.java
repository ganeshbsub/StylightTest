package io.github.ganeshbsub.stylighttest;

import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    RecyclerView brandRecyclerView;
    BrandAdapter brandAdaptor;
    ArrayList<BrandInfo> allBrands;
    ProgressBar dataLoadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataLoadProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        allBrands = BrandData.getBrandData();

        brandRecyclerView = (RecyclerView) findViewById(R.id.rv_all_brands);
        brandAdaptor = new BrandAdapter(this, allBrands);

        brandRecyclerView.setAdapter(brandAdaptor);
        brandRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public class QueryStylightTask extends AsyncTask<URL, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dataLoadProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(URL... params) {
            URL searchUrl = params[0];
            String queryResult = null;
            String[] brands = null;
            try {
                queryResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSO
            return queryResult;
        }

        @Override
        protected void onPostExecute(String queryResults) {
            dataLoadProgressBar.setVisibility(View.INVISIBLE);
            if (queryResults != null) {

            } else {

            }
        }
    }
}
