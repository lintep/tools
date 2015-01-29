package tools.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class ImageWrite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void saveGrayImage(int[][] imageData, String imageFileAddress)
			throws IOException {
		int width = imageData.length; // Dimensions of the image
		int height = imageData[0].length;
		// Let's create a BufferedImage for a gray level image.
		BufferedImage im = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		// We need its raster to set the pixels' values.
		WritableRaster raster = im.getRaster();
		// Put the pixels on the raster, using values between 0 and 255.
		for (int h = 0; h < height; h++)
			for (int w = 0; w < width; w++) {
				raster.setSample(w, h, 0, imageData[w][h]);
			}
		Logger.log("Writing ...");
		// Store the image using the PNG format.
		ImageIO.write(im, "PNG", new File(imageFileAddress + width + "-"
				+ height +"_"+tools.util.Time.getTimeStampAsName() +".png"));
	}
}
