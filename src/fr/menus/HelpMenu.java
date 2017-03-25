package fr.menus;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.RotateTransition;

public class HelpMenu extends Menu{
	
	
	public static int ID=2;

	public HelpMenu(){
		super();
		menuTitle= "HELP";
		try {
			background=new Image("sprites/help.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		items.add("No need for it");
	}
	
	@Override
	void execOption() {
		game.enterState(MainMenu.ID, null,new RotateTransition());
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
}
