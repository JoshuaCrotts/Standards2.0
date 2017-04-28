package com.andrewmatzureff.commands;
/**
 * Write a description of class NullCommand here.
 * 
 * @author (Andrew Matzureff) 
 * @version (10/19/2016)
 */
public class Null extends Command
{
    public Null()
    {
    }
    public void execute()
    {
        System.out.println("No Command has been set.");
    }
}
