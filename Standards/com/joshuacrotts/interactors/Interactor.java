package com.joshuacrotts.interactors;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Interactor {

	private short x;
	private short y;
	
	private short velX;
	private short velY;
	
	private short width;
	private short height;
	
	private boolean interactable = true;
	
	public Interactor(){
		
	}
	
	public Interactor(short x, short y){
		this.x = x;
		this.y = y;
	}
	
	public Interactor(short x, short y, boolean interactable){
		this.x = x;
		this.y = y;
		this.interactable = interactable;
	}
	
	public Interactor(short x, short y, short width, short height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Interactor(short x, short y, short width, short height, boolean interactable){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.interactable = interactable;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g2);

	public Rectangle getBounds(){
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public short getX() {
		return x;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}

	public short getVelX() {
		return velX;
	}

	public void setVelX(short velX) {
		this.velX = velX;
	}

	public short getVelY() {
		return velY;
	}

	public void setVelY(short velY) {
		this.velY = velY;
	}

	public short getWidth() {
		return width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public boolean isInteractable() {
		return interactable;
	}

	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}
	
	
}
