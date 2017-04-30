package tests;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardHandler;
import com.joshuacrotts.standards.StandardID;
import com.joshuacrotts.standards.StandardTrail;

public class Ball extends StandardGameObject{

	private StandardGame stdGame;
	
	private StandardHandler stdHandler;
	
	public Ball(int x, int y, StandardGame g){
		super(x, y, StandardID.Entity);
		
		this.stdGame = g;
		this.stdHandler = new StandardHandler();
		
		this.width = 15;
		this.height = 15;
		
		this.velX = 4;
		this.velY = 4;
	}
	
	public void tick(){

		
		if(this.x <= 0 || this.x >= stdGame.width() - this.width){
			this.velX = -this.velX;
		}
		
		if(this.y <= 0 || this.y >= stdGame.height() - this.height){
			this.velY = -this.velY;
		}
		
		this.x += velX;
		this.y += velY;
		
	}
	
	public void render(Graphics2D g2){
		g2.setColor(Color.red);
		g2.fillOval((int) x, (int) y, width, height);
		
		g2.setColor(Color.red);
		this.stdHandler.addEntity(new StandardTrail(this.getX(), this.getY(), this.getWidth(), this.getHeight(), Color.RED, 20f, this.stdGame, stdHandler, "Square"));
	
	}

	public StandardHandler getTrail() {
		return stdHandler;
	}
	
	
	
}
