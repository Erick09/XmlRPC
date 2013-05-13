package br.ifce.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConectaBanco {
	
	private String url;
    private String login;
    private String senha;

    public ConectaBanco(String url, String login, String senha) {
        setUrl(url);
        setLogin(login);
        setSenha(senha);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public void insere(String s, String msg) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //System.out.println("\n Salvando URL: ...\n");
            try {
                Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getSenha());
                try {
                    String sql = s;
                    System.out.println(s);
                    Statement stm = conn.createStatement();
                    try {
                        stm.executeUpdate(sql);
                        System.out.println(msg);
                    } catch (Exception ex) {
                        System.out.println("\nErro no resultset!\n" + ex);
                        ex.printStackTrace();
                    }
                } catch (Exception ex) {
                    System.out.println("\nErro no statement!");
                }
            } catch (Exception ex) {
                System.out.println("\nErro no connection!");
            }
        } catch (Exception ex) {
            System.out.println("\nDriver nao pode ser carregado!");
        }

    }
    
    public ArrayList busca(String s) {
        ArrayList amigo = new ArrayList();
        Obqrcode a = new Obqrcode();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //System.out.println("\n Salvando URL: ...\n");
            try {
                Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getSenha());
                try {
                    String sql = s;
                    Statement stm = conn.createStatement();
                    try {
                        ResultSet rs = stm.executeQuery(sql);
                        while (rs.next()) {
                            a.setUsuario(rs.getString(1));
                            a.setProduto(rs.getString(2));
                            a.setQuantidade(rs.getInt(3));
                            a.setId(rs.getInt(4));
                            amigo.add(a);
                        }
                        //System.out.println(rs.getInt(1));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } catch (Exception ex) {
                    System.out.println("\nErro no statement!");
                }
            } catch (Exception ex) {
                System.out.println("\nErro no connection! " + ex);
            }
        } catch (Exception ex) {
            System.out.println("\nDriver nao pode ser carregado!");
        }
        return amigo;
    }
}



