package com.chress;

/**
 * Abstract Piece Class with base Methods for rendering and Game Logic
 *
 * @author Christoph Schobesberger
 * @version 1.0
 */

import com.chress.enums.*;
import com.chress.enums.ColorBW;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public abstract class Piece
{
    private int x;
    private int y;
    private final Type type;
    private boolean isAlive;
    private final ColorBW colorBW;
    //TODO Add Image

    private final PieceHandler pieceHandler;

    Piece(Type type, ColorBW colorBW, int x, int y, PieceHandler pieceHandler)
    {
        this.type = type;
        this.colorBW = colorBW;
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.pieceHandler = pieceHandler;
    }

    // GETTER / SETTER
    public int getY()
    {
        return y;
    }
    public int getX()
    {
        return x;
    }
    public boolean getIsAlive() { return this.isAlive;}
    public Type getType()
    {
        return type;
    }
    public ColorBW getColor()
    {
        return colorBW;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setAlive(boolean alive)
    {
        this.isAlive = alive;
    }

    /**
     * moves Piece to a Target
     * @param x TargetX
     * @param y TargetY
     * @return LEGAL: Move was Successful ILLEGAL: Move is not allowed CHECK: Move was Successful and other King is in Danger
     */

    public Flag movePiece(int x, int y)
    {
        return Flag.LEGAL;
    }

    /**
     * validates the target's coordinates if move is allowed, get Overwritten
     * @param x TargetX
     * @param y TargetY
     * @return LEGAL:
     */

    private Flag validateTarget(int x, int y)
    {
        return Flag.LEGAL;
    }

    /**
     * determinds if there is another Piece in the Way
     * @param startX StartX
     * @param startY StartY
     * @param endX endX
     * @param endY endY
     * @return LEGAL or ILLEGAL
     */

    private Flag validateMovement(int startX, int startY,int endX,int endY)
    {
        return Flag.LEGAL;
    }

    /**
     * Checks if own King is in Danger or the other King is in check
     * @return
     */

    private Flag validateKing()
    {
        return Flag.LEGAL;
    }

    /**
     * Renders Piece
     * @param g
     */

    public void render(Graphics g)
    {
        /*
        try
        {
            BufferedImage image = ImageIO.read(new File("D:\\OneDrive\\Studium\\Java\\Tutorial\\chress\\King.png"));
            g.drawImage(image, x, y, null);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        */
        //TODO Render Image
    }



}
