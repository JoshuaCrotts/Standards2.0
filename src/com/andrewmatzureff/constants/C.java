package com.andrewmatzureff.constants;

import java.awt.event.*;

/**
 * Write a description of class C here.
 *
 * @author (Andrew Matzureff)
 * @version (10/3/2016)
 */
public class C
{

    //MASKS//
    public static final int MOUSE_MASK = -2147483648;//10...0; flag must exist within 2 most significant bytes
    public static final int KEYBOARD_MASK = 0;//0...0
    public static final int COMMAND_MASK = 254;//1111 1110; pre-accounts for byte to int sign extension
    public static final int STATE_MASK = 1;//0000 0001; will ALWAYS equal 1!!!
    public static final int TOGGLE_MASK = 3;//0000 0011
    public static final long RELEASE_MASK =  ~ 3;//1111 1100
    public static final int CHAR_MASK = ( ( int ) ( Character.MAX_VALUE ) << 1 ) | 1;
    public static final long NANO = 1000000000;
    /*
     * NOTE about masks: value & mask == mask checks for presence of a flag in
     * the final result whereas value & mask == value tests for the equality
     * between the flag and the result. 1001 0011 & 1000 0000 = 1000 0000; ==
     * 1000 0000 is true: mouse flag is present in tested value == 1001 0011 is
     * true: mouse flag is only flag present in tested value
     */
    //ENUMS//
    public static final int END_OF_FILE = -1;
    public static final int NULL = 0;
    public static final int STATE = 1;
    public static final int COMMAND = 2;
    public static final int BOTH = 0;
    public static final int UNDEFINED =  ~ 0;
    public static final int TOGGLE = 1;
    public static final int CONTINUOUS = 0;

    //OTHER//
    public static final int MAX_COMMANDS = 128;
    public static final int ESCAPE = KeyEvent.VK_ESCAPE;
    public static final int DELETE = KeyEvent.VK_DELETE;
    public static final int MAX_SCAN = 1 << 10;//1024
    public static final int BITS_INT = 32;
    public static final int BITS_LONG = 64;
    public static final int MAX_CHARS = ( int ) ( Character.MAX_VALUE ) + 1;
    public static final char NULL_CHAR = ( char ) 0;
    public static final String TXT = "txt";
    public static final String TX8 = "tx8";
    public static final String T16 = "t16";
    public static final String B16 = "b16";
    public static final String FILE_SEPARATOR = java.io.File.separator;
}
