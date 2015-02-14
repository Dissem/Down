package ch.dissem.apps.down;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import rajawali.RajawaliFragment;
import rajawali.renderer.RajawaliRenderer;

public class Fragment3D extends RajawaliFragment {
    private RajawaliRenderer renderer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        renderer = new DownRenderer(getActivity());
        renderer.setSurfaceView(mSurfaceView);
        setRenderer(renderer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mSurfaceView;
    }

    @Override
    public void onDestroy() {
        try {
            super.onDestroy();
        } finally {
            renderer.onSurfaceDestroyed();
        }
    }
}
