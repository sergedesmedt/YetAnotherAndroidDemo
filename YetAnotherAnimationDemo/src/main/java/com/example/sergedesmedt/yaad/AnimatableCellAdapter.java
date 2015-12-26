package com.example.sergedesmedt.yaad;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SergeDesmedt on 24/12/2015.
 */
public class AnimatableCellAdapter extends ArrayAdapter<CheeseViewModel> {

    HashMap<CheeseViewModel, Integer> mIdMap = new HashMap<CheeseViewModel, Integer>();
    View.OnTouchListener mTouchListener;

    public AnimatableCellAdapter(Context context, int textViewResourceId, List<CheeseViewModel> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        CheeseViewModel item = getItem(position);
        return item.id;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        return view;
    }
}