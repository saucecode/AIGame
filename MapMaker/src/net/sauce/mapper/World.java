package net.sauce.mapper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class World {

	Game game;
	boolean[] keys = new boolean[256];
	List<Block> blocks = new ArrayList<Block>();
	Cursor c = new Cursor(this);
	
	public World(Game game) {
		this.game = game;
	}

	public void onTick() {
		c.onTick();
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 600);
		c.draw(g);
		for(Block b: blocks){
			b.draw(g);
		}
	}

	public void keyPressed(int keyCode) {
		keys[keyCode] = true;
	}

	public void keyReleased(int keyCode) {
		keys[keyCode] = false;
	}
}
