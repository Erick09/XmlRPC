package br.ifce.qrcode;

import java.io.Serializable;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Auxiliar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IplImage img;
	
	Auxiliar(IplImage img)
	{
		this.img = img;
	}

}
