package Gravity;

import javax.swing.JFrame;

public class Skeleton extends JFrame 
{
    public Skeleton(GameEngine engine) 
    {
        add(engine);
        setTitle("Skeleton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 280);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}