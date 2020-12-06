package com.chress;

import com.chress.enums.ColorBW;
import com.chress.enums.Type;

public class King extends Piece
{
    King(ColorBW colorBW, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.KING, colorBW,x ,y ,pieceHandler);
    }

}
