package com.joshuacrotts.standards;

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

	private double counter;
	private long delay = 0;
	private int frame = 0;
	private StandardGameObject object;

	//public static int attackCounter = 0;

	private boolean animating = true;

	public StandardAnimator(ArrayList<BufferedImage> images, long delay, StandardGameObject o){
		this.images = images;
		this.delay = delay;
		this.object = o;
	}

	/**
	 * Method animates the list of BufferedImages.
	 * 
	 * By default it will animate.
	 * If setAnimating(false), animation will terminate.
	 */
	public void animate(){
		
		if(!animating) return;
		
		if(this.animating){
			for(int i = 0; i<images.size(); i++){
				counter++;
				if(counter > delay){			
					object.setCurrentSprite(this.images.get(i));
					counter = 0;
				}
			}
		}
	}

	public ArrayList<BufferedImage> getImages() {
		return images;
	}

	public void setImages(ArrayList<BufferedImage> images) {
		this.images = images;
	}

	public double getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
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