package com.chress;

import com.chress.enums.Flag;
import com.chress.enums.Type;

import java.awt.*;

public class Pawn extends Piece
{

    Pawn(Color color, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.BISHOP, color, x, y, pieceHandler, "pawn");
    }

    @Override
    protected Flag validateTarget(int x, int y)
    {
        if(pieceHandler.selectPiece(x,y) == null)
        {
            if(Math.abs(this.x - x) != 0)
            {
                return Flag.ILLEGAL;
            }
            if(color == Color.WHITE)
            {
                if(this.y - y == 1) return Flag.LEGAL;
                if(this.y == 6 && this.y - y == 2) return Flag.LEGAL;
            }
            if(color == Color.BLACK)
            {
                if(y - this.y == 1) return Flag.LEGAL;
                if(this.y == 1 && y - this.y == 2) return Flag.LEGAL;
            }
        }
        if(Math.abs(this.x - x) != 1)
        {
            return Flag.ILLEGAL;
        }
        if(color == Color.WHITE)
        {
            if(this.y - y == 1) return Flag.LEGAL;
        }
        if(color == Color.BLACK)
        {
            if(y - this.y == 1) return Flag.LEGAL;
        }
        return Flag.ILLEGAL;
    }
}
