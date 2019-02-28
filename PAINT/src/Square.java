import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Square extends Rectangle{
	private int d;
	public Square(String name,Point start, Point end, Color strokeColor, Color fillColor, float stroke) {
		super(name,start, end, strokeColor, fillColor, stroke);
		d = (int) Math.max( Math.abs(start.getX()-end.getX() )  , Math.abs(start.getY()-end.getY()));
		width = 2*d;
		height = width;
		x = (int) (start.getX()-d);
		y = (int) (start.getY()-d);
		r = new Rectangle2D.Double(x, y, width, height);
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected void set(Point start, Point end) {
		d = (int) Math.max( Math.abs(start.getX()-end.getX() )  , Math.abs(start.getY()-end.getY()));
		width = 2*d;
		height = width;
		x = (int) (start.getX()-d);
		y = (int) (start.getY()-d);
		r.setRect(x, y, width, height);
	}
	
	@Override
	public void resize(Point p) {
		
		if(selectRecArr[1].contains(p) || selectRecArr[3].contains(p) || selectRecArr[4].contains(p) || selectRecArr[6].contains(p))
		{
			end = p;
			set(start, end);
		}
	}
	
	@Override
	public void move(Point p) {
		if (r.contains(p)) {
			
			this.start = p;
			this.end.x = (int) (p.getX() + width/2);
			this.end.y = (int) (p.getY() + height/2);
			set(start, end);
			
		}
	}

}
