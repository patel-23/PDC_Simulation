package newPackage;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;



public class ConveyorBelt {

	// Fields required to create conveyor belt objects
	private int x1;
	private int x2;
	private int y;
	private boolean move;
	private int xLine;
	// Position of square inside wheel
	private double inner1_X;
	private double inner1_Y;
	private double inner2_X;
	private double inner2_Y;
	// Speed of square inside wheel
	private double inner1_A = 0;
	private double inner2_A = Math.toRadians(180);
	private int blueTotal;
	private int greenTotal;
	private int yellowTotal;
	private int total;
	private BufferedImage img;
	public ConveyorBelt[] cb = new ConveyorBelt[4];
	public Parcels[] p = new Parcels[20];

	// Initializing fields through constructor
	public ConveyorBelt(int X1, int X2, int Y, boolean Move, ConveyorBelt[] c, Parcels[] p) {

		this.x1 = X1;
		this.x2 = X2;
		this.y = Y;
		this.move = Move;
		this.xLine = this.x1;
		this.cb = c;
		this.p = p;
		inner1_X = 0.0;
		inner1_Y = 0.0;
		inner2_X = 0.0;
		inner2_Y = 0.0;
		blueTotal = 0;
		greenTotal = 0;
		yellowTotal = 0;
		total = 0;
	}

	// Accessors and mutators
	public int getX1() {
		return this.x1;
	}

	public int getX2() {
		return this.x2;
	}

	public int getY() {
		return this.y;
	}

	public int getXL() {
		return this.xLine;
	}

	public void setXL(int XL) {
		this.xLine = XL;
	}

	public boolean getMove() {
		return this.move;
	}

	public void setMove(boolean m) {
		this.move = m;
	}

	public void setInner1_X(double x) {
		this.inner1_X = x;
	}

	public double getInner1_X() {
		return this.inner1_X;
	}

	public void setInner2_X(double x) {
		this.inner2_X = x;
	}

	public double getInner2_X() {
		return this.inner2_X;
	}

	public void setInner1_Y(double y) {
		this.inner1_Y = y;
	}

	public double getInner1_Y() {
		return this.inner1_Y;
	}

	public void setInner2_Y(double y) {
		this.inner2_Y = y;
	}

	public double getInner2_Y() {
		return this.inner2_Y;
	}

	public double getInner1_A() {
		return this.inner1_A;
	}

	public void setInner1_A(double a) {
		this.inner1_A = a;
	}

	public double getInner2_A() {
		return this.inner2_A;
	}

	public void setInner2_A(double a) {
		this.inner2_A = a;
	}

	public BufferedImage getImg() {
		return this.img;
	}

	public int getB() {
		return this.blueTotal;
	}

	public void increaseB() {
		this.blueTotal++;
	}

	public int getG() {
		return this.greenTotal;
	}

	public void increaseG() {
		this.greenTotal++;
	}

	public int getYel() {
		return this.yellowTotal;
	}

	public void increaseYel() {
		this.yellowTotal++;
	}

	public int getTotal() {
		return this.total;
	}

	public void increaseT() {
		this.total++;
	}

	public void paint(Graphics2D g) {

		// Draws conveyer cb
		g.setColor(Color.darkGray);
		g.fillRoundRect(this.getX1() - 10, this.getY(), this.getX2() - 35, 70, 30, 30);
		g.setColor(Color.gray);
		g.fillRoundRect(this.getX1() - 5, this.getY() + 45, this.getX2() - 45, 20, 20, 30);
		g.setColor(Color.black);

		for (int i = 0; i <= (this.getX2() - 60); i += 25) {

			// Creates black wheels
			g.setColor(Color.black);
			g.fillOval(this.getX1() + i, this.getY() + 45, 20, 20);
			g.setColor(Color.gray);

			// Creates 2 inner circles
			this.setInner1_X((this.getX1() + i + 8) + Math.cos(this.getInner1_A()) * 5);
			this.setInner1_Y((this.getY() + 53) + Math.sin(this.getInner1_A()) * 5);
			g.fillRect((int) this.getInner1_X(), (int) this.getInner1_Y(), 5, 5);
			this.setInner2_X((this.getX1() + i + 8) + Math.cos(this.getInner2_A()) * 5);
			this.setInner2_Y((this.getY() + 53) + Math.sin(this.getInner2_A()) * 5);
			g.fillRect((int) this.getInner2_X(), (int) this.getInner2_Y(), 5, 5);
		}

		// Moves 2 inner circles inside black wheel
		if (this.getMove()) {
			this.setInner1_A(this.getInner1_A() + 0.1);
			this.setInner2_A(this.getInner2_A() + 0.1);
		}

		// Creates and moves conveyer belt lines
		g.setColor(Color.black);

		for (int i = this.getXL() - 40; i <= this.getX2() - 80; i = i +20) {
			g.drawLine(i, this.getY(), i + 42, this.getY() + 44);
		}

		if (this.getMove() && this.getXL() + 456 != this.getX2()) {
			this.setXL(this.getXL() + 1);
		} else if (this.getXL() + 456 == this.getX2()) {
			this.setXL(this.getX1());
		}

		// Checks the color and position of parcel and turns on corresponding belt
		for (int i = 0; i < 20; i++) {

			if (this.p[i].getX() <= 1020 && this.p[i].getX() >= 500 && this.p[i].getC() == 1) {
				this.cb[1].setMove(true);
			} else if (this.p[i].getX() >= 1020 && this.p[i].getC() == 1) {
				this.cb[1].setMove(false);
			}

			if (this.p[i].getX() <= 1020 && this.p[i].getX() >= 500 && this.p[i].getC() == 2) {
				this.cb[2].setMove(true);
			} else if (this.p[i].getX() >= 1020 && this.p[i].getC() == 2) {
				this.cb[2].setMove(false);
			}

			if (this.p[i].getX() <= 1020 && this.p[i].getX() >= 500 && this.p[i].getC() == 3) {
				this.cb[3].setMove(true);
			} else if (this.p[i].getX() >= 1020 && this.p[i].getC() == 3) {
				this.cb[3].setMove(false);

			}

			if (this.p[19].getX() > 400) {
				this.cb[0].setMove(false);
			}

			// Creates graphics for the switch
			g.setColor(Color.orange);
			g.fillRect(265, 330, 50, 20);
			g.setColor(Color.darkGray);
			g.fillRect(270, 335, 40, 10);
			g.drawLine(265, 348, 240, 330);
			int[] xtri = { 265, 240, 265 };
			int[] ytri = { 348, 330, 330 };
			g.setColor(Color.black);
			g.fillPolygon(xtri, ytri, 3);
			if (this.cb[0].getMove()) {

				g.setColor(Color.green);
				g.fillRect(271, 336, 19, 8);

			} else {
				g.setColor(Color.red);
				g.fillRect(290, 336, 19, 8);
			}

		}

		// Creates black rectangle for batch info
		g.setColor(Color.black);
		g.fillRect(40, 450, 220, 120);
		g.setColor(Color.orange);
		g.fillRect(800,190, 260,25);
		g.fillRect(800,330, 260,25);
		int[] xtri = { 800, 785, 800 };
		int[] ytri = { 215, 190, 190 };
		int[] xtri1 = { 800, 785, 800 };
		int[] ytri1 = { 355, 330, 330 };
		g.fillPolygon(xtri, ytri, 3);
		g.fillPolygon(xtri1, ytri1, 3);
		g.setColor(Color.black);
		g.drawRect(800,190, 260, 25);
		g.drawRect(800,330, 260,25);
		g.drawPolygon(xtri, ytri, 3);
		g.drawPolygon(xtri1, ytri1, 3);
		g.setColor(Color.white);
		g.fillRect(50, 460, 100, 100);

		// Paints the image corresponding to the color of the package
		for (int i = 0; i < p.length; i++) {

			if (i == 19) {

				if (this.p[19].getX() >= 500 && this.p[19].getX() < 1020) {
					image(getImg(), g, 19);
					break;

				}
			} else {
				if (this.p[i].getX() >= 500 && this.p[i + 1].getX() + this.p[i + 1].getW() <= 555)
					image(getImg(), g, i);
			}
		}

		// Calculates total number of packages and total packages of each color
		for (int i = 0; i < 20; i++) {
			if (this.p[i].getX() == 500 && this.p[i].getC() == 1) {
				increaseB();
				increaseT();
			}

			if (this.p[i].getX() == 500 && this.p[i].getC() == 2) {
				increaseG();
				increaseT();
			}

			if (this.p[i].getX() == 500 && this.p[i].getC() == 3) {
				increaseYel();
				increaseT();
			}
		}
		
		

		// Paints batch info board
		g.setColor(Color.white);
		g.drawString("Batch Info", 175, 470);
		g.setColor(Color.blue);
		g.drawString("International: " + getB(), 160, 490);
		g.setColor(Color.green);
		g.drawString("Ground: " + getG(), 160, 510);
		g.setColor(Color.yellow);
		g.drawString("Unknown: " + getYel(), 160, 530);
		g.setColor(Color.white);
		g.drawString("Total: " + getTotal(), 160, 550);
		
		if (getB() < 5) {
		g.setColor(Color.black);
		g.drawString("Paris Flight: " + getB() + "/5", 807, 207);
		}
		
		if (getB() >= 5 && getB() < 10) {
		g.setColor(Color.black);
		g.drawString("Paris Flight:", 807, 207);
		g.setColor(Color.red);
		g.drawString("FULL", 875, 207);
		g.setColor(Color.black);
		g.drawString("Tokyo Flight: " + (getB()-5) + "/5", 907, 207);
		}
		
		if (getB() >= 10) {
			g.setColor(Color.black);
			g.drawString("Paris Flight:", 807, 207);
			g.setColor(Color.red);
			g.drawString("FULL", 875, 207);
			g.setColor(Color.black);
			g.drawString("Tokyo Flight:", 907, 207);
			g.setColor(Color.red);
			g.drawString("FULL", 982, 207);
		}
		
		if (getG() < 5) {
			g.setColor(Color.black);
			g.drawString("P.E.I.Truck: " + getG() + "/5", 807, 347);
			}
			
			if (getG() >= 5 && getG() < 10) {
			g.setColor(Color.black);
			g.drawString("P.E.I Truck: ", 807, 347);
			g.setColor(Color.red);
			g.drawString("FULL", 873, 347);
			g.setColor(Color.black);
			g.drawString("Québéc Truck: " + (getG()-5) + "/5", 903, 347);
			}
			if (getG() >= 10) {
				g.setColor(Color.black);
				g.drawString("P.E.I Truck: ", 807, 347);
				g.setColor(Color.red);
				g.drawString("FULL", 873, 347);
				g.setColor(Color.black);
				g.drawString("Québéc Truck: ", 903, 347);
				g.setColor(Color.red);
				g.drawString("FULL", 984, 347);
			}
		
	}

	// Obtains keyboard input
	public void keyPressed(KeyEvent e) {

		// Stops the parcel if the space bar is pressed, and vice versa

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.setMove(!this.getMove());
		}
	}

	// Obtains and draws the image corresponding to the package color
	public void image(BufferedImage pic, Graphics2D g, int n) {

		if (this.p[n].getC() == 1) {
			try {
				pic = ImageIO.read(new File("plane.png"));
			} catch (IOException e) {
				System.err.println("Error whilst loading image");
			}

		}
		if (this.p[n].getC() == 2) {
			try {
				pic = ImageIO.read(new File("truck.png"));
			} catch (IOException e) {
				System.err.println("Error whilst loading image");
			}
		}
		if (this.p[n].getC() == 3) {
			try {
				pic = ImageIO.read(new File("question.png"));
			} catch (IOException e) {
				System.err.println("Error whilst loading image");
			}
		}

		g.drawImage(pic, 50, 460, 100, 100, null);

	}
}
