package tests;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.andrewmatzureff.input.Movement;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardID;

public class Paddle extends StandardGameObject{

	private Movement left, right;
	
	private StandardGame stdGame;
	
	public Paddle(int x, int y, StandardGame stdGame){
		super(x, y, StandardID.Player);
		
		this.stdGame = stdGame;
		
		this.width = 100;
		this.height = 10;
		
		this.left = new Movement(this, -2, 0);
		this.right = new Movement(this, 2, 0);
		
		this.left.bind(this.stdGame.getKeyboard(), KeyEvent.VK_A);
		this.right.bind(this.stdGame.getKeyboard(), KeyEvent.VK_D);
		
	}
	
	public void tick(){
		this.x += this.velX;
		this.y += this.velY;
		
		if(this.x <= 0){
			this.x = 0;
			return;
		}
		
		if(this.x > 640 - this.width){
			this.x = 640 - this.width;
			return;
		}
		
		if(Math.abs(this.velX) > 0.01){
			this.velX *= 0.85;
		}else{
			this.velX = 0;
		}
		if(Math.abs(this.velY) > 0.01){
			this.velY *= 0.85;
		}else{
			this.velY = 0;
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(Color.blue);
		g2.fillRect((int) x, (int) y, width, height);
	}
	
}
