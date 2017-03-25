package fr.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.utils.Movable;

public class Planet extends Movable{

	private Image img;

	public Planet(float centerPointX, float centerPointY,int numDecor,int numPlanet) {
		super(centerPointX, centerPointY, 0);
		try {
			img=new Image("sprites/planet"+numDecor+numPlanet+".png");
		} catch (SlickException e) {
			System.out.println("Planète"+numDecor+numPlanet+" n'a pas pu avoir son image chargée");
		}
		this.speedY=(float) 0.01;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(img, x, y);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
	}


}
