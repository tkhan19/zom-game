package blockingdead2.pkg0;


import java.awt.Graphics;
import java.util.LinkedList;

//This class is just in charge of updating and rendering every single thing in out game
public class Handler
{
    public LinkedList<GameObject> object = new LinkedList<>();
    private LinkedList<GameObject> tObject;

    private boolean up = false, down = false, right = false, left = false;

    public void tick()
    {
        for (int i = 0; i < this.object.size(); i++)
        {
            GameObject tempObject = this.object.get(i);
            tempObject.tick(tObject);
        }
    }

    public void render(Graphics g)
    {

        for (int i = 0; i < this.object.size(); i++)
        {
            GameObject tempObject = this.object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject( GameObject tempObject)
    {
        object.add(tempObject);
    }

    public void removeObject( GameObject tempObject)
    {
        object.remove(tempObject);
    }

    /**
     * @return the up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * @return the down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * @return the right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * @return the left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(boolean left) {
        this.left = left;
    }
}
