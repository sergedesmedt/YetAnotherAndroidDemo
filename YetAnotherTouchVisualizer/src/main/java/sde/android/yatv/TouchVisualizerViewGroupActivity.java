package sde.android.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class TouchVisualizerViewGroupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vw = new TouchVisualizerViewGroupView(this);

        setContentView(vw);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.graphic_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuItem = item.getItemId();
        switch (menuItem) {
            case R.id.mnu_single_graphic_config:
                Intent myIntent = new Intent(TouchVisualizerViewGroupActivity.this, TouchVisualizerViewGroupConfigActivity.class);

                Bundle b = new Bundle();
                b.putBoolean(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT, vw.getInterceptTouchEvent());
                b.putFloat(TouchVisualizerViewGroupConfigActivity.START_RETURN_TRUE_TIMEOUT, vw.getStartReturnTrueTimeOut());
                b.putFloat(TouchVisualizerViewGroupConfigActivity.STOP_CHILD1_CAPTURE_TIMEOUT, vw.getStopChild1CaptureTimeOut());

                myIntent.putExtras(b);

                startActivityForResult(myIntent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        Bundle config = intent.getExtras();

        vw.setInterceptTouchEvent(config.getBoolean(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT));
        vw.setStartReturnTrueTimeOut(config.getFloat(TouchVisualizerViewGroupConfigActivity.START_RETURN_TRUE_TIMEOUT));
        vw.setStopChild1CaptureTimeOut(config.getFloat(TouchVisualizerViewGroupConfigActivity.STOP_CHILD1_CAPTURE_TIMEOUT));

    }

    TouchVisualizerViewGroupView vw;
}
