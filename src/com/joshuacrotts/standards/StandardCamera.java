package com.joshuacrotts.standards;

import java.awt.Graphics2D;

/**
 * Write a description of class StandardCamera here.
 *
 * @author (Andrew Matzureff)
 * @version (4/28/2017)
 */
public class StandardCamera extends StandardGameObject
{

    public double snap = 1;
    public boolean active = true;
    
    public StandardGameObject subject;


    public StandardCamera( StandardGameObject sgo, double snap )
    {
        super( ( int ) sgo.x, ( int ) sgo.y, StandardID.Camera );
        this.subject = sgo;
        this.snap = snap;
    }

    @Override
    public void tick()
    {
        this.x += ( this.velX = ( subject.velX - this.velX ) * snap );
        this.y += ( this.velY = ( subject.velY - this.velY ) * snap );
    }

    @Override
    public void render( Graphics2D g2 )
    {
        if ( active )
        {
            // StandardDraw.DELTAX = -this.x;
            // StandardDraw.DELTAY = -this.y;
            g2.translate(  - this.x,  - this.y );
        }
    }
}
