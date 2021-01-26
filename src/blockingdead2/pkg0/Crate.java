package blockingdead2.pkg0;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Crate extends GameObject {

    private BufferedImage crate_image;
    public Crate(int x,int y , ID Id,SpriteSheet ss)
    {
        super(x,y,Id,ss);
        crate_image=ss.grabImage(6,2,32,32);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(crate_image,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBoundsTop()
    {
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBoundsBottom()
    {
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
