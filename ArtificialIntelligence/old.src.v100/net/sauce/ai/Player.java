package net.sauce.ai;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Entity {

	float speed = 3.4f;
	float collisionSize = 28f;
	int alarm = 10;
	boolean canShoot = true;
	
	public Player(World w, Vector v) {
		super(w, v);
	}

	public void onTick() {
		updateMovement();
		updateShooting();
		if(alarm>0)
			alarm--;
		if(alarm <= 0){
			alarm = 10;
			canShoot = true;
		}
	}

	public void onDraw(Graphics2D g) {
		g.setColor(Color.green);
		g.drawRect(getVector().getIntX()-12, getVector().getIntY()-12, 24, 24);
		g.drawString(""+getHealth(), getVector().getIntX()-10, getVector().getIntY()+6);
	}

	public void updateShooting(){
		if(getWorld().keys[KeyEvent.VK_SPACE]){
			fire();
		}
	}
	
	public void updateMovement(){
		if(getWorld().keys[KeyEvent.VK_W]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setY(getVector().getY() - speed);
			checkCollision();
		}
		if(getWorld().keys[KeyEvent.VK_S]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setY(getVector().getY() + speed);
			checkCollision();
		}
		if(getWorld().keys[KeyEvent.VK_A]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setX(getVector().getX() - speed);
			checkCollision();
		}
		if(getWorld().keys[KeyEvent.VK_D]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setX(getVector().getX() + speed);
			checkCollision();
		}
	}
	
	public void checkCollision(){
		for(Block b: getWorld().walls){
			if(Math.abs(getVector().getX() - b.getVector().getX()) < collisionSize
			&& Math.abs(getVector().getY() - b.getVector().getY()) < collisionSize){
				getVector().setX(getVectorPrevious().getX());
				getVector().setY(getVectorPrevious().getY());
				System.out.println("true "+getVector()+" vs "+getVectorPrevious());
			}
		}
	}
	
	public void fire(){
		if(canShoot){
			getWorld().bullets.add(new Bullet(getWorld(), getVector().clone(), this, getWorld().game.mousePosition.clone()));
			canShoot = false;
			alarm = 10;
		}
	}
}
