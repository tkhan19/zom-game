package blockingdead2.pkg0;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Block extends GameObject
{ private BufferedImage block_image;

    public Block(float x, float y, ID id,SpriteSheet ss)
    {
        super(x, y, id,ss);
        block_image=ss.grabImage(5,2,32,32);
    }

    @Override
    public void tick(LinkedList<GameObject> tempObject)
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(block_image,(int)x,(int)y,null);
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
