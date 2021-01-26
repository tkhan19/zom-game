package blockingdead2.pkg0;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter
{

    private Handler handler;
    private Camera camera;
    private Game game;
    private SpriteSheet ss;

    public MouseInput(Handler handler, Camera camera,Game game,SpriteSheet ss)
    {
        this.handler = handler;
        this.camera = camera;
        this.game=game;
        this.ss=ss;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());

        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player&&game.ammo>=1) // Colliding with Block& checking ammo
            {
                handler.addObject(new Bullet(tempObject.getX() + 12,
                        tempObject.getY() + 12, handler, ID.Bullet, mx, my,ss));
                game.ammo--;
            }
        }
    }
}