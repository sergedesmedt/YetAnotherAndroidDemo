package com.example.sergedesmedt.yaad;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class YetAnotherAnimationDemoActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        String[] names = res.getStringArray(R.array.views_available);
        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names));

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent myIntent = null;
        if(position == 0)
            myIntent = new Intent(YetAnotherAnimationDemoActivity.this, SimpleViewAnimationActivity.class);
        if(position == 1)
            myIntent = new Intent(YetAnotherAnimationDemoActivity.this, SimplePropertyAnimationActivity.class);
        if(position == 2)
            myIntent = new Intent(YetAnotherAnimationDemoActivity.this, SimpleLayoutAnimationActivity.class);
        if(position == 3)
            myIntent = new Intent(YetAnotherAnimationDemoActivity.this, ComplexAnimationSampleActivity.class);
        if(position == 4)
            myIntent = new Intent(YetAnotherAnimationDemoActivity.this, InsertingCellsActivity.class);
        if(position == 5)
            myIntent = new Intent(YetAnotherAnimationDemoActivity.this, ListViewRemovalAnimation.class);

        startActivity(myIntent);
    }
}
