package tests;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.andrewmatzureff.input.Movement;
import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardFade;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardHandler;
import com.joshuacrotts.standards.StandardID;
import com.joshuacrotts.standards.StandardTrail;

public class Player extends StandardGameObject{

	private Movement left, right, up, down;
	
	private StandardGame g;
	private StandardHandler trailHandler;
	private StandardFade pFade;
	
	public Player(int x, int y, StandardGame g){
		super(x, y, StandardID.Player);
		
		this.g = g;
		
		this.pFade = new StandardFade(StandardDraw.CARROT_ORANGE, StandardDraw.ST_PATRICK, 0.005f);
		
		this.setWidth(70);
		this.setHeight(70);
		
		this.left = new Movement(this, -3, 0);
		this.right = new Movement(this, 3, 0);
		this.up = new Movement(this, 0, -3);
		this.down = new Movement(this, 0, 3);
	
		this.left.bind(g.getKeyboard(), KeyEvent.VK_A);
		this.right.bind(g.getKeyboard(), KeyEvent.VK_D);
		this.up.bind(g.getKeyboard(), KeyEvent.VK_W);
		this.down.bind(g.getKeyboard(), KeyEvent.VK_S);
		
		this.trailHandler = new StandardHandler();	
		
	}
	
	public void tick(){
		this.x += this.velX;
		this.y += this.velY;
		
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
		g2.setColor(this.pFade.combine());
		g2.fillRect((int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight());
		
		new StandardTrail(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.pFade.combine(), .1f, this.g, this.trailHandler, "Square");
	}
	
	public StandardHandler getTrail(){
		return this.trailHandler;
	}
}
