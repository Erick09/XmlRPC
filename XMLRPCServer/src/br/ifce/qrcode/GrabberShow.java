package br.ifce.qrcode;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.geom.Line;
import jp.sourceforge.qrcode.geom.Point;
import jp.sourceforge.qrcode.util.DebugCanvas;

import com.googlecode.javacv.CanvasFrame; 
import com.googlecode.javacv.FrameGrabber; 
import com.googlecode.javacv.VideoInputFrameGrabber; 
import com.googlecode.javacv.cpp.opencv_core.IplImage; 

public class GrabberShow implements Runnable { 
	//final int INTERVAL=1000;///you may use interval 
	IplImage image;
	CanvasFrame canvas = new CanvasFrame("Web Cam"); 
	public GrabberShow() { 
		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
	}
	
	@Override public void run() {
		FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera 
		//int i=0; 
		try { 
			Thread.sleep(2000);
			grabber.start(); 
			IplImage img; 
			while (true) {
				//grabber.setImageHeight(200);
				//grabber.setImageWidth(200);
				img = grabber.grab(); 
				if (img != null) { 
					//cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise 
					//cvSaveImage("teste.jpg", img);
					
					
					BufferedImage im = img.getBufferedImage();
					
					File outputfile = new File("C:\\xampp\\htdocs\\image2.jpg");
					ImageIO.write(im, "jpg", outputfile);

					// show image on window 
					canvas.showImage(img);
					
					QRCodeDecoder decoder = new QRCodeDecoder();
					J2SEDebugCanvas canvas = new J2SEDebugCanvas();
					QRCodeDecoder.setCanvas(canvas);
					String decodedString = null;
					try {
						byte[] decodedBytes = decoder.decode(new J2SEImage(im));
						decodedString = new String(decodedBytes);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					//decodedString = ContentConverter.convert(decodedString);
					canvas.println("\nDecode result:");
					canvas.println(decodedString);
					canvas.println("--------");
				
//					QRCodeDecoder decoder = new QRCodeDecoder();
//					J2SEDebugCanvas imgmod = new J2SEDebugCanvas();
//					QRCodeDecoder.setCanvas(imgmod);
//					String decodedString = null;
//					try {
//						decodedString = new String(decoder.decode(new J2SEImage(im)));
//					} catch (DecodingFailedException e) {
//						imgmod.println(e.getMessage());
//						imgmod.println("--------");
//						return;
//					}
//					decodedString = ContentConverter.convert(decodedString);
//					imgmod.println("\nResultado da decodificação:");
//					imgmod.println(decodedString);
//					imgmod.println("--------");
					
					
					
					break;
					 }
					//Thread.sleep(INTERVAL);
				}
				
			} catch(Exception e){
				e.printStackTrace();
		}
	}
	
}

class J2SEDebugCanvas extends Canvas implements DebugCanvas {
	BufferedImage image;

	public void paint(Graphics g){
		if (image != null)
			g.drawImage(image, 0, 0, java.awt.Color.WHITE, null);
	}
	
	public  void println(String string){
		System.out.println(string);
	}
	
	public  void drawMatrix(boolean[][] matrix) {
		if (image == null) {
			image = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_ARGB);
			setSize(matrix.length, matrix[0].length);
		}
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(java.awt.Color.WHITE);
		int width = getWidth();
		for (int x = 0; x < matrix.length; x++) {
			g2d.drawLine(x, 0, x, width);
		}
		g2d.setColor(java.awt.Color.BLACK);
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[0].length; y++) {
				if (matrix[x][y] == true)
					g2d.drawLine(x, y, x, y);
			}
		}
		repaint();
	}

	public  void drawLine(Line line, int color){
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(new Color(color));
		g2d.drawLine(line.getP1().getX(), line.getP1().getY(),
					line.getP2().getX(), line.getP2().getY());
		repaint();
	}

	public  void drawLines(Line[] lines, int color){
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(new Color(color));
		for (int i = 0; i < lines.length; i++) {
			g2d.drawLine(lines[i].getP1().getX(), lines[i].getP1().getY(),
					lines[i].getP2().getX(), lines[i].getP2().getY());
		}
		repaint();
	}
	
	public  void drawPolygon(Point[] points, int color){
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(new Color(color));
		int numPoints = points.length;
		int[] polygonX = new int[numPoints];
		int[] polygonY = new int[numPoints];
		for (int i = 0; i < numPoints; i++) {
			polygonX[i] = points[i].getX();
			polygonY[i] = points[i].getY();
		}
		g2d.drawPolygon(polygonX, polygonY, numPoints);
		repaint();
	}

	public  void drawPoints(Point[] points, int color){
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(new Color(color));
		for (int i = 0; i < points.length; i++)
			g2d.drawLine(points[i].getX(), points[i].getY(),points[i].getX(), points[i].getY());
		repaint();

	}

	public  void drawPoint(Point point, int color){
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(new Color(color));
		g2d.drawLine(point.getX(), point.getY(),point.getX(), point.getY());
		repaint();

	}

	public  void drawCross(Point point, int color){
		int x = point.getX();
		int y = point.getY();

		Line[] lines = {
			new Line(x - 5, y, x + 5, y),new Line(x, y - 5, x ,y + 5)
		};
		drawLines(lines, color);
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}

class J2SEImage implements QRCodeImage {
	BufferedImage image;

	public J2SEImage(BufferedImage image) {
		this.image = image;
	}

	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}

	public int getPixel(int x, int y) {
		return image.getRGB(x, y);
	}
}
