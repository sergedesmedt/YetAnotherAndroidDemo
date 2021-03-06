package sde.android.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by sergedesmedt on 28/01/15.
 */
public class TouchVisualizerViewGroupConfigActivity extends Activity {

    public static final String INTERCEPT_TOUCHEVENT = "INTERCEPT_TOUCHEVENT";
    public static final String START_RETURN_TRUE_TIMEOUT = "START_RETURN_TRUE_TIMEOUT";
    public static final String START_RETURN_FALSE_ONTOUCHEVENT_TIMEOUT = "START_RETURN_FALSE_ONTOUCHEVENT_TIMEOUT";
    public static final String STOP_CHILD1_CAPTURE_TIMEOUT = "STOP_CHILD1_CAPTURE_TIMEOUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewgroup_config_dialog);

        m_chkOnInterceptTouchEvent = (CheckBox)findViewById(R.id.checkBoxTrueOnIntercept);
        m_edtStartReturnTrueTimeOut = (EditText)findViewById(R.id.editStartReturnTrueTimeOut);
        m_edtStartReturnFalseInOnTouchEventTimeOut = (EditText)findViewById(R.id.editStartReturnFalseInOnTouchTimeOut);
        m_edtStopChild1CaptureTimeOut = (EditText)findViewById(R.id.editStopChild1CaptureTimeOut);

        Bundle data = getIntent().getExtras();
        if(data != null){
            if(data.containsKey(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT)) {
                m_chkOnInterceptTouchEvent.setChecked(data.getBoolean(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT));
            }
            if(data.containsKey(TouchVisualizerViewGroupConfigActivity.START_RETURN_TRUE_TIMEOUT)) {
                m_edtStartReturnTrueTimeOut.setText(Float.toString(data.getFloat(TouchVisualizerViewGroupConfigActivity.START_RETURN_TRUE_TIMEOUT)));
            }
            if(data.containsKey(TouchVisualizerViewGroupConfigActivity.START_RETURN_FALSE_ONTOUCHEVENT_TIMEOUT)) {
                m_edtStartReturnFalseInOnTouchEventTimeOut.setText(Float.toString(data.getFloat(TouchVisualizerViewGroupConfigActivity.START_RETURN_FALSE_ONTOUCHEVENT_TIMEOUT)));
            }
            if(data.containsKey(TouchVisualizerViewGroupConfigActivity.STOP_CHILD1_CAPTURE_TIMEOUT)) {
                m_edtStopChild1CaptureTimeOut.setText(Float.toString(data.getFloat(TouchVisualizerViewGroupConfigActivity.STOP_CHILD1_CAPTURE_TIMEOUT)));
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();

        Bundle b = new Bundle();
        b.putBoolean(TouchVisualizerViewGroupConfigActivity.INTERCEPT_TOUCHEVENT, m_chkOnInterceptTouchEvent.isChecked());
        b.putFloat(TouchVisualizerViewGroupConfigActivity.START_RETURN_TRUE_TIMEOUT, Float.parseFloat(m_edtStartReturnTrueTimeOut.getText().toString()));
        b.putFloat(TouchVisualizerViewGroupConfigActivity.START_RETURN_FALSE_ONTOUCHEVENT_TIMEOUT, Float.parseFloat(m_edtStartReturnFalseInOnTouchEventTimeOut.getText().toString()));
        b.putFloat(TouchVisualizerViewGroupConfigActivity.STOP_CHILD1_CAPTURE_TIMEOUT, Float.parseFloat(m_edtStopChild1CaptureTimeOut.getText().toString()));

        result.putExtras(b);

        setResult(Activity.RESULT_OK, result);

        super.onBackPressed();
    }

    private CheckBox m_chkOnInterceptTouchEvent;
    private EditText m_edtStartReturnTrueTimeOut;
    private EditText m_edtStartReturnFalseInOnTouchEventTimeOut;
    private EditText m_edtStopChild1CaptureTimeOut;
}
