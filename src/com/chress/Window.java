package com.chress;

/**
 * - com.chress.Window with JFrame for Chress
 *
 * @author Christoph Schobesberger
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Window extends Canvas
{
    JFrame jFrame;

    public Window(String title, int width, int height, Game game)
    {
        jFrame = new JFrame(title);
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setMinimumSize(new Dimension(width, height));
        jFrame.setMaximumSize(new Dimension(width, height));
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.add(game);
        jFrame.pack();
        jFrame.setVisible(true);
    }


}
