package fr.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;

@SuppressWarnings("serial")
public class Clue extends Bonus{

	private int number;
	
	public Clue(float centerPointX, float centerPointY,int number) {
		super(centerPointX, centerPointY);
		try {
			img=new Image("sprites/test.png");
		} catch (SlickException e) {
			System.out.println("the clue img can't be loaded");
		}
		this.number=number;
	}
	
	public void action(){
		fr.world.World.getPlayer().addClue(number);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(img, x+10, y+10);
	}
	
}
