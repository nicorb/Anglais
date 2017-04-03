package fr.menus;


import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
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
	protected static StateBasedGame game;
	protected long time;
	protected String titre;
	protected TrueTypeFont fontTitrePrincipal,fontPhrases;


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
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		setFontTitrePrincipal("font/gemina3dital.ttf",Font.BOLD,40,false);
		fontPhrases=fr.utils.FontUtils.chargerFont("font/neuropol.ttf",Font.BOLD,20,false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		
		g.setFont(fontPhrases);
		for (int i=0;i<items.size();i++){
			g.drawString(items.get(i),(Game.longueur-1*fontPhrases.getWidth(items.get(i)))/2, 250+50*i);
			
			if((items.size()>1)&&(selection==i)){
				g.drawString(">>>", (Game.longueur-fontPhrases.getWidth(items.get(i)))/2-fontPhrases.getWidth(">>>"), 250+50*selection);
			}
		}
		
		g.setFont(fontTitrePrincipal);
		g.drawString(titre,(Game.longueur-fontTitrePrincipal.getWidth(titre))/2 , 70);
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
	
	public void setFontTitrePrincipal(String name, int type, int size, boolean isSystemFont) {
		fontTitrePrincipal=fr.utils.FontUtils.chargerFont(name,type,size,isSystemFont);
	}

}
