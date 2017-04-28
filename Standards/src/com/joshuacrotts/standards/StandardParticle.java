package com.joshuacrotts.standards;

import java.awt.Color;

import com.andrewmatzureff.constants.C;
/**
 * This is a StandardParticle class, it needs to be passed the 
 * StandardHandler it will be placed in DIRECTLY; you cannot 
 * reference the StandardGame and use it, for some reason. It
 * is probably an issue with the passing-by-reference problems
 * and memory allocation.
 * @author Joshua
 *
 */
public abstract class StandardParticle extends StandardGameObject{
    
    //public long death = 0;
    public Color color = Color.red;
    protected StandardHandler handler;
    
    private StandardParticle(double x, double y, StandardHandler handler){
        super((int) x, (int) y, StandardID.Particle);
        
        this.handler = handler;
        
        this.handler.addEntity(this);
    }
    
    public StandardParticle(double x, double y, double life, StandardHandler handler){
        this(x, y, handler);
        life *= C.NANO;
        this.death = System.nanoTime() + (long)life;
    }

    public StandardParticle(double x, double y, double life, StandardHandler handler, Color c){
        this(x, y, life, handler);
        this.color = c;
    }

    //@Override
    //public abstract void tick();

    //@Override
    //public abstract void render(Graphics2D g2);



}
