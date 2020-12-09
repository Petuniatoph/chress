package com.chress;

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
    Piece selectedPiece;
    private final Color currentPlayer;

    public PieceHandler()
    {
        this.currentPlayer = Color.WHITE;
        player1 = new LinkedList<>();
        player2 = new LinkedList<>();

        //TODO add Pieces
        player1.add(new King(Color.BLACK, 4,7,this));
        player1.add(new King(Color.WHITE, 4,0,this));

    }

    public Color getCurrentPlayer()
    {
        return currentPlayer;
    }

    public LinkedList<Piece> getPlayer1()
    {
        return player1;
    }

    public LinkedList<Piece> getPlayer2()
    {
        return player2;
    }

    /**
     * Selects a piece by coordinate
     * @param x
     * @param y
     * @return
     */

    public Piece selectPiece(int x, int y)
    {
        Piece piece;
        for(int i = 0; i < player1.size(); i++)
        {
            piece = player1.get(i);
            if(piece.getX() == x && piece.getY() == y)
            {
                return piece;
            }
        }
        for(int i = 0; i < player2.size(); i++)
        {
            piece = player2.get(i);
            if(piece.getX() == x && piece.getY() == y)
            {
                return piece;
            }
        }
        return null;
    }

    /**
     * Get Piece By Index
     * @param color
     * @param index
     * @return
     */

    public Piece getPieceByIndex(Color color, int index)
    {
        if(color == Color.WHITE)
        {
            return player1.get(index);
        }
        if(color == Color.BLACK)
        {
            return player2.get(index);
        }
        return null;
    }


    /**
     * - renders all objects
     * @param g
     */

    public void render(Graphics g)
    {
        for(int i = 0; i < player1.size(); i++)
        {
            player1.get(i).render(g);
            //player2.get(i).render(g);

        }
    }

    public void tick()
    {

    }

}
