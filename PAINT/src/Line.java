import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends myShape{
	
	private Line2D l;
	Rectangle r;
	private int x1,y1,x2,y2;
	
	public Line(String name, Point start,Point end,Color strokeColor,Color fillColor,float stroke) {
		this.name = name;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.stroke = stroke;
		x1 = (int) start.getX();
		y1 = (int) start.getY();
		x2 = (int) end.getX();
		y2 = (int) end.getY();
		l = new Line2D.Double(x1, y2, x2, y2);
		r = new Rectangle("Rectangle",new Point(x1, y1), new Point(x2, y2), Color.BLACK, Color.BLACK, 2.0f);
		this.start = start;
		this.end = end;
		//start = new Point( (int) Math.min(start.getX(), end.getX()) ,(int) Math.min(start.getY(), end.getY()));
	}

	@Override
	public void drawShape(Graphics2D g2d) {
		g2d.setPaint(strokeColor);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.draw(l);
	}

	@Override
	public void select(Point P,Graphics2D g) { 
			r.select(P, g);
	}

	@Override
	public void resize(Point p) {
		if(r.selectRecArr[0].contains(p))
		{
			if(r.selectRecArr[0].contains(start))
			{
				start = p;
			}else
			{
				end = p;
			}
			set(start, end);
		}else if(r.selectRecArr[7].contains(p))
		{
			if(r.selectRecArr[7].contains(start))
			{
				start = p;
			}else
			{
				end = p;
			}
			set(start, end);
		}
		
	}

	@Override
	public void move(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void set(Point start, Point end) {
		x1 = (int) start.getX();
		y1 = (int) start.getY();
		x2 = (int) end.getX();
		y2 = (int) end.getY();
		l.setLine(x1, y1, x2, y2);
		r.set(start, end);
	}

	@Override
	protected int getArea() {
		// TODO Auto-generated method stub
		return r.getArea();
	}

	@Override
	protected boolean contains(Point p) {
		return r.contains(p);
	}

	@Override
	protected void fill(Color color) {
		// TODO Auto-generated method stub
		
	}

}
