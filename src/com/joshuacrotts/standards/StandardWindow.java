package com.joshuacrotts.standards;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This class is the class that instantiates the Screen. It takes four
 * parameters: width, height, a title, and the Game class itself that will
 * automatically be added to the frame as a component due to it extending class
 * once it is instantiated.
 *
 * The @param title can be manipulated.
 *
 * @author Joshua
 *
 */
public class StandardWindow extends Canvas
{

    private static final long serialVersionUID = 6915741236108516353L;

    private JFrame frame;
    private String title;

    private int width;
    private int height;


    public StandardWindow( int width, int height, String title, StandardGame game )
    {
        this.frame = new JFrame();

        this.frame.setTitle( title );
        this.width = width;
        this.height = height;
        this.title = title;

        this.frame.setMinimumSize( new Dimension( width, height ) );
        this.frame.setMaximumSize( new Dimension( width, height ) );
        this.frame.setPreferredSize( new Dimension( width, height ) );

        this.frame.setResizable( false );
        this.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.frame.setLocationRelativeTo( null );

        this.frame.add( game );

        this.frame.getContentPane().setSize( new Dimension( width, height ) );

        this.frame.pack();

        this.frame.setVisible( true );

    }

    public StandardWindow( int width, int height, String title, Object game )
    {
        this.frame = new JFrame();

        this.frame.setTitle( title );
        this.width = width;
        this.height = height;
        this.title = title;

        this.frame.setMinimumSize( new Dimension( width, height ) );
        this.frame.setMaximumSize( new Dimension( width, height ) );
        this.frame.setPreferredSize( new Dimension( width, height ) );

        this.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.frame.setLocationRelativeTo( null );

        this.frame.add( ( Component ) game );

        this.frame.getContentPane().setSize( new Dimension( width, height ) );

        this.frame.pack();

        this.frame.setVisible( true );

    }

    public void setBackgroundColor( Color color )
    {
        this.frame.setBackground( color );
    }

    /**
     * @return the provided width of the frame.
     */
    public int width()
    {
        return width;
    }

    /**
     *
     * @return the provided height of the frame.
     */
    public int height()
    {
        return height;
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void setFrame( JFrame frame )
    {
        this.frame = frame;
    }

    public void setWidth( short width )
    {
        this.width = width;
    }

    public void setHeight( short height )
    {
        this.height = height;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.frame.setTitle( title );
    }
}
