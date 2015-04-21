package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame extends JPanel {

	public TestFrame() {
		// TODO Auto-generated constructor stub
	}

	public TestFrame(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public TestFrame(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public TestFrame(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File file = new File("square.png");
		BufferedImage img;
		try {
			img = ImageIO.read(file);
			g.drawImage(img, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Frame");
		MapOne map = new MapOne();
		frame.add(map);
		Unit barb = new Barbarian("this", new Player());
		barb.setPosition(2, 2, map);
		map.repaint();
		frame.setVisible(true);
		frame.pack();
		Scanner scan = new Scanner(System.in);
		String move = "";
		while(!move.equalsIgnoreCase("stop")) {
			System.out.print("Enter a direction to move: ");
			move = ((String) scan.next());
			if(move.equalsIgnoreCase("up")) {
				barb.move(Direction.UP, map);
				map.repaint();
			} else if (move.equalsIgnoreCase("down")) {
				barb.move(Direction.DOWN, map);
				map.repaint();
			} else if (move.equalsIgnoreCase("left")) {
				barb.move(Direction.LEFT, map);
				map.repaint();
			} else if (move.equalsIgnoreCase("right")) {
				barb.move(Direction.RIGHT, map);
				map.repaint();
			} else {
				System.out.println("enter new direction");
			}
		}
		
	}

}
