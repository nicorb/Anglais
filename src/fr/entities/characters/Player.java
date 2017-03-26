package fr.entities.characters;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fr.entities.Projectile;
import fr.main.Game;
import fr.menus.MainMenu;
import fr.menus.QuestionMenu;
import fr.utils.Movable;


@SuppressWarnings("serial")
public class Player extends Movable{



	private float radius;//for the hitbox
	private float height,width;// position of the image 
	private ArrayList<Integer> numClue;
	private int life;
	private boolean downPress,upPress,rightPress,leftPress,hautbas,droitegauche;
	private long time,lastShot;
	private Image skin;

	public Player(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
		this.radius=radius;
		this.numClue=new ArrayList<Integer>();
		this.life=5;
		this.lastShot=0;
		time=400;
		try {
			this.skin=new Image("sprites/Vaisseau1.png");
		} catch (SlickException e) {
			System.out.println("l'image du player a rencontré un pb de chargement");
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.drawImage(skin, x-skin.getWidth()/2+radius, y-skin.getHeight()/2+radius);
		g.fillOval(x, y, 2*this.radius, 2*this.radius);
		g.drawString("clues= "+numClue, 20, 620);
		g.drawString("life= "+life, 20, 640);
		g.drawString("radius= "+radius, 20, 660);
		g.drawString("CenterX= "+(x+radius), 20, 680);
		g.drawString("CenterY= "+(y+radius), 20, 700);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		deplacement();
		moveX(delta);
		moveY(delta);
		for (Projectile p : fr.world.World.getProjectiles()){
			if(this.intersects(p) && !p.getAllied()){
				this.life-=1;
				p.destruct();
			}
		}
		if(life<=0){
			fr.menus.QuestionMenu.setClues(this);
			fr.menus.ScorePopUp.main(fr.world.World.getScore(), null);
		}
		if(System.currentTimeMillis()-lastShot>time)
			fr.world.World.add(new Projectile(x+radius,y+radius,2,0,-1,true));
		
	}


	public void keyReleased(int key, char c) {

		switch (key){

		case Input.KEY_UP:
			upPress=false;
			break;

		case Input.KEY_DOWN:
			downPress=false;
			break;

		case Input.KEY_LEFT:
			leftPress=false;
			break;

		case Input.KEY_RIGHT:
			rightPress=false;
			break;			
		}


	}

	public void keyPressed(int key, char c) {
		switch (key){

		case Input.KEY_UP:
			upPress=true;
			hautbas=false;
			break;

		case Input.KEY_DOWN:
			downPress=true;
			hautbas=true;
			break;

		case Input.KEY_LEFT:
			leftPress=true;
			droitegauche=false;
			break;
		case Input.KEY_RIGHT:
			rightPress=true;
			droitegauche=true;
			break;
		}

	}

	private void deplacement() {
		speedX = 0;
		speedY = 0;
		if((upPress && !downPress) || (upPress && downPress && !hautbas)) 
		{
			if(y>25){
				speedY=(float) -0.8;
			}

		}
		if((downPress && !upPress) || (upPress && downPress && hautbas)){
			if(y< Game.hauteur- 40){
				speedY=(float) 0.8;
			}
		}
		if((leftPress && !rightPress)|| (leftPress && rightPress && !droitegauche))
		{
			if(x>20){
				speedX = (float) -0.8;
				//image=imagegauche;
			}

		}
		if((!leftPress && rightPress)|| (leftPress && rightPress && droitegauche))
		{
			if(x< Game.longueur - 40)
			{

				//image=imagedroite;
				speedX = (float) 0.8;
			}
		}
		if(!rightPress && !leftPress)
		{
			//image=imagecentrale;
		}

	}

	public void addClue(int numClue){
		this.numClue.add(numClue);
	}

	public ArrayList<Integer> getClues(){
		return numClue;
	}
	
	public void reinitializeDep(){
		downPress=false;
		upPress=false;
		rightPress=false;
		leftPress=false;
		hautbas=false;
		droitegauche=false;
		numClue=new ArrayList<Integer>();
	}

}
