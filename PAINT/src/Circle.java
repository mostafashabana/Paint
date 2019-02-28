import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Circle extends Ellipse {
	private int r;

	public Circle(String name,Point start, Point end, Color strokeColor, Color fillColor, float stroke) {
		super(name,start, end, strokeColor, fillColor, stroke);
		r = (int) Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
		width = 2 * r;
		height = 2 * r;
		x = (int) (start.getX() - r);
		y = (int) (start.getY() - r);
		e = new Ellipse2D.Double(x, y, width, height);
		this.start = start;
		this.end = end;
	}

	@Override
	protected void set(Point start, Point end) {
		r = (int) Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
		width = 2 * r;
		height = 2 * r;
		x = (int) (start.getX() - r);
		y = (int) (start.getY() - r);
		e.setFrame(x, y, width, height);
	}

	@Override
	protected void resize(Point p) {
		if (selectRecArr[1].contains(p) || selectRecArr[3].contains(p) || selectRecArr[4].contains(p)
				|| selectRecArr[6].contains(p)) {
			end = p;
			set(start, end);
		}
	}

	@Override
	protected void move(Point p) {
		if (e.contains(p)) {

			this.start = p;
			this.end.x = (int) (p.getX());
			this.end.y = (int) (p.getY() + height / 2);
			set(start, end);
		}
	}
}
