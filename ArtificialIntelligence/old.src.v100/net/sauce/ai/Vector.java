package net.sauce.ai;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Vector implements Serializable {

	private static final long serialVersionUID = -3959846179703062839L;
	private float x, y;
	
	public Vector(float x, float y){
		setX(x);
		setY(y);
	}
	
	public Vector(Vector v){
		setX(v.getX());
		setY(v.getY());
	}

	public Vector(double x, double y) {
		setX((float) x);
		setY((float) y);
	}

	public float getX() {
		return x;
	}
	
	public int getIntX() {
		return (int) Math.round(x);
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	
	public int getIntY() {
		return (int) Math.round(y);
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public String toString(){
		return "Vector2D("+getX()+","+getY()+")";
	}
	
	public Vector clone(){
		return new Vector(this);
	}
	
	public static float vectorDistance(Vector vec1, Vector vec2){
		float dist = 0;
		float width = vec2.getX() - vec1.getX();
		float height = vec2.getY() - vec1.getY();
		dist = (float) Math.sqrt(Math.pow(width,2)+Math.pow(height,2));
		return dist;
	}

	public static float calcAngle(Vector p1, Vector p2 ){
	    float f1 = (float)Math.toDegrees( Math.atan2( p1.getY()-p2.getY(), p1.getX()-p2.getX() ) );
	    f1+=270;
	    while ( f1 > 360 ) f1-=360;
	    while ( f1 <   0 ) f1+=360;
	    return f1;
	}
	
	public static boolean collisionLine(Vector v1, Vector v2){
		return false;
	}
	
	public static boolean linesIntersect(Line2D.Float l1, Line2D.Float l2){
		return Line2D.linesIntersect(l1.getX1(), l1.getY1(), l1.getX2(), l1.getY2(), l2.getX1(), l2.getY1(), l2.getX2(), l2.getY2());
	}
}
