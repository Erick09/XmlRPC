package br.ifce.teste;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

import client.j2se.BufferedImageLuminanceSource;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class TesteDecoder implements Runnable{
	
	IplImage image;
	CanvasFrame canvas = new CanvasFrame("Web Cam"); 
	public TesteDecoder() { 
		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
	}
	
	public static void main(String[] args)
	{
		TesteDecoder t1 = new TesteDecoder();
		Thread t = new Thread(t1);
		t.run();
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
					
					LuminanceSource source = new BufferedImageLuminanceSource(im);
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
					Result result = null;
					try {
						result = new MultiFormatReader().decode(bitmap);
					} catch (ReaderException re) {
						System.out.println(re.toString());
					}
					System.out.println(String.valueOf(result.getText()));
					
					break;
					 }
					//Thread.sleep(INTERVAL);
				}
				
			} catch(Exception e){
				e.printStackTrace();
		}

	}
}
