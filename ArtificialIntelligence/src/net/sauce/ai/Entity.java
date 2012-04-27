package net.sauce.ai;

import java.awt.Graphics2D;

public abstract class Entity {

	private Vector vector;
	private Vector vectorPrevious;
	private World world;
	private boolean isActive = true;
	private int health = 100;
	
	public Entity(World w, Vector v){
		setVector(v);
		setWorld(w);
	}

	public void doTick(){
		if(isActive())
			onTick();
	}
	
	public void doDraw(Graphics2D g){
		if(isActive())
			onDraw(g);
	}
	
	public abstract void onTick();
	public abstract void onDraw(Graphics2D g);
	
	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Vector getVectorPrevious() {
		return vectorPrevious;
	}

	public void setVectorPrevious(Vector vectorPrevious) {
		this.vectorPrevious = vectorPrevious;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void damage(int amount){
		this.health -= amount;
	}
	
	public void heal(int amount){
		this.health += amount;
	}
}
