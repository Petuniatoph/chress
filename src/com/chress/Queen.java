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
        return null;
    }
}
