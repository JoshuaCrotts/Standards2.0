package com.joshuacrotts.standards;

import java.awt.Color;

public class StandardFade {
	
	private float time = 0;
	private boolean firstColor = true;
	
	private Color color1;
	private Color color2;
	private double alpha;
	
	/**
	 * Finally! I got this method to work. Lines that implement the 
	 * r,g and b values come from Princeton University; I will author
	 * them in at the bottom. I calculated the time and variables to 
	 * switch the colors accordingly.
	 * 
	 * Method takes two parameters and fades them into one another according to a 
	 * timer/clock value: alpha.
	 * @param c1 First color to be used. 
	 * @param c2 Second color to be used. 
	 * @param alpha How fast colors should shift. 0 <= n <= 1. 
	 * Closer value is to zero, the longer it will take to shift.
	 * ***Important note about alpha: for non-seizure inducing colors, alpha <= .0005***
	 * 
	 *
	 * @return new Color based on r, g, and b values calculated.
	 * 
	 * @author (Only code utilized was lines 58-60):
	 * http://introcs.cs.princeton.edu/java/31datatype/Fade.java.html
	 */
	public StandardFade(Color c1, Color c2, double alpha){
		this.color1 = c1;
		this.color2 = c2;
		
		this.alpha = alpha;
	}
	
	public Color combine() {
		
		if(time <= 1f && firstColor){
			time += alpha;
		}

		else{
			firstColor = false;
		}
		if(time >= 0f && !firstColor)
			time -= alpha;
		else{
			firstColor = true;
		}
		
		short r = (short) (time * color2.getRed()   + (1 - time) * color1.getRed());
		short g = (short) (time * color2.getGreen() + (1 - time) * color1.getGreen());
		short b = (short) (time * color2.getBlue()  + (1 - time) * color1.getBlue());
		
		if(r > 255) r = 255;
		if(g > 255) g = 255;
		if(b > 255) b = 255;
		
		if(r < 0) r = 0;
		if(g < 0) g = 0;
		if(b < 0) b = 0;
		
		
		return new Color(r, g, b);
	}
	
}