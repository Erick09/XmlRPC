package br.ifce.teste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class ServidorXMLRPCParaTestes {
	
	private static ServidorXMLRPCParaTestes euMesmo = null;

	private ServidorXMLRPCParaTestes() {
		try {
			final WebServer server = new WebServer(8888); // Cria um servidor na porta 8888
			XmlRpcServer servidor = server.getXmlRpcServer(); // Pega o servidor XmlRpc
			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			phm.addHandler("Calc", Calculadora.class); // Adiciona um novo "handler" ao PHM
			servidor.setHandlerMapping(phm); // Define o handler no servidor
			server.start(); // inicia o servidor.
			
			
			int delay = 60000; //milliseconds
	        ActionListener taskPerformer = new ActionListener() {
	                public void actionPerformed(ActionEvent evt) {
	                   server.shutdown();
	                }
	        };
	        new Timer(delay, taskPerformer).start();
			
		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}

	public static ServidorXMLRPCParaTestes obterInstancia() {
		if (euMesmo == null)
			euMesmo = new ServidorXMLRPCParaTestes();
		return euMesmo;
	}

}