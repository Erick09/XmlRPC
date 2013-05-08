package br.ifce.xmlrpcclient_android;

import android.util.Log;

public class CalculadoraCliente {
	
	private ClienteXmlRpc cliente;
	
	public CalculadoraCliente() {
		cliente = new ClienteXmlRpc();
	}
	
	public String exibe(String x, String y) {
		Object[] parametros = new Object[]{new String(x), new String(y)};
		String resultado = (String) (cliente.executar("Calc.exibe", parametros));
		Log.i("ET", resultado);
		return resultado;

	}

}
