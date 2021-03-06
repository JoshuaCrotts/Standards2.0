package com.joshuacrotts.standards;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class will be similar to the Math class in terms of operations.
 * Used for arithmetic, but primarily as of now (3-20-17), it's good for:
 * 
 * - Random Numbers
 * - Determining Mouse Location (if the mouse coordinates are over a rectangle (area)
 * - Added the ability to load in specific fonts at a specific size. 
 *   Pass in the String and the size, and it will be returned.
 * 
 * 
 * @author Joshua
 *
 */
public abstract class StdOps {

	public static int rand(int min, int max){
		return ThreadLocalRandom.current().nextInt(min, max+1);
	}
	
	public static double rand(double min, double max){
		return ThreadLocalRandom.current().nextDouble(min, max+1);
	}
	
	public static long rand(long min, long max){
		return ThreadLocalRandom.current().nextLong(min, max+1);
	}

	public static short randShort(int min, int max){
		return (short) ThreadLocalRandom.current().nextInt(min, max+1);
	}

	public static boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if((mx > x) && (mx < x + width)){
			if((my > y) && (my < y + height)){
				return true;
			}return false;
		}return false;
	}

	public static Font initFont(String path, float size){
		Font f = null;

		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return f;
	}
}