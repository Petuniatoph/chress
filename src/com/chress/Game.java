package com.chress;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

/**
 * - Implements com.chress.Game Loop
 *
 * @author Christoph
 * @version 1.0
 */

public class Game extends Canvas implements Runnable
{
    private final int WIDTH = 750;
    private final int HEIGHT = 636;
    private final int BOARD = 75;
    private boolean running;
    private Thread thread;
    private final PieceHandler pieceHandler;

    Game()
    {
        this.pieceHandler = new PieceHandler();
        this.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int x = e.getX()/75;
                int y = e.getY()/75;
                pieceHandler.movePiece(x, y);
                System.out.println(x);
                System.out.println(y);
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
        new Window("Chress - Christoph's Chess", WIDTH, HEIGHT, this);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        long lastTIme = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTIme) / ns;
            lastTIme = now;
            while (delta >= 1)
            {
                tick();
                delta--;
            }
            if (running)
            {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, WIDTH, HEIGHT);
        drawBoard(g);

        pieceHandler.render(g);

        g.dispose();
        bs.show();

    }

    public void selectPiece(int x, int y)
    {

    }


    private void tick()
    {
        pieceHandler.tick();

    }

    private void drawBoard(Graphics g)
    {
        boolean color = true;
        for (int pos_y = 0; pos_y <= 7; pos_y++)
        {
            for (int pos_x = 0; pos_x <= 7; pos_x++)
            {
                if (color)
                {
                    g.setColor(Color.WHITE);
                    color = false;
                } else
                {
                    g.setColor(new Color(92,196,97));
                    color = true;
                }
                g.fillRect(pos_x * BOARD, pos_y * BOARD, BOARD, BOARD);
            }

            color = !color;
        }
    }


    //TODO place Main somewhere else
    public static void main(String[] args)
    {
        new Game();
    }


}


