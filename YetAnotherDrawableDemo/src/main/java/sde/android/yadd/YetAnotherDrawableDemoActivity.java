package sde.android.yadd;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import com.hfk.yadd.R;

public class YetAnotherDrawableDemoActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
			myIntent = new Intent(YetAnotherDrawableDemoActivity.this, CustomDrawableFromXMLActivity.class);
		if(position == 1)
			myIntent = new Intent(YetAnotherDrawableDemoActivity.this, CustomDrawableActivity.class);
        if(position == 2)
            myIntent = new Intent(YetAnotherDrawableDemoActivity.this, CustomDrawableGroupActivity.class);
		if(position == 3)
			myIntent = new Intent(YetAnotherDrawableDemoActivity.this, CustomDrawableMatrixTransformationActivity.class);
		if(position == 4)
			myIntent = new Intent(YetAnotherDrawableDemoActivity.this, CustomDrawableCameraTransformationActivity.class);

		startActivity(myIntent);
	}
}