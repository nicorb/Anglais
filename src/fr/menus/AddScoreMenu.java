package fr.menus;

import java.awt.Font;
import java.sql.SQLException;
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
import fr.utils.Score;

public class AddScoreMenu extends Menu{

	public static int ID=100;
	private static String name;
	private static int score;


	public AddScoreMenu(int score) {
		this.score=score;
		name="";
		try {
			background=new Image("sprites/badAnswer.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
		fontPhrases=fr.utils.FontUtils.chargerFont("font/neuropol.ttf",Font.BOLD,20,false);
	}


	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		super.enter(arg0, arg1);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		
		g.drawImage(background, 0, 0);
		g.setFont(fontTitrePrincipal);
		g.drawString("Enter your name:",(Game.longueur-fontTitrePrincipal.getWidth("Enter your name:"))/2 , 70);
		g.setFont(fontPhrases);
		g.drawString("Name: "+name, 640-fontPhrases.getWidth(name)/2, 400);
		//g.drawString("Enter your name", x, y);
	}

	@Override
	void execOption() {
	}

	@Override
	public int getID() {
		return ID;
	}


	@Override
	public void keyPressed(int key, char c) {
		if((c>='a' && c<='z')||(c>='Z' && c<='Z')||(c=='_' || c=='-')) {
			name+=c;
		}
		if(key==Input.KEY_BACK && name.length()>0) {
			name=name.substring(0, name.length()-1);
		}
		if(key==Input.KEY_ENTER) {
			ArrayList<Score> scores = fr.database.SQLiteJDBC.getScore();
			if (scores.size()==5){
				if(score>scores.get(4).getScore()){
					scores.get(4).setName(name);
					scores.get(4).setScore(score);
					try {
						System.out.println("oui");
						fr.database.SQLiteJDBC.setScores(scores);
					} catch (ClassNotFoundException | SQLException e) {
						System.out.println("non");
						e.printStackTrace();
					}
				}
			}else{
				fr.database.SQLiteJDBC.addScore(score, name);
			}
			fr.menus.MainMenu.getGame().enterState(ScoreMenu.ID);
		}

	}

}
