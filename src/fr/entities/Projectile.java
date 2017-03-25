package fr.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.main.Game;
import fr.utils.Movable;

public class Projectile extends Movable{

	protected boolean destructed,allied;
	
	public Projectile(float centerPointX, float centerPointY, float radius,float speedX,float speedY,boolean allied) {
		super(centerPointX, centerPointY, radius);
		this.speedY=speedY;
		this.speedX=speedX;
		this.radius =radius;
		destructed = false;
		this.allied=allied;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillOval(x-radius,(float)y-radius,(float)2*radius, 2*radius);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveX(delta);
		moveY(delta);
		if(x>Game.longueur+100 || x<-100 || y>Game.hauteur+100 || y<-100)
			destructed = true;
	}
	
	public boolean isDestructed(){
		return destructed;
	}
	
	public void destruct(){
		this.destructed=true;
	}
	
	public boolean getAllied(){
		return allied;
	}

}
