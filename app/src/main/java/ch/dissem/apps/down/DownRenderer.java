package ch.dissem.apps.down;

import android.content.Context;
import ch.dissem.libraries.math.Quaternion;
import rajawali.BaseObject3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.DiffuseMaterial;
import rajawali.parser.AParser;
import rajawali.parser.ObjParser;
import rajawali.renderer.RajawaliRenderer;

import javax.microedition.khronos.opengles.GL10;

import static ch.dissem.libraries.math.Quaternion.H;
import static java.lang.Math.PI;

/**
 * Created by chris on 02.01.15.
 */
public class DownRenderer extends RajawaliRenderer {
    private BaseObject3D arrow;
    private SensorService sensorService;

    public DownRenderer(Context context) {
        super(context);
        setFrameRate(60);
        sensorService = new SensorService(context);
    }

    protected void initScene() {
        setBackgroundColor(1, 1, 1, 1);
        DirectionalLight light1 = new DirectionalLight(1f, 0.2f, -1.0f); // set the direction
        light1.setColor(1.0f, 1.0f, 1.0f);
        light1.setPower(1);

        DirectionalLight light2 = new DirectionalLight(-1f, -0.2f, 1.0f); // set the direction
        light2.setColor(1.0f, 1.0f, 1.0f);
        light2.setPower(0.1f);

        ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.arrow_obj);
        try {
            objParser.parse();
        } catch (AParser.ParsingException e) {
            throw new RuntimeException(e);
        }
        arrow = objParser.getParsedObject();
        DiffuseMaterial material = new DiffuseMaterial();
        material.setAmbientColor(getContext().getResources().getColor(R.color.primary_dark));
        material.setUseColor(true);
        arrow.setMaterial(material);
        arrow.addLight(light1);
        arrow.setScale(0.1f);
        arrow.setColor(getContext().getResources().getColor(R.color.primary));
        addChild(arrow);

        mCamera.setZ(4.2f);
    }

    public void onDrawFrame(GL10 glUnused) {
        super.onDrawFrame(glUnused);

        Quaternion orientation = sensorService.getOrientation();
        if (orientation != null) {
            Quaternion rotation = Quaternion.getRotation(H(0, 0, 1), orientation);

            double[] rot = rotation.getEulerAngles();
            arrow.setRotation((float) (90 - rot[0] * 180 / PI), (float) (-rot[1] * 180 / PI), (float) (-rot[1] * 180 / PI));
        }
    }
}
