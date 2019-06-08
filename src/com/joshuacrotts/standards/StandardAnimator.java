package com.joshuacrotts.standards;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class is for animating any image/entity that has multiple images. Pass
 * it the ArrayList of BufferedImage sprites, a delay, and the
 * StandardGameObject the animation is being applied to. The sprite is applied
 * to StandardGameObject's currentSprite instance variable, so draw that one to
 * the screen.
 *
 * @author Joshua
 *
 */
public class StandardAnimator
{

    private BufferedImage[] images      = null;

    private double          counter     = 0.0;
    private long            delay       = 0;
    private int             frame       = 0;
    
    private final StandardGameObject object;

    //public static int attackCounter = 0;
    private boolean animating = true;

    public StandardAnimator( BufferedImage[] images, long delay, StandardGameObject o )
    {
        this.images = images;
        this.delay = delay;
        this.object = o;
    }

    /**
     * Method animates the list of BufferedImages.
     *
     * By default it will animate. If setAnimating(false), animation will
     * terminate.
     */
    public void animate()
    {

        if (  ! animating )
        {
            return;
        }

        if ( this.animating )
        {
            for ( BufferedImage image : images )
            {
                counter ++;
                if ( counter > delay )
                {
                    object.setCurrentSprite( image );
                    counter = 0;
                }
            }
        }
    }

    public BufferedImage[] getImages()
    {
        return images;
    }

    public void setImages( BufferedImage[] images )
    {
        this.images = images;
    }

    public double getCounter()
    {
        return counter;
    }

    public void setCounter( int counter )
    {
        this.counter = counter;
    }

    public long getDelay()
    {
        return delay;
    }

    public void setDelay( long delay )
    {
        this.delay = delay;
    }

    public int getFrame()
    {
        return frame;
    }

    public void setFrame( int frame )
    {
        this.frame = frame;
    }

    public boolean isAnimating()
    {
        return animating;
    }

    public void setAnimating( boolean animating )
    {
        this.animating = animating;
        if ( this.animating )
        {
            this.animate();
        }
    }
}
