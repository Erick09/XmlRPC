package br.ifce.xmlrpcclient_android;

public class CalculadoraCliente {
	
	private ClienteXmlRpc cliente;
	
	public CalculadoraCliente() {
		cliente = new ClienteXmlRpc();
	}

	public int soma(int x, int y) {
		Object[] parametros = new Object[]{new Integer(x), new Integer(y)};
		Integer resultado = (Integer) cliente.executar("Calc.soma", parametros);
		return resultado;
	}

	public int subtracao(int x, int y) {
		Object[] parametros = new Object[]{new Integer(x), new Integer(y)};
		Integer resultado = (Integer) cliente.executar("Calc.subtracao", parametros);
		return resultado;
	}
	
	public String exibe(String x, String y) {
		Object[] parametros = new Object[]{new String(x), new String(y)};
		String resultado = (cliente.executar("Calc.exibe", parametros)).toString();
		return resultado;

	}

}
