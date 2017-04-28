package com.joshuacrotts.standards;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.util.EventListener;

import com.andrewmatzureff.commands.Command;
import com.andrewmatzureff.input.Keyboard;
import com.andrewmatzureff.input.Mouse;

/**
 * This class is for creating a StandardGame. This incorporates the Runnable 
 * interface for instantiating the thread, the Window, and the Handler, basically
 * everything that a basic game requires. 
 * 
 * Listeners will need to be added into the constructor of this class. Add them in the constructor
 * directly, or add them in the method initIterators() or initObjects(). These methods may make the
 * game run slightly slower because it presumes every object to bode a listener of some sort, which
 * some may not.
 * 
 * Major update! All one must do now is extend this class, and create the tick and render methods in their
 * sub classes. Call StandardGame.Renderer, and it will draw whatever your heart contends.
 * 
 *@author Joshua
 */
public abstract class StandardGame extends Canvas implements Runnable{

	private static final long serialVersionUID = -7267473597604645224L;

	//Window itself
	public StandardWindow window = null;

	//Information on the thread and its state
	private Thread thread = null;
	private boolean running = false;
	
	//Debug information
	private int currentFPS;
	public boolean consoleFPS = true;
	public boolean titleFPS = true;
	
	//Graphics information
	public static BufferStrategy bufferStrategy = null;

	//InputDevices - may need to add list of devices should we decide to support FULL local multiplayer
	protected Mouse mouse;
	protected Keyboard keyboard;
	
	public StandardGame(int width, int height, String title){
		this.window = new StandardWindow((short) width,(short) height, title, this);
		
		this.createBufferStrategy(3);
		
		StandardGame.bufferStrategy = this.getBufferStrategy();
		
		this.mouse = new Mouse();
		this.keyboard = new Keyboard();
		
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addKeyListener(keyboard);

		Command.init();
		
		this.start();
	}
	/**
	 * This constructor is for making the frame 16:9 ratio.
	 */
	public StandardGame(int width, String title){
		
		this.window = new StandardWindow((short) width,(short) (width / 16 * 9), title, this);
		
		this.createBufferStrategy(3);
		
		StandardGame.bufferStrategy = this.getBufferStrategy();
		
		this.mouse = new Mouse();
		this.keyboard = new Keyboard();
		
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addKeyListener(keyboard);

		Command.init();
		
		this.start();
	}

	private synchronized void start(){
		if(running)
			return;
		else{
			this.thread = new Thread(this);
			this.thread.start();
			this.running = true;
		}
	}

	private synchronized void stop(){
		if(!this.running)
			return;
		else{
			try{
				this.thread.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			this.running = false;
			System.exit(0);
		}
	}
	/**
	 * This game loop was provided by online sources, though there are many examples
	 * of a game loop online.
	 * 
	 * @author RealTutsGML
	 */
	public void run() {
		requestFocus(); //Focuses the click/input on the frame/canvas.
		long lastTime = System.nanoTime(); //The current system's nanotime.
		double ns = 1000000000.0 / 60.0; //Retrieves how many nano-seconds are currently in one tick/update.
		double delta = 0; //How many unprocessed nanoseconds have gone by so far.
		long timer = System.currentTimeMillis();
		int frames = 0; //The frames per second.
		int updates = 0; //The updates per second.
		while (running) {
			
			boolean renderable = false; //Determines if the game should render the actual graphics.
			
			long now = System.nanoTime();//At this point, the current system's nanotime once again.
			delta += (now - lastTime) / ns;
			lastTime = now;
			/*If the amount of unprocessed ticks is or goes above one...
			*Also determines if the game should update or not/render. Approximately sixty frames per second.*/
			while (delta >= 1) {
				Command.tick(this);
				
				tick();
				
				delta--;
				updates++;
				
				renderable = true;
			}
			
			/*
			 * This clause replaces the original methods of tick and render.
			 * One can use tick and render exclusively.
			 */
			if(renderable){
				frames++;
				StandardGame.bufferStrategy = this.getBufferStrategy();//Retrieves the current bufferstrategy
				StandardDraw.Renderer = (Graphics2D) bufferStrategy.getDrawGraphics(); //Sets up the available graphics on the StandardDraw Graphics2D object
				
				//Sets the color to black, and buffers the screen to eradicate the black flickering dilemma.
				StandardDraw.Renderer.setColor(Color.BLACK);
				StandardDraw.Renderer.fillRect(0, 0, this.width(), this.height());
				
				//Calls the render method that the sub class will forcibly possess. (render() is abstract)
				render();
				
				StandardDraw.Renderer.dispose(); //Disposes of the Renderer Graphics object to free resources
				StandardGame.bufferStrategy.show(); //Shows the current BufferStrategy
			}
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(this.titleFPS)
					window.setTitle(window.getTitle() + " | " + updates + " ups, " + frames + " fps");

				if(this.consoleFPS)
					System.out.println(window.getTitle() + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		
		this.stop();
	}

	/**
	 * This method should tick everything that needs to be updated via positioning, 
	 * mouse input, etc.
	 */
	public abstract void tick();

	/**
	 * This method should draw everything to the screen appropriately. Call everything in the 
	 * forcibly called render method in your subclass.
	 */
	public abstract void render();

	public StandardGame getGame(){
		return this;
	}
	
	/**
	 * Adds a specified object that implements:
	 * KeyListener
	 * MouseListener
	 * MouseMotionListener
	 * MouseWheelListener
	 * 
	 * @param listener
	 */
	public void addListener(EventListener listener){
		
		if(listener instanceof KeyListener)
			this.addKeyListener((KeyListener) listener);
		
		if(listener instanceof MouseListener)
			this.addMouseListener((MouseListener) listener);
		
		if(listener instanceof MouseMotionListener)
			this.addMouseMotionListener((MouseMotionListener) listener);
		
		if(listener instanceof MouseWheelListener)
			this.addMouseWheelListener((MouseWheelListener) listener);
		
	}

	public int getFPS(){
		return this.currentFPS;
	}
	
	public int width(){
		return this.window.width();
	}

	public int height(){
		return this.window.height();
	}
	
	/**
	 * These methods will take in a @param print, and allow for printing of the
	 * frames per second to either the console, title, or both, or neither.
	 */
	public void framesToConsole(boolean print){
		this.consoleFPS = print;
	}

	public void framesToTitle(boolean print){
		this.titleFPS = print;
	}

	//Getter for the window on a StandardGame object.
	public StandardWindow getWindow(){
		return this.window;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
}
