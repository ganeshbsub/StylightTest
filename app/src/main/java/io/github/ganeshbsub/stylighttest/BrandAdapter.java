package io.github.ganeshbsub.stylighttest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zeo on 12-03-2017.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {
    ArrayList<BrandInfo> brands;
    int lastSelectedPosition;
    Context context;
    LayoutInflater inflater;

    public BrandAdapter(Context context, ArrayList<BrandInfo> brands) {
        this.context = context;
        this.brands = brands;
        inflater = LayoutInflater.from(context);
        lastSelectedPosition = 1;
    }

    @Override
    public int getItemViewType(int position) {
        return brands.get(position).type;
    }

    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == 0){
            view = inflater.inflate(R.layout.row_header, parent, false);
        } else {
            view = inflater.inflate(R.layout.row_brand, parent, false);
        }
        BrandViewHolder holder = new BrandViewHolder(view, viewType);

        return holder;
    }

    @Override
    public void onBindViewHolder(final BrandViewHolder holder, final int position) {
        holder.text.setText(brands.get(position).brandName);

        if(holder.view != null) {
            holder.brandCheckBox.setChecked(brands.get(position).checked);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BrandInfo selectedBrand;
                    if(holder.brandCheckBox.isChecked() == false){
                        //If it is an unselected brand, then add it to selected
                        holder.brandCheckBox.setChecked(true);
                        brands.get(position).checked = true;

                        selectedBrand = new BrandInfo(brands.get(position).brandName, 2, true, brands.get(position).positionInArray);
                        addSelected(selectedBrand);
                        lastSelectedPosition++;
                    }else {
                        holder.brandCheckBox.setChecked(false);
                        selectedBrand = brands.get(position);

                        if(selectedBrand.type == 2) {
                            brands.get(lastSelectedPosition + selectedBrand.positionInArray - 1).checked = false;
                            removeFromSelected(position);
                            lastSelectedPosition--;
                        }
                        else if(selectedBrand.type == 1) {
                            brands.get(position).checked = false;
                            int actualBrandPosition = findMatchingBrandPosition(selectedBrand.positionInArray);
                            Log.d("KEY", String.valueOf(actualBrandPosition));
                            if(actualBrandPosition != -1) {
                                removeFromSelected(actualBrandPosition);
                                lastSelectedPosition--;
                            }
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
    private void removeFromSelected(int position) {
        brands.remove(position);
        notifyItemRemoved(position);
    }

    private void addSelected(BrandInfo selectedBrand) {
        brands.add(lastSelectedPosition, selectedBrand);
        notifyItemInserted(lastSelectedPosition);
    }

    private int findMatchingBrandPosition(int key) {
        for(int currentIndex = 0; currentIndex < lastSelectedPosition; currentIndex++){
            if(brands.get(currentIndex).matchBrand(key))
                return currentIndex;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    class BrandViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        CheckBox brandCheckBox;
        View view;
        int viewType;

        public BrandViewHolder(View itemView, int viewType) {
            super(itemView);

            this.viewType = viewType;
            if (viewType == 0){
                text = (TextView) itemView.findViewById(R.id.header_text);
                view = null;
            } else {
                text = (TextView) itemView.findViewById(R.id.brand_name);
                brandCheckBox = (CheckBox) itemView.findViewById(R.id.brand_checkbox);
                view = itemView;
            }

        }
    }
}
