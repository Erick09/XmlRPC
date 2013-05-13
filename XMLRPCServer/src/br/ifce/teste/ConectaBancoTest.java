package br.ifce.teste;

import java.util.ArrayList;
import java.util.Scanner;

public class ConectaBancoTest {

    public static void main(String[] args) {
        // TODO code application loSgic here
        Scanner ler  = new Scanner(System.in);
        ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost/qrcode", "root", "");

        int x=-1;
        while (x != 0) {
            System.out.println("Escolha uma opcao");
            System.out.println("1 - Cadastrar amigo");
            System.out.println("2 - Buscar Amigo");
            System.out.println("3 - Sair");
            x = Integer.parseInt(ler.nextLine());

            switch (x) {
                case 1: {
                    Obqrcode amigo = new Obqrcode();

                    System.out.println("Digite um nome:");
                    amigo.setUsuario(ler.nextLine());
                    System.out.println("Digite a data de nascimento:");
                    amigo.setProduto(ler.nextLine());
                    System.out.println("Digite o Telefone:");
                    amigo.setQuantidade(Integer.parseInt(ler.nextLine()));
                    
                    cb.insere("INSERT INTO  qrcode.cadastro  VALUES"+
                            "('" + amigo.getUsuario() + "', '" + amigo.getProduto() +
                             "', '" + amigo.getQuantidade() + "', NULL);"
                             , "Qrcode gravado corretamente...");
                    break;
                }
                case 2: {
                    ArrayList a = new ArrayList();
                    System.out.println("Digite o nome do seu amigo para procura:");
                    String nome = ler.nextLine();
                    a =  cb.busca("SELECT * FROM qrcode.cadastro WHERE"+ nome +"LIKE '%" + nome + "%';");
                    if (a.size()>0){
                        Obqrcode ab = (Obqrcode) a.get(0);
                        System.out.println(ab.getId()+ab.getProduto()+ab.getQuantidade()+ab.getUsuario());
                    }
                    else{
                        System.out.println("nao achamdos seu amigo :(  ");
                    }

                    break;
                }
                case 3: {
                    x=0;
                    ler.close();
                }
            }

        }
    }

}