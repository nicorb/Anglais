package fr.entities.characters;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.bonus.Clue;
import fr.entities.Projectile;
import fr.main.Game;
import fr.utils.Movable;
import fr.world.World;

public class Enemy extends Movable {

	int hp;
	protected boolean destructed;
	protected long time,lastshot;
	private Image img;
	protected int points=50;
	
	public Enemy(float centerPointX, float centerPointY, float radius,Image img) {
		super(centerPointX, centerPointY, radius);
		time=1000;
		destructed = false;
		this.img=img;
		this.speedX=(float) -0.3;
		this.speedY=0;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(img, x-img.getWidth()/2+radius, y-img.getHeight()/2+radius);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
		if(System.currentTimeMillis()-lastshot>time){
			lastshot=System.currentTimeMillis();
			fr.world.World.add(new Projectile(x,y,5,0,(float) 0.2,false));
		}
		
		for (Projectile p : fr.world.World.getProjectiles()){
			if(this.intersects(p) && p.getAllied()){
				this.destruct();
				p.destruct();
				fr.world.World.upScore(points);
			}
			
		}
		
		if(x>Game.longueur+400 || x<-400 || y>Game.hauteur+400 || y<-400)
			destructed = true;
	}

	

	public void move(int delta){
		moveX(delta);
		moveY(delta);
	}

	public boolean isDestructed(){
		return destructed;
	}
	
	public void destruct(){
		this.destructed=true;
	}

}
