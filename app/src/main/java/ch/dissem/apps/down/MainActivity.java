package ch.dissem.apps.down;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends ActionBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        if (savedInstanceState == null) {
            Fragment3D testFragment = new Fragment3D();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, testFragment, "testFragment")
                    .commit();
        }
    }
}
