package com.chress;

import com.chress.enums.Flag;
import com.chress.enums.Type;

import java.awt.*;

public class Rook extends Piece
{

    Rook(Color color, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.QUEEN, color, x, y, pieceHandler, "rook");
    }

    @Override
    protected Flag validateTarget(int x, int y)
    {
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
