package fr.entities.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DrunkEnemy extends Enemy{
	
	private float xinit;

	public DrunkEnemy(float centerPointX, float centerPointY, float radius, Image img,float speedX,float speedY,float xinit) {
		super(centerPointX, centerPointY, radius, img);
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		speedX=(speedX);
		speedY=(float) ((float) Math.cos((x-xinit)/20)+0.1)/2;
		super.update(container, game, delta);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawString("bounour", 1230, 10);
	}
}
