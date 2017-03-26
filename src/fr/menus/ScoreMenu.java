package fr.menus;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.utils.Score;
import fr.world.World;

public class ScoreMenu extends Menu{

	public static int ID=7;
	private ArrayList<Score> scores;
	
	public ScoreMenu(StateBasedGame g){
		super();
		game=g;
		try {
			background=new Image("sprites/main_menu.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		menuTitle="SCORES";
		margeMoins=150;
	}

	
	

	@Override
	public void enter(GameContainer container, StateBasedGame game){
		scores=fr.database.SQLiteJDBC.getScore();
		for (int i=0;i<scores.size();i++){
			items.add((i+1)+":"+scores.get(i).getName()+" , "+scores.get(i).getScore()+"pts");
		}


	}

	@Override
	void execOption() {
		game.enterState(MainMenu.ID, new FadeOutTransition(),
				new FadeInTransition());
	}

	@Override
	public int getID() {
		return ID;
	}

}
