package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
		JPanel base = new JPanel(new GridLayout(5, 5));
		frame.add(base);
		for(int i = 0; i < 15; i ++) {
			PlainTile newTile = new PlainTile();
			base.add(newTile);
		}
		for(int i = 0; i < 10; i ++) {
			MountainTile newTile = new MountainTile();
			base.add(newTile);
		}
		frame.setVisible(true);
		frame.pack();
	}

}
