package com.joshuacrotts.interactors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.joshuacrotts.standards.StdOps;

/**
 * This is a StandardButton; basically used for input. One should instantiate a
 * StandardButton, then work with it depending on if it is pressed, or the mouse
 * is over it, etc.
 *
 * @author Joshua
 *
 */
public class StandardButton extends Interactor implements MouseListener, KeyListener, MouseMotionListener
{

    public boolean  pressed     = false;
    public boolean  mouseOver   = false;
    private boolean isImage     = false;

    private Color color;
    private String text;
    private String fileLocation;
    private BufferedImage image;

    public StandardButton()
    {
    }

    public StandardButton( int x, int y )
    {
        super( x,  y );
    }

    public StandardButton( int x, int y, int width, int height )
    {
        super( x,  y,  width, height );
    }

    public StandardButton( int x, int y, int width, int height, Color color )
    {
        super( x, y, width, height );
        this.color = color;
    }

    public StandardButton( int x, int y, int width, int height, String text, Color color )
    {
        super(  x,  y,  width, height );
        this.text = text;
        this.color = color;
    }

    public StandardButton( int x, int y, int width, int height, int red, int green, int blue )
    {
        super( x, y, width, height );
        this.color = new Color( red, green, blue );
    }

    public StandardButton( int x, int y, String fileLocation )
    {
        super( x, y );

        try
        {
            this.image = ImageIO.read( new File( fileLocation ) );
        } catch ( IOException e )
        {
            e.printStackTrace();
        }

        this.setWidth( this.image.getWidth() );
        this.setHeight( this.image.getHeight() );
        this.isImage = true;
    }

    public StandardButton( int x, int y, BufferedImage image )
    {
        super( x, y );

        this.image = image;

        this.setWidth( this.image.getWidth() );
        this.setHeight( this.image.getHeight() );
        this.isImage = true;
    }

    @Override
    public void tick()
    {

        this.setX( ( this.getX() + this.getVelX() ) );
        this.setY( ( this.getY() + this.getVelY() ) );

        if ( mouseOver )
        {
            this.color = Color.green;
            if ( pressed )
            {
                this.color = Color.BLUE;
            } else
            {
                this.color = Color.GREEN;
            }
        } else
        {
            this.color = Color.red;
        }

    }

    @Override
    public void render( Graphics2D g2 )
    {
        if ( isImage )
        {
            g2.drawImage( this.image, this.getX(), this.getY(), null );
        } else
        {
            g2.setColor( this.color );
            g2.fillRect( this.getX(), this.getY(), this.getWidth(), this.getHeight() );

            g2.setColor( Color.WHITE );

            if ( this.text != null )
            {
                g2.drawString( this.text, this.getX() + ( this.getHeight() >> 1 ), this.getY() + ( this.getHeight() >> 1 ) );
            }
        }
    }

    @Override
    public void mousePressed( MouseEvent e )
    {

        if ( this.isInteractable() )
        {
            System.out.println( "Mouse clicked button." );

            //Handle what happens when the StandardButton is clicked.
            this.pressed = StdOps.mouseOver( e.getX(), e.getY(), this.getX(), this.getY(), this.getWidth(), this.getHeight() );
        }
    }

    @Override
    public void mouseReleased( MouseEvent e )
    {

        if ( this.isInteractable() )
        {
            System.out.println( "Mouse released button." );

            //Handle what happens when the StandardButton is released.
            if ( StdOps.mouseOver( e.getX(), e.getY(), this.getX(), this.getY(), this.getWidth(), this.getHeight() ) )
            {
                this.pressed = false;
            }
        }
    }

    @Override
    public void mouseMoved( MouseEvent e )
    {

        if ( this.isInteractable() )
        {
            //Determines if the mouse is over the button or not.
            this.mouseOver = StdOps.mouseOver( e.getX(), e.getY(), this.getX(), this.getY(), this.getWidth(), this.getHeight() ); //System.out.println("Mouse entered button.");
            //System.out.println("Mouse exited button.");
        }
    }

    @Override
    public void mouseClicked( MouseEvent e )
    {

    }

    @Override
    public void mouseEntered( MouseEvent e )
    {

    }

    @Override
    public void mouseExited( MouseEvent e )
    {

    }

    @Override
    public void mouseDragged( MouseEvent e )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped( KeyEvent e )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed( KeyEvent e )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased( KeyEvent e )
    {
        // TODO Auto-generated method stub

    }

    public String getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation( String fileLocation )
    {
        this.fileLocation = fileLocation;
    }

    @Override
    public String toString()
    {
        return "StandardButton Object: X: " + this.getX() + "\tY: " + this.getY() + "\tWidth: " + this.getWidth() + "\tHeight: " + this.getHeight() + "\tText: " + this.text + "\tColor: " + this.color;
    }

}
