package ch.dissem.apps.down;

import ch.dissem.libraries.math.Quaternion;

import java.util.TimerTask;

import static ch.dissem.libraries.math.Quaternion.H;

/**
 * Created by chris on 02.01.15.
 */
public class SensorFusionFilter extends TimerTask {
    private final Quaternion factor;
    private final Quaternion coFactor;

    private Quaternion fusedOrientation;

    private volatile Quaternion gyroOrientation;
    private volatile Quaternion acceleratorOrientation;

    public SensorFusionFilter(double factor) {
        this.factor = H(factor);
        this.coFactor = H(1 - factor);
    }

    public void updateGyro(Quaternion gyroValue) {
        if (gyroOrientation != null) {
            gyroValue = gyroValue.normalize();
            gyroOrientation = gyroValue.multiply(gyroOrientation).multiply(gyroValue.conjugate());
        }
    }

    public void updateAccelerator(Quaternion accValue) {
        if (acceleratorOrientation == null) {
            acceleratorOrientation = accValue;
        } else {
            acceleratorOrientation = factor.multiply(acceleratorOrientation).add(coFactor.multiply(accValue));
        }
    }

    public void update() {
        if (gyroOrientation == null)
            fusedOrientation = gyroOrientation = acceleratorOrientation;
        else {
            fusedOrientation = gyroOrientation = coFactor.multiply(gyroOrientation).add(factor.multiply(acceleratorOrientation));
        }
    }

    public Quaternion getOrientation() {
        return fusedOrientation;
    }

    @Override
    public void run() {
        update();
    }
}
