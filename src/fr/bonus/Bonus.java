package fr.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.utils.Movable;

public abstract class Bonus extends Movable{

	private boolean destructed;
	protected Image img;
	
	public Bonus(float centerPointX, float centerPointY) {
		super(centerPointX, centerPointY, 20);
		this.speedY=(float) 0.1;
		destructed = false;
	}

	

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		moveY(delta);
		if(this.intersects(fr.world.World.getPlayer())){
			action();
			destructed = true;
		}
	}
	
	public abstract void action();



	public boolean isDestructed() {
		return destructed;
	}
	
}
