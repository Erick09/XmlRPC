package br.ifce.teste;

import java.util.ArrayList;

public class Obqrcode {
	
	String usuario;
	String produto;
	int quantidade;
	int id;
	int valido;
	
	public Obqrcode() {
		usuario = "";
		produto = "";
		quantidade=0;
		valido = 0;
	}
	
	public Obqrcode(String u, String p, int q, int v) {
		usuario = u;
		produto = p;
		quantidade=q;
		valido = v;
	}
	
	public int getId() {
		return id;
	}

	public String getProduto() {
		return produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public int getValido() {
		return valido;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setValido(int valido) {
		this.valido = valido;
	}
	
	public void invalidaqr(int id)
	{
		ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");
		
		cb.invalidaQr("UPDATE qrcode.cadastro SET Valido = 0 WHERE `cadastro`.`ID` = "+ id +";");;

	}
	
	public void salvar()
	{
		ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");	
		        
        cb.insere("INSERT INTO  qrcode.cadastro  VALUES"+
                "('" + getUsuario() + "', '" + getProduto() +
                 "', '" + getQuantidade() + "', NULL,'" + getValido() + "');"
                 , "Qrcode gravado corretamente...");
	}
	
	public String recuperar()
	{
		ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");	
		String res;
		
		ArrayList a = new ArrayList();
        a =  cb.busca("SELECT * FROM qrcode.cadastro WHERE Usuario LIKE '" + getUsuario() + "';"); 
        if (a.size()>0){
            Obqrcode ab = (Obqrcode) a.get(0);
            res = ab.getUsuario()+":"+ab.getProduto()+":"+ab.getQuantidade()+":"+ab.getId();
        } else{
            res = "nao achamos seu Produto";
        }
        
        return res;
	}
	
	public String recuperar(int i)
	{
		ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");	
		String res;
		
		ArrayList a = new ArrayList();
        a =  cb.busca("SELECT * FROM qrcode.cadastro WHERE Usuario LIKE '" + getUsuario() + "';"); 
        if (a.size()>0){
            Obqrcode ab = (Obqrcode) a.get(i);
            res = ab.getUsuario()+":"+ab.getProduto()+":"+ab.getQuantidade()+":"+ab.getId();
        } else{
            res = "nao achamos seu Produto";
        }
        
        return res;
	}

}
