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
        return Flag.LEGAL;
    }
}
