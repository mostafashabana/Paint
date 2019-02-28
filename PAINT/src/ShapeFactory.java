import java.awt.Color;
import java.awt.Point;

public class ShapeFactory {

	public myShape create(String shape,Point start,Point end,Color strokeColor,Color fillColor,float stroke) {
		if (shape.equals("line")) {
			return new Line(shape,start,end,strokeColor,fillColor,stroke);
		} else if (shape.equals("rectangle")) {
			return new Rectangle(shape,start,end,strokeColor,fillColor,stroke);
		} else if (shape.equals("circle")) {
			return new Circle(shape,start,end,strokeColor,fillColor,stroke);
		} else if (shape.equals("ellipse")) {
			return new Ellipse(shape,start,end,strokeColor,fillColor,stroke);
		} else if (shape.equals("square")) {
			return new Square(shape,start,end,strokeColor,fillColor,stroke);
		}
		return null;
	}

}
