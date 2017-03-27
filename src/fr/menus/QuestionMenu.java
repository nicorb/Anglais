package fr.menus;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.entities.characters.Player;
import fr.world.World;

public class QuestionMenu extends Menu{

	//question,answer,7clue

	public static int ID=3;
	private String[] datas;
	private boolean questionSeen;
	private int question;
	private static ArrayList<Integer> clues;

	public QuestionMenu(){
		super();
		questionSeen=false;
		margeMoins = 300;
		margePlus=500;
		

		try {
			background=new Image("sprites/main_menu.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		this.game = game;
		container.setShowFPS(false);

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		question=fr.world.World.getQuestion();
		this.menuTitle="QUESTION TIME !!!";
		items=new ArrayList<String>();
		//datas=new String[11];
		datas = fr.database.SQLiteJDBC.search(question);
		items.add(datas[1]);
		for(int i=3;i<6;i++){
			if(clues.contains(i-2)){
				items.add(datas[i]);
			}
		}
		BadAnswer.setAnswer(datas[5+Integer.parseInt(datas[2])]);
		BadAnswer.setContext(datas[11]);
	}

	@Override
	void execOption() {
		World.getPlayer().reinitializeDep();
		if(!questionSeen){
			questionSeen=!questionSeen;
			for(int i=6;i<10;i++)
				items.add(datas[i]);
			while (items.size()>5){
				items.remove(1);
			}
		}else{
			if(selection==Integer.parseInt(datas[2])){
				World.upScore(1000);
				World.setNextScore(World.getScore()+8000);
				items=new ArrayList<String>();
				game.enterState(GoodAnswer.ID, new FadeOutTransition(), new FadeInTransition());
			}else {
				World.upScore(-500);
				World.setNextScore(World.getScore()+5000);
				questionSeen=!questionSeen;
				game.enterState(BadAnswer.ID, new FadeOutTransition(), new FadeInTransition());
				items=new ArrayList<String>();
			}

		}
	}

	@Override
	public int getID() {
		return ID;
	}

	public static void setClues(Player player){
		clues=player.getClues();
	}

}
