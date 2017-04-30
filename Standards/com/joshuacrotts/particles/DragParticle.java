package com.joshuacrotts.particles;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joshuacrotts.standards.StandardHandler;
import com.joshuacrotts.standards.StandardParticle;
import com.joshuacrotts.standards.StdOps;
/**
 * This is the original pink particle code @Andrew made, 
 * however, it is now in its own separate class so we can define
 * different particle classes.
 * 
 * KEEP IN MIND ***DO NOT ADD THIS TO A HANDLER; STANDARDPARTICLE
 * ADDS IT FOR YOU***
 * @author Joshua
 *
 */
public class DragParticle extends StandardParticle{
    
    public DragParticle(double x, double y, double life){
        super(x,y, life);
        //life = StdOps.rand(0d, life);
        //life *= C.NANO;//value passed for life should probably be kept greater than 1/60 (since there's a chance almost an entire frame might pass before the particle is ticked, it might not stay alive long enough to move at any less than the framerate)
        
        //this.death = System.nanoTime() + (long)life;//NOTE: it would probably be useful (and better convention) to add a time member that records the construction time of the game
        
        double d = StdOps.rand(0, Math.PI * 2d);//direction of velocity in radians
        double h = StdOps.rand(0.05d, 2.5d);//hypotenuse (magnitude or speed component of velocity) length in pixels
        
        this.velX = h * Math.sin(d);//solve for horizontal leg of right triangle formed by velocity vector (sin(d) = opposite / h)
        this.velY = h * Math.cos(d);//solve for vertical leg of right triangle formed by velocity vector (cos(d) = adjacent / h)
        //and yes I'm pretty sure those are switched but it still puts me in polar coordinates... just with an orthogonal transformation of some sort applied; whatever
        
        //velX = StdOps.rand(-death, death) / death;//quasar particle stuff
        //velY = StdOps.rand(-death, death) / death / velX;//literally just random math bs
        //velX *= velX * velX * Math.sin(death) * 10;//pure waste from a bovine-behind
        this.color = new Color(255, 255 - (int)(death) & 127, 255 - (int)(death) & 255, 255);//StdOps.rand(0, 64));//alpha translucency is VERY expensive!
        //this.color = new Color(255, StdOps.rand(0, 130), StdOps.rand(0, 130));
    }
    
    public DragParticle(double x, double y, double life, StandardHandler handler){
        super(x,y, life, handler);
        //life = StdOps.rand(0d, life);
        //life *= C.NANO;//value passed for life should probably be kept greater than 1/60 (since there's a chance almost an entire frame might pass before the particle is ticked, it might not stay alive long enough to move at any less than the framerate)
        
        //this.death = System.nanoTime() + (long)life;//NOTE: it would probably be useful (and better convention) to add a time member that records the construction time of the game
        
        double d = StdOps.rand(0, Math.PI * 2d);//direction of velocity in radians
        double h = StdOps.rand(0.05d, 2.5d);//hypotenuse (magnitude or speed component of velocity) length in pixels
        
        this.velX = h * Math.sin(d);//solve for horizontal leg of right triangle formed by velocity vector (sin(d) = opposite / h)
        this.velY = h * Math.cos(d);//solve for vertical leg of right triangle formed by velocity vector (cos(d) = adjacent / h)
        //and yes I'm pretty sure those are switched but it still puts me in polar coordinates... just with an orthogonal transformation of some sort applied; whatever
        
        //velX = StdOps.rand(-death, death) / death;//quasar particle stuff
        //velY = StdOps.rand(-death, death) / death / velX;//literally just random math bs
        //velX *= velX * velX * Math.sin(death) * 10;//pure waste from a bovine-behind
        this.color = new Color(255, 255 - (int)(death) & 127, 255 - (int)(death) & 255, 255);//StdOps.rand(0, 64));//alpha translucency is VERY expensive!
        //this.color = new Color(255, StdOps.rand(0, 130), StdOps.rand(0, 130));
        //this.color = c;
    }
	
    public DragParticle(double x, double y, double life, StandardHandler handler, Color c){
        super(x,y, life, handler);
        //life = StdOps.rand(0d, life);
        //life *= C.NANO;//value passed for life should probably be kept greater than 1/60 (since there's a chance almost an entire frame might pass before the particle is ticked, it might not stay alive long enough to move at any less than the framerate)
        
        //this.death = System.nanoTime() + (long)life;//NOTE: it would probably be useful (and better convention) to add a time member that records the construction time of the game
        
        double d = StdOps.rand(0, Math.PI * 2d);//direction of velocity in radians
        double h = StdOps.rand(0.05d, 2.5d);//hypotenuse (magnitude or speed component of velocity) length in pixels
        
        this.velX = h * Math.sin(d);//solve for horizontal leg of right triangle formed by velocity vector (sin(d) = opposite / h)
        this.velY = h * Math.cos(d);//solve for vertical leg of right triangle formed by velocity vector (cos(d) = adjacent / h)
        //and yes I'm pretty sure those are switched but it still puts me in polar coordinates... just with an orthogonal transformation of some sort applied; whatever
        
        //velX = StdOps.rand(-death, death) / death;//quasar particle stuff
        //velY = StdOps.rand(-death, death) / death / velX;//literally just random math bs
        //velX *= velX * velX * Math.sin(death) * 10;//pure waste from a bovine-behind
        //this.color = new Color(255, 255 - (int)(death) & 127, 255 - (int)(death) & 255, 255);//StdOps.rand(0, 64));//alpha translucency is VERY expensive!
        //this.color = new Color(255, StdOps.rand(0, 130), StdOps.rand(0, 130));
        this.color = c;
    }

    @Override
    public void tick() {
        if(this.alive)
        {
            //width = (int)Math.abs(velX) + 1;//for the drawRect version
            //height = (int)Math.abs(velY) + 1;
            this.width = (int)(velX*velX*Math.signum(velX)) + 1;//drawLine looks much better IMO
            this.height = (int)(velY*velY*Math.signum(velY)) + 1;
            this.x += this.velX;
            this.y += this.velY += 0.05    ;//pseudo gravity effect
            this.alive = System.nanoTime() - death <= 0;//too old to continue living?
            return;
        }
        
    //    this.handler.removeEntity(this);//you might want to uncomment this stuff since your removal is different than the system I'm testing
    }

    @Override
    public void render(Graphics2D g2) {
        if(!this.alive)//they'll appear frozen in time if you forget to terminate the render() on a dead particle
            return;
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = color.getAlpha();
        g2.setColor(new Color(red,green,blue,alpha));
        
        //g2.fillRect((int) (this.x - width / 2),(int) (this.y - height / 2), (int)width, (int)height);
        g2.drawLine((int) (this.x),(int) (this.y), (int)(x + width), (int)(y + height));//much cooler
    }



}
