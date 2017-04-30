package com.andrewmatzureff.input;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.andrewmatzureff.constants.C;

/**
 * Write a description of class Keyboard here.
 * 
 * @author (Andrew Matzureff) 
 * @version (10/2/2016)
 */
public class Keyboard implements KeyListener, InputDevice
{
    public final byte[] _Keys;
    //Left 7 bits: game command index (0 - 127)
    //Right 1 bit: state description (ON?)
    
    public Keyboard()
    {
        _Keys = new byte[C.MAX_CHARS];
    }
    /**
     * See InputDevice.
     */
    public int getDeviceMask()
    {
        return KEYBOARD_MASK;
    }
    /**
     * See InputDevice.
     */
    public byte[] getBytes()
    {
        return _Keys;
    }
    /**
     * See InputDevice.
     */
    public void set(int k, int v)
    {
        if(k >= 0 && k < _Keys.length)
        {
            _Keys[k] = (byte)v;
        }
    }
    /**
     * See InputDevice.
     */
    public int get(int code, int type)
    {
        if(code >= 0 && code < _Keys.length)
        {
            switch(type)
            {
                case STATE://returns Style and Status state bits
                    return _Keys[code] & STATE_MASK;
                case COMMAND://returns unmodified command bits
                //must be shifted once to the right to
                //obtain command index
                    return _Keys[code] & COMMAND_MASK;
                default://returns key byte verbatim
                    return _Keys[code];
            }
        }
        return C.UNDEFINED;
    }
    /**
     * See InputDevice.
     */
    public boolean stateOn(int code)
    {
        if(code >= 0 && code < _Keys.length)
        {
            return (_Keys[code] & STATE_MASK) != 0;
        }
        return false;
    }
    public void keyPressed (KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code >= 0 && code < _Keys.length)
        {
            _Keys[code] |= (byte)STATE_MASK;
        }
    }
    public void keyReleased (KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code >= 0 && code < _Keys.length)
        {
            _Keys[code] &= (byte)COMMAND_MASK;
        }
    }
    public void keyTyped (KeyEvent e)
    {
    }
}
