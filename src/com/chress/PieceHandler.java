package com.chress;

import com.chress.enums.ColorBW;

import java.awt.*;
import java.util.LinkedList;

/**
 * Handler for all Pieces in the Game
 *
 * @author Christoph Schobesberger
 * @version 1.0
 */

public class PieceHandler
{
    //TODO make list final
    private LinkedList<Piece> player1;
    private LinkedList<Piece> player2;
    private final ColorBW currentPlayer;

    public PieceHandler()
    {
        this.currentPlayer = ColorBW.WHITE;
        player1 = new LinkedList<>();
        player2 = new LinkedList<>();

        //TODO add Pieces
        player1.add(new King(ColorBW.WHITE, 0,0,this));

    }

    public void render(Graphics g)
    {
        for(int i = 0; i < player1.size(); i++)
        {
            /* TODO render all Objects
            player1.get(i).render(g);
            player2.get(i).render(g);

             */
        }
    }

    public void tick()
    {

    }

}
