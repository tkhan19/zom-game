package blockingdead2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bullet extends GameObject {
    Handler handler;

    public Bullet(float x, float y, Handler handler, ID id, int mx, int my,SpriteSheet ss)
    {
        super(x, y, id,ss);
        this.handler = handler;

        calculateVelocity(x, y, mx, my);
    }

    private void calculateVelocity(float fromX, float fromY, int toX, int toY)
    {
        double distance = Math.sqrt(Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
        double speed = 10; //set the speed in [2,n)  n should be < 20 for normal speed
        //find Yr
        vely = (float) ((toY - fromY) * speed / distance);
        //find X
        velx = (float) ((toX - fromX) * speed / distance);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velx;
        y += vely;

        collision(object);
    }

    private void collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size()-1; i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Block) // Colliding with Block
            {
                if (getBoundsTop().intersects(tempObject.getBoundsTop())) {
                    handler.removeObject(this);
                }

                if (getBoundsTop().intersects(tempObject.getBoundsBottom())) {
                    handler.removeObject(this);
                }

                if (getBoundsTop().intersects(tempObject.getBoundsRight())) {
                    handler.removeObject(this);
                }

                if (getBoundsTop().intersects(tempObject.getBoundsLeft())) {
                    handler.removeObject(this);
                }
            }

            if (tempObject.getID() == ID.Zombie) // Colliding with Block
            {
                if (getBoundsTop().intersects(tempObject.getBoundsTop())) {
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                    Game.counter++;
                    Game.score += 5;
                    return;
                }

                else if (getBoundsTop().intersects(tempObject.getBoundsBottom())) {
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                    Game.counter++;
                    Game.score += 5;
                    return;
                }

                else if (getBoundsTop().intersects(tempObject.getBoundsRight())) {
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                    Game.counter++;
                    Game.score += 5;
                    return;
                }

                else if (getBoundsTop().intersects(tempObject.getBoundsLeft())) {
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                    Game.counter++;
                    Game.score += 5;
                    return;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        // Creates Bullet Square
        g.setColor(Color.white);
        g.fillOval((int) x, (int) y, 8, 8);
    }

    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle((int) x, (int) y, 8, 8);
    }

    @Override
    public Rectangle getBoundsBottom() {
        return null;
    }

    @Override
    public Rectangle getBoundsRight() {
        return null;
    }

    @Override
    public Rectangle getBoundsLeft() {
        return null;
    }
}