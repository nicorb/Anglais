package fr.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.bonus.Bonus;
import fr.bonus.Clue;
import fr.entities.Projectile;
import fr.entities.characters.Enemy;
import fr.entities.characters.EnemyGenerator;
import fr.entities.characters.Player;
import fr.menus.MainMenu;
import fr.menus.QuestionMenu;



public class World extends BasicGameState{

	private static int question;
	private int tailleBDD=3;
	public enum direction {HAUT,DROITE,BAS,GAUCHE};
	private static Player player;
	public static int ID=0;
	private static ArrayList<Projectile> projectiles;
	private static ArrayList<Enemy> enemies;
	private static ArrayList<EnemyGenerator> enemyGen;
	private static GameContainer container;
	private static StateBasedGame game;
	//private static Decor decor;
	private static boolean pause;
	private static ArrayList<Clue> clues;
	private static Decor decor;
	private static int score;
	public static Music Mbackground;
	public static Music MMenu;
	private static int k;//k pour faire tomber les Clues et a pour saovir s'il faut afficher une question
	private static int nextScore;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		reset();
		this.game=arg1;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game){
		k=1;
		question=(int) (Math.floor(Math.random()*tailleBDD)+1);
		pause=false;
		enemyGen.add(new EnemyGenerator(3,1300,-20,2000));
		enemyGen.add(new EnemyGenerator(1,1200,500,1500));
		for (int i=0;i<3;i++){
			enemyGen.add(new EnemyGenerator(2,1300+i*60,0+i*90,3000));
			enemyGen.add(new EnemyGenerator(2,-50-i*100,0+i*90,3000));
		}
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game){
		pause=true;
	}

	public static void reset(){
		decor = new Decor(1);
		nextScore=7000;
		player = new Player(400, 400, 10);
		score= 0;
		projectiles=new ArrayList<Projectile>();
		clues=new ArrayList<Clue>();
		enemies=new ArrayList<Enemy>();
		enemyGen=new ArrayList<EnemyGenerator>();
		
		
		pause=false;
		k=1;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		decor.render(container, game, g);
		player.render(container, game, g);
		for (Bonus b:clues)
			b.render(container, game, g);
		for (Projectile p:projectiles)
			p.render(container, game, g);
		for (Enemy p:enemies)
			p.render(container, game, g);

		g.drawString("nbr de projectiles= "+projectiles.size(), 1000, 680);
		g.drawString("nbr d'ennemis= "+enemies.size(), 1000, 700);
		
		g.setColor(Color.yellow);
		g.drawString("scove: "+score, 10, 10);
		g.drawString("k: "+k, 10, 30);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(!pause){
			decor.update(container, game, delta);
			for(int i=0;i<enemies.size();i++){
				if(enemies.get(i).isDestructed()){
					enemies.remove(i);
				}
			}
			for(int i=0;i<projectiles.size();i++){
				if(projectiles.get(i).isDestructed()){
					projectiles.remove(i);
				}
			}
			for(int i=0;i<clues.size();i++){
				if(clues.get(i).isDestructed()){
					clues.remove(i);
				}
			}
			
			if(score>nextScore){
				fr.menus.QuestionMenu.setClues(player);
				game.enterState(QuestionMenu.ID, new FadeOutTransition(), new FadeInTransition());
			}
			
			player.update(container, game, delta);
			for (Projectile p:projectiles)
				p.update(container, game, delta);
			for (Bonus b:clues)
				b.update(container, game, delta);
			for (EnemyGenerator p:enemyGen)
				p.update(container, game, delta);
			for (Enemy p:enemies)
				p.update(container, game, delta);
			


			if(score > nextScore-6000 && k==1){
				clues.add(new Clue((float) (Math.random()*1000+100),-50,1));
				k+=1;
			}
			if(score > nextScore-4000 && k==2){
				clues.add(new Clue((float) (Math.random()*1000+100),-50,2));
				k+=1;
			}
			if(score > nextScore-2000 && k==3){
				clues.add(new Clue((float) (Math.random()*1000+100),-50,3));
				k+=1;
			}

			
		}
	}

	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}


	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
		if(key==Input.KEY_ESCAPE)
			System.exit(0);
		if(key==Input.KEY_SPACE)
			pause=!pause;

	}

	public static ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public static ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}

	@Override
	public int getID() {
		return ID;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int scoreP) {
		score = scoreP;
	}
	public static void add(Enemy enemy) {
		enemies.add(enemy);
	}
	public static void add(Projectile projectile) {
		projectiles.add(projectile);
	}
	public static void add(Clue bonus) {
		clues.add(bonus);
	}
	
	public static Player getPlayer() {
		return player;
	}

	public static void upScore(int i) {
		score+=i;
	}
	public static void setNextScore(int i){
		nextScore=i;
	}

	public static int getQuestion(){
		return question;
	}
	public static StateBasedGame getGame(){
		return game;
	}
}
