package blockingdead2.pkg0;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player)
            {
                if (key == KeyEvent.VK_UP||key == KeyEvent.VK_W) handler.setUp(true);
                if (key == KeyEvent.VK_DOWN||key == KeyEvent.VK_S) handler.setDown(true);
                if (key == KeyEvent.VK_LEFT||key == KeyEvent.VK_A) handler.setLeft(true);
                if (key == KeyEvent.VK_RIGHT||key == KeyEvent.VK_D) handler.setRight(true);

            }
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player)
            {
                if (key == KeyEvent.VK_UP||key == KeyEvent.VK_W) handler.setUp(false);
                if (key == KeyEvent.VK_DOWN||key == KeyEvent.VK_S) handler.setDown(false);
                if (key == KeyEvent.VK_LEFT||key == KeyEvent.VK_A) handler.setLeft(false);
                if (key == KeyEvent.VK_RIGHT||key == KeyEvent.VK_D) handler.setRight(false);
            }

        }
    }
}
