package fr.entities.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EnemyGenerator {
	
	private float x,y,speedX,speedY;
	private long time,lastspawn;
	private Image img;
	private int type;
	
	public EnemyGenerator(int type,float x,float y,long time){
		this.x=x;
		this.y=y;
		this.time=time;
		lastspawn=0;
		
		this.type=type;
		
		try {
			img=new Image("sprites/En"+type+".png");
		} catch (SlickException e) {
			System.out.println("l'image En"+type+" ne s'est pas chargée corectement");
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(System.currentTimeMillis()-lastspawn>time){
			lastspawn=System.currentTimeMillis();
			generate();
			
			
		}
	}
	
	
	private void generate(){
		switch (type){
		case 1:
			fr.world.World.add(new Enemy(x, y, 10,img));
			break;
		case 2:
			speedX=(float) (x-(Math.random()*1000+200));
			speedY=(float) (y-(Math.random()*350+100));
			float a=speedX*speedX+speedY*speedY;
			speedX=(float) ((float) -0.3*(speedX/Math.sqrt(a)));
			speedY=(float) ((float) -0.2*(speedY/Math.sqrt(a)));
			fr.world.World.add(new StraightEnemy(x,y,10,img,speedX,speedY));
			break;
		case 3:
			speedX=(float) (x-(Math.random()*1000+200));
			speedY=(float) (y-(Math.random()*350+100));
			float b=speedX*speedX+speedY*speedY;
			speedX=(float) ((float) -0.3*(speedX/Math.sqrt(b)));
			speedY=(float) ((float) -0.2*(speedY/Math.sqrt(b)));
			fr.world.World.add(new DrunkEnemy(x,y,10,img,speedX,speedY,x));
		}
	}
}
