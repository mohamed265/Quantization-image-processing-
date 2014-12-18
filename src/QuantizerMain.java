package Quantizer_MY;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class QuantizerMain {

	private static int getRed(int pixel) {
		return (pixel >> 16) & 0xff;
	}

	private static int getGreen(int pixel) {
		return (pixel >> 8) & 0xff;
	}

	private static int getBlue(int pixel) {
		return (pixel) & 0xff;
	}

	public static long compress(String originalpath, int level,
			String extention, String newPath) throws IOException {
		int Rang = 256 / level, bit = (int) (Math.log(level) / Math.log(2)), pixel, newPixal;
		BufferedImage img = ImageIO.read(new File(originalpath));

		long MSE = 0;
		int width = img.getWidth();
		int height = img.getHeight();

		BufferedImage sav = new BufferedImage(width, height, img.getType());

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixel = img.getRGB(i, j);
				newPixal = new Color((getRed(pixel) / Rang) * Rang + Rang / 2,
						(getGreen(pixel) / Rang) * Rang + Rang / 2,
						(getBlue(pixel) / Rang) * Rang + Rang / 2).getRGB();
				MSE += Math.pow(newPixal - pixel,  2);
				sav.setRGB(i, j, newPixal);
			}
		}

		File out = new File(newPath + "." + extention);
		ImageIO.write(sav, extention, out);
		new Prev(ImageIO.read(out), out.getName().substring(0,
				out.getName().indexOf(".")));
		return MSE / (width * height);
	}

	public static void main(String[] args) {
		// compress("1.jpg", 8, "jpg", "2");
		new GUI();
	}

}
