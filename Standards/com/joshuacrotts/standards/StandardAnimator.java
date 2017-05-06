package com.joshuacrotts.standards;

import com.andrewmatzureff.constants.C;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class is for animating any image/entity that has multiple images.
 * Pass it the ArrayList of BufferedImage sprites, a delay, and the StandardGameObject
 * the animation is being applied to. The sprite is applied to StandardGameObject's currentSprite 
 * instance variable, so draw that one to the screen.
 * 
 * @author Joshua
 *
 */
public class StandardAnimator{

    private ArrayList<BufferedImage> images;

    private long counter = 0;
    private long delay = 1;
    private long frame = 0;
    private long time = 0;
    private StandardGameObject object;

    //public static int attackCounter = 0;

    private boolean animating = true;

    public StandardAnimator(ArrayList<BufferedImage> images, double delay, StandardGameObject o){
        this.images = images;
        this.delay = (long)(delay * C.NANO);
        this.object = o;
    }

    /**
     * Method animates the list of BufferedImages.
     * 
     * By default it will animate.
     * If setAnimating(false), animation will terminate.
     */
    public void animate(){//got it!
        //System.out.println("Animating...");
        long current = System.nanoTime();
        long interval = current - time;
        time = current;
        if(interval - delay > 0)
            counter = 0;
        else
            counter += interval;
            frame = counter / delay;
        object.setCurrentSprite(this.images.get((int)frame % images.size()));
    }
    
    public void animate0(){//on the right path...
        //System.out.println("Animating...");
        long current = System.nanoTime();
        long interval = current - time;
        time = current;
        //if(interval - delay > 0)
            frame += (int)(interval / delay);
        object.setCurrentSprite(this.images.get((int)frame % images.size()));
    }
    
    public void animateOld(){//old old...
        
        if(!animating) return;
        
        for(int i = (int)frame; i<images.size(); i++){
            counter++;
            if(counter > delay){            
                object.setCurrentSprite(this.images.get(i));
                counter = 0;
            }
        }
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<BufferedImage> images) {
        this.images = images;
    }
    
    public void reset(){
        this.counter = 0;
        this.frame = 0;
        object.setCurrentSprite(this.images.get(0));
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getFrame() {
        return frame;
    }

    public void setFrame(long frame) {
        this.frame = frame;
    }

    public boolean isAnimating() {
        return animating;
    }

    public void setAnimating(boolean animating) {
        this.animating = animating;
        if(this.animating)
            this.animate();
	}
}