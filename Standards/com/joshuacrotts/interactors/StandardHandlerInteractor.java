package com.joshuacrotts.interactors;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Essentially an extension to the StandardHandler, except it is only for
 * Interactor objects.
 * 
 * @author Joshua
 *
 */
public class StandardHandlerInteractor {

	private ArrayList<Interactor> interactors;
	
	public StandardHandlerInteractor(){
		this.interactors = new ArrayList<Interactor>();
	}
	
	public void tick(){
		for(int i = 0; i<this.interactors.size(); i++){
			this.interactors.get(i).tick();
		}
	}
	
	public void render(Graphics2D g2){
		for(int i = 0; i<this.interactors.size(); i++){
			this.interactors.get(i).render(g2);
		}
	}
	
	public void addInteractor(Interactor actor){
		this.interactors.add(actor);
	}
	
	public void removeInteractor(Interactor actor){
		this.interactors.remove(actor);
	}
	
	public int size(){
		return this.interactors.size();
	}
	
	public Interactor get(int i){
		return this.interactors.get(i);
	}
	
	
}
