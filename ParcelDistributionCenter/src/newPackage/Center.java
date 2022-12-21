package newPackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Center extends JPanel {

	// Fields required to create parcel distribution center
	private Parcels[] p = new Parcels[20];
	private ConveyorBelt[] belt = new ConveyorBelt[4];
	private Sorter s = new Sorter();
	private BufferedImage img;
	private Random r = new Random();
	private int[] num = new int[1000];
	private Person[] worker = new Person[4];

	// Initializing fields through constructor
	public Center() {

		// Creates 4 conveyor belts
		belt[0] = new ConveyorBelt(-20, 455, 260, true, belt, p);
		belt[1] = new ConveyorBelt(620, 1095, 120, false, belt, p);
		belt[2] = new ConveyorBelt(620, 1095, 260, false, belt, p);
		belt[3] = new ConveyorBelt(620, 1095, 390, false, belt, p);
		worker[0] = new Person(1020, 490);
		worker[1] = new Person(1020, 490);
		worker[2] = new Person(1020, 490);
		worker[3] = new Person(1020, 490);

		// Fills array of 1000 integers from numbers 1-3
		for (int i = 0; i < 1000; i++) {
			num[i] = r.nextInt(3) + 1;
		}

		// Creates 20 parcels
		for (int i = 0; i < p.length; i++) {
			p[i] = new Parcels(true, -50 - (i * 300), 200, r.nextInt(31) + 20, r.nextInt(31) + 20, num[(i*23)+7],
					r.nextInt(41) + 10);
		}

		// Obtain the background image
		try {
			this.img = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			System.err.println("Error whilst importing image");
		}

		// Create key listener to obtain keyboard input
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				// Passes the KeyEvent e to the parcel instance
				for (Parcels i : p) {
					i.keyPressed(e);
				}

				belt[0].keyPressed(e);
			}
		}); // NOTE THE SEMI-COLON!!!!
		// Making sure our JPanel has the focus and therefore it is the
		// instance that will receive the keyboard input
		setFocusable(true);

	}

	// Accessor to obtain background image
	public BufferedImage getImg() {
		return this.img;
	}

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		// Smmoths out the movement
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Paints the background
		g2d.drawImage(getImg(), 0, 0, 1020, 640, null);

		// Paints back and inside of scanner
		int[] x = { 322, 390, 390, 322 };
		int[] y = { 170, 205, 375, 340 };
		g2d.fillPolygon(x, y, 4);
		s.paint_back(g2d);

		// Paints conveyoor belts and parcels
		for (ConveyorBelt c : belt) {
			c.paint(g2d);
		}
		for (Parcels i : p) {
			i.paint(g2d);
		}

		// Paints front of scanner
		s.paint(g2d);

		// Paints scanner light
		g2d.setColor(Color.red.darker());
		g2d.fillRect(455, 215, 105, 20);

		for (int i = 0; i < p.length; i++) {
			if (p[i].getX() >= 400) {
				g2d.setColor(Color.red);
				g2d.fillRect(455, 215, 105, 20);
			}
			if (p[i].getX() >= 550) {
				g2d.setColor(Color.red.darker());
				g2d.fillRect(455, 215, 105, 20);
			}
			g2d.setColor(Color.black);
			g2d.drawString("Scan In Progress", 460, 230);
		}

		// Paints person and departure message
		if (belt[0].getB() == 5) {
			worker[0].paint(g2d);
			worker[0].move();
			if (worker[0].getX() <= 901) {
				g2d.setColor(Color.orange);
				g2d.fillRect(695, 510, 175, 45);
				int[] xtri = { 870, 870, 905 };
				int[] ytri = { 510, 555, 505 };
				g2d.fillPolygon(xtri, ytri, 3);
				g2d.setColor(Color.black);
				g2d.drawRect(695, 510, 175, 45);
				g2d.drawPolygon(xtri, ytri, 3);
				g2d.drawString("The Paris flight will depart at", 700, 525);
				g2d.drawString("23:56 on Runway 07-L.", 700, 545);
			}
			if (worker[0].getX() == 900) {

				worker[0].setXA(0);

				try {

					Thread.sleep(5000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				worker[0].setXA(1);
				worker[0].move();
			}
		}

		if (belt[0].getB() == 10) {
			worker[1].paint(g2d);
			worker[1].move();
			if (worker[1].getX() <= 901) {
				g2d.setColor(Color.orange);
				g2d.fillRect(695, 510, 175, 45);
				int[] xtri = { 870, 870, 905 };
				int[] ytri = { 510, 555, 505 };
				g2d.fillPolygon(xtri, ytri, 3);
				g2d.setColor(Color.black);
				g2d.drawRect(695, 510, 175, 45);
				g2d.drawPolygon(xtri, ytri, 3);
				g2d.drawString("The Tokyo flight will depart", 700, 525);
				g2d.drawString("at 02:13 from Runway 32-R.", 700, 545);
			}
			if (worker[1].getX() == 900) {

				worker[1].setXA(0);

				try {

					Thread.sleep(5000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				worker[1].setXA(1);
				worker[1].move();
			}
		}

		if (belt[0].getG() == 5) {
			worker[2].paint(g2d);
			worker[2].move();
			if (worker[2].getX() <= 901) {
				g2d.setColor(Color.orange);
				g2d.fillRect(695, 510, 175, 45);
				int[] xtri = { 870, 870, 905 };
				int[] ytri = { 510, 555, 505 };
				g2d.fillPolygon(xtri, ytri, 3);
				g2d.setColor(Color.black);
				g2d.drawRect(695, 510, 175, 45);
				g2d.drawPolygon(xtri, ytri, 3);
				g2d.drawString("The P.E.I. truck will depart at", 700, 525);
				g2d.drawString("00:57 from Dock C.", 700, 545);
			}
			if (worker[2].getX() == 900) {

				worker[2].setXA(0);

				try {

					Thread.sleep(5000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				worker[2].setXA(1);
				worker[2].move();
			}
		}

		if (belt[0].getG() == 10) {
			worker[3].paint(g2d);
			worker[3].move();
			if (worker[3].getX() <= 901) {
				g2d.setColor(Color.orange);
				g2d.fillRect(695, 510, 175, 45);
				int[] xtri = { 870, 870, 905 };
				int[] ytri = { 510, 555, 505 };
				g2d.fillPolygon(xtri, ytri, 3);
				g2d.setColor(Color.black);
				g2d.drawRect(695, 510, 175, 45);
				g2d.drawPolygon(xtri, ytri, 3);
				g2d.drawString("The Québéc truck will depart", 700, 525);
				g2d.drawString("at 01:49 from Dock E.", 700, 545);
			}
			if (worker[3].getX() == 900) {

				worker[3].setXA(0);

				try {

					Thread.sleep(5000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				worker[3].setXA(1);
				worker[3].move();
			}
		}

	}

	// Driver method
	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame("Parcel Distribution Center");

		Center c = new Center();

		frame.add(c);

		frame.setSize(1020, 640);

		frame.setVisible(true);

		frame.setResizable(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			c.repaint();
			Thread.sleep(10);
		}

	}
}
