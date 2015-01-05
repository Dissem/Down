package ch.dissem.apps.down;

import ch.dissem.libraries.math.Quaternion;

import static ch.dissem.libraries.math.Quaternion.H;

/**
 * Low-pass filter: every time a new value is available,
 * it is weighted with a factor and added to the absolute
 * value.
 */
public class LowPassFilter {
    private Quaternion factor;
    private Quaternion coFactor;

    private Quaternion value;

    public LowPassFilter(double factor) {
        this.factor = H(factor);
        this.coFactor = H(1 - factor);
    }

    public void update(Quaternion newValue) {
        value = coFactor.multiply(value).add(factor.multiply(newValue));
    }
}
