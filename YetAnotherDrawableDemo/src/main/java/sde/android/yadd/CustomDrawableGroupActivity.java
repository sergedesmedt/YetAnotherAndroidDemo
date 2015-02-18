package sde.android.yadd;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by sergedesmedt on 18/02/15.
 */
public class CustomDrawableGroupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vw = new CustomDrawableGroupView(this);

        setContentView(vw);
    }

    private CustomDrawableGroupView vw;
}
