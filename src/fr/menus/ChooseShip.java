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

public class ChooseShip extends BasicGameState{

	public static int ID=8;
	private Image img1,img2,img3,img4,selecter;
	private int selection;
	private StateBasedGame game;
	private TrueTypeFont fontTitrePrincipal;
	private String titre= "CHOOSE YOUR SHIP";
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		setFontTitrePrincipal("font/PressStart2P.ttf",Font.BOLD,40,false);
		selection=1;
		game=arg1;
		img1=new Image("sprites/Vaisseau1.png");
		img2=new Image("sprites/Vaisseau2.png");
		img3=new Image("sprites/Vaisseau3.png");
		img4=new Image("sprites/Vaisseau4.png");
		selecter=new Image("sprites/pointer.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(img1, Game.longueur/5-img1.getWidth()/2, Game.hauteur*3/5);
		arg2.drawImage(img2, Game.longueur*2/5-img2.getWidth()/2, Game.hauteur*3/5);
		arg2.drawImage(img3, Game.longueur*3/5-img3.getWidth()/2, Game.hauteur*3/5);
		arg2.drawImage(img4, Game.longueur*4/5-img4.getWidth()/2, Game.hauteur*3/5);
		arg2.drawImage(selecter, Game.longueur*selection/5-selecter.getWidth()/2, Game.hauteur*3/5+img1.getHeight()+10);
		arg2.setFont(fontTitrePrincipal);
		arg2.drawString(titre,(Game.longueur-fontTitrePrincipal.getWidth(titre))/2 , 120);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_RIGHT:
			if (selection < 4)
				selection++;
			else
				selection = 1;

			break;
		case Input.KEY_LEFT:
			if (selection > 1)
				selection--;
			else
				selection = 4;

			break;
		case Input.KEY_ENTER:
			try {
				execOption();
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;

		case Input.KEY_ESCAPE:
			System.exit(0);
			break;
		}
	}

	private void execOption() throws SlickException {
		game.addState(new World());
		fr.world.World.setPlayer(selection);
		game.enterState(World.ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	public void setFontTitrePrincipal(String name, int type, int size, boolean isSystemFont) {
		fontTitrePrincipal=fr.utils.FontUtils.chargerFont(name,type,size,isSystemFont);
	}

}
