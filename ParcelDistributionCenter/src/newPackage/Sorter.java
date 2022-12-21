package newPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Sorter {

	// Fields required to create sorter object
	private BufferedImage img1;
	private BufferedImage img2;
	private BufferedImage img3;

	// Initializing fields through constructor
	public Sorter() {

		try {
			this.img1 = ImageIO.read(new File("scanner.png"));
		} catch (IOException e) {
			System.err.println("Error whilst importing image");
		}

		try {
			this.img2 = ImageIO.read(new File("scannerback.png"));
		} catch (IOException e) {
			System.err.println("Error whilst importing image");
		}
		try {
			this.img3 = ImageIO.read(new File("brand.png"));
		} catch (IOException e) {
			System.err.println("Error whilst importing image");
		}

	}

	// Accesor Methods
	public BufferedImage getImg1() {
		return img1;
	}

	public BufferedImage getImg2() {
		return img2;
	}
	
	public BufferedImage getImg3() {
		return img3;
	}

	// Painting the scanner and brand logo
	public void paint(Graphics2D g) {

		g.drawImage(getImg1(), 350, 10, 275, 520, null);
		g.drawImage(getImg3(), 405, 250, null); 

	}

	// Painting the back half of the scanner
	public void paint_back(Graphics2D g) {

		g.drawImage(getImg2(), 320, 10, 30, 520, null);

	}

}
