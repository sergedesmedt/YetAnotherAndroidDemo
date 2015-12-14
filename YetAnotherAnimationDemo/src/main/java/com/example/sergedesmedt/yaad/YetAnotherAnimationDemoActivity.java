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

        startActivity(myIntent);
    }
}
