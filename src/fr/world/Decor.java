package fr.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Decor {

	private Image background;
	private Planet planet1,planet2,planet3;
	private int num;
	
	public Decor(int num){
		try {
			background = new Image ("sprites/starfield"+num+".png");
			planet1=new Planet(1200,-1000,num,1);
			planet2=new Planet(50,-600,num,2);
			planet3=new Planet(900,-300,num,3);
		} catch (SlickException e) {
			System.out.println("impossible to load the background");
		}
		this.num=num;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		planet1.render(container, game, g);
		planet3.render(container, game, g);		
		planet2.render(container, game, g);		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		planet1.update(container, game, delta);
		planet2.update(container, game, delta);
		planet3.update(container, game, delta);
	}
	
}
