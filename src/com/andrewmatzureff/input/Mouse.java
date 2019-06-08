package com.andrewmatzureff.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.andrewmatzureff.constants.C;

/**
 * Write a description of class Mouse here.
 *
 * @author (Andrew Matzureff)
 * @version (10/3/2016)
 */
public class Mouse implements MouseListener, MouseMotionListener, InputDevice
{

    public final byte[] _Buttons;
    //Left 7 bits: game command index (0 - 127)
    //Right 1 bit: state description (ON?)

    public int x = 0;
    public int y = 0;

    public Mouse()
    {
        _Buttons = new byte[ 16 ];
    }

    /**
     * See InputDevice.
     */
    public int getDeviceMask()
    {
        return MOUSE_MASK;
    }

    /**
     * See InputDevice.
     */
    public byte[] getBytes()
    {
        return _Buttons;
    }

    /**
     * See InputDevice.
     */
    public void set( int k, int v )
    {
        if ( k >= 0 && k < _Buttons.length )
        {
            _Buttons[k] = ( byte ) v;
        }
    }

    /**
     * See InputDevice.
     */
    public int get( int code, int type )
    {
        if ( code >= 0 && code < _Buttons.length )
        {
            switch ( type )
            {
                case STATE://returns State bit
                    return _Buttons[code] & STATE_MASK;
                case COMMAND://returns unmodified command bits
                    //must be shifted once to the right to
                    //obtain command index
                    return _Buttons[code] & COMMAND_MASK;
                default://returns key byte verbatim
                    return _Buttons[code];
            }
        }
        return C.UNDEFINED;
    }

    /**
     * See InputDevice.
     */
    public boolean stateOn( int code )
    {
        if ( code >= 0 && code < _Buttons.length )
        {
            return ( _Buttons[code] & STATE_MASK ) != 0;
        }
        return false;
    }

    public void update( MouseEvent e )//would probably be inherited from Analogue Interface
    {
        x = e.getX();
        y = e.getY();
    }

    public void mousePressed( MouseEvent e )
    {
        update( e );
        int code = e.getButton();
        if ( code >= 0 && code < _Buttons.length )
        {
            _Buttons[code] |= ( byte ) STATE_MASK;
        }
    }

    public void mouseReleased( MouseEvent e )
    {
        update( e );
        int code = e.getButton();
        if ( code >= 0 && code < _Buttons.length )
        {
            _Buttons[code] &= ( byte ) COMMAND_MASK;
        }
    }

    public void mouseClicked( MouseEvent e )
    {
        update( e );
    }

    public void mouseEntered( MouseEvent e )
    {
        update( e );
    }

    public void mouseExited( MouseEvent e )
    {
        update( e );
    }

    public void mouseMoved( MouseEvent e )
    {
        update( e );
    }

    public void mouseDragged( MouseEvent e )
    {
        update( e );
    }
}
