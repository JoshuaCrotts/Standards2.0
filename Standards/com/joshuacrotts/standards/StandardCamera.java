package com.joshuacrotts.standards;

import java.awt.Graphics2D;
/**
 * Write a description of class Camera0 here.
 * 
 * @author (Andrew Matzureff) 
 * @version (5/4/2017)
 */
public class StandardCamera extends StandardGameObject
{
    public StandardGameObject subject;
    public double snap = 1;
    public int vpw, vph;
    public StandardCamera(StandardGameObject sgo, double snap, int vpw, int vph)
    {
        super(sgo.x, sgo.y, StandardID.Camera);
        this.vpw = vpw >> 1;
        this.vph = vph >> 1;
        this.subject = sgo;
        this.snap = snap;
    }
    public void tick()
    {
        //System.out.println("camera");
        this.velX = (this.subject.x - this.x) * this.snap;
        this.velY = (this.subject.y - this.y) * this.snap;
        this.x += this.velX;// * this.snap;
        this.y += this.velY;// * this.snap;
    }
    public void render(Graphics2D g2)
    {
        g2.translate(-this.x + vpw, -this.y + vph);
    }
}
