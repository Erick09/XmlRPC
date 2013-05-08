package br.ifce.qrcode;

import java.io.File;  
import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.nio.CharBuffer;  
import java.nio.charset.CharacterCodingException;  
import java.nio.charset.Charset;  
import java.nio.charset.CharsetEncoder;  
  
import jp.sourceforge.qrcode.QRCodeDecoder;  
  
import client.j2se.MatrixToImageWriter;

import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.QRCodeWriter;  
  
public class Exemplo {
  
    public static void main(String[] args) {  
  
        encode();  
        decode();  
  
        System.out.println("Fim de execução.");  
    }  
  
    public static void decode() {  
        System.out.println("Decodificando...");  
        QRCodeDecoder decoder = new QRCodeDecoder();  
        
        byte[] bytes = decoder.decode(new MyImage("C:\\xampp\\htdocs\\image.jpg"));  
  
        String result = new String(bytes);  
        System.out.println(result);  
    }  
  
    public static void encode() {  
        System.out.println("Codificando...");  
  
        Charset charset = Charset.forName("ISO-8859-1");  
        CharsetEncoder encoder = charset.newEncoder();  
        byte[] b = null;  
        try {  
            // Convert a string to ISO-8859-1 bytes in a ByteBuffer  
            java.nio.ByteBuffer bbuf = encoder.encode(CharBuffer  
                    .wrap("http://www.google.com.br/"));  
            b = bbuf.array();  
        } catch (CharacterCodingException e) {  
            System.out.println(e.getMessage());  
        }  
  
        String data = null;  
        try {  
            data = new String(b, "ISO-8859-1");  
        } catch (UnsupportedEncodingException e) {  
            System.out.println(e.getMessage());  
        }  
  
        // get a byte matrix for the data  
        BitMatrix matrix = null;  
        int h = 100;  
        int w = 100;  
        com.google.zxing.Writer writer = new QRCodeWriter();  
        try {  
            matrix = writer.encode(data,  
                    com.google.zxing.BarcodeFormat.QR_CODE, w, h);  
        } catch (com.google.zxing.WriterException e) {  
            System.out.println(e.getMessage());  
        }  
  
        String filePath = "C:\\testimg.jpg";  
        File file = new File(filePath);  
        try {  
            MatrixToImageWriter.writeToFile(matrix, "JPG", file);  
            System.out.println("printing to " + file.getAbsolutePath());  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
  
}  