package ch.dissem.apps.down;

import android.app.Activity;
import android.os.Bundle;
import rajawali.RajawaliSupportFragment;

public class Fragment3D extends RajawaliSupportFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DownRenderer renderer = new DownRenderer(getActivity());
        renderer.setSurfaceView(mSurfaceView);
        setRenderer(renderer);
    }
}
