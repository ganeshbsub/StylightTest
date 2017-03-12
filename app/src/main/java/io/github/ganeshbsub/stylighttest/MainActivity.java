package io.github.ganeshbsub.stylighttest;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    RecyclerView brandRecyclerView;
    BrandAdapter brandAdaptor;
    ArrayList<BrandInfo> allBrands;
    ArrayList<BrandInfo> selectedBrands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allBrands = BrandData.getBrandData();
        selectedBrands = null;
        for( BrandInfo brand: allBrands){
            Log.d("Brands", String.valueOf(brand.positionInArray));
        }

        brandRecyclerView = (RecyclerView) findViewById(R.id.rv_all_brands);
        brandAdaptor = new BrandAdapter(this, allBrands);

        brandRecyclerView.setAdapter(brandAdaptor);
        brandRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
