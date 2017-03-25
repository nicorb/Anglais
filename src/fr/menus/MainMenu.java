package fr.menus;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.world.World;

public class MainMenu extends Menu {

	public static int ID=1;

	public MainMenu(){
		super();
		menuTitle= "MAIN MENU";
		try {
			background=new Image("sprites/main_menu.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		items.add("Play");
		items.add("Help");
		items.add("Options");
		items.add("Credits");
		items.add("Edit Questions");
		items.add("Quit");
		margeMoins=50;
		margePlus=100;
	}






	@Override
	public int getID() {
		return ID;
	}






	@Override
	void execOption() {
		switch (selection){
		case 0:
			game.addState(new World());
			game.enterState(World.ID, new FadeOutTransition(),
					new FadeInTransition());
			World.reset();
			break;
		case 1:
			game.addState(new HelpMenu());
			game.enterState(HelpMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 4:
			fr.menus.AddingQuestion.main(null);
			break;
		case 5:
			System.exit(0);
			break;

		}
	}

}
