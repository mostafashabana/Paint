import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ellipse extends myShape{
	
	protected Ellipse2D e;
	protected int x,y,width,height;
	protected Rectangle2D selectRecArr[];
	final static float dash1[] = {10.0f};     
	final static BasicStroke dashed =new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f); 
	
	public Ellipse(String name,Point start,Point end,Color strokeColor,Color fillColor,float stroke) {
		
		this.name = name;
		this.start = start;
		this.end = end;
		selectRecArr = new Rectangle2D[8];
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.stroke = stroke;
		
		selectRecArr[0] = new Rectangle2D.Double(x-5, y-5, 10, 10);
		selectRecArr[1] = new Rectangle2D.Double(x+width/2-5, y-5, 10, 10);
		selectRecArr[2] = new Rectangle2D.Double(x-5+width, y-5, 10, 10);
		selectRecArr[3] = new Rectangle2D.Double(x-5, y-5+height/2, 10, 10);
		selectRecArr[4] = new Rectangle2D.Double(x-5+width, y-5+height/2, 10, 10);
		selectRecArr[5] = new Rectangle2D.Double(x-5, y-5+height, 10, 10);
		selectRecArr[6] = new Rectangle2D.Double(x+width/2-5, y-5+height, 10, 10);
		selectRecArr[7] = new Rectangle2D.Double(x-5+width, y-5+height, 10, 10);
		
		x = (int) Math.min(start.getX(), end.getX());
		y = (int) Math.min(start.getY(), end.getY());
		width = (int) Math.abs(start.getX()-end.getX());
		height = (int) Math.abs(start.getY()-end.getY());
		e = new Ellipse2D.Double(x, y, width, height);
	}

	@Override
	protected void drawShape(Graphics2D g2d) {
		g2d.setPaint(strokeColor);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.draw(e);
		
		if(filled)
		{
			g2d.setPaint(fillColor);
			g2d.fill(e);
		}
		
	}

	@Override
	protected void select(Point p,Graphics2D g) {
			selectRecArr[0].setFrame(x-5, y-5, 10, 10);
			selectRecArr[1].setFrame(x+width/2-5, y-5, 10, 10);
			selectRecArr[2].setFrame(x-5+width, y-5, 10, 10);
			selectRecArr[3].setFrame(x-5, y-5+height/2, 10, 10);
			selectRecArr[4].setFrame(x-5+width, y-5+height/2, 10, 10);
			selectRecArr[5].setFrame(x-5, y-5+height, 10, 10);
			selectRecArr[6].setFrame(x+width/2-5, y-5+height, 10, 10);
			selectRecArr[7].setFrame(x-5+width, y-5+height, 10, 10);
			g.setPaint(Color.BLACK);
			for(Rectangle2D rec:selectRecArr)
			{
				g.fill(rec);
			}
			g.setStroke(dashed);
			g.drawRect(x-3, y-3, width+5, height+5);
	}

	@Override
	protected void resize(Point p) {
		if(selectRecArr[0].contains(p))
		{
			start = p;
			set(start, end);
		}else if(selectRecArr[7].contains(p))
		{
			end = p;
			set(start,end);
		}
		
	}

	@Override
	protected void move(Point p) {
		this.start.setLocation(p.getX()-width/2, p.getY()-height/2);
		this.end.setLocation(p.getX()+width/2,p.getY()+height/2);
		set(start, end);
		
	}

	@Override
	protected void set(Point start, Point end) {
		x = (int) Math.min(start.getX(), end.getX());
		y = (int) Math.min(start.getY(), end.getY());
		width = (int) Math.abs(start.getX()-end.getX());
		height = (int) Math.abs(start.getY()-end.getY());
		e.setFrame(x, y, width, height);
	}

	@Override
	protected int getArea() {
		return width*height;
	}

	@Override
	protected boolean contains(Point p) {
		if(e.contains(p))
			return true;
		return false;
	}

	@Override
	protected void fill(Color color) {
		filled = true;
		fillColor = color;
	}


}
