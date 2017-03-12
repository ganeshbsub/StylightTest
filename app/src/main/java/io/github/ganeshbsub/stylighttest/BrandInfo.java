package io.github.ganeshbsub.stylighttest;

/**
 * Created by Zeo on 12-03-2017.
 */

public class BrandInfo {
    public String brandName;
    public boolean checked;
    public int positionInArray;
    public int type; //selected = 2, all = 1, header = 0

    public BrandInfo(String brandName, int type){
        this.brandName = brandName;
        this.type = type;
    }

    public BrandInfo(String brandName, int type, boolean checked, int positionInArray){
        this.brandName = brandName;
        this.positionInArray = positionInArray;
        this.type = type;
        this.checked = checked;
    }

    public boolean matchBrand(int key){
        if(key == positionInArray)
            return true;
        return false;
    }
}

