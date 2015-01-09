package ch.dissem.apps.down;

import android.opengl.GLES20;

/**
 * Created by chris on 02.01.15.
 */
public class Arrow {
    static final float[] arrowCoords = {
            -0.1f, 0.1f, 0.0f,   // top left
            -0.1f, -0.1f, 0.0f,  // bottom left
            0.1f, -0.1f, 0.0f,   // bottom right
            0.1f, 0.1f, 0.0f,    // top right
            0.0f, 0.0f, 0.6f};  // pointy end

    private final short[] drawOrder = {
            0, 1, 2,
            0, 2, 3,
            4, 1, 0,
            4, 2, 1,
            4, 3, 2,
            4, 0, 3}; // order to draw vertices

    private final Polygon top;
    private final Polygon front;
    private final Polygon back;
    private final Polygon left;
    private final Polygon right;

    float color[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};
    float lighterColor[] = {0.333333333f, 0.819607843f, 1.0f, 1.0f};
    float lightestColor[] = {0.666666666f, 0.909803921f, 1.0f, 1.0f};

    /**
     * Sets up the drawing object data for use in an OpenGL ES context.
     */
    public Arrow() {
        top = new Polygon(arrowCoords, new short[]{2, 1, 0, 0, 2, 3}, color);
        front = new Polygon(arrowCoords, new short[]{4, 2, 1}, lightestColor);
        back = new Polygon(arrowCoords, new short[]{4, 0, 3}, lightestColor);
        left = new Polygon(arrowCoords, new short[]{4, 1, 0}, lighterColor);
        right = new Polygon(arrowCoords, new short[]{4, 3, 2}, lighterColor);
    }

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * @param mvpMatrix - The Model View Project matrix in which to draw
     *                  this shape.
     */
    public void draw(float[] mvpMatrix) {
        top.draw(mvpMatrix);
        front.draw(mvpMatrix);
        back.draw(mvpMatrix);
        left.draw(mvpMatrix);
        right.draw(mvpMatrix);
    }
}
