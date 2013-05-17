
package br.ifce.teste;

/*
 * Classe encontrada em http://www.guj.com.br/java/135814-zxing
 * Cria um novo QRCode que armazena o nome atribuido a ele
 */

import java.io.File;  
import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.nio.CharBuffer;  
import java.nio.charset.CharacterCodingException;  
import java.nio.charset.Charset;  
import java.nio.charset.CharsetEncoder;  
import java.util.StringTokenizer;
  
import jp.sourceforge.qrcode.QRCodeDecoder;  
  
import client.j2se.MatrixToImageWriter;

import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.QRCodeWriter;  
  
public class Exemplo {  
	
	private String nome;
	
	/* Construtor da classe
	 * Recebe uma String que representara o nome do QRCode 
     */
	Exemplo(String s){
		nome = s;
	}
  
	/* 
	 * Decodifica o QRCode indicado pelo atalho
     */
    public void decode() {
    	System.out.println("Decodificando...");  
        QRCodeDecoder decoder = new QRCodeDecoder();  
        byte[] bytes = decoder.decode(new MyImage("C:\\xampp\\htdocs\\image2.jpg"));  
  
        String result = new String(bytes);  
        System.out.println(result);  
    }  
  
    /* 
     * Gera o QRCode com base no nome atribuido a ele
     */
    
    public String encode() {  
        System.out.println("Codificando...");  
        
        Charset charset = Charset.forName("ISO-8859-1");  
        CharsetEncoder encoder = charset.newEncoder();  
        byte[] b = null;  
        try {  
            // Convert a string to ISO-8859-1 bytes in a ByteBuffer  
            java.nio.ByteBuffer bbuf = encoder.encode(CharBuffer  
                    .wrap(nome));  
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
        int h = 250;  
        int w = 250;  
        com.google.zxing.Writer writer = new QRCodeWriter();  
        try {  
            matrix = writer.encode(data,  
                    com.google.zxing.BarcodeFormat.QR_CODE, w, h);  
        } catch (com.google.zxing.WriterException e) {  
            System.out.println(e.getMessage());  
        }  
        
        StringTokenizer nom = new StringTokenizer(nome,":");
        String sal = nom.nextToken()+"_"+nom.nextToken()+"_"+nom.nextToken()+"_"+nom.nextToken();        
        
        String filePath = "C:\\xampp\\htdocs\\" + sal + ".jpg";  
        File file = new File(filePath);  
        try {  
            MatrixToImageWriter.writeToFile(matrix, "JPG", file);  
            System.out.println("printing to " + file.getAbsolutePath()); 
            return file.getAbsolutePath();
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
        
        return "";
    }    
  
}  