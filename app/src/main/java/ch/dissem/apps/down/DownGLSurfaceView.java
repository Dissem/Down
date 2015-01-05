package ch.dissem.apps.down;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chris on 02.01.15.
 */
public class DownGLSurfaceView extends GLSurfaceView {
    private final static float TOUCH_SCALE_FACTOR = 180.0f / 320;

    private DownRenderer renderer;

    public DownGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        final SensorService sensorService = new SensorService(context);
        renderer = new DownRenderer(sensorService);

        setRenderer(renderer);

        final Activity ctx = (Activity) context;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ctx.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (sensorService.getOrientation() != null) {
                            ctx.setTitle("" + sensorService.getOrientation().getVector());
                        }
                    }
                });
            }
        }, 1100, 1000);

        // Render the view only when there is a change in the drawing data
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
