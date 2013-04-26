package br.ifce.xmlrpcclient_android;

import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class ClienteXmlRpc {
	
	private static final String urlServidor = "http://10.0.2.2:8888"; //DEFINE A URL DO SERVIDOR //porta:8888
	private XmlRpcClient xmlrpc;

	public ClienteXmlRpc() {
		try {
			XmlRpcClientConfigImpl configuracaoCliente = new XmlRpcClientConfigImpl();
			configuracaoCliente.setServerURL(new URL(urlServidor));

			xmlrpc = new XmlRpcClient();
			xmlrpc.setConfig(configuracaoCliente);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public Object executar(String comando, Object[] parametros) {
		try {
			
			Object resposta = xmlrpc.execute(comando, parametros);
			return resposta;
		} catch (XmlRpcException e) {
			e.printStackTrace();
			return null;
		}
	}

}
