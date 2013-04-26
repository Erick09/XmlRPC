package br.ifce.teste;

public class Calculadora {
	
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

}