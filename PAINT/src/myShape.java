import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Point;


public abstract class myShape{
	
	protected String name;
	protected Color strokeColor;
	protected Color fillColor;
	protected float stroke;
	protected Point start,end;
	protected boolean filled;

	protected abstract void drawShape(Graphics2D g2d);
	protected abstract void select(Point p,Graphics2D g);
	protected abstract void resize(Point p);
	protected abstract void move(Point p);
	protected abstract void set(Point start,Point end);
	protected abstract int getArea();
	protected abstract boolean contains(Point p);
	protected abstract void fill(Color color);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Color getStrokeColor() {
		return strokeColor;
	}
	public float getStroke() {
		return stroke;
	}
	public Point getStart() {
		return start;
	}
	public Point getEnd() {
		return end;
	}
	
	public boolean isFilled() {
		return filled;
	}
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	
}
