package blockingdead2.pkg0;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class  Player extends GameObject
{
    private Handler handler;
    private String name;
    Game game;
    private BufferedImage player_image;
    public Player (float x, float y, Handler handler, ID id, String name,Game game,SpriteSheet ss)
    {
        super(x, y, id,ss);
        this.handler = handler;
        this.name = name;
        this.game=game;

        player_image=ss.grabImage(1,1,32,48);
    }

    @Override
    public void tick(LinkedList<GameObject> object)
    {
        // Updating Coordinates
        x += velx;
        y += vely;

        if(HUD.health <= 2)
        {
            handler.removeObject(this);
            Game.endGame = true;
        }

        // KeyInput and Velocity
        //MOVEMENT
        if (handler.isUp()) vely = -7;
        else if (!handler.isDown()) vely = 0;

        if (handler.isDown()) vely = 7;
        else if (!handler.isUp()) vely = 0;

        if (handler.isRight()) velx = 7;
        else if (!handler.isLeft()) velx = 0;

        if (handler.isLeft()) velx = -7;
        else if (!handler.isRight()) velx = 0;

        // Check Collision
        collision(object);
    }



    private void collision(LinkedList<GameObject> object)
    {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // Colliding with Barrier or Block
            if (tempObject.getID() == ID.Block || tempObject.getID() == ID.Barrier)
            {
                if (getBoundsTop().intersects(tempObject.getBoundsBottom()))
                {
                    y = tempObject.getY() + 32;
                    vely = 0;
                }

                if (getBoundsBottom().intersects(tempObject.getBoundsTop()))
                {
                    y = tempObject.getY() - 32;
                    vely = 0;
                }

                if (getBoundsRight().intersects(tempObject.getBoundsLeft()))
                {
                    x = tempObject.getX() - 32;
                    velx = 0;
                }

                if (getBoundsLeft().intersects(tempObject.getBoundsRight()))
                {
                    x = tempObject.getX() + 32;
                    velx = 0;
                }
            }

            // Colliding with Zombie
            if (tempObject.getID() == ID.Zombie) // Colliding with Block
            {
                if (getBoundsTop().intersects(tempObject.getBoundsBottom()))
                {
                    HUD.health--;
//                    handler.removeObject(this);
                }

                else if (getBoundsBottom().intersects(tempObject.getBoundsTop()))
                {
                    HUD.health--;
//                    handler.removeObject(this);
                }

                else if (getBoundsRight().intersects(tempObject.getBoundsLeft()))
                {
                    HUD.health--;
//                    handler.removeObject(this);
                }

                else if (getBoundsLeft().intersects(tempObject.getBoundsRight()))
                {
                    HUD.health--;
//                    handler.removeObject(this);

                }
            }

            // Colliding with Crate
            if (tempObject.getID() == ID.Crate)
            {
                if (getBoundsTop().intersects(tempObject.getBoundsBottom()))
                {
                    game.ammo += Game.ammoRefill;
                    handler.removeObject(tempObject);
                }

                else if (getBoundsBottom().intersects(tempObject.getBoundsTop()))
                {
                    game.ammo += Game.ammoRefill;
                    handler.removeObject(tempObject);
                }

                else if (getBoundsRight().intersects(tempObject.getBoundsLeft()))
                {
                    game.ammo += Game.ammoRefill;
                    handler.removeObject(tempObject);
                }

                else if (getBoundsLeft().intersects(tempObject.getBoundsRight()))
                {
                    game.ammo += Game.ammoRefill;
                    handler.removeObject(tempObject);
                }
            }
        }
    }
    @Override
    public void render(Graphics g)
    {
        // Creates Player Square
        g.drawImage(player_image,(int)x,(int)y,null);
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
