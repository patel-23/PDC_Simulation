package newPackage;

import java.awt.*;
import java.awt.event.*;

public class Parcels {

	// Fields required to create parcel object
	private boolean move;
	public int x;
	private int y;
	public int width;
	private int height;
	public int color;
	private double depth;
	private int[] xLeft = new int[4];
	private int[] xRight = new int[4];
	private int[] yLeft = new int[4];
	private int[] yRight = new int[4];

	// Initializing fields through constructor
	public Parcels(boolean Move, int x, int y, int W, int H, int C, double D) {

		this.move = Move;
		this.width = W;
		this.height = H;
		this.color = C;
		this.depth = D;
		this.x = x;
		this.y = 290 - this.height;

	}

	// Accessors and mutators
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return this.width;
	}

	public int getH() {
		return this.height;
	}

	public double getD() {
		return this.depth;
	}

	public boolean getMove() {
		return this.move;
	}

	public void setMove(boolean m) {
		this.move = m;
	}

	public int getC() {
		return this.color;
	}

	// Sets coordinates of parcel
	public void parcelCoordinates() {

		this.xLeft[0] = this.getX();
		this.yLeft[0] = this.getY();
		this.xLeft[1] = this.getX();
		this.yLeft[1] = this.getY() + this.getH();
		this.xLeft[2] = this.getX() - ((int) this.getD() / 2);
		this.yLeft[2] = (this.getY() + this.getH()) - ((int) this.getD() / 2);
		this.xLeft[3] = this.getX() - ((int) this.getD() / 2);
		this.yLeft[3] = this.getY() - ((int) this.getD() / 2);

		this.yRight[0] = this.getY();
		this.xRight[0] = this.getX();
		this.yRight[1] = this.getY();
		this.xRight[1] = this.getX() + this.getW();
		this.xRight[2] = (this.getX() + this.getW()) - ((int) this.getD() / 2);
		this.yRight[2] = this.getY() - ((int) this.getD() / 2);
		this.xRight[3] = this.getX() - ((int) this.getD() / 2);
		this.yRight[3] = this.getY() - ((int) this.getD() / 2);
	}

	// Paints the parcels
	public void paint(Graphics2D g) {

		// Obtains the parcel coordinates
		parcelCoordinates();

		// Front of parcel
		g.setColor(Color.black);
		g.drawRect(this.getX(), this.getY(), this.getW(), this.getH());

		// Sets color of parcel
		if (this.getC() == 1) {
			g.setColor(Color.blue);

		} else if (this.getC() == 2) {
			g.setColor(Color.green);

		} else if (this.getC() == 3) {
			g.setColor(Color.yellow);

		}

		// Fills rectangles and polygons using coordinates obtained above
		g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
		g.fillPolygon(this.xLeft, this.yLeft, 4);
		g.fillPolygon(this.xRight, this.yRight, 4);

		g.setColor(Color.BLACK);
		g.drawRect(this.getX(), this.getY(), this.getW(), this.getH());
		g.drawLine(this.getX(), this.getY(), this.xLeft[2], this.yLeft[3]);
		g.drawLine(this.xLeft[2], this.yLeft[3], this.xLeft[2], this.yLeft[2]);
		g.drawLine(this.xLeft[3], this.yLeft[2], this.getX(), this.yLeft[1]);
		g.drawLine(this.xLeft[2], this.yLeft[3], this.xRight[2], this.yLeft[3]);
		g.drawLine(this.xRight[2], this.yLeft[3], this.xRight[1], this.yRight[1]);

		// Allows parcel to move forward
		if (this.getMove()) {
			this.setX(this.getX() + 1);
		}

		// Sorts the parcel based on color, adjusts height if necessary to go to
		// different belt
		if (this.getX() >= 400) {
			this.setMove(true);
			if (this.getC() == 1 && this.getX() >= 550) {

				this.setY(150 - this.getH());
			} else if (this.getC() == 3 && this.getX() >= 550) {
				this.setY(420 - this.getH());

			}
		}

	}

	// Obtains keyboard input
	public void keyPressed(KeyEvent e) {
		
		// Stops the parcel if the space bar is pressed, and vice versa 
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.setMove(!this.getMove());
		}

	}

}
