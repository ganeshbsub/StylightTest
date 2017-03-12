package io.github.ganeshbsub.stylighttest;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zeo on 12-03-2017.
 */

public class BrandData {

    public static ArrayList<BrandInfo> getBrandData(){
        ArrayList<BrandInfo> allBrands = new ArrayList<BrandInfo>();


        String[] brands = {"Clinique", "adidas", "String Furniture", "Chi Chi London", "Rosefield", "Lancaster", "Puma", "IKEA"};
        allBrands.add(new BrandInfo("Selected Brands", 0));
        allBrands.add(new BrandInfo("All Brands", 0));

        int i = 2;
        for (String brand: brands){
            BrandInfo tempInfo = new BrandInfo(brand, 1);

            tempInfo.checked = false;
            tempInfo.positionInArray = i++;

            allBrands.add(tempInfo);
        }

        return allBrands;
    }



}
