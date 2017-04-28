package com.joshuacrotts.standards;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class StandardTrail extends StandardGameObject{

	private float alpha = 1;
	private float life; //0.01 - 0.1
	private Color color;
	private String shape;
	
	private boolean isImage = false;

	private StandardHandler stdHandler;
	
	public StandardTrail(int x, int y,  int width, int height, Color color,  float life, StandardGame stdGame, StandardHandler stdHandler){
		super(x, y, width, height);
		
		this.color = color;
		this.life = life;
		this.setId(StandardID.Trail);
		
		this.stdHandler = stdHandler;
		this.stdHandler.addEntity(this);
		
		if(this.getCurrentSprite() != null){
			this.isImage = true;
		}
		
		this.checkNullShape();
	}
	
	public StandardTrail(double x, double y,  int width, int height, Color color,  float life, StandardGame stdGame, StandardHandler stdHandler){
		super((int) x, (int) y, width, height);
		
		this.color = color;
		this.life = life;
		this.setId(StandardID.Trail);
		
		this.stdHandler = stdHandler;
		this.stdHandler.addEntity(this);
		
		if(this.getCurrentSprite() != null){
			this.isImage = true;
		}
		
		this.checkNullShape();
	}
	public StandardTrail(int x, int y,  int width, int height, Color color,  float life, StandardGame stdGame, StandardHandler stdHandler, String shape){
		super(x, y, width, height);
		
		this.color = color;
		this.life = life;
		this.shape = shape;
		this.setId(StandardID.Trail);
		
		this.stdHandler = stdHandler;
		this.stdHandler.addEntity(this);
		
		if(this.getCurrentSprite() != null){
			this.isImage = true;
		}
		
		this.checkNullShape();
	}
	
	public StandardTrail(double x, double y,  int width, int height, Color color,  float life, StandardGame stdGame, StandardHandler stdHandler, String shape){
		super((int) x, (int) y, width, height);
		
		this.color = color;
		this.life = life;
		this.shape = shape;
		this.setId(StandardID.Trail);
		
		this.stdHandler = stdHandler;
		this.stdHandler.addEntity(this);
		
		if(this.getCurrentSprite() != null){
			this.isImage = true;
		}
		
		this.checkNullShape();
	}

	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}
	public void tick() {
		if(alpha > life){
			alpha -= life - .001f;
		}
		else if(alpha > life){
			alpha += life - .001f;
		}
		else{
			this.stdHandler.removeEntity(this);
		}
	}

	public void render(Graphics2D g2) {
		
		g2.setComposite(makeTransparent(alpha));
		
		if(!isImage){
			g2.setColor(color);
			if(this.shape.equalsIgnoreCase("Circle"))
				g2.fillOval((int)this.getX(),(int)this.getY(), this.getWidth(), this.getHeight());
			else
				g2.fillRect((int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight());
		}
		else{
			g2.drawImage(this.getCurrentSprite(),(int) getX(), (int) getY(), null);
		}
		g2.setComposite(makeTransparent(1));
	}
	
	/**
	 * Checks to see if the provided shape is null.
	 * If it is, it throws an error and defaults the shape to a square trail.
	 */
	private void checkNullShape(){
		if(this.shape == null){
			System.err.println("Shape is NULL in a Trail");
			System.exit(1);
		}
	}
	
	public float getAlpha() {
		return alpha;
	}


	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public float getLife() {
		return life;
	}


	public void setLife(float life) {
		this.life = life;
	}

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	
}
