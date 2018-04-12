package fr.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class AddQuestionMenu extends Menu{
	
	private static String file;
	public static int ID=150;
	@Override
	void execOption() {
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		super.enter(arg0, arg1);
		try {
			background=new Image("sprites/badAnswer.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {

		g.drawImage(background, 0, 0);

		g.setFont(fontPhrases);
		g.drawString(name, 640-fontPhrases.getWidth("Enter your name:"), 300);
		g.drawString(name, 640-fontPhrases.getWidth(name), 400);
		
	}

}
