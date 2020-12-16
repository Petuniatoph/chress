package com.chress;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * - Implements com.chress.Game Loop
 *
 * @author Christoph
 * @version 1.0
 */

public class Game extends Canvas
{
    private static final int WIDTH = 616;
    private static final int HEIGHT = 636;
    private final int BOARD = 75;
    private final PieceHandler pieceHandler;

    Game()
    {
        this.pieceHandler = new PieceHandler();
        this.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX()/75;
                int y = e.getY()/75;
                pieceHandler.movePiece(x, y);
                repaint();
                System.out.println(x);
                System.out.println(y);
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

    }

    @Override
    public void paint(Graphics g)
    {
        render(g);
    }

    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, WIDTH, HEIGHT);
        drawBoard(g);
        pieceHandler.renderPossibleMoves(g);
        pieceHandler.render(g);

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
        Game game = new Game();
        new Window("Chress - Christoph's Chess", WIDTH, HEIGHT, game);
    }


}


