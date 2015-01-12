package ch.dissem.apps.down;

import android.os.Bundle;
import rajawali.RajawaliActivity;


public class MainActivity extends RajawaliActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
//
//        Fragment3D content = new Fragment3D();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.container, content, "rajawali")
//                .commit();

        DownRenderer renderer = new DownRenderer(this);
        renderer.setSurfaceView(mSurfaceView);
        setRenderer(renderer);
    }
}
