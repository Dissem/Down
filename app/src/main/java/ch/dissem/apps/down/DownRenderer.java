package ch.dissem.apps.down;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ch.dissem.libraries.math.Quaternion;
import rajawali.BaseObject3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.DiffuseMaterial;
import rajawali.primitives.Sphere;
import rajawali.renderer.RajawaliRenderer;

import javax.microedition.khronos.opengles.GL10;

import static ch.dissem.libraries.math.Quaternion.H;
import static java.lang.Math.PI;

/**
 * Created by chris on 02.01.15.
 */
public class DownRenderer extends RajawaliRenderer {
    private BaseObject3D sphere;
    private SensorService sensorService;

    public DownRenderer(Context context) {
        super(context);
        setFrameRate(60);
        sensorService = new SensorService(context);
    }

    protected void initScene() {
        setBackgroundColor(1, 1, 1, 1);
        DirectionalLight light = new DirectionalLight(1f, 0.2f, -1.0f); // set the direction
        light.setColor(1.0f, 1.0f, 1.0f);
        light.setPower(2);

        Bitmap bg = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.earth);
        DiffuseMaterial material = new DiffuseMaterial();
        sphere = new Sphere(1, 18, 18);
        sphere.setMaterial(material);
        sphere.addLight(light);
        sphere.addTexture(mTextureManager.addTexture(bg));
        addChild(sphere);

        mCamera.setZ(4.2f);
    }

    public void onDrawFrame(GL10 glUnused) {
        super.onDrawFrame(glUnused);

        Quaternion orientation = sensorService.getOrientation();
        if (orientation != null) {
            Quaternion rotation = Quaternion.getRotation(H(0, 1, 0), orientation);

            double[] rot = rotation.getEulerAngles();
            sphere.setRotation((float) (-rot[0] * 180 / PI), 0, (float) (-rot[1] * 180 / PI));
        }
    }
}
