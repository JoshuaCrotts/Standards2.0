package com.andrewmatzureff.commands;

import com.andrewmatzureff.constants.C;

/**
 * Write a description of class PrintCommand here.
 *
 * @author (Andrew Matzureff)
 * @version (10/3/2016)
 */
public class PrintCommand extends Command
{

    final long _StartTime;

    public PrintCommand( long time, int i )
    {
        _Style = C.TOGGLE;
        _StartTime = time;
        GAME_COMMANDS[i] = this;
    }

    public void execute()
    {
        System.out.println( "Command executed at: " + ( System.nanoTime() - _StartTime ) / 1000000000d + "s." );
    }
}
