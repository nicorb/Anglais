package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.world.World;

public class MainMenu extends Menu {

	public static int ID=1;
	
	
	public MainMenu(){
		super();
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		titre= "MAIN MENU";
		try {
			background=new Image("sprites/main_menu.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		items.add("Play");
		items.add("Scores");
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
			game.addState(new QuestionMenu());
			game.addState(new BadAnswer());
			game.addState(new GoodAnswer());
			game.addState(new ChooseShip());
			game.enterState(ChooseShip.ID, new FadeOutTransition(),
					new FadeInTransition());
			World.reset();
			break;
		case 1:
			game.enterState(ScoreMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 2:
			game.addState(new HelpMenu());
			game.enterState(HelpMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 5:
			try{
				fr.menus.AddingQuestion.main(null);;
			}catch(Exception e){
				fr.menus.AddingQuestion.main(null);
			}
			break;
		case 6:
			System.exit(0);
			break;

		}
	}
	public static StateBasedGame getGame(){
		return game;
	}

}
