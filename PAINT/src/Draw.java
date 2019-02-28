import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Draw extends JComponent {

	private String currentShape;
	private String currentTool;
	private Color currentColor;
	private Color fillColor;
	ArrayList <myShape> shapesList;
    Stack<  ArrayList<myShape> > listForUndo;
    Stack<  ArrayList<myShape> > listForRedo;

	private Point startPoint;
	private Point endPoint;
	private Graphics2D g2d;

	private myShape shape, selectedShape;
	public myShape getSelectedShape() {
		return selectedShape;
	}

	private ShapeFactory factory;

	public Draw() {
		currentColor = Color.BLACK;
		currentShape = "line";
		this.currentTool = "";
		setBackground(Color.WHITE);
		shapesList = new ArrayList<>();
		factory = new ShapeFactory();
		listForUndo = new Stack<>();
		listForRedo = new Stack<>();
		ListenForMouse listener = new ListenForMouse(this);
		listener.listen();
	}

	@Override
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (myShape s : shapesList) {
			s.drawShape(g2d);
		}
		
		switch (currentTool) {
		case "select": {
			selectedShape = select(startPoint, g2d);
			if (selectedShape != null)
				selectedShape.select(startPoint, g2d);
			break;
		}
		case "resize": {
			if (selectedShape != null) {
				selectedShape.select(endPoint, g2d);
				selectedShape.resize(endPoint);
			}
			break;
		}
		case "move": {
			if(selectedShape != null)
			{
				selectedShape.select(endPoint, g2d);
				selectedShape.move(endPoint);
			}
			break;
		}
		
		case "fill": {
			if ((selectedShape = select(startPoint, g2d)) != null) {
				selectedShape.fill(fillColor);
				selectedShape.drawShape(g2d);
			}
			break;
		}
		default:
			break;
		}
	}

	private myShape select(Point p, Graphics2D g) {
		int area = 100000 * 100000;
		myShape selected = null;
		for (myShape s : shapesList) {
			if (s.contains(p) && s.getArea() < area) {
				selected = s;
				area = s.getArea();
			}
		}
		return selected;
	}
	
	public void takeSnap()
	{
		
		ArrayList<myShape> temp = new ArrayList<>();
		for(myShape t : shapesList)
		{
			myShape newShape = factory.create(t.name, t.start, t.end, t.strokeColor, t.fillColor, t.stroke);
			newShape.setFilled(t.filled);
			temp.add(newShape);
		}
		listForUndo.push(temp);
	}
	public void save()
	{
		
	}
	public void load()
	{
		
	}

	public ArrayList<myShape> getShapesList() {
		return shapesList;
	}
	
	public String getCurrentShape() {
		return currentShape;
	}

	public void setCurrentTool(String currentTool) {
		this.currentTool = currentTool;
	}

	public String getCurrentTool() {
		return currentTool;
	}

	public void setCurrentShape(String currentShape) {
		this.currentShape = currentShape;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	private class ListenForMouse {

		private Draw d;

		public ListenForMouse(Draw d) {
			this.d = d;
		}

		public void listen() {
			d.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					startPoint = new Point(e.getX(), e.getY());
					endPoint = new Point(e.getX(), e.getY());
					if (currentTool.equals("")) {
						shape = factory.create(currentShape, startPoint, endPoint, currentColor, currentColor, 2.0f);
						shapesList.add(shape);
					}
					if(currentTool.equals("fill"))
						takeSnap();
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					if(!currentTool.equals("select"))
					{
						d.takeSnap();
					}
				}

			});

			d.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					endPoint.setLocation(e.getX(), e.getY());
					if (currentTool == "")
						shape.set(startPoint, endPoint);
					repaint();
				}
			});
		}
	}
}
