package blockingdead2.pkg0;
import java.awt.Graphics;
import java.awt.Color;
public class HUD {
    public static int health = 100;

    private int healthColorValue = 255;

    public void tick()
    {
        // Update healthColorValue
        if(healthColorValue > 2)
            healthColorValue = health * 2;

        // Clamp Health Bar
        if (health <= 2)
            health = 2;
        else if(health >= 100)
            health = 100;
    }

    public void render(Graphics g)
    {
        // Health Bar
        g.setColor(Color.GRAY); // Inside
        g.fillRect(15,15, 200,32);
        g.setColor(new Color(75, healthColorValue, 0)); // Health
        g.fillRect(15,15, health * 2,32);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawRect(15,15, 200,32);

        // Wave
        g.drawString("Wave: " + Game.c, 15, 84);
        // Score
        g.drawString("Score: " + Game.score, 15, 64);
        // Bullet Count
        g.drawString("Bullets: " + Game.ammo, 15, 104);
    }
}

