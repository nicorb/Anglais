package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.world.World;

public class BadAnswer extends Menu{

	private static String answer;
	public static int ID=4;
	
	public BadAnswer(){
		super();
		menuTitle= "BAD ANSWER !!!";
		
		
		margeMoins=50;
		margePlus=100;
		
	}
	
	public static void setAnswer(String s){
		answer=s;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		try {
			background=new Image("sprites/badAnswer.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		items.add("This is a bad answer...The good one was "+answer);
		items.add("You just lost 1000 pts");
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