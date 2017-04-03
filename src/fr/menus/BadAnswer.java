package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.world.World;

public class BadAnswer extends Menu{

	private static String answer,context;
	public static int ID=4;
	
	public BadAnswer(){
		super();
		margeMoins=50;
		margePlus=100;
		
	}
	
	public static void setAnswer(String s){
		answer=s;
	}
	public static void setContext(String s){
		context=s;
	}
	
	
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		titre= "BAD ANSWER !!!";
		try {
			background=new Image("sprites/badAnswer.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		items.add("This is a bad answer...The good one was "+answer);
		items.add(context);
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
