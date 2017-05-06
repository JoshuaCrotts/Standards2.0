package com.joshuacrotts.standards;

import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * Write a description of class ParticleHandler here.
 * 
 * @author (Andrew Matzureff) 
 * @version (4/27/2017)
 */
public class StandardParticleHandler extends StandardHandler
{
    static final int MAX_PARTICLES = 100000;
    public int dead = MAX_PARTICLES;//The partition at which life stops and death begins...
    public int oldest = 0;
    public int replace = MAX_PARTICLES;
    public StandardParticleHandler()
    {
        //These references are Typed as SGOs and not SPs so as to conform to StandardHandler's
        //restrictions. Subtypes of StandardParticles are Typed as such for convenience upon
        //construction and operation for the programmer.
        this.entities = new ArrayList<StandardGameObject>(MAX_PARTICLES + 1);
        for(int i = 0; i < MAX_PARTICLES; i++)
        {
            this.entities.add(null);
        }
    }
    
    public void tick(){
        //My source for the unsigned compare:
        //http://www.drmaciver.com/2008/08/unsigned-comparison-in-javascala/
        long death = (long)-1;
        //long time = System.nanoTime();
        
        if(dead < MAX_PARTICLES)
        for(int i = dead; i < MAX_PARTICLES; i++){
            
            StandardGameObject particle = this.entities.get(i);
            
            //if(particle != null)
            //{
                if(particle.alive())
                {
                    particle.tick();
                    if((particle.death < death) ^ (particle.death < 0) ^ (death < 0))
                    {
                        oldest = i;//I still need to think about this feature,
                        death = particle.death;//so I'm leaving it unimplemented for now...
                    }
                }
                else
                {
                    StandardGameObject swap = entities.get(dead);
                    entities.set(i, swap);
                    entities.set(dead++, null);
                }
            //}
        }
    }
    
    public void render(Graphics2D g2){
        for(int i = 0; i<entities.size(); i++){
            
            StandardGameObject particle = this.entities.get(i);
            
            if(particle != null && particle.alive())
                particle.render(g2);
        }
    }

    public void addEntity(StandardGameObject particle){
        switch(dead)
        {
            case 0:
				//Unfortunately, this does not ensure the oldest particle will be replaced
				//first, just the rightmost element (which is sometimes most likely to be
				//oldest in uniformly timed sets). A progressive (1 pass per tick) sort
				//could technically be implemented to achieve this over time but, for all
				//intents and purposes, this setup certainly works.
                if(replace == 0)
                    replace = MAX_PARTICLES;
                entities.set(--replace, particle);
                return;
            case MAX_PARTICLES:
                entities.set(--dead, particle);
                return;
            default:
                entities.set(--dead, particle);
        }
    }
    
    public void removeEntity(StandardGameObject obj){
        return;
    }
    
    public int size(){
        return MAX_PARTICLES - dead;
    }
}
