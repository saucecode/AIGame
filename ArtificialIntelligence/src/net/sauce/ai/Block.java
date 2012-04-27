package net.sauce.ai;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;


public class Block extends Entity {

	private float hitBounds = 15f;
	
	public Block(World w, Vector v){
		super(w,v);
	}

	public void onTick() {
		
	}

	public void onDraw(Graphics2D g) {
		g.drawImage(getWorld().wall.getImage(), getVector().getIntX()-16, getVector().getIntY()-16, null);
	}
	
	public Vector getNorthWest(){
		return new Vector(getVector().getX()-16, getVector().getY()-16);
	}
	
	public Vector getSouthWest(){
		return new Vector(getVector().getX()-16, getVector().getY()+16);
	}
	
	public Vector getNorthEast(){
		return new Vector(getVector().getX()-16, getVector().getY()+16);
	}
	
	public Vector getSouthEast(){
		return new Vector(getVector().getX()+16, getVector().getY()+16);
	}
	
	public float getNorth(){
		return getVector().getY()-16f;
	}
	
	public float getSouth(){
		return getVector().getY()+16f;
	}
	
	public float getEast(){
		return getVector().getX()+16f;
	}
	
	public float getWest(){
		return getVector().getX()-16f;
	}
	
	public Line2D.Float getNorthLine(){
		return new Line2D.Float(getWest()-hitBounds, getNorth()-hitBounds, getEast()+hitBounds, getNorth()-hitBounds);
	}
	
	public Line2D.Float getSouthLine(){
		return new Line2D.Float(getWest()-hitBounds, getSouth()+hitBounds, getEast()+hitBounds, getSouth()+hitBounds);
	}
	
	public Line2D.Float getEastLine(){
		return new Line2D.Float(getEast()+hitBounds, getNorth()-hitBounds, getEast()+hitBounds, getSouth()+hitBounds);
	}
	
	public Line2D.Float getWestLine(){
		return new Line2D.Float(getWest()-hitBounds, getNorth()-hitBounds, getWest()-hitBounds, getSouth()+hitBounds);
	}
	
	public Line2D.Float getNorthWestLine(){
		return new Line2D.Float(getWest()-hitBounds, getNorth()-hitBounds, getEast()-hitBounds, getSouth()+hitBounds);
	}
	
	public Line2D.Float getSouthWestLine(){
		return new Line2D.Float(getWest()-hitBounds, getSouth()-hitBounds, getEast()-hitBounds, getNorth()+hitBounds);
	}
	
	public Line2D.Float getCenterVerticalLine(){
		return new Line2D.Float(getVector().getX(), getNorth(), getVector().getX(), getSouth());
	}
	
	public Line2D.Float getCenterHorizontalLine(){
		return new Line2D.Float(getWest(), getVector().getY(), getEast(), getVector().getY());
	}
	
	public List<Line2D.Float> getLines(){
		List<Line2D.Float> lines = new ArrayList<Line2D.Float>();
		lines.add(getCenterHorizontalLine());
		lines.add(getCenterVerticalLine());
//		lines.add(getEastLine());
//		lines.add(getNorthLine());
//		lines.add(getWestLine());
//		lines.add(getSouthLine());
//		lines.add(getNorthWestLine());
//		lines.add(getSouthWestLine());
		return lines;
	}
}
