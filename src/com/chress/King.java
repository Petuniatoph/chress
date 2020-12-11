package com.chress;

import com.chress.enums.Flag;
import com.chress.enums.Type;

import java.awt.*;

public class King extends Piece
{
    King(Color color, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.KING, color,x ,y ,pieceHandler, "king");
    }

    @Override
    protected Flag validateTarget(int x, int y)
    {
        if ((Math.abs(this.x - x) > 1) || (Math.abs(this.y - y) > 1))
        {
            return Flag.ILLEGAL;
        }
        if ((Math.abs(this.x - x) == 0) && (Math.abs(this.y - y) == 0))
        {
            return Flag.ILLEGAL;
        }
        return Flag.LEGAL;
    }
}
