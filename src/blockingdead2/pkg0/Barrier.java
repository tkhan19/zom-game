package blockingdead2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Barrier extends GameObject
{
    private BufferedImage barrier_image;

    public Barrier(float x, float y, ID id,SpriteSheet ss)
    {
        super(x, y, id,ss);
        barrier_image=ss.grabImage(3,2,32,32);
    }

    @Override
    public void tick(LinkedList<GameObject> tempObject)
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(barrier_image,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBoundsTop()
    {
        return new Rectangle((int)x + (32/2)-((32/2)/2), (int)y, 32/2, 32/2);
    }

    @Override
    public Rectangle getBoundsBottom()
    {
        return new Rectangle((int)x + (32/2)-((32/2)/2), (int)y + 32/2, 32/2, 32/2);
    }

    @Override
    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)x + (32 - 5),(int)((int)y + 2.5), 5, 32-5);
    }

    @Override
    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int)x, (int)((int)y + 2.5), 5, 32-5);
    }
}