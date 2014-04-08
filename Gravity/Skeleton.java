package Gravity;

import javax.swing.JFrame;

public class Skeleton extends JFrame 
{
    public Skeleton(GameEngine engine, int width, int height) 
    {
        add(engine);
        setTitle("Skeleton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}