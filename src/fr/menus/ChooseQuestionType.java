package fr.menus;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.main.Game;
import fr.world.World;

public class ChooseQuestionType extends Menu {

	public static int ID=-15;
	private static String type1 = "Music";
	private static String type2 = "General culture";
	private static String type3 = "All";
	private int selection;
	private Image selecter;
	private StateBasedGame game;
	private static String titre= "CHOOSE YOUR TOPIC ";
		
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		super.enter(container, game);
		selection=1;
		game=arg1;
		selecter=new Image("sprites/pointer.png");
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		this.setFontTitrePrincipal("font/gemina3dital.ttf",Font.BOLD,40,false);
		fontPhrases=fr.utils.FontUtils.chargerFont("font/neuropol.ttf",Font.BOLD,20,false);
		arg2.setFont(fontTitrePrincipal);
		arg2.drawString(titre,(Game.longueur-fontTitrePrincipal.getWidth(titre))/2 , Game.hauteur/5);
		arg2.setFont(fontPhrases);
		arg2.drawString(type1, (Game.longueur-fontPhrases.getWidth(type1))/2, Game.hauteur*2/5);
		arg2.drawString(type2, (Game.longueur-fontPhrases.getWidth(type2))/2, Game.hauteur*3/5);
		arg2.drawString(type3,(Game.longueur-fontPhrases.getWidth(type3))/2, Game.hauteur*4/5);
		arg2.drawImage(selecter, (Game.longueur-selecter.getWidth())/2, Game.hauteur*(selection+1)/5 + 50);
	}
		
	
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			if (selection <2)
				selection=3;
			else
				selection--;

			break;
		case Input.KEY_DOWN:
			if (selection > 2)
				selection=1;
			else
				selection++;

			break;
		case Input.KEY_ENTER:
			execOption();
			break;

		case Input.KEY_ESCAPE:
			System.exit(0);
			break;
		}
	}
	
	@Override
	void execOption() {
		game.addState(new World());
		try {
			if (selection == 1){
				fr.world.World.setQuestionType(type1);
			}else if (selection == 2) {
				fr.world.World.setQuestionType(type2);
			} else {
				fr.world.World.setQuestionType(type3);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		game.enterState(ChooseShip.ID, new FadeOutTransition(), new FadeInTransition());
		
	}

	@Override
	public int getID() {
		return ID;
	}
	

}
