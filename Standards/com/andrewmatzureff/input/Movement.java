package com.andrewmatzureff.input;

import com.andrewmatzureff.commands.Command;
import com.joshuacrotts.standards.*;

/**
 * Command used for moving the player... or any SGO!
 * That's another feature I never told you about my Command 
 * framework and why it's so highly abstracted! You see, the 
 * InputDevice interface is pretty neat in how it can be used to 
 * interact with Commands... I never said an InputDevice HAD to
 * necessarily b e a physical device, we just assume that's the 
 * standard case...! Technically, an InputDevice can be anything
 * as long as it implements a few methods and stores its inputs
 * as a byte array! With this flexibility, not only can we control
 * SGOs like my TestPlayer class with physical human
 * interaction devices... but we can also control OTHER entities
 * with (you guessed it!) VIRTUAL devices! That's right! We can
 * use this framework to write AIControllers!!!
 * 
 * @author Andrew Matzureff
 * @version (4/16/2017)
 *
 */

public class Movement extends Command{

    public StandardGameObject player;
    public StandardAnimator animator;
    
    public float deltaX;
    public float deltaY;
    
    public Movement(StandardGameObject sgo, StandardAnimator sa, float deltax, float deltay){
        this.player = sgo;
        this.animator = sa;
        this.deltaX = deltax;
        this.deltaY = deltay;
    }
    
    public void execute(){
        //System.out.println("Executing...");
        if(animator != null)
        	animator.animate();
        player.setVelX(player.getVelX() + this.deltaX);
        player.setVelY(player.getVelY() + this.deltaY);
    }
}
