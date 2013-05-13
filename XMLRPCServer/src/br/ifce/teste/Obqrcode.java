package br.ifce.teste;

import java.util.ArrayList;

public class Obqrcode {
	
	String usuario;
	String produto;
	int quantidade;
	int id;
	
	public Obqrcode() {
		// TODO Auto-generated constructor stub
	}
	
	public Obqrcode(String u, String p, int q) {
		usuario = u;
		produto = p;
		quantidade=q;
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
	
	public void salvar()
	{
		ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");	
		        
        cb.insere("INSERT INTO  qrcode.cadastro  VALUES"+
                "('" + getUsuario() + "', '" + getProduto() +
                 "', '" + getQuantidade() + "', NULL);"
                 , "Qrcode gravado corretamente...");
	}
	
	public String recuperar()
	{
		ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");	
		String res;
		
		ArrayList a = new ArrayList();
        a =  cb.busca("SELECT * FROM qrcode.cadastro WHERE Usuario LIKE '" + getUsuario() + "';"); //'Erick'
        if (a.size()>0){
            Obqrcode ab = (Obqrcode) a.get(0);
            res = ab.getUsuario()+":"+ab.getProduto()+":"+ab.getQuantidade()+":"+ab.getId();
        }
        else{
            res = "nao achamos seu amigo :(  ";
        }
        
        return res;
	}

}
