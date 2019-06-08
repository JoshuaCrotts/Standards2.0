package com.joshuacrotts.standards;

import java.awt.Color;

public class StandardFade
{
    private float time = 0.0f;
    private boolean firstColor = true;

    private final Color color1;
    private final Color color2;
    private final double alpha;

    /**
     * Finally! I got this method to work. Lines that implement the r,g and b
     * values come from Princeton University; I will author them in at the
     * bottom. I calculated the time and variables to switch the colors
     * accordingly.
     *
     * Method takes two parameters and fades them into one another according to
     * a timer/clock value: alpha.
     *
     * @param c1 First color to be used.
     * @param c2 Second color to be used.
     * @param alpha How fast colors should shift. 0 <= n <= 1. Closer value is
     * to zero, the longer it will take to shift. ***Important note about alpha:
     * for non-seizure inducing colors, alpha <= .0005***
     *
     *
     * @return new Color based on r, g, and b values calculated.
     *
     * @author (Only code utilized was lines 58-60):
     * http://introcs.cs.princeton.edu/java/31datatype/Fade.java.html
     */
    public StandardFade( Color c1, Color c2, double alpha )
    {
        this.color1 = c1;
        this.color2 = c2;

        this.alpha = alpha;
    }

    public Color combine()
    {

        if ( time <= 1.0f && firstColor )
        {
            time += alpha;
        } else
        {
            firstColor = false;
        }
        if ( time >= 0.0f &&  ! firstColor )
        {
            time -= alpha;
        } else
        {
            firstColor = true;
        }

        int r = ( int ) ( time * color2.getRed()   + ( 1.0 - time ) * color1.getRed() );
        int g = ( int ) ( time * color2.getGreen() + ( 1.0 - time ) * color1.getGreen() );
        int b = ( int ) ( time * color2.getBlue()  + ( 1.0 - time ) * color1.getBlue() );

        StdOps.clamp( r, 0, 255 );
        StdOps.clamp( g, 0, 255 );
        StdOps.clamp( b , 0, 255 );
        return new Color( r, g, b );
    }

}
