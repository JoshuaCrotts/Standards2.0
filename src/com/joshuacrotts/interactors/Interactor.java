package com.joshuacrotts.interactors;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Interactor
{

    private int x;
    private int y;

    private int velX;
    private int velY;

    private int width;
    private int height;

    private boolean interactable = true;

    public Interactor()
    {

    }

    public Interactor( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    public Interactor( int x, int y, boolean interactable )
    {
        this.x = x;
        this.y = y;
        this.interactable = interactable;
    }

    public Interactor( int x, int y, int width, int height )
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Interactor( int x, int y, int width, int height, boolean interactable )
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.interactable = interactable;
    }

    public abstract void tick();

    public abstract void render( Graphics2D g2 );

    public Rectangle getBounds()
    {
        return new Rectangle( this.x, this.y, this.width, this.height );
    }

    public int getX()
    {
        return x;
    }

    public void setX( int x )
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY( int y )
    {
        this.y = y;
    }

    public int getVelX()
    {
        return velX;
    }

    public void setVelX( int velX )
    {
        this.velX = velX;
    }

    public int getVelY()
    {
        return velY;
    }

    public void setVelY( int velY )
    {
        this.velY = velY;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth( int width )
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight( int height )
    {
        this.height = height;
    }

    public boolean isInteractable()
    {
        return interactable;
    }

    public void setInteractable( boolean interactable )
    {
        this.interactable = interactable;
    }

}
