package io.github.ganeshbsub.stylighttest;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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

        //String[] brandsBackup = {"Clinique", "adidas", "String Furniture", "Chi Chi London", "Rosefield", "Lancaster", "Puma", "IKEA"};
        String[] backup = {};
        dataLoadProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        URL url = NetworkUtils.buildUrl();
        new QueryStylightTask().execute(url);

        allBrands = BrandData.getBrandData(backup);
        brandRecyclerView = (RecyclerView) findViewById(R.id.rv_all_brands);
        brandAdaptor = new BrandAdapter(MainActivity.this, allBrands);

        brandRecyclerView.setAdapter(brandAdaptor);
        brandRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

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
                JSONObject initialResults = new JSONObject(queryResult);
                JSONArray brandsArray = initialResults.getJSONArray("brands");
                Log.d("JSON", String.valueOf(brandsArray));
                int noOfBrands = brandsArray.length();

                brands = new String[noOfBrands];
                for(int index = 0; index<brandsArray.length(); index++){
                    brands[index] = brandsArray.getJSONObject(index).getString("name");
                    Log.d("Object", brandsArray.getJSONObject(index).getString("name"));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return brands;
        }

        @Override
        protected void onPostExecute(String[] queryResults) {
            dataLoadProgressBar.setVisibility(View.INVISIBLE);

            if(queryResults != null) {
                allBrands = BrandData.getBrandData(queryResults);
                brandAdaptor.newData(allBrands);
            }
        }
    }
}
