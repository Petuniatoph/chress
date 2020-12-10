package com.chress;

import com.chress.enums.Flag;

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
    private final LinkedList<Piece> player1;
    private final LinkedList<Piece> player2;
    private Piece selectedPiece = null;
    private Color currentPlayer;

    public PieceHandler()
    {
        this.currentPlayer = Color.WHITE;
        player1 = new LinkedList<>();
        player2 = new LinkedList<>();

        // WHITE - DON'T CHANGE KING
        player1.add(new King(Color.WHITE, 4,7,this));
        for(int i = 0; i < 8; i++)
        {
            player1.add(new Pawn(Color.WHITE, i, 6, this));
        }
        player1.add(new Rook(Color.WHITE, 0,7,this));
        player1.add(new Knight(Color.WHITE, 1,7,this));
        player1.add(new Bishop(Color.WHITE, 2,7,this));
        player1.add(new Queen(Color.WHITE, 3,7,this));
        player1.add(new Bishop(Color.WHITE, 5,7,this));
        player1.add(new Knight(Color.WHITE, 6,7,this));
        player1.add(new Rook(Color.WHITE, 7,7,this));

        // BLACK - DON'T CHANGE KING
        player2.add(new King(Color.BLACK, 4,0,this));
        for(int i = 0; i < 8; i++)
        {
            player2.add(new Pawn(Color.BLACK, i, 1, this));
        }
        player2.add(new Rook(Color.BLACK, 0,0,this));
        player2.add(new Knight(Color.BLACK, 1,0,this));
        player2.add(new Bishop(Color.BLACK, 2,0,this));
        player2.add(new Queen(Color.BLACK, 3,0,this));
        player2.add(new Bishop(Color.BLACK, 5,0,this));
        player2.add(new Knight(Color.BLACK, 6,0,this));
        player2.add(new Rook(Color.BLACK, 7,0,this));



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
            player2.get(i).render(g);
        }
        if(selectedPiece != null)
        {
            g.setColor(Color.RED);
            g.drawRect(75*selectedPiece.getX(),75*selectedPiece.getY(), 75, 75);
        }
    }

    public synchronized void movePiece(int x, int y)
    {
        Piece tempPiece = selectPiece(x, y);
        if(selectedPiece != null)
        {
            if(tempPiece == null)
            {
                if(selectedPiece.movePiece(x,y) != Flag.ILLEGAL) return;
                selectedPiece = null;
                nextPlayer();
                return;
            }
            if(tempPiece.getColor() == currentPlayer)
            {
                selectedPiece = tempPiece;
                return;
            }
            if(selectedPiece.movePiece(x,y) != Flag.ILLEGAL) return;
            selectedPiece = null;
            nextPlayer();
            return;
        }
        if(tempPiece == null) return;
        if(tempPiece.getColor() == currentPlayer)
        {
            selectedPiece = tempPiece;
        }
    }

    public synchronized void nextPlayer()
    {
        if(currentPlayer == Color.WHITE)
        {
            currentPlayer = Color.BLACK;
            return;
        }
        currentPlayer = Color.WHITE;
    }


    public void tick()
    {

    }

}
