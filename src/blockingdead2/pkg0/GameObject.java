
package blockingdead2.pkg0;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
    protected float x;
    protected float y;
    protected float velx;
    protected float vely;
    protected ID Id;
    protected SpriteSheet ss;

    public GameObject(float x, float y, ID id,SpriteSheet ss)
    {
        this.setX(x);
        this.setY(y);
        this.Id =id;
        this.ss=ss;
    }
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBoundsTop();
    public abstract Rectangle getBoundsBottom();
    public abstract Rectangle getBoundsRight();
    public abstract Rectangle getBoundsLeft();


    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getVelx()
    {
        return velx;
    }

    public void setVelx(float velx)
    {
        this.velx = velx;
    }

    public float getVely()
    {
        return vely;
    }

    public void setVely(float vely)
    {
        this.vely = vely;
    }

    public ID getID()
    {
        return Id;
    }

}