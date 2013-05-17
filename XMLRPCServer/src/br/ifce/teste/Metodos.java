package br.ifce.teste;

import java.io.File;
import java.util.StringTokenizer;
/*
 * A classe que armazena os metodos do servidor que podem ser acessados remotamente
 */


public class Metodos {

	static MontaRes ms;
	int induser=0;

	public int soma(int x, int y) {
		return x + y;
	}
	
	public int subtracao(int x, int y) {
		return x - y;
	}
	
	public String exibe(String c, String a, String b)
	{
		System.out.println(a+" "+b);
		return a+" "+b;
	}
	
	/* Recebe os dados fornecidos pelo Formulario no Android
	 * Retorna a url do QRCode gerado pelo servidor
	 */
	public String urlQr(String usuario, String item, int qtd)
	{

    	System.out.println(usuario+"\n"+item);
		Obqrcode us = new Obqrcode(usuario,item,qtd,1); 
		us.salvar();
		
		Exemplo exe = new Exemplo(us.recuperar(induser));
		String res = exe.encode();
		StringTokenizer st = new StringTokenizer(res, "\\");
		int r = st.countTokens();
		do{
			st.nextToken();
			r--;
		}while(r>1);
		String nomeimg = st.nextToken();
		System.out.println(nomeimg);
		String url = "http://10.0.2.2\\"+nomeimg;
		
		induser++;
		
		return url;
	}
	
	public String mountRes(String s)
	{
		if(ms==null)
			ms = new MontaRes();
		ms.capturaLista();
		ms.endRes();
		ms = null;
		
		return "";
	}
	
	public String testasub(String s)
	{
		String filePath = "C:\\xampp\\htdocs\\resp.html";  
        File file = new File(filePath);
        
        long s1 = file.length();
        
        
		ms = new MontaRes();
		ms.capturaLista();
		ms.endRes();
		
		long s2 = file.length();
				       
		if(s2 != s1){
			return 1+"";
		}
			
		return 0+"";
	}

}