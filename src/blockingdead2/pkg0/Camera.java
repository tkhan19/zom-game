package blockingdead2.pkg0;

public class Camera {
    private float x, y;

    public Camera(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object)
    {
        x += ((object.getX() - x) - Game.Width / 2) * 0.1f;
        y += ((object.getY() - y) - Game.Height / 2) * 0.1f;

        //Clamping so you don't see outside of the box you made

        if(x<=0)x=0;
        if(x>=1032)x=1065;
        if(y<=0)y=0;
        if(y>=400)y=400;
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }
}