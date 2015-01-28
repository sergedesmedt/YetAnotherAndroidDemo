package sde.android.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

/**
 * Created by sergedesmedt on 28/01/15.
 */
public class TouchVisualizerViewGroupConfigActivity extends Activity {

    public static final String INTERCEPT_TOUCHEVENT = "INTERCEPT_TOUCHEVENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewgroup_config_dialog);

        m_chkOnInterceptTouchEvent = (CheckBox)findViewById(R.id.checkBoxTrueOnIntercept);

        Bundle data = getIntent().getExtras();
        if(data != null){
            if(data.containsKey(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT)) {
                m_chkOnInterceptTouchEvent.setChecked(data.getBoolean(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT));
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();

        Bundle b = new Bundle();
        b.putBoolean(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT, m_chkOnInterceptTouchEvent.isChecked());

        result.putExtras(b);

        setResult(Activity.RESULT_OK, result);

        super.onBackPressed();
    }

    private CheckBox m_chkOnInterceptTouchEvent;
}
