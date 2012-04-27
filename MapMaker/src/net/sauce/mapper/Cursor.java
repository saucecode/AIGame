package net.sauce.mapper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Cursor {

	World world;
	int x = 16, y = 16;
	
	public Cursor(World w){
		world = w;
	}
	
	public void onTick(){
		if(world.keys[KeyEvent.VK_W]){
			if(y > 16){
				y -= 32;
			}
		}
		if(world.keys[KeyEvent.VK_S]){
			if(y < 600){
				y += 32;
			}
		}
		if(world.keys[KeyEvent.VK_A]){
			if(x > 16){
				x -= 32;
			}
		}
		if(world.keys[KeyEvent.VK_D]){
			if(x < 800){
				x += 32;
			}
		}
		if(world.keys[KeyEvent.VK_SPACE]){
			boolean canPlace = true;
			for(Block b: world.blocks){
				if(b.x == x && b.y == y){
					canPlace = false;
				}
			}
			if(canPlace){
				world.blocks.add(new Block(x,y));
				System.out.println("walls.add(new Block(this, new Vector("+x+","+y+")));");
			}
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.drawRect(x-16, y-16, 32, 32);
	}
}
