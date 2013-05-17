package br.ifce.teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MontaRes {
	
	private String res;
	int numdoPedido;
	
	public MontaRes() {
		res="<!DOCTYPE html>"+
		  		   "<html>"+
		  				"<head>"+
		  					"<title>Lista de Pedidos</title>"+
		  					"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
		  					"<!-- Bootstrap -->"+
		  					"<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">"+
		  					"<script type=\"text/javascript\">"+
		  					 	"var intervalo = window.setInterval(recarrega, 1000);"+
		  					 	"function recarrega(){"+
		  					 	"	JSInterface.testasub();}"+
		  					"</script>"+
		  						  					
		  				"</head>"+
		  				"<body>"+
		  					"<div class=\"navbar navbar-inverse\">"+
		  						"<div class=\"navbar-inner\">"+
		  							"<a class=\"brand\" >Lista de Pedidos</a>"+
		  						"</div>"+
		  					"</div>"+
		  					"<div class=\"accordion\" id=\"accordion2\">";
		
		numdoPedido=0;
	}
	
	public void capturaLista()
	{
		ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");	
		String res;
		
		ArrayList<Obqrcode> a = new ArrayList<Obqrcode>();
        a =  cb.busca("SELECT * FROM qrcode.cadastro WHERE Usuario LIKE 'Usuario1';"); 
        int i=0;
        while(a.size()>i){
        	Obqrcode ab = a.get(i);
        	if(ab.getValido()==1){
        		res = ab.getUsuario()+"_"+ab.getProduto()+"_"+ab.getQuantidade()+"_"+ab.getId()+".jpg";
        		addRes(res);
        	} else{
        		res = "nao achamos seu Produto";
        	}
        	i++;
        }
	}
	
	public void addRes(String s){
		if(numdoPedido==0)
		res+=	"<div class=\"accordion-group\">"+
					"<div class=\"accordion-heading\">"+
						"<a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion2\" href=\"#collapse"+(numdoPedido+1)+"\">"+
							"Pedido "+(numdoPedido+1)+
						"</a>"+
					"</div>"+
					"<div id=\"collapse"+(numdoPedido+1)+"\" class=\"accordion-body collapse in\">"+
						"<div class=\"accordion-inner\">"+
							"<img src="+s+">"+
						"</div>"+
					"</div>"+
				"</div>";
		else
			res+=	"<div class=\"accordion-group\">"+
					"<div class=\"accordion-heading\">"+
						"<a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion2\" href=\"#collapse"+(numdoPedido+1)+"\">"+
							"Pedido "+(numdoPedido+1)+
						"</a>"+
					"</div>"+
					"<div id=\"collapse"+(numdoPedido+1)+"\" class=\"accordion-body collapse\">"+
						"<div class=\"accordion-inner\">"+
							"<img src="+s+">"+
						"</div>"+
					"</div>"+
				"</div>";
		
		numdoPedido++;
	}
	
	public void endRes()
    {
    	res +="</div>"+
    		  "<script src=\"http://code.jquery.com/jquery.js\"></script>"+
			  "<script src=\"js/bootstrap.min.js\"></script>"+
		  "</body>"+
        "</html>";
    	
    	File arquivo;   
		arquivo = new File("C:\\xampp\\htdocs\\resp.html");  // Chamou e nomeou o arquivo txt.  
        FileOutputStream fos;
		try {
			fos = new FileOutputStream(arquivo);// Perceba que estamos instanciando uma classe aqui. A FileOutputStream. Pesquise sobre ela!   
        	fos.write(res.getBytes());          
        	fos.close();  // Fecha o arquivo. Nunca esquecer disso.  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	

}
