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
        return null;
    }
}
