package com.joshuacrotts.standards;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * This class creates a StandardLevel for playing usage.
 * 
 * <br><br>
 * 
 * Level data can be as many squares wide as you want, but 
 * for a typical tileset, a set "tile size" should be used.
 * 
 * <br><br>
 * 
 * For example: A levelData.png file for a screen that is 1280x720, with tiles averaging 32x32, should be at the most
 * 22 (as 720/32 is 22.5, round down to 22 for safety) pixels high, yet can be as many wide as one wants the level
 * to be.
 * 
 * <br><br>
 * 
 * To clear all items and assets from the handler for a new level, do stdHandler.clearEntities();
 * 
 * <br><br>
 * 
 * StandardLevel is abstract because one needs to override the <code>loadLevelData()</code> method to 
 * instantiate pixel data as objects.
 * @author Joshua
 *
 */
public abstract class StandardLevel {
	
	private String fileLocation;//Path for the level data containing where to place the squares
	private BufferedImage levelData;//Actual level data with the squares, etc.
	private String bgImagePath;//Path for the background image
	private BufferedImage bgImage; //Actual image itself for background, if applicable
	public StandardHandler stdHandler; //Handler used for objects
	
	/**
	 * Creates a StandardLevel, needs to be extended 
	 * @param fileLocation - File Location of level data.
	 * @param bgImagePath - Background image, if applicable.
	 * @param stdHandler - Handler to store all of the objects in the level.
	 */
	public StandardLevel(String fileLocation, String bgImagePath, StandardHandler stdHandler){
		this.fileLocation = fileLocation;
		this.bgImagePath = bgImagePath;
		this.stdHandler = stdHandler;
		
		try{
			this.levelData = ImageIO.read(new File(this.fileLocation));
			this.bgImage = ImageIO.read(new File(this.bgImagePath));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method uses the rgb values for each pixel to interpret what brick to place.
	 * Copy this code: 
	 * <pre>
	 * <code>
	 * int w = this.levelData.getWidth();
	 * int h = this.levelData.getHeight();
	 * 
	 * 
	 * for(int x = 0; x < w; x++){
	 * 	for(int y = 0; y < h; y++){
	 * 		int pixel = this.levelData.getRGB(x,y);
	 * 		
	 * 		int r = ((pixel >> 16) & 0xff);
	 * 		int g = ((pixel >> 8) & 0xff);
	 * 		int b = ((pixel) & 0xff);
	 * 
	 * 		/*Below this point, load in the colors of the bricks you made in your levelData.png
	 * 		 *file. For example: if(r == 255 && g == 255 && b == 255){ this.stdHandler.add(new ...) }/
	 * 
	 * 	}
	 * }
	 * </code>
	 * </pre>
	 * 		 	
	 */
	public abstract void loadLevelData();
	
	/**
	 * Use these methods as needed.
	 */
	public abstract void tick();
	
	/**
	 * Use these methods as needed.
	 * Draw the image here in this method and call it somehow, or draw it straight from the game class.
	 * Preferably, one should override this method, and draw the background image within the subclass render method.
	 */
	public abstract void render(Graphics2D g2);

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public BufferedImage getLevelData() {
		return levelData;
	}

	public void setLevelData(BufferedImage levelData) {
		this.levelData = levelData;
	}

	public String getBgImagePath() {
		return bgImagePath;
	}

	public void setBgImagePath(String bgImagePath) {
		this.bgImagePath = bgImagePath;
	}

	public BufferedImage getBgImage() {
		return bgImage;
	}

	public void setBgImage(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}
}
