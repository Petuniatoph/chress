package com.chress;

import com.chress.enums.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class King extends Piece
{
    King(Color color, int x, int y, PieceHandler pieceHandler)
    {
        super(Type.KING, color,x ,y ,pieceHandler, "king");
    }

}
