package net.sauce.ai;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;

public class Bot extends Entity {

	Vector moveTo;
	Random random = new Random();
	private float speed = 2f, angle = 0f;
	float collisionSize = 28f;
	int counter = 300;
	boolean canShoot = true;
	int alarm = 20;
	
	public Bot(World w, Vector v) {
		super(w, v);
		moveTo = new Vector(random.nextInt(800), random.nextInt(600));
	}

	public void onTick() {
		setVectorPrevious(new Vector(getVector()));
		
		angle = Vector.calcAngle(getVector(), moveTo);
		getVector().setX((float) (getVector().getX() + speed * Math.sin(Math.toRadians(getAngle()))));
		getVector().setY((float) (getVector().getY() - speed * Math.cos(Math.toRadians(getAngle()))));
		
		checkCollision();
		
		if(moveTo.getIntX() - getVector().getIntX() < 4 && moveTo.getIntY() - getVector().getIntY() < 4){
			moveTo.setX(random.nextInt(800));
			moveTo.setY(random.nextInt(600));
		}
		
		counter--;
		if(counter<=0){
			counter = 300;
			moveTo = new Vector(random.nextInt(800), random.nextInt(600));
		}
		
		if(alarm>0)
			alarm--;
		if(alarm <= 0){
			alarm = 20;
			canShoot = true;
		}
		
		fire();
	}
	
	public void fire(){
		if(canShoot && canSee()){
			getWorld().bullets.add(new Bullet(getWorld(), new Vector(getVector()), this, new Vector(getWorld().player.getVector())));
			canShoot = false;
			alarm = 10;
		}
	}

	public void onDraw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(getVector().getIntX()-12, getVector().getIntY()-12, 24, 24);
		g.setColor(Color.red);
		g.drawRect(getVector().getIntX()-12, getVector().getIntY()-12, 24, 24);
		g.drawString("Can See Player: "+canSee(), 16, 38);
		g.setColor(Color.black);
		g.drawString(""+getHealth(), getVector().getIntX()-10, getVector().getIntY()+6);
	}

	public void checkCollision(){
		for(Block b: getWorld().walls){
			if(Math.abs(getVector().getX() - b.getVector().getX()) < collisionSize
			&& Math.abs(getVector().getY() - b.getVector().getY()) < collisionSize){
				getVector().setX(getVectorPrevious().getX());
				getVector().setY(getVectorPrevious().getY());
				
				if(Math.abs(getVector().getX() - moveTo.getX()) > Math.abs(getVector().getY() - moveTo.getY())){
					moveTo.setX(getVector().getX());
				}else if(Math.abs(getVector().getX() - moveTo.getX()) < Math.abs(getVector().getY() - moveTo.getY())){
					moveTo.setY(getVector().getY());
				}else{
					System.out.println("Something happened");
				}
			}
		}
	}
	
	public boolean canSee(){
		Vector player = getWorld().player.getVector().clone();
		Line2D.Float between = new Line2D.Float(player.getX(), player.getY(), getVector().getX(), getVector().getY());
		for(Block b: getWorld().walls){
			for(Line2D.Float fl: b.getLines()){
				if(Vector.linesIntersect(between, fl)){
					return false;
				}
			}
		}
		return true;
	}
	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
}
