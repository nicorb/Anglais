package fr.menus;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.world.World;

public class GoodAnswer extends Menu{
	
	public static int ID=5;
	
	public GoodAnswer(){
		super();
		menuTitle= "Good Answer";
		
		items.add("You just earned 1000 pts");
		margeMoins=50;
		margePlus=100;
		try {
			background=new Image("sprites/goodAnswer.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		
	}

	@Override
	void execOption() {
		game.enterState(World.ID, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return ID;
	}
}
