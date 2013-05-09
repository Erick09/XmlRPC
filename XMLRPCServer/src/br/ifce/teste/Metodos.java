package br.ifce.teste;

import java.util.StringTokenizer;
/*
 * A classe que armazena os metodos do servidor que podem ser acessados remotamente
 */


public class Metodos {

	static MontaRes ms;

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
		Exemplo exe = new Exemplo(usuario+item+qtd);
		String res = exe.encode();
		StringTokenizer st = new StringTokenizer(res, "\\");
		int r = st.countTokens();
		do{
			st.nextToken();
			r--;
		}while(r>1);
		String nomeimg = st.nextToken();
		System.out.println(nomeimg);
		if(ms==null)
			ms = new MontaRes();
		ms.addRes(nomeimg);
		String url = "http://10.0.2.2\\"+nomeimg;
		return url;
	}
	
	/* Recebe o produto clicado no WebView
	 * Retorna a url do QRCode gerado pelo servidor
	 */
	public String urlQrcode(String produto)
	{
		Exemplo exe = new Exemplo(produto);
		String res = exe.encode();
		StringTokenizer st = new StringTokenizer(res, "\\");
		int r = st.countTokens();
		do{
			st.nextToken();
			r--;
		}while(r>1);
		String nomeimg = st.nextToken();
		ms.addRes(nomeimg);
		String url = "http://10.0.2.2\\"+nomeimg;
		return url;
	}
		
	public String mountRes(String s)
	{
		if(ms==null)
			ms = new MontaRes();
		ms.endRes();
		ms = null;
		
		return "";
	}

}