package com.chress;

import com.chress.enums.Flag;
import com.chress.enums.Type;

import java.awt.*;

public class Queen extends Piece
{
    Queen(Color color, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.QUEEN, color, x, y, pieceHandler, "queen");
    }

    @Override
    protected Flag validateTarget(int x, int y)
    {
        if(Math.abs(this.x - x) == Math.abs(this.y - y))
        {
            return Flag.LEGAL;
        }
        if(this.x == x && this.y == y)
        {
            return Flag.ILLEGAL;
        }
        if(this.y == y || this.x == x)
        {
            return Flag.LEGAL;
        }
        return Flag.ILLEGAL;
    }
}
