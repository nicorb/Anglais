package fr.utils;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;


public class Movable extends Circle{
	
	
	public Movable(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
	}

	//protected float x,y;
	protected float speedX,speedY;
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}
	
	public static void reset(){
	}
	
	protected void moveX(int delta){
		x=x+speedX*delta;
	}
	
	protected void moveY(int delta){
		y=y+speedY*delta;
	}
	
	//get,set,loose,gain for speedX,speedY
	
}
