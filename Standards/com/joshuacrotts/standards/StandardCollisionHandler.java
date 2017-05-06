package com.joshuacrotts.standards;

import java.awt.geom.Rectangle2D;
/**
 * StandardHandler will be a class for a Handler that will loop through
 * all available StandardGameObjects, tick, and render them. They *must* be added in
 * the StandardHandler for the to be tick-able and render-able. In a class that 
 * extends GameObject, use Graphics2D for the render(g2) method that is abstract
 * (i.e. must be coded in) in the class.
 * 
 * Other Handlers can be made of course, and one probably shouldn't extend this class
 * for a proprietary Handler for a class that, for example, will only parse through,
 * in example, StandardParticle objects.
 * 
 * @author Joshua
 *
 */
public class StandardCollisionHandler extends StandardHandler {
	public StandardCollisionHandler(StandardCamera c){
		super(); 
		this.stdCamera = c;
	}
	/**
	 * This method will tick every single object in the entities array list.
	 * An entity is determined by an object or something in the game.
	 * Enemies, Walls, Players, Other stuff is an entity.
	 * Ticking the entity ***will update positions***, do stuff, etc.
	 * NOTHING GRAPHICALLY IS DONE IN THE tick() METHOD.
	 */
	@Override
	public void tick(){
		//Fast Unit Vector Normalization Approximation Source: https://www.h3xed.com/programming/fast-unit-vector-calculation-for-2d-games
		//System.out.println("Ticking...");
		double[] norm = new double[2];
		for(int i = 0; i<entities.size(); i++){

			if(entities.get(i).getId() == StandardID.Player ||
					entities.get(i).getId() == StandardID.Enemy){

				//System.out.println("Collision?");
				StandardGameObject obj1 = entities.get(i);
				//obj1.getBounds().x = -(obj1.getBounds().width >> 1);
				//obj1.getBounds().y = -(obj1.getBounds().height >> 1);

				for(int j = 0; j < entities.size(); j++){

					StandardGameObject obj2 = entities.get(j);
					//obj2.getBounds().x = -(obj2.getBounds().width >> 1);
					//obj2.getBounds().y = -(obj2.getBounds().height >> 1);
					norm[0] = 0;
					norm[1] = 0;
					if(obj1 != obj2)
						intersection(obj1, obj2, norm);
					if(norm[0] + norm[1] != 0 &&//obj1.getBounds().intersects(obj2.getBounds()) &&
							/*(obj2.getX() > this.stdCamera.getX() - 300 && obj2.getX() < this.stdCamera.getX() + 300) &&*/ 
							(obj2.getId() == StandardID.Block || obj2.getId() == StandardID.Brick ||
							obj2.getId() == StandardID.Obstacle || obj2.getId() == StandardID.NPC ||
							obj2.getId() == StandardID.Powerup && obj2.getId() != StandardID.Camera)){
						// Get absolute value of each vector
						/*double ax = obj1.getVelX();
                        double ay = obj1.getVelY();
                        if(ax < 0)
                            ax = -ax;
                        if(ay < 0)
                            ay = -ay;

                        // Create a ratio
                        double ratio = 1d / (ax > ay? ax: ay);
                        ratio *= (1.29289 - (ax + ay) * ratio * 0.29289);
                        //ratio = 1;
                        // Multiply by ratio
                        double norm = (obj1.getVelX() * ratio * perpdot[1]) - (perpdot[0] * obj1.getVelY() * ratio);

						 */
						double res = 1.5;//obj2.restitution;//0.01;
						double dot = obj1.velX * norm[0] + obj1.velY * norm[1];
						obj1.setVelX((obj1.getVelX() - norm[0] * dot * res));
						obj1.setVelY((obj1.getVelY() - norm[1] * dot * res));
					}
				}
				//obj1.tick();
				//obj1.setVelY(obj1.getVelY() + 0.5);
			}

			entities.get(i).tick();

		}
	}
	public static void intersection(StandardGameObject r1, StandardGameObject r2, double[] norm)
	{
		double x1 = r1.getX() - Math.signum(r1.getVelX());
		double y1 = r1.getY() - Math.signum(r1.getVelY());
		double x2 = r2.getX();
		double y2 = r2.getY();
		double w1 = r1.currentSprite != null ? r1.currentSprite.getWidth(): r1.getBounds().width;
		double h1 = r1.currentSprite != null ? r1.currentSprite.getHeight(): r1.getBounds().height;
		double w2 = r2.currentSprite != null ? r2.currentSprite.getWidth(): r2.getBounds().width;
		double h2 = r2.currentSprite != null ? r2.currentSprite.getHeight(): r2.getBounds().height;

		Rectangle2D.Double b1 = new Rectangle2D.Double(x1, y1, w1, h1);
		Rectangle2D.Double b2 = new Rectangle2D.Double(x2, y2, w2, h2);
		Rectangle2D.Double bx = new Rectangle2D.Double(x1, y1, w1, h1);
		//Rectangle2D bt = b1.createIntersection(b2);


		//System.out.println("Intersection Area: " + bt.getWidth() + " X " + bt.getHeight());
		if(!b1.intersects(b2))
		{
			norm[0] = 0;
			norm[1] = 0;
			return;
		}
		bx.x -= r1.velX;
		b1.y -= r1.velY;
		if(!bx.intersects(b2))
		{
			norm[0] = bx.x < b2.x ? -1: 1;//<=?
			norm[1] = 0;
			if(!b1.intersects(b2))
			{
				norm[1] = b1.y < b2.y ? -1: 1;
			}
			return;
		}else
			if(!b1.intersects(b2))
			{
				norm[0] = 0;
				norm[1] = b1.y < b2.y ? -1: 1;
				return;
			}
	}
}