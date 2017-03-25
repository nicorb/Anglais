package fr.entities.characters;

import org.newdawn.slick.Image;

public class StraightEnemy extends Enemy{

	public StraightEnemy(float centerPointX, float centerPointY, float radius, Image img,float speedX,float speedY) {
		super(centerPointX, centerPointY, radius, img);
		this.speedX=speedX;
		this.speedY=speedY;
		this.points=60;
		this.time=700;
	}

}
