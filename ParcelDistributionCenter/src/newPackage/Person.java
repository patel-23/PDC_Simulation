package newPackage;

import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.awt.*;

public class Person {

	private int x;
	private int y;
	private int xa;
	private int width;
	private int height;
	private BufferedImage img;

	public Person(int x, int y) {

		this.x = x;
		this.y = y;
		this.xa = -1;
		this.width = 40;
		this.height = 120;

		// Imports the image of the car
		try {
			img = ImageIO.read(new File("person.png"));
		} catch (IOException e) {
			System.err.println("Error whilst importing image");
		}
	}

	public int getX() {
		return this.x;
	}

	public void setX(int n) {
		this.x = n;
	}

	public int getXA() {
		return this.xa;
	}

	public void setXA(int n) {
		this.xa = n;
	}

	public int getY() {
		return this.y;
	}
	public int getW() {
		return this.width;
	}

	public int getH() {
		return this.height;
	}
	public BufferedImage getImg() {
		return this.img;
	}

	public void move() {

		setX(getX() + getXA());
		
		if (getX() == 1100) {
			setXA(0);
		}
	}

	public void paint(Graphics2D g) {

		g.drawImage(getImg(), getX(), getY(), getW(), getH(), null);
	}

	
}
