package com.chress;

/**
 * Abstract Piece Class with base Methods for rendering and Game Logic
 *
 * @author Christoph Schobesberger
 * @version 1.0
 */

import com.chress.enums.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public abstract class Piece
{
    protected int x;
    protected int y;
    protected final Type type;
    protected boolean isAlive;
    protected final Color color;
    protected final char pieceCharacter;
    protected BufferedImage image;

    protected final PieceHandler pieceHandler;

    Piece(Type type, Color color, int x, int y, PieceHandler pieceHandler, String fileName)
    {
        this.type = type;
        this.color = color;
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.pieceHandler = pieceHandler;
        this.pieceCharacter = 'T';
        try
        {
            if(color == Color.WHITE) fileName = fileName + "W.png";
            else fileName = fileName + "B.png";
            image = ImageIO.read(new File(System.getProperty("user.dir") + "\\" + fileName));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
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

    public boolean getIsAlive()
    {
        return this.isAlive;
    }

    public Type getType()
    {
        return type;
    }

    public Color getColor()
    {
        return color;
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
     *
     * @param x TargetX
     * @param y TargetY
     * @return LEGAL: Move was Successful ILLEGAL: Move is not allowed CHECK: Move was Successful and other King is in Danger
     */

    public Flag movePiece(int x, int y)
    {
        Piece targetPiece = pieceHandler.selectPiece(x, y);
        Flag tempFlag;
        int saveX = this.x;
        int saveY = this.y;
        boolean hit = false;

        tempFlag = validateMove(x, y);
        if(tempFlag != Flag.LEGAL)
        {
            return tempFlag;
        }
        if(targetPiece == null)
        {
            this.x = x;
            this.y = y;
        }
        else
        {
            hit = true;
            this.x = x;
            this.y = y;
            targetPiece.setAlive(false);
            targetPiece.setX(-1);
            targetPiece.setY(-1);
        }
        tempFlag = validateKing();
        if(tempFlag == Flag.ILLEGAL)
        {
            System.out.println("MOVE PIECE: King is in Danger");
            if(hit)
            {
                targetPiece.setAlive(false);
                targetPiece.setX(x);
                targetPiece.setY(y);
            }
            this.x = saveX;
            this.y = saveY;
        }
        return tempFlag;
    }

    /**
     * validates the target's coordinates if move is allowed, gets Overwritten
     *
     * @param x TargetX
     * @param y TargetY
     * @return LEGAL:
     */

    protected abstract Flag validateTarget(int x, int y);

    /**
     * validateMove
     * @param x
     * @param y
     * @return
     */

    private Flag validateMove(int x, int y)
    {
        Piece targetPiece = pieceHandler.selectPiece(x, y);
        Flag tempFlag;

        if(targetPiece != null)
        {
            if(targetPiece.getColor() == color)
            {
                System.out.println("MOVE PIECE: target is same color");
                return Flag.ILLEGAL;
            }
        }
        tempFlag = validateTarget(x, y);
        if(tempFlag != Flag.LEGAL)
        {
            System.out.println("MOVE PIECE: invalid target");
            return tempFlag;
        }
        tempFlag = validateMovement(this.x,this.y, x, y);
        if(tempFlag != Flag.LEGAL)
        {
            System.out.println("MOVE PIECE: invalid Movement");
            return tempFlag;
        }
        return Flag.LEGAL;
    }

    /**
     * determinds if there is another Piece in the Way
     *
     * @param startX StartX
     * @param startY StartY
     * @param endX   endX
     * @param endY   endY
     * @return LEGAL or ILLEGAL
     */

    public Flag validateMovement(int startX, int startY, int endX, int endY)
    {
        switch (getDirection(startX, startY, endX, endY))
        {
            case UP:
                startY--;
                break;
            case UP_RIGHT:
                startX++;
                startY--;
                break;
            case RIGHT:
                startX++;
                break;
            case DOWN_RIGHT:
                startX++;
                startY++;
                break;
            case DOWN:
                startY++;
                break;
            case DOWN_LEFT:
                startX--;
                startY++;
                break;
            case LEFT:
                startX--;
                break;
            case UP_LEFT:
                startX--;
                startY--;
                break;
            case NONE:
                return Flag.LEGAL;
        }
        if (pieceHandler.selectPiece(startX, startY) != null && !(startX == endX && startY == endY))
        {
            return Flag.ILLEGAL;
        }
        return validateMovement(startX, startY, endX, endY);


    }

    /**
     * getDirection
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */

    public Direction getDirection(int startX, int startY, int endX, int endY)
    {
        // UP
        if (startX == endX && startY > endY) return Direction.UP;
        // UP RIGHT
        if (startX < endX && startY > endY) return Direction.UP_RIGHT;
        // RIGHT
        if (startX < endX && startY == endY) return Direction.RIGHT;
        // DOWN RIGHT
        if(startX < endX) return Direction.DOWN_RIGHT;
        // DOWN
        if(startX == endX && startY < endY) return Direction.DOWN;
        // LEFT DOWN
        if(startX > endX && startY < endY) return Direction.DOWN_LEFT;
        // LEFT
        if(startX > endX && startY == endY) return Direction.LEFT;
        // UP LEFT
        if(startX > endX) return Direction.UP_LEFT;;
        return Direction.NONE;
    }


    /**
     * Checks if own King is in Danger or the other King is in check
     *
     * @return
     */

    private Flag validateKing()
    {
        Piece king_player = pieceHandler.getPieceByIndex(pieceHandler.getCurrentPlayer(), 0);
        int king_x = king_player.getX();
        int king_y = king_player.getY();
        Color notPlayer = Color.BLACK;
        if(king_player.getColor() == Color.BLACK) notPlayer = Color.WHITE;

        if(moveToKing(notPlayer, king_x, king_y))
        {
            return Flag.ILLEGAL;
        }

        king_player = pieceHandler.getPieceByIndex(notPlayer, 0);
        king_x = king_player.getX();
        king_y = king_player.getY();

        if(moveToKing(pieceHandler.getCurrentPlayer(), king_x, king_y))
        {
            return Flag.CHECK;
        }

        return Flag.LEGAL;
    }

    /**
     *
     * @param color
     * @param king_x
     * @param king_y
     * @return
     */

    private boolean moveToKing(Color color, int king_x, int king_y)
    {
        boolean alive;
        Piece piece;
        int size = pieceHandler.getPlayer1().size();
        for (int i = 0; i < size; i++)
        {
            piece = pieceHandler.getPieceByIndex(color, i);
            alive = piece.getIsAlive();
            if (Flag.LEGAL == piece.validateMove(king_x, king_y) && alive)
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Renders Piece
     *
     * @param g
     */

    public void render(Graphics g)
    {
        g.drawImage(image, x * 75,y * 75, null);
    }


}
