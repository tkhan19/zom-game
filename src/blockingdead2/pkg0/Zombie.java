package blockingdead2.pkg0;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Zombie extends GameObject
{
    private Handler handler;
    private int health = 3;

    private BufferedImage zombie_image;

    public Zombie(float x, float y, Handler handler, ID Id,SpriteSheet ss)
    {
        super(x, y, Id,ss);
        this.handler = handler;

        zombie_image=ss.grabImage(4,1,32,32);
    }

    @Override
    public void tick(LinkedList<GameObject> object)
    {
        // Updating Coordinates
        x += velx;
        y += vely;


        calculateVelocity(x, y);
        collision(object);
    }

    private void calculateVelocity(float fromX, float fromY)
    {
        float toX = 0, toY = 0;

        for (int i = 0; i < handler.object.size(); i++) // Gets Player Coord
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player)
            {
                toX = tempObject.x;
                toY = tempObject.y;
            }
        }

        double distance = Math.sqrt(Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
        double speed = 4; //set the speed in [2,n)  n should be < 20 for normal speed
        //find Y
        vely = (float)((toY - fromY) * speed / distance);
        //find X
        velx = (float)((toX - fromX) * speed / distance);
    }

    private void collision(LinkedList<GameObject> object)
    {
        for (int i = 0; i < handler.object.size()-1; i++)
        {
            GameObject tempObject = handler.object.get(i);

            // Colliding with Block or Zombie
            if(tempObject.getID() == ID.Block || tempObject.getID() == ID.Zombie)
            {
                if(getBoundsTop().intersects(tempObject.getBoundsBottom()))
                {
                    y = tempObject.getY() + 32;
                    vely = 0;
                }

                if(getBoundsBottom().intersects(tempObject.getBoundsTop()))
                {
                    y = tempObject.getY() - 32;
                    vely = 0;
                }

                if(getBoundsRight().intersects(tempObject.getBoundsLeft()))
                {
                    x = tempObject.getX() - 32;
                    velx = 0;
                }

                if(getBoundsLeft().intersects(tempObject.getBoundsRight()))
                {
                    x = tempObject.getX() + 32;
                    velx = 0;
                }
            }

            // Colliding with Player
            if(tempObject.getID() == ID.Player)
            {
                if(getBoundsTop().intersects(tempObject.getBoundsBottom()))
                {
                    y = tempObject.getY() + 31;
                    vely = 0;
                }

                if(getBoundsBottom().intersects(tempObject.getBoundsTop()))
                {
                    y = tempObject.getY() - 31;
                    vely = 0;
                }

                if(getBoundsRight().intersects(tempObject.getBoundsLeft()))
                {
                    x = tempObject.getX() - 31;
                    velx = 0;
                }

                if(getBoundsLeft().intersects(tempObject.getBoundsRight()))
                {
                    x = tempObject.getX() + 31;
                    velx = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(zombie_image,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBoundsTop()
    {
        return new Rectangle((int)x, (int)y, 32, 32/2);
    }

    @Override
    public Rectangle getBoundsBottom()
    {
        return new Rectangle((int)x, (int)y + 32/2, 32, 32/2);
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