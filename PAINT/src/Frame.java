import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	private JButton lineBut, rectBut, circleBut, squareBut, triangleBut, ellipseBut;
	private JButton undo, redo, save, load, strokeBut, fillBut, selectBut, resizeBut, moveBut, deleteBut;

	private Draw draw;
	private Color color;

	public Frame() {
		// TODO Auto-generated constructor stub
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Paint Application");
		Image im = Toolkit.getDefaultToolkit().getImage("images/paint-icon.png");
		this.setIconImage(im);

		// Initialize draw object

		draw = new Draw();

		// set shapes buttons
		lineBut = setButton("line", "images/Line.png");
		rectBut = setButton("rectangle", "images/rectangle.png");
		circleBut = setButton("circle", "images/circle.png");
		squareBut = setButton("square", "images/square.png");
		triangleBut = setButton("triangle", "images/triangle.png");
		ellipseBut = setButton("ellipse", "images/ellipse.png");

		undo = setButton2("undo", "images/undo.png");
		redo = setButton2("redo", "images/redo.png");
		save = setButton2("save", "images/save.png");
		load = setButton2("load", "images/load.png");
		selectBut = setButton2("select", "images/select.png");
		resizeBut = setButton2("resize", "images/resize.png");
		moveBut = setButton2("move", "images/move.png");
		deleteBut = setButton2("delete", "images/move.png");

		strokeBut = setButton3("stroke", "images/stroke.png");
		fillBut = setButton3("FillColor", "images/fill.png");

		// shapes buttons panel
		Box shapesBox = Box.createHorizontalBox();
		JPanel shapePanel = new JPanel();
		shapesBox.add(lineBut);
		shapesBox.add(rectBut);
		shapesBox.add(circleBut);
		shapesBox.add(squareBut);
		shapesBox.add(triangleBut);
		shapesBox.add(ellipseBut);
		shapePanel.add(shapesBox);
		this.add(shapePanel, BorderLayout.NORTH);

		// tools buttons panel
		Box toolsBox = Box.createHorizontalBox();
		JPanel toolPanel = new JPanel();
		toolsBox.add(undo);
		toolsBox.add(redo);
		toolsBox.add(save);
		toolsBox.add(load);
		toolPanel.add(toolsBox);
		this.add(toolPanel, BorderLayout.SOUTH);

		JPanel toolPanel2 = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		toolPanel2.setLayout(layout);
		toolPanel2.add(strokeBut);
		toolPanel2.add(fillBut);
		toolPanel2.add(selectBut);
		toolPanel2.add(resizeBut);
		toolPanel2.add(moveBut);
		toolPanel2.add(deleteBut);
		toolPanel2.setLocation(20, 200);
		toolPanel2.setSize(120, 330);
		this.add(toolPanel2);

		this.add(draw, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private JButton setButton(String name, String icon) {
		JButton b = new JButton(name);
		b.setIcon(new ImageIcon(icon));
		b.setFocusPainted(false);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				draw.setCurrentShape(name);
				draw.setCurrentTool("");
			}
		});
		return b;
	}

	private JButton setButton2(String name, String icon) {
		JButton b = new JButton(name);
		b.setIcon(new ImageIcon(icon));
		b.setFocusPainted(false);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				draw.setCurrentTool(name);
				if (name.equals("delete") && draw.getSelectedShape() != null) {
					draw.getShapesList().remove(draw.getSelectedShape());
					draw.takeSnap();
					draw.repaint();
				} else if (name.equals("undo")) {
					if (!draw.listForUndo.isEmpty()) {
						draw.listForRedo.push(draw.listForUndo.pop());
						if (!draw.listForUndo.isEmpty())
							draw.shapesList = draw.listForUndo.peek();
					} else {
					    draw.shapesList.clear();
						System.out.println("Empty");
					}
					draw.repaint();
				} else if (name.equals("redo")) {
					if(!draw.listForRedo.isEmpty())
					{
						draw.shapesList = draw.listForRedo.peek();
						draw.listForUndo.push(draw.listForRedo.pop());
						draw.repaint();
					}
				}
			}
		});
		return b;
	}

	private JButton setButton3(String name, String icon) {
		JButton b = new JButton(name);
		b.setIcon(new ImageIcon(icon));
		b.setFocusPainted(false);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Let's choose a color", Color.BLACK);
				if (name.equals("stroke"))
					draw.setCurrentColor(color);
				else {
					draw.setFillColor(color);
					draw.setCurrentTool("fill");
				}
			}
		});
		return b;
	}

	public static void main(String[] args) {
		new Frame();
	}

}
