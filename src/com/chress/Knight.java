package com.chress;

import com.chress.enums.Flag;
import com.chress.enums.Type;

import java.awt.*;

public class Knight extends Piece
{
    Knight(Color color, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.KNIGHT, color, x, y, pieceHandler, "knight");
    }

    @Override
    protected Flag validateTarget(int x, int y)
    {
        if(this.x + 2 == x && ((this.y - 1 == y) || (this.y + 1 == y)))
        {
            return Flag.LEGAL;
        }
        if(this.x - 2 == x && ((this.y - 1 == y) || (this.y + 1 == y)))
        {
            return Flag.LEGAL;
        }
        if(this.y + 2 == y && ((this.x - 1 == x) || (this.x + 1 == x)))
        {
            return Flag.LEGAL;
        }
        if(this.y - 2 == y && ((this.x - 1 == x) || (this.x + 1 == x)))
        {
            return Flag.LEGAL;
        }
        return Flag.ILLEGAL;
    }

    @Override
    public Flag validateMovement(int startX, int startY, int endX, int endY)
    {
        return Flag.LEGAL;
    }
}
