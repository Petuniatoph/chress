package com.chress;

import com.chress.enums.Flag;
import com.chress.enums.Type;

import java.awt.*;

public class Bishop extends Piece
{

    Bishop(Color color, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.BISHOP, color, x, y, pieceHandler, "bishop");
    }

    @Override
    protected Flag validateTarget(int x, int y)
    {
        return null;
    }
}
