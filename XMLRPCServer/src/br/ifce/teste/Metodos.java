package br.ifce.teste;

import java.util.StringTokenizer;

public class Metodos {
	
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
	
	public String urlQr(String usuario, String item, int qtd)
	{
		Exemplo exe = new Exemplo(usuario+item+qtd);
		String url = exe.encode();
		StringTokenizer st = new StringTokenizer(url, "\\");
		int r = st.countTokens();
		do{
			st.nextToken();
			r--;
		}while(r>1);
		String res = "http://10.0.2.2\\"+st.nextToken();
		return res;
	}

}