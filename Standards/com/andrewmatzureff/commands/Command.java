package com.andrewmatzureff.commands;
import com.andrewmatzureff.constants.C;
import com.andrewmatzureff.input.InputDevice;
import com.joshuacrotts.standards.StandardGame;
/**
 * Write a description of interface Command here.
 * 
 * @author (Andrew Matzureff) 
 * @version (10/3/2016)
 */
public abstract class Command
{
    public static final int MAX_COMMANDS = C.MAX_COMMANDS;
    public static final int UNDEFINED = C.UNDEFINED;
    public static final int ESC = C.ESCAPE;
    public static final int DEL = C.DELETE;
    public static final int STATE_MASK = C.STATE_MASK;
    public static final int TOGGLE = C.TOGGLE;
    public static final int TOGGLE_MASK = C.TOGGLE_MASK;
    public static final int CONTINUOUS = C.CONTINUOUS;
    public static final Command NULL = new Null();
    protected static Command[] GAME_COMMANDS = new Command[MAX_COMMANDS];
    public static int[] KEY_BINDS = new int[MAX_COMMANDS];
    protected int _Style = CONTINUOUS;
    protected long _States = 0;
    protected int _Bits = 0;
    protected boolean _On = false;
    //KEY_BINDS entries 0 - 65535: Keyboard while
    //entries 65536 - 65551 are reserved for Mouse
    /**
     * Initializes the Command framework;
	 * unbound keys MUST use the UNDEFINED
	 * constant as explicit checks for this
	 * value are performed throughout the
	 * framework; null entries in the global
	 * Command list are NOT permitted;
	 * failure to adhere to these guidelines
	 * will result in IndexOutOfBounds and
	 * NullPointer exceptions.
     */
    public static void init()
    {
        java.util.Arrays.fill(KEY_BINDS, UNDEFINED);
        java.util.Arrays.fill(GAME_COMMANDS, NULL);
    }
    /**
     * This execute() method is intended to be exposed to the user.
     * In a typical implementation, this method will be called 128
	 * times (or the maximum number of simultaneously existent
	 * Commands) during each update. Even if a Command is not in
	 * use or has not been defined this method will still be
     * invoked on a key during the update loop. This static
	 * version of execute() is responsible for determining
	 * whether or not a Command has actually been triggered
	 * and, in such a case, invoking its corresponding
     * user-defined execute() method. There are 2 primary styles by
	 * which a Command can be triggered by an InputDevice:
	 * CONTINUOUS and via a TOGGLE. The simpler of the 2,
	 * CONTINUOUS, simply works by setting a Command's _On
	 * field to true or false depending on the last recorded
	 * state of that Command's corresponding key or button trigger.
	 * 1 means a key press is in progress while 0 indicates a
	 * release. CONTINUOUS Commands maintain a direct relationship
	 * with their representative elements' values in the
     * structures backing the devices to which they're bound.
     * TOGGLE Commands, however, are slightly more complex. Key
	 * strikes are progressively logged (up to the most recent
     * 64 button states) in the form of an 8-byte bit string
	 * (this is happening regardless of _Style), the 62 most
     * significant bits serving no integral purpose (although, I'm
	 * sure one could find something cool to use them for; who
	 * knows, maybe analogu e emulation is possible this way...).
	 * What we're really concerned with are the 2 least
	 * significant bits (rightmost and most recent states). The
	 * abridged explanation is as follows: 00, 01, and 11 do
	 * NOT trigger a state change, while a 10 does, meaning a
	 * Command will be toggled to its opposite state ONLY at
	 * the instant its key or button has been released.
     * 
     * @param key contents of an array element represented by a
     * physical key or button; note that this argument is NOT
     * the actual physical key that's been pressed, but the
     * state and command index information in the array element
     * representing the physical key's status. See InputDevice.get().
     */
    public static void execute(int key)//key (value returned by InputDevice.get()) CAN be UNDEFINED!
    {
        if(key == UNDEFINED)
        return;
        int index = key >> STATE_MASK;
        key = key & STATE_MASK;
        Command command = GAME_COMMANDS[index];
        command._Bits = command._Bits - (int)(command._States >>> (C.BITS_LONG - 1)) + key;//subtract leftmost bit being shifted off, add most recent bit
        command._States = (command._States << STATE_MASK) | key;//1111 1110; advance the key/ button strike sequence and append the most recent button state onto the bit string
        
        switch(command._Style)
        {
            case CONTINUOUS:
                if(key == STATE_MASK)
                    command._On = true;
                else
                    command._On = false;
                break;
            case TOGGLE:
                if(( (command._States ^ TOGGLE_MASK) & TOGGLE_MASK) == STATE_MASK)//this means the 2 rightmost bits are a 1 followed by a 0, indicating a press and a release
                    command._On = !command._On;
                break;
            default://User Style-Handling
                break;
        }
        if(command._On)
            command.execute();
    }
    /**
     * The main method of a Command, this member
	 * version of execute() is called by the static
	 * execute(int) method when a Command has been
	 * triggered by its corresponding bind.
     */
    public abstract void execute();
    /**
     * Prepares a specified key to be
     * bound, deleted or the operation to
     * be cancelled.
     * 
     * @param id InputDevice from which the
     * specified key argument will be
     * bound to this Command.
     * @param key key to which this
     * Command will be bound.
     */
    public boolean on(){return _On;}
    public void bind(InputDevice id, int key)
    {
        switch(key)
        {
            case ESC:
                return;
            case UNDEFINED:
                this.clear(id);
                return;
            default:
                this.assign(id, key);
        }
        return;
    }
    /**
     * Removes all keys bound to this
     * Command.
     * 
     * @param id InputDevice from which the
     * specified key argument will be
     * bound to this Command.
     * @param key Key to which this
     * Command will be bound.
     */
    protected void assign(InputDevice id, int key)
    {
        //GAME_COMMANDS must be filled BEFORE assigning any keys!
        //Null entries are not permitted!
        //cases that must be accounted for:
        //undefined key bind found
        //this Command is already bound to a key
        //key in question is already bound to another action
        //
        //KEY_BINDS is full
        for(int i = 0; i < MAX_COMMANDS; i++)//iterate through list of physical keys and buttons
        {
            if(KEY_BINDS[i] == key)//this key or button has already been assigned an action
            {
                int commandIndex = id.get(KEY_BINDS[i], id.COMMAND) >> STATE_MASK;
                if(GAME_COMMANDS[commandIndex] == this)//this key or button has been assigned this action
                    return;
                else//this key or button has been assigned a different action
                {
                    GAME_COMMANDS[commandIndex].clear(id);//discard the existing key binds
                    id.set(key, (this.index(true) << STATE_MASK));
                    return;
                }
            }
            else if(KEY_BINDS[i] == UNDEFINED)
            {
                KEY_BINDS[i] = key | id.getDeviceMask();
              //  System.out.println("KEY_BINDS[i] = "+KEY_BINDS[i]);
              //  System.out.println("Device Mask = "+id.getDeviceMask());
               // System.out.println("Keyboard Mask = "+C.KEYBOARD_MASK);
                id.set(key, (this.index(true) << STATE_MASK));
                return;
            }
        }
    }
    /**
     * Removes all keys bound to this
     * Command.
     * 
     * 
     * @param id InputDevice from which this
     * Command's corresponding key entries
     * will be cleared to reflect its
     * vacancy.
     */
    public void clear(InputDevice id)
    {
        for(int i = 0; i < MAX_COMMANDS; i++)
        {
            //check existing binds
            if(KEY_BINDS[i] != UNDEFINED)
            {
                int commandIndex = id.get(KEY_BINDS[i], id.COMMAND) >> STATE_MASK; //STATE_MASK = 1 so a right shift of 1 is performed
                if(GAME_COMMANDS[commandIndex] == this)
                {
                    id.set(KEY_BINDS[i], 0);//id.getBytes()[KEY_BINDS[i]] = 0, basically; clears command index and state of the key, technically not required
                    KEY_BINDS[i] = UNDEFINED;
                }
            }
        }
    }
    /**
     * Searches and retrieves this
     * Command's index in the
     * GAME_COMMANDS array if it
     * exists.
     * 
     * 
     * @return This Command's index
     * in the GAME_COMMANDS array
     * or UNDEFINED if the search
     * fails (exceeds MAX_COMMANDS).
     */
    public int index(boolean insert)
    {
        for(int i = 0; i < MAX_COMMANDS; i++)
        {
            if(GAME_COMMANDS[i] == this)
            {
                return i;
            }
            else
            if(GAME_COMMANDS[i] == NULL && insert)
            {
                GAME_COMMANDS[i] = this;
                return i;
            }
        }
        return UNDEFINED;
    }
    
    public static void tick(StandardGame stdGame){
    	InputDevice id;
        for(int i = 0; i < C.MAX_COMMANDS; i++)//this loop executes every Command that has been assigned if it has been triggered by its corresponding input
        {
            int key = Command.KEY_BINDS[i];
            
            if((key & C.MOUSE_MASK) == C.MOUSE_MASK)
            {
                id = stdGame.getMouse();
                key ^= C.MOUSE_MASK;
            }
            else
            {
                id = stdGame.getKeyboard();
            }
            key = id.get(key, C.BOTH);
            Command.execute(key);
        }
    }
}
