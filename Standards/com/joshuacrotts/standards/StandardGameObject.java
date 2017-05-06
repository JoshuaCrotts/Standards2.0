package com.joshuacrotts.standards;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is an abstract structure for an object or an entity in the game.
 * Use this for players, weapons, objects, whatever can be interacted with in
 * the game. If there is a non interactable character or object in the game,
 * that can be modified either in an class that extends this or even modifying
 * this class.
 * 
 * One more tip: ADD THE OBJECT TO THE StandardHandler WITHIN THE CONSTRUCTOR OF
 * THAT OBJECT. Example:
 * 
 * public class Player extends StandardGameObject{
 * 
 * public Player(......){ super(...); //call the parent constructor
 * 
 * StandardGame.handler.addEntity(this); //Adds 'this' instance of the
 * StandardGameObject to the handler. } }
 * 
 * @author Joshua
 *
 */
public abstract class StandardGameObject {

	public double x;
	public double y;

	public double velX;
	public double velY;

	public int width;
	public int height;

	public static double restitution = 0;
	public static double gravity = 0;

	private String fileLocation;

	public BufferedImage currentSprite;

	// Boolean that will determine whether or not to add a listener for the
	// object to the StandardGame.
	private boolean interactable = false;
	public boolean alive = true;
	public long death = 0;
	
	public boolean jumping = false;
	public boolean falling = false;

	public StandardID id;

	public StandardGameObject() {

	}

	public StandardGameObject(double x, double y, StandardID id) {
		this.x = x;
		this.y = y;

		this.id = id;
	}

	public StandardGameObject(double x, double y, StandardID id, boolean interactable) {
		this.x = x;
		this.y = y;

		this.id = id;
		this.interactable = interactable;
	}

	public StandardGameObject(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public StandardGameObject(double x, double y, int width, int height, StandardID id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	public StandardGameObject(double x, int y, int width, int height, StandardID id, boolean interactable) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.interactable = interactable;
	}

	public StandardGameObject(double x, double y, String fileLocation) {
		this.x = x;
		this.y = y;

		this.fileLocation = fileLocation;

		try {
			this.currentSprite = ImageIO.read(new File(this.fileLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = this.currentSprite.getWidth();
		this.height = this.currentSprite.getHeight();
	}

	public StandardGameObject(double x, double y, String fileLocation, boolean interactable) {
		this.x = x;
		this.y = y;

		this.fileLocation = fileLocation;

		try {
			this.currentSprite = ImageIO.read(new File(this.fileLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = this.currentSprite.getWidth();
		this.height = this.currentSprite.getHeight();

		this.interactable = interactable;
	}

	public StandardGameObject(double x, double y, String fileLocation, StandardID id) {
		this.x = x;
		this.y = y;

		this.fileLocation = fileLocation;

		try {
			this.currentSprite = ImageIO.read(new File(this.fileLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = this.currentSprite.getWidth();
		this.height = this.currentSprite.getHeight();
		this.id = id;
	}

	public StandardGameObject(double x, double y, String fileLocation, StandardID id, boolean interactable) {
		this.x = x;
		this.y = y;

		this.fileLocation = fileLocation;

		try {
			this.currentSprite = ImageIO.read(new File(this.fileLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = this.currentSprite.getWidth();
		this.height = this.currentSprite.getHeight();
		this.id = id;

		this.interactable = interactable;
	}

	public StandardGameObject(double x, double y, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.currentSprite = image;

		this.width = this.currentSprite.getWidth();
		this.height = this.currentSprite.getHeight();
	}

	public StandardGameObject(double x, double y, BufferedImage image, StandardID id) {
		this.x = x;
		this.y = y;
		this.currentSprite = image;

		this.width = this.currentSprite.getWidth();
		this.height = this.currentSprite.getHeight();
		this.id = id;
	}

	public StandardGameObject(double x, double y, BufferedImage image, StandardID id, boolean interactable) {
		this.x = x;
		this.y = y;
		this.currentSprite = image;

		this.width = this.currentSprite.getWidth();
		this.height = this.currentSprite.getHeight();
		this.id = id;

		this.interactable = interactable;
	}

	/**
	 * This method will be implemented in the class that extends GameObject.
	 * Usually the purpose of tick is for game logic, while reserving render(g2)
	 * for displaying graphics.
	 */
	public abstract void tick();

	/**
	 * This method will be implemented in the class that extends GameObject.
	 * Usually the purpose of render(g2) is for displaying graphics, while
	 * reserving tick() for game logic and movement.
	 */
	public abstract void render(Graphics2D g2);

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public BufferedImage getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(BufferedImage currentSprite) {
		this.currentSprite = currentSprite;
	}

	public StandardID getId() {
		return id;
	}

	public void setId(StandardID id) {
		this.id = id;
	}

	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}

	public boolean isInteractable() {
		return this.interactable;
	}

	public StandardAnimator getAnimation() {
		return null;
	}

	public void setAnimating(boolean bool) {

	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, this.width, this.height);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y, 1, this.height);
	}

	public Rectangle getRightBounds() {
		return new Rectangle((int) x + this.width, (int) y, 1, this.height);
	}

	public Rectangle getTopBounds() {
		return new Rectangle((int) x, (int) y, this.width, 3);
	}

	public Rectangle getBottomBounds() {
		return new Rectangle((int) x, (int) y + this.height, this.width, 1);
	}
	
//	public Rectangle getBounds() {
//		return (this.currentSprite != null) ? new Rectangle((int) x, (int) y, this.currentSprite.getWidth(), this.currentSprite.getHeight()) : new Rectangle((int) x, (int) y, this.width, this.height);
//	}
//
//	public Rectangle getLeftBounds() {
//		return (this.currentSprite != null) ? new Rectangle((int) x, (int) y, 1, this.currentSprite.getHeight()) : new Rectangle((int) x, (int) y, 1, this.height);
//	}
//
//	public Rectangle getRightBounds() {
//		return (this.currentSprite != null) ? new Rectangle((int) x + this.getWidth(), (int) y, 1, this.currentSprite.getHeight()) : new Rectangle((int) x + this.width, (int) y, 1, this.height);
//	}
//
//	public Rectangle getTopBounds() {
//		return (this.currentSprite != null) ? new Rectangle((int) x, (int) y, this.currentSprite.getWidth(), 3) : new Rectangle((int) x, (int) y, this.width, 3);
//	}
//
//	public Rectangle getBottomBounds() {
//		return (this.currentSprite != null) ? new Rectangle((int) x, (int) y + this.currentSprite.getHeight(), this.currentSprite.getWidth(), 1) : new Rectangle((int) x, (int) y + this.height, this.width, 1);
//	}

	public boolean alive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public long getDeath() {
		return death;
	}

	public void setDeath(long death) {
		this.death = death;
	}

	public double getRestitution() {
		return restitution;
	}
	public double getGravity() {
		return gravity;
	}

	public boolean isAlive() {
		return alive;
	}

}
