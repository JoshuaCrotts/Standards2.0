package com.joshuacrotts.standards;

import java.awt.Graphics2D;
import java.util.ArrayList;

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
public class StandardHandler {

	protected ArrayList<StandardGameObject> entities;

	public StandardHandler(){
		this.entities = new ArrayList<StandardGameObject>();
	}
	
	/**
	 * This method will tick any StandardGameObject that is passed to it
	 * Very useful for abstraction, if needed.
	 */
	public static void Object(StandardGameObject obj){
		obj.tick();
	}
	
	/**
	 * This method will tick any StandardHandler that is passed to it
	 * Very useful for abstraction, if needed.
	 */
	
	public static void Handler(StandardHandler handler){
		/**
		 * We're forced to parse through the entire list 
		 * because entities is not static; we can't simply do
		 * handler.tick() in this case. It still runs at the
		 * same speed as tick() though.
		 */
		for(int i = 0; i<handler.entities.size(); i++){
			
			StandardGameObject o = handler.entities.get(i);
			
			if(o != null)
				o.tick();
			
		}
	}
	
	/**
	 * This method will take in parameters for an object, and add it to the
	 * specified handler n times
	 * 
	 * This method DOES RUN FASTER than a traditional for loop with adding the elements to 
	 * a handler (i.e., it will require less n to have them added to the loop.
	 * 
	 * @param obj is the object to be added.
	 * @param stdHandler is the handler that obj will be added to
	 * @param n is the amt of times obj will be added to stdHandler
	 */
	public static void Add(StandardGameObject obj, StandardHandler stdHandler, int n){
		for(int i = 0; i < n; i++)
			stdHandler.addEntity(obj);
	}

	/**
	 * This method will tick every single object in the entities array list.
	 * An entity is determined by an object or something in the game.
	 * Enemies, Walls, Players, Other stuff is an entity.
	 * Ticking the entity ***will update positions***, do stuff, etc.
	 * NOTHING GRAPHICALLY IS DONE IN THE tick() METHOD.
	 */
	public void tick(){
		for(int i = 0; i<entities.size(); i++){
			
			StandardGameObject o = this.entities.get(i);
			
			if(o != null)
				o.tick();
			
		}
	}

	/**
	 * This method will render everything graphically per object.
	 * All objects are entities that the render method is being called on.
	 * Rendering object will update it's positions GRAPHICALLY.
	 * @param g is the graphics object. Just used. Yknow.
	 * NO ACTUAL MOVEMENTS OR CALCULATIONS (setVelX(getVelX()+10)) ARE DONE
	 * IN THE render(Graphics g) method.
	 */
	public void render(Graphics2D g2){
		for(int i = 0; i<entities.size(); i++){
			
			StandardGameObject o = this.entities.get(i);
			
			if(o != null)
				o.render(g2);
		}
	}

	public void addEntity(StandardGameObject obj){
		entities.add(obj);
	}

	/**
	 * This will remove an entity based on the StandardGameObject
	 * passed.
	 * @param obj is a StandardGameObject, if found in ArrayList, it will
	 * be removed.
	 */
	public void removeEntity(StandardGameObject obj){
		this.entities.remove(obj);
	}

	/**
	 * Clears all entities on screen that aren't the player.
	 * 
	 * ***MAKE SURE YOUR PLAYER ENTITY IS OF ID StandardID.Player***
	 */
	public void clearEntities(){
		for(int i = 0; i<entities.size(); i++){

			if(entities.get(i).getId() == StandardID.Player){
				entities.remove(i);
				i--;
			}
		}
	}

	/**
	 * Clears all entities including the player.
	 * USE WITH CAUTION
	 */
	public void clearAllEntities(){
		this.entities.clear();
	}
	
	public void sort(){
		for(int i = 0; i<this.entities.size(); i++){
			if(this.entities.get(i).getId() == StandardID.Player){
				this.entities.add(0, this.entities.get(i));
				this.removeEntity(this.entities.get(i));
			}
			
			if(this.entities.get(i).getId() == StandardID.Enemy){
				this.entities.add(1, this.entities.get(i));
				this.removeEntity(this.entities.get(i));
			}
		}
	}
//	
//	/**
//	 * The reason we have to have the horizontalTest and verticalTest parameters
//	 * in the x-range and y-range methods respectively, is because when we say
//	 * it's true it will test one pixel further away, meaning that if I am
//	 * testing vertically and I'm one pixel away from this object, I want the
//	 * block to be out of the x-range as a potential vertical collision. This
//	 * allows us to check if the blocks are within range without mistaking
//	 * blocks too far away as close enough
//	 * 
//	 * @author Brandon Willis
//	 */
//
//	public boolean sameX_Range(StandardGameObject obj1, StandardGameObject obj2, boolean horizontalTest) {
//		double lowerBound = obj1.getX() + obj1.getVelX();
//		double upperBound = lowerBound + obj1.getWidth() + obj1.getVelX();
//
//		// Expand range by one pixel
//		if (horizontalTest) {
//			lowerBound--;
//			upperBound++;
//		}
//		if (lowerBound <= obj2.getX() + obj2.getVelX() && obj2.getX() + obj2.getVelX() <= upperBound) {
//			return true;
//		}
//		if (lowerBound <= (obj2.getX() + obj2.getVelX() + obj2.getWidth())
//				&& (obj2.getX() + obj2.getVelX() + obj2.getWidth()) <= upperBound) {
//			return true;
//		}
//		if (obj2.getX() + obj2.getVelX() <= lowerBound
//				&& obj2.getX() + obj2.getWidth() + obj2.getVelX() >= upperBound) {
//			return true;
//		}
//
//		return false;
//	}
//
//	public boolean sameY_Range(StandardGameObject obj1, StandardGameObject obj2, boolean verticalTest) {
//
//		double lowerBound = obj1.getY();
//		double upperBound = lowerBound + obj1.getHeight();
//
//		if (verticalTest) {
//			lowerBound--;
//			upperBound++;
//		}
//
//		// 3 scenarios where they would have the same y range
//		if (lowerBound <= obj2.getY() && obj2.getY() <= upperBound) {
//			return true;
//		}
//		if (lowerBound <= (obj2.getY() + obj2.getHeight()) && (obj2.getY() + obj2.getHeight()) <= upperBound) {
//			return true;
//		}
//		if (obj2.getY() <= lowerBound && obj2.getY() + obj2.getHeight() >= upperBound) {
//			return true;
//		}
//
//		return false;
//	}

	public int size(){
		return this.entities.size();
	}

	public StandardGameObject get(int i){
		return this.entities.get(i);
	}

	public ArrayList<StandardGameObject> getEntities(){
		return entities;
	}


}