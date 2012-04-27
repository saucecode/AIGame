package net.sauce.mapper;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block {

	int x, y;
	
	public Block(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.red);
		g.drawRect(x-16, y-16, 32, 32);
		g.drawLine(x-16, y-16, x+16, y+16);
	}
}
