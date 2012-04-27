package net.sauce.ai;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Entity {
	
	private float speed = 4f;
	private float angle = 0f;
	private float collisionSize = 16f;
	Vector target;
	private Entity owner;
	
	public Bullet(World w, Vector v, Entity owner, Vector target){
		super(w,v);
		this.target = target;
		setAngle(Vector.calcAngle(getVector(), target));
		this.owner = owner;
	}

	public void onTick() {
		getVector().setX((float) (getVector().getX() + speed * Math.sin(Math.toRadians(getAngle()))));
		getVector().setY((float) (getVector().getY() - speed * Math.cos(Math.toRadians(getAngle()))));
		checkCollision();
	}

	public void onDraw(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.drawRect(getVector().getIntX()-2, getVector().getIntY()-2, 4, 4);
	}
	
	public void checkCollision(){
		for(Block b: getWorld().walls){
			if(Math.abs(getVector().getX() - b.getVector().getX()) < collisionSize
			&& Math.abs(getVector().getY() - b.getVector().getY()) < collisionSize){
				this.setActive(false);
			}
		}
		if(Math.abs(getVector().getX() - owner.getWorld().player.getVector().getX()) < collisionSize
		&& Math.abs(getVector().getY() - owner.getWorld().player.getVector().getY()) < collisionSize
		&& owner instanceof Bot){
			this.setActive(false);
			owner.getWorld().player.damage(World.rand.nextInt(8));
		}
		if(Math.abs(getVector().getX() - owner.getWorld().bot.getVector().getX()) < collisionSize
		&& Math.abs(getVector().getY() - owner.getWorld().bot.getVector().getY()) < collisionSize
		&& owner instanceof Player){
			this.setActive(false);
			owner.getWorld().bot.damage(World.rand.nextInt(8));
		}
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Entity getOwner() {
		return owner;
	}

}
