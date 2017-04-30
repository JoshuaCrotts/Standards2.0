package com.andrewmatzureff.input;

import com.andrewmatzureff.constants.C;
/**
 * Interface InputDevice - HIDs such as keyboards, mouses, and controllers should implement this interface
 * so that Commands may be linked to their inputs.
 * 
 * @author (Andrew Matzureff)
 * @version (10/18/2016)
 */
public interface InputDevice
{
    public static final int COMMAND_MASK = C.COMMAND_MASK;
    public static final int STATE_MASK = C.STATE_MASK;
    public static final int MOUSE_MASK = C.MOUSE_MASK;
    public static final int KEYBOARD_MASK = C.KEYBOARD_MASK;
    public static final int STATE = C.STATE;
    public static final int COMMAND = C.COMMAND;
    public static final int BOTH = C.BOTH;
    public static final int UNDEFINED = C.UNDEFINED;
    public static final int TOGGLE = C.TOGGLE;
    public static final int CONTINUOUS = C.CONTINUOUS;
    /**
     * Provides an interface by which the structures backing
     * different input devices can be modified without
     * explicite knowledge of the device in question.
     * 
     * @return The byte array containing state and
     * command info.
     */
    public byte[] getBytes();
    /**
     * Retrieves this InputDevice's "device mask".
     * 
     * @return returns a mask indicating what type of device is being operated on.
     */
    public int getDeviceMask();
    /**
     * Manually assign a Command and State to a key.
     * 
     * @param k key or button to be set
     * @param v value to be assigned
     */
    public void set(int k, int v);
    /**
     * Returns information about the specified key.
     * 
     * @param code key or button in question
     * @param type the type of information to be retrieved (see ENUMS in Constants)
     * @return current state or command status or both
     */
    public int get(int code, int type);
    /**
     * Evaluate the state of the specified key.
     * 
     * @param code key or button in question
     * @return current state of the key or button represented by code
     */
    public boolean stateOn(int code);
}
