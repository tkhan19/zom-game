package blockingdead2.pkg0;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;



public class Window

{
    public Window(int width, int height, String title, Game game) {



        JFrame frame= new JFrame(title);
        JPanel menuScreen= new JPanel();
        JPanel gameScreen = new JPanel();


        JButton startButton= new JButton("Start");

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menuScreen.setVisible(false); // the screen with the start button
                gameScreen.setVisible(true); // actual game
            }
        });
        menuScreen.add(startButton);
        menuScreen.setVisible(true);
        menuScreen.repaint();
        frame.add(menuScreen);

        gameScreen.setVisible(false);
        frame.add(game);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));

        frame.setLayout(new CardLayout());

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


    }
}



