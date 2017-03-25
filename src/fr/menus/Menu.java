package fr.menus;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.main.Game;


public abstract class Menu extends BasicGameState {

	protected int margeMoins,margePlus;
	public static int ID;
	protected ArrayList<String> items;//for if there is a choice to make

	protected String menuTitle;
	protected Image background;

	protected int selection;

	protected GameContainer container;
	protected StateBasedGame game;
	protected long time;


	public Menu(){
		items = new ArrayList<String>();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		this.game = game;
		container.setShowFPS(false);

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.drawString(menuTitle, Game.longueur/2-100, 50);
		for (int i=0;i<items.size();i++){
			g.drawString(items.get(i),Game.longueur/2-margeMoins, 250+50*i);
			
		}
		if(items.size()>1){
			g.drawString(">>>", Game.longueur/2-margeMoins-50, 250+50*selection);
			g.drawString("<<<", Game.longueur/2+margePlus, 250+50*selection);
		}
		g.drawString(""+selection, 1240, 700);
	}



	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}


	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_DOWN:
			if (selection < items.size() - 1)
				selection++;
			else
				selection = 0;

			break;
		case Input.KEY_UP:
			if (selection > 0)
				selection--;
			else
				selection = items.size() - 1;

			break;
		case Input.KEY_ENTER:
			execOption();
			break;

		case Input.KEY_ESCAPE:
			System.exit(0);
			break;
		}
	}

	abstract void execOption();

}
