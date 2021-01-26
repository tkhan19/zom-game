package blockingdead2.pkg0;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Game extends Canvas implements Runnable
{

    public static int Width = 1000, Height = 800;
    public String title = "Blocking Dead";
    private Window window;

    public static boolean endGame = false;
    private Thread thread;
    private boolean isRunning = false; //determines if game is running

    //Instances
    public Handler handler;
    private Camera camera;
    private SpriteSheet ss;
    private HUD hud;

    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;
    private BufferedImage menu = null;


    //PlayerName
   // private static String playerName;
    public static Integer score = 0;
    public static Integer counter=0;
    public static Integer c = 1;
    private Integer countLimit=3;

    //Ammo
    public static int ammo = 0;
    public static  int ammoRefill =1;

    //The Main Constructor
    public Game()
    {
        window = new Window(Width, Height, title, this);
        init();
        start();
    }

    private void init()
    {
    handler = new Handler();
    camera = new Camera(0, 0);
    hud = new HUD();

    this.addKeyListener(new KeyInput(handler));


    BufferedImageLoader loader = new BufferedImageLoader();

    level = loader.loadImage("res/Player_Level.png");
    sprite_sheet = loader.loadImage("res/sprite_sheet.png");
   // menu = loader.loadImage("res/GameMenuPic.png");

    ss = new SpriteSheet(sprite_sheet);


    floor = ss.grabImage(4, 2, 32, 32);

    this.addMouseListener(new MouseInput(handler, camera, this, ss));
    loadLevel(level);


}

    private void loadMenu(BufferedImage menu)
    {
    }


    //Create methods for starting/stopping the game
    private synchronized void start()
    {
        if (isRunning) return;        //safety precaution

        thread = new Thread(this);
        thread.start();
        isRunning = true;    //thread wil call the run method

    }

    private synchronized void stop()
    {
        if (!isRunning) return;
        checkHighscore();
        try
        {
            thread.join();


        }
        catch (InterruptedException ex)
        {

        }
        isRunning = false;
    }

    //add game-loop
    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;

        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        //add while loop
        while (isRunning)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1)
            {
                tick();  //updating method
                delta--;


            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }
    //avoid a while loop due to insufficient regulation

    private void tick()
    {
        //will update the game
        //Camera will follow the player
        for (int i = 0; i < handler.object.size(); i++)
        {
            if (handler.object.get(i).getID() == ID.Player)
            {
                camera.tick(handler.object.get(i));
            }
        }

        if(counter==countLimit)
        {
            c++;
            spawnZombies(level);
            counter=0;
            countLimit+=3;
            HUD.health = 100;
        }

        if(endGame)
            stop();

        handler.tick();
        hud.tick();

    }

    private void render() {
        //will render the game
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        //Need this in order for the camera to work, you are converting graphics g to 2D graphics
        Graphics g = bs.getDrawGraphics();
        Graphics gt = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;


        g2d.translate(-camera.getX(), -camera.getY());

        for (int xx = 0; xx < 30 * 72; xx += 32)
        {
            for (int yy = 0; yy < 30 * 72; yy += 32)
            {
                g.drawImage(floor, xx, yy, null);
            }
        }

        //Included rendering information
        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());
        hud.render(g);

        bs.show();
        g.dispose();
    }
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int blue = (pixel) & 0xff;
                int green = (pixel >> 8) & 0xff;
                if (red==230&&blue==0)
                    handler.addObject(new Barrier(xx * 32, yy * 32, ID.Barrier, ss));
                if (red == 255 && blue == 0 && green == 0)
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));

//                if (red == 255 && blue == 0 && green == 255)
//                    handler.addObject(new Barrier(xx * 32, yy * 32, ID.Barrier, ss));

                if (red == 0 && blue == 255 && green == 0)
                    handler.addObject(new Player(xx, yy, handler, ID.Player, "h", this, ss));

                if (green==255&&blue==0&&red==0)
                    handler.addObject(new Zombie(xx * 32, yy * 32, handler, ID.Zombie, ss));

                if (green == 255 && blue == 255 && red == 0)
                    handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));
            }
        }
    }
    private void spawnZombies(BufferedImage image){
        int w=image.getWidth();
        int h=image.getHeight();

        for(int xx=0;xx<w;xx++)
        {
            for(int yy=0;yy<h;yy++)
            {
                int pixel=image.getRGB(xx,yy);
                int blue= (pixel)&0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel>>8)&0xff;

                for(int i = 0; i < c; i++)
                {
                    if (green==255&&blue==0&&red==0)
                    {
                        handler.addObject(new Zombie(xx*32,yy*32,handler,ID.Zombie,ss));
                    }
                    if (green==255&&blue==255)
                    {
                        handler.removeObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));
                    }
                    if (green==255&&blue==255)
                    {
                        handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));
                    }
                }
            }
        }
        ammoRefill++;
    }

    private void checkHighscore()
    {
        // Getting Highscore
        Scanner scanner = null;

        try
        {
            scanner = new Scanner(new File("HighScore.txt"));
        }
        catch (FileNotFoundException ex)
        {
            ex.getMessage();
        }
}


    /**
     * @param args
     */

    public static void main(String args[])
    {
        new Game();


    }


}
